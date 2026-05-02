const express = require('express');
const app = express();
const jwt = require('jsonwebtoken');

app.use(express.json());

const users = [
    {
        name: 'Swaroop',
        email: 'cnsswaroop@gmail.com',
        password: 'Hello'
    },
    {
        name: 'Varun',
        email: 'cnsvarun@gmail.com',
        password: 'Hello'
    }
];

const SECRET = "ABC";

// LOGIN
app.post('/login', (req, res) => {
    const { email, password } = req.body;

    const user = users.find(u => u.email === email);

    if (!user) {
        return res.send("User Not Found");
    }

    if (user.password !== password) {
        return res.send("Wrong Password");
    }

    const token = jwt.sign(
        {
            email: user.email
        },
        SECRET
    );

    return res.json({ token });
});


// PROTECTED ROUTE
app.get('/data', middleUse, (req, res) => {
    res.send(`Welcome ${req.user.email}`);
});


// MIDDLEWARE
function middleUse(req, res, next) {
    const header = req.headers.authorization;
    console.log(header)

    if (!header) {
        return res.send("No token provided");
    }


    try {
        const decoded = jwt.verify(header, SECRET);
        req.user = decoded;
        next();
    } catch (err) {
        return res.send("Invalid Token");
    }
}

app.listen(6969, () => {
    console.log('Running on Port 6969');
});