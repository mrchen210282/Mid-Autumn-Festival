package cn.bitflash.manage;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Decoder;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Map;

@RestController
@RequestMapping("answerPicture")
public class AnswerController {

    @PostMapping("addAnswerPng")
    public Map<String,Object> uploadPayment(@RequestBody String img) {

        String imgPath = "http://www.bitflash.vip/answer/";
        String path = "/home/statics/answer/";
        String imgName = RandomStringUtils.randomAlphanumeric(10)+".png";
        path = path+imgName;
        BASE64Decoder decoder = new BASE64Decoder();
        try {

            // Base64解码
            String[] base64Str = img.split(",");
            if (base64Str.length >= 2) {
                byte[] b = decoder.decodeBuffer(base64Str[1]);
                for (int i = 0; i < b.length; ++i) {
                    if (b[i] < 0) {// 调整异常数据
                        b[i] += 256;
                    }
                }
                // 生成jpeg图片
                OutputStream out = new FileOutputStream(path);
                out.write(b);
                out.flush();
                out.close();
            } else {
                return new ModelMap("status",500);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelMap("status",500);
        }
        return new ModelMap("status",0).addAttribute("address",imgPath+imgName);
    }

}
