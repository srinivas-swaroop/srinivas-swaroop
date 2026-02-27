const express = require('express');
const routerAuth = express.Router();
const routerData = express.Router();
const mongoose = require('mongoose');

const cookieParser = require('cookie-parser');
routerAuth.use(cookieParser());
routerData.use(cookieParser());

let sessions = {};

function createSession(user){
    const sessionId = Math.random().toString(36).substring(2, 15);
    sessions[sessionId] = user;
    return sessionId;
}

function getUserFromSession(sessionId){
    return sessions[sessionId];
}

const userSchema = new mongoose.Schema({
    firstName :{
        type : String,
        required : true 
    },
    email : {
        type : String,
        required : true,
        unique : true
    },
    password : {
        type : String,
        required : true 
    }
}, {timestamps: true});

const User = mongoose.model('User', userSchema);



routerAuth.post('/signup', (req, res)=>{
    const {firstName, email, password} = req.body;

    const newUser = new User({
        firstName,
        email,
        password
    });

    newUser.save().then((user) => {
        res.status(201).json({message : 'User Created Successfully', user});
    }).catch((err) => {
        res.status(500).json({message : 'Error Creating User', error : err.message});
    });

});

routerAuth.post('/login', (req, res) => {
    const {email, password} = req.body;

    User.findOne({email}).then((user) => {
        if(!user){
            return res.status(404).json({message : 'User Not Found'});
        }
        if(user.password === password){
            const sessionId = createSession(user);
            res.cookie('sessionId', sessionId, {httpOnly : true});
            res.status(200).json({message : 'Login Successful', sessionId});
        } else {
            res.status(401).json({message : 'Invalid Password'});
        }
    }).catch((err) => {
        res.status(500).json({message : 'Error Finding User', error : err.message});
    });
});


routerData.get('/', async (req, res) => {
    const sessionId = req.cookies.sessionId;
    const user = getUserFromSession(sessionId);

    if (!user) {
        return res.status(401).json({ message: 'Unauthorized' });
    }

    try {
        const allUsers = await User.find();

        res.status(200).json({
            message: 'Data Retrieved Successfully',
            loggedInUser: user,
            allUsers: allUsers
        });

    } catch (err) {
        res.status(500).json({
            message: 'Error fetching users',
            error: err.message
        });
    }
});
module.exports = {routerAuth, routerData};