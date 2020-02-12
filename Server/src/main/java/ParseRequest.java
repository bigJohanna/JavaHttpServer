import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.IOException;


public class ParseRequest {

    HTTPRequest requestIn;
    BufferedReader buffReaderIn;

    public ParseRequest(HTTPRequest requestIn, BufferedReader buffReaderIn) {
        this.requestIn = requestIn;
        this.buffReaderIn = buffReaderIn;
    }

    public HTTPRequest parseStartLineAndHeadToJavaObject(HTTPRequest reqIn, BufferedReader in) throws IOException {

        String[] splitHead = in.readLine().split(" ");
        reqIn.setStartLineImplementation(splitHead[0].toUpperCase());
        reqIn.setStartLineURL(splitHead[1]);
        reqIn.setStartLineStatus(splitHead[2]);

        String headerLine  = "test";
        while (!headerLine.isEmpty()) {
            headerLine = in.readLine();
             String[] splitHeader = headerLine.split(":", 2);
            if(splitHeader.length > 1)
            reqIn.getHeaders().put(splitHeader[0], splitHeader[1]);
        }

        return reqIn;
    }

    public JsonObject parseBodyToJson(HTTPRequest requestIn, BufferedReader buffReaderIn) throws IOException {

        int contentLenght = Integer.parseInt((requestIn.getHeaders().get("Content-Length")).replace(" ", ""));

        char[] sizeByContentLenght = new char[contentLenght];
        buffReaderIn.read(sizeByContentLenght, 0 ,contentLenght);
        String jsonBody = new String(sizeByContentLenght);
        JsonObject jsonObject = new JsonParser().parse(jsonBody).getAsJsonObject();

        return jsonObject;
    }

}
