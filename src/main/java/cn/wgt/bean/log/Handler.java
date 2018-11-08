package cn.wgt.bean.log;


public class Handler {
    private String account;
    private String operateTime;
    private String operateContent;


    public Handler(String account,String operateTime,String operateContent){
        this.account=account;
        this.operateTime=operateTime;
        this.operateContent=operateContent;
    }



    public void setOperateTime(String operateTime) {
        this.operateTime = operateTime;
    }

    public String getOperateTime() {
        return operateTime;
    }

    public void setOperateContent(String operateContent) {
        this.operateContent = operateContent;
    }

    public String getOperateContent() {
        return operateContent;
    }

    @Override
    public String toString() {
        return "handler"+"{account="+this.account+",operateTime="+this.getOperateTime()+
                ",operateContent="+this.getOperateContent()+"}";
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
