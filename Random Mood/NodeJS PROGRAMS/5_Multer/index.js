const multer  = require('multer');
const express = require('express');
const path = require('path');

const app = express();
let fileNo = 1;

const uploadDir = path.join(__dirname, "uploads");

const storage = multer.diskStorage({
  destination: function (req, file, cb) {
    cb(null, uploadDir)
  },
  filename: function (req, file, cb) {
    const uniqueSuffix = Date.now() + '-upload-' + fileNo++;
    const ext = path.extname(file.originalname);
    cb(null, file.fieldname + '-' + uniqueSuffix + ext);
  }
})

app.post("/upload", multer({storage : storage}).single("file"), (req, res) => {
    console.log(req.file); // Debugging log to check the uploaded file information
    res.send('File uploaded successfully');
}); 

app.get("/", (req, res) => {
  res.sendFile(path.join(__dirname, "form.html"));
});


app.listen(7070, ()=>{
    console.log('Server is Running is Port 7070 : http://localhost:7070');
});