package se.iths.sjap.server;
import com.google.gson.JsonObject;
import java.util.HashMap;

public class HTTPRequest {

    String StartLineImplementation;
    String StartLineURL;
    String StartLineStatus;
    HashMap<String, String> headers = new HashMap<String, String>();
    JsonObject jsonObject;

    public JsonObject getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(JsonObject jsonObject) {
       this.jsonObject = jsonObject;
    }

    public String getStartLineStatus() {
        return StartLineStatus;
    }

    public void setStartLineStatus(String startLineStatus) {
        StartLineStatus = startLineStatus;
    }

    public void setHeaders(String key, String value) {
        this.headers.put(key, value);
    }

   public HashMap<String, String> getHeaders() {
        return headers;
    }


    public String getStartLineImplementation() {
        return StartLineImplementation;
    }

    public void setStartLineImplementation(String startLineImplementation) {
        StartLineImplementation = startLineImplementation;
    }

    public String getStartLineURL() {
        return StartLineURL;
    }

    public void setStartLineURL(String startLineURL) {
        StartLineURL = startLineURL;
    }






}
