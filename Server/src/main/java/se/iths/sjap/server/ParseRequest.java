package se.iths.sjap.server;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.IOException;

public class ParseRequest {

     public HTTPRequest parse(HTTPRequest reqIn, BufferedReader in) throws IOException {

             // Get starline
         String[] splitHead = in.readLine().split(" ");
         reqIn.setStartLineImplementation(splitHead[0].toUpperCase());
         reqIn.setStartLineURL(splitHead[1]);
         reqIn.setStartLineStatus(splitHead[2]);

         // Get headers
         String headerLine  = "avoid null";
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
      
         if (reqIn.StartLineImplementation.equals("POST") || reqIn.StartLineImplementation.equals("PUT") ) {

             int contentLenght = Integer.parseInt((reqIn.getHeaders().get("Content-Length")).replace(" ", ""));  //65
             int contentLenghtFromJson = 0;
             String jsonBody = "";

             if (contentLenght > 0) {

                 char[] sizeByContentLenght = new char[contentLenght];
                 in.read(sizeByContentLenght, 0, contentLenght);
                 jsonBody = new String(sizeByContentLenght);
                 JsonObject jsonObject = new JsonParser().parse(jsonBody).getAsJsonObject();
             }
         }
         return  reqIn;
    }
}
