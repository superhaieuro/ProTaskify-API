package com.protaskify.protaskify_api.service;

import com.protaskify.protaskify_api.model.enity.Project;
import com.protaskify.protaskify_api.model.enity.Semester;
import com.protaskify.protaskify_api.repository.ProjectRepository;
import com.protaskify.protaskify_api.repository.SemesterRepository;
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

    @Scheduled(cron = "0 0 0 * * ?")
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


