package basic.java.NIO.netty.ch2.pseudoAsync;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import basic.java.NIO.netty.ch2.bio.TimeServerHandler;

/*
 * chapter 2.1
 * 底层通信依然采用同步阻塞模型，无法从根本上解决问题
 */
public class Timeserver {

    public static void main(String[] args) throws IOException {
        int port = 8080;
        if(args != null && args.length > 0){
            try{
                port = Integer.valueOf(args[0]);
            }catch(NumberFormatException e){
                
            }
        }
            
        ServerSocket server = null;
        try{
            server = new ServerSocket(port);
            System.out.println("The time server is start in port: " + port);
            Socket socket = null;
            
            TimeServerHandlerExecutePool singleExecutor = new TimeServerHandlerExecutePool(50, 10000); //创建I/O任务线程池
            
            while(true){
                socket = server.accept(); //阻塞
//                new Thread(new TimeServerHandler(socket)).start();
                singleExecutor.execute(new TimeServerHandler(socket));
            }
        }finally{
            if(server != null){
                System.out.println("The time server close");
                server.close();
                server = null;
            }
        }
    }
}
