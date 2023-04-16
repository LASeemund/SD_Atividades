package SD_T2;

/**
 * UDPServer: Servidor UDP
 * Descricao: Recebe um datagrama de um cliente, imprime o conteudo e retorna o mesmo
 * datagrama ao cliente
 */

import java.net.*;
import java.io.*;

public class UDPServer{
    public static void main(String args[]){ 
    	DatagramSocket dgramSocket = null;
        String type;
        int nickSize;
        int msgSize;
        byte[] nick = new byte[64];
        byte[] msg = new byte[256];
        try{
            dgramSocket = new DatagramSocket(6666); // cria um socket datagrama em uma porta especifica
            
            while(true){
                byte[] buffer = new byte[1000]; // cria um buffer para receber requisições

                /* cria um pacote vazio */
                DatagramPacket dgramPacket = new DatagramPacket(buffer, buffer.length);
                dgramSocket.receive(dgramPacket);  // aguarda a chegada de datagramas

                type = new String(dgramPacket.getData(), 0, 1);
                nickSize = dgramPacket.getData()[1];
                System.arraycopy(dgramPacket.getData(), 2, nick, 0, nickSize);
                msgSize = dgramPacket.getData()[2 + nickSize];
                System.arraycopy(dgramPacket.getData(), 3 + nickSize, msg, 0, msgSize);
                // System.out.println("Tipo: " + type + " " + new String(nick, 0, nickSize) + " disse: " + new String(msg, 0, msgSize) + " Tam Ap: " + nickSize + " Tam Msg: " + msgSize);
                System.out.println(new String(nick, 0, nickSize) + " disse: " + new String(msg, 0, msgSize));
                // /* imprime e envia o datagrama de volta ao cliente */ 
                // DatagramPacket reply = new DatagramPacket(dgramPacket.getData(),
                //         dgramPacket.getLength(), dgramPacket.getAddress(), dgramPacket.getPort()); // cria um pacote com os dados
                DatagramPacket reply = new DatagramPacket("OK".getBytes(), 2, dgramPacket.getAddress(), dgramPacket.getPort()); // cria um pacote com os dados

                dgramSocket.send(reply); // envia o pacote
            } //while
        }catch (SocketException e){
            System.out.println("Socket: " + e.getMessage());
        }catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        } finally {
            dgramSocket.close();
        } //finally
    } //main
}//class
