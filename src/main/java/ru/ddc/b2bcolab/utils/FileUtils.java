package ru.ddc.b2bcolab.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Objects;

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
        return root.resolve(Paths.get(Objects.requireNonNull(file.getOriginalFilename())));
    }

    public static String encodePathToBase64(Path path) {
        return Base64.getUrlEncoder().encodeToString(path.toString().getBytes());
    }

    public static Path decodeBase64ToPath(String base64) {
        return Path.of(new String(Base64.getUrlDecoder().decode(base64)));
    }
}
