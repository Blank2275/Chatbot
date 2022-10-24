const fs = require("fs");

let data = fs.readFileSync("./synonyms.json").toString();
let synonyms = JSON.parse(data);



console.log(Object.keys(synonyms).length);