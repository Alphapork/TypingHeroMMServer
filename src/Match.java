import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Match {
    private Socket player1;
    private Socket player2;

    public Match() {
        player1 = null;
        player2 = null;
    }


    public void join(Socket socket) {
        if (player1 == null) {
            player1 = socket;
        } else if ( player2 == null) {

            player2 = socket;
            start();
        }
    }

    public void start(){
        try {
            DataInputStream player1in = new DataInputStream(player1.getInputStream());
            DataOutputStream player1out = new DataOutputStream(player1.getOutputStream());
            DataInputStream player2in = new DataInputStream(player2.getInputStream());
            DataOutputStream player2out = new DataOutputStream(player2.getOutputStream());
            MatchThread mt1 = new MatchThread(player1in,player2out);
            MatchThread mt2 = new MatchThread(player2in,player1out);
            mt1.start();
            mt2.start();
            System.out.println("START");
        } catch (Exception e) {
            System.out.println(e);
        }


    }
}
