package it.fi.meucci;
import java.io.*;
import java.net.*;

public class Client {
    String serverName = "localhost";
    int serverPort = 6789;
    Socket mioSocket;
    BufferedReader tastiera;
    String stringaUtente;
    String stringaDalServer;
    DataOutputStream out;
    BufferedReader in;

    public Socket connetti(){
        System.out.println("CLIENT is running...");
        try {
            tastiera = new BufferedReader(new InputStreamReader(System.in));
            mioSocket = new Socket(serverName, serverPort);
            out = new DataOutputStream(mioSocket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(mioSocket.getInputStream()));
        } catch (Exception e) {
            System.out.println("Errore nella connessione");
        } 
        return mioSocket;
    }

    public void comunica(){
        for(;;){
            try {
                System.out.println("Inserisci i dati da trasmettere al server (num1, num2, operatore):"+"\n");
                stringaUtente = tastiera.readLine();
                System.out.println("invio al server");
                out.writeBytes(stringaUtente + "\n");
    
                stringaDalServer = in.readLine();
                System.out.println("risposta dal server: " + "\n" + stringaDalServer);
                // se la stringa dell'utente è 'BYE' chiudo la connessione
                if (stringaUtente.equals("BYE")){
                    System.out.println("CLIENT : ending connection");
                    mioSocket.close();
                    break;
                }
            } catch (Exception e) {
                System.out.println("Errore nella comunicazione");
            }
        }
        
    }


}