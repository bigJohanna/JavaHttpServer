package Response;

//import org.json.simple.JSONObject;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import se.iths.sjap.server.HTTPRequest;

import java.io.*;

import java.net.http.HttpRequest;
import java.util.Date;

import static Response.FileHandler.FILE_NOT_FOUND;
import static Response.FileHandler.WEB_ROOT;

public class POSTResponse {

    //läsa av parametrar
    //lägg in i ett jsonobject
    //skicka tillbaka till clienten


    public void method(HTTPRequest httpRequest, PrintWriter out, BufferedOutputStream dataOut) throws IOException {

        String fileRequested = "johanna.json";

        // If Json file
        if(httpRequest.getHeaders().get("Content-Type").replace(" ", "").equals("application/json")){

            FileWriter jsonFileFromInsomnia = new FileWriter(WEB_ROOT + "/johanna.json");
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
        String[] suffix = fileRequested.split("\\.");
        return "text/." + suffix[suffix.length-1];
    }
}
