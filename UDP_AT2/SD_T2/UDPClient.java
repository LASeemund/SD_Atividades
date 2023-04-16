package SD_T2;

/**
 * UDPClient: Cliente UDP Descricao: Envia uma msg em um datagrama e recebe a
 * mesma msg do servidor
 */
import java.net.*;
import java.io.*;
import javax.swing.JOptionPane;

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

                String msgtype = "";

                // verifica se a mensagem é um ECHO, URL ou EMOJI
                if(msg == "ECHO"){
                    msgtype = "1"; // 1 = ECHO
                }
                else if(isURL(msg)){
                    msgtype = "2"; // 2 = URL
                }
                else if(stringIsEmoji(msg)){
                    msgtype = "3"; // 3 = EMOJI
                }
                else{
                    msgtype = "4"; // 4 = Mensagem normal
                }

                byte[] t = msgtype.getBytes();      // transforma o tipo da mensagem em bytes
                byte[] m = msg.getBytes();          // transforma a mensagem em bytes

                // envia pacotes de 255 bytes para o servidor.
                int packetSize = 255, msgSize = m.length, bytePos = 0;
                byte [] packetBuffer;
                while(true){
                    if(msgSize < packetSize) { //está proximo do final da msg.
                        packetSize = msgSize;
                    }
                    
                    // separa a mensagem em pacotes de 255 bytes
                    packetBuffer = new byte[packetSize];
                    System.arraycopy(m, bytePos, packetBuffer, 0, packetSize);

                    // cria um pacote datagrama 
                    DatagramPacket request
                            = new DatagramPacket(packetBuffer, packetBuffer.length, serverAddr, serverPort);

                    // envia o pacote 
                    dgramSocket.send(request);

                    msgSize -= packetSize;
                    if(msgSize <= 0) break;
                    bytePos += packetSize;
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
