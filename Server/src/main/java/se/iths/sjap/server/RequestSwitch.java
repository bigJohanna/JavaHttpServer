package se.iths.sjap.server;

import Response.*;

import java.io.*;
import java.util.Date;


public class RequestSwitch {

    public void Request(HTTPRequest httpRequest, PrintWriter out, BufferedOutputStream dataOut) throws IOException {

        switch (httpRequest.getStartLineImplementation()) {
            case "GET":
                System.out.println("GET yes -----------------------------------------------------------------------");


                new GETResponse().method(httpRequest,out,dataOut);
                break;
            case "HEAD":
                System.out.println("HEAD yes -----------------------------------------------------------------------");


                new HEADResponse().method(httpRequest,out,dataOut);
                // HEADResponse
                break;
            case "POST":
                System.out.println("POST yes  -----------------------------------------------------------------------");

                new POSTResponse().method(httpRequest,out,dataOut);
                break;
            default:
                File file = new File(FileHandler.WEB_ROOT, FileHandler.METHOD_NOT_SUPPORTED);
                int fileLength = (int) file.length();
                String content = "text/html";
                //read content to return to client

                byte[] fileData = new ReadFileData().read(file, fileLength);
                // we send HTTP Headers with data to client
                out.print("HTTP/1.1 501 Not Implemented\r\n");
                out.print("Server: Java HTTP Server from SJAP : 1.0\r\n");
                out.print("Date: " + new Date() + "\r\n");
                out.print("Content-type: " + content + "\r\n");
                out.print("Content-length: " + fileLength + "\r\n");
                out.print("\r\n");
                out.flush();

                dataOut.write(fileData, 0, fileLength);
                dataOut.flush();
        }
    }
}
