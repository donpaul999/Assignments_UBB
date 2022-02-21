import json

import asyncio
import websockets

from flask import Flask, jsonify, request
from flask_cors import CORS

app = Flask(__name__)
CORS(app)

def get_id():
    with open('data/books.txt', 'r') as file:
        data = file.read()
        books = json.loads(data)
        return int(books[-1]['id']) + 1


bookID = get_id()


@app.route('/')
def hello_world():
    return 'Book a Book'


@app.route('/books', methods=['GET'])
def get_books():
    with open('data/books.txt', 'r') as file:
        data = file.read()
        books = json.loads(data)
        return jsonify(books)


@app.route('/book/<id>', methods=['GET'])
def get_book(id):
    id = int(request.view_args['id'])
    with open('data/books.txt', 'r') as file:
        data = file.read()
        books = json.loads(data)
        for book in books:
            if int(book['id']) == id:
                return jsonify(book)
        return jsonify({'error': 'book not found'})


@app.route('/books', methods=['POST'])
def add_book():
    global bookID
    book = json.loads(request.data)
    book['id'] = str(bookID)
    bookID += 1
    with open('data/books.txt', 'r') as file:
        data = file.read()
    if not data:
        books = [book]
    else:
        books = json.loads(data)
        books.append(book)
    with open('data/books.txt', 'w') as file:
        file.write(json.dumps(books, indent=2))
    book['id'] = int(book['id'])
    return jsonify(book)


@app.route('/books/<id>', methods=['PUT'])
def update_book(id):
    new_book = json.loads(request.data)
    updated_books = []
    with open('data/books.txt', 'r') as file:
        data = file.read()
        books = json.loads(data)
    for b in books:
        if int(b['id']) == int(new_book['id']):
            b['name'] = new_book['name']
            b['author'] = new_book['author']
            b['publishDate'] = new_book['publishDate']
            b['isBooked'] = new_book['isBooked']
        updated_books.append(b)
    with open('data/books.txt', 'w') as file:
        file.write(json.dumps(updated_books, indent=2))
    return jsonify(new_book)


@app.route('/books/<id>', methods=['DELETE'])
def delete_book(id):
    id = int(request.view_args['id'])
    updated_books = []
    with open('data/books.txt', 'r') as file:
        data = file.read();
        books = json.loads(data)
        for book in books:
            if int(book['id']) == id:
                continue
            updated_books.append(book)
    with open('data/books.txt', 'w') as file:
        file.write(json.dumps(book, indent=2))
    return jsonify()

async with websockets.serve(echo, "localhost", 8765):
    await asyncio.Future()  # run forever

asyncio.run(main())
if __name__ == '__main__':
    app.run()
    resonse_object = app.jsonify()
    resonse_object.header("Access-Control-Allow-Origin", "*");
    resonse_object.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");