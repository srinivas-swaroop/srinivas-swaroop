const express = require('express');
const app = express();
const mongoose = require('mongoose');
const {routerAuth, routerData} = require('./routeAuth');

mongoose.connect('mongodb://localhost:27017/authDB')
.then(() => {
    console.log('Connected to MongoDB');
}).catch((err) => {
    console.error('Error connecting to MongoDB:', err);
});

app.use(express.json());
app.use('/account', routerAuth);
app.use('/getData', routerData);


app.listen(3000, ()=> {
    console.log('Server is running : http://localhost:3000');
});