package org.yhguodu.iot.datacenter.loadbalancer;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by yhguodu on 2017/10/13.
 */
public class HashFunction {
    public static long hash(String key) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("md5");
            md5.reset();
            md5.update(key.getBytes());
            byte[] bKey = md5.digest();
            //具体的哈希函数实现细节--每个字节 & 0xFF 再移位
            long result = ((long) (bKey[3] & 0xFF) << 24)
                    | ((long) (bKey[2] & 0xFF) << 16
                    | ((long) (bKey[1] & 0xFF) << 8) | (long) (bKey[0] & 0xFF));
            return result & 0xffffffffL;
        }
        catch(NoSuchAlgorithmException e) {
            throw new RuntimeException("get Md5 algorithmException");
        }
    }
}
