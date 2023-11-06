package com.protaskify.protaskify_api.service;

import com.protaskify.protaskify_api.model.enity.Lecturer;
import com.protaskify.protaskify_api.model.enity.Project;
import com.protaskify.protaskify_api.model.enity.Semester;
import com.protaskify.protaskify_api.model.enity.Student;
import com.protaskify.protaskify_api.model.request.ImportLecturerRequest;
import com.protaskify.protaskify_api.repository.LecturerRepository;
import com.protaskify.protaskify_api.repository.ProjectRepository;
import com.protaskify.protaskify_api.repository.SemesterRepository;
import com.protaskify.protaskify_api.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@EnableScheduling
public class SemesterService {
    private final SemesterRepository semesterRepository;
    private final ProjectRepository projectRepository;

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
    public void saveSemester(Semester semester) {
        if (getActiveSemester() != null) {
            semester.setStatus(false);
        } else {
            semester.setStatus(true);
        }
        semesterRepository.save(semester);
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

    @Scheduled(cron = "0 * * * * ?")
    public void updateSemesterStatus() {
        List<Semester> semesters = semesterRepository.findAll();
        LocalDate today = LocalDate.now();
        for (Semester semester : semesters) {
            LocalDate endDate = semester.getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            if (endDate.equals(today)) {
                semester.setStatus(false);
                semesterRepository.save(semester);
                // setStatus for project
                List<Project> projects = projectRepository.findAll();
                for (Project project : projects) {
                    project.setStatus(false);
                }
                projectRepository.saveAll(projects);
            }
        }
    }
}
