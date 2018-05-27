package com.yz.album.api.dto;

public class Picture {

    private String id;
    private String tag_id;
    private String url;

    /************************************************/
    private String commentAmount;
    private String favoriteAmount;
    private String praiseAmount;
    private String readAmount;
    private Integer isPraise;
    private Integer isFavorite;


    public Integer getIsPraise() {
        return isPraise;
    }

    public void setIsPraise(Integer isPraise) {
        this.isPraise = isPraise;
    }

    public Integer getIsFavorite() {
        return isFavorite;
    }

    public void setIsFavorite(Integer isFavorite) {
        this.isFavorite = isFavorite;
    }

    public String getCommentAmount() {
        return commentAmount;
    }

    public void setCommentAmount(String commentAmount) {
        this.commentAmount = commentAmount;
    }

    public String getFavoriteAmount() {
        return favoriteAmount;
    }

    public void setFavoriteAmount(String favoriteAmount) {
        this.favoriteAmount = favoriteAmount;
    }

    public String getPraiseAmount() {
        return praiseAmount;
    }

    public void setPraiseAmount(String praiseAmount) {
        this.praiseAmount = praiseAmount;
    }

    public String getReadAmount() {
        return readAmount;
    }

    public void setReadAmount(String readAmount) {
        this.readAmount = readAmount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTag_id() {
        return tag_id;
    }

    public void setTag_id(String tag_id) {
        this.tag_id = tag_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
