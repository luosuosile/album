package com.yz.album.api.v1;

import com.yz.album.api.ApiVersion;
import com.yz.album.api.BaseApi;
import com.yz.album.api.Constant;
import com.yz.album.api.dto.Album;
import com.yz.album.api.dto.ApiResponse;
import org.jooq.Record;
import org.jooq.SelectJoinStep;
import org.jooq.impl.DSL;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController("SerachApi_v1")
@RequestMapping("/{version}/search")
@ApiVersion(1)
public class SerachApi extends BaseApi {

    //根据关键词，筛选合适的套图名称
    @RequestMapping("/inAlbumName")
    public ApiResponse serach(@RequestParam String keyword,
                              @RequestHeader Integer userId,
                              @RequestHeader(required = false) String channelNum,
                              @RequestParam(defaultValue = "0") Integer offset,
                              @RequestParam(defaultValue = "10") Integer limit,
                              HttpServletRequest request){
        ApiResponse apiResponse = new ApiResponse();

        Map<String, Object> map = new HashMap<String, Object>();
        //下面这部分是有关于搜索关键字

        SelectJoinStep<Record> selectAlbum = dslContext.select(ALBUM.fields())
                .select(dslContext.selectCount().from(USER_ALBUM_COMMENT).where(USER_ALBUM_COMMENT.ALBUM_ID.eq(ALBUM.ID)).asField("commentAmount"))
                .select(dslContext.selectCount().from(USER_ALBUM_FAVORITE).where(USER_ALBUM_FAVORITE.ALBUM_ID.eq(ALBUM.ID)).asField("favoriteAmount"))
                .select(dslContext.selectCount().from(USER_ALBUM_PRAISE).where(USER_ALBUM_PRAISE.ALBUM_ID.eq(ALBUM.ID)).asField("praiseAmount"))
                .select(dslContext.selectCount().from(USER_ALBUM_READ).where(USER_ALBUM_READ.ALBUM_ID.eq(ALBUM.ID)).asField("readAmount"))
                .select(DSL.selectCount().from(USER_ALBUM_FAVORITE).where(USER_ALBUM_FAVORITE.USER_ID.eq(userId),USER_ALBUM_FAVORITE.ALBUM_ID.eq(ALBUM.ID)).asField("isFavorite"))
                .select(DSL.selectCount().from(USER_ALBUM_PRAISE).where(USER_ALBUM_PRAISE.USER_ID.eq(userId),USER_ALBUM_PRAISE.ALBUM_ID.eq(ALBUM.ID)).asField("isPraise"))
                .from(ALBUM);

        String serverName =request.getServerName();

        //根据域名做过滤
        if (Constant.SERVER_NAME_LINE.equalsIgnoreCase(serverName)){
            selectAlbum.where(ALBUM.ACCESS_TYPE.eq("1"));
        } else if (Constant.SERVER_NAME.equalsIgnoreCase(serverName)){
            selectAlbum.where(ALBUM.ACCESS_TYPE.eq("2"));
        }
        else if(Constant.SERVER_NAME_PURE.equalsIgnoreCase(serverName)){
            selectAlbum.where(ALBUM.ACCESS_TYPE.eq("3"));
        }
        else if (Constant.SERVER_NAME_OOMPH.equalsIgnoreCase(serverName)){
            selectAlbum.where(ALBUM.ACCESS_TYPE.eq("4"));
        }
        else{
            selectAlbum.where(ALBUM.CHANNEL_NUM.eq("SSEEQQSSRRSSAAFFTTVVTT"));
        }
        //筛选条件，已上架（1），渠道号是空或者空字符串（null或者""），
        selectAlbum.where(ALBUM.IS_PUTAWAY.eq("1"));
        selectAlbum.where(ALBUM.CHANNEL_NUM.eq(channelNum).or(ALBUM.CHANNEL_NUM.isNull()).or(ALBUM.CHANNEL_NUM.eq("")));

        //筛选条件，ALBUM.NAME，和keyword很相似
        List<Album> albums = selectAlbum.where(ALBUM.NAME.like("%"+keyword+"%")).orderBy(ALBUM.PUTAWAY_TIME.desc()).limit(offset,limit).fetchInto(Album.class);

        apiResponse.setSuccessData(albums);
        return apiResponse;
    }
}