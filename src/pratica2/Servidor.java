package pratica2;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor {

    public static void main(String[] args) {

        try {

            InetAddress endereco_remoto;
            int porta_remota;
            ServerSocket s = new ServerSocket(2000);
            System.out.println("Esperando conexão........");

            Socket conexao = s.accept();
            System.out.println("Conexão aceita, esperando dados...");

            endereco_remoto = conexao.getInetAddress();
            porta_remota = conexao.getPort();

            System.out.println("Nome da maquina remota: " + endereco_remoto.getHostName());
            System.out.println("IP da maquina remota: " + endereco_remoto.getHostAddress());
            System.out.println("Porta da maquina remota: " + porta_remota);

            FileWriter arq = new FileWriter("/home/keilane/Documentos/Log.txt");
            PrintWriter gravarArq = new PrintWriter(arq);

            String content = "Nome da maquina remota: " + endereco_remoto.getHostName()
                    + "\n IP da maquina remota: " + endereco_remoto.getHostAddress()
                    + "\n Porta da maquina remota: " + porta_remota;
            
            gravarArq.write(content);

            DataInputStream entrada = new DataInputStream(conexao.getInputStream());
            DataOutputStream saida = new DataOutputStream(conexao.getOutputStream());

            for (int i = 0; i < 200; i++) {
                int in = entrada.readInt();
                System.out.println("Entrei");
                saida.writeUTF("Recebi seu dado: " + in);
            }
            
            arq.close();

        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
