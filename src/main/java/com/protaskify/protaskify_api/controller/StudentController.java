package com.protaskify.protaskify_api.controller;

import com.protaskify.protaskify_api.model.enity.Group;
import com.protaskify.protaskify_api.model.enity.Student;
import com.protaskify.protaskify_api.repository.GroupRepository;
import com.protaskify.protaskify_api.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/student")
@CrossOrigin(value = "*", allowedHeaders = "*")
public class StudentController {
    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;
    @GetMapping("/getStudentsByClass")
    public ResponseEntity<List<Student>> getStudentByClass(@RequestParam String clasID){
        List<Student> list = studentRepository.findStudentByClass(clasID);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/getStudentsByGroup")
    public ResponseEntity<List<Student>> getStudentByGroup(@RequestParam String groupID){
        List<Student> list = studentRepository.findStudentByGroup(groupID);
        return ResponseEntity.ok(list);
    }

    @PutMapping("/updateGroup")
    public ResponseEntity<?> updateStudentGroup(@RequestParam String groupID,@RequestParam String studentID)
    {
        Student student = studentRepository.findById(studentID).get();
        Group group = groupRepository.findById(groupID).get();
        student.setGroup(group);
        studentRepository.save(student);
        return ResponseEntity.ok(null);
    }
}
