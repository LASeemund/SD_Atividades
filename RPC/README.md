Aluno: Lucas Alexandre Seemund

--> Descrição:
    Programa para comunicação TCP entre Cliente e Servidor com linguagens de programação diferentes
    que utilizam RPC para se comunicar.

--> Dependências
    
    -> Python:
        - pymongo -> {pip install pymongo}
        - gRPC -> { python -m pip install grpcio }
        - protoPYTHON -> { pip install grpcio-tools }

    -> Java:
        Apache Maven 3.9.3

--> Como executar:
    Gerar Protobuf:
        python -m grpc_tools.protoc -I./Client/src/main/proto --python_out=./Server --pyi_out=./Server --grpc_python_out=./Server ./Client/src/main/proto/protocolo.proto
    
    Cliente:
        cd Client
        mvn compile
        mvn exec:java -Dexec.mainClass="RPCClient.Client"

    Servidor:
        cd Server
        python Server.py

--> Exemplo de uso:
    -> Client:
        -> Procura um filme pelo titulo
            FindTitle
            Envia um nome de um filme e retorna as informacoes do filme
        
        -> Procura filmes por categoria
            FindCategory
            Envia uma categoria e retorna uma lista dos filmes com a categoria
        
        -> Procura filmes por ator
            FindActor
            Envia o nome de um ator e retorna uma lista dos filmes com o ator
        
        -> Criar filme
            Create
            Envia o titulo, o plot, o fullplot, e o elenco,
            e é retornado as informacoes do filme
        
        -> Update filme
            Update
            É enviado o nome do filme com a função FindTitle e com isso o cliente altera o que for desejado,
            apos isso, é enviado as informacoes do filme e é retornado as informacoes do filme. Se recebeu tudo igual é por que foi atualizado no database
        
        -> Remove um filme
            Remove
            É enviado o nome do filme e é retornado apenas uma string de se foi deletado ou não ("DELETED","ERROR").
            
    -> Servidor
        -> Ele não tem nenhum comando proprio, ele só recebe e responde comandos do cliente

    -> Teste
        FindTitle
            Blacksmith Scene
        FindCategory
            Drama
        FindActor
            Martin Fuller
