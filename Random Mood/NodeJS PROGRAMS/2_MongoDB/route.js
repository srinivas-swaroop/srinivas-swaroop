const express = require('express');
const app = express();
const router = express.Router();

const routerAuth = express.Router();

app.use(express.json());
const controller = require('./controller');


router.post('/addUser', controller.addUser);
router.get('/getUsers', controller.getUsers);
router.delete('/deleteUser/:mail', controller.deleteUserByEmail);
router.patch('/updateUser/:id', controller.updateUserById);



module.exports = router;