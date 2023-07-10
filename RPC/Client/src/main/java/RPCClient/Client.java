/**
 * RPCClient: Cliente para conexao RPC
 * Descricao: Arquivo do cliente para comunicação e transferência de dados por protocolo e gRPC (Protobuf)
 * Autores: Lucas Alexandre Seemund
 * Creation Date: 7 / 7 / 2023
 */

package RPCClient;

import java.net.*;
import java.io.*;
import java.util.Scanner;
import java.util.Arrays;
import java.util.List;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import messages.TesteServiceGrpc;
import messages.Protocolo.FindTitle;
import messages.Protocolo.Movie;
import messages.Protocolo.FindCategory;
import messages.Protocolo.FindResponse;
import messages.Protocolo.FindActor;
import messages.Protocolo.Create;
import messages.Protocolo.Update;
import messages.Protocolo.Delete;
import messages.Protocolo.Response;

/* import para o sha512 */
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Client {
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
		try {
			/* Endereço e porta do servidor */
			int serverPort = 50051;
			ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", serverPort)
                .usePlaintext()
				.maxInboundMessageSize(Integer.MAX_VALUE)
                .build();
			TesteServiceGrpc.TesteServiceBlockingStub stub = TesteServiceGrpc.newBlockingStub(channel);

			BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));

			String buffer = "";

			System.out.println("Você está conectado com o servidor.");
			boolean loop = true;
			while (loop) {
				System.out.println("Os comandos são:");
				System.out.println("FindTitle\nFindCategory\nFindActor\nCreate\nUpdate\nRemove");
				System.out.print(">");
				buffer = reader.readLine(); // espera o cliente escrever o comando
				// verifica o comando do cliente
				if (buffer.equals("FindTitle")) {
					System.out.print("Digite o nome do filme:\n>");
					String title = reader.readLine(); // input do user

					// Prepara o que vai ser enviado para o servidor
					FindTitle find = FindTitle.newBuilder()
					.setTitle(title)
					.build();

					// Envia a mensagem para o servidor e recebe a resposta
					Movie response = stub.findtitleMsg(find);
            		System.out.println(response);
				}
				else if (buffer.equals("FindCategory")){
					System.out.print("Digite a categoria do filme:\n>");
					String category = reader.readLine(); // input do user

					// Prepara o que vai ser enviado para o servidor
					FindCategory find = FindCategory.newBuilder()
					.setCategory(category)
					.build();

					// Envia a mensagem para o servidor e recebe a resposta
					FindResponse response = stub.findcategoryMsg(find);
					List<Movie> movies = response.getMoviesList();

					// printa os filmes encontrados
					for (Movie mov : movies) {
						System.out.println("================================================================");
						System.out.println(mov);
					}
				}
				else if (buffer.equals("FindActor")){
					System.out.print("Digite o nome do ator:\n>");
					String actor = reader.readLine(); // input do user

					// Prepara o que vai ser enviado para o servidor
					FindActor find = FindActor.newBuilder()
					.setActor(actor)
					.build();

					// Envia a mensagem para o servidor e recebe a resposta
					FindResponse response = stub.findactorMsg(find);
					List<Movie> movies = response.getMoviesList();

					// printa os filmes encontrados
					for (Movie mov : movies) {
						System.out.println(mov);
					}
				}
				else if (buffer.equals("Create")){
					System.out.print("Digite o nome do novo filme:\n>");
					String title = reader.readLine(); // input do user;
					System.out.print("Digite o plot do novo filme:\n>");
					String plot = reader.readLine(); // input do user;
					System.out.print("Digite o fullplot do novo filme:\n>");
					String fullplot = reader.readLine(); // input do user;

					Create find = Create.newBuilder()
					.setTitle(title)
					.setPlot(plot)
					.setFullplot(fullplot)
					.build();

					// Envia a mensagem para o servidor e recebe a resposta
					Movie response = stub.createMsg(find);
					System.out.println("\n================================================");
					System.out.println(response);
				}
				else if (buffer.equals("Update")){
					System.out.print("Digite o nome do filme:\n>");
					String title = reader.readLine(); // input do user

					// Prepara o que vai ser enviado para o servidor
					FindTitle find = FindTitle.newBuilder()
					.setTitle(title)
					.build();

					// Envia a mensagem para o servidor e recebe a resposta
					Movie response = stub.findtitleMsg(find);

					String aux;
					String plot = response.getPlot();
					String runtime = "1";
					String num_mflix_comments = "0";
					String titleM = response.getTitle();
					String fullplot = response.getFullplot();
					String rated = "TV";
					String type = "movie";
					System.out.println("Tem como editar esses valores: plot\nruntime\nnum_mflix_comments\ntitle\nfullplot\nrated\ntype");
					System.out.println("Digite exit para parar de editar");
					while (true) {
						System.out.print("> ");
						aux = reader.readLine(); // input do user
						if (aux.equals("exit")) {
                            break;
                        }
						else if (aux.equals("plot")) {
							System.out.print("Digite o novo plot do filme:\n>");
							plot = reader.readLine(); // input do user;
						}
						else if (aux.equals("runtime")) {
							System.out.print("Digite o novo runtime do filme:\n>");
							runtime = reader.readLine(); // input do user;
						}
						else if (aux.equals("num_mflix_comments")) {
							System.out.print("Digite o novo num_mflix_comments do filme:\n>");
							num_mflix_comments = reader.readLine(); // input do user;
						}
						else if (aux.equals("title")) {
							System.out.print("Digite o novo title do filme:\n>");
							titleM = reader.readLine(); // input do user;
						}
						else if (aux.equals("fullplot")) {
							System.out.print("Digite o novo fullplot do filme:\n>");
							fullplot = reader.readLine(); // input do user;
						}
						else if (aux.equals("rated")) {
							System.out.print("Digite o novo rated do filme:\n>");
							rated = reader.readLine(); // input do user;
						}
						else if (aux.equals("type")) {
							System.out.print("Digite o novo type do filme:\n>");
							type = reader.readLine(); // input do user;
						}//Integer.parseInt(
					}

					response = Movie.newBuilder()
					.mergeFrom(response)
					.setTitle(titleM)
					.setPlot(plot)
					.setRuntime(Integer.parseInt(runtime))
					.setNumMflixComments(Integer.parseInt(num_mflix_comments))
					.setFullplot(fullplot)
					.setRated(rated)
					.setType(type)
					.build();
					
					Update find2 = Update.newBuilder()
					.setTitle(title)
					.setMovies(response)
					.build();

					// Envia a mensagem para o servidor e recebe a resposta
					response = stub.updateMsg(find2);
					System.out.println(response);
				}
				else if (buffer.equals("Remove")){
					System.out.print("Digite o nome do filme que vai ser deletado:\n>");
					String title = reader.readLine(); // input do user

					Delete find = Delete.newBuilder()
					.setTitle(title)
					.build();

					Response response = stub.deleteMsg(find);
					if (response.getMsg().equals("DELETED")){
						System.out.println("O filme foi deletado!");
					}
					else if (response.getMsg().equals("ERROR")){
						System.out.println("O filme NÃO foi deletado!");
					}
					else {
						System.out.println("ERROR");
					}
				}
				else if (buffer.equals("Error")){
					System.out.println("Error");
				}
				else if (buffer == null) {
					System.out.println("Parando cliente.");
					break;
				}
			}
		} catch (UnknownHostException ue) {
			System.out.println("Socket:" + ue.getMessage());
		} catch (EOFException eofe) {
			System.out.println("EOF:" + eofe.getMessage());
		} catch (IOException ioe) {
			System.out.println("IO:" + ioe.getMessage());
		} finally {
			System.out.println("Error");
		}
	} // main
} // class
