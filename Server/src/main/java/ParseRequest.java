import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;

public class ParseRequest {


    public HTTPRequest parseRequestToJavaObject(HTTPRequest reqIn, BufferedReader in) throws IOException {

        // Get starline
        String[] splitHead = in.readLine().split(" ");
        reqIn.setStartLineImplementation(splitHead[0].toUpperCase());
        reqIn.setStartLineURL(splitHead[1]);
        reqIn.setStartLineStatus(splitHead[2]);

        // Get headers
        String headerLine  = "test";

        while (!headerLine.isEmpty()) {
            headerLine = in.readLine();
             String[] splitHeader = headerLine.split(":", 2);
            if(splitHeader.length > 1)
            reqIn.getHeaders().put(splitHeader[0], splitHeader[1]);
        }
        // See headers, sys out..
        reqIn.getHeaders().entrySet().forEach(entry->{
            System.out.println(entry.getKey() + " " + entry.getValue());
        });





        // int för att få ut contentLenght som int.
        int contentLenght = Integer.parseInt((reqIn.getHeaders().get("Content-Length")).replace(" ", ""));  //65
        int contentLenghtFromJson = 0;

        // strängar för lagring till parsning.
        String jsonBody  = "";
        String stop = "stop";


        while (contentLenght != contentLenghtFromJson) {
            stop = in.readLine();
            jsonBody = jsonBody + stop;
            String str = stop;
            // Creating array of string length
            char[] ch = new char[str.length()];
            // Copy character by character into array
            for (int i = 0; i < str.length(); i++) {
                ch[i] = str.charAt(i);
            }
            contentLenghtFromJson = contentLenghtFromJson + (ch.length-  1);
                System.out.println(jsonBody);

        }
        in.close();

        JsonObject jsonObject = new JsonParser().parse(jsonBody).getAsJsonObject();
        System.out.println("Print json file:");
        System.out.println(jsonObject.toString());


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
