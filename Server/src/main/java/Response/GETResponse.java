package Response;
import se.iths.sjap.server.HTTPRequest;
import java.io.*;
import java.util.Date;

import static Response.FileHandler.*;

public class GETResponse {

    private String fileRequested = DEFAULT_FILE;

        public void method(HTTPRequest httpRequest, PrintWriter out, BufferedOutputStream dataOut) throws IOException {

            try {
                    String file = httpRequest.getStartLineURL() + ".html";

                    if (httpRequest.getStartLineURL().length() > 1) {
                        fileRequested = file;
                        fileRequested.substring(1);
                    }

                    File file2 = new File(FileHandler.WEB_ROOT, fileRequested);
                    int fileLength = (int) file2.length();
                    String content = getContentType(fileRequested);
                    byte[] fileData = new ReadFileData().read(file2, fileLength);

                    // send HTTP Headers
                    out.print("HTTP/1.1 200 OK \r\n");
                    out.print("Server: Java HTTP Server from SJAP : 1.0 \r\n");
                    out.print("Date: " + new Date() + "\r\n");
                    out.print("Content-type: " + content + "\r\n");
                    out.print("Content-length: " + fileLength + "\r\n");
                    out.print("\r\n"); // blank line between headers and content, very important !
                    out.flush(); // flush character output stream buffer

                    dataOut.write(fileData, 0, fileLength);
                    dataOut.flush();

            }catch (FileNotFoundException fnfe){
                    try {
                        new NotFound().fileNotFound(out, dataOut, fileRequested);
                    } catch (IOException e) {
                        e.printStackTrace();
            }
        }
    }

    private String getContentType(String fileRequested) {
            //TODO ska det kunna vara fel ändelser på filerna här? typ .pdf/.json/.css?
        if (fileRequested.endsWith(".htm")  ||  fileRequested.endsWith(".html"))
            return "text/html";
        else
            return "text/plain";
    }

}
