package se.iths.sjap.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.*;

import static Response.FileHandler.*;

// Each Client Connection will be managed in a dedicated Thread
public class Server {

    // port to listen connection
    static final int PORT = 8080;
    // verbose mode
    static final boolean verbose = true;
    // Client Connection via Socket Class


    public static void main(String[] args) {

        //create class that implements runnable
        ExecutorService executor = Executors.newScheduledThreadPool(2);
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("Server started.\nListening for connections on port : " + PORT + " ...\n");

        } catch (Exception err) {
            System.err.println("Server Connection error : " + err.getMessage());
        }

        try {
            while(verbose) {
                executor.submit(new ServerConnector(serverSocket.accept()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
