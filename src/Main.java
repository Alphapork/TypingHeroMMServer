import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class Main {
    private HashMap<String,Match> pendingMatches;
    private ServerSocket serversocket;
    private Socket socket;
    public static void main(String args[]) {
        Main main = new Main();
    }

    public Main() {
        pendingMatches = new HashMap<>();
        try {
            serversocket = new ServerSocket(9999);
            System.out.println("Server Started.");
            while(true){
                socket = serversocket.accept();
                System.out.println(socket.getInetAddress() + " connected.");
                DataInputStream in = new DataInputStream(socket.getInputStream());
                String msg = in.readUTF();
                if (msg.equals("JOIN")){
                    msg = in.readUTF();
                    if (pendingMatches.containsKey(msg)){
                        pendingMatches.get(msg).join(socket);
                        System.out.println(socket.getInetAddress() + " joined match with id: " + msg);
                        System.out.println("Match starting!");
                        pendingMatches.remove(msg);
                    } else {
                        pendingMatches.put(msg,new Match());
                        pendingMatches.get(msg).join(socket);
                        System.out.println(socket.getInetAddress() + " created match with id: " + msg);
                        System.out.println("Waiting for players...");
                    }
                }
            }
        } catch (Exception e){
            System.out.println(e);
        }
    }
}
