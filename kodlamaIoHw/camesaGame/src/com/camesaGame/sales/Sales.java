package com.camesaGame.sales;

public class Sales {
    private int id;
    private String nameOfGameSold;
    private int level;
    private int price;

    public Sales() {
    }

    public Sales(int id, String nameOfGameSold, int level, int price) {
        this.id = id;
        this.nameOfGameSold = nameOfGameSold;
        this.level = level;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameOfGameSold() {
        return nameOfGameSold;
    }

    public void setNameOfGameSold(String nameOfGameSold) {
        this.nameOfGameSold = nameOfGameSold;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getPrice() {
        return price;
    }

    public int setPrice(int price) {
        this.price = price;
        return price;
    }
}
