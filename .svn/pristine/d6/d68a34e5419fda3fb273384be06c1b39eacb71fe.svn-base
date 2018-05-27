package com.yz.album.api.managementSystem;

import com.yz.album.api.ApiVersion;
import com.yz.album.api.dto.ApiResponse;
import com.yz.album.api.dto.ManagePlatformApiResponse;
import com.yz.album.api.dto.User;
import com.yz.album.api.dto.Vip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/{version}/test")
@ApiVersion(1)
public class SearchUserApi {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping("/user/{deviceNumber}")
    public ManagePlatformApiResponse getUser(@PathVariable("deviceNumber") String deviceNumber){
        ManagePlatformApiResponse apiResponse = new ManagePlatformApiResponse();

        String sql3 ="SELECT DISTINCT IF(EXISTS(SELECT * FROM user WHERE  device_num= ? ),1,0)";
        List<Object> params4 = new ArrayList<Object>();
        params4.add(deviceNumber);
        Integer countUser = jdbcTemplate.queryForObject(sql3,params4.toArray(),Integer.class);
        if(countUser == 0 ){
            apiResponse.setFailureMsg("1","你输入的机器码不存在USER表中");
            return  apiResponse;
        }

        String sql = "SELECT * FROM user where device_num = ?";
        List<Object> params = new ArrayList<Object>();
        params.add(deviceNumber);
        User user = jdbcTemplate.queryForObject(sql ,params.toArray(), new BeanPropertyRowMapper<User>(User.class));

        apiResponse.setSuccessData(user);
        return  apiResponse;
    }


    @RequestMapping("user/vip/{deviceNumber}")
    public ManagePlatformApiResponse getVip(@PathVariable("deviceNumber") String deviceNumber){
        ManagePlatformApiResponse apiResponse = new ManagePlatformApiResponse();

        String sql3 ="SELECT DISTINCT IF(EXISTS(SELECT * FROM user WHERE  device_num= ? ),1,0)";
        List<Object> params4 = new ArrayList<Object>();
        params4.add(deviceNumber);
        Integer countUser = jdbcTemplate.queryForObject(sql3,params4.toArray(),Integer.class);
        if(countUser == 0 ){
            apiResponse.setFailureMsg("1","你输入的机器码不存在USER表中");
            return  apiResponse;
        }

        String sql = "SELECT * FROM user where device_num = ?";
        List<Object> params = new ArrayList<Object>();
        params.add(deviceNumber);
        User user = jdbcTemplate.queryForObject(sql ,params.toArray(), new BeanPropertyRowMapper<User>(User.class));

        String sql4 ="SELECT DISTINCT IF(EXISTS(SELECT * FROM user_vip WHERE  user_id = ?),1,0)";
        List<Object> params5 = new ArrayList<Object>();
        params5.add(user.getId());
        Integer countVip = jdbcTemplate.queryForObject(sql4,params5.toArray(),Integer.class);
        if(countVip == 0 ){
            apiResponse.setFailureMsg("1","对应这个机器码的用户不存在USER_VIP表中");
            return  apiResponse;
        }

        String sql2 = "SELECT * FROM user_vip where user_id = ?";
        List<Object> params2 = new ArrayList<Object>();
        params2.add(user.getId());
        Vip vip = jdbcTemplate.queryForObject(sql2,params2.toArray(),new BeanPropertyRowMapper<Vip>(Vip.class));

        apiResponse.setSuccessData(vip);

        return apiResponse;
    }

}
