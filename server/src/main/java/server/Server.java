package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static ServerSocket server;
    public static Socket socket;
    public static final int PORT = 8189;

    public static void main(String[] args) {

        try {
            Server.server = new ServerSocket(PORT);
            System.out.println("server started");


            socket = server.accept();
            System.out.println("Client connected");

            Scanner sc = new Scanner(socket.getInputStream());
            PrintWriter out = new PrintWriter(socket.getOutputStream(),true);



            while (true){
                String str = sc.nextLine();

                if(str.equals("/end")){
                    break;
                }
                System.out.println("Client: "+str);
                out.println("ECHO: "+str);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
