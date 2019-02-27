package pratica1;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor {

    public static void main(String[] args) {
        
        try {
            ServerSocket s = new ServerSocket(2000);
            System.out.println("Esperando conexão........");
            Socket conexao = s.accept();
            System.out.println("Conexão aceita, esperando dados...");
            DataInputStream entrada = new DataInputStream(conexao.getInputStream());
            DataOutputStream saida = new DataOutputStream(conexao.getOutputStream());
            
            for ( int i = 0; i < 100000; i++ ) {
                int in = entrada.readInt();
                System.out.println("Entrei");
                saida.writeUTF("Recebi seu dado: " + in);
            }
            
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}