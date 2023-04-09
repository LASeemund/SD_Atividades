package SD_T1;

/**
 * TCPClient: Cliente para conexao TCP
 * Descricao: Envia uma informacao ao servidor e recebe confirmações ECHO
 * Ao enviar "PARAR", a conexão é finalizada.
 */

import java.net.*;
import java.io.*;
import java.util.Scanner;
import java.util.Arrays;
import java.util.List;

/* import para o sha512 */
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class TCPClient {
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

	public static void main(String args[]) {
		Socket clientSocket = null; // socket do cliente
		Scanner reader = new Scanner(System.in); // ler mensagens via teclado

		try {
			/* Endereço e porta do servidor */
			int serverPort = 6666;
			InetAddress serverAddr = InetAddress.getByName("127.0.0.1");

			/* conecta com o servidor */
			clientSocket = new Socket(serverAddr, serverPort);

			/* cria objetos de leitura e escrita */
			DataInputStream in = new DataInputStream(clientSocket.getInputStream());
			DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());

			String buffer = "";

			System.out.print("Digite o usuario e a senha. (User, Password): ");
			buffer = reader.nextLine();

			/** separa o User do Passowrd */
			List<String> bufferList;
			String key;
			/** separa por uma ',' ou apenas por espaço */
			try{
				bufferList = Arrays.asList(buffer.split(","));
				key = bufferList.get(1); /** para verficar se tem , na get(1) */
			}catch(ArrayIndexOutOfBoundsException exception){
				bufferList = Arrays.asList(buffer.split(" "));
			}
			key = get_SHA_512_SecurePassword(bufferList.get(1).replace(" ", ""), "OnePiece");
			out.writeUTF("CONNECT " + bufferList.get(0) + " " + key.replace(" ", ""));

			buffer = in.readUTF();
			if (buffer.equals("ERROR")) {
				System.out.print("ERROR: senha errada");
			}
			boolean loop = false;
			if (buffer.equals("SUCCESS")) {
				System.out.println("Logado");
				loop = true;
			}

			/* protocolo de comunicação */
			while (loop) {
				System.out.print("Mensagem: ");
				buffer = reader.nextLine(); // lê mensagem via teclado

				out.writeUTF(buffer); // envia a mensagem para o servidor

				if (buffer.equals("PARAR"))
					break;

				buffer = in.readUTF(); // aguarda resposta do servidor

				System.out.println("Server disse: " + buffer);
			}
		} catch (UnknownHostException ue) {
			System.out.println("Socket:" + ue.getMessage());
		} catch (EOFException eofe) {
			System.out.println("EOF:" + eofe.getMessage());
		} catch (IOException ioe) {
			System.out.println("IO:" + ioe.getMessage());
		} finally {
			try {
				clientSocket.close();
			} catch (IOException ioe) {
				System.out.println("IO: " + ioe);
				;
			}
		}
	} // main
} // class
