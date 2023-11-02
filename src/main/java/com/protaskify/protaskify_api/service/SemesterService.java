package com.protaskify.protaskify_api.service;

import com.protaskify.protaskify_api.model.enity.Lecturer;
import com.protaskify.protaskify_api.model.enity.Semester;
import com.protaskify.protaskify_api.model.enity.Student;
import com.protaskify.protaskify_api.model.request.ImportLecturerRequest;
import com.protaskify.protaskify_api.repository.LecturerRepository;
import com.protaskify.protaskify_api.repository.SemesterRepository;
import com.protaskify.protaskify_api.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SemesterService {
    private final SemesterRepository semesterRepository;
    private final StudentRepository studentRepository;
    private final LecturerRepository lecturerRepository;

    public Semester getActiveSemester() {
        Optional<Semester> semester = semesterRepository.findAllByStatus(true);
        if (semester.isPresent()) {
            return semester.get();
        }
        return null;
    }

    public List<Semester> findAllSemester() {
        return semesterRepository.findAll();
    }

    //SAVE SEMESTER
    public boolean saveSemester(Semester semester) {
        if (getActiveSemester() == null) {
            semesterRepository.save(semester);
            return true;
        }
        return false;
    }

    //FIND SEMESTER
    public Semester finSemesterId(Long semester_id) {
        return semesterRepository.findById(semester_id).orElse(null);
    }

    //UPDATE SEMESTER
    public Semester updateSemester(Long semester_id, Semester semesterDetails) {
        Semester existingSemester = semesterRepository.findById(semester_id).orElse(null);
        if (existingSemester != null) {
            existingSemester.setName(semesterDetails.getName());
            existingSemester.setStartDate(semesterDetails.getStartDate());
            existingSemester.setEndDate(semesterDetails.getEndDate());
            existingSemester.setStatus(semesterDetails.isStatus());
        }
        assert existingSemester != null;
        return semesterRepository.save(existingSemester);
    }

    //DELETE SEMESTER
    public Semester deleteSemester(Long semester_id) {
        Semester endSemester = semesterRepository.findById(semester_id).orElse(null);
        if (endSemester != null) {
            semesterRepository.delete(endSemester);
        }
        return null;
    }

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
        Lecturer existingLecturer = lecturerRepository.findById(lecturer_id).orElse(null);
        if (existingLecturer != null) {
            existingLecturer.setName(lecturerDetails.getName());
            existingLecturer.setEmail(lecturerDetails.getEmail());
            existingLecturer.setStatus(lecturerDetails.isStatus());
        }
        assert existingLecturer != null;
        return lecturerRepository.save(existingLecturer);
    }
}
