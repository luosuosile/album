package com.yz.album.api.v1;

import com.yz.album.api.ApiVersion;
import com.yz.album.api.BaseApi;
import com.yz.album.api.dto.ApiResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController("downloadApi_v1")
@RequestMapping("/{version}/download/")
@ApiVersion(1)
public class DownloadApi extends BaseApi {


    @RequestMapping("rank")
    public ApiResponse downloadRank(@RequestParam Integer albumId){
        Map<String, Object> map = new HashMap<>();
        ApiResponse apiResponse = new ApiResponse();
        try {
            dslContext.update(ALBUM).set(ALBUM.DOWNLOAD_TIME, ALBUM.DOWNLOAD_TIME.add(1)).where(ALBUM.ID.eq(albumId)).execute();
            map.put("status", "1");
            map.put("explain", "更新成功");
        }catch (Exception e){
            map.put("status", "-1");
            map.put("explain", "系统异常");
        }
        apiResponse.setSuccessData(map);
        return apiResponse;
    }
}
