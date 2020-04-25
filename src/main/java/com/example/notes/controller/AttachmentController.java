package com.example.notes.controller;

import com.example.notes.entity.Attachment;
import com.example.notes.model.NameAndId;
import com.example.notes.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping(path = "/attachment")
public class AttachmentController {
    @Autowired
    private AttachmentService attachmentService;

    @PostMapping()
    public @ResponseBody
    void create(
            @RequestParam() MultipartFile file,
            @RequestParam() String noteId
    ) throws IOException {
        attachmentService.create(noteId, file);
    }

    @GetMapping(path = "/all/{noteId}")
    public @ResponseBody
    List<NameAndId> getAll(
            @PathVariable String noteId
    ) {
        return attachmentService.getAll(noteId);
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody
    ResponseEntity<Resource> get(
            @PathVariable String id
    ) {
        Attachment attachment = attachmentService.get(id);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(attachment.type))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + attachment.name + "\"")
                .body(new ByteArrayResource(attachment.value));
    }

    @DeleteMapping(path = "/{id}")
    public @ResponseBody
    void delete(
            @PathVariable String id
    ) {
        attachmentService.delete(id);
    }

}