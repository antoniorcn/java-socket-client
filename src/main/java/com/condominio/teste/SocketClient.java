package com.condominio.teste;
import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class SocketClient {
    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
            try{
                Socket soc=new Socket("127.0.0.1",8080);
                DataOutputStream dout= new DataOutputStream(soc.getOutputStream());
                DataInputStream in = new DataInputStream(soc.getInputStream());

                dout.writeUTF("Olá sou um cliente Java\r\n");

                // BufferedReader bfrKey = new BufferedReader(new InputStreamReader(System.in));
                // Scanner scan = new Scanner(System.in);
                System.out.println("Digite um comando: ");
                while (true) {

//                    if (bfrKey.ready()) {
                        // String cmd = bfrKey.readLine() + "\r\n";
                        String cmd = scanner.nextLine() + "\r\n";
                        byte[] b = String.format("%128s", cmd).getBytes();
                        System.out.println("Bytes da mensagem ==> " +  b.length);
                        dout.writeUTF(cmd);
                        dout.flush();

                        if ("QUIT".equals(cmd)) {
                            break;
                        }
                        System.out.println("Digite um comando: ");
//                    }
                    if (in.available() > 0) {
                        String msg=(String)in.readUTF();
                        System.out.println("Server: " + msg);
                    }

                    Thread.sleep(5);
                }


                dout.close();
                soc.close();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
}