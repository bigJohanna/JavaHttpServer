package Response;

import java.io.*;
import java.util.Date;

import static Response.FileHandler.FILE_NOT_FOUND;
import static Response.FileHandler.WEB_ROOT;

public class NotFound {

    public void fileNotFound(PrintWriter out, BufferedOutputStream dataOut, String fileRequested) throws IOException {
        File file = new File(WEB_ROOT, FILE_NOT_FOUND);
        int fileLength = (int) file.length();
        String content = "text/html";
        byte[] fileData = new ReadFileData().read(file, fileLength);

        HttpHeadersDataToClient(out, dataOut, fileLength, content, fileData);

        System.out.println("File " + fileRequested + " not found");

    }

    private void HttpHeadersDataToClient(PrintWriter out, BufferedOutputStream dataOut, int fileLength, String content, byte[] fileData) throws IOException {
        out.print("HTTP/1.1 404 File Not Found\r\n");
        out.print("Server: Java HTTP Server from SJAP : 1.0\r\n");
        out.print("Date: " + new Date() + "\r\n");
        out.print("Content-type: " + content + "\r\n");
        out.print("Content-length: " + fileLength + "\r\n");
        out.print("\r\n");
        out.flush();

        dataOut.write(fileData, 0, fileLength);
        dataOut.flush();
    }


}
