package com.lighthouse.sdk.utils;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;

/**
 * @author null&&
 * @Date 2024/6/2 15:08
 */
public class SignUtils {
    /**
     * 生成签名
     *
     * @param body      请求参数
     * @param secretKey 秘钥
     * @return 签名
     */
    public static String getSign(String body, String secretKey) {
        Digester digester = new Digester(DigestAlgorithm.SHA256);
        String content = body + "." + secretKey;
        return digester.digestHex(content);
    }
}
