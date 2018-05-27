package com.yz.album.api.dto;

import java.util.List;

public class Album {

    private Integer id;
    private String name;

    private String isPutAway;
    private String thumbnailUrl;

    /****************************************************************************************************/
    private List<String> category;
    private Integer commentAmount;
    private Integer favoriteAmount;
    private Integer readAmount;
    private Integer praiseAmount;
    private int isPraise;
    private int isFavorite;
    private String channelNum;
    private Integer rate;

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }

    public String getIsPutAway() {
        return isPutAway;
    }

    public void setIsPutAway(String isPutAway) {
        this.isPutAway = isPutAway;
    }

    public Integer getCommentAmount() {
        return commentAmount;
    }

    public void setCommentAmount(Integer commentAmount) {
        this.commentAmount = commentAmount;
    }

    public Integer getFavoriteAmount() {
        return favoriteAmount;
    }

    public void setFavoriteAmount(Integer favoriteAmount) {
        this.favoriteAmount = favoriteAmount;
    }

    public Integer getReadAmount() {
        return readAmount;
    }

    public void setReadAmount(Integer readAmount) {
        this.readAmount = readAmount;
    }

    public Integer getPraiseAmount() {
        return praiseAmount;
    }

    public void setPraiseAmount(Integer praiseAmount) {
        this.praiseAmount = praiseAmount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIsPraise() {
        return isPraise;
    }

    public void setIsPraise(int isPraise) {
        this.isPraise = isPraise;
    }

    public int getIsFavorite() {
        return isFavorite;
    }

    public void setIsFavorite(int isFavorite) {
        this.isFavorite = isFavorite;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getChannelNum() {
        return channelNum;
    }

    public void setChannelNum(String channelNum) {
        this.channelNum = channelNum;
    }
}
