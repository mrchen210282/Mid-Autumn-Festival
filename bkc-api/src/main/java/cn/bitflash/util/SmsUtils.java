package cn.bitflash.util;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;


/**
 * @author soso
 * @date 2018��5��22�� ����6:37:31
 */

public class SmsUtils {
    public static R smsApi(String phoneNumber, String verifyCode, String signName, String templateCode) {
        try {
            // ���ó�ʱʱ��-�����е���
            System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
            System.setProperty("sun.net.client.defaultReadTimeout", "10000");
            // ��ʼ��ascClient��Ҫ�ļ�������
            final String product = "Dysmsapi";// ����API��Ʒ���ƣ����Ų�Ʒ���̶��������޸ģ�
            final String domain = "dysmsapi.aliyuncs.com";// ����API��Ʒ�������ӿڵ�ַ�̶��������޸ģ�
            // �滻�����AK
            final String accessKeyId = "LTAIMrTiy4deE8bo";// ���accessKeyId,�ο����ĵ�����2
            final String accessKeySecret = "1hF0rq269TlwO0JXRG98QMGcXrynes";// ���accessKeySecret���ο����ĵ�����2
            // ��ʼ��ascClient,��ʱ��֧�ֶ�region�������޸ģ�
            IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
            IAcsClient acsClient = new DefaultAcsClient(profile);
            // ��װ�������
            SendSmsRequest request = new SendSmsRequest();
            // ʹ��post�ύ
            request.setMethod(MethodType.POST);
            // ����:�������ֻ��š�֧���Զ��ŷָ�����ʽ�����������ã���������Ϊ1000���ֻ�����,������������ڵ������ü�ʱ�������ӳ�,��֤�����͵Ķ����Ƽ�ʹ�õ������õķ�ʽ�����͹���/�۰�̨��Ϣʱ�����պ����ʽΪ00+��������+���룬�硰0085200000000��
            request.setPhoneNumbers(phoneNumber);
            // ����:����ǩ��-���ڶ��ſ���̨���ҵ�
            request.setSignName(signName);
            // ����:����ģ��-���ڶ��ſ���̨���ҵ�
            request.setTemplateCode(templateCode);
            // ��ѡ:ģ���еı����滻JSON��,��ģ������Ϊ"�װ���${name},������֤��Ϊ${code}"ʱ,�˴���ֵΪ
            // ������ʾ:���JSON����Ҫ�����з�,����ձ�׼��JSONЭ��Ի��з���Ҫ��,������������а���\r\n�������JSON����Ҫ��ʾ��\\r\\n,����ᵼ��JSON�ڷ���˽���ʧ��
            request.setTemplateParam("{\"code\":\"" + verifyCode + "\"}");
            // ��ѡ-���ж�����չ��(��չ���ֶο�����7λ�����£������������û�����Դ��ֶ�)
            // request.setSmsUpExtendCode("90997");
            // ��ѡ:outIdΪ�ṩ��ҵ����չ�ֶ�,�����ڶ��Ż�ִ��Ϣ�н���ֵ���ظ�������
            // request.setOutId("yourOutId");
            // ����ʧ���������ClientException�쳣
            SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
            if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("mobile", phoneNumber);
                map.put("verifyCode", verifyCode);
                return R.ok(map);
            }
            return R.error(500, "���ʹ���");
        } catch (ServerException e) {
            e.printStackTrace();
            return R.error(500, "���ŷ������");
        } catch (ClientException e) {
            e.printStackTrace();
            return R.error(500, "���ŷ������");
        }

    }
}
