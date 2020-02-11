import java.io.BufferedReader;
import java.io.IOException;
import java.util.StringTokenizer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.StringTokenizer;



public class ParseRequest {




    // Denna metod bör skrivas om för att parsa den nya typen av TheRequest

    public TheRequest parseRequestToJavaObject(TheRequest reqIn, BufferedReader in) throws IOException {


        //Line 1 of request ("GET /index.html HTTP/1.1")
        // Split by space
        String[] splitHead = in.readLine().split(" ");
        reqIn.setStartLineImplementation(splitHead[0].toUpperCase());
        reqIn.setStartLineStatus(splitHead[2]);
        // För test
        //System.out.println(reqIn.getStartLineImplementation());
        //System.out.println(reqIn.getStartLineStatus());

        //set hostname and user-agent name from line 2 and 3 from request
        // reqIn.setStartLine(in.readLine());



        while (in.readLine().getClass() != null) {

        }

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
