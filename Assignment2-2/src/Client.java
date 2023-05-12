import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1",3333);
        ClientThread Scon = new ClientThread(socket);

        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(socket.getOutputStream(),true);

        new Thread(Scon).start();
        while(true) {
            System.out.println(">");

            String command = keyboard.readLine();
            if (command.equals("quit")){
                break;
            }
            out.println(command);



        }
        socket.close();
        System.exit(0);
    }
}
