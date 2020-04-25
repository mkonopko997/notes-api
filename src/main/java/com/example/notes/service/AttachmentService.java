package com.example.notes.service;

import com.example.notes.entity.Attachment;
import com.example.notes.entity.Note;
import com.example.notes.model.NameAndId;
import com.example.notes.repository.AttachmentRepository;
import com.example.notes.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AttachmentService {
    @Autowired
    private NoteRepository noteRepository;
    @Autowired
    private AttachmentRepository attachmentRepository;

    public void create(String noteId, MultipartFile file) throws IOException {
        Note note = this.noteRepository.findById(noteId).get();
        Attachment attachment = new Attachment();
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        if(fileName.contains("..")) {
            return;
        }

        attachment.note = note;
        attachment.name = fileName;
        attachment.type = file.getContentType();
        attachment.value = file.getBytes();
        this.attachmentRepository.save(attachment);
    }

    public Attachment get(String attachmentId) {
        return this.attachmentRepository.findById(attachmentId).get();
    }

    public List<NameAndId> getAll(String noteId) {
        List<Attachment> attachments = this.attachmentRepository.findAll().stream()
                .filter(attachment -> attachment.note.id.equals(noteId))
                .collect(Collectors.toList());

        List<NameAndId> list = new ArrayList<NameAndId>();
        for(Attachment attachment: attachments) {
            list.add(new NameAndId(attachment.name, attachment.id));
        }

        return list;
    }

    public void delete(String attachmentId) {
        Attachment attachment = this.attachmentRepository.findById(attachmentId).get();
        this.attachmentRepository.delete(attachment);
    }

}
