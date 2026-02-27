const fs = require('fs');

console.log("Start");
data = undefined;

const data = fs.readFile('number.txt', 'utf-8', (err, data) =>{
    if(err){
        console.log(err);
    } else{
        this.data = data;
        console.log(data);
    }
});

console.log(data);
console.log("End");
