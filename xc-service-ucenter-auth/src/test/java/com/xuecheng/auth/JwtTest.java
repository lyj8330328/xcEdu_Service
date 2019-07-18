package com.xuecheng.auth;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.RsaSigner;
import org.springframework.security.jwt.crypto.sign.RsaVerifier;
import org.springframework.security.rsa.crypto.KeyStoreKeyFactory;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: 98050
 * @Time: 2019-07-16 21:50
 * @Feature:
 */
public class JwtTest {

    @Test
    public void testCreateJwt(){
        //证书文件
        String keyLocation = "xc.keystore";
        //密钥库密码
        String keyStorePassword = "xuechengkeystore";
        //访问证书路径
        ClassPathResource resource = new ClassPathResource(keyLocation);
        //密钥工厂
        KeyStoreKeyFactory keyFactory = new KeyStoreKeyFactory(resource, keyStorePassword.toCharArray());
        //密钥的密码，此密码和别名要匹配
        String keyPassword = "xuecheng";
        //密码别名
        String alias = "xckey";
        //密钥对（密钥和公钥）
        KeyPair keyPair = keyFactory.getKeyPair(alias, keyPassword.toCharArray());

        //私钥
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

        //定义payload信息
        Map<String,Object> objectMap = new HashMap<>();
        objectMap.put("id", "123");
        objectMap.put("name", "mrt");
        objectMap.put("roles", "r01,r02");
        objectMap.put("ext", "1");

        //生成jwt令牌
        Jwt jwt = JwtHelper.encode(JSON.toJSONString(objectMap),new RsaSigner(privateKey));
        //取出jwt令牌
        String token = jwt.getEncoded();
        System.out.println("-------------------------------------");
        System.out.println("token=" + token);
        testVerify(token);
    }

    public void testVerify(String token){
        String publickey = "-----BEGIN PUBLIC KEY-----MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAvZpafGqvhr7Nq4hY+SV4 PYNesKN1PqSb0B3+j9/UOSF4c5iphs6sB5flZa5ZGcUD7nd5LApuFLsKcbNRKXVv U0LxBOfUPSzSfzugjygvt4PKOgfhkqLFQbTF+ryo3UPJOhso4s04ZVm5ctLgvFQw AqYiRppID/0BROp4e5job3evPUgYyeG2oRCCcopi14yds6lUeiFPedyeBQgB6JWO Eiou6zttlaDeEMpPcyuBMtAf0WwXhp02VwTFdq8KQb5XAjHbjaWgywJy5FPMN0p2 H3p0z/V/1DTr8QTRU4KV6ZdhPjPtt0CjgOhx8dhYgS0PLC40rIMCmk141LKLLxOK jwIDAQAB-----END PUBLIC KEY-----";

        //检验jwt
        Jwt jwt = JwtHelper.decodeAndVerify(token, new RsaVerifier(publickey));
        //获取jwt原始内容
        String claims = jwt.getClaims();
        System.out.println(claims);
    }

}
