/*
 * This file is generated by jOOQ.
*/
package com.yz.album.orm.tables;


import com.yz.album.orm.Album;
import com.yz.album.orm.Keys;
import com.yz.album.orm.tables.records.UserAlbumCommentRecord;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.TableImpl;


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
public class UserAlbumComment extends TableImpl<UserAlbumCommentRecord> {

    private static final long serialVersionUID = 637877954;

    /**
     * The reference instance of <code>album.user_album_comment</code>
     */
    public static final UserAlbumComment USER_ALBUM_COMMENT = new UserAlbumComment();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<UserAlbumCommentRecord> getRecordType() {
        return UserAlbumCommentRecord.class;
    }

    /**
     * The column <code>album.user_album_comment.id</code>.
     */
    public final TableField<UserAlbumCommentRecord, Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>album.user_album_comment.user_id</code>.
     */
    public final TableField<UserAlbumCommentRecord, Integer> USER_ID = createField("user_id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>album.user_album_comment.album_id</code>.
     */
    public final TableField<UserAlbumCommentRecord, Integer> ALBUM_ID = createField("album_id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>album.user_album_comment.content</code>.
     */
    public final TableField<UserAlbumCommentRecord, String> CONTENT = createField("content", org.jooq.impl.SQLDataType.VARCHAR.length(500).nullable(false), this, "");

    /**
     * The column <code>album.user_album_comment.rate</code>.
     */
    public final TableField<UserAlbumCommentRecord, Double> RATE = createField("rate", org.jooq.impl.SQLDataType.DOUBLE, this, "");

    /**
     * The column <code>album.user_album_comment.create_time</code>.
     */
    public final TableField<UserAlbumCommentRecord, Timestamp> CREATE_TIME = createField("create_time", org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false).defaultValue(org.jooq.impl.DSL.inline("CURRENT_TIMESTAMP", org.jooq.impl.SQLDataType.TIMESTAMP)), this, "");

    /**
     * The column <code>album.user_album_comment.update_time</code>.
     */
    public final TableField<UserAlbumCommentRecord, Timestamp> UPDATE_TIME = createField("update_time", org.jooq.impl.SQLDataType.TIMESTAMP, this, "");

    /**
     * Create a <code>album.user_album_comment</code> table reference
     */
    public UserAlbumComment() {
        this("user_album_comment", null);
    }

    /**
     * Create an aliased <code>album.user_album_comment</code> table reference
     */
    public UserAlbumComment(String alias) {
        this(alias, USER_ALBUM_COMMENT);
    }

    private UserAlbumComment(String alias, Table<UserAlbumCommentRecord> aliased) {
        this(alias, aliased, null);
    }

    private UserAlbumComment(String alias, Table<UserAlbumCommentRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return Album.ALBUM;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<UserAlbumCommentRecord, Integer> getIdentity() {
        return Keys.IDENTITY_USER_ALBUM_COMMENT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<UserAlbumCommentRecord> getPrimaryKey() {
        return Keys.KEY_USER_ALBUM_COMMENT_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<UserAlbumCommentRecord>> getKeys() {
        return Arrays.<UniqueKey<UserAlbumCommentRecord>>asList(Keys.KEY_USER_ALBUM_COMMENT_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ForeignKey<UserAlbumCommentRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<UserAlbumCommentRecord, ?>>asList(Keys.USER_ALBUM_COMMENT_IBFK_1, Keys.USER_ALBUM_COMMENT_IBFK_2);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserAlbumComment as(String alias) {
        return new UserAlbumComment(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public UserAlbumComment rename(String name) {
        return new UserAlbumComment(name, null);
    }
}