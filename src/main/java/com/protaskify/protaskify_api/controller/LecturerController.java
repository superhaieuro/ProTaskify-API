package com.protaskify.protaskify_api.controller;


import com.protaskify.protaskify_api.model.enity.Lecturer;
import com.protaskify.protaskify_api.service.lecturer.LecturerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.protaskify.protaskify_api.model.request.ImportLecturerRequest;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/lecturers")
@RequiredArgsConstructor
public class LecturerController {
    private final LecturerService lecturerService;

    //display all
    @GetMapping("/view-all-lecturer")
    public List<Lecturer> getAllLecturers() {
        return lecturerService.findAllLecturer();
    }
    //build create lecture REST API
    @PostMapping("/import-lecturer")
    public void importLecturer(@RequestBody ImportLecturerRequest request) {
         lecturerService.saveLecturerList(request);
    }

    // build get lecture by id REST API
    @GetMapping("/view-lecturer-by-id/{lecturer_id}")
    public ResponseEntity<Lecturer> getLecturerById(@PathVariable String lecturer_id) {
        try {
        Lecturer lecturer = lecturerService.findLecturerId(lecturer_id);
            return ResponseEntity.ok(lecturer);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    //build update lecturer REST API
    @PutMapping("/update-lecturer/{lecturer_id}")
    public ResponseEntity<Lecturer> updateLecturer(@PathVariable String lecturer_id, @RequestBody Lecturer lecturerDetails) {
        try {
            Lecturer updateLecturer = lecturerService.updateLecturer(lecturer_id, lecturerDetails);
            return ResponseEntity.ok(updateLecturer);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

//    //build delete lecturer REST API
//    @DeleteMapping("{id}")
//    public ResponseEntity<Void> deleteLecturer(@PathVariable String id){
//        try {
//            Lecturer deleteLecturer = lecturerService.updateLecturer(lecturer_id, lecturerDetails);
//            return ResponseEntity.ok(updateLecturer);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//        }
//    }

}

