package com.yz.album.api.v1;


import com.yz.album.api.ApiVersion;
import com.yz.album.api.BaseApi;
import com.yz.album.api.dto.ApiResponse;
import com.yz.album.api.dto.Comment;
import com.yz.album.api.dto.SubComment;
import com.yz.album.api.dto.User;
import org.jooq.Record;
import org.jooq.SelectSelectStep;
import org.jooq.Table;
import org.jooq.impl.DSL;
import org.omg.CORBA.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController("CommentApi_v1")
@RequestMapping("/{version}/comment")
@ApiVersion(1)
public class CommentApi extends BaseApi {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 暂时只用到父评论列表，评论点赞和评论功能
     */

    /**
     * 返回指定套图下面所有的评论
     * 父评论列表（包含子评论）
     * @param albumId
     * @return
     * hot是指热门评论，暂时未使用，limit是步长，offset是起点，是mysql语法limit的两个参数
     *
     */

    @RequestMapping("/{albumId}/list")
    public ApiResponse list(@PathVariable Integer albumId, @RequestParam(value = "hot", defaultValue = "false") boolean hot,
                            @RequestParam(defaultValue = "0") Integer offset, @RequestParam(defaultValue = "10") Integer limit,
                            @RequestParam(defaultValue = "0") Integer subOffset, @RequestParam(defaultValue = "3") Integer subLimit,
                            @RequestHeader Integer userId) {
        ApiResponse apiResponse = new ApiResponse();
        String sql = "";
        SelectSelectStep<Record> selectComment = dslContext.select(USER_ALBUM_COMMENT.fields());

        selectComment.select(USER.NICKNAME, USER.HEAD_PICTURE)//要返回用户头像昵称
                .select(DSL.selectCount().from(USER_ALBUM_COMMENT_PRAISE)
                        .where(USER_ALBUM_COMMENT.ID.eq(USER_ALBUM_COMMENT_PRAISE.COMMENT_ID)).asField("praiseAmount"))//返回点赞总数
                .select(DSL.selectCount().from(USER_ALBUM_COMMENT_PRAISE)
                        .where(USER_ALBUM_COMMENT_PRAISE.USER_ID.eq(userId),
                                USER_ALBUM_COMMENT.ID.eq(USER_ALBUM_COMMENT_PRAISE.COMMENT_ID))
                        .asField("isPraise"))//该用户是否点赞
                .from(USER_ALBUM_COMMENT)
                .leftJoin(USER)
                .on(USER_ALBUM_COMMENT.USER_ID.eq(USER.ID))
                .where(USER_ALBUM_COMMENT.ALBUM_ID.eq(albumId))
                .orderBy(USER_ALBUM_COMMENT.CREATE_TIME.desc())
                .limit(offset,limit);

        if (hot) {
            selectComment.where(USER_ALBUM_COMMENT.ID.in(
                            dslContext.select(USER_ALBUM_COMMENT_PRAISE.COMMENT_ID).from(USER_ALBUM_COMMENT_PRAISE).groupBy(USER_ALBUM_COMMENT_PRAISE.COMMENT_ID)
                                    .orderBy(USER_ALBUM_COMMENT_PRAISE.COMMENT_ID.count().desc())
                    )
            );
        }

        //将搜索的结果放入comment模型
        List<Comment> comments =selectComment.fetchInto(Comment.class);
        //for循环父评论，将子评论放入父评论
        for (Comment comment : comments) {
            Map<String, Object> map = new HashMap<String, Object>();
            List<SubComment> subComments = dslContext.select(USER_ALBUM_COMMENT_SUB.fields()).select(USER.NICKNAME)
                    .select(DSL.select(USER.NICKNAME).from(USER).where(USER.ID.eq(USER_ALBUM_COMMENT_SUB.USER_ID)).asField("atNickname"))
                    .from(USER_ALBUM_COMMENT_SUB)
                    .leftJoin(USER).on(USER_ALBUM_COMMENT_SUB.USER_ID.eq(USER.ID))
                    .where(USER_ALBUM_COMMENT_SUB.PARENT_COMMENT_ID.eq(comment.getId()))
                    .orderBy(USER_ALBUM_COMMENT_SUB.CREATE_TIME.desc())
                    .limit(subOffset,subLimit).fetchInto(SubComment.class);

            Integer total = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM user_album_comment_sub WHERE parent_comment_id=?", new Object[]{comment.getId()}, Integer.class);
            map.put("list", subComments);
            map.put("total", total);//子评论数量
            comment.setSubComments(map);
        }

        apiResponse.setSuccessData(comments);

        return apiResponse;
    }


    /**
     * 评论点赞开关（只对父评论起作用）
     * @param userId
     * @param commentId
     * @return
     */
    @RequestMapping("/praise/switch")
    public ApiResponse praise(@RequestHeader Integer userId, @RequestParam Integer commentId) {
        ApiResponse apiResponse = new ApiResponse();

        Table<Record> userAlbumCommentPraiseTable = DSL.table("user_album_comment_praise");

        Map<String, Object> map = new HashMap<>();
        try {
            boolean exists = dslContext.fetchExists
                    (dslContext.selectFrom(userAlbumCommentPraiseTable).where(DSL.field("user_id").eq(userId), DSL.field("comment_id").eq(commentId)));
            if (exists) {
                dslContext.delete(userAlbumCommentPraiseTable).where(DSL.field("user_id").eq(userId), DSL.field("comment_id").eq(commentId)).execute();
                map.put("status", "0");
                map.put("explain", "取消点赞成功");
            } else {
                dslContext.insertInto(userAlbumCommentPraiseTable).columns(DSL.field("user_id"), DSL.field("comment_id")).values(userId, commentId).execute();
                map.put("status", "1");
                map.put("explain", "点赞成功");
            }
        }catch (Exception e){
            map.put("status","-1");
            map.put("explain","系统异常");
        }
        apiResponse.setSuccessData(map);
        return apiResponse;
    }

    /**
     * 对评论进行再评论的情况
     *
     * @param userId
     * @param commentId
     * @param content
     * @param subCommentId
     * @return
     */
    @RequestMapping("/sub/{commentId}")
    public ApiResponse subComment(@RequestHeader Integer userId, @PathVariable Integer commentId,
                                  @RequestParam String content, Integer subCommentId) {
        ApiResponse apiResponse = new ApiResponse();
        dslContext.insertInto(USER_ALBUM_COMMENT_SUB).columns
                (USER_ALBUM_COMMENT_SUB.USER_ID, USER_ALBUM_COMMENT_SUB.PARENT_COMMENT_ID
                ,USER_ALBUM_COMMENT_SUB.AT_COMMENT_ID,USER_ALBUM_COMMENT_SUB.CONTENT)
                .values(userId,commentId,subCommentId,content).execute();

        apiResponse.setSuccess();
        return apiResponse;
    }

    /**
     * 评论动作的接口，并且把他返回给客户端
     *
     * @return
     */
    @RequestMapping("/user/comment/{albumId}")
    public ApiResponse comment(@RequestHeader() Integer userId, @PathVariable("albumId") Integer albumId,
                               @RequestParam() String content, @RequestParam(defaultValue = "5") Double rate,
                               @RequestParam String language) {
        ApiResponse apiResponse = new ApiResponse();
            Comment comment = dslContext.insertInto(USER_ALBUM_COMMENT).columns(USER_ALBUM_COMMENT.USER_ID,
                    USER_ALBUM_COMMENT.ALBUM_ID,USER_ALBUM_COMMENT.CONTENT,USER_ALBUM_COMMENT.RATE)
                    .values(userId,albumId,content,rate)
                    .returning().fetchOne().into(Comment.class);

            User user = dslContext.selectFrom(USER).where(USER.ID.eq(userId)).fetchOneInto(User.class);
            comment.setNickname(user.getNickname());
            comment.setHeadPicture(user.getHeadPicture());
        apiResponse.setSuccessData(comment);
        return apiResponse;
    }


    @RequestMapping("/{commentId}/subComment/list")
    public ApiResponse list(@PathVariable Integer commentId,
                            @RequestParam(defaultValue = "0") Integer offset, @RequestParam(defaultValue = "10") Integer limit){
        ApiResponse apiResponse = new ApiResponse();
        Map<String , Object> map = new HashMap<String , Object>();
        List<SubComment> subComments = dslContext.selectFrom(USER_ALBUM_COMMENT_SUB).where(USER_ALBUM_COMMENT_SUB.PARENT_COMMENT_ID.eq(commentId))
                .limit(offset, limit)
                .fetchInto(SubComment.class);

        Integer total = dslContext.selectCount().from(USER_ALBUM_COMMENT_SUB).where(USER_ALBUM_COMMENT_SUB.PARENT_COMMENT_ID.eq(commentId)).fetchOneInto(Integer.class);

        map.put("list", subComments);
        map.put("total", total);
        apiResponse.setSuccess();
        apiResponse.setSuccessData(map);
        return apiResponse;
    }
}

