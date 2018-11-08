package cn.wgt.bean;

public class DatingInfo {
    private String account;
    private String name;

    private String hometown;

    private String location;
    private String longitude;
    private String altitude;

    private String sex;
    private String height;
    private String birthday;

    /**
     * 星座信息
     */
    private String constellatory;
    /**
     * 兴趣爱好
     */
    private String hobby;
    /**
     * 交友宣言
     */
    private String declaration;
    private String photo;

    public DatingInfo(String account, String name, String hometown, String location, String longitude, String altitude, String sex, String height,
                      String birthday, String constellatory, String hobby,
                      String declaration, String photo) {
        this.account = account;
        this.name = name;
        this.hometown = hometown;
        this.location=location;
        this.longitude = longitude;
        this.altitude=altitude;
        this.sex = sex;
        this.height=height;
        this.birthday = birthday;
        this.constellatory = constellatory;
        this.hobby = hobby;
        this.declaration = declaration;
        this.photo = photo;
    }
    public DatingInfo(){}
//    public String toString(){
//        return "datingInfo{" +
//
//                " account='" + account + '\'' +
//                "name='" + name + '\'' +
//                ", longitude='" + longitude + '\'' +
//                ", sex='" + sex + '\'' +
//                ", height='" + height + '\'' +
//                " birthday='" + birthday + '\'' +
//                "constellatory='" + constellatory + '\'' +
//                ", hobby='" + hobby + '\'' +
//                ", declaration='" + declaration + '\'' +
//                ", photo='" + photo + '\'' +
//
//                '}';
//
//    }
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getConstellatory() {
        return constellatory;
    }

    public void setConstellatory(String constellatory) {
        this.constellatory = constellatory;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getDeclaration() {
        return declaration;
    }

    public void setDeclaration(String declaration) {
        this.declaration = declaration;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getAltitude() {
        return altitude;
    }

    public void setAltitude(String altitude) {
        this.altitude = altitude;
    }

    public String getHometown() {
        return hometown;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }
}
