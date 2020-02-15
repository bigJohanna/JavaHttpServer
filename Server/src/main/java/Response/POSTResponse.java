package Response;

import se.iths.sjap.server.HTTPRequest;

import java.io.BufferedOutputStream;
import java.io.PrintWriter;

public class POSTResponse {
    private String fileRequested = "index.html";

    public void method(HTTPRequest httpRequest, PrintWriter out, BufferedOutputStream dataOut) {

        String file = httpRequest.getStartLineURL();
        if (httpRequest.getStartLineURL().length() > 1) {
            fileRequested = file;
            fileRequested.substring(1);
        }
    }
}
