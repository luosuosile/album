/*
 * This file is generated by jOOQ.
*/
package com.yz.album.orm.tables.records;


import com.yz.album.orm.tables.UserAlbumFavorite;

import java.sql.Timestamp;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record5;
import org.jooq.Row5;
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
public class UserAlbumFavoriteRecord extends UpdatableRecordImpl<UserAlbumFavoriteRecord> implements Record5<Integer, Integer, Integer, Timestamp, Timestamp> {

    private static final long serialVersionUID = 1149939636;

    /**
     * Setter for <code>album.user_album_favorite.id</code>.
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>album.user_album_favorite.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>album.user_album_favorite.user_id</code>.
     */
    public void setUserId(Integer value) {
        set(1, value);
    }

    /**
     * Getter for <code>album.user_album_favorite.user_id</code>.
     */
    public Integer getUserId() {
        return (Integer) get(1);
    }

    /**
     * Setter for <code>album.user_album_favorite.album_id</code>.
     */
    public void setAlbumId(Integer value) {
        set(2, value);
    }

    /**
     * Getter for <code>album.user_album_favorite.album_id</code>.
     */
    public Integer getAlbumId() {
        return (Integer) get(2);
    }

    /**
     * Setter for <code>album.user_album_favorite.create_time</code>.
     */
    public void setCreateTime(Timestamp value) {
        set(3, value);
    }

    /**
     * Getter for <code>album.user_album_favorite.create_time</code>.
     */
    public Timestamp getCreateTime() {
        return (Timestamp) get(3);
    }

    /**
     * Setter for <code>album.user_album_favorite.update_time</code>.
     */
    public void setUpdateTime(Timestamp value) {
        set(4, value);
    }

    /**
     * Getter for <code>album.user_album_favorite.update_time</code>.
     */
    public Timestamp getUpdateTime() {
        return (Timestamp) get(4);
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
    // Record5 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row5<Integer, Integer, Integer, Timestamp, Timestamp> fieldsRow() {
        return (Row5) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row5<Integer, Integer, Integer, Timestamp, Timestamp> valuesRow() {
        return (Row5) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field1() {
        return UserAlbumFavorite.USER_ALBUM_FAVORITE.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field2() {
        return UserAlbumFavorite.USER_ALBUM_FAVORITE.USER_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field3() {
        return UserAlbumFavorite.USER_ALBUM_FAVORITE.ALBUM_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Timestamp> field4() {
        return UserAlbumFavorite.USER_ALBUM_FAVORITE.CREATE_TIME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Timestamp> field5() {
        return UserAlbumFavorite.USER_ALBUM_FAVORITE.UPDATE_TIME;
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
        return getAlbumId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp value4() {
        return getCreateTime();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp value5() {
        return getUpdateTime();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserAlbumFavoriteRecord value1(Integer value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserAlbumFavoriteRecord value2(Integer value) {
        setUserId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserAlbumFavoriteRecord value3(Integer value) {
        setAlbumId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserAlbumFavoriteRecord value4(Timestamp value) {
        setCreateTime(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserAlbumFavoriteRecord value5(Timestamp value) {
        setUpdateTime(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserAlbumFavoriteRecord values(Integer value1, Integer value2, Integer value3, Timestamp value4, Timestamp value5) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached UserAlbumFavoriteRecord
     */
    public UserAlbumFavoriteRecord() {
        super(UserAlbumFavorite.USER_ALBUM_FAVORITE);
    }

    /**
     * Create a detached, initialised UserAlbumFavoriteRecord
     */
    public UserAlbumFavoriteRecord(Integer id, Integer userId, Integer albumId, Timestamp createTime, Timestamp updateTime) {
        super(UserAlbumFavorite.USER_ALBUM_FAVORITE);

        set(0, id);
        set(1, userId);
        set(2, albumId);
        set(3, createTime);
        set(4, updateTime);
    }
}
