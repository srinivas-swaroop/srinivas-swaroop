const express = require('express');
const path = require('path');
const methodOverride = require('method-override');

const app = express();
const PORT = 4000;

app.set('view engine', 'ejs');
app.set('views', path.join(__dirname, 'views'));

app.use(express.urlencoded({ extended: true }));
app.use(methodOverride('_method'));

let todos = [
  { id: 1, task: 'Learn Node.js', status: 'Pending' },
  { id: 2, task: 'Build EJS Todo App', status: 'Done' }
];

app.get('/', (req, res) => {
  const editId = parseInt(req.query.editId);
  const editTodo = todos.find(t => t.id === editId) || null;
  res.render('index', { todos, editTodo });
});

app.post('/add', (req, res) => {
  const task = req.body.task?.trim();
  if (task) {
    todos.push({ id: Date.now(), task, status: 'Pending' });
  }
  res.redirect('/');
});

app.post('/update/:id', (req, res) => {
  const id = parseInt(req.params.id);
  const todo = todos.find(t => t.id === id);

  if (todo) {
    todo.task = req.body.task?.trim() || todo.task;
    todo.status = req.body.status || todo.status;
  }

  res.redirect('/');
});

app.delete('/delete/:id', (req, res) => {
  const id = parseInt(req.params.id);
  todos = todos.filter(t => t.id !== id);
  res.redirect('/');
});

app.listen(PORT, () => {
  console.log(`Server running at http://localhost:${PORT}`);
});