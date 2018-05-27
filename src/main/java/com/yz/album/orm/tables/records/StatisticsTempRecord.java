/*
 * This file is generated by jOOQ.
*/
package com.yz.album.orm.tables.records;


import com.yz.album.orm.tables.StatisticsTemp;

import java.sql.Timestamp;

import javax.annotation.Generated;

import org.jooq.Record1;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.9.5"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class StatisticsTempRecord extends UpdatableRecordImpl<StatisticsTempRecord> {

    private static final long serialVersionUID = -1012131169;

    /**
     * Setter for <code>album.statistics_temp.id</code>.
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>album.statistics_temp.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>album.statistics_temp.channel_num</code>. 渠道号
     */
    public void setChannelNum(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>album.statistics_temp.channel_num</code>. 渠道号
     */
    public String getChannelNum() {
        return (String) get(1);
    }

    /**
     * Setter for <code>album.statistics_temp.added_user_count</code>. 新增用户数
     */
    public void setAddedUserCount(Integer value) {
        set(2, value);
    }

    /**
     * Getter for <code>album.statistics_temp.added_user_count</code>. 新增用户数
     */
    public Integer getAddedUserCount() {
        return (Integer) get(2);
    }

    /**
     * Setter for <code>album.statistics_temp.new_user_active_count</code>. 新用户活跃数
     */
    public void setNewUserActiveCount(Integer value) {
        set(3, value);
    }

    /**
     * Getter for <code>album.statistics_temp.new_user_active_count</code>. 新用户活跃数
     */
    public Integer getNewUserActiveCount() {
        return (Integer) get(3);
    }

    /**
     * Setter for <code>album.statistics_temp.old_user_active_count</code>. 老用户活跃数
     */
    public void setOldUserActiveCount(Integer value) {
        set(4, value);
    }

    /**
     * Getter for <code>album.statistics_temp.old_user_active_count</code>. 老用户活跃数
     */
    public Integer getOldUserActiveCount() {
        return (Integer) get(4);
    }

    /**
     * Setter for <code>album.statistics_temp.keep_user_day_2</code>. 次日留存
     */
    public void setKeepUserDay_2(Integer value) {
        set(5, value);
    }

    /**
     * Getter for <code>album.statistics_temp.keep_user_day_2</code>. 次日留存
     */
    public Integer getKeepUserDay_2() {
        return (Integer) get(5);
    }

    /**
     * Setter for <code>album.statistics_temp.keep_user_day_3</code>. 三日留存
     */
    public void setKeepUserDay_3(Integer value) {
        set(6, value);
    }

    /**
     * Getter for <code>album.statistics_temp.keep_user_day_3</code>. 三日留存
     */
    public Integer getKeepUserDay_3() {
        return (Integer) get(6);
    }

    /**
     * Setter for <code>album.statistics_temp.keep_user_day_7</code>. 七日留存
     */
    public void setKeepUserDay_7(Integer value) {
        set(7, value);
    }

    /**
     * Getter for <code>album.statistics_temp.keep_user_day_7</code>. 七日留存
     */
    public Integer getKeepUserDay_7() {
        return (Integer) get(7);
    }

    /**
     * Setter for <code>album.statistics_temp.keep_user_day_15</code>. 十五日留存
     */
    public void setKeepUserDay_15(Integer value) {
        set(8, value);
    }

    /**
     * Getter for <code>album.statistics_temp.keep_user_day_15</code>. 十五日留存
     */
    public Integer getKeepUserDay_15() {
        return (Integer) get(8);
    }

    /**
     * Setter for <code>album.statistics_temp.keep_user_day_2_percent</code>.
     */
    public void setKeepUserDay_2Percent(Double value) {
        set(9, value);
    }

    /**
     * Getter for <code>album.statistics_temp.keep_user_day_2_percent</code>.
     */
    public Double getKeepUserDay_2Percent() {
        return (Double) get(9);
    }

    /**
     * Setter for <code>album.statistics_temp.keep_user_day_3_percent</code>.
     */
    public void setKeepUserDay_3Percent(Double value) {
        set(10, value);
    }

    /**
     * Getter for <code>album.statistics_temp.keep_user_day_3_percent</code>.
     */
    public Double getKeepUserDay_3Percent() {
        return (Double) get(10);
    }

    /**
     * Setter for <code>album.statistics_temp.keep_user_day_7_percent</code>.
     */
    public void setKeepUserDay_7Percent(Double value) {
        set(11, value);
    }

    /**
     * Getter for <code>album.statistics_temp.keep_user_day_7_percent</code>.
     */
    public Double getKeepUserDay_7Percent() {
        return (Double) get(11);
    }

    /**
     * Setter for <code>album.statistics_temp.keep_user_day_15_percent</code>.
     */
    public void setKeepUserDay_15Percent(Double value) {
        set(12, value);
    }

    /**
     * Getter for <code>album.statistics_temp.keep_user_day_15_percent</code>.
     */
    public Double getKeepUserDay_15Percent() {
        return (Double) get(12);
    }

    /**
     * Setter for <code>album.statistics_temp.vip1_pay_count_n</code>. 新用户（VIP1）付费次数
     */
    public void setVip1PayCountN(Integer value) {
        set(13, value);
    }

    /**
     * Getter for <code>album.statistics_temp.vip1_pay_count_n</code>. 新用户（VIP1）付费次数
     */
    public Integer getVip1PayCountN() {
        return (Integer) get(13);
    }

    /**
     * Setter for <code>album.statistics_temp.vip1_pay_user_count_n</code>. 新用户（VIP1）付费人数
     */
    public void setVip1PayUserCountN(Integer value) {
        set(14, value);
    }

    /**
     * Getter for <code>album.statistics_temp.vip1_pay_user_count_n</code>. 新用户（VIP1）付费人数
     */
    public Integer getVip1PayUserCountN() {
        return (Integer) get(14);
    }

    /**
     * Setter for <code>album.statistics_temp.vip1_pay_amount_n</code>. 新用户（VIP1）总金额
     */
    public void setVip1PayAmountN(Double value) {
        set(15, value);
    }

    /**
     * Getter for <code>album.statistics_temp.vip1_pay_amount_n</code>. 新用户（VIP1）总金额
     */
    public Double getVip1PayAmountN() {
        return (Double) get(15);
    }

    /**
     * Setter for <code>album.statistics_temp.vip1_pay_count_o</code>. 老用户（VIP1）付费次数
     */
    public void setVip1PayCountO(Integer value) {
        set(16, value);
    }

    /**
     * Getter for <code>album.statistics_temp.vip1_pay_count_o</code>. 老用户（VIP1）付费次数
     */
    public Integer getVip1PayCountO() {
        return (Integer) get(16);
    }

    /**
     * Setter for <code>album.statistics_temp.vip1_pay_user_count_o</code>.
     */
    public void setVip1PayUserCountO(Integer value) {
        set(17, value);
    }

    /**
     * Getter for <code>album.statistics_temp.vip1_pay_user_count_o</code>.
     */
    public Integer getVip1PayUserCountO() {
        return (Integer) get(17);
    }

    /**
     * Setter for <code>album.statistics_temp.vip1_pay_amount_o</code>.
     */
    public void setVip1PayAmountO(Double value) {
        set(18, value);
    }

    /**
     * Getter for <code>album.statistics_temp.vip1_pay_amount_o</code>.
     */
    public Double getVip1PayAmountO() {
        return (Double) get(18);
    }

    /**
     * Setter for <code>album.statistics_temp.vip2_pay_count_n</code>.
     */
    public void setVip2PayCountN(Integer value) {
        set(19, value);
    }

    /**
     * Getter for <code>album.statistics_temp.vip2_pay_count_n</code>.
     */
    public Integer getVip2PayCountN() {
        return (Integer) get(19);
    }

    /**
     * Setter for <code>album.statistics_temp.vip2_pay_user_count_n</code>.
     */
    public void setVip2PayUserCountN(Integer value) {
        set(20, value);
    }

    /**
     * Getter for <code>album.statistics_temp.vip2_pay_user_count_n</code>.
     */
    public Integer getVip2PayUserCountN() {
        return (Integer) get(20);
    }

    /**
     * Setter for <code>album.statistics_temp.vip2_pay_amount_n</code>.
     */
    public void setVip2PayAmountN(Double value) {
        set(21, value);
    }

    /**
     * Getter for <code>album.statistics_temp.vip2_pay_amount_n</code>.
     */
    public Double getVip2PayAmountN() {
        return (Double) get(21);
    }

    /**
     * Setter for <code>album.statistics_temp.vip2_pay_count_o</code>.
     */
    public void setVip2PayCountO(Integer value) {
        set(22, value);
    }

    /**
     * Getter for <code>album.statistics_temp.vip2_pay_count_o</code>.
     */
    public Integer getVip2PayCountO() {
        return (Integer) get(22);
    }

    /**
     * Setter for <code>album.statistics_temp.vip2_pay_user_count_o</code>.
     */
    public void setVip2PayUserCountO(Integer value) {
        set(23, value);
    }

    /**
     * Getter for <code>album.statistics_temp.vip2_pay_user_count_o</code>.
     */
    public Integer getVip2PayUserCountO() {
        return (Integer) get(23);
    }

    /**
     * Setter for <code>album.statistics_temp.vip2_pay_amount_o</code>.
     */
    public void setVip2PayAmountO(Double value) {
        set(24, value);
    }

    /**
     * Getter for <code>album.statistics_temp.vip2_pay_amount_o</code>.
     */
    public Double getVip2PayAmountO() {
        return (Double) get(24);
    }

    /**
     * Setter for <code>album.statistics_temp.create_time</code>.
     */
    public void setCreateTime(Timestamp value) {
        set(25, value);
    }

    /**
     * Getter for <code>album.statistics_temp.create_time</code>.
     */
    public Timestamp getCreateTime() {
        return (Timestamp) get(25);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached StatisticsTempRecord
     */
    public StatisticsTempRecord() {
        super(StatisticsTemp.STATISTICS_TEMP);
    }

    /**
     * Create a detached, initialised StatisticsTempRecord
     */
    public StatisticsTempRecord(Integer id, String channelNum, Integer addedUserCount, Integer newUserActiveCount, Integer oldUserActiveCount, Integer keepUserDay_2, Integer keepUserDay_3, Integer keepUserDay_7, Integer keepUserDay_15, Double keepUserDay_2Percent, Double keepUserDay_3Percent, Double keepUserDay_7Percent, Double keepUserDay_15Percent, Integer vip1PayCountN, Integer vip1PayUserCountN, Double vip1PayAmountN, Integer vip1PayCountO, Integer vip1PayUserCountO, Double vip1PayAmountO, Integer vip2PayCountN, Integer vip2PayUserCountN, Double vip2PayAmountN, Integer vip2PayCountO, Integer vip2PayUserCountO, Double vip2PayAmountO, Timestamp createTime) {
        super(StatisticsTemp.STATISTICS_TEMP);

        set(0, id);
        set(1, channelNum);
        set(2, addedUserCount);
        set(3, newUserActiveCount);
        set(4, oldUserActiveCount);
        set(5, keepUserDay_2);
        set(6, keepUserDay_3);
        set(7, keepUserDay_7);
        set(8, keepUserDay_15);
        set(9, keepUserDay_2Percent);
        set(10, keepUserDay_3Percent);
        set(11, keepUserDay_7Percent);
        set(12, keepUserDay_15Percent);
        set(13, vip1PayCountN);
        set(14, vip1PayUserCountN);
        set(15, vip1PayAmountN);
        set(16, vip1PayCountO);
        set(17, vip1PayUserCountO);
        set(18, vip1PayAmountO);
        set(19, vip2PayCountN);
        set(20, vip2PayUserCountN);
        set(21, vip2PayAmountN);
        set(22, vip2PayCountO);
        set(23, vip2PayUserCountO);
        set(24, vip2PayAmountO);
        set(25, createTime);
    }
}