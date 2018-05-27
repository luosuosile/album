package com.yz.album.api.test;

import com.yz.album.api.ApiVersion;
import com.yz.album.api.BaseApi;
import com.yz.album.api.dto.Album;
import com.yz.album.api.dto.ApiResponse;
import com.yz.album.api.dto.Comment;
import com.yz.album.api.dto.User;
import com.yz.album.util.IDUtil;
import org.jooq.DSLContext;
import org.jooq.Record1;
import org.jooq.SelectOffsetStep;
import org.jooq.impl.DSL;
import org.jooq.impl.DefaultDSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.text.resources.ar.FormatData_ar_LB;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/{version}/GMAction")
@ApiVersion(1)
public class GMAction extends BaseApi {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private List getFakeComment(){
        String s = null;
        List<String> comment = new ArrayList<String>();//comment
        try {
            //远程linux comment地址
//            BufferedReader br = new BufferedReader(new FileReader("/home/comment.txt"));
            //本地 comment地址
            BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\NK\\Desktop\\comment.txt"));
            while (((s = br.readLine()) != null)) {
                comment.add(s);
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return comment;
    }

    private List getFakeUser(){
        List<User> fakeUsers = new ArrayList<>();
        //获取表里面的假用户
        for (int i = 369; i < 469; i++) {
            User user = dslContext.selectFrom(USER).where(USER.ID.eq(i)).fetchOneInto(User.class);
            fakeUsers.add(user);
        }
        return fakeUsers;
    }

    private void insertAlbumFakePraise(List<Integer> newPutAwayAlbum){
        Integer userId = 469;//GM用户,用来刷点赞的用户ID

        for (Integer albumId:newPutAwayAlbum){
            int randomPraiseNum = (int) (30+ Math.random() * ( 40 - 1 + 1));//随机的点赞数
            for (int i = 0; i < randomPraiseNum; i++) {
                dslContext.insertInto(USER_ALBUM_PRAISE)
                        .columns(USER_ALBUM_PRAISE.USER_ID,USER_ALBUM_PRAISE.ALBUM_ID)
                        .values(userId,albumId).execute();
            }
        }
    }

    private List getNewPutAwayAlbum(){
       List<Integer> albumIds =  dslContext.select(ALBUM.ID)
                .from(ALBUM).where(ALBUM.PUTAWAY_TIME.greaterOrEqual(DSL.timestamp(DSL.currentDate()).add(-2))).fetchInto(Integer.class);
       return albumIds;
        //获取所有相册ID，没有评论的相册
    }

    private void insertIntoComment(List<Integer> newPutAwayAlbumIds,
                                   List<User> fakeUsers,
                                   List<String> fakeComments) {
        for (Integer newPutAwayAlbumId : newPutAwayAlbumIds) {
            //这里是随机没套图插入评论的数量
            int random = (int) (15 + Math.random() * (20));
            //随机索引LIST
            List<Integer> randomset = new ArrayList<Integer>();
            //这里是随机取得评论的索引
            for (int i = 0; i < random; i++) {
                int random1 = (int) (7 + Math.random() * (85));
                //下面的for循环是为了让取到的名字和评论不重复
                randomset.add(random1);
            }
            //这里是给上面取到的随机索引去重 randomset去重
            Set hashSet = new HashSet();
            List<Integer> newListRandomSet = new ArrayList<Integer>();
            for (Integer cd : randomset) {
                if (hashSet.add(cd)) {
                    newListRandomSet.add(cd);
                }
            }
            //
            for (int k = 0; k < newListRandomSet.size() & k < random; k++) {
                int randomcommment = (int) (Math.random() * (97));
                int randomUser = (int) (4 + Math.random() * (95));
                int rate = (int) (3 + Math.random() * (2.9));
                dslContext.insertInto(USER_ALBUM_COMMENT)
                        .columns(USER_ALBUM_COMMENT.USER_ID, USER_ALBUM_COMMENT.ALBUM_ID,
                                USER_ALBUM_COMMENT.CONTENT, USER_ALBUM_COMMENT.RATE)
                        .values(fakeUsers.get(randomUser).getId(), newPutAwayAlbumId, fakeComments.get(randomcommment), (double) rate).execute();
            }
        }
    }

    private void insertIntoCommentPraise(){
        List<Comment> comments =dslContext.selectFrom(USER_ALBUM_COMMENT)
                .where(USER_ALBUM_COMMENT.ID
                        .notIn(dslContext.select(USER_ALBUM_COMMENT_PRAISE.COMMENT_ID).from(USER_ALBUM_COMMENT_PRAISE))).fetchInto(Comment.class);
        for(Comment comment:comments) {
            int random2 = (int) (20+ Math.random() * ( 60 - 1 + 1));
            for (int i = 0; i < random2; i++) {
                String insertSql = "INSERT INTO user_album_comment_praise(user_id,comment_id) VALUES (?,?)";
                List<Object> params = new ArrayList<Object>();
                params.add(469);
                params.add(comment.getId());
                jdbcTemplate.update(insertSql, params.toArray());
            }
        }
    }


    @RequestMapping("/insert/album/praise")
    public ApiResponse insertAlbumPraise(){
        ApiResponse apiResponse = new ApiResponse();
        List<Integer> newPutAwayAlbumIds = getNewPutAwayAlbum();
        insertAlbumFakePraise(newPutAwayAlbumIds);
        apiResponse.setSuccess();
        return apiResponse;
    }

    @RequestMapping("/insercomment")
    public ApiResponse insertComment(){
        ApiResponse apiResponse = new ApiResponse();
        //评论LIST
        List<String> fakeComments = getFakeComment();
        List<User> fakeUsers = getFakeUser();
        List<Integer> newPutAwayAlbumIds = getNewPutAwayAlbum();

        insertIntoComment(newPutAwayAlbumIds,fakeUsers,fakeComments);
        return apiResponse;
    }

    @RequestMapping("/insert/commentPraise")
    public ApiResponse insertCommentPraise(){
        ApiResponse apiResponse = new ApiResponse();
        List<Integer> commentIds = dslContext.select(USER_ALBUM_COMMENT.ID).from(USER_ALBUM_COMMENT)
                .where(USER_ALBUM_COMMENT.CREATE_TIME.greaterOrEqual(DSL.timestamp(DSL.currentDate()).add(-2))).fetchInto(Integer.class);
        Integer userId = 469;
        for (Integer commentId:commentIds){
            dslContext.insertInto(USER_ALBUM_COMMENT_PRAISE)
                    .columns(USER_ALBUM_COMMENT_PRAISE.USER_ID,USER_ALBUM_COMMENT_PRAISE.COMMENT_ID)
                    .values(userId,commentId).execute();
        }
        apiResponse.setSuccess();
        return apiResponse;
    }
}