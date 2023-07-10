#================================================================================================
# RPCUtils
# Descricao: Uma função feita para a tratar os erros do movies
# Autores: Lucas Alexandre Seemund
# Creation Date: 7 / 7 / 2023
#================================================================================================


import protocolo_pb2
# função para facilitar na criação de uma mensagem Movies do gRPC
def criaMovies(data):
    try:
        dataReleased = data["released"].strftime("%Y-%m-%d %H:%M:%S.%f")
        if type(dataReleased) != type("string"):
            dataReleased = "1999-10-20 00:00:00.000000"
    except:
        dataReleased = "1999-10-20 00:00:00.000000"
    try:
        full = data["fullplot"]
        if type(full) != type("string"):
            full = "."
    except:
        full = "."
    try:
        r = data["rated"]
        if type(r)!= type("string"):
            r = "UNRATED"
    except:
        r = "UNRATED"
    try:
        plot = data["plot"]
        if type(plot)!= type("string"):
            plot = "."
    except:
        plot = "."
    try:
        runtime = data["runtime"]
        if type(runtime)!= type(1):
            runtime = 1
    except:
        runtime = 1
    try:
        mflix = data["num_mflix_comments"]
        if type(mflix)!= type(1):
            mflix = 1
    except:
        mflix = 0
    try:
        title = data["title"]
        if type(title)!= type("string"):
            title = "."
    except:
        title = "."
    try:
        lastUp = data["lastupdated"]
        if type(lastUp)!= type("string"):
            lastUp = "1999-10-20 00:00:00.000000"
    except:
        lastUp = "1999-10-20 00:00:00.000000"
    try:
        year = data["year"]
        if type(year)!= type(1):
            year = 1999
    except:
        year = 1999
    try:
        typ = data["type"]
        if type(typ)!= type("string"):
            typ = "movie"
    except:
        typ = "movie"

    # prepara a mensagem para enviar para o cliente
    aux = {
        "plot": plot,
        "runtime": runtime,
        "num_mflix_comments": mflix,
        "title": title,
        "fullplot": full,
        "released": dataReleased,
        "rated": r,
        "lastupdated": lastUp,
        "year": year,
        "type": typ
    }
    try:
        aux["genres"] = data["genres"]
        if type(aux["genres"])!= type([]):
            if type(aux["genres"])== type("string"):
                aux["genres"] = [aux["genres"]]
    except:
        aux["genres"] = []
    try:
        aux["cast"] = data["cast"]
        if type(aux["cast"])!= type([]):
            if type(aux["cast"])== type("string"):
                aux["cast"] = [aux["cast"]]
    except:
        aux["cast"] = []
    try:
        aux["countries"] = data["countries"]
        if type(aux["countries"])!= type([]):
            if type(aux["countries"])== type("string"):
                aux["countries"] = [aux["countries"]]
    except:
        aux["countries"] = []
    try:
        aux["directors"] = data["directors"]
        if type(aux["directors"])!= type([]):
            if type(aux["directors"])== type("string"):
                aux["directors"] = [aux["directors"]]
    except:
        aux["directors"] = []
    return protocolo_pb2.Movie(**aux)