//Connect.java used to establish a connection with backend
import java.io.*;
import java.net.*;

public class Connect {
    static Thread check;
    static Thread register;
    static Socket socket;
    String reply;
    boolean taken, exists;
    //login = false - register, login = true - check if user exists
    Connect(String msg, boolean login) throws Exception{
    //public static void main(String[] args){
            try {
                socket = new Socket("localhost",9999);
            check = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        BufferedReader stdIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                        out.print(msg);
                        out.flush();
                        reply = stdIn.readLine();
                        if(login)
                        {
                            if(reply.equals("ok"))
                            exists = true;
                            else
                            exists = false;
                        }
                        if(!login)
                        {   
                            if(reply.equals("taken"))
                            taken = true;
                            else
                            taken = false;
                        }
                    } catch (IOException e) {
                        System.out.println("IO Exception while running thread");
                    }
                }
            });
            // register = new Thread(new Runnable() {
            //     @Override
            //     public void run() {
            //         try {
            //             out = new PrintWriter(socket.getOutputStream(), true);
            //             if(msg.length == 2)
            //             {
            //                 out.print(msg[0]);
            //                 out.flush();
            //                 out.print(msg[1]);
            //                 out.flush();
            //             }
            //             if(msg.length == 1)
            //             {
            //                 out.print(msg[0]);
            //                 out.flush();
            //             }
            //         } catch (IOException e) {
            //             // TODO Auto-generated catch block
            //             e.printStackTrace();
            //         }
            //     }
            // });
            // if(!send)
        } catch (UnknownHostException e1) {
            System.out.println("Unknown Host");
        } catch (IOException e1) {
            System.out.println("IO Exception before running thread");
        }
            check.start();
            // if(send)
            // register.start();
        try {
            check.join();
            // register.join();
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }
    }

    Connect(String msg) throws Exception{
        try {
            socket = new Socket("localhost",9999);
            check = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    BufferedReader stdIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    if(msg.equals("close"))
                    out.print(msg);
                    else
                    out.print(Integer.parseInt(msg));
                    out.flush();
                } catch (IOException e) {
                    System.out.println("IO Exception while running thread");
                }
            }
        });
    } catch (UnknownHostException e1) {
        System.out.println("Unknown Host");
    } catch (IOException e1) {
        System.out.println("IO Exception before running thread");
    }
        check.start();
    try {
        check.join();
    } catch (InterruptedException e) {
        System.out.println("Interrupted");
    }
    }
}    
