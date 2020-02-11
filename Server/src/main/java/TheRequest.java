import java.util.HashMap;

public class TheRequest {
    
    
    String StartLineImplementation;
    String StartLineStatus;

    HashMap<String, String> headers = new HashMap<String, String>();
    String body;

    public void TheRequest() {
    }

    public void setHeaders(String key, String value) {
        this.headers.put(key, value);
    }

   public HashMap<String, String> getHeaders() {
        return headers;
    }

        public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }


    public String getStartLineImplementation() {
        return StartLineImplementation;
    }

    public void setStartLineImplementation(String startLineImplementation) {
        StartLineImplementation = startLineImplementation;
    }

    public String getStartLineStatus() {
        return StartLineStatus;
    }

    public void setStartLineStatus(String startLineStatus) {
        StartLineStatus = startLineStatus;
    }
}
