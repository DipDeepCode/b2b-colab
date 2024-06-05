package ru.ddc.b2bcolab.upload.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtils {

    private FileUtils() {
    }

    public static Path calculatePath(Path root) throws IOException {
        String currentTimeMd5Hex = DigestUtils.md5Hex(Long.toString(System.currentTimeMillis()));
        for (int i = 0; i < 3; i++) {
            int beginIndex = (int) (Math.random() * 30);
            root = root.resolve(currentTimeMd5Hex.substring(beginIndex, beginIndex + 2));
        }
        return root;
    }

    public static Path calculateFilename(Path root, MultipartFile file) {
        try {
            String originalFilename = file.getOriginalFilename();
            assert originalFilename != null;
            String md5Hex = DigestUtils.md5Hex(file.getBytes()).toUpperCase();
            String extension = originalFilename.substring(originalFilename.lastIndexOf('.'));
            return root.resolve(Paths.get(md5Hex + extension));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
