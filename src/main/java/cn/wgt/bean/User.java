package cn.wgt.bean;
/**
 * @author cn.wgt
 */
public class User {

    /**
     * 账号即手机号
     */
    private String account;
    private String password;
    private String secretQA;
    public User(String account,  String password,String secretQA) {
        this.account = account;
        this.password = password;
        this.secretQA=secretQA;
    }


    @Override
    public String toString() {
        return "user{" +

                " account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", secretQA='" + secretQA + '\'' +
                '}';
    }

    public String getSecretQA() {
        return secretQA;
    }

    public void setSecretQA(String secretQA) {
        this.secretQA = secretQA;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User() {

    }

}