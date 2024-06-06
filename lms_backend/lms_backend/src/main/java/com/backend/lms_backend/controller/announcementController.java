package com.backend.lms_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.backend.lms_backend.entity.announcement;
import com.backend.lms_backend.entity.teacherAssignment;
import com.backend.lms_backend.service.announcementService;
import com.backend.lms_backend.service.teacherAssignmentService;

@RestController
public class announcementController {

    @Autowired
    private announcementService AnnouncementService;
    
    @Autowired
    private teacherAssignmentService TeacherAssignmentService;

    @GetMapping("/announcements")
    public List<announcement> retrieveAllAnnouncements() {
        return AnnouncementService.findAll();
    }
    
    @GetMapping("/announcements/{id}")
    public announcement retrieveAnnouncement(@PathVariable int id) {
        return AnnouncementService.findOne(id);
    }

    @GetMapping("/announcements/teacherAssignment/{teacherAssignment_id}")
    public List<announcement> getAnnouncementsByTeacherAssignment(@PathVariable int teacherAssignment_id) {
        teacherAssignment teacherAssignmentEntity = TeacherAssignmentService.findOne(teacherAssignment_id);
        return AnnouncementService.getAnnouncementsByTeacherAssignment(teacherAssignmentEntity);
    }
    
    @PostMapping("/announcements")
    public announcement createAnnouncement(@RequestBody announcement ann) {
        announcement savedAnnouncement = AnnouncementService.save(ann);
        return savedAnnouncement;
    }
    
    @DeleteMapping("/announcements/{id}")
    public void deleteAnnouncement(@PathVariable int id) throws Exception {
        try {
            AnnouncementService.deleteById(id);
        } catch (Exception e) {
            throw new Exception("Not found id: " + id);
        }
    }
    
    @PutMapping("/announcements/{id}")
    public announcement updateAnnouncement(@RequestBody announcement ann, @PathVariable int id) {
        announcement existingAnnouncement = AnnouncementService.findOne(id);
        if (existingAnnouncement == null) {
            throw new RuntimeException("Announcement not found with id: " + id);
        }
        ann.setId(id);
        return AnnouncementService.updateannouncement(ann);
    }
}
