package SD_T2;

/**
 * UDPClient: Cliente UDP Descricao: Envia uma msg em um datagrama e recebe a
 * mesma msg do servidor
 */
import java.net.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/** import do javax */
import java.awt.*;
import javax.swing.*;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/* import para o sha512 */
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UDPClient {
    /* https://stackoverflow.com/a/33085670 */
	public static String get_SHA_512_SecurePassword(String passwordToHash, String salt) {
		String generatedPassword = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			md.update(salt.getBytes(StandardCharsets.UTF_8));
			byte[] bytes = md.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			generatedPassword = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return generatedPassword;
	}

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

            //cria o buffer para receber o datagrama
            byte [] bufferRecive = new byte[256];
            DatagramPacket dgrambufferRecive = new DatagramPacket(bufferRecive, bufferRecive.length);

            //127.0.0.1 6666
            // String dstIP = JOptionPane.showInputDialog("IP Destino?");
            // int dstPort = Integer.parseInt(JOptionPane.showInputDialog("Porta Destino?"));
            String dstIP = "127.0.0.1";
            int dstPort = 6666;

            /* armazena o IP do destino */
            InetAddress serverAddr = InetAddress.getByName(dstIP);
            int serverPort = dstPort; // porta do servidor

            /** pega o user e senha */
            JTextField JTFLogin = new JTextField(10);
            JTextField JTFPassword = new JTextField(10);
            JPanel myPanel = new JPanel();
            myPanel.setLayout(new BoxLayout(myPanel,BoxLayout.Y_AXIS));
            //myPanel.setAlignmentX(LEFT_ALIGNMENT);

            myPanel.add(new JLabel("Digite o usuario: "));
            myPanel.add(JTFLogin);
            //myPanel.add(Box.createVerticalStrut(15)); // a \n
            myPanel.add(new JLabel("Digite a senha: ")); //.setAlignmentX(LEFT_ALIGNMENT)
            myPanel.add(JTFPassword);

            String login, Password;
            if (JOptionPane.showConfirmDialog(null, myPanel,"Digite o usuario e a senha:",
            JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION){
                login = JTFLogin.getText().replace(" ", "");
                Password = JTFPassword.getText().replace(" ", "");
            }
            else{
                dgramSocket.close();
                return;
            }
            //String login = JOptionPane.showInputDialog("Digite o usuario e a senha: ");

			String key;

			key = get_SHA_512_SecurePassword(Password, "TheOnePieceIsReal").replace(" ", "");
            String user = "CONNECT " + login + " " + key;
            byte [] userBytes = user.getBytes(); // transforma o login em bytes
            // cria um pacote datagrama do login.
            DatagramPacket userRequest
                = new DatagramPacket(userBytes, userBytes.length, serverAddr, serverPort);

            // envia o pacote do login.
            dgramSocket.send(userRequest);
            // fazer com que receba se o login esta correto
            //dgramSocket.receive(dgrambufferRecive);
            //byte[] receive = new byte[dgrambufferRecive.getBytes().length]; // transforma a mensagem em bytes
            //System.arraycopy(dgrambufferRecive.getBytes(), 0, receive, 0, dgrambufferRecive.getBytes().length);


            do {
                String msg = JOptionPane.showInputDialog("Digite a mensagem:");

                Byte msgtype = 0;

                List<String> protocolList;
                protocolList = Arrays.asList(msg.split(" "));

                // verifica se a mensagem é um ECHO, URL ou EMOJI
                if(protocolList.get(0).equals("ECHO")){
                    msgtype = 1; // 1 = ECHO
                    msg = Arrays.asList(msg.split(" ", 2)).get(1);
                }
                else if(isURL(protocolList.get(0))){
                    msgtype = 2; // 2 = URL
                }
                else if(stringIsEmoji(protocolList.get(0))){
                    msgtype = 3; // 3 = EMOJI
                }
                else{
                    msgtype = 4; // 4 = Mensagem normal
                }

                /** Cria o buffer com o formato da mensagem */
                byte[] m = new byte[msg.getBytes().length]; // transforma a mensagem em bytes
                System.arraycopy(msg.getBytes(), 0, m, 0, msg.getBytes().length);
                //System.arraycopy(a, 0, c, 0, a.length);
                //System.arraycopy(b, 0, c, a.length, b.length);


                int msgSize = 255, currentSize = m.length, bytePos = 0;
                byte [] buffer;
                byte [] packetBuffer;
                byte [] nick = new byte[64];

                nick = login.getBytes();
                int nickSize = nick.length;
                // envia pacotes para o servidor.
                /*
                1 = tipo da mensagem
                1 = tamanho do apelido
                1-64  nickSize
                1 = tamanho da mensagem
                0-255  msgSize
                 */
                while(true){
                    if(currentSize < msgSize) { //está proximo do final da msg.
                        msgSize = currentSize;
                    }

                    // separa a mensagem em pacotes de 255 bytes
                    packetBuffer = new byte[msgSize];
                    System.arraycopy(m, bytePos, packetBuffer, 0, msgSize);

                    // cria um buffer com o tipo da mensagem, tam apelido, apelido, tamanho e conteudo da mensagem
                    byte nickSizeByte = (byte) nickSize;
                    byte msgSizeByte = (byte) msgSize;
                    buffer = new byte[1 + 1 + nickSizeByte + 1 + msgSizeByte];

                    // popular o buffer
                    buffer[0] = msgtype;
                    buffer[1] = nickSizeByte;
                    System.arraycopy(nick, 0, buffer, 2, nickSizeByte);
                    buffer[2 + nickSizeByte] = msgSizeByte;
                    System.arraycopy(packetBuffer, 0, buffer, 3 + nickSizeByte, msgSizeByte);

                    // cria um pacote datagrama
                    DatagramPacket request
                            = new DatagramPacket(buffer, buffer.length, serverAddr, serverPort);

                    // envia o pacote
                    dgramSocket.send(request);

                    currentSize -= msgSize;
                    if(currentSize <= 0) break;
                    bytePos += msgSize;
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
