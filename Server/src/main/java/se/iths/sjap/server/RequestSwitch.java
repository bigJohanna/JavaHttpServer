package se.iths.sjap.server;

import Response.FileHandler;
import Response.GETResponse;

import java.io.*;
import java.util.Date;


public class RequestSwitch {

    public void Request(HTTPRequest httpRequest, PrintWriter out, BufferedOutputStream dataOut) throws IOException, FileNotFoundException {

        switch (httpRequest.getStartLineImplementation()) {
            case "GET":
                System.out.println("GET yes");
                new GETResponse().method(httpRequest,out,dataOut);
                break;
            case "HEAD":
                System.out.println("HEAD yes");
                // HEADResponse
                break;
            case "POST":
                // POSTResponce
                break;
            default:
                File file = new File(FileHandler.WEB_ROOT, FileHandler.METHOD_NOT_SUPPORTED);
                int fileLength = (int) file.length();
                String content = "text/html";
                //read content to return to client

                byte[] fileData = readFileData(file, fileLength);
                // we send HTTP Headers with data to client
                out.print("HTTP/1.1 501 Not Implemented\r\n");
                out.print("Server: Java HTTP Server from SSaurel : 1.0\r\n");
                out.print("Date: " + new Date() + "\r\n");
                out.print("Content-type: " + content + "\r\n");
                out.print("Content-length: " + fileLength + "\r\n");
                out.print("\r\n");
                out.flush();

                dataOut.write(fileData, 0, fileLength);
                dataOut.flush();
        }
    }

    private byte[] readFileData(File file, int fileLength) throws IOException {
        FileInputStream fileIn = null;
        byte[] fileData = new byte[fileLength];

        try {
            fileIn = new FileInputStream(file);
            fileIn.read(fileData);
        } finally {
            if (fileIn != null)
                fileIn.close();
        }

        return fileData;
    }
}
