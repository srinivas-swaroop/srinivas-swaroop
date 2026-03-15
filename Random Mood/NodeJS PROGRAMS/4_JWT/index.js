const express = require('express');
const app = express();
const jwt = require('jsonwebtoken');

app.use(express.json());

const sect = "$3kj9@LmP!92#jsKd_2026_secure_token_key";

app.post('/login', (req, res) => {
   const user = {
        id : req.body.id,
        username : req.body.username,
        email : req.body.email
   };

    const token = jwt.sign(
        user, 
        sect, 
        { expiresIn: "1h" }
    );

    res.json({ token });
});


app.get('/profile', (req, res) => {

    const authHeader = req.headers.authorization;
    const token = authHeader.split(" ")[1];

    console.log("Received Token:", token); // Debugging log
    console.log("Authorization Header:", authHeader); // Debugging log

    try {
        const decoded = jwt.verify(token, sect6);
        console.log("Decoded Token:", decoded); // Debugging log
        res.json({ message: "Protected Data", user: decoded });
    } catch (err) {
        res.status(401).json({ error: "Invalid token" });
    }
});


app.listen(7070, () => {
    console.log('Server is Running is Port 7070 : http://localhost:7070');
});
