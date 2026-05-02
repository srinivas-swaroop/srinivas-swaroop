const fs = require('fs');

fs.writeFileSync('data.txt', "MeraNaam Meri Haiiiiiii");
let data = fs.readFileSync('data.txt', 'utf-8');

exports.modules = data;