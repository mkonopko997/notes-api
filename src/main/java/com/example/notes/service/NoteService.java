package com.example.notes.service;

import com.example.notes.entity.Note;
import com.example.notes.entity.User;
import com.example.notes.repository.NoteRepository;
import com.example.notes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoteService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private NoteRepository noteRepository;
    @Autowired
    private CurrentUserService currentUserService;

    public void create(String value) {
        Note note = new Note();
        note.user = new ArrayList<>();
        note.user.add(this.currentUserService.user());
        note.value = value;
        this.noteRepository.save(note);
    }

    public void update(String noteId, String value) {
        Note note = this.noteRepository.findById(noteId).get();
        note.value = value;
        this.noteRepository.save(note);
    }

    public Note get(String noteId) {
        return this.noteRepository.findById(noteId).get();
    }

    public List<Note> getAll() {
        return this.noteRepository.findAll().stream()
                .filter(note -> note.user.get(0).email.equals(this.currentUserService.user().email))
                .collect(Collectors.toList());
    }

    public void assignNoteToOtherUser(String noteId, String email) {
        Note note = this.noteRepository.findById(noteId).get();
        User user = this.userRepository.findById(email).get();
        note.user.add(user);
        this.noteRepository.save(note);
    }

    public void unassignNoteFromUser(String noteId, String email) {
        Note note = this.noteRepository.findById(noteId).get();
        User user = this.userRepository.findById(email).get();
        note.user.remove(user);
        this.noteRepository.save(note);
    }

    public void delete(String noteId) {
        Note note = this.noteRepository.findById(noteId).get();
        this.noteRepository.delete(note);
    }

}
