package com.lab.common.storage;

import com.lab.common.api.ResultCode;
import com.lab.common.exception.BizException;
import com.lab.common.storage.FileStorageService;
import com.lab.common.storage.StorageProperties;
import jakarta.annotation.PostConstruct;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;
import javax.imageio.ImageIO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileStorageService {
    private static final Logger log = LoggerFactory.getLogger(FileStorageService.class);
    public static final String AVATAR_SUBDIR = "avatars";
    private final StorageProperties props;

    @PostConstruct
    void init() {
        File dir = new File(this.props.getUploadDirAbsolute(), AVATAR_SUBDIR);
        if (!dir.exists() && !dir.mkdirs()) {
            log.warn("avatar upload dir not created (may already exist): {}", (Object)dir.getAbsolutePath());
        }
    }

    public StoredAvatar storeAvatar(MultipartFile file) {
        this.validate(file);
        BufferedImage image = this.readImage(file);
        BufferedImage normalized = this.resizeToFit(image, this.props.getAvatarMaxDimension());
        String uuid = UUID.randomUUID().toString();
        String fileName = uuid + ".png";
        File target = new File(this.props.getUploadDirAbsolute(), AVATAR_SUBDIR + File.separator + fileName);
        try {
            if (!ImageIO.write((RenderedImage)normalized, "png", target)) {
                throw new BizException(ResultCode.SERVER_ERROR, "\u5934\u50cf\u5199\u5165\u5931\u8d25\uff1a\u65e0\u53ef\u7528 PNG \u5199\u5165\u5668");
            }
        }
        catch (IOException e) {
            throw new BizException(ResultCode.SERVER_ERROR, "\u5934\u50cf\u5199\u5165\u5931\u8d25\uff1a" + e.getMessage());
        }
        return new StoredAvatar(uuid, this.avatarUrl(uuid));
    }

    public void deleteAvatar(String uuid) {
        if (uuid == null || uuid.isBlank()) {
            return;
        }
        File f = new File(this.props.getUploadDirAbsolute(), AVATAR_SUBDIR + File.separator + uuid + ".png");
        try {
            Files.deleteIfExists(f.toPath());
        }
        catch (IOException e) {
            log.warn("delete avatar file failed: {} err={}", (Object)f.getAbsolutePath(), (Object)e.toString());
        }
    }

    public String avatarUrl(String uuid) {
        if (uuid == null || uuid.isBlank()) {
            return null;
        }
        return this.props.getUrlPrefix() + "/avatars/" + uuid + ".png";
    }

    private void validate(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new BizException(ResultCode.BAD_REQUEST, "\u5934\u50cf\u6587\u4ef6\u4e3a\u7a7a");
        }
        if (file.getSize() > this.props.getAvatarMaxBytes()) {
            throw new BizException(ResultCode.BAD_REQUEST, "\u6587\u4ef6\u8fc7\u5927\uff0c\u6700\u5927 " + this.props.getAvatarMaxBytes() / 1024L + "KB");
        }
        String ct = file.getContentType();
        if (ct == null || !this.props.getAvatarAllowedContentTypes().contains(ct)) {
            throw new BizException(ResultCode.BAD_REQUEST, "\u4ec5\u652f\u6301 JPG/PNG/GIF \u56fe\u7247");
        }
    }

    private BufferedImage readImage(MultipartFile file) {
        try (InputStream in = file.getInputStream()) {
            BufferedImage img = ImageIO.read(in);
            if (img == null) {
                throw new BizException(ResultCode.BAD_REQUEST, "\u65e0\u6548\u56fe\u7247\u6587\u4ef6");
            }
            return img;
        }
        catch (IOException e) {
            throw new BizException(ResultCode.BAD_REQUEST, "\u56fe\u7247\u8bfb\u53d6\u5931\u8d25\uff1a" + e.getMessage());
        }
    }

    private BufferedImage resizeToFit(BufferedImage src, int maxDim) {
        int w = src.getWidth();
        int h = src.getHeight();
        double scale = Math.min(1.0, Math.min((double)maxDim / (double)w, (double)maxDim / (double)h));
        int nw = Math.max(1, (int)Math.round((double)w * scale));
        int nh = Math.max(1, (int)Math.round((double)h * scale));
        if (nw == w && nh == h) {
            return src;
        }
        BufferedImage out = new BufferedImage(nw, nh, 2);
        Graphics2D g = out.createGraphics();
        try {
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.drawImage(src, 0, 0, nw, nh, null);
        }
        finally {
            g.dispose();
        }
        return out;
    }

    private Path resolveAvatarPath(String uuid) {
        return Path.of(this.props.getUploadDirAbsolute(), AVATAR_SUBDIR, uuid + ".png");
    }

    public FileStorageService(StorageProperties props) {
        this.props = props;
    }

    public record StoredAvatar(String uuid, String url) {
    }
}

