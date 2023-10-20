package com.protaskify.protaskify_api.service.semester;

import com.protaskify.protaskify_api.model.enity.Semester;
import com.protaskify.protaskify_api.repository.SemesterRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SemesterService {
private final SemesterRepository semesterRepository;
    public List<Semester> findAllSemester(){
    return semesterRepository.findAll();
}

}
