const MongoClient = require("mongodb").MongoClient;
var express = require('express');  
var app = express();  
var server = require('http').createServer(app);  
var io = require('socket.io')(server);

const url = "mongodb://localhost:5000";
const mongoClient = new MongoClient(url, { useNewUrlParser: true });
var requirejs = require('requirejs');

app.set('views', __dirname + '/client')
app.set('view engine', 'pug')
app.use(express.static(__dirname + '/client'))

server.listen(5000, function() {
    console.log('App is running on 5000');
})




app.get('/', function (req, res) {
    res.render('index');
  });


io.sockets.on('connection', function (socket) {
    socket.emit('message', 'You are connected!');
    socket.on('message',  function (message) {
        mongoClient.connect(function(err, client){
         var array = new Array();
            requirejs(["show"], 
            function(show) {  
                var con=0;
           show.show(err,client).then((doc) =>{ 
            array[con]=doc;
                    console.log(array[con]);
                
                socket.emit('show', array[con]);
                con++;
           });
            });
           
           
        }); 
       
    }); 


    socket.on('add',  function (message) {

        mongoClient.connect(function(err, client){
            requirejs(["add"], 
            function(add) {  
               console.log(message);
           add.add(err,client,message);
            });
    }); 
    }); 


    socket.on('delete',  function (message) {

        mongoClient.connect(function(err, client){
            requirejs(["deleteOne"], 
            function(deleteOne) {  
           deleteOne.delete(err,client,message);
            });
    }); 
    }); 

    socket.on('change',  function (message) {

        mongoClient.connect(function(err, client){
            requirejs(["change"], 
            function(change) {  
               console.log(message);
           change.change(err,client,message);
            });
    }); 
    }); 

    socket.on('showOnDate',  function (message) {
        mongoClient.connect(function(err, client){
            var array = new Array();
               requirejs(["show"], 
               function(show) {  
                var con=0,count=0;
              show.show(err,client).then((doc) =>{ 
                array[con]=doc;
                for(var i=0;i<doc.length;i++){
            if(array[con][i].date.indexOf(message)==0){
                console.log(array[con][i]);
                count++;
            }
                }
                if(count==0){
                    console.log("Таких записей не найдено!");
                }
                else console.log("Вывод данных совершен успешно!");
              con++;
               });
              
              
           }); 
    
        }); 
    }); 

    socket.on('deleteNonFood',  function (message) {

        mongoClient.connect(function(err, client){
         const db = client.db("productiondb");
         const collection = db.collection("products");
        console.log(message);
         collection.deleteMany({feature:message}, function(err, result){
              
            if(err){ 
                 return console.log(err);
         }
            client.close();
        });
    }); 
    }); 
});

