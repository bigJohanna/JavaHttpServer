package Response;

import se.iths.sjap.server.HTTPRequest;

import java.io.*;

import java.net.http.HttpRequest;
import java.util.Date;

public class POSTResponse {
    private String fileRequested = "johanna.json";

    public void method(HTTPRequest httpRequest, PrintWriter out, BufferedOutputStream dataOut) throws IOException {
        httpRequest.getJsonObject();
        try {

            // Constructs a FileWriter given a file name, using the platform's default charset
            file = new FileWriter(fileRequested);
            file.write(httpRequest.getJsonObject().getAsString());


        } catch (IOException e) {
            e.printStackTrace();

        } finally {

            try {
                file.flush();
                file.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

        FileWriter file = httpRequest.getStartLineURL();
        if (httpRequest.getStartLineURL().length() > 1) {
            fileRequested = file;
            fileRequested.substring(1);
        }
        File file2 = new File(FileHandler.WEB_ROOT, fileRequested);
        int fileLength = (int) file2.length();
        String content = getContentType(fileRequested);
        byte[] fileData = readFileData(file2, fileLength);

        HttpHeadersDataToClient(out, dataOut, fileLength, content, fileData);

        System.out.println("File " + fileRequested + " not found");
    }

    private String getContentType(String fileRequested) {
        if (fileRequested.endsWith(".json") || fileRequested.endsWith(".json"))
            return "text/json";
        else
            return "text/plain";
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
