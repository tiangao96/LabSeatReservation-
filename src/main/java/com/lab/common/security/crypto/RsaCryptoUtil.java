package com.lab.common.security.crypto;

import com.lab.common.api.ResultCode;
import com.lab.common.exception.BizException;
import com.lab.common.security.crypto.RsaCryptoProperties;
import jakarta.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import javax.crypto.Cipher;
import org.springframework.stereotype.Component;

/*
 * Exception performing whole class analysis ignored.
 */
@Component
public class RsaCryptoUtil {
    private static final String TRANSFORMATION = "RSA/ECB/PKCS1Padding";
    private final RsaCryptoProperties props;
    private PrivateKey privateKey;
    private PublicKey publicKey;

    @PostConstruct
    void init() {
        if (!this.props.isEnabled()) {
            return;
        }
        if (this.props.getPrivateKey() == null || this.props.getPrivateKey().isBlank()) {
            throw new IllegalStateException("app.crypto.rsa.enabled=true \u4f46 private-key \u672a\u914d\u7f6e\uff1b\u5efa\u8bae\u901a\u8fc7\u73af\u5883\u53d8\u91cf RSA_PRIVATE_KEY \u6ce8\u5165");
        }
        try {
            byte[] privDer = Base64.getDecoder().decode(RsaCryptoUtil.stripPem((String)this.props.getPrivateKey()));
            this.privateKey = KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(privDer));
            if (this.props.getPublicKey() != null && !this.props.getPublicKey().isBlank()) {
                byte[] pubDer = Base64.getDecoder().decode(RsaCryptoUtil.stripPem((String)this.props.getPublicKey()));
                this.publicKey = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(pubDer));
            }
        }
        catch (IllegalArgumentException e) {
            throw new IllegalStateException("RSA \u5bc6\u94a5 base64 \u89e3\u7801\u5931\u8d25: " + e.getMessage(), e);
        }
        catch (Exception e) {
            throw new IllegalStateException("RSA \u5bc6\u94a5\u52a0\u8f7d\u5931\u8d25: " + e.getMessage(), e);
        }
    }

    public String decrypt(String base64Cipher) {
        if (this.privateKey == null) {
            throw new BizException(ResultCode.BAD_REQUEST, "\u767b\u5f55\u5bc6\u7801\u52a0\u5bc6\u672a\u542f\u7528");
        }
        try {
            Cipher c = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            c.init(2, this.privateKey);
            byte[] plain = c.doFinal(Base64.getDecoder().decode(base64Cipher));
            return new String(plain, StandardCharsets.UTF_8);
        }
        catch (Exception e) {
            throw new BizException(ResultCode.BAD_REQUEST, "\u5bc6\u7801\u5bc6\u6587\u65e0\u6548\u6216\u5df2\u635f\u574f");
        }
    }

    public String encrypt(String plain) {
        if (this.publicKey == null) {
            throw new IllegalStateException("public-key \u672a\u914d\u7f6e\uff0c\u65e0\u6cd5\u52a0\u5bc6");
        }
        try {
            Cipher c = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            c.init(1, this.publicKey);
            byte[] cipher = c.doFinal(plain.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(cipher);
        }
        catch (Exception e) {
            throw new IllegalStateException("RSA \u52a0\u5bc6\u5931\u8d25: " + e.getMessage(), e);
        }
    }

    private static String stripPem(String key) {
        return key.replace("-----BEGIN PRIVATE KEY-----", "").replace("-----END PRIVATE KEY-----", "").replace("-----BEGIN RSA PRIVATE KEY-----", "").replace("-----END RSA PRIVATE KEY-----", "").replace("-----BEGIN PUBLIC KEY-----", "").replace("-----END PUBLIC KEY-----", "").replaceAll("\\s", "");
    }

    public RsaCryptoUtil(RsaCryptoProperties props) {
        this.props = props;
    }
}

