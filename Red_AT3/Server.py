import pymongo
from pymongo import MongoClient
from pymongo.server_api import ServerApi

# Connect to MongoDB
uri = "mongodb+srv://mnakao:2312135@theonepieceisreal.5btci82.mongodb.net/?retryWrites=true&w=majority"
client = MongoClient(uri, server_api=ServerApi('1'))
db = client["Teste"]

# Create a collection
collection = db['ColetaneaDeTestes']

# CRUD

# Create
collection.insert_one({"nome": "Mauricio", "idade": 20})
# collection.insert_many([{"nome": "Mauricio", "idade": 20}, {"nome": "Mauricio", "idade": 20}])

# Read
# print(collection.find_one({"nome": "Mauricio"}))
# print(collection.find({"nome": "Mauricio"}))

# Update
# collection.update_one({"nome": "Mauricio"}, {"$set": {"nome": "Mauricio Nakao"}})
# collection.update_many({"nome": "Mauricio"}, {"$set": {"nome": "Mauricio Nakao"}})

# Delete
# collection.delete_one({"nome": "Mauricio Nakao"})
# collection.delete_many({"nome": "Mauricio Nakao"})