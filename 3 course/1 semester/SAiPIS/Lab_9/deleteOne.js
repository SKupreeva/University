define([], function() {  
    return {    
         delete: function(err, client,message){
       
            const db = client.db("productiondb");
             const collection = db.collection("products");
             collection.deleteOne({name:message}, function(err, result){
                  
                if(err){ 
                     return console.log(err);
             }
                client.close();
            });
         }
     };
 });