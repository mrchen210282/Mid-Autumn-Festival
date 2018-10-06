package cn.bitflash.vip.trade.controller;

public class ShoujiHuaFei {


   /* public static void main(String[] args) {

        int cardnum = 100;
        long phoneno = 15041133842L;
        String key = "335ed0a89818d1614eaf149ef2b63a78";
        StringBuffer sb = new StringBuffer();
        sb.append("");
        sb.append("?cardnum=" + cardnum);
        sb.append("&phoneno=" + phoneno);
        sb.append("&key="+key);
        String host = "http://op.juhe.cn";
        String path = "/ofpay/mobile/telcheck";
        try {
            HttpResponse response = HttpUtils.doGet(host, null, method, headers, querys);
            //获取response的body
            String msg = EntityUtils.toString(response.getEntity());
            code = JSON.parseObject(msg).getString("status");
            if (code.equals("01") || code.equals("1")) {
                info.setIsAuthentication(Common.AUTHENTICATION);
                userFeign.updateUserInfoById(info);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }*/
}
