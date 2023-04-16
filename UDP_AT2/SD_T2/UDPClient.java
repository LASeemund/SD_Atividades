package SD_T2;

/**
 * UDPClient: Cliente UDP Descricao: Envia uma msg em um datagrama e recebe a
 * mesma msg do servidor
 */
import java.net.*;
import java.io.*;
import javax.swing.JOptionPane;

import java.nio.charset.StandardCharsets; //lembrar de remover quando nao usar mais

public class UDPClient {
    
    // valida se a string é um URL
    // https://stackoverflow.com/questions/163360/regular-expression-to-match-urls-in-java (alterada para apenas validar urls)
    public static boolean isURL(String inputStr) {
        String regex = "\\b(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
        try{
            return inputStr.matches(regex);
        }
        catch (Exception e){
            return false;
        }
    }

    //validada se a string está contina no array de strings
    //https://stackoverflow.com/questions/8992100/test-if-a-string-contains-any-of-the-strings-from-an-array (alterada para validar se a string contém algum emoji)
    public static boolean stringIsEmoji(String inputStr) {
        String [] itens = {":)", ":(", "XD", "XP", "XO", ";)", ":D", ":P", ":O", ":*", ":|", ":/", ";-;", "'-'", ":'(", ":'D", ":'P", ":'O", ":'*", ":'|", ":'/", ":'-;", ":'-'", ":'-(", ":'-D", ":'-P", ":'-O", ":'-*", ":'-|", ":'-/", ":'-;", ":'--", ":'-'"};
        for (int i = 0; i < itens.length; i++) {
            if (inputStr.contains(itens[i])) {
                return true;
            }
        }
        return false;
    }

    public static void main(String args[]) {
        DatagramSocket dgramSocket;
        int resp = 0;

        try {
            dgramSocket = new DatagramSocket(); //cria um socket datagrama
            
            //127.0.0.1 6666
            // String dstIP = JOptionPane.showInputDialog("IP Destino?");
            // int dstPort = Integer.parseInt(JOptionPane.showInputDialog("Porta Destino?"));
            String dstIP = "127.0.0.1";
            int dstPort = 6666;
            
            /* armazena o IP do destino */
            InetAddress serverAddr = InetAddress.getByName(dstIP);
            int serverPort = dstPort; // porta do servidor

            do {
                String msg = JOptionPane.showInputDialog("Digite a mensagem:");

                Byte msgtype = 0;

                // verifica se a mensagem é um ECHO, URL ou EMOJI
                if(msg == "ECHO"){
                    msgtype = 1; // 1 = ECHO
                }
                else if(isURL(msg)){
                    msgtype = 2; // 2 = URL
                }
                else if(stringIsEmoji(msg)){
                    msgtype = 3; // 3 = EMOJI
                }
                else{
                    msgtype = 4; // 4 = Mensagem normal
                }

                /** Cria o buffer com o formato da mensagem */
                byte[] m = new byte[msg.getBytes().length];          // transforma a mensagem em bytes
                System.arraycopy(msg.getBytes(), 0, m, 0, msg.getBytes().length);
                //System.arraycopy(a, 0, c, 0, a.length);
                //System.arraycopy(b, 0, c, a.length, b.length);


                int total = m.length, pos = 0, newlength = 255;
                byte[] buffer = new byte[newlength];

                // envia pacotes de 255 bytes
                while(true){
                    if(total <= 0) break;

                    if(total < 255){
                        newlength = total;
                    }
                    buffer = new byte[newlength];
                    System.out.println(new String(buffer, StandardCharsets.UTF_8));
                    System.arraycopy(m, pos, buffer, 0, newlength);
                    
                    /* cria um pacote datagrama */
                    DatagramPacket request
                            = new DatagramPacket(buffer, buffer.length, serverAddr, serverPort);

                    /* envia o pacote */
                    dgramSocket.send(request);

                    total -= newlength;
                    pos += 255;
                }

                /* cria um buffer vazio para receber datagramas */
                byte[] bufferResponse = new byte[1000];
                DatagramPacket reply = new DatagramPacket(bufferResponse, bufferResponse.length);

                /* aguarda datagramas */
                dgramSocket.receive(reply);
                System.out.println("Resposta: " + new String(reply.getData(),0,reply.getLength()));

                resp = JOptionPane.showConfirmDialog(null, "Nova mensagem?", 
                        "Continuar", JOptionPane.YES_NO_OPTION);

            } while (resp != JOptionPane.NO_OPTION);

            /* libera o socket */
            dgramSocket.close();
        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        } //catch
    } //main		      	
} //class
