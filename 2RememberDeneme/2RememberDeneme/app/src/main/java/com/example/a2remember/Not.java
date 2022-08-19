package com.example.a2remember;

public class Not {
    private long ID;
    private String baslik;
    private String content;
    private String date;
    private String time;

    Not() {}
    Not (String baslik, String content, String date, String time) {
        this.baslik = baslik;
        this.content= content;
        this.date=date;
        this.time=time;
    }

    Not (long id,String baslik, String content, String date, String time){
        this.ID=id;
        this.baslik = baslik;
        this.content= content;
        this.date=date;
        this.time=time;

    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getBaslik() {
        return baslik;
    }

    public void setBaslik(String baslik) {
        this.baslik = baslik;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
