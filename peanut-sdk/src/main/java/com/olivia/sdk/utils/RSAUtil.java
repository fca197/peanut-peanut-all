package com.olivia.sdk.utils;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

/***
 *
 */
@Slf4j
@Setter
@Getter
@Accessors(chain = true)
public class RSAUtil {

  /**
   * 类型
   */
  public static final String ENCRYPT_TYPE = "RSA";

  /**
   * 获取公钥的key
   */
  private static final String PUBLIC_KEY = "RSAPublicKey";

  /**
   * 获取私钥的key
   */
  private static final String PRIVATE_KEY = "RSAPrivateKey";


  /**
   * 公钥加密
   *
   * @param content   要加密的内容
   * @param publicKey 公钥
   */
  public static String encryptByPublicKey(String content, String publicKey) {
    try {
      RSA rsa = new RSA(null, publicKey);
      return rsa.encryptBase64(content, KeyType.PublicKey);
    } catch (Exception e) {
      log.error("RSA加密失败 {}", content, e);
    }
    return null;
  }

  /****
   * 私钥加密
   * @param content 待加密字段
   * @param privateKey 私钥
   * @return String
   */
  public static String encryptByPrivateKey(String content, String privateKey) {
    try {
      RSA rsa = new RSA(privateKey, null);
      return rsa.encryptBase64(content, KeyType.PrivateKey);
    } catch (Exception e) {
      log.error("RSA加密失败 {}", content, e);
    }
    return null;
  }


  /**
   * 私钥解密
   *
   * @param content    要解密的内容
   * @param privateKey 私钥
   */
  public static String decryptByPrivateKey(String content, String privateKey) {
    try {
      RSA rsa = new RSA(privateKey, null);
      return rsa.decryptStr(content, KeyType.PrivateKey);
    } catch (Exception e) {
      log.error("RSA解密失败 {}", content, e);
    }
    return null;
  }

  /****
   * 公钥解密
   * @param content
   * @param publicKey
   * @return
   */
  public static String decryptByPublicKey(String content, String publicKey, boolean logException) {
    try {
      RSA rsa = new RSA(null, publicKey);
      return rsa.decryptStr(content, KeyType.PublicKey);
    } catch (Exception e) {
      if (logException) {
        log.error("RSA解密失败 {}", content, e);
      }
    }
    return null;
  }

  /**
   * 获取公私钥-请获取一次后保存公私钥使用
   *
   * @return Map<String, String>
   */
  public static Map<String, String> generateKeyPair() {
    try {
      String seepStr = RandomUtil.randomString(43);

      KeyPair pair = SecureUtil.generateKeyPair(ENCRYPT_TYPE, 2048 * 2, seepStr.getBytes());
      PrivateKey privateKey = pair.getPrivate();
      PublicKey publicKey = pair.getPublic();

      // 获取 公钥和私钥 的 编码格式（通过该 编码格式 可以反过来 生成公钥和私钥对象）
      byte[] pubEncBytes = publicKey.getEncoded();
      byte[] priEncBytes = privateKey.getEncoded();

      // 把 公钥和私钥 的 编码格式 转换为 Base64文本 方便保存
      String pubEncBase64 = Base64.getEncoder().encodeToString(pubEncBytes);
      String priEncBase64 = Base64.getEncoder().encodeToString(priEncBytes);

      Map<String, String> map = new HashMap<>(2);
      map.put(PUBLIC_KEY, pubEncBase64);
      map.put(PRIVATE_KEY, priEncBase64);

      return map;
    } catch (Exception e) {
      log.error("生成公私钥失败 {} ,e", e.getMessage(), e);
    }
    return null;
  }

}
