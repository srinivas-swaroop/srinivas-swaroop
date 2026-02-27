const express = require('express');
const DB = require('./db');

const app = express();
const route = require('./route');

DB.connectDB();  
  
app.use(express.json());
app.use('/', route);



app.listen(6969, () => {
    console.log('Server is Running is Port 6969 : http://localhost:6969');
})