package cn.bitflash.vip.user.entity;

import javax.validation.constraints.NotBlank;

public class ImgForm {

    private String img;

    @NotBlank(message = "图片类型不能为空")
    private String imgType;

    @NotBlank(message = "账户不能为空")
    private String account;

    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getImgType() {
        return imgType;
    }

    public void setImgType(String imgType) {
        this.imgType = imgType;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

}
