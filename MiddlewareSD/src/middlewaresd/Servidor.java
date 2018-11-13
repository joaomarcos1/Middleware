/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package middlewaresd;

/**
 *
 * @author Helbert Monteiro
 */
public class Servidor {
    
    private final Thread thread;
    
    public Servidor(String ipSoma, String ipMultiplicacao){
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Middleware ON\n");
                new CriarThread().run(9002, ipSoma, ipMultiplicacao);
            }});
        thread.start();
    }
    
}