package Response;

import se.iths.sjap.server.HTTPRequest;

import java.io.*;
import java.util.Date;

import static Response.FileHandler.*;

public class HEADResponse {

    private String fileRequested = DEFAULT_FILE;

    public void method(HTTPRequest httpRequest, PrintWriter out) throws IOException {

            String file = httpRequest.getStartLineURL();
            if (httpRequest.getStartLineURL().length() > 1) {
                fileRequested = file.substring(1);
            }

            File file2 = new File(FileHandler.WEB_ROOT, fileRequested);
            int fileLength = (int) file2.length();
            String content = FileHandler.getContentType(fileRequested);

            out.print("HTTP/1.1 200 OK \r\n");
            out.print("Server: Java HTTP Server from SJAP : 1.0 \r\n");
            out.print("Date: " + new Date() + "\r\n");
            out.print("Content-type: " + content + "\r\n");
            out.print("Content-length: " + fileLength + "\r\n");
            out.print("\r\n");
            out.flush();
    }
}
