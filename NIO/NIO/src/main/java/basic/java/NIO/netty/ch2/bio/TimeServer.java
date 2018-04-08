package basic.java.NIO.netty.ch2.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * chapter 2.1
 * BIO:每当有一个新的客户端请求接入时，服务端必须创建一个新的线程处理新的接入的客户端链路，一个线程只能处理一个客户端连接
 * 在高性能服务器应用领域，往往需要成千上万个客户端的并发连接，这种模型显然无法满足高性能、高并发接入的场景。
 */
public class TimeServer {

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
            
            while(true){
                socket = server.accept(); //阻塞
                new Thread(new TimeServerHandler(socket)).start();
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
