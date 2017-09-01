package http.utils;

/**
 * @author lifeng
 */
public class UDTSecretUtil {
    /**
     * 加密
     * 1.构造密钥生成器
     * 2.根据ecnodeRules规则初始化密钥生成器
     * 3.产生密钥
     * 4.创建和初始化密码器
     * 5.内容加密
     * 6.返回字符串
     */
    public static String AESEncode(String encodeKey, String content) throws Exception {
        //KeyGenerator keygen = KeyGenerator.getInstance("AES");
        //SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        //random.setSeed(encodeKey.getBytes());
        //keygen.init(128, random);
        //SecretKey original_key = keygen.generateKey();
        //byte[] raw = original_key.getEncoded();
        //SecretKey key = new SecretKeySpec(raw, "AES");
        //Cipher cipher = Cipher.getInstance("AES");
        //cipher.init(Cipher.ENCRYPT_MODE, key);
        //byte[] byte_encode = content.getBytes("utf-8");
        //byte[] byte_AES = cipher.doFinal(byte_encode);
        //String AES_encode = new String(new BASE64Encoder().encode(byte_AES));
        //return AES_encode;
        return content;
    }
    
    public static byte[] AESEncode(String encodeKey, byte[] content) throws Exception {
        //KeyGenerator keygen = KeyGenerator.getInstance("AES");
        //SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        //random.setSeed(encodeKey.getBytes());
        //keygen.init(128, random);
        //SecretKey original_key = keygen.generateKey();
        //byte[] raw = original_key.getEncoded();
        //SecretKey key = new SecretKeySpec(raw, "AES");
        //Cipher cipher = Cipher.getInstance("AES");
        //cipher.init(Cipher.ENCRYPT_MODE, key);
        //byte[] byte_AES = cipher.doFinal(content);
        //return byte_AES;
        return content;
    }

    /**
     * 解密
     * 解密过程：
     * 1.同加密1-4步
     * 2.将加密后的字符串反纺成byte[]数组
     * 3.将加密内容解密
     */
    public static String AESDecode(String encodeKey, String content) throws Exception {
        //KeyGenerator keygen = KeyGenerator.getInstance("AES");
        //SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        //random.setSeed(encodeKey.getBytes());
        //keygen.init(128, random);
        //SecretKey original_key = keygen.generateKey();
        //byte[] raw = original_key.getEncoded();
        //SecretKey key = new SecretKeySpec(raw, "AES");
        //Cipher cipher = Cipher.getInstance("AES");
        //cipher.init(Cipher.DECRYPT_MODE, key);
        //byte[] byte_content = new BASE64Decoder().decodeBuffer(content);
        //byte[] byte_decode = cipher.doFinal(byte_content);
        //String AES_decode = new String(byte_decode, "utf-8");
        //return AES_decode;
        return content;
    }

    public static byte[] AESDecode(String encodeKey, byte[] content) throws Exception {
        //KeyGenerator keygen = KeyGenerator.getInstance("AES");
        //SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        //random.setSeed(encodeKey.getBytes());
        //keygen.init(128, random);
        //SecretKey original_key = keygen.generateKey();
        //byte[] raw = original_key.getEncoded();
        //SecretKey key = new SecretKeySpec(raw, "AES");
        //Cipher cipher = Cipher.getInstance("AES");
        //cipher.init(Cipher.DECRYPT_MODE, key);
        //byte[] byte_decode = cipher.doFinal(content);
        //return byte_decode;
        return content;
    }
}
