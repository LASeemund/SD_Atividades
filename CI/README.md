Atividade Comunicação Indireta
Autores: Gustavo Sengling Favaro e Lucas Alexandre Seemund
Data de criação: 09/07/2023

Como executar: 
Abra três terminais no diretório dos arquivos e instale a lib pika e pandas (pip install pika, pandas)
    1. Em um terminal, inicializar o classificador
    - python classifier.py

    2. Em outro terminal, inicializar o consumidor
    - python consumer.py
    - informar os tópicos que deseja consumir (politics, soccer, tv_shows)

    3. Em outro terminal, inicializar o coletor
    - python collector.py

Bibliotecas utilizadas:
- Pandas: leitura da base de dados do Twitter (twitter_dataset.csv)
- Pika: execução do messaging queuing RabbitMQ