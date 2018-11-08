package cn.wgt.bean;


public class Task {

  private String account;
  private String taskTitle;
  private String taskPicUrl;
  private long redu;
  private long flag;

  public Task(String account, String taskTitle, String taskPicUrl, long redu, long flag) {
    this.account = account;
    this.taskTitle = taskTitle;
    this.taskPicUrl = taskPicUrl;
    this.redu = redu;
    this.flag = flag;
  }

  public String getAccount() {
    return account;
  }

  public void setAccount(String account) {
    this.account = account;
  }


  public String getTaskTitle() {
    return taskTitle;
  }

  public void setTaskTitle(String taskTitle) {
    this.taskTitle = taskTitle;
  }


  public String getTaskPicUrl() {
    return taskPicUrl;
  }

  public void setTaskPicUrl(String taskPicUrl) {
    this.taskPicUrl = taskPicUrl;
  }


  public long getRedu() {
    return redu;
  }

  public void setRedu(long redu) {
    this.redu = redu;
  }


  public long getFlag() {
    return flag;
  }

  public void setFlag(long flag) {
    this.flag = flag;
  }

}
