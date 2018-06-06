package com.klanrock.klanrock;

/**
 * Created by fendrik on 20/05/2018.
 */

public class Galery {
    private String name;
    private String thumbnail;
    private String btn;
    private String id;
    private String jml_foto;
    private String keterangan;

    public Galery(String name,String thumbnail, String btn,String id,String jmt_foto,String keterangan) {
        this.name = name;
        this.thumbnail = thumbnail;
        this.btn = btn;
        this.id = id;
        this.jml_foto = jmt_foto;
        this.keterangan = keterangan;
    }
    public String getId(){
        return this.id;
    }
    public String getJml_foto(){
        return this.jml_foto;
    }
    public String getKeterangan(){
        return this.keterangan;
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


    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}

