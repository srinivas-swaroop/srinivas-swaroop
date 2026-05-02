const mongoose = require('mongoose');

function dbConnect(){
    mongoose.connect('mongodb://127.0.0.1:27017/Learning').then(()=> console.log("Connection Done")).catch((err)=>console.log(err));
}

const schema = new mongoose.Schema({
        name : {type : String, required : true},
        email : {type : String, required : true},
        password : {type : String, required : true}}
)

const model = mongoose.model('model', schema);

function addUser(req, res){

    try{
    const newUser = new model({
        name : req.body.name,
        email : req.body.email,
        password : req.body.password
    })
     newUser.save();
     res.json(newUser);
}catch(err){
    res.end(err);
}

   
}

async function getUser(req, res) {
    const users = await model.find({}); // ⏳ wait for data
    console.log(users);

    res.json(users);
}

async function patchUser(req, res) {
    const changeFor = req.body.changeFor;
    const changeData = req.body.changeData;
    const email = req.body.email; 

    
    try {
        let updateField = {};

        console.log("Before Updating", updateField);

        if (changeFor === 'name') {
            updateField.name = changeData;
        }

        console.log("After Updating", updateField);

        const updatedUser = await User.findOneAndUpdate(
            {email: email},
            { $set: updateField },
            { new: true }
        );

        res.json(updatedUser);

    } catch (err) {
        res.status(500).json({ error: err.message });
    }
}

async function deleteUser(req, res){

}



module.exports = {dbConnect, model, addUser, getUser, patchUser, deleteUser};