package org.jet.io.common.utils.common;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.asn1.gm.GMObjectIdentifiers;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class SM2Signature {

    public static KeyPair generateKeyPair() throws Exception {
        // 获取SM2椭圆曲线的参数
        final ECGenParameterSpec sm2Spec = new ECGenParameterSpec("sm2p256v1");
        // 获取一个椭圆曲线类型的密钥对生成器
        final KeyPairGenerator kpg = KeyPairGenerator.getInstance("EC", new BouncyCastleProvider());
        // 使用SM2参数初始化生成器
        kpg.initialize(sm2Spec);

        // 使用SM2的算法区域初始化密钥生成器
        kpg.initialize(sm2Spec, new SecureRandom());
        // 获取密钥对
        return kpg.generateKeyPair();
    }

    public static String sign(String content, PrivateKey privateKey) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        // 生成SM2sign with sm3 签名验签算法实例
        Signature signature = Signature.getInstance(GMObjectIdentifiers.sm2sign_with_sm3.toString(), new BouncyCastleProvider());

        // 签名需要使用私钥，使用私钥 初始化签名实例
        signature.initSign(privateKey);
        // 签名原文
        byte[] plainText = content.getBytes(StandardCharsets.UTF_8);
        // 写入签名原文到算法中
        signature.update(plainText);
        // 计算签名值
        byte[] signatureValue = signature.sign();

        return Hex.toHexString(signatureValue);
    }

    public static PrivateKey toPrivateKey(String privateKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        final BouncyCastleProvider bc = new BouncyCastleProvider();
        KeyFactory keyFact = KeyFactory.getInstance("EC", bc);
        byte[] bytes = Base64.decodeBase64(privateKey);
        return keyFact.generatePrivate(new PKCS8EncodedKeySpec(bytes));
    }

    public static PublicKey toPublicKey(String publicKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        final BouncyCastleProvider bc = new BouncyCastleProvider();
        KeyFactory keyFact = KeyFactory.getInstance("EC", bc);
        byte[] bytes = Base64.decodeBase64(publicKey);
        return keyFact.generatePublic(new X509EncodedKeySpec(bytes));
    }

    public static boolean doCheck(String content, String sign, PublicKey publicKey) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        final BouncyCastleProvider bc = new BouncyCastleProvider();
        Signature signature = Signature.getInstance("SM3withSm2", bc);
        signature.initVerify(publicKey);
        signature.update(content.getBytes(StandardCharsets.UTF_8));
        return signature.verify(Hex.decode(sign));
    }
}
