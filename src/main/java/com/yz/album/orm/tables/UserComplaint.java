/*
 * This file is generated by jOOQ.
*/
package com.yz.album.orm.tables;


import com.yz.album.orm.Album;
import com.yz.album.orm.Keys;
import com.yz.album.orm.tables.records.UserComplaintRecord;

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
public class UserComplaint extends TableImpl<UserComplaintRecord> {

    private static final long serialVersionUID = -594221780;

    /**
     * The reference instance of <code>album.user_complaint</code>
     */
    public static final UserComplaint USER_COMPLAINT = new UserComplaint();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<UserComplaintRecord> getRecordType() {
        return UserComplaintRecord.class;
    }

    /**
     * The column <code>album.user_complaint.id</code>.
     */
    public final TableField<UserComplaintRecord, Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>album.user_complaint.user_id</code>.
     */
    public final TableField<UserComplaintRecord, Integer> USER_ID = createField("user_id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>album.user_complaint.content</code>.
     */
    public final TableField<UserComplaintRecord, String> CONTENT = createField("content", org.jooq.impl.SQLDataType.VARCHAR.length(500).nullable(false), this, "");

    /**
     * The column <code>album.user_complaint.create_time</code>.
     */
    public final TableField<UserComplaintRecord, Timestamp> CREATE_TIME = createField("create_time", org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false).defaultValue(org.jooq.impl.DSL.inline("CURRENT_TIMESTAMP", org.jooq.impl.SQLDataType.TIMESTAMP)), this, "");

    /**
     * Create a <code>album.user_complaint</code> table reference
     */
    public UserComplaint() {
        this("user_complaint", null);
    }

    /**
     * Create an aliased <code>album.user_complaint</code> table reference
     */
    public UserComplaint(String alias) {
        this(alias, USER_COMPLAINT);
    }

    private UserComplaint(String alias, Table<UserComplaintRecord> aliased) {
        this(alias, aliased, null);
    }

    private UserComplaint(String alias, Table<UserComplaintRecord> aliased, Field<?>[] parameters) {
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
    public Identity<UserComplaintRecord, Integer> getIdentity() {
        return Keys.IDENTITY_USER_COMPLAINT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<UserComplaintRecord> getPrimaryKey() {
        return Keys.KEY_USER_COMPLAINT_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<UserComplaintRecord>> getKeys() {
        return Arrays.<UniqueKey<UserComplaintRecord>>asList(Keys.KEY_USER_COMPLAINT_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ForeignKey<UserComplaintRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<UserComplaintRecord, ?>>asList(Keys.USER_COMPLAINT_IBFK_1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserComplaint as(String alias) {
        return new UserComplaint(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public UserComplaint rename(String name) {
        return new UserComplaint(name, null);
    }
}