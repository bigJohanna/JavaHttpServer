package Response;

import se.iths.sjap.server.HTTPRequest;

import java.io.*;
import java.util.Date;

public class GETResponse {

//    private String fileRequested;

    public void method(HTTPRequest httpRequest, PrintWriter out, BufferedOutputStream dataOut, String fileRequested) throws IOException {

     String file = httpRequest.getStartLineImplementation() + ".html";
        if (httpRequest.getStartLineURL().endsWith("/")) {
            fileRequested = file;
        }
        if (httpRequest.getStartLineURL().endsWith("/")) {
        fileRequested += FileHandler.DEFAULT_FILE;
        }


    File file2 = new File(FileHandler.WEB_ROOT, fileRequested);
    int fileLength = (int) file.length();
    String content = getContentType(fileRequested);

        if (httpRequest.getStartLineImplementation().equals("GET")) { // GET method so we return content
        byte[] fileData = readFileData(file2, fileLength);

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

    private String getContentType(String fileRequested) {
        if (fileRequested.endsWith(".htm")  ||  fileRequested.endsWith(".html"))
            return "text/html";
        else
            return "text/plain";
    }

    private byte[] readFileData(File file, int fileLength) throws IOException {
        FileInputStream fileIn = null;
        byte[] fileData = new byte[fileLength];

        try {
            fileIn = new FileInputStream(file);
            fileIn.read(fileData);
        } finally {
            if (fileIn != null)
                fileIn.close();
        }

        return fileData;
    }


}
