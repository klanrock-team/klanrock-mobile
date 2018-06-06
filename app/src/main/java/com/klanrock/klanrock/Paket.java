package com.klanrock.klanrock;
public class Paket {
    private String name;
    private int price;
    private String thumbnail;
    private String btn;
    private String id;
    private String kategori;

    public Paket(String name, int price, String thumbnail, String btn,String id,String kategori) {
        this.name = name;
        this.price = price;
        this.thumbnail = thumbnail;
        this.btn = btn;
        this.id = id;
        this.kategori=kategori;
    }
    public String getKategori(){
        return this.kategori;
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
    public String getId(){
        return this.id;
    }
    public String getThumbnail() {
        return thumbnail;
    }

}
