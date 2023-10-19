package com.protaskify.protaskify_api.service.lecturer;

import com.protaskify.protaskify_api.model.enity.Lecturer;
import com.protaskify.protaskify_api.model.enity.Student;
import com.protaskify.protaskify_api.model.request.ImportLecturerRequest;
import com.protaskify.protaskify_api.repository.LecturerRepository;
import com.protaskify.protaskify_api.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LecturerService {
    private final StudentRepository studentRepository;
    private  final LecturerRepository lecturerRepository;

    public void saveStudentsFromJSON(List<Student> students) {
        studentRepository.saveAll(students);
    }

    public void saveLecturerList(ImportLecturerRequest request) {
        lecturerRepository.saveAll(request.getLecturers());
    }

    public List<Lecturer> findAllLecturer() {
        return lecturerRepository.findAll();
    }

    public Lecturer findLecturerId(String lecturer_id) {
        return lecturerRepository.findById(lecturer_id).orElse(null);
    }

    public Lecturer updateLecturer(String lecturer_id, Lecturer lecturerDetails) {
       Lecturer existingLecturer =  lecturerRepository.findById(lecturer_id).orElse(null);
       if(existingLecturer != null){
           existingLecturer.setName(lecturerDetails.getName());
           existingLecturer.setEmail(lecturerDetails.getEmail());
           existingLecturer.setStatus(lecturerDetails.isStatus());
       }
        return lecturerRepository.save(existingLecturer);

    }
}
