define([], function() {  
    return {    
         show: async function(err, client){
             const db = client.db("productiondb");
             const collection = db.collection("products");
             var array=new Array();
             if(err) return console.log(err);
            const cursor= collection.find({});
            const count=await cursor.count();
           // console.log(count);
                var con=0;
                await cursor.forEach(doc => {
                    array[con]=doc;
                   // console.log(doc.name);
                con++;
                }
                );
               
            return array;
         }
     };
 });