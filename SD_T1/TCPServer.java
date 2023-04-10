package SD_T1;

/**
 * TCPServer: Servidor para conexao TCP com Threads Descricao: Recebe uma
 * conexao, cria uma thread, recebe uma mensagem e finaliza a conexao
 */
import java.net.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class TCPServer {
    public static void main(String args[]) {
        try {
            int serverPort = 6666; // porta do servidor

            /* cria um socket e mapeia a porta para aguardar conexao */
            ServerSocket listenSocket = new ServerSocket(serverPort);

            while (true) {
                System.out.println("Servidor aguardando conexao ...");

                /* aguarda conexoes */
                Socket clientSocket = listenSocket.accept();

                System.out.println("Cliente conectado ... Criando thread ...");

                /* cria um thread para atender a conexao */
                ClientThread c = new ClientThread(clientSocket);

                /* inicializa a thread */
                c.start();
            } //while

        }  catch (IOException e) {
          System.out.println("Listen socket:" + e.getMessage());
        } //catch
    } //main
} //class


class Path {
	String _path;
	public Path(String str){
		//this.path = new String(str);
		this._path = (str);
    }
	public void addPath(String str){
		//this.path = new String(this.path + str);
		this._path = (this._path + str);
	}
	public String getPath(){
		return this._path;
	}
}

/**
 * Classe ClientThread: Thread responsavel pela comunicacao
 * Descricao: Rebebe um socket, cria os objetos de leitura e escrita,
 * aguarda msgs clientes e responde com a msg + :OK
 */
class ClientThread extends Thread {
    DataInputStream in;
    DataOutputStream out;
    Socket clientSocket;
    Path _path;
    FileInputStream fis = null;
	String packageName = "SD_T1";

    public ClientThread(Socket clientSocket) {
        try {
            this.clientSocket = clientSocket;
            in = new DataInputStream(clientSocket.getInputStream());
            out = new DataOutputStream(clientSocket.getOutputStream());
            this._path = new Path("/");
        } catch (IOException ioe) {
            System.out.println("Connection:" + ioe.getMessage());
        }
    } //construtor

    /* metodo executado ao iniciar a thread - start() */
    @Override
    public void run() {
        try {
            String buffer  = "";
            boolean loop = true;
            buffer = in.readUTF(); /* aguarda ( CONNECT user, password ) */

            //verifica se o diretório do usuário existe.
            File fileUser = new File(packageName + "/users");
            if(!fileUser.exists()){
                fileUser.mkdir();
                //fileUser.close();
            }

            /* recebe o user e senha para validar */
            /* separa o buffer por espaco */
            FileWriter fileWriter;
            //FileReader fileReader;
            BufferedReader reader;
            List<String> key = Arrays.asList(buffer.split(" "));
			String userName = key.get(1).replace(" ", "");

            /*System.out.println("Key[0]: " + key.get(0));
            System.out.println("Key[1]: " + key.get(1));
            System.out.println("Key[2]: " + key.get(2).replace(" ", ""));*/

            if(key.get(0).equals("CONNECT")){
                fileUser = new File( packageName + "/users/" + userName + ".txt" );
                /* verifica a senha do user */
                if(fileUser.exists()){
                    //fileReader = new FileReader("users/" + userName + ".txt");
                    //String line = fileReader.readLine();
                    reader = new BufferedReader(new FileReader(packageName + "/users/" + userName + ".txt" ));
                    String line = reader.readLine();
                    reader.close();

                    if(!(key.get(2).replace(" ", "").equals(line.toString()))){ /* usuario errou a senha, desconectando user */
                        // log do servidor
                        System.out.println("Error: User: " + userName + " Password Invalid.");

                        // saida para para o cliente
                        out.writeUTF("ERROR");
                        in.close();
              		    out.close();
              		    clientSocket.close();
                        loop = false;
                    }
                    else{ /* usuario conectado */
                        out.writeUTF("SUCCESS");
                        System.out.println("User: " + userName + " connected");
                    }
                }
                else{ /* caso não tenha o user, cria um novo */
                    System.out.println("Create User: " + userName);
                    out.writeUTF("SUCCESS");
                    System.out.println("User: " + userName + " connected");
                    fileUser.createNewFile();
                    fileWriter = new FileWriter(packageName + "/users/" + userName + ".txt");
                    fileWriter.write(key.get(2));
                    fileWriter.close();
                }
            }
            else{ /* caso protocolo CONNECT não for encontrado desconecta o cliente */
                out.writeUTF("ERROR");
                System.out.println("Error: waiting for the CONNECT protocol");
                in.close();
                out.close();
                clientSocket.close();
                loop = false;
            }

            /* loop pimario */
            while (loop) {
                buffer = in.readUTF();   /* aguarda o envio de dados */

                if(buffer == "PWD"){
                    /* Devolve o caminho corrente (PATH) usando String UTF separando os diretórios por barra(/). */
                    out.writeUTF(this._path.getPath());
                    System.out.println("User: " + userName + " is at " + this._path.getPath());
                }
                else if(buffer.equals("CHDIR")){
                    /* Acessa o diretório que o usuário digitar apos o comando CHDIR */
                    out.writeUTF("ENTERDIR");
                    buffer = in.readUTF();  /* aguarda o envio do diretório */
                    File directoryCHDIR = new File(this._path.getPath() + buffer); 
                    if(directoryCHDIR.exists()){ 
                        Paths.get(buffer).toAbsolutePath().normalize(); /* normaliza o caminho */
                        System.out.println("User: " + userName + " is at " + this._path.getPath() + buffer + "/");
                        out.writeUTF("SUCCESS");
                    }
                    else{
                        System.out.println("Error: Directory " + directoryCHDIR + " not found.");
                        out.writeUTF("ERROR");
                    }
                }
                else if(buffer == "GETFILES"){
                    
                }
                else if(buffer == "GETDIRS"){
                    
                }
                else if(buffer == "EXIT"){
                    System.out.println("User: " + userName + " disconect.");
                    in.close();
                	out.close();
                	clientSocket.close();
                    break;
                    }
                else{
                    System.out.println("Cliente disse: " + buffer);
                    out.writeUTF("MSG: protocolo indevido");
                }
            }
        } catch (EOFException eofe) {
            System.out.println("EOF: " + eofe.getMessage());
        } catch (IOException ioe) {
            System.out.println("IOE: " + ioe.getMessage());
        } finally {
            try {
                in.close();
                out.close();
                clientSocket.close();
            } catch (IOException ioe) {
                System.err.println("IOE: " + ioe);
            }
        }
        System.out.println("Thread comunicação cliente finalizada.");
    } //run
} //class
