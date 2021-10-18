package com.condominio.teste;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class SocketClient {
    public static void main(String[] args) {
            try{
                Socket soc=new Socket("127.0.0.1",8080);
                DataOutputStream dout= new DataOutputStream(soc.getOutputStream());
                DataInputStream in = new DataInputStream(soc.getInputStream());

                Scanner scan = new Scanner(System.in);
                System.out.println("Digite um comando: ");
                while (true) {

                    if (System.in.available() > 0) {
                        String cmd = scan.nextLine() + "\r\n";
                        byte[] b = String.format("%128s", cmd).getBytes();
                        System.out.println("Bytes da mensagem ==> " +  b.length);
                        dout.writeUTF(cmd);
                        dout.flush();

                        if ("QUIT".equals(cmd)) {
                            break;
                        }
                        System.out.println("Digite um comando: ");
                    }
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