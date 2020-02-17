package Response;

import se.iths.sjap.server.HTTPRequest;

import java.io.*;
import java.util.Date;

import static Response.FileHandler.*;

public class HEADResponse {

    private String fileRequested = DEFAULT_FILE;

    public void method(HTTPRequest httpRequest, PrintWriter out, BufferedOutputStream dataOut) throws IOException {

            String file = httpRequest.getStartLineURL() + ".html";
            if (httpRequest.getStartLineURL().length() > 1) {
                fileRequested = file.substring(1);
            }

            File file2 = new File(FileHandler.WEB_ROOT, fileRequested);
            int fileLength = (int) file2.length();
            String content = getContentType(fileRequested);

            //TODO denna arrayen används aldrig här? är det för att vi inte skickar nån body vid HEAD-request?
            byte[] fileData = new ReadFileData().read(file2, fileLength);

            // send HTTP Headers
            out.print("HTTP/1.1 200 OK \r\n");
            out.print("Server: Java HTTP Server from SJAP : 1.0 \r\n");
            out.print("Date: " + new Date() + "\r\n");
            out.print("Content-type: " + content + "\r\n");
            out.print("Content-length: " + fileLength + "\r\n");
            out.print("\r\n"); // blank line between headers and content, very important !
            out.flush(); // flush character output stream buffer
    }

    private String getContentType(String fileRequested) {
        String[] suffix = fileRequested.split("\\.");
        return "text/." + suffix[suffix.length-1];
    }
}
