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
	private String _path;
    private String _root;
    
	public Path(String str){
        this._root = "SD_T1/root";
		this._path = (str);
        File path = new File(this._root);
        if(!path.exists()){
            path.mkdir();
        }
    }
	public void addPath(String str){
        if(str.equals("..")){
            for (int i = this._path.length()-2; i > 0; i = i-1) {
                if(this._path.charAt(i) == ('\\')){
                    this._path = this._path.substring(0,i);
                    break;
                }
            }
            for (int i = this._root.length()-2; i > 0; i = i-1) {
                if(this._root.charAt(i) == ('\\')){
                    this._root = this._root.substring(0,i);
                    break;
                }
            }
        }
        else{
            this._path = (this._path + str + "/");
            this._root = (this._root + str);
        }
	}
	public String getPath(){
		return this._path;
	}
    public String getRootPath(){
		return this._root;
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
    String currentDir = packageName;

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
                List<String> bufferData = Arrays.asList(buffer.split(" "));
                if(buffer.replace(" ","").equals("PWD")){
                    /* Devolve o caminho corrente (PATH) usando String UTF separando os diretórios por barra(/). */
                    out.writeUTF(this._path.getPath());
                    System.out.println("User: " + userName + " is at " + this._path.getPath());
                }
                else if(bufferData.get(0).equals("CHDIR")){
                    /* Acessa o diretório que o usuário digitar junto com o comando CHDIR */
                    if(bufferData.size() < 2){
                        System.out.println("Error: Null Directory.");
                        out.writeUTF("ERROR NULL DIRECTORY");
                    }
                    else{
                        File directoryCHDIR = new File(this._path.getPath() + bufferData.get(1)); 
                        if(directoryCHDIR.exists()){ 
                            currentDir = currentDir + this._path.getPath() + bufferData.get(1) + "/";
                            Paths.get(buffer).toAbsolutePath().normalize(); /* normaliza o caminho */
                            System.out.println("User: " + userName + " is at " + currentDir);
                            out.writeUTF("SUCCESS");
                        }
                        else{
                            System.out.println("Error: Directory " + directoryCHDIR + " not found.");
                            out.writeUTF("ERROR");
                        }
                    }
                }
                else if(bufferData.get(0).equals("GETFILES")){
                    File directoryfiles = new File(this._path.getRootPath()); 
                    String listBuffer = "";
                    /* lista os arquivos do diretório corrente */
                    if(directoryfiles.isDirectory()){
                        /* lista somente os arquivos do diretório corrente */
                        File[] files = directoryfiles.listFiles(
                            new FileFilter() {
                                @Override
                                public boolean accept(File file) {
                                    return file.isFile();
                                }
                            }
                        );
                        if(files.length > 0){ // se existir arquivos
                          // System.out.println("Number of files: " + files.length);
                          for(File file : files) {
                              listBuffer = listBuffer + "\n" + file.getName();
                          }
                          listBuffer = listBuffer + "\n";
                          // System.out.println(listBuffer);
                        }
                        else{
                          System.out.println("nao tem arquivos");
                          listBuffer = "\nnão tem arquivos no diretorio\n";
                        }
                        // out.writeUTF("SUCCESS");
                        out.writeUTF(files.length + listBuffer);
                        System.out.println("Quantidade arquivos: " + files.length + listBuffer);
                    }
                    else{ //não é diretório valido.
                        out.writeUTF("ERROR");
                    }
                }
                else if(buffer.replace(" ","").equals("GETDIRS")){
                    File directoryGETDIRS = new File(this._path.getRootPath());
                    String listBuffer = "";
                    System.out.println(directoryGETDIRS.list().length);
                    File[] files = directoryGETDIRS.listFiles(
                        new FileFilter() {
                            @Override
                            public boolean accept(File file) {
                                return file.isDirectory();
                            }
                        }
                    );
                    //System.out.println(Arrays.toString(list.toArray(directoryGETDIRS.list())));
                    if(files.length > 0){
                        //System.out.println(directoryGETDIRS.list());
                        for (File pathname : files) {
                            listBuffer = listBuffer + "\n" + pathname.getName();
                        }
                        listBuffer = listBuffer + "\n";
                        System.out.println(listBuffer);
                    }
                    else{
                        System.out.println("nao tem diretorios.");
                        listBuffer = "\nnão tem diretorios\n";
                    }
                    //out.writeUTF(directoryGETDIRS.list().length + listBuffer);
                    out.writeUTF(files.length + listBuffer);
                }
                else if(bufferData.get(0).equals("DELETE")){
                    File myObj = new File(bufferData.get(1)); 
                    if (myObj.delete()) {
                        out.writeUTF("SUCCESS");
                    } else {
                        out.writeUTF("ERROR");
                    }
                }
                else if(buffer.replace(" ","").equals("EXIT")){
                    System.out.println("User: " + userName + " disconect.");
                    // in.close();
                	// out.close();
                	// clientSocket.close();
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
