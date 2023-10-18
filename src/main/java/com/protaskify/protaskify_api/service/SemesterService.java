package com.protaskify.protaskify_api.service;

import com.protaskify.protaskify_api.model.enity.Semester;
import com.protaskify.protaskify_api.repository.SemesterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SemesterService {
    private final SemesterRepository semesterRepository;

    public Semester getActiveSemester() {
        Optional<Semester> semester = semesterRepository.findAllByStatus(true);
        if (semester.isPresent()) {
            return semester.get();
        }
        return null;
    }
}
