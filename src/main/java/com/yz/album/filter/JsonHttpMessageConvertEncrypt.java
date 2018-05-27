package com.yz.album.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.yz.album.util.AesUtil;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;

public class JsonHttpMessageConvertEncrypt extends FastJsonHttpMessageConverter {

    @Override
    protected void writeInternal(Object obj, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {


        String jsonString = JSON.toJSONString(obj);

        //加密
        String result = null;
        try {
            result = jsonString;
            //加密开关
            result = AesUtil.aesEncrypt(jsonString,"1259632153695615");
        } catch (Exception e) {
            e.printStackTrace();
        }
        //输出
        outputMessage.getBody().write(result.getBytes());
    }


    public static void main(String[] args) {
        JSONObject node = new JSONObject();
        node.put("msg", "0");
        node.put("data", "0");
        node.put("code", "0");
        node.put("data",1);
        Object object = (Object) node;
        JSONObject jsonObject = JSON.parseObject(object.toString());
        jsonObject.put("data","12345");

        System.out.println(node);
        System.out.println(jsonObject);
    }
}
