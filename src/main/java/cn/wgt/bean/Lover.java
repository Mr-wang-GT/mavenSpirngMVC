package cn.wgt.bean;


public class Lover {

  private String account;
  private String bindedAccount;

  public Lover(String account, String bindedAccount) {
    this.account = account;
    this.bindedAccount = bindedAccount;
  }

  public String getAccount() {
    return account;
  }

  public void setAccount(String account) {
    this.account = account;
  }


  public String getBindedAccount() {
    return bindedAccount;
  }

  public void setBindedAccount(String bindedAccount) {
    this.bindedAccount = bindedAccount;
  }

}
