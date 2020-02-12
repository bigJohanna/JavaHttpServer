import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.IOException;


public class ParseRequest {

    HTTPRequest requestIn;
    BufferedReader buffReaderIn;

    public ParseRequest(HTTPRequest requestIn, BufferedReader buffReaderIn) {
        this.requestIn = requestIn;
        this.buffReaderIn = buffReaderIn;
    }

    public HTTPRequest parseStartLineAndHeadToJavaObject(HTTPRequest reqIn, BufferedReader in) throws IOException {

        String[] splitHead = in.readLine().split(" ");
        reqIn.setStartLineImplementation(splitHead[0].toUpperCase());
        reqIn.setStartLineURL(splitHead[1]);
        reqIn.setStartLineStatus(splitHead[2]);

        String headerLine  = "test";
        while (!headerLine.isEmpty()) {
            headerLine = in.readLine();
             String[] splitHeader = headerLine.split(":", 2);
            if(splitHeader.length > 1)
            reqIn.getHeaders().put(splitHeader[0], splitHeader[1]);
        }

        return reqIn;
    }

    public JsonObject parseBodyToJson(HTTPRequest requestIn, BufferedReader buffReaderIn) throws IOException {

        int contentLenght = Integer.parseInt((requestIn.getHeaders().get("Content-Length")).replace(" ", ""));

        char[] sizeByContentLenght = new char[contentLenght];
        buffReaderIn.read(sizeByContentLenght, 0 ,contentLenght);
        String jsonBody = new String(sizeByContentLenght);
        JsonObject jsonObject = new JsonParser().parse(jsonBody).getAsJsonObject();

<<<<<<< HEAD
      //  reqIn // In med obj

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


=======
        return jsonObject;
>>>>>>> b0cef8de5b95261eac20d2a91ad121d0af263b42
    }

}
