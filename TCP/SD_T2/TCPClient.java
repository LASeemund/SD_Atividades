/**
 * TCPClient: Cliente para conexao TCP
 * Descricao: Envia uma informacao ao servidor
 * Ao enviar "PARAR", a conexão é finalizada.
 * Autores: Lucas Alexandre Seemund
 * Creation Date: 9 / 7 / 2023
 */

package SD_T2;

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
		boolean inMsg = false; // flag para indicar se a conexao deve continuar

		//solicitação
		byte messageType;
		byte command;
		byte fileNameSize;
		byte[255] fileName;

		byte resp;
		byte command;
		byte status;

		ByteBuffer bb = ByteBuffer.allocate(4).order(ByteOrder.BIG_ENDIAN);
		bb.putShort(a.a).put(a.b).putInt(a.c).flip();
		byte[] buffer = bb.array();

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
			if (buffer.replace(" ","").equals("ERROR")) {
				System.out.println("ERROR: senha errada");
			}
			boolean loop = false;
			if (buffer.replace(" ","").equals("SUCCESS")) {
				System.out.println("Logado");
				loop = true;
			}
			String packageName = "SD_T2";

			System.out.print("Comandos:\nPWD\nCHDIR path\nGETFILES\nGETDIRS\nADDFILE path\nDELETE path\nGETFILESLIST\nGETFILE path\nEXIT\n");
			/* protocolo de comunicação */
			while (loop) {
				System.out.print("$ ");
				buffer = reader.nextLine().trim(); // lê mensagem via teclado
				bufferList = Arrays.asList(buffer.split(" "));
				//PWD
				if(buffer.equals("PWD")){
					out.writeUTF(buffer); // envia a mensagem para o servidor
					buffer = in.readUTF(); // aguarda resposta para PWD
					System.out.println(">" + buffer);
				}
				else if(bufferList.get(0).equals("CHDIR")){  //CHDIR
					if(bufferList.size() < 2){
						System.out.println("ERROR NULL DIRECTORY");
					}
					else{
						out.writeUTF(buffer); // envia a mensagem para o servidor
						buffer = in.readUTF(); // aguarda resposta do servidor para o comando CHDIR
						if(buffer.replace(" ","").equals("SUCCESS")){
							/* faz o comando PWD depois do SUCCESS para saber o path do cliente */
							out.writeUTF("PWD");
							buffer = in.readUTF();
							System.out.println(">" + buffer);
							//System.out.println(">" + bufferList.get(1));
						}
						else{
							System.out.println("Arquivo não existe.");
						}
					}
				}
				else if(buffer.equals("GETFILES")){  //GETFILES
					out.writeUTF(buffer); // envia a mensagem para o servidor
					buffer = in.readUTF(); // aguarda resposta do servidor
					System.out.println("=========================================");
					System.out.println("Quantidade de arquivos: " + buffer);
					System.out.println("=========================================");
				}
				else if(buffer.equals("GETDIRS")){  //GETDIRS
					out.writeUTF(buffer); // envia a mensagem para o servidor
					buffer = in.readUTF(); // aguarda resposta do servidor
					System.out.println("=========================================");
					System.out.println("Quantidade de diretorios: " + buffer);
					System.out.println("=========================================");
				}
				// Questao 2 ========================================================================
				else if (bufferList.get(0).equals("ADDFILE")) {
                    if (bufferList.size() < 2) {
                        System.out.println("ERROR NULL FILENAME");
                    } else {
                        String filePath = bufferList.get(1);
                        File file = new File(filePath);

                        if (!file.exists()) { //verifica se o arquivo existe
                            System.out.println("Error: File " + filePath + " not found.");
                        } else {
                            out.writeUTF(bufferList.get(0) + " " + file.getName());
                            buffer = in.readUTF(); //recebe se pode enviar o arquivo
                            if (buffer.replace(" ","").equals("ADDFILE_OK")) {
								
								//envio do arquivo
								FileInputStream fileInputStream = new FileInputStream(file);
								BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
								OutputStream outputStream = clientSocket.getOutputStream();

								byte[] bufferB = new byte[4096];
								int bytesRead;
								while ((bytesRead = bufferedInputStream.read(bufferB)) > 0) {
									outputStream.write(bufferB, 0, bytesRead);
									outputStream.flush();
								}

                                System.out.println("File " + filePath + " added");
                            } else {
                                System.out.println("Error adding file: " + filePath);
                            }
                        }
                    }
                }
				else if (bufferList.get(0).equals("DELETE")) {
                    if (bufferList.size() < 2) {
                        System.out.println("ERROR NULL FILENAME");
                    } else {
						//envia o comando do delete
                        String filename = bufferList.get(1);
                        out.writeUTF(buffer);
                        buffer = in.readUTF();
						//verifica se foi deletado
                        if (buffer.replace(" ","").equals("SUCCESS")) {
                            System.out.println("File " + filename + " deleted");
                        } else {
                            System.out.println("Error deleting file: " + filename);
                        }
                    }
                }
                else if (bufferList.get(0).equals("GETFILESLIST")) {
					//envia o comando
                    out.writeUTF(buffer);
                    buffer = in.readUTF();
                    System.out.println("=========================================");
                    System.out.println("Lista de arquivos:");
                    System.out.println(buffer);//mostra os arquivos recebidos
                    System.out.println("=========================================");
                }
                else if (bufferList.get(0).equals("GETFILE")) {
                    if (bufferList.size() < 2) {
                        System.out.println("ERROR NULL FILENAME");
                    } else {
						//envia o comando
                        String filePath = bufferList.get(1);
                        out.writeUTF(buffer);
                        buffer = in.readUTF();
                        if (buffer.replace(" ","").equals("SUCCESS")) { //recebe se pode esperar o arquivo
							
							// cria e recebe o arquivo
                            FileOutputStream fos = new FileOutputStream(filePath);
                            byte[] fileBuffer = new byte[1024];
                            int bytesRead;

                            while ((bytesRead = in.read(fileBuffer)) != -1) {
                                fos.write(fileBuffer, 0, bytesRead);
								// Verifica se não há mais dados disponíveis no fluxo de entrada
								if (in.available() == 0) {
									break;
								}
                            }

                            fos.close(); //fecha o fileoutput
                            System.out.println("File " + filePath + " downloaded");
                        } else {
                            System.out.println("Error downloading file: " + filePath);
                        }
                    }
                }
				else if(buffer.equals("EXIT")){  //EXIT
					out.writeUTF(buffer);
					break;
				}
				else{ //caso não for protocolo é mensagem.
					// System.out.println("Protocolo indevido.");
					System.out.println(buffer);
				}
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
