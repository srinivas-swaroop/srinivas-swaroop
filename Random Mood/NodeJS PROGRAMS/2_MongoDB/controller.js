const mongoose = require('mongoose');
const { user } = require('./model.js');

async function addUser(req, res){
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

};


async function getUsers(req, res){
    const result = await user.find({});
    
        console.log(result);   
    
        res.status(200).json({
            message : 'Fetched Successfully',
            data : result
        });
};


async function deleteUserByEmail(req, res){
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
};


async function updateUserById(req, res){
    const updateFirstName = req.body.firstName;
    const result = await user.findByIdAndUpdate(req.params.id, {firstName : updateFirstName});

    console.log(result);

    res.status(200).json({
        message : 'User Updated Successfully',
        data : result
    });
};

module.exports = {addUser, getUsers, deleteUserByEmail, updateUserById};
