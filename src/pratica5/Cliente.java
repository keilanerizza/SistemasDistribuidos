package pratica5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 *
 * @author keilane
 */
public class Cliente {

    public static void main(String[] args) {

        try {
            DatagramSocket s = new DatagramSocket();
            InetAddress dest = InetAddress.getByName("10.10.21.90");
            
            String envio;
            
            BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
            
            System.out.print(" > ");
            envio = teclado.readLine();
            
            while(!envio.equalsIgnoreCase(""))
            {
                byte[] buffer = envio.getBytes();
                
                DatagramPacket msg = new DatagramPacket(buffer, buffer.length, dest, 4545);
                
                s.send(msg);
                
                DatagramPacket resposta = new DatagramPacket(new byte[512], 512);
                
                s.receive(resposta);
                
                for(int i = 0; i < resposta.getLength(); i++) {
                System.out.print((char) resposta.getData() [i]);
            }
                
                System.out.print(" > ");
                envio = teclado.readLine();
                
                s.close();
                
            }            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
