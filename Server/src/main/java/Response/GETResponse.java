package Response;

import se.iths.sjap.server.HTTPRequest;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class GETResponse {

    public void method((HTTPRequest httpRequest, PrintWriter out, BufferedOutputStream dataOut) throws IOException {

    // GET
    if (httpRequest.getStartLineURL().endsWith("/")) {
    fileRequested += DEFAULT_FILE;
    }


    File file = new File(WEB_ROOT, fileRequested);
    int fileLength = (int) file.length();
    String content = getContentType(fileRequested);

        if (httpRequest.getStartLineImplementation().equals("GET")) { // GET method so we return content
        byte[] fileData = readFileData(file, fileLength);

        // send HTTP Headers
        out.print("HTTP/1.1 200 OK \r\n");
        out.print("Server: Java HTTP Server from SSaurel : 1.0 \r\n");
        out.print("Date: " + new Date() + "\r\n");
        out.print("Content-type: " + content + "\r\n");
        out.print("Content-length: " + fileLength + "\r\n");
        out.print("\r\n"); // blank line between headers and content, very important !
        out.flush(); // flush character output stream buffer

        dataOut.write(fileData, 0, fileLength);
        dataOut.flush();
    }


}
 */
}
