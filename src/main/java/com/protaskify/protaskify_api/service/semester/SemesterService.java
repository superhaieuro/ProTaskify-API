package com.protaskify.protaskify_api.service.semester;

import com.protaskify.protaskify_api.model.enity.Semester;
import com.protaskify.protaskify_api.repository.SemesterRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SemesterService {
private final SemesterRepository semesterRepository;
    public List<Semester> findAllSemester(){
    return semesterRepository.findAll();
}

    //SAVE SEMESTER
    public Semester saveSemester(Semester semester){
        semesterRepository.save(semester);
        return semester;
    }

    //FIND SEMESTER
    public Semester finSemesterId(String semester_id){
            return semesterRepository.findById(semester_id).orElse(null);
    }

    //UPDATE SEMESTER
    public Semester updateSemester(String semester_id, Semester semesterDetails){
        Semester existingSemester = semesterRepository.findById(semester_id).orElse(null);
        if(existingSemester != null){
            existingSemester.setName(semesterDetails.getName());
            existingSemester.setStartDate(semesterDetails.getStartDate());
            existingSemester.setEndDate(semesterDetails.getEndDate());
            existingSemester.setStatus(semesterDetails.isStatus());
        }
        assert existingSemester != null;
        return semesterRepository.save(existingSemester);
    }

    //DELETE SEMESTER
    public Semester deleteSemester(String semester_id){
        Semester endSemester = semesterRepository.findById(semester_id).orElse(null);
        if (endSemester != null){
            semesterRepository.delete(endSemester);
        }
        return null;
    }
}
