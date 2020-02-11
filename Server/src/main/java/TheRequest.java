import java.util.HashMap;

public class TheRequest {

        String startLine;
    HashMap<String, String> headers = new HashMap<String, String>();
    String body;

    public void TheRequest() {
    }

    public void setHeaders(String key, String value) {
        this.headers.put(key, value);
    }

    public String getStartLine() {
        return startLine;
    }

    public void setStartLine(String startLine) {
        this.startLine = startLine;
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








    // OLD CODE
    
    private String method;
    private String file;
    private String host;
    private String userAgent;
    private String connection;
    private String accept;

    public TheRequest() {
    }

    public TheRequest(String method, String file, String host, String userAgent, String connection, String accept) {
        this.method = method;
        this.file = file;
        this.host = host;
        this.userAgent = userAgent;
        this.connection = connection;
        this.accept = accept;
    }

    public String getConnection() {
        return connection;
    }

    public void setConnection(String connection) {
        this.connection = connection;
    }

    public String getAccept() {
        return accept;
    }

    public void setAccept(String accept) {
        this.accept = accept;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }
}
