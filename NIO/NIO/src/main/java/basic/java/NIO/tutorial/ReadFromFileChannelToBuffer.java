package basic.java.NIO.tutorial;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ReadFromFileChannelToBuffer {

    public static void main(String[] args) {
        
        /*
        //  file:/C:/Developer/STS/Workspaces/ASDF/BasicJava/NIO/maven.1522138347450/NIO/NIO/target/classes/data/nio-data.txt
        ReadFromFileChannelToBuffer.class.getClassLoader().getResource("data/nio-data.txt");
        
        //  /C:/Developer/STS/Workspaces/ASDF/BasicJava/NIO/maven.1522138347450/NIO/NIO/target/classes/
        ReadFromFileChannelToBuffer.class.getClassLoader().getResource("").getPath();
        */
        try {
//            ReadFromFileChannelToBuffer.class.getClassLoader().getResource("data/nio-data.txt");
            RandomAccessFile aFile = new RandomAccessFile("src/main/resources/data/nio-data.txt", "rw");
            
            FileChannel inChannel = aFile.getChannel();
            ByteBuffer buf = ByteBuffer.allocate(48);
            
            try {
                int bytesRead = inChannel.read(buf);
                //读取了32个字符，此时，buffer中的position=32，limit=48，capacity=48
                
                while(bytesRead != -1){
                    System.out.println("Read " + bytesRead);
                    buf.flip();
                    //flip()之后，position=0，limit=32， capacity=48
                    
                    while(buf.hasRemaining()){
                        System.out.print((char)buf.get());
                    }
                    
                    buf.clear();
                    //clear()之后，position=0，limit=48， capacity=48
                    
                    bytesRead = inChannel.read(buf);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            try {
                aFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

/*
 *Read 32
zhangsan 32
lisi 64
wangwu 128
*/