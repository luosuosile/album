package com.yz.album.api.managementSystem;


import com.yz.album.api.ApiVersion;
import com.yz.album.api.BaseApi;
import com.yz.album.api.dto.ApiResponse;
import com.yz.album.api.dto.Complaint;
import com.yz.album.api.dto.ManagePlatformApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/{version}/showSomeThing/")
@ApiVersion(1)
public class ShowUserComplaintApi extends BaseApi {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @RequestMapping("complaint")
    public ManagePlatformApiResponse showComplaint(@RequestParam(defaultValue = "0")Integer offset,
                                                   @RequestParam(defaultValue = "10") Integer limit ){

        ManagePlatformApiResponse apiResponse = new ManagePlatformApiResponse();
        String  sql = "select id,user_id,content,CONVERT_TZ(create_time,\"+00:00\",\"+08:00\")as create_time from user_complaint ORDER BY create_time desc limit ?,?";
        List<Object> param = new ArrayList<>();
        param.add(offset);
        param.add(limit);
        List<Complaint> complaints = jdbcTemplate.query(sql,param.toArray(),new BeanPropertyRowMapper<Complaint>(Complaint.class));
//        List<Complaint> complaints = dslContext
//                .selectFrom(USER_COMPLAINT)
//                .orderBy(USER_COMPLAINT.CREATE_TIME.desc())
//                .limit(offset,limit).fetchInto(Complaint.class);
        Integer sum = dslContext.selectCount().from(USER_COMPLAINT).fetchOneInto(Integer.class);
        for(Complaint complaint : complaints) {
            String deviceNum = dslContext.select(USER.DEVICE_NUM)
                    .from(USER).where(USER.ID.eq(Integer.valueOf(complaint.getUserId()))).fetchOneInto(String.class);
            complaint.setSum(sum);
            complaint.setDeviceNum(deviceNum);
        }
        apiResponse.setSuccessData(complaints);
        return apiResponse;
    }
}
