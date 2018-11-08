package cn.wgt.bean;


import java.sql.Timestamp;

public class Diary {

  private String id;
  private String account;
  private String title;
  private String msg;
  private String photoUrl;
  private java.sql.Timestamp pubTime;
  private String weather;

  public Diary(String id, String account, String title, String msg, String photoUrl, Timestamp pubTime, String weather) {
    this.id = id;
    this.account = account;
    this.title = title;
    this.msg = msg;
    this.photoUrl = photoUrl;
    this.pubTime = pubTime;
    this.weather = weather;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public String getAccount() {
    return account;
  }

  public void setAccount(String account) {
    this.account = account;
  }


  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }


  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }


  public String getPhotoUrl() {
    return photoUrl;
  }

  public void setPhotoUrl(String photoUrl) {
    this.photoUrl = photoUrl;
  }


  public java.sql.Timestamp getPubTime() {
    return pubTime;
  }

  public void setPubTime(java.sql.Timestamp pubTime) {
    this.pubTime = pubTime;
  }


  public String getWeather() {
    return weather;
  }

  public void setWeather(String weather) {
    this.weather = weather;
  }

}
