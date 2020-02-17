package Response;

import se.iths.sjap.server.HTTPRequest;

import java.io.*;

import java.util.Date;

import static Response.FileHandler.WEB_ROOT;

public class POSTResponse {

    public void method(HTTPRequest httpRequest, PrintWriter out, BufferedOutputStream dataOut) throws IOException {

        String fileRequested = "jsonTest.json";

        if(httpRequest.getHeaders().get("Content-Type").replace(" ", "").equals("application/json")){

            FileWriter jsonFileFromInsomnia = new FileWriter(WEB_ROOT + "/jsonTest.json");
            try {
                jsonFileFromInsomnia.write(httpRequest.getJsonObject().toString());
                jsonFileFromInsomnia.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {

            File file2 = new File(FileHandler.WEB_ROOT, fileRequested);
            int fileLength = (int) file2.length();
            String content = FileHandler.getContentType(fileRequested);
            byte[] fileData = new ReadFileData().read(file2, fileLength);

            out.print("HTTP/1.1 200 OK \r\n");
            out.print("Server: Java HTTP Server from SJAP : 1.0 \r\n");
            out.print("Date: " + new Date() + "\r\n");
            out.print("Content-type: " + content + "\r\n");
            out.print("Content-length: " + fileLength + "\r\n");
            out.print("\r\n");
            out.flush();

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
}
