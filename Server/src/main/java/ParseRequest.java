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
        // system.out headers
        reqIn.getHeaders().entrySet().forEach(entry->{
            System.out.println(entry.getKey() + " " + entry.getValue());
        });


        // Get json body
        // int för att få ut contentLenght som int.
        int contentLenght = Integer.parseInt((reqIn.getHeaders().get("Content-Length")).replace(" ", ""));  //65
        int contentLenghtFromJson = 0;

        char[] sizeByContentLenght = new char[contentLenght];
        in.read(sizeByContentLenght, 0 ,contentLenght);
        String jsonBody = new String(sizeByContentLenght);
        JsonObject jsonObject = new JsonParser().parse(jsonBody).getAsJsonObject();

        reqIn // In med obj

        return  reqIn;

        /*
        System.out.println(test);



        while (contentLenght != contentLenghtFromJson) {
                stop = in.readLine();
                jsonBody += stop;

                // Count chars for contentLenghtFromJason
                String str = stop;
                in.read

                char[] ch = new char[str.length()];
                for (int i = 0; i < str.length(); i++) {
                    ch[i] = str.charAt(i);
                }
                contentLenghtFromJson += (ch.length-  1);

                System.out.println(jsonBody);

        }


        JsonObject jsonObject = new JsonParser().parse(jsonBody).getAsJsonObject();
        System.out.println("Print json file:");
        System.out.println(jsonObject.toString());

        in.close();
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


    }

}
