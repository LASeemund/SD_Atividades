/**
 * RPCClient: Cliente para conexao RPC
 * Descricao: Arquivo do cliente para comunicação e transferência de dados por protocolo e gRPC (Protobuf)
 * Autores: Lucas Alexandre Seemund
 * Creation Date: 7 / 7 / 2023
 */

package Client;

import java.net.*;
import java.io.*;
import java.util.Scanner;
import java.util.Arrays;
import java.util.List;

/* import para o sha512 */
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class RPCClient {
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

			String buffer = "";

			buffer = reader.nextLine();

			while (loop) {
				
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
