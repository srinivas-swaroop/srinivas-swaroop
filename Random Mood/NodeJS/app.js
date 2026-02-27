const http = require('http');
const fs = require('fs');
const url = require('url');
const express = require('express');

const app = express();

app.use(express.json());

//----------------------
// const myServer = http.createServer((req, res)=>{
//     const urlGot = req.url;
//     const methodGot = req.method;
//     const data = JSON.stringify(url.parse(urlGot));

    
    
//     fs.appendFile('headers.txt', `URL: ${urlGot}, Method: ${methodGot}, ${data}\n`, (err)=>{
//         if(err) console.log(err);
//     });

//     if(urlGot === '/'){
//         res.end(` Home page : got url ${urlGot}`);
//     } else if(urlGot === '/about'){
//         res.end('ABout Page Mawa');
//     } else{
//         res.end(`Error 404 ${data}`);
//     }

// });

//myServer.listen(8000, ()=> console.log('Server Started in Port 8000'));
//----------------------------------

// function stringToHex(str) {
//     let hex = "";
//     for (let i = 0; i < str.length; i++) {
//         hex += str.charCodeAt(i).toString(16).padStart(2, "0") + " ";
//     }
//     return hex.trim();
// }


// function hexToString(hexStr) {
//     let result = "";
//     const arr = hexStr.split(" ");
//     for (let i = 0; i < arr.length; i++) {
//         result += String.fromCharCode(parseInt(arr[i], 16));
//     }
//     return result;
// }

// app.get('/home', (req, res) => {
//     res.send("<h1>Welcome to Encryption and Decryption App</h1>")
// });



// app.post('/encrypt', (req, res)=>{
//     const data = req.body.data;
//     const hashAnswer = stringToHex(data);

//     res.send(hashAnswer);

// });

// app.post('/decrypt', (req, res) => {
//     const data = req.body.data;
//     const normalAns = hexToString(data);
//     res.send(normalAns);
// })
//--------------------------

// app.use((req, res, next)=>{
//     if(req.body.name === 'Swaroop C N S') req.body.name = 'SWAROOP C N S';
//     console.log('Name Checked', req.body.name);

//     next();
// });

// app.use((req, res, next)=>{
//     if(!req.body.email.includes('@')) {
//         req.body.email = req.body.email + '@gmail.com';

//     console.log('Email Updated:', req.body.email);
    
//     next();
// }});
app.get('/getUsers', (req, res)=>{
    const data = fs.readFileSync('MOCK_DATA.json', 'utf8');
    const users = JSON.parse(data);
    for(let i =0; i<users.length; i++){
        console.log(users[i].first_name, users[i].last_name || users[i].name);
    }
    res.json(users);
}) 


app.post('/addUser', (req, res) => {
    const data = fs.readFileSync('MOCK_DATA.json', 'utf8');
    const users = JSON.parse(data);          
    const idLen = users.length;      

    console.log(idLen);        
    
    const userData = { 
        id: idLen + 1, 
        name: req.body.name, 
        email: req.body.email 
    };

    users.push(userData);                    

    fs.writeFile('MOCK_DATA.json', JSON.stringify(users, null, 2), (err) => {
        if (err) console.log(err);
    });

    res.send({
        status: "success",
        Data: userData
    });
});





app.listen(8000);




