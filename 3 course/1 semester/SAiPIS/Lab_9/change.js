define([], function() {  
    return {    
         change: function(err, client,message){
       
             const db = client.db("productiondb");
          const collection = db.collection("products");
         console.log(message);
          collection.update({ "name": message.name}, {$set: {"feature": message.feature, "number":message.number, "date":message.date}}, // Update
          {upsert: true});
         }
     };
 });