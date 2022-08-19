package com.example.a2remember;

public class tarih {
    int gun;
    int ay;
    int yil;
    String icerik;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    int id;

    public tarih() {
    }

    public tarih(int gun, int ay, int yil, String icerik, int id) {
        this.gun = gun;
        this.ay = ay;
        this.yil = yil;
        this.icerik = icerik;
        this.id = id;
    }

    public int getGun() {
        return gun;
    }

    public void setGun(int gun) {
        this.gun = gun;
    }

    public int getAy() {
        return ay;
    }

    public void setAy(int ay) {
        this.ay = ay;
    }

    public int getYil() {
        return yil;
    }

    public void setYil(int yil) {
        this.yil = yil;
    }

    public String getIcerik() {
        return icerik;
    }

    public void setIcerik(String icerik) {
        this.icerik = icerik;
    }
}
