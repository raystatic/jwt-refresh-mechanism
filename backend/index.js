const express = require('express');
const verifyToken = require('./verifytoken');
const jwt = require('jsonwebtoken');

const app = express();

const router = express.Router();

router.post('/createNewToken', (req, res) => {
    const token = jwt.sign({
        data: "someIdentifierData"
    }, 'thisshouldbeasecretkey', { expiresIn: 60 })

    res.status(201).json({
        "message": "New token created",
        "token": token
    });

}); // this token will expire in 1 minute

router.get('/getSomeData', verifyToken, (req, res) => {
    res.status(200).json({
        "message":"Data fetched",
        "data": "some data returned from server"
    });
})

app.use('/api', router);

app.get('/', (req, res) => {
    res.json({
        'message':"Server running"
    })
});

const port = 3000

app.listen(port, () => {
    console.log(`Server running at port: ${port}`);
});