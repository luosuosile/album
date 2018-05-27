package com.yz.album.api.managementSystem;

import com.yz.album.api.ApiVersion;
import com.yz.album.api.dto.ApiResponse;
import com.yz.album.api.dto.ManagePlatformApiResponse;
import com.yz.album.api.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/{version}/deleteVip")
@ApiVersion(1)
public class DeleteVipApi {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping("/deviceNumber/{deviceNumber}")
    public ManagePlatformApiResponse deleteDev(@PathVariable("deviceNumber") String deviceNumber) {
        ManagePlatformApiResponse apiResponse = new ManagePlatformApiResponse();

        String usersql = "SELECT * FROM user where device_num = ?";
        List<Object> params = new ArrayList<Object>();
        params.add(deviceNumber);
        User user = jdbcTemplate.queryForObject(usersql, params.toArray(), new BeanPropertyRowMapper<User>(User.class));

        String sql = "Delete FROM user_vip WHERE user_id  = ?";
        List<Object> params2 = new ArrayList<Object>();
        params2.add(user.getId());
        jdbcTemplate.update(sql, params2.toArray());

        String sql2 = "SELECT DISTINCT IF(EXISTS(SELECT * FROM user_vip  WHERE  user_id = ? ),1,0)";

        List<Object> params3 = new ArrayList<Object>();
        params3.add(user.getId());

        Integer count = jdbcTemplate.queryForObject(sql2, params3.toArray(), Integer.class);

        if(count == 0) {
            apiResponse.setSuccessData("删除vip成功");
        }
        else{
            apiResponse.setSuccessData("用户可能还在VIP表中，可以用搜索功能确认");
        }

        return apiResponse;

    }
}