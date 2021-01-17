var express = require('express');  
var app = express();  
var server = require('http').createServer(app);  
var io = require('socket.io')(server);
var fs = require('fs');

app.set('views', __dirname + '/client')
app.set('view engine', 'pug')
app.use(express.static(__dirname + '/client'))

server.listen(5000, function () {
  console.log('Server listening at port %d', 5000);
});



app.get('/', function (req, res) {
  res.render('index');
})

app.get('/results.html', function(req,res) {
    console.log('PAGE@');   
    res.writeHead(200, {'Content-Type': 'text/html'});
    fs.createReadStream(__dirname + '/results.html').pipe(res);
        
});
app.get('/results2.html', function(req,res) {
    console.log('PAGE@');   
    res.writeHead(200, {'Content-Type': 'text/html'});
    fs.createReadStream(__dirname + '/results2.html').pipe(res);
        
});


io.sockets.on('connection', function (socket) {
    socket.emit('message', 'You are connected!');
    // Когда сервер получает сообщение типа “message” от клиента
    socket.on('message', function (message) {
        console.log('A client is speaking to me! They’re saying: ' + message);
        var p = "";
        for(var i = 0; i < message.length; i++){
            if(message[i]!=""){
                p = p + message[i]+'\n';
            }
        }
        fs.writeFile("wrong.txt", p, function(error){
            if(error) throw error; // если возникла ошибка
            //console.log("Асинхронная запись файла завершена. Содержимое файла:");
            let data = fs.readFileSync("wrong.txt", "utf8");
            //console.log(data);  // выводим считанные данные
        });
        p = "";
        message.sort();
        for(var i=0;i< message.length;i++){
            if (! message[i]){} 
            else{
           message[i]=message[i][0].toUpperCase() + message[i].slice(1);
           p = p + message[i]+'\n';
            }
        }
      

        fs.writeFile("right.txt", p, function(error){
            if(error) throw error; // если возникла ошибка
            //console.log("Асинхронная запись файла завершена. Содержимое файла:");
            let data1 = fs.readFileSync("right.txt", "utf8");
            //console.log(data1);  // выводим считанные данные
        });
    }); 

    socket.on('message2', function (message) {
        
        fs.readFile("wrong.txt", function(err, data){
            if (err) {
                console.log(err);
            } else {
                let masc = data.toString().split("\n"); // содержимое файла
                //console.log("DATA "+ data.toString().split("\n"));
                socket.emit('wrong', masc);
            }
        });
    }); 
    socket.on('message3', function (message) {
        
        fs.readFile("right.txt", function(err, data){
            if (err) {
                console.log(err);
            } else {
                let masc = data.toString().split("\n"); // содержимое файла
                //console.log("DATA "+ data.toString().split("\n"));
                socket.emit('right', masc);
            }
        });
    }); 
});
