package se.iths.sjap.server;


public class RequestSwitch {


    static final String DEFAULT_FILE = "index.html";
    static final String FILE_NOT_FOUND = "404.html";
    static final String METHOD_NOT_SUPPORTED = "not_supported.html";


    public void Request(HTTPRequest httpRequest) {

        switch (httpRequest.getStartLineImplementation()) {
            case "GET":
                System.out.println("GET yes");
                // GetResponse
                break;
            case "HEAD":
                System.out.println("HEAD yes");
                // HEADResponse
                break;
            case "POST":
                // POSTResponce
                break;
            default:
                System.out.println(" finish ");
        }
    }
}
