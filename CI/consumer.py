#!/usr/bin/env python3
#-----------------------------------------------------------------------
# Autores: Gustavo Sengling Favaro e Lucas Alexandre Seemund
# Data de criação: 09/07/2023
# Data da última atualização: 10/07/2023
#-----------------------------------------------------------------------
""" Implementação do consumidor de mensagens do twitter utilizando RabbitMQ """
#-----------------------------------------------------------------------

import pika

# Imprime o tweet no console
def print_tweet(ch, method, properties, body):
    tweet = body.decode('utf-8')
    topic = method.routing_key
    print(f'Topic: {topic}\n{tweet}\n')

# Configurações do RabbitMQ
rabbitmq_host = 'localhost'

# Conectando-se ao RabbitMQ
connection = pika.BlockingConnection(pika.ConnectionParameters(host=rabbitmq_host))
channel = connection.channel()

# Recebe a entrada do usuário
while True:
    try:
        # Recebe os tópicos por input
        wished_topics = input('Inform us what topics you would like to receive tweets about: ').split(', ')
        
        channel.exchange_declare(exchange='topic_logs', exchange_type='topic')

        result = channel.queue_declare('', exclusive=True)
        queue_name = result.method.queue

        for topic in wished_topics:
            channel.queue_bind(exchange='topic_logs', queue=queue_name, routing_key=topic)
        
        channel.basic_consume(queue=queue_name, on_message_callback=print_tweet, auto_ack=True)

        # Consumidor se conectou aos tópicos com sucesso, sai do loop de requisição
        break  

    except Exception as e:
        # Exibe os erros e repete o processo até que o usuário insira uma entrada válida
        print(str(e))

try:
    # Iniciando o consumo de mensagens
    print("Receiving messages...")
    channel.start_consuming()

except KeyboardInterrupt:
    # Capturando o KeyboardInterrupt (Ctrl+C) para sair do loop
    print("Keyboard Interrupt: stopping message receiving")
    channel.stop_consuming()

# Fechando a conexão
connection.close()