
public class TheRequest {

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
