var form=document.getElementById("textA");
var btn=document.getElementById("save");

/*$(function () {
    $("#form").submit(function (event) {
      event.preventDefault();
      $.post("/testr", $(this).serialize());
    })
  })
*/




//btn.addEventListener('onclick',function(){

function saveForm(){ 
    
    let array=form.value.split('\n');
    for(var i=0;i<array.length;i++){
        //array[i].submit();
        
        //document.getElementById("").innerHTML=array[i];
    }

    let newArray=changeArray();
    for(var i=0;i<newArray.length;i++){
        alert(newArray[i]);
       // newArray[i].submit();

    }
   
}
//);


function changeArray(){
    let array=form.value.split('\n');
    for(var i=0;i<array.length;i++){
        if (!array[i]){} 
        else{
       array[i]=array[i][0].toUpperCase() + array[i].slice(1);
        }
    }
    array.sort();
    return array;
}
  

function arrayToList(array){
  

    
    return array;
}
  
