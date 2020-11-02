$('#changeStyle').click(function(){
    $(selector).find('h1').replaceWith(function() {
        return '<h2>' + $(this).text() + '</h2>';
    });

    $("[id='id1']").each(function (){ 
        $(this).attr("id", "idNEW");
     });
  });


  $('#changeText').click(function(){
      $(selector).find('.class2, .class4, .class6').array.forEach(element => {
        $(this).animate({ 'opacity': 0}, 100), 
        $(this).toggleClass('roll'),
            $(this).animate({'opacity': 1}, 100);
      });
  });