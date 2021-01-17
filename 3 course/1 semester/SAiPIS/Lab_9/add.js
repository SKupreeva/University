define([], function() {  
   return {    
        add: function(err, db, message){
      
        // const db = client.db("productiondb");
        
        console.log(message);
        db.collection("products").insertOne(message, function(err, result){
              
            if(err){ 
                 return console.log(err);
         }
             console.log(result.ops);
            db.close();
        });
        }
    };
});