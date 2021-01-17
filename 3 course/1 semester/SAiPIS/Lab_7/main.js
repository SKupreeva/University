
$("#button1").click(function(){
    $.ajax({
        url: 'Apple.txt',
        dataType: 'text',
        
        success: function (data) {
            $("#Apple").append(data);
            $("#Apple").toggleClass('Apple');
        }
      });
      $('#button1').attr("disabled",true);
});

$("#button2").click(function(){
    $.ajax({
        url: 'Samsung.txt',
        dataType: 'text',
        success: function (data) {
            $("#Samsung").append(data);
            $("#Samsung").toggleClass('Samsung');
        },
        error: function (data) {
            $("#button1").bind('click',function(err){
alert("jkdhjk");
            });
        }
      });
    
});

$("#button3").click(function(){
    $.ajax({
        url: 'Nokia.txt',
        dataType: 'text',
        
        success: function (data) {
            $("#Nokia").append(data);
            $("#Nokia").toggleClass('Nokia');
        }
      });
      $('#button3').attr("disabled",true);
});

$("#button4").click(function(){
    $.ajax({
        url: 'Sony.txt',
        dataType: 'text',
        
        success: function (data) {
            $("#Sony").append(data);
            $("#Sony").toggleClass('Sony');
        }
      });
      $('#button4').attr("disabled",true);
});

$("#button5").click(function(){
    $.getJSON( "Apple.json", function(obj) {
$.each(obj, function(key, value) {
$("#AppleInfo").append("<div>"+"<span class='color'>"+key+"</span>"+value+".</div>");
$("#AppleInfo").toggleClass('infoApple');
});

});
$.getJSON( "Samsung.json", function(obj) {
$.each(obj, function(key, value) {
$("#SamsungInfo").append("<div>"+"<span class='color'>"+key+"</span>"+value+"</div>");
$("#SamsungInfo").toggleClass('infoSamsung');
});
});
$.getJSON( "Nokia.json", function(obj) {
$.each(obj, function(key, value) {
$("#NokiaInfo").append("<div>"+"<span class='color'>"+key+"</span>"+value+"</div>");
$("#NokiaInfo").toggleClass('infoNokia');
});
});
$.getJSON( "Sony.json", function(obj) {
$.each(obj, function(key, value) {
$("#SonyInfo").append("<div>"+"<span class='color'>"+key+"</span>"+value+"</div>");
$("#SonyInfo").toggleClass('infoSony');
});
});
$('#button5').attr("disabled",true);
});

$("#button6").click(function(){

   $("#AppleCompanyInfo").text('Innovations'); // задаем содержимое элементов <p>
    $("#AppleCompanyInfo").toggleClass('info');
    $("#SamsungCompanyInfo").text('Innovations'); // задаем содержимое элементов <p>
     $("#SamsungCompanyInfo").toggleClass('info');
     $("#NokiaCompanyInfo").text('Innovations'); // задаем содержимое элементов <p>
     $("#NokiaCompanyInfo").toggleClass('info');
     $("#SonyCompanyInfo").text('Innovations'); // задаем содержимое элементов <p>
     $("#SonyCompanyInfo").toggleClass('info');
     $('#AppleImage').load("images.html");
   $('.SamsungImage').load("images2.html");
   $('#NokiaImage').load('images3.html');
   $('#SonyImage').load('images4.html');
    $('#button6').attr("disabled",true);
});

$("#button6").bind('click',function(){
alert("All info will be shown!");
});
 

  