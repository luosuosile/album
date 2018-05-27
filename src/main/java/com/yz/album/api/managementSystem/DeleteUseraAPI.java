package com.yz.album.api.managementSystem;



import com.yz.album.api.ApiVersion;
import com.yz.album.api.dto.ApiResponse;
import com.yz.album.api.dto.ManagePlatformApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/{version}/deleteUser")
@ApiVersion(1)
public class DeleteUseraAPI {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping("/deviceNumber/{deviceNumber}")
    public ManagePlatformApiResponse deleteDev(@PathVariable("deviceNumber") String deviceNumber){
        ManagePlatformApiResponse apiResponse = new ManagePlatformApiResponse();

        String sql = "Delete FROM user WHERE device_num  = ?";
        List<Object> params = new ArrayList<Object>();
        params.add(deviceNumber);
        jdbcTemplate.update(sql,params.toArray());

        apiResponse.setSuccess();

        String sql3 ="SELECT DISTINCT IF(EXISTS(SELECT * FROM user WHERE  device_num= ? ),1,0)";
        List<Object> params4 = new ArrayList<Object>();
        params4.add(deviceNumber);
        Integer countUser = jdbcTemplate.queryForObject(sql3,params4.toArray(),Integer.class);
        if(countUser == 0 ){
            apiResponse.setSuccessData("删除用户信息成功，此操作会删除所有与USER相关的信息（VIP表对应的用户信息也删除了）");
            return  apiResponse;
        }else{
            apiResponse.setSuccessData("错误，请用搜索功能确认");
        }
        return  apiResponse;

    }

}
