package com.example;
import java.io.*;
import java.net.*;

public class Client {

    String nomeServer = "localhost";
    int portaServer = 6789;
    Socket miSocket;
    BufferedReader tastiera;
    String stringaUtente;
    String strRicevutaDalServer;
    DataOutputStream outVersoServer;
    BufferedReader inDaServer;
    

    public Socket connetti(){
        System.out.println("2 CLIENT partito in esecuzione...");
        try {
            tastiera = new BufferedReader(new InputStreamReader(System.in));
            miSocket = new Socket(nomeServer,portaServer);

            outVersoServer = new DataOutputStream(miSocket.getOutputStream());
            inDaServer = new BufferedReader(new InputStreamReader(miSocket.getInputStream()));

        } catch (UnknownHostException e) {
            // TODO: handle exception
            System.err.println("host sconosciuto");
        } catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("errore durante la connesione ");
            System.exit(1);
        }
        return miSocket;
    }

    public void comunica(){
        for(;;)
        try {
            System.out.println("4 ...  inserisci la stringa da trasmettere al server:"+'\n');
            stringaUtente = tastiera.readLine();

            System.out.println("5 invio la stringa al server e attendo...");
            outVersoServer.writeBytes(stringaUtente+'\n');

            strRicevutaDalServer = inDaServer.readLine();
            System.out.println("8 la risposta del server :"+'\n'+strRicevutaDalServer);

            if(stringaUtente.equals("BYE")){
            System.out.println("9 Client:termina elaborazione e chiude la connessione");
            miSocket.close();
            break;
            }
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
            System.out.println("errore durante la connesione con il server!");
            System.exit(1);
        }
    }
    
}
