const express = require("express");
const path = require("path");

const app = express();
const PORT = 5000;

app.set("view engine", "ejs");
app.set("views", path.join(__dirname, "views"));

app.use(express.urlencoded({ extended: true }));

app.get("/", (req, res) => {
  res.render("emi", {
    result: null,
    values: {
      amount: "",
      tenure: "",
      rate: "",
      type: "in Arrears"
    }
  });
});

app.post("/", (req, res) => {
  let { amount, tenure, rate, type } = req.body;

  amount = parseFloat(amount);
  tenure = parseInt(tenure);
  rate = parseFloat(rate);

  let emi = 0;

  if (amount > 0 && tenure > 0 && rate > 0) {
    const monthlyRate = rate / 12 / 100;

    emi =
      (amount * monthlyRate * Math.pow(1 + monthlyRate, tenure)) /
      (Math.pow(1 + monthlyRate, tenure) - 1);

    if (type === "in Advance") {
      emi = emi / (1 + monthlyRate);
    }
  }

  res.render("emi", {
    result: emi.toFixed(2),
    values: {
      amount,
      tenure,
      rate,
      type
    }
  });
});

app.listen(PORT, () => {
  console.log(`Server running at http://localhost:${PORT}`);
});