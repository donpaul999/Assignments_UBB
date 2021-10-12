const express = require('express');
const router = express.Router();
const database = require('../sql');

router.get('/books', function(req, res, next) {
    data = JSON.parse(database.getAllBooks());
    console.log(data);
    var data2 = [];
    for (let i = 0; i < data.length; i++)
    {
        var book = data[i];
        var x = {
            id: book.id,
            name: book.name,
            author: book.author,
            publishDate: book.publishDate,
            isBooked: book.isBooked
        };
        data2.push(x)
    }
    console.log(data2);
    res.send(data2);
});


router.get('/books/:id', function(req, res, next) {
    const id = req.params.id;
    database.getBookById(id).toArray((err, data) => {
        res.send(data[0]);
    });
});

router.post('/books', function(req, res, next) {
    console.log(req.body);
    database.addBook(req.body);
    res.send(true);
});

router.delete('/shows/:id', function(req, res, next) {
    const id = req.params.id;
    database.deleteBook(id);
    res.send(true);
});

router.put('/shows/:id', function(req, res, next) {
    const id = req.params.id;
    const newBook = req.body;
    database.updateBook(id, newBook);
    res.send(true);
});

module.exports = router;
