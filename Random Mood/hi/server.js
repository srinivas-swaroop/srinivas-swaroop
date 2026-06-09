const http = require("http");
const { MongoClient } = require("mongodb");
const url = "mongodb://127.0.0.1:27017";
const client = new MongoClient(url);
const server = http.createServer(async (req, res) => {
  try {
    await client.connect();
    const db = client.db("taskAppDB");
    const collection = db.collection("users");
    await collection.insertOne({
      name: "Alice",
      email: "alice@example.com",
      role: "Admin",
    });

    const data = await collection.find({ name: "Alice" });
    res.writeHead(200, { "Content-Type": "text/html" });
    res.write(`<h2>User Details</h2>
<p>Name: ${data.name}</p>
<p>Email: ${data.email}</p>
<p>Role: ${data.role}</p>`);
    res.end();
  } catch (err) {
    res.write("Error: " + err);
    res.end();
  }
});
server.listen(3000, () => {
  console.log("Server running");
});
