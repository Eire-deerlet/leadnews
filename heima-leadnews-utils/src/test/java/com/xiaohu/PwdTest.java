package com.xiaohu;

import com.xiaohu.utils.common.BCrypt;
import org.junit.Test;

public class PwdTest {
    @Test
    public void testEncode(){
        String password ="123456";
        String gensalt = BCrypt.gensalt(); //生成隨機鹽
        String pwd = BCrypt.hashpw(password, gensalt);
        System.out.println("password = " + password);
        System.out.println("pwd = " + pwd);

    }

    @Test
    public void testMatch(){
        String password ="123456";
        boolean flag = BCrypt.checkpw(password, "$2a$10$a56NvbNYoUwN9TwpfnA25Om/KGZmAait9p6lsN421RUiBVnbYOATy");
        System.out.println("flag = " + flag);

    }
}
