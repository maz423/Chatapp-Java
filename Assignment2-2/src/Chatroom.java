import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;

public class Chatroom {

    public Hashtable<String, ArrayList<String>> chats = new Hashtable<>();
//    public ArrayList<String> chats = new ArrayList<String>();


     synchronized void create_room(String name){
         ArrayList<String> msg = new ArrayList<>();
         chats.put(name,msg);


    }


     synchronized ArrayList<String> get_rooms(String name){

         return chats.get(name);




    }

    synchronized StringBuilder get_messages(String name){

         StringBuilder str = new StringBuilder();
         ArrayList<String> array = get_rooms(name);
         for (int i = 0; i< array.size(); i ++){
             str.append(array.get(i)).append("\n");
         }
        return str;


    }

    synchronized void Send_messages(String name, String msg){



         chats.get(name).add(msg);


    }

    synchronized Set<String> List_rooms(){
         return chats.keySet();
    }


}
