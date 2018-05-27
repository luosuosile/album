package com.yz.album.api.v1;


import com.yz.album.api.ApiVersion;
import com.yz.album.api.BaseApi;
import com.yz.album.api.dto.ApiResponse;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController("CopyrightApi_v1")
@RequestMapping("/{version}/copyright/")
@ApiVersion(1)
public class CopyrightApi extends BaseApi {

    /**
     * 版权投诉接口
     * @return
     */
    @RequestMapping("/complaint")
    public ApiResponse postComplaint(@RequestHeader Integer userId,
                                     @RequestParam String content){
        ApiResponse apiResponse = new ApiResponse();

        dslContext.insertInto(USER_COMPLAINT)
                .columns(USER_COMPLAINT.CONTENT,USER_COMPLAINT.USER_ID)
                .values(content,userId).execute();
        apiResponse.setSuccessData(0);
        return apiResponse;
    }
}