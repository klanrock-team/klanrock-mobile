package com.klanrock.klanrock;


public class Galery {
    private String name;
    private int price;
    private int thumbnail;
    private String btn;

    public Galery(String name, int price, int thumbnail, String btn) {
        this.name = name;
        this.price = price;
        this.thumbnail = thumbnail;
        this.btn = btn;
    }
    public String getBtn(){
        return btn;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }
}

