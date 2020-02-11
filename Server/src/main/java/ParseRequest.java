import java.io.BufferedReader;
import java.io.IOException;


public class ParseRequest {




    // Denna metod bör skrivas om för att parsa den nya typen av TheRequest

    public HTTPRequest parseRequestToJavaObject(HTTPRequest reqIn, BufferedReader in) throws IOException {
        /*
        Split startline
                Split headers
                        hitta empty startline
                            hitta body, gör nått.

        */

        //Line 1 of request ("GET /index.html HTTP/1.1")
        // Split by space
        String[] splitHead = in.readLine().split(" ");
        reqIn.setStartLineImplementation(splitHead[0].toUpperCase());
        reqIn.setStartLineURL(splitHead[1]);
        reqIn.setStartLineStatus(splitHead[2]);

        System.out.println(reqIn.StartLineURL);


        // Get headers
        String headerLine  = "test";

        while (!headerLine.isEmpty()) {
            headerLine = in.readLine();
             String[] splitHeader = headerLine.split(":", 2);
            if(splitHeader.length > 1)
            reqIn.getHeaders().put(splitHeader[0], splitHeader[1]);

        }

        reqIn.getHeaders().entrySet().forEach(entry->{
            System.out.println(entry.getKey() + " " + entry.getValue());
        });



/*
        {
        reqIn.setUserAgent(in.readLine());
        reqIn.setConnection(in.readLine());
        reqIn.setAccept(in.readLine());

        //  requestLineOne into method and fileRequested
        StringTokenizer parse = new StringTokenizer(lineOne);
        //get first word of line one of request
        reqIn.setMethod(parse.nextToken().toUpperCase());
        // get second part of line one of request
        reqIn.setFile(parse.nextToken().toLowerCase());


        //print info to console
        System.out.println("Method of TheRequestObject!!!!!!!!!!!!!!!!!!!!!!: " + reqIn.getMethod());
        System.out.println("File requested of TheRequestObject!!!!!!!!!!!!!!: " + reqIn.getFile());
        System.out.println("Host of TheRequestObject!!!!!!!!!!!!!!!!!!!!!!!!: " + reqIn.getHost());
        System.out.println("User-Agent of TheRequestObject!!!!!!!!!!!!!!!!!!: " + reqIn.getUserAgent());
        System.out.println("Connection of TheRequestObject!!!!!!!!!!!!!!!!!!: " + reqIn.getConnection());
        System.out.println("Accept of TheRequestObject!!!!!!!!!!!!!!!!!!!!!!: " + reqIn.getAccept());
        //System.out.println("Body of TheRequestObject!!!!!!!!!!!!!!!!!!!!!!!!: " + );

*/

        return  reqIn;
    }

}
