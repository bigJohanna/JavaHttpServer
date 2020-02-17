package Response;

import java.io.File;

public class FileHandler {

    public static final File WEB_ROOT = new File("Server/src/main/resources");
    public static final String DEFAULT_FILE = "index.html";
    public static final String FILE_NOT_FOUND = "404.html";
    public static final String METHOD_NOT_SUPPORTED = "not_supported.html";

    public static String getContentType(String fileRequested) {
        String[] suffix = fileRequested.split("\\.");
        String content = "";
        String type = suffix[suffix.length - 1];
        switch (suffix[suffix.length - 1]) {
            case "html":
                content = "text/";
                break;
            case "json":
            case "pdf":
                content = "application/";
                break;
            case "png":
            case "gif":
            case "jpg":
                content = "image/";
                break;
        }
        return content + type;
    }
}
