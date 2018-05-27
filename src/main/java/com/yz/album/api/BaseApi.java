package com.yz.album.api;

import com.yz.album.orm.tables.*;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseApi {

    @Autowired
    protected DSLContext dslContext;

    /**
     * The table <code>album.album</code>.
     */
    public static final Album ALBUM = com.yz.album.orm.tables.Album.ALBUM_;

    /**
     * The table <code>album.album_category</code>.
     */
    public static final AlbumCategory ALBUM_CATEGORY = com.yz.album.orm.tables.AlbumCategory.ALBUM_CATEGORY;

    /**
     * The table <code>album.album_picture</code>.
     */
    public static final AlbumPicture ALBUM_PICTURE = com.yz.album.orm.tables.AlbumPicture.ALBUM_PICTURE;

    /**
     * The table <code>album.banner</code>.
     */
    public static final Banner BANNER = com.yz.album.orm.tables.Banner.BANNER;

    /**
     * The table <code>album.category</code>.
     */
    public static final Category CATEGORY = com.yz.album.orm.tables.Category.CATEGORY;

    /**
     * The table <code>album.essenceurl</code>.
     */
    public static final Essenceurl ESSENCEURL = com.yz.album.orm.tables.Essenceurl.ESSENCEURL;

    /**
     * The table <code>album.head_picture</code>.
     */
    public static final HeadPicture HEAD_PICTURE = com.yz.album.orm.tables.HeadPicture.HEAD_PICTURE;

    /**
     * The table <code>album.login_log</code>.
     */
    public static final LoginLog LOGIN_LOG = com.yz.album.orm.tables.LoginLog.LOGIN_LOG;

    /**
     * The table <code>album.picture</code>.
     */
    public static final Picture PICTURE = com.yz.album.orm.tables.Picture.PICTURE;

    /**
     * The table <code>album.statistics_temp</code>.
     */
    public static final StatisticsTemp STATISTICS_TEMP = com.yz.album.orm.tables.StatisticsTemp.STATISTICS_TEMP;

    /**
     * The table <code>album.user</code>.
     */
    public static final User USER = com.yz.album.orm.tables.User.USER;

    /**
     * The table <code>album.user_album_comment</code>.
     */
    public static final UserAlbumComment USER_ALBUM_COMMENT = com.yz.album.orm.tables.UserAlbumComment.USER_ALBUM_COMMENT;

    /**
     * The table <code>album.user_album_comment_praise</code>.
     */
    public static final UserAlbumCommentPraise USER_ALBUM_COMMENT_PRAISE = com.yz.album.orm.tables.UserAlbumCommentPraise.USER_ALBUM_COMMENT_PRAISE;

    /**
     * The table <code>album.user_album_comment_sub</code>.
     */
    public static final UserAlbumCommentSub USER_ALBUM_COMMENT_SUB = com.yz.album.orm.tables.UserAlbumCommentSub.USER_ALBUM_COMMENT_SUB;

    /**
     * The table <code>album.user_album_favorite</code>.
     */
    public static final UserAlbumFavorite USER_ALBUM_FAVORITE = com.yz.album.orm.tables.UserAlbumFavorite.USER_ALBUM_FAVORITE;

    /**
     * The table <code>album.user_album_praise</code>.
     */
    public static final UserAlbumPraise USER_ALBUM_PRAISE = com.yz.album.orm.tables.UserAlbumPraise.USER_ALBUM_PRAISE;

    /**
     * The table <code>album.user_album_read</code>.
     */
    public static final UserAlbumRead USER_ALBUM_READ = com.yz.album.orm.tables.UserAlbumRead.USER_ALBUM_READ;

    /**
     * The table <code>album.user_complaint</code>.
     */
    public static final UserComplaint USER_COMPLAINT = com.yz.album.orm.tables.UserComplaint.USER_COMPLAINT;

    /**
     * The table <code>album.user_pay</code>.
     */
    public static final UserPay USER_PAY = com.yz.album.orm.tables.UserPay.USER_PAY;

    /**
     * The table <code>album.user_search_keyword</code>.
     */
    public static final UserSearchKeyword USER_SEARCH_KEYWORD = com.yz.album.orm.tables.UserSearchKeyword.USER_SEARCH_KEYWORD;

    /**
     * The table <code>album.user_vip</code>.
     */
    public static final UserVip USER_VIP = com.yz.album.orm.tables.UserVip.USER_VIP;

}
