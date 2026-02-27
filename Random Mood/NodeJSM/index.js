const fs = require('fs');
const express = require('express');
const reqFunctions = require('./reqFunctions');

const app = express();

app.use(express.json());

//getMethod - getAllNotes
app.get('/getNotes', (req, res) => {
    reqFunctions.getNotes(req, res);
});

//postNotes - postNotes
app.post('/postNotes', (req, res) => {
    reqFunctions.postNotes(req, res);
});

//patchNotes 
app.patch('/patchNotes/users/:id', (req, res) => {
    reqFunctions.patchNotes(req, res);
});

//deleteNotes
app.delete('/deleteNotes', (req, res) => {

});


app.listen(3000, () => {console.log("Server is running on port 3000")});