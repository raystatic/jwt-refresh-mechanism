const jwt = require('jsonwebtoken');

module.exports = (req, res, next) => {
    const token = req.header('Authorization');
    if(!token) {
        res.status(401).json({
            "message":"Unauthorized Access"
        });
    }

    try{
        const jwtToken = token.split(" ")[1];
        const verified = jwt.verify(jwtToken, 'thisshouldbeasecretkey');
        req.user = verified
        next();
    }catch(err) {
        res.status(401).json({
            "message":"Invalid token"
        });
    }

}