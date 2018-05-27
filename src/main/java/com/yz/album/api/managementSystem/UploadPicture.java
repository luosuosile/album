package com.yz.album.api.managementSystem;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yz.album.api.ApiResponse;
import com.yz.album.api.ApiVersion;
import com.yz.album.api.BaseApi;
import com.yz.album.api.dto.Album;
import com.yz.album.api.dto.Banner;
import com.yz.album.api.dto.ManagePlatformApiResponse;
import com.yz.album.orm.tables.records.AlbumRecord;
import org.apache.commons.lang3.StringUtils;
import org.jooq.UpdateQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/{version}/management")
@ApiVersion(1)
public class UploadPicture extends BaseApi {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     *
     * @param obj
     * @return
     */

    @RequestMapping("/upload")
    public ManagePlatformApiResponse uploadPicture(@RequestBody String obj ,
                                     @RequestParam(required = false) String addPicture) {
        ManagePlatformApiResponse apiResponse = new ManagePlatformApiResponse();

        if(addPicture==null) {

            JSONObject jsonObject = JSON.parseObject(obj);
            String name = jsonObject.get("name").toString();
            String thumbnailUrl = jsonObject.get("thumbnailUrl").toString();
            String brief = jsonObject.get("breif").toString();
            String accessType = jsonObject.get("accessType").toString();
            String isPutAway = jsonObject.get("isPutaway").toString();
            String categoryId = jsonObject.get("category").toString();
            String isBanner = jsonObject.get("isBanner").toString();
            String channelNum = jsonObject.get("channelNum").toString();

            JSONArray images = jsonObject.getJSONArray("images");

            //插入套图信息
            Integer albumId = dslContext.insertInto(ALBUM).columns(ALBUM.NAME, ALBUM.THUMBNAIL_URL, ALBUM.BREIF, ALBUM.ACCESS_TYPE, ALBUM.IS_PUTAWAY, ALBUM.IS_BANNER,ALBUM.CHANNEL_NUM)
                    .values(name, thumbnailUrl, brief,accessType, isPutAway, Integer.valueOf(isBanner),channelNum).returning(USER.ID).fetchOne().getId();
            //插入图片到图片表
            List<Integer> pictureIds = new ArrayList<Integer>();
            for (int i = 0; i < images.size(); i++) {
                Integer pictureId = dslContext.insertInto(PICTURE).columns(PICTURE.URL).values(images.get(i).toString()).returning(PICTURE.ID).fetchOne().getId();
                pictureIds.add(pictureId);
            }
            //插入图片关联
            for (int i = 0; i < pictureIds.size(); i++) {
                dslContext.insertInto(ALBUM_PICTURE).columns(ALBUM_PICTURE.ALBUM_ID, ALBUM_PICTURE.PICTURE_ID).values(albumId, pictureIds.get(i)).execute();
            }
            //插入类型关联
            dslContext.insertInto(ALBUM_CATEGORY).columns(ALBUM_CATEGORY.ALBUM_ID, ALBUM_CATEGORY.CATEGORY_ID).values(albumId, Integer.valueOf(categoryId)).execute();
        }
        else if(addPicture!=null){
            JSONObject jsonObject = JSON.parseObject(obj);
            String albumId = jsonObject.get("albumId").toString();
            JSONArray images = jsonObject.getJSONArray("images");
            //插入图片到图片表
            List<Integer> pictureIds = new ArrayList<Integer>();
            for (int i = 0; i < images.size(); i++) {
                Integer pictureId = dslContext.insertInto(PICTURE).columns(PICTURE.URL).values(images.get(i).toString()).returning(PICTURE.ID).fetchOne().getId();
                pictureIds.add(pictureId);
            }
            for (int i = 0; i < pictureIds.size(); i++) {
                dslContext.insertInto(ALBUM_PICTURE).columns(ALBUM_PICTURE.ALBUM_ID, ALBUM_PICTURE.PICTURE_ID).values(Integer.valueOf(albumId), pictureIds.get(i)).execute();
            }
        }
        apiResponse.setSuccessData("1");
        return  apiResponse;
    }


    @RequestMapping("update/an/{albumId}/property")
    public ManagePlatformApiResponse updateAnAlbum(@RequestBody String obj,
                                     @PathVariable Integer albumId
                                     ){

        JSONObject jsonObject = JSON.parseObject(obj);
        String name = jsonObject.get("name").toString();
        String thumbnailUrl = jsonObject.get("thumbnailUrl").toString();
        String brief = jsonObject.get("breif").toString();
        String accessType = jsonObject.get("accessType").toString();
        String isPutAway = jsonObject.get("isPutaway").toString();
        String isBanner = jsonObject.get("isBanner").toString();
        String channelNum = jsonObject.get("channelNum").toString();
        ManagePlatformApiResponse apiResponse = new ManagePlatformApiResponse();
        boolean exists = dslContext.fetchExists(dslContext.selectFrom(ALBUM).where(ALBUM.ID.eq(albumId)));
        if (exists) {
            if (StringUtils.isNotBlank(channelNum) || StringUtils.isNotBlank(thumbnailUrl)||StringUtils.isNotBlank(name)||StringUtils.isNotBlank(brief)
                    ||StringUtils.isNotBlank(isPutAway)||StringUtils.isNotBlank(isBanner)||StringUtils.isNotBlank(accessType)){
                UpdateQuery<AlbumRecord> updateAlbum = dslContext.updateQuery(ALBUM);
                if (StringUtils.isNotBlank(channelNum)) {
                    updateAlbum.addValue(ALBUM.CHANNEL_NUM, channelNum);
                }
                if (StringUtils.isNotBlank(thumbnailUrl)) {
                    updateAlbum.addValue(ALBUM.THUMBNAIL_URL, thumbnailUrl);
                }
                if(StringUtils.isNotBlank(name)){
                    updateAlbum.addValue(ALBUM.NAME,name);
                }
                if(StringUtils.isNotBlank(brief)){
                    updateAlbum.addValue(ALBUM.BREIF,brief);
                }
                if (StringUtils.isNotBlank(isPutAway)) {
                    updateAlbum.addValue(ALBUM.IS_PUTAWAY, isPutAway);
                }
                if(StringUtils.isNotBlank(isBanner)){
                    updateAlbum.addValue(ALBUM.IS_BANNER, Integer.valueOf(isBanner));
                }
                if(StringUtils.isNotBlank(accessType)){
                    updateAlbum.addValue(ALBUM.ACCESS_TYPE, accessType);
                }

                updateAlbum.addConditions(ALBUM.ID.eq(albumId));
                updateAlbum.execute();
            }
           Album album = dslContext.selectFrom(ALBUM).where(ALBUM.ID.eq(albumId)).fetchOneInto(Album.class);
            apiResponse.setSuccessData(album);
        } else {
            apiResponse.setFailureMsg("X2", "你的albumId不对或者相册不存在");
        }
        return  apiResponse;
    }

    /**
     *
     * @param albumId
     * @param bannerCategory
     * @return
     */

    @RequestMapping("insert/banner")
    public ManagePlatformApiResponse insertBanner(@RequestParam(required = false) Integer albumId,
                                    @RequestParam(required = false) String thumnailUrl,
                                    @RequestParam String bannerCategory,
                                    @RequestParam(required = false) String contentUrl,
                                    @RequestParam(required = false) Integer sort,
                                    @RequestParam Integer accessType){


        ManagePlatformApiResponse apiResponse = new ManagePlatformApiResponse();
        //当插入的banner是套图
        if(bannerCategory.equals("1")) {
           thumnailUrl = dslContext.select(ALBUM.THUMBNAIL_URL)
                    .from(ALBUM)
                    .where(ALBUM.ID.eq(albumId))
                    .fetchOneInto(String.class);
            dslContext.insertInto(BANNER)
                    .columns(BANNER.BANNER_URL,BANNER.ALBUM_ID,BANNER.BANNER_CATEGORY,BANNER.ACCESS_TYPE,BANNER.SORT)
                    .values(thumnailUrl,albumId,bannerCategory,accessType,sort).execute();
        }

        //当插入的banner是广告
        else if(bannerCategory.equals("2")){
            dslContext.insertInto(BANNER)
                    .columns(BANNER.BANNER_URL,BANNER.CONTENT_URL,BANNER.BANNER_CATEGORY,BANNER.ACCESS_TYPE)
                    .values(thumnailUrl,contentUrl,bannerCategory,accessType)
                    .execute();
        }
        apiResponse.setSuccessData("1");
        return apiResponse;
    }

    @RequestMapping("delete/{bannerId}")
    public ManagePlatformApiResponse deleteBanner(@PathVariable("bannerId") Integer bannerId){

        ManagePlatformApiResponse apiResponse = new ManagePlatformApiResponse();
        dslContext.deleteFrom(BANNER).where(BANNER.ID.eq(bannerId)).execute();
        apiResponse.setSuccessData("1");
        return apiResponse;
    }

    @RequestMapping("get/all/banner")
    public ManagePlatformApiResponse getAllBanner(@RequestParam(defaultValue = "0") Integer offset,
                                    @RequestParam(defaultValue = "10") Integer limit
                                    ){
        ManagePlatformApiResponse apiResponse = new ManagePlatformApiResponse();
        List<Banner> banners = dslContext.selectFrom(BANNER).limit(offset,limit).fetchInto(Banner.class);
        Integer integer = dslContext.selectCount().from(BANNER).fetchOneInto(Integer.class);

        for(Banner banner:banners){
            banner.setSum(integer);
        }
        apiResponse.setSuccessData(banners);
        return apiResponse;
    }

    @RequestMapping("delete/an/album/atId/{albumId}")
    public ManagePlatformApiResponse deleteAnAlbum(@PathVariable("albumId") Integer albumId){

        ManagePlatformApiResponse apiResponse = new ManagePlatformApiResponse();
            boolean exists = dslContext.fetchExists(dslContext.selectFrom(ALBUM).where(ALBUM.ID.eq(albumId)));
            if(exists) {
                dslContext.delete(ALBUM).where(ALBUM.ID.eq(albumId)).execute();
            }
            else{
                apiResponse.setSuccessData("0");
            }
            apiResponse.setSuccessData("1");
            return  apiResponse;
    }

    public static void main(String[] args) {

    }
}
