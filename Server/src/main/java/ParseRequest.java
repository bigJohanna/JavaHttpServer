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

    public HTTPRequest parseStartLineAndHeadToJavaObject() throws IOException {

        String[] splitHead = buffReaderIn.readLine().split(" ");
        requestIn.setStartLineImplementation(splitHead[0].toUpperCase());
        requestIn.setStartLineURL(splitHead[1]);
        requestIn.setStartLineStatus(splitHead[2]);

        String headerLine  = "test";
        while (!headerLine.isEmpty()) {
            headerLine = buffReaderIn.readLine();
             String[] splitHeader = headerLine.split(":", 2);
            if(splitHeader.length > 1)
            requestIn.getHeaders().put(splitHeader[0], splitHeader[1]);
        }

        return requestIn;
    }

    public JsonObject parseBodyToJson() throws IOException {

        int contentLenght = Integer.parseInt((requestIn.getHeaders().get("Content-Length")).replace(" ", ""));

        char[] sizeByContentLenght = new char[contentLenght];
        buffReaderIn.read(sizeByContentLenght, 0 ,contentLenght);
        String jsonBody = new String(sizeByContentLenght);
        JsonObject jsonObject = new JsonParser().parse(jsonBody).getAsJsonObject();
        System.out.println(jsonBody);
        return jsonObject;
    }

}
