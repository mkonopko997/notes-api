package com.example.notes.controller;

import com.example.notes.entity.Attachment;
import com.example.notes.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping(path = "/note")
public class AttachmentController {
    @Autowired
    private AttachmentService attachmentService;

    @PostMapping()
    public @ResponseBody
    void create(
            @RequestParam() MultipartFile file,
            @RequestBody Attachment attachment
    ) {


        attachmentService.create(attachment.note.Id, file);
    }

    @GetMapping(path = "/all")
    public @ResponseBody
    List<Attachment> getAll() {
        return attachmentService.getAll();
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody
    Attachment get(
            @PathVariable String id
    ) {
        return this.attachmentService.get(id);
    }

    @PutMapping(path = "/{id}")
    public @ResponseBody
    void update(
            @PathVariable String id,
            @RequestBody String value
    ) {
        attachmentService.update(id, value);
    }

    @DeleteMapping(path = "/{id}")
    public @ResponseBody
    void delete(
            @PathVariable String id
    ) {
        attachmentService.delete(id);
    }

}