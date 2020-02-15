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

    private void HttpHeadersDataToClient(PrintWriter out, BufferedOutputStream dataOut, int fileLength, String content, byte[] fileData) throws IOException {
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

    // return supported MIME Types
    private String getContentType(String fileRequested) {
        if (fileRequested.endsWith(".htm") || fileRequested.endsWith(".html"))
            return "text/html";
        else
            return "text/plain";
    }

    private void fileNotFound(PrintWriter out, BufferedOutputStream dataOut, String fileRequested) throws IOException {
        File file = new File(WEB_ROOT, FILE_NOT_FOUND);
        int fileLength = (int) file.length();
        String content = "text/html";
        byte[] fileData = readFileData(file, fileLength);

        HttpHeadersDataToClient(out, dataOut, fileLength, content, fileData);

        System.out.println("File " + fileRequested + " not found");

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
