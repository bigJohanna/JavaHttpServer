package Response;

//import org.json.simple.JSONObject;
import se.iths.sjap.server.HTTPRequest;

import java.io.*;

import java.net.http.HttpRequest;
import java.util.Date;

import static Response.FileHandler.FILE_NOT_FOUND;
import static Response.FileHandler.WEB_ROOT;

public class POSTResponse {
    private String fileRequested = "johanna.json";

    public void method(HTTPRequest httpRequest, PrintWriter out, BufferedOutputStream dataOut) throws IOException {

        FileWriter jsonFileFromInsomnia = new FileWriter(WEB_ROOT + "/johanna.json");

        try {
            jsonFileFromInsomnia.write(httpRequest.getJsonObject().toString());
            jsonFileFromInsomnia.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

        //TODO här behövs en rad för att spara över  johanna.json i resorce med den som vi skapat på rad 20


        try {

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


            //TODO Här kan man skriva ut Json filen som text???

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
        if (fileRequested.endsWith(".json"))
            return "text/json";
        else
            return "text/plain";
    }
}
