package cn.bitflash.utils;

import cn.bitflash.vip.user.controller.WalletAddress;

public class CreatAddress {

    public static void main(String[] args) {
        String uid = "51623111";
        //创建钱包地址
        WalletAddress walletAddress = new WalletAddress();
        try {
            walletAddress.createWalletAddress(uid);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
