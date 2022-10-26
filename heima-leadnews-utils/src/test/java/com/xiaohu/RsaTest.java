package com.xiaohu;

import com.xiaohu.utils.common.RsaUtils;
import org.junit.Test;

import java.security.PrivateKey;
import java.security.PublicKey;

public class RsaTest {

    public  static  final String publicKeyPath="D:\\work\\JAVA\\leadnews\\RsaKey\\ras_key_public.pub";
    public  static  final String privateKeyPath="D:\\work\\JAVA\\leadnews\\RsaKey\\ras_key_private";

    /**
     * 生成公钥和私钥
     */
    @Test
    public void testGetKey() throws Exception {
        /**
         * 参数一：公钥的文件路径
         * 参数二：私钥的文件路径
         * 参数三：加密密文
         * 参数四：文件大小（字节）
         */
        RsaUtils.generateKey(publicKeyPath,privateKeyPath,"xiaohu",2048);

    }

    /**
     * 读取公钥
     * @throws Exception
     */
    @Test
    public void GetPubKey() throws Exception {
        PublicKey pubKey   =  RsaUtils.getPublicKey(publicKeyPath);
        System.out.println("pubKey = " + pubKey);
    }

    /**
     * 读取私钥
     * @throws Exception
     */
    @Test
    public void GetPriKey() throws Exception {
        PrivateKey priKey  =  RsaUtils.getPrivateKey(privateKeyPath);
        System.out.println("priKey = " + priKey);
    }
}
