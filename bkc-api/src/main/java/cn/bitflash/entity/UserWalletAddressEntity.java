package cn.bitflash.entity;

import java.io.Serializable;

/**
 * @author gaoyuguo
 * @date 2018年9月22日
 */
public class UserWalletAddressEntity implements Serializable {
    private String uid;

    private String address;

    private String privateKey;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }
}
