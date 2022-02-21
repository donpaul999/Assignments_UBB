const mysql = require('mysql');

var db = mysql.createConnection({
    host: "localhost",
    database: "books",
    user: "root",
    password: "123456789"
});

db.connect(function(err) {
    if (err) throw err;
    console.log("Connected!");
});


module.exports = {
    getAllBooks: function () {
        var data;
        var sql = "SELECT * FROM books";
        db.connect(function(err) {
            db.query(sql, function (err, result) {
                if (err) throw err;
                data = result;
                data = JSON.parse(data);
                console.log(data);
                return data;
            });
        });
    },

    getBookById: function (showId) {
        var sql = "SELECT * FROM books where id =" + showId;
        db.connect(function(err) {
            if (err) throw err;
            db.query(sql, function (err, result) {
                if (err) throw err;
                return result;
            });
        });
    },

    addBook: function (book) {
        var sql = "INSERT INTO books ('id', 'name', 'author', 'publishDate', 'isBooked') values ('" + book.name + "', '"  + book.author + "', '"  + book.publishDate + "', '"  + book.isBooked + "')";
        db.connect(function(err) {
            if (err) throw err;
            db.query(sql, function (err, result) {
                if (err) throw err;
                return result;
            });
        });
    },

    deleteBook: function(bookId) {
        var sql = "DELETE FROM books where id =" + bookId;
        db.connect(function(err) {
            if (err) throw err;
            db.query(sql, function (err, result) {
                if (err) throw err;
                return result;
            });
        });
    },

    updateBook: function(bookId, newBook) {
        var sql = "UPDATE books SET name='" + newBook.name + "', author='" + newBook.author + "' publishDate= '" + newBook.publishDate + "' isBooked='" + newBook.isBooked + "' where id =" + bookId;
        db.connect(function(err) {
            if (err) throw err;
            db.query(sql, function (err, result) {
                if (err) throw err;
                return result;
            });
        });
    }
};

