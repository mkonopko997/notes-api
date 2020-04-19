package com.example.notes.controller;

import com.example.notes.entity.Note;
import com.example.notes.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/note")
public class NoteController {
    @Autowired
    private NoteService noteService;

    @PostMapping()
    public @ResponseBody
    void create(
            @RequestBody String value
    ) {
        noteService.create(value);
    }

    @GetMapping(path = "/all")
    public @ResponseBody
    List<Note> getAll() {
        return noteService.getAll();
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody
    Note get(
            @PathVariable String id
    ) {
        return this.noteService.get(id);
    }

    @PutMapping(path = "/{id}")
    public @ResponseBody
    void update(
            @PathVariable String id,
            @RequestBody String value
    ) {
        noteService.update(id, value);
    }

    @DeleteMapping(path = "/{id}")
    public @ResponseBody
    void delete(
            @PathVariable String id
    ) {
        noteService.delete(id);
    }

}