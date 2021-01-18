import java.net.*;
import java.io.*;
import java.util.*;

public class Server {
    private static DatagramSocket socket;
    private static byte[] buffer = new byte[1024];
    private static final int BUF_LEN = 1024;

    public static void main(String args[]) throws SocketException, IOException
    {
      if(args.length != 1){
        System.out.println("Specify a port number");
        System.exit(-1);
      }
      final int PORT_NUM = Integer.parseInt(args[0]);
      socket = new DatagramSocket(PORT_NUM);
      DatagramPacket packet;
      String msg;
      while(true){
        packet = new DatagramPacket(buffer, BUF_LEN);
        socket.receive(packet);
        packet = new DatagramPacket(buffer, BUF_LEN, packet.getAddress(), packet.getPort());
        msg = new String(packet.getData(), 0, packet.getLength());
        if(msg.equals("eof")){
          break;
        }
        System.out.println(msg);
        buffer = new byte[1024];
        socket.send(packet);
      }
      socket.close();
    }
}
