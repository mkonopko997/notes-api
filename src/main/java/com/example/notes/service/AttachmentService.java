package com.example.notes.service;

import com.example.notes.entity.Attachment;
import com.example.notes.entity.Note;
import com.example.notes.repository.AttachmentRepository;
import com.example.notes.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AttachmentService {
    @Autowired
    private NoteRepository noteRepository;
    @Autowired
    private AttachmentRepository attachmentRepository;

    public void create(String noteId, MultipartFile file) {
        Note note = this.noteRepository.findById(noteId).get();
        Attachment attachment = new Attachment();
        attachment.note = note;
        attachment.name = name;
        attachment.value = value;
        this.attachmentRepository.save(attachment);
    }

    public Attachment get(String attachmentId) {
        return this.attachmentRepository.findById(attachmentId).get();
    }

    public List<Attachment> getAll(int noteId) {
        return this.attachmentRepository.findAll().stream()
                .filter(attachment -> attachment.note.Id == noteId)
                .collect(Collectors.toList());
    }

    public void delete(String attachmentId) {
        Attachment attachment = this.attachmentRepository.findById(attachmentId).get();
        this.attachmentRepository.delete(attachment);
    }

}
