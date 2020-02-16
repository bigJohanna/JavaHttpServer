package se.iths.sjap.server;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;

public class ParseRequest {

     public HTTPRequest parse(HTTPRequest httpRequest, BufferedReader in) throws IOException {
         System.out.println("Parsing ---------------------");
         // Get starline
         String[] splitHead = in.readLine().split(" ");
         httpRequest.setStartLineImplementation(splitHead[0].toUpperCase());
         httpRequest.setStartLineURL(splitHead[1]);
         httpRequest.setStartLineStatus(splitHead[2]);

         // Get headers
         String headerLine  = "avoid null";
         while (!headerLine.isEmpty()) {
             headerLine = in.readLine();
             String[] splitHeader = headerLine.split(":", 2);
             if(splitHeader.length > 1)
                 httpRequest.getHeaders().put(splitHeader[0], splitHeader[1]);
         }

         // Get json body
         if (httpRequest.StartLineImplementation.equals("POST") || httpRequest.StartLineImplementation.equals("PUT") ) {

             // If json file is comming from client, have no method for xml...
             if(httpRequest.getHeaders().get("Content-Type").replace(" ", "").equals("application/json")){
                 int contentLenght = Integer.parseInt((httpRequest.getHeaders().get("Content-Length")).replace(" ", ""));
                 int contentLenghtFromJson = 0;
                 char[] sizeByContentLenght = new char[contentLenght];
                 in.read(sizeByContentLenght, 0 ,contentLenght);
                 String jsonBody = new String(sizeByContentLenght);
                 JsonObject jsonObject = new JsonParser().parse(jsonBody).getAsJsonObject();
                 httpRequest.setJsonObject(jsonObject);
             }
         }
         return  httpRequest;
    }
}
