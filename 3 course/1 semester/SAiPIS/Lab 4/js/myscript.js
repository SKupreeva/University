var form = document.getElementById('myform');
var itext = document.getElementById('text');
var iwidth = document.getElementById('width');
var icolor = document.getElementById('bColor');
var iopacity = document.getElementById('opacity');
var iname = document.getElementById('name');
try {
  var imgArray = JSON.parse(localStorage.getItem('imgs')) || [];
} catch (error) {
  var imgArray = [];
}

var imt = document.getElementById('imtext');
var im = document.createElement('div');
switch(imgArray[imgArray.length - 1].text) {
    case "Pumpkin 1": im.innerHTML = "<P>Pumpkin 1</P>";
    break;
    case "Pumpkin 2": im.innerHTML = "<P>Pumpkin 2</P>";
    break;
    case "Pumpkin 3": im.innerHTML = "<P>Pumpkin 3</P>";
    break;
}
imt.append(im);

var canvas = document.getElementById("drawingCanvas");
var context = canvas.getContext("2d");
var pump = new Image();
switch(imgArray[imgArray.length - 1].name) {
    case "pumpkin1.jpg": pump.src = "/4/resource/pumpkin1.jpg";
    break;
    case "pumpkin2.jpg": pump.src = "/4/resource/pumpkin2.jpg";
    break;
    case "pumpkin3.jpg": pump.src = "/4/resource/pumpkin3.jpg";
    break;
}
pump.onload = function () {
    canvas.width = this.naturalWidth;
    canvas.height = this.naturalHeight;
    context.lineWidth = 7;
    switch(imgArray[imgArray.length - 1].bColor) {
        case "black": context.strokeStyle = 'black';
        break;
        case "grey": context.strokeStyle = 'grey';
        break;
        case "white": context.strokeStyle = 'white';
        break;
    }
    switch(imgArray[imgArray.length - 1].width) {
        case "100px": context.strokeRect(5, 5, 300, 558);
        break;
        case "200px": context.strokeRect(5, 5, 600, 558);
        break;
        case "300px": context.strokeRect(5, 5, 790, 558);
        break;
    }
    switch(imgArray[imgArray.length - 1].opacity) {
        case "100%": context.globalAlpha = 1.0;
        break;
        case "50%": context.globalAlpha = 0.5;
        break;
        case "0%": context.globalAlpha = 0.0;
        break;
    }
    switch(imgArray[imgArray.length - 1].width) {
        case "100px": context.drawImage(pump, 8, 10, 293, 550);
        break;
        case "200px": context.drawImage(pump, 8, 10, 593, 550);
        break;
        case "300px": context.drawImage(pump, 8, 10, 783, 550);
        break;
    }
};

function submitFunc() {
  imgArray.push({text : itext.value, width : iwidth.value, bColor : icolor.value, opacity : iopacity.value, name : iname.value});
  localStorage.setItem('imgs',JSON.stringify(imgArray));
  location.reload();
}

var table = document.getElementById('table');
for(var i = 0 ;i<imgArray.length;i++) {
    var tr = document.createElement('tr');
    tr.innerHTML = "<td>" + imgArray[i].text +"</td>" + "<td>" + imgArray[i].width +"</td>" +  "<td>" + imgArray[i].bColor +"</td>" +  "<td>" + imgArray[i].opacity +"</td>" +  "<td>" + imgArray[i].name +"</td>";
    table.append(tr);
}

function clearFunc() {
    localStorage.setItem('imgs',[{}]);
    location.reload();
}