import java.io.*;
import java.net.*;
import java.util.*;
import java.time.ZonedDateTime;

public class EchoServer {
    public static void main(String[] args) {
        try {
            ServerSocket s = new ServerSocket(8190);
            Socket incoming = s.accept();
            try {
                InputStream inStream = incoming.getInputStream();
                OutputStream outStream = incoming.getOutputStream();

                PrintWriter out = new PrintWriter(outStream, true /* autoFlush */);
                out.println("Hello! Enter BYE to exit.");
                out.println("Hello! Enter 1 to Actual Hour.");
                out.println("Hello! Enter 2 to Name of System.");
                Scanner in = new Scanner(inStream);

                // echo client input
                boolean done = false;
                while (!done && in.hasNextLine()) {

                    {
                        String line = in.nextLine();
                        System.out.println("Cliente " + line);
                        if (line.trim().equals("BYE")){
                        done = true;
                        //out.println("Echo: " + line);
                        }
                        else if (line.trim().equals("1"))
                        {
                            out.println("A hora atual é: " + ZonedDateTime.now());
                        }

                            else if (line.trim().equals("2"))
                            {
                            out.println(System.getProperty("os.name"));
                        }
                    }
                }
            } finally {
                incoming.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
};