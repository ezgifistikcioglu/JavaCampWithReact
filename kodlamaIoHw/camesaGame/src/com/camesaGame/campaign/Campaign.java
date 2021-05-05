package com.camesaGame.campaign;

public class Campaign {
    private int id;
    private String campaignName;
    private int discount;
    private String giftName;

    public Campaign() {
    }

    public Campaign(int id, String campaignName, int discount, String giftName) {
        this.id = id;
        this.campaignName = campaignName;
        this.discount = discount;
        this.giftName = giftName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCampaignName() {
        return campaignName;
    }

    public void setCampaignName(String campaignName) {
        this.campaignName = campaignName;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getGiftName() {
        return giftName;
    }

    public void setGiftName(String giftName) {
        this.giftName = giftName;
    }
}
