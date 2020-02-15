package se.iths.sjap.server;

import java.io.*;
import java.net.Socket;

public class ServerConnector implements Runnable {

    private Socket connect;
    public ServerConnector(Socket c) {
        this.connect = c;
    }

    @Override
    public void run() {

        System.out.println("Current thread running: " + Thread.currentThread().getName());

        //work that we want the threads to do

        // we manage our particular client connection
        BufferedReader in = null;
        PrintWriter out = null;
        BufferedOutputStream dataOut = null;
        String fileRequested = null;

        try {
            System.out.println("Reading and parsing data..");
            // we read characters from the client via input stream on the socket
            in = new BufferedReader(new InputStreamReader(connect.getInputStream()));
            // we get character output stream to client (for headers)
            out = new PrintWriter(connect.getOutputStream());
            // get binary output stream to client (for requested data)
            dataOut = new BufferedOutputStream(connect.getOutputStream());

            //parse the request to a javaObject
            HTTPRequest httpRequest = new HTTPRequest();
            httpRequest = new ParseRequest().parse(httpRequest, in);
            fileRequested = httpRequest.StartLineURL;

            new RequestSwitch().Request(httpRequest, out, dataOut);

        } catch (IOException ioe) {
            System.err.println("Error : " + ioe);
        } finally {
            try {
                in.close();
                out.close();
                dataOut.close();
                connect.close(); // we close socket connection
            } catch (Exception e) {
                System.err.println("Error closing stream : " + e.getMessage());
            }
        }

    }
}
