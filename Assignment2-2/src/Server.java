import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private static ArrayList<ServerThread> Clients = new ArrayList<>();


    public static Chatroom rooms = new Chatroom();


    public static void main(String[] args) throws IOException {


        try {
            // create server socket
            ServerSocket server = new ServerSocket(3333);


            while (true) {
                System.out.println("Waiting for connection");
                new ServerThread(server.accept(),rooms);

                System.out.println("Connected");



            }
        } catch (Exception E) {
            System.out.println("something bad happened: possibly port is occupied");
        }


    }
    }



