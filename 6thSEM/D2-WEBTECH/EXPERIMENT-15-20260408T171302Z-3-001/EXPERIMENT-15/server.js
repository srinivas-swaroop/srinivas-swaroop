const express = require("express");
const path = require("path");

const app = express();
const PORT = 3000;

app.set("view engine", "ejs");
app.set("views", path.join(__dirname, "views"));

const menu = {
  "Briyani": 140,
  "Fried Rice": 110,
  "Chicken Curry": 230,
  "Veg Meals": 120,
  "Paneer Butter Masala": 180,
  "Noodles": 100
};

app.get("/", (req, res) => {
  let billItems = [];

  if (req.query.item && req.query.qty) {
    const itemNames = Array.isArray(req.query.item) ? req.query.item : [req.query.item];
    const qtyValues = Array.isArray(req.query.qty) ? req.query.qty : [req.query.qty];

    for (let i = 0; i < itemNames.length; i++) {
      const name = itemNames[i];
      const qty = parseInt(qtyValues[i]);

      if (menu[name] && qty > 0) {
        billItems.push({
          name: name,
          qty: qty,
          price: menu[name],
          amount: menu[name] * qty
        });
      }
    }
  }

  const total = billItems.reduce((sum, item) => sum + item.amount, 0);
  const gst = total * 0.05;
  const grandTotal = total + gst;

  res.render("bill", {
    menu,
    billItems,
    total,
    gst,
    grandTotal,
    today: new Date().toLocaleDateString("en-IN")
  });
});

app.listen(PORT, () => {
  console.log(`Server running at http://localhost:${PORT}`);
});