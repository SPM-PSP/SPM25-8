package cn.kmbeast.utils;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class PathUtils {
    public static String getClassLoadRootPath() {
        String userDefinedPath = System.getProperty("custom.upload.dir");
        if (userDefinedPath != null && new File(userDefinedPath).exists()) {
            return userDefinedPath;
        }

        try {
            // 尝试获取 classpath 路径（只用于开发）
            String path = PathUtils.class.getProtectionDomain()
                    .getCodeSource().getLocation().getPath();
            return URLDecoder.decode(path, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return System.getProperty("user.dir");
        }
    }
}