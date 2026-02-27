const fs = require('fs');

function readNotes() {

    if (!fs.existsSync('./notes.json')) {
        fs.writeFileSync('./notes.json', '[]');
    }

    const data = fs.readFileSync('./notes.json', 'utf8');

    if (!data) return [];

    return JSON.parse(data);
}

function writeNotes(data) {
    fs.writeFileSync('./notes.json', JSON.stringify(data, null, 2));
}

function getNotes(req, res) {
    console.log("GET /notes");
    console.log(req.headers, 'Printed');
    res.set('X-Response-Time', '100ms');
    console.log(req.headers, 'Printed2');
    const notes = readNotes();
    res.json(notes);
}

function postNotes(req, res) {
    console.log("POST /notes");

    const notes = readNotes();

    const newId = notes.length > 0 
        ? notes[notes.length - 1].id + 1 
        : 1;

    const newNote = {
        id: newId,
        name: req.body.name,
        content: req.body.content
    };

    notes.push(newNote);
    writeNotes(notes);

    res.status(201).json(newNote);
}

function patchNotes(req, res) {
    console.log("PATCH /notes/:id");

    const id = parseInt(req.params.id);
    const notes = readNotes();

    const note = notes.find(n => n.id === id);

    if (!note) {
        return res.status(404).json({ message: "Note not found" });
    }

    if (req.body.name) note.name = req.body.name;
    if (req.body.content) note.content = req.body.content;

    writeNotes(notes);

    res.json(note);
}

function deleteNotes(req, res) {
    console.log("DELETE /notes/:id");

    const id = parseInt(req.params.id);
    let notes = readNotes();

    const newNotes = notes.filter(n => n.id !== id);

    if (notes.length === newNotes.length) {
        return res.status(404).json({ message: "Note not found" });
    }

    writeNotes(newNotes);
    res.json({ message: "Note deleted" });
}

module.exports = {
    getNotes,
    postNotes,
    patchNotes,
    deleteNotes
};
