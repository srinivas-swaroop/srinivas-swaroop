const https = require("https");

const API_KEY = "FURZPUER46Q24NXK";

function sendData(temperature) {

    const x = temperature;

    const url = `https://api.thingspeak.com/update?api_key=${API_KEY}&field1=${x}`;

    https.get(url, (res) => {
        console.log("Temperature sent to cloud:", temperature);
        console.log(res.url);
        console.log(res.statusCode);
        console.log(res.statusMessage);
    });

}





sendData(50);
setTimeout(() => sendData(29), 15000);
setTimeout(() => sendData(30), 30000);



