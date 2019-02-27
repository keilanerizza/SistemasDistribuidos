package pratica3;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor {

    public static void main(String[] args) {

        try {
            ServerSocket s = new ServerSocket(2001);
            
            while(true) {
                
                System.out.println("Esperando conectar !");
                Socket conexao = s.accept();
                System.out.println("Conectou !");
                
                DataInputStream entrada = new DataInputStream(conexao.getInputStream());
                DataOutputStream saida = new DataOutputStream(conexao.getOutputStream());
                
                String linha = entrada.readUTF();
                
                while (linha != null && !(linha.trim().equals(""))) {
                    saida.writeUTF(linha);
                    linha = entrada.readUTF();
                }
                
                saida.writeUTF(linha);
                conexao.close();
            }
            
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

}
