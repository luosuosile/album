package com.yz.album.api.v1;


import com.yz.album.api.ApiVersion;
import com.yz.album.api.BaseApi;
import com.yz.album.api.dto.Album;
import com.yz.album.api.dto.ApiResponse;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController("FavoriteApi_v1")
@RequestMapping("/{version}/favorite")
@ApiVersion(1)
public class FavoriteApi extends BaseApi {

    /**
     * 谁收藏/取消收藏了那本
     * 收藏的开关，调用一次收藏，第二次取消收藏
     */

    @RequestMapping("/switch")
    public ApiResponse switchFavorite(@RequestHeader Integer userId, @RequestParam Integer albumId){
        ApiResponse apiResponse = new ApiResponse();

        //先判断是否已经收藏，已收藏用delete，未收藏用insert
        boolean isExist = dslContext.fetchExists(dslContext
                .selectFrom(USER_ALBUM_FAVORITE)
                .where(USER_ALBUM_FAVORITE.USER_ID.eq(userId),USER_ALBUM_FAVORITE.ALBUM_ID.eq(albumId)));

        Map<String, Object> map = new HashMap<>();
        if(isExist){
            dslContext.delete(USER_ALBUM_FAVORITE)
                    .where(USER_ALBUM_FAVORITE.USER_ID.eq(userId),USER_ALBUM_FAVORITE.ALBUM_ID.eq(albumId)).execute();
            map.put("status","0");
            map.put("explain","取消收藏成功");
        }
        else {
            dslContext.insertInto(USER_ALBUM_FAVORITE)
                    .columns(USER_ALBUM_FAVORITE.USER_ID,USER_ALBUM_FAVORITE.ALBUM_ID)
                    .values(userId,albumId).execute();
            map.put("status","1");
            map.put("explain","收藏成功");
        }
        apiResponse.setSuccessData(map);
        return apiResponse;
    }

    /**
     * 获取指定用户收藏的套图接口
     * @param userId
     * @return
     */

    @RequestMapping("/album/list")
    public ApiResponse getFavorite(@RequestHeader Integer userId) {
        ApiResponse apiResponse = new ApiResponse();

        List<Album> albums =dslContext
                .selectFrom(ALBUM.rightJoin(USER_ALBUM_FAVORITE).on(ALBUM.ID.eq(USER_ALBUM_FAVORITE.ALBUM_ID)))
                .where(USER_ALBUM_FAVORITE.USER_ID.eq(userId))
                .orderBy(USER_ALBUM_FAVORITE.CREATE_TIME.desc()).fetchInto(Album.class);

        apiResponse.setSuccessData(albums);
        return apiResponse;
    }

}
