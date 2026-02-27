const express = require('express');
const mongoose = require('mongoose');

const app = express();

app.use(express.json());

mongoose.connect('mongodb://127.0.0.1:27017/userDB').then(() => {
    console.log('Connected to MongoDB');}).catch((err) => console.log(err));

const userSchema = new mongoose.Schema({
    firstName : { type : String, required : true},
    lastName : {type : String, required : true },
    email : {type : String, required : true, unique : true},
    password : {type : String, required : true},
    age : {type : Number, required : true}
} , {timestamps : true});


const user = mongoose.model('user', userSchema);

app.post('/addUser', (req, res) => {
    console.log(req.body);

    const result = user.create({
    firstName : req.body.firstName,
    lastName  : req.body.lastName,
    email : req.body.email,
    password : req.body.password,
    age : req.body.age,
    }).then(result => {
        console.log(result);
    }).catch(error => {
        console.log(error);
    });

    console.log(result);

    res.status(201).json({
        message : 'User Added Successfully',
        data : result
    });


});


app.get('/getUsers', async (req, res) => {
    const result = await user.find({});

    console.log(result);   

    res.status(200).json({
        message : 'Fetched Successfully',
        data : result
    });
});


app.delete('/deleteUser/:mail', async(req, res) => {
    
    await user.findOneAndDelete({email : req.params.mail}).then(() => {
        res.status(200).json({
            message : req.params.mail + 'Deleted Successful'
        }).catch(error => {
            res.status(500).json({
                message : 'Error Deleting User',
                error : error.message
            })
        })
    })
});

app.patch('/updateUser/:id', async (req, res) => {
    const updateFirstName = req.body.firstName;
    const result = await user.findByIdAndUpdate(req.params.id, {firstName : updateFirstName});

    console.log(result);

    res.status(200).json({
        message : 'User Updated Successfully',
        data : result
    })
});



app.listen(6969, () => {
    console.log('Server is Running is Port 6969 : http://localhost:6969');
})