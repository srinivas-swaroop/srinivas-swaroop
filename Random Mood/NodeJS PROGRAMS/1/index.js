const express = require('express');
const app = express();

app.use((req, res, next)  =>{
    if(req.url === '/daridram'){
        console.log(req.url);
        res.redirect(301, '/about/onlypostivity'); 
    }

    next();
});

app.get('/', (req, res) =>{

    res.send({headers : req.headers,
        message : "Welcome to the Home Page"
    });
})
app.get('/about/onlypostivity', (req, res) =>{
    res.send('<h1>Only Postivity No Space for Daridram</h1>');
})

app.listen(3000, () => {
    console.log('Server is running on port 3000');
});