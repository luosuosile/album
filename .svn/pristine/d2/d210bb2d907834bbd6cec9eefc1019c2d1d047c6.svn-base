package com.yz.album.api.v1;

import com.yz.album.api.ApiVersion;
import com.yz.album.api.BaseApi;
import com.yz.album.api.dto.ApiResponse;
import com.yz.album.api.dto.Vip;
import com.yz.album.util.TimeUtil;
import org.jooq.DatePart;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.*;

@RestController("VipApi_v1")
@RequestMapping("/{version}/vip/")
@ApiVersion(1)
public class VipApi extends BaseApi {

    @Autowired
    public JdbcTemplate jdbcTemplate;

    /**
     * @param userId
     * @return
     * @throws ParseException
     */

    //判断指定用户是否是VIP
    @RequestMapping("isVip")
    public ApiResponse isVip(@RequestHeader Integer userId) throws ParseException {
        ApiResponse apiResponse = new ApiResponse();
        boolean exists = dslContext.fetchExists(dslContext.selectFrom(USER_VIP).where(USER_VIP.USER_ID.eq(userId)));
        //存在VIP表中的VIP有两种状态，一种是还没有失效，另一种是已经到期了
        //分支有2，A有效，B到期
        if (exists) {
            TimeUtil timeUtil = new TimeUtil();
            List<Object> params = new ArrayList<Object>();
            params.add(userId);
            Vip vip = dslContext.selectFrom(USER_VIP)
                    .where(USER_VIP.USER_ID.eq(userId)).orderBy(USER_VIP.CREATE_TIME)
                    .fetchOneInto(Vip.class); //获取VIP信息
            long remainingTime = vip.getExpiredTime().getTime() - System.currentTimeMillis();//计算剩余时间
            // B 如果剩余时间小于0那么失效
            if (remainingTime < 0) {
                vip.setRemainingTime(0);
                apiResponse.setSuccessData("200");
            }
            // A 剩余时间大于0，将剩余时间转成天数，放入VIP模型，返回给客户端
            else {
                int remainingTimeOut = (int) timeUtil.millChangeFormat(remainingTime, "dd");
                vip.setRemainingTime(remainingTimeOut + 1);
                apiResponse.setSuccessData(vip);
            }
        }
        //不存在表中，不是VIP
        else {
            String data = "200";
            apiResponse.setSuccessData(data);
        }
        return apiResponse;
    }

    /**
     *
     * APP客户端会生成一个订单ID，然后这个接口第一件事是记录一条订单
     *
     *
     * @param userId
     * @param payAmount
     * @param mealInfo
     * @return
     * @throws ParseException
     */

    //支付接口，购买VIP
    @RequestMapping("/paid/{payAmount}/{mealInfo}/{paymentId}")
    public ApiResponse isPaid(
            @RequestHeader Integer userId, @PathVariable("payAmount") Double payAmount,
            @RequestHeader() String channelNum, @PathVariable("mealInfo") String mealInfo,
            @PathVariable("paymentId") String paymentId,
            @RequestParam(required = false)String chargeChannel) throws ParseException {
        ApiResponse apiResponse = new ApiResponse();
        Integer isSuperVip = 0;
        if (mealInfo.equals("forever")) {
            isSuperVip = 1;
        }
        TimeUtil timeUtil = new TimeUtil();
        //在USER_PAY表记录支付信息
         dslContext.insertInto(USER_PAY)
                .columns(USER_PAY.USER_ID, USER_PAY.PAY_AMOUNT, USER_PAY.MEAL_INFO, USER_PAY.CHANNEL_NUM,USER_PAY.PAYMENT_ID,USER_PAY.CHARGE_CHANNEL)
                .values(userId, payAmount, mealInfo, channelNum,paymentId,chargeChannel).execute();

        //判断用户是不是VIP会员,a是，b不是
        boolean exists = dslContext.fetchExists(dslContext.selectFrom(USER_VIP).where(USER_VIP.USER_ID.eq(userId)));
        //情况A是VIP会员
        if (exists) {
            //获取对应VIP信息
            Vip vip = dslContext.selectFrom(USER_VIP).where(USER_VIP.USER_ID.eq(userId)).fetchOneInto(Vip.class);
            long lasttime = vip.getExpiredTime().getTime();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            //买的是永久VIP，直接更新过期时间到最大（暂时未有此套餐）
            if(isSuperVip==1){
                dslContext.update(USER_VIP).set(USER_VIP.EXPIRED_TIME,Timestamp.valueOf(timeUtil.millToDate((long)9999999999999.0))).execute();
                apiResponse.setSuccess();
            }
            //A1 情况分支一，会员还没失效,在原基础过期时间加上买入的套餐
            else if (calendar.getTimeInMillis() < lasttime) {

                    dslContext.update(USER_VIP)
                            .set(USER_VIP.EXPIRED_TIME, DSL.timestampAdd(USER_VIP.EXPIRED_TIME, timeUtil.getRemainTime(mealInfo) / 1000, DatePart.SECOND)).where(USER_VIP.USER_ID.eq(userId)).execute();
                    apiResponse.setSuccess();
            }
            // A2情况分支2，会员已经失效,不在原基础更新时间，而是根据购买时间重新设定一个到期时间
            else {
                    dslContext.update(USER_VIP)
                            .set(USER_VIP.EXPIRED_TIME, Timestamp.valueOf(timeUtil.millToDate((long) timeUtil.getLastTime(mealInfo))))
                            .where(USER_VIP.USER_ID.eq(userId)).execute();
                    apiResponse.setSuccess();
                }
            }
        //情况B，用户没有购买过
        else {
            double lastday = 0;
            //购买的是普通会员
            if (isSuperVip != 1) {
                lastday = timeUtil.getLastTime(mealInfo);   //lastday是指有效期最后一日的日期
            }
            //购买的是超级会员
            else {
                lastday = 9999999999999.0;
            }
            /**
             * 用户ID,用户是否是永久会员，用户的过期时间
             */
            dslContext.insertInto(USER_VIP).columns(USER_VIP.USER_ID, USER_VIP.EXPIRED_TIME)
                    .values(userId,Timestamp.valueOf(timeUtil.millToDate((long)lastday)))
                    .execute();
            apiResponse.setSuccessData(0);
        }
        return apiResponse;
    }

    /**
     * 订单记录状态的变更，APP发来，来验证这个订单是否付费成功。一般APP需要数分钟来验证订单是否付费成功，所以需要这个接口
     * @param payStatus
     * @param userId
     * @param paymentId
     * @return
     * @throws ParseException
     */
    //更新状态
    @RequestMapping("/update/payStatus")
    public ApiResponse updatePayMent(@RequestParam String payStatus,
                                     @RequestHeader Integer userId,
                                     @RequestParam String paymentId) throws ParseException {
        ApiResponse apiResponse = new ApiResponse();

        boolean exists = dslContext.fetchExists(dslContext.selectFrom(USER_PAY).where(USER_PAY.PAYMENT_ID.eq(paymentId)));
        if (exists) {
            dslContext.update(USER_PAY).set(USER_PAY.PAYMENT_STATUS,payStatus).where(USER_PAY.PAYMENT_ID.eq(paymentId)).execute();
            Map<String, Object> map = new HashMap<>();
            map.put("status", "0");
            map.put("explain", "更新状态成功");
            apiResponse.setSuccessData(map);
        } else {
            apiResponse.setData(1);
            apiResponse.setMsg("订单ID不存在");
        }
        return apiResponse;
    }
}
