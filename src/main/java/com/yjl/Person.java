package com.yjl;

/**
 * @author unbroken
 * @Description TODO
 * @Version 1.0
 * @date 2023/4/12 22:16
 */
public class Person {
    private String uuid;
    private String userName;
    public String getUuid() {
        return uuid;
    }
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "Person{" +
                "uuid='" + uuid + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
