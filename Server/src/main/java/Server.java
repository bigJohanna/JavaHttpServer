import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;


// Each Client Connection will be managed in a dedicated Thread
    public class Server implements Runnable{

        static final File WEB_ROOT = new File("Server/src/main/resources");
        static final String DEFAULT_FILE = "index.html";
        static final String FILE_NOT_FOUND = "404.html";
        static final String METHOD_NOT_SUPPORTED = "not_supported.html";
        // port to listen connection
        static final int PORT = 8080;

        // verbose mode
        static final boolean verbose = true;

    // Client Connection via Socket Class
    private Socket connect;

    public Server(Socket c) {
        connect = c;
    }

    public static void main(String[] args) {
        try {
            ServerSocket serverConnect = new ServerSocket(PORT);
            System.out.println("Server started.\nListening for connections on port : " + PORT + " ...\n");

            // we listen until user halts server execution
            while (true) {
                Server myServer = new Server(serverConnect.accept());

                if (verbose) {
                    System.out.println("Connecton opened. (" + new Date() + ")");
                }

                // create dedicated thread to manage the client connection
                Thread thread = new Thread(myServer);
                thread.start();
            }

        } catch (IOException e) {
            System.err.println("Server Connection error : " + e.getMessage());
        }
    }

    @Override
    public void run() {
        // we manage our particular client connection
        BufferedReader in = null;
        PrintWriter out = null;
        BufferedOutputStream dataOut = null;
        String fileRequested = null;


        try {
            // we read characters from the client via input stream on the socket
            in = new BufferedReader(new InputStreamReader(connect.getInputStream()));
            // we get character output stream to client (for headers)
            out = new PrintWriter(connect.getOutputStream());
            // get binary output stream to client (for requested data)
            dataOut = new BufferedOutputStream(connect.getOutputStream());

            //parse the request to a javaObject

            HTTPRequest httpRequest = new HTTPRequest();
            ParseRequest parseRequest = new ParseRequest(httpRequest, in);
            parseRequest.parseBodyToJson(httpRequest,in);
            HTTPRequest result = parseRequest.parseStartLineAndHeadToJavaObject(httpRequest, in);




            // we support only GET and HEAD methods, we check
            if (result.getStartLineImplementation().equals("GET") ) {// &&  !httpRequest.getMethod().equals("HEAD")) {
                if (verbose) {
                    System.out.println("501 Not Implemented : " + result.getStartLineImplementation() + " method.");
                }

                // we return the not supported file to the client
                File file = new File(WEB_ROOT, METHOD_NOT_SUPPORTED);
                int fileLength = (int) file.length();
                String contentMimeType = "text/html";
                //read content to return to client
                byte[] fileData = readFileData(file, fileLength);

                // we send HTTP Headers with data to client
                out.print("HTTP/1.1 501 Not Implemented\r\n");
                out.print("Server: Java HTTP Server from SSaurel : 1.0\r\n");
                out.print("Date: " + new Date() + "\r\n");
                out.print("Content-type: " + contentMimeType + "\r\n");
                out.print("Content-length: " + fileLength + "\r\n");
                out.print("\r\n"); // blank line between headers and content, very important !
                out.flush(); // flush character output stream buffer
                // file
                dataOut.write(fileData, 0, fileLength);
                dataOut.flush();

            } else {

                System.out.println(" finish ");

                /*
                // GET or HEAD method
                if (httpRequest.getFile().endsWith("/")) {
                    fileRequested += DEFAULT_FILE;
                }

                File file = new File(WEB_ROOT, httpRequest.getFile());
                int fileLength = (int) file.length();
                String content = getContentType(httpRequest.getFile());

                if (httpRequest.getMethod().equals("GET")) { // GET method so we return content
                    byte[] fileData = readFileData(file, fileLength);

                    // send HTTP Headers
                    out.print("HTTP/1.1 200 OK \r\n");
                    out.print("Server: Java HTTP Server from SSaurel : 1.0 \r\n");
                    out.print("Date: " + new Date() + "\r\n");
                    out.print("Content-type: " + content + "\r\n");
                    out.print("Content-length: " + fileLength + "\r\n");
                    out.print("\r\n"); // blank line between headers and content, very important !
                    out.flush(); // flush character output stream buffer

                    dataOut.write(fileData, 0, fileLength);
                    dataOut.flush();
                }

                if (verbose) {
                    System.out.println("File " + fileRequested + " of type " + content + " returned");
                }

                 */

            }

        } catch (FileNotFoundException fnfe) {
            try {
                fileNotFound(out, dataOut, fileRequested);
            } catch (IOException ioe) {
                System.err.println("Error with file not found exception : " + ioe.getMessage());
            }

        } catch (IOException ioe) {
            System.err.println("Server error : " + ioe);
        } finally {
            try {
                in.close();
                out.close();
                dataOut.close();
                connect.close(); // we close socket connection
            } catch (Exception e) {
                System.err.println("Error closing stream : " + e.getMessage());
            }

            if (verbose) {
                System.out.println("Connection closed.\n");
            }
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

    // return supported MIME Types
    private String getContentType(String fileRequested) {
        if (fileRequested.endsWith(".htm")  ||  fileRequested.endsWith(".html"))
            return "text/html";
        else
            return "text/plain";
    }

    private void fileNotFound(PrintWriter out, OutputStream dataOut, String fileRequested) throws IOException {
        File file = new File(WEB_ROOT, FILE_NOT_FOUND);
        int fileLength = (int) file.length();
        String content = "text/html";
        byte[] fileData = readFileData(file, fileLength);

        out.print("HTTP/1.1 404 File Not Found\r\n");
        out.print("Server: Java HTTP Server from SSaurel : 1.0\r\n");
        out.print("Date: " + new Date() + "\r\n");
        out.print("Content-type: " + content + "\r\n");
        out.print("Content-length: " + fileLength + "\r\n");
        out.print("\r\n"); // blank line between headers and content, very important !
        out.flush(); // flush character output stream buffer

        dataOut.write(fileData, 0, fileLength);
        dataOut.flush();

        if (verbose) {
            System.out.println("File " + fileRequested + " not found");
        }
    }
/*
    public TheRequest parseRequestToJavaObject(BufferedReader in) throws IOException {
        //requestObject
        TheRequest theRequest = new TheRequest();

        //Line 1 of request ("GET /index.html HTTP/1.1")
        String lineOne = in.readLine();

        //set hostname and user-agent name from line 2 and 3 from request
        theRequest.setHost(in.readLine());
        theRequest.setUserAgent(in.readLine());
        theRequest.setConnection(in.readLine());
        theRequest.setAccept(in.readLine());

        //  requestLineOne into method and fileRequested
        StringTokenizer parse = new StringTokenizer(lineOne);
        //get first word of line one of request
        theRequest.setMethod(parse.nextToken().toUpperCase());
        // get second part of line one of request
        theRequest.setFile(parse.nextToken().toLowerCase());


        //print info to console
        System.out.println("Method of TheRequestObject!!!!!!!!!!!!!!!!!!!!!!: " + theRequest.getMethod());
        System.out.println("File requested of TheRequestObject!!!!!!!!!!!!!!: " + theRequest.getFile());
        System.out.println("Host of TheRequestObject!!!!!!!!!!!!!!!!!!!!!!!!: " + theRequest.getHost());
        System.out.println("User-Agent of TheRequestObject!!!!!!!!!!!!!!!!!!: " + theRequest.getUserAgent());
        System.out.println("Connection of TheRequestObject!!!!!!!!!!!!!!!!!!: " + theRequest.getConnection());
        System.out.println("Accept of TheRequestObject!!!!!!!!!!!!!!!!!!!!!!: " + theRequest.getAccept());
        //System.out.println("Body of TheRequestObject!!!!!!!!!!!!!!!!!!!!!!!!: " + );

        return theRequest;
    }
*/

}
