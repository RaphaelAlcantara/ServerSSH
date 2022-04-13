import java.io.*;
import java.net.*;
import java.util.*;
import java.time.ZonedDateTime;



public class EchoServer extends Thread{
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
                out.println("Hello! Enter 3 to visualize the package");

                Scanner in = new Scanner(inStream);



                // echo client input
                boolean done = false;
                while (!done && in.hasNextLine()) {

                    {
                        String line = in.nextLine();
                        System.out.println("Cliente inseriu: " + line);
                        if (line.trim().equals("BYE")){
                        done = true;

                        }
                        else if (line.trim().equals("1")) {
                            out.println("A hora atual é: " + ZonedDateTime.now());
                        }
                            else if (line.trim().equals("2")) {
                            out.println("O sistema é: "+ System.getProperty("os.name"));

                        }else if (line.trim().equals("3")) {
                            for(int pacote = 0; pacote < 5; pacote++){
                                out.println("Pacote "+ pacote +" enviado");
                                wait(2000);
                                if(pacote==2){
                                    out.println("Pacote "+ pacote +" Timeout");
                                    wait(1000);
                                    out.println("Pacote "+ pacote +" reenviado");
                                    wait(2000);
                                }
                                System.out.println("Pacote "+ pacote +" recebido");
                            }

                        }else {
                                out.println("Opção inválida");
                        }out.println("Insira outro número para uma nova operação: ");

                    }
                }
            } finally {
                incoming.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void wait(int ms)
    {
        try
        {
            Thread.sleep(ms);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }
};