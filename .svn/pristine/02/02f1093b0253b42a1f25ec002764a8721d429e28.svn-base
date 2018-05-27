package com.yz.album.api.v1;


import com.yz.album.api.ApiVersion;
import com.yz.album.api.BaseApi;
import com.yz.album.api.dto.ApiResponse;
import com.yz.album.api.dto.Essence;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("EssenceApi_v1")
@RequestMapping("/{version}/essence")
@ApiVersion(1)
public class EssenceApi extends BaseApi {

    @RequestMapping("/getEssence")
    public ApiResponse getEssence (){
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setSuccessData(dslContext.selectFrom(ESSENCEURL).limit(1).fetchOneInto(Essence.class));
        return apiResponse;
    }
}
