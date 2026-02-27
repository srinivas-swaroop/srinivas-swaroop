const mongoose = require('mongoose');

function connectDB() {
    mongoose.connect('mongodb://127.0.0.1:27017/userDB').then(() => {
        console.log('Connected to MongoDB');}).catch((err) => console.log(err));
}

module.exports = { connectDB };