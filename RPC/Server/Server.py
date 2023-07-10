#================================================================================================
# RPCServer: Server para conexao RPC
# Descricao: Servidor RPC com conexão com o mongoDB para comunicação e transferência de dados por protocolo e gRPC
# Autores: Lucas Alexandre Seemund
# Creation Date: 7 / 7 / 2023
#================================================================================================

import pymongo
import datetime
import grpc
from concurrent import futures
from utils import criaMovies
import protocolo_pb2
import protocolo_pb2_grpc

#obtem a data do sistema altualmente
def obter_timestamp():
    agora = datetime.datetime.now()
    timestamp = agora.strftime("%Y-%m-%d %H:%M:%S.%f")
    #print(timestamp)
    #print(str(timestamp))
    return timestamp

# Precisa tratar erros acho
class MovieDatabase:
    def __init__(self):

        print("Starting Server...\nCarregando Database")
        connection_string = "mongodb+srv://Seemund:2VXv0HRMm0OlcKXk@cluster0.ureaswk.mongodb.net/?retryWrites=true&w=majority"
        
        # Autenticação com o URI de acesso ao banco
        self.client = pymongo.MongoClient(connection_string)
        db = self.client["sample_mflix"]
        self.users = db["users"]
        self.theaters = db["theaters"]
        self.sessions = db["sessions"]
        self.movies = db["movies"]  # Atribui o acesso ao banco de filmes
        self.embedded_movies = db["embedded_movies"]
        self.comments = db["comments"]
        
        print("Database Carregado")
    
    # Busca por um dado no banco
    # * key: nome do atributo
    # * data: dado do atributo
    def query(self, key, data):
        # Dict com os dados para a busca
        query = {key: data}

        # Retorna lista de elementos encontrados
        return list(self.movies.find(query))
    
    def new_package_data(self, title, plot, fullplot, cast=[], released = "1999-10-20 00:00:00.000000"):
        year = 1999
        if released != type("str"):
            released = "1999-10-20 00:00:00.000000"

        #cria o novo package
        data = {
            "plot": plot,
            "genres": [],
            "runtime": 1,
            "cast": cast,
            "num_mflix_comments": 0,
            "title": title,
            "fullplot": fullplot,
            "countries": [],
            "released": released,
            "directors": [],
            "rated": "UNRATED",
            "lastupdated": obter_timestamp(),
            "year": year,
            "type": "movie"
        }
        return data

    # Criação de um dado novo no banco (filme)
    # * packed_data: dados empacotados para inserção
    def create(self, packed_data):
        name = [
            "plot",
            "genres",
            "runtime",
            "cast",
            "num_mflix_comments",
            "title",
            "fullplot",
            "countries",
            "released",
            "directors",
            "rated",
            "lastupdated",
            "year",
            "type"
        ]

        # Dict para a inserção de um filme novo
        new_data = {}

        # Cada dado do packed_data é inserido no dict new_data
        for key, data in packed_data.items():
            # Verifica se a chave é válida para inserção
            if key not in name:
                continue
            new_data[key] = data
        
        # Insere o dict na base de dados
        result = self.movies.insert_one(new_data)

        # ID de inserção para verificar o funcionamento da operação
        if result.inserted_id:
            return True
        return False

    # Atualiza um dado no banco
    # * movie_name: nome do filme
    # * new_data: lista de dois elementos com dados novos
    def update(self, movie_name, new_data):
        # Busca o filme pelo nome
        movie = self.query("title", movie_name)

        updateList = {
            "plot": new_data.plot,
            "runtime": new_data.runtime,
            "title": new_data.title,
            "fullplot": new_data.fullplot,
            "released": new_data.released,
            "rated": new_data.rated,
            "year": new_data.year,
            "type": new_data.type
        }

        try:
            # Dict para a execução da atualização
            for k, d in updateList.items():
                update = {'$set': {k : d}}
                
                # Atualiza os dados do filme no banco    
                result = self.movies.update_one({"_id": movie[0]["_id"]}, update)
            update = {'$set': {"lastupdated" : obter_timestamp()}}  
            result = self.movies.update_one({"_id": movie[0]["_id"]}, update)
        except Exception as e:
            print(e)

        # Retorna número de elementos modificados
        return result.modified_count

    # Remove um filme do banco
    # * movie_name: nome do filme
    def remove(self, movie_name):
        try:
            # Busca o filme pelo nome
            movie = self.query("title", movie_name)

            # Remove o filme do banco
            result = self.movies.delete_one(movie[0])

            # Retorna o número de elementos modificados
            return result.deleted_count
        except Exception as e:
            print(e)
            print("Error aqui")
            return 0
    
    def movies_by_Actor(self, actor):
        # Criar a consulta para encontrar filmes com o ator
        query = {"cast": {"$in": [actor]}}

        # Executar a consulta na coleção
        filmes = self.movies.find(query)

        # Retorna os resultados
        listaFilmes = []
        for filme in filmes:
            filmesR = {}
            for key, data in filme.items():
                if key == 'imdb' or key == 'tomatoes' or key == 'awards':
                    continue
                filmesR[key] = data
            listaFilmes.append(filmesR)
        return listaFilmes

    def movies_by_Category(self, category):
        # Criar a consulta para encontrar filmes com a string no campo "genres"
        query = {"genres": {"$in": [category]}}

        # Executar a consulta na coleção
        filmes = self.movies.find(query)

        # Retorna os resultados
        listaFilmes = []
        for filme in filmes:
            filmesR = {}
            for key, data in filme.items():
                if key == 'imdb' or key == 'tomatoes' or key == 'awards':
                    continue
                filmesR[key] = data
            listaFilmes.append(filmesR)
        return listaFilmes

    def movie_by_title(self, title):
        query = {"title": title}

        # Executar a consulta na coleção
        filmes = self.movies.find(query)

        # Retorna os resultados
        listaFilmes = []
        for filme in filmes:
            filmesR = {}
            for key, data in filme.items():
                if key == 'imdb' or key == 'tomatoes' or key == 'awards':
                    continue
                filmesR[key] = data
            listaFilmes.append(filmesR)
        return listaFilmes

# Cria uma instância do objeto Database
database = MovieDatabase()

# Classe que implementa as funções do servidor
class TesteService(protocolo_pb2_grpc.TesteServiceServicer):
    
    #========================================================
    # Procura um filme pelo seu nome
    def findtitleMsg(self, request, context):
        lista = database.movie_by_title(request.title) # busca no database
        lista = lista[0] # vai ignorar o resto da lista pq sim (era para os nomes serem todos diferentes e nao ter 1 igual ao outro)

        try:
            response = criaMovies(lista) # passa as informações do filme para a função do grpc
        except Exception as e:
            print(e)
        
        return response
    
    #========================================================
    # Retorna todos os filmes com tal categoria
    def findcategoryMsg(self, request, context):
        lista = database.movies_by_Category(request.category) # busca no database
        
        # prepara a informação para enviar para o cliente
        response = protocolo_pb2.FindResponse()
        for mov in lista:
            try:
                response.movies.append(criaMovies(mov))
            except Exception as e:
                print(e)

        #envia para o cliente enpacotado no FindResponse
        return response
    
    #========================================================
    # Retorna todos os filmes com tal ator
    def findactorMsg(self, request, context):
        lista = database.movies_by_Actor(request.actor) # busca no database

        # prepara a informação para enviar para o cliente
        response = protocolo_pb2.FindResponse()
        for mov in lista:
            try:
                response.movies.append(criaMovies(mov))
            except Exception as e:
                print(e)
        
        #envia para o cliente enpacotado no FindResponse
        return response

    #========================================================
    # Cria um filme no database
    def createMsg(self, request, context):
        #faz o empacotamento da mensagem e adiciona informações faltantes
        aux = database.new_package_data(request.title, request.plot, request.fullplot)

        if database.create(aux): # verifica se deu tudo certo
            print("Created movie")
        else:
            print("Failed to create movie")
        
        # procura no database o filme que foi criado
        lista = database.movie_by_title(request.title) # busca no database
        lista = lista[0]

        try:
            response = criaMovies(lista) # passa as informações do filme para a função do grpc
        except Exception as e:
            print(e)

        print(response)
        return response

    
    def updateMsg(self, request, context):
        try:
            if database.update(request.title, request.movies) > 0:
                return request.movies
            return database.new_package_data("A","A","A")
        except Exception as e:
            print(e)
            return database.new_package_data("A","A","A")

    def deleteMsg(self, request, context):
        try:
            if database.remove(request.title) > 0:
                return protocolo_pb2.Response(msg="DELETED")
            else:
                return protocolo_pb2.Response(msg="ERROR")
        except Exception as e:
            print(e)

        
# Função principal do servidor
def main():
    import time
    # Cria o servidor gRPC
    server = grpc.server(futures.ThreadPoolExecutor(max_workers=10))

    # Adiciona classe de serviço ao servidor
    protocolo_pb2_grpc.add_TesteServiceServicer_to_server(TesteService(), server)

    # Inicia o servidor na porta 50051
    print('Starting server. Listening on port 50051.')
    server.add_insecure_port('[::]:50051')
    server.start()
    try:
        while True:
            time.sleep(1)
    except KeyboardInterrupt:
        server.stop(0)
    

if __name__ == '__main__':
    main()
