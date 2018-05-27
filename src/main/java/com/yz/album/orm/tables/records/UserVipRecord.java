/*
 * This file is generated by jOOQ.
*/
package com.yz.album.orm.tables.records;


import com.yz.album.orm.tables.UserVip;

import java.sql.Timestamp;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record6;
import org.jooq.Row6;
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
public class UserVipRecord extends UpdatableRecordImpl<UserVipRecord> implements Record6<Integer, Integer, Integer, Timestamp, Timestamp, Timestamp> {

    private static final long serialVersionUID = 400018599;

    /**
     * Setter for <code>album.user_vip.id</code>.
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>album.user_vip.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>album.user_vip.user_id</code>.
     */
    public void setUserId(Integer value) {
        set(1, value);
    }

    /**
     * Getter for <code>album.user_vip.user_id</code>.
     */
    public Integer getUserId() {
        return (Integer) get(1);
    }

    /**
     * Setter for <code>album.user_vip.is_super_vip</code>.
     */
    public void setIsSuperVip(Integer value) {
        set(2, value);
    }

    /**
     * Getter for <code>album.user_vip.is_super_vip</code>.
     */
    public Integer getIsSuperVip() {
        return (Integer) get(2);
    }

    /**
     * Setter for <code>album.user_vip.expired_time</code>.
     */
    public void setExpiredTime(Timestamp value) {
        set(3, value);
    }

    /**
     * Getter for <code>album.user_vip.expired_time</code>.
     */
    public Timestamp getExpiredTime() {
        return (Timestamp) get(3);
    }

    /**
     * Setter for <code>album.user_vip.create_time</code>.
     */
    public void setCreateTime(Timestamp value) {
        set(4, value);
    }

    /**
     * Getter for <code>album.user_vip.create_time</code>.
     */
    public Timestamp getCreateTime() {
        return (Timestamp) get(4);
    }

    /**
     * Setter for <code>album.user_vip.update_time</code>.
     */
    public void setUpdateTime(Timestamp value) {
        set(5, value);
    }

    /**
     * Getter for <code>album.user_vip.update_time</code>.
     */
    public Timestamp getUpdateTime() {
        return (Timestamp) get(5);
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
    // Record6 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row6<Integer, Integer, Integer, Timestamp, Timestamp, Timestamp> fieldsRow() {
        return (Row6) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row6<Integer, Integer, Integer, Timestamp, Timestamp, Timestamp> valuesRow() {
        return (Row6) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field1() {
        return UserVip.USER_VIP.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field2() {
        return UserVip.USER_VIP.USER_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field3() {
        return UserVip.USER_VIP.IS_SUPER_VIP;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Timestamp> field4() {
        return UserVip.USER_VIP.EXPIRED_TIME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Timestamp> field5() {
        return UserVip.USER_VIP.CREATE_TIME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Timestamp> field6() {
        return UserVip.USER_VIP.UPDATE_TIME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value2() {
        return getUserId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value3() {
        return getIsSuperVip();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp value4() {
        return getExpiredTime();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp value5() {
        return getCreateTime();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp value6() {
        return getUpdateTime();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserVipRecord value1(Integer value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserVipRecord value2(Integer value) {
        setUserId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserVipRecord value3(Integer value) {
        setIsSuperVip(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserVipRecord value4(Timestamp value) {
        setExpiredTime(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserVipRecord value5(Timestamp value) {
        setCreateTime(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserVipRecord value6(Timestamp value) {
        setUpdateTime(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserVipRecord values(Integer value1, Integer value2, Integer value3, Timestamp value4, Timestamp value5, Timestamp value6) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached UserVipRecord
     */
    public UserVipRecord() {
        super(UserVip.USER_VIP);
    }

    /**
     * Create a detached, initialised UserVipRecord
     */
    public UserVipRecord(Integer id, Integer userId, Integer isSuperVip, Timestamp expiredTime, Timestamp createTime, Timestamp updateTime) {
        super(UserVip.USER_VIP);

        set(0, id);
        set(1, userId);
        set(2, isSuperVip);
        set(3, expiredTime);
        set(4, createTime);
        set(5, updateTime);
    }
}