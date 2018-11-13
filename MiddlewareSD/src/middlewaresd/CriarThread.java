/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package middlewaresd;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Helbert Monteiro
 */
public class CriarThread {
    
    private ServerSocket socketServidor;
    private Socket       dispositivoCliente;
    
    private String ipDispositivo;
    private Scanner mensagem;
    private int    portaDispositivo;
    
    
    public void run(int porta, String ipSoma, String ipMultiplicacao){
        try {
            socketServidor = new ServerSocket(porta);
            System.out.println("Ouvindo na porta 9002...");

            while(true) {
                dispositivoCliente = socketServidor.accept();
                System.out.println("\n\nCliente conectado: " + dispositivoCliente.getInetAddress().getHostAddress());

                mensagem = new Scanner(dispositivoCliente.getInputStream());
                String msg = mensagem.nextLine();
                System.out.println(String.valueOf(msg));

                if((msg.charAt(0)) == '+'){
                    ipDispositivo    = dispositivoCliente.getInetAddress().getHostAddress();
                    portaDispositivo = dispositivoCliente.getPort();
                            
                    System.out.println("Mensagem a ser enviada para ServSoma: " + msg);
                    new Cliente().enviar(String.valueOf(msg), ipSoma, 9008);
                }else{
                    if((msg.charAt(0)) == '*'){
                        ipDispositivo    = dispositivoCliente.getInetAddress().getHostAddress();
                        portaDispositivo = dispositivoCliente.getPort();
                        
                        System.out.println("Mensagem a ser enviada para ServMultiplicação: " + msg);
                        new Cliente().enviar(String.valueOf(msg), ipMultiplicacao, 9009);
                    }else{
                        System.out.println("Mensagem a ser devolvida para Cliente: " + msg);
                        new Cliente().enviar(msg, ipDispositivo, portaDispositivo);
                    }
                }

                dispositivoCliente.close();
            }
        }catch(Exception a) {
            System.out.println("Erro na Thread: " + a.getMessage());
        }
    }
}