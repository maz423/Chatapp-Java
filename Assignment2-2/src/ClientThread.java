import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLOutput;

public class ClientThread implements Runnable{
    private Socket server;
    PrintWriter out;
    BufferedReader in;
    public ClientThread(Socket s) throws IOException {
        server = s;

        in = new BufferedReader(new InputStreamReader(server.getInputStream()));

    }
    @Override
    public void run() {

             String response = "init";

             try{

                 while(true){
                 response = in.readLine();
                 if(response == null){
                     System.out.println("Goodbye");
                     System.exit(0);
                 }
                 System.out.println(response);
                 }

             }



             catch (IOException e) {

             }
             finally {
                 try {
                     in.close();
                 } catch (IOException e) {
                     throw new RuntimeException(e);
                 }
             }



         }
    }

