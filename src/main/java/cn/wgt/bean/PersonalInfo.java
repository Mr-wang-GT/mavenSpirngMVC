package cn.wgt.bean;

public class PersonalInfo {

  private String account;
  private String name;
  private String hoppy;
  private String personalSig;

  public PersonalInfo(String account, String name, String hoppy, String personalSig) {
    this.account = account;
    this.name = name;
    this.hoppy = hoppy;
    this.personalSig = personalSig;
  }

  public String getAccount() {
    return account;
  }

  public void setAccount(String account) {
    this.account = account;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getHoppy() {
    return hoppy;
  }

  public void setHoppy(String hoppy) {
    this.hoppy = hoppy;
  }


  public String getPersonalSig() {
    return personalSig;
  }

  public void setPersonalSig(String personalSig) {
    this.personalSig = personalSig;
  }

}
