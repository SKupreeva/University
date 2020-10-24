var table = document.getElementById('table');
try {
    var winesArray = JSON.parse(localStorage.getItem('wines')) || [];
  } catch (error) {
    var winesArray = [];
  }

for(var i = 0 ;i<winesArray.length;i++) {
    var tr = document.createElement('tr');
    tr.innerHTML = "<td>" + winesArray[i].name +"</td>" + "<td>" + winesArray[i].gender +"</td>" +  "<td>" + winesArray[i].age +"</td>" +  "<td>" + winesArray[i].datetime +"</td>" +  "<td>" + winesArray[i].likes +"</td>" +  "<td>" + winesArray[i].rating +"</td>" ;
    table.append(tr);
}

function clearFunc() {
    localStorage.setItem('wines',[{}]);
    location.reload();
}