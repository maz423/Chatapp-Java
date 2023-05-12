import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.security.Key;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class ServerThread extends  Thread implements Runnable{

    private Socket client;
    PrintWriter out;
    BufferedReader in;

    Chatroom group;

    public ServerThread(Socket Client , Chatroom rooms) {
        group = rooms;
        client = Client;
        this.start();


    }



    public void run() {


        try {

            out = new PrintWriter(client.getOutputStream(),true);
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));


            try {
                while (true) {
                    out.println("Welcome to chatroom: \n 1. create room \n 2. join room  \n 3. List existing rooms  \n 4. exit ");
                    String request = in.readLine();


                if (Integer.parseInt(request) == 1){
                    out.println("Enter name of room to be created:");
                    String name = in.readLine();
                    group.create_room(name);
                    out.println("Room " + name + " created");

//                    out.println(group.get_rooms(name));
                }
                else if (Integer.parseInt(request) == 2){

                    out.println("Please enter room name to join");
                    String grp_name = in.readLine();
                    out.println("Welcome to room: " + grp_name);
                    boolean found = false;
                    for( String key: group.List_rooms()){

                        if(key.equals(grp_name)){
                            found = true;
                        }


                    }
                    if (!found){
                        out.println("ERROR: Enter a valid group name:");
                    }


                    else{
                    out.println("What is your name :");
                    String usr_name = in.readLine();
                    String messages = String.valueOf(group.get_messages(grp_name));
                    ArrayList<String> arr = group.get_rooms(grp_name);
                    AtomicInteger prev = new AtomicInteger(arr.size());
                    out.println(messages);


                    new Thread(() -> {

                        out.println("Start chatting: Enter \"leave\" to exit room");
                        while(true){
                            String msgs = String.valueOf(group.get_messages(grp_name));
                            ArrayList<String> check = group.get_rooms(grp_name);
                            if(check.size() != prev.get()){
                                int index = check.size()-1 ;
                                if (check.get(index) != null){
                                    out.println(check.get(index));
                                }


                                prev.set(check.size());
                            }
                        }
                    }).start();

                    while(true){





                        String text = in.readLine();
                        if (text.equals("leave")){
                            break;
                        }
                        if(text != null){
                            String prefix = usr_name + " :" + text;

                            group.Send_messages(grp_name,prefix);
                        }





                    }}

                }
                else if (Integer.parseInt(request) == 3){
                    Set<String> rooms = group.List_rooms();
                    out.println("Rooms :" + rooms.toString());


                }
                else if (Integer.parseInt(request) == 4){
                    System.exit(0);

                }


                }
            } finally{
                out.close();
                in.close();}
            }

         catch (Exception E) {
        }


    }



}
