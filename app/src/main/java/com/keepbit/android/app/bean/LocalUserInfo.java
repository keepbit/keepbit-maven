package com.keepbit.android.app.bean;

/**
 * Created by CoderMario on 2019-05-15.
 */
public class LocalUserInfo {

    private String avatar;
    private String name;

    private int platform;

    private int gender;
    private int age;

    private int uid;

    private String signature;

    public String getAvatar() {
        return this.avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPlatform() {
        return this.platform;
    }

    public void setPlatform(int platform) {
        this.platform = platform;
    }

    public int getGender() {
        return this.gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getUid() {
        return this.uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getSignature() {
        return this.signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    @Override
    public String toString() {
        return String.format("{uid = %s, name = %s, age = %s, gender = %s, avatar = %s, platform = %s, signature = %s}"
                , this.uid, this.name, this.age, this.gender, this.age, this.platform, this.signature);
    }
}
