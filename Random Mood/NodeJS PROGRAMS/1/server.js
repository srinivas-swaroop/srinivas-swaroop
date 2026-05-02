const express = require('express');
const mongoose = require('mongoose');
const connection = require('./mongodbConnect');

const app = express();

connection.dbConnect();
//.defineModel();

app.use(express.json());

app.get('/getUser', connection.getUser);
app.post('/postUser', connection.addUser);
app.patch('/patchUser', connection.patchUser);
app.delete('/deleteUser', connection.deleteUser);

app.listen(6060, ()=> console.log("Server Running on Port 6060"));


