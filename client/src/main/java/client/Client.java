package client;

import javafx.application.Platform;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static ServerSocket server;
    public static Socket socket;
    public static final int PORT = 8189;
    private static final String ADDRESS = "localhost";

    private static DataInputStream in;
    private static DataOutputStream out;

    public static void main(String[] args) {


        try {
            socket=new Socket(ADDRESS, PORT);
            in =new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            new Thread(()->{
                try{
                    while(true){

                        String  str = in.readUTF();
                        if(str.equals("/end")){
                            break;
                        }
                        System.out.println("Server: "+str);

                    }
                }catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }).start();

            Scanner sc = new Scanner(System.in);

            while (true){
                String str = sc.nextLine();

                if(str.equals("/end")){
                    break;
                }
                out.writeUTF(str);
            }










        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
