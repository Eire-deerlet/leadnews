package com.xiaohu;

import com.xiaohu.utils.common.JwtUtils;
import com.xiaohu.utils.common.Payload;
import com.xiaohu.utils.common.RsaUtils;
import org.junit.Test;

import java.security.PrivateKey;
import java.security.PublicKey;

public class JwtTest {
    public static final String publicKeyPath = "D:\\work\\JAVA\\RsaKey\\ras_key_public.pub";
    public static final String privateKeyPath = "D:\\work\\JAVA\\RsaKey\\ras_key_private";

    @Test
    public void testGenerateToken() throws Exception {


        /**
         * 参数一：登录的用户信息
         * 参数二：私钥对象
         * 参数三：过期时间
         */
        Integer userId = 1001;
        //读取私钥
        PrivateKey privateKey = RsaUtils.getPrivateKey(privateKeyPath);
        String token = JwtUtils.generateTokenExpireInMinutes(userId, privateKey, 3);
        System.out.println("token = " + token);

    }

    @Test
    public void verifyToken() throws Exception {
        /**
         * 参数一：校验的token
         * 参数二：公钥对象
         * 参数三：存入的登录信息的类型
         */
        String token = "eyJhbGciOiJSUzI1NiJ9.eyJ1c2VyIjoiMTAwMSIsImp0aSI6IlpERXlNall5TkRrdE5qSmtOaTAwTVdRekxUbGhZemt0WmpVMFpHUTJZVGhqTm1SaSIsImV4cCI6MTY2NTQ3ODE4OX0.DA0_egTzCxNobhttd30ZmwfqHSKv-sszNnVakz_kAGgjmXm-3vq3fb6iDaZDLRQKJX7rNYTSB-LaIByGr_hlP_WSNQSU_KVPmGFXyIigPvxTV9_w9QUvjyaKw4hssdWtMF6GDV_FZiZTFYop2DijSylwt0D255rbrLda7EiumymmSSkh5fQSb0_XTpVR_-RmfQfEqf0DU0XmvtY76K5dilGUlCAcis6FhfoO8nIzXP049EvU1torWXvHi-HGyVG6GTdHwm-s0V2kz8jxHBXQKu3ZfozWwuKS2eW6Oa7163A50OKr8YgyqiecMFYP6Bnt8jVcjE39yGDzmh4OX7uciA";
        PublicKey publicKey = RsaUtils.getPublicKey(publicKeyPath);
        Payload<Integer> payload = JwtUtils.getInfoFromToken(token, publicKey, Integer.class);
        Integer userId = payload.getInfo();
        System.out.println("userId = " + userId);

    }

}
