var form = document.getElementById('myform');
var people_name = document.getElementById('name');
var gender = document.getElementsByName('gender');
var age = document.getElementById('age');
var datetime = document.getElementById('date');
var whatYouLike = document.getElementsByName('adv');
var rating = document.getElementById('rate');
try {
  var winesArray = JSON.parse(localStorage.getItem('wines')) || [];
} catch (error) {
  var winesArray = [];
}



form.addEventListener("submit",function(e){
  e.preventDefault();

  var checkedGender;
  for(var i = 0;i<gender.length;i++) {
    if(gender[i].checked) {
      checkedGender = gender[i].value;
      break;
    }
  }
  var likes = [];
  for(var i = 0;i<whatYouLike.length;i++){
    if(whatYouLike[i].checked){
      likes.push(whatYouLike[i].value);
    }
  }
  var rates = [];
  for(var i = 0;i<rating.options.length;i++){
    if(rating.options[i].selected) {
      rates.push(rating.options[i].value);
    }
  }
  
  // for(var i = 0; i<rating.length; i++){
  //     rates.push(rating[i].value);
  // }
  console.log(people_name.value,checkedGender,age.value);
  winesArray.push({name : people_name.value, gender: checkedGender, age: age.value, datetime : datetime.value, likes : likes, rating:rates});
  localStorage.setItem('wines',JSON.stringify(winesArray));
})