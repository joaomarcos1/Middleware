/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package middlewaresd;



import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Cliente {
    
    OutputStream osSaida;
    PrintWriter  pwSaida;
    Socket       dispositivoCliente;
    
    public void enviar(String mensagem, String ip, int porta){
        try{
            System.out.println("Preparando para enviar mensagem...");
            dispositivoCliente = new Socket(ip, porta);
            osSaida = dispositivoCliente.getOutputStream();
            pwSaida = new PrintWriter(osSaida);
            pwSaida.println(mensagem);
            System.out.println("Mensagem enviada...\n");
        }catch(Exception erro){
            System.out.println("Erro no envio da mensagem: " + erro.getMessage());
        }
    }
    
}