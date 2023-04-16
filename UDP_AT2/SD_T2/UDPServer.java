package SD_T2;

/**
 * UDPServer: Servidor UDP
 * Descricao: Recebe um datagrama de um cliente, imprime o conteudo e retorna o mesmo
 * datagrama ao cliente
 */

import java.net.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class UDPServer{
    public static void main(String args[]){ 
    	DatagramSocket dgramSocket = null;
        String type;
        int nickSize;
        int msgSize;
        byte[] nick = new byte[64];
        byte[] msg = new byte[256];
        byte[] buffer = new byte[1000];

        String packageName = "SD_T2";
        try{
            dgramSocket = new DatagramSocket(6666); // cria um socket datagrama em uma porta especifica
            
            //verifica se o diretório do usuário existe.
            File fileUser = new File(packageName + "/users");
            if(!fileUser.exists()){
                fileUser.mkdir();
            }

            /* recebe o user e senha para validar */
            DatagramPacket dgramPacket = new DatagramPacket(buffer, buffer.length);
            dgramSocket.receive(dgramPacket);  // aguarda a chegada de datagramas
            FileWriter fileWriter;
            BufferedReader reader;
            List <String> key = Arrays.asList(new String(dgramPacket.getData(), 0, dgramPacket.getLength()).split(" "));
			String userName = key.get(1).replace(" ", "");

            if(key.get(0).equals("CONNECT")){
                fileUser = new File( packageName + "/users/" + userName + ".txt" );
                /* verifica a senha do user */
                if(fileUser.exists()){
                    reader = new BufferedReader(new FileReader(packageName + "/users/" + userName + ".txt" ));
                    String line = reader.readLine();
                    reader.close();

                    if(!(key.get(2).replace(" ", "").equals(line.toString()))){ /* usuario errou a senha, desconectando user */
                        System.out.println("User: " + userName + " disconnected");
                    }
                    else{ /* usuario conectado */
                        System.out.println("User: " + userName + " connected");
                    }
                }
                else{ /* caso não tenha o user, cria um novo */
                    fileUser.createNewFile();
                    fileWriter = new FileWriter(packageName + "/users/" + userName + ".txt");
                    fileWriter.write(key.get(2));
                    fileWriter.close();
                }
            }

            while(true){
                // byte[] buffer = new byte[1000]; // cria um buffer para receber requisições

                /* cria um pacote vazio */
                dgramPacket = new DatagramPacket(buffer, buffer.length);
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
