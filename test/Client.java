import java.net.*;
import java.util.Scanner;
import java.io.*;

public class Client {
    private static DatagramSocket socket;
    private static InetAddress address;
    private static byte[] buf;

    private final static int MAGIC = 0xC356;


    public static void main(String args[]) throws SocketException, IOException
    {
      socket = new DatagramSocket();
      address = InetAddress.getByName("localhost");
      Scanner sc = new Scanner(System.in);
      while(sc.hasNextLine()){
        String msg = sc.nextLine();
        buf = msg.getBytes();
        DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 4445);
        socket.send(packet);
        packet = new DatagramPacket(buf, buf.length);
        socket.receive(packet);
        String received = new String(packet.getData(), 0, packet.getLength());
        System.out.println(received);
      }
      socket.close();
    }
}
