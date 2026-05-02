const express = require('express');
const app = express();

app.use(express.json())

require('dotenv').config();

let arr = [];

// console.log(arr.map(obj => obj.name));
// console.log(arr);

app.get('/get', (req, res)=>{
    res.send({
        status : "succuess"
    })
})

app.get('/showData', (req,res)=>{
    let index = 1;
    arr.forEach(user => {
        console.log(`User ${index++}, name : ${user.name}, age: ${user.age}, email : ${user.email}`);
    })

    res.send({
        status : "Good"
    })
})

app.post('/sendData', (req, res)=>{
    const {name, age, email} = req.body;

    if(!name || !age || !email){
        return res.status(400).send({
            status : "ERROR",
            statusCode : "404",
        })
    }

    const user = {
        "name" : name,
        "age" : age,
        "email" : email
    }

    arr.push(user);

    console.log(arr.map(obj => obj.name));
    console.log(arr);


    res.send({
        status : "Succesfully Added Person",
        data : user
    })
})

app.patch('/UpdateData', (req,res)=>{
    const updateFrom = req.body.updateFrom;
    const updateData = req.body.name;

    let userData= null;

    arr.forEach(users => {
        if(users.name === updateFrom){
            users.name = updateData;
            userData = users;
        }
    }
    )

    let index = 1;
    arr.forEach(user => {
        console.log(`User ${index++}, name : ${user.name}, age: ${user.age}, email : ${user.email}`);
    })


    res.send({
        Status : "Done",
        DataChanged : userData
    })
})




app.listen(process.env.PORT, ()=>{
    console.log('Running on Port 3000');
})


