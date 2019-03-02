import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class MatchThread extends Thread {

    private DataInputStream in;
    private DataOutputStream out;

    public MatchThread(DataInputStream in, DataOutputStream out) {

        this.in = in;
        this.out = out;
        setDaemon(true);
    }

    @Override
    public void run() {

        while(true) {
            try {
                String msg = in.readUTF();
                out.writeUTF(msg);
            } catch (Exception e) {
                System.out.println(e);
                return;
            }
        }
    }
}
