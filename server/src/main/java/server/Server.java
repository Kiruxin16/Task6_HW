package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static ServerSocket server;
    public static Socket socket;
    public static final int PORT = 8189;

    private static DataInputStream in;
    private static DataOutputStream out;

    public static void main(String[] args) {

        try {
            Server.server = new ServerSocket(PORT);
            System.out.println("server started");
            socket = server.accept();
            System.out.println("Client connected");


            in =new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());



            new Thread(()->{
                try {
                    while (true) {
                       String str = in.readUTF();

                        if (str.equals("/end")) {
                            break;
                        }

                        System.out.println("Client: " + str);
                    }

                } catch (IOException e) {
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
