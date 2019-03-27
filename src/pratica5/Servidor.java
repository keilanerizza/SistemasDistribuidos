package pratica5;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author keilane
 */
public class Servidor {

    public static void main(String[] args) {

        try {
            DatagramSocket s = new DatagramSocket(4545);
            System.out.println("Servidor esperando conexão.........");

            DatagramPacket recebe = new DatagramPacket(new byte[512], 512);

            while (true) {
                s.receive(recebe);

                for (int i = 0; i < recebe.getLength(); i++) {
                    System.out.print((char) recebe.getData()[i]);
                }

                DatagramPacket resp = new DatagramPacket(recebe.getData(), recebe.getLength(), recebe.getAddress(), recebe.getPort());
                s.send(resp);
            }

        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
