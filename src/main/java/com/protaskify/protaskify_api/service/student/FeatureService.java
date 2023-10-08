package com.protaskify.protaskify_api.service.student;
import com.protaskify.protaskify_api.model.enity.Feature;
import com.protaskify.protaskify_api.model.enity.Group;
import com.protaskify.protaskify_api.model.enity.Student;
import com.protaskify.protaskify_api.model.response.AuthenticationResponse;
import com.protaskify.protaskify_api.repository.FeatureRepository;
import com.protaskify.protaskify_api.repository.ProcessRepository;
import com.protaskify.protaskify_api.repository.StudentRepository;
import com.protaskify.protaskify_api.service.auth.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FeatureService {

    private final FeatureRepository featureRepository;
    private final ProcessRepository processRepository;
    private final StudentRepository studentRepository;

    public Feature createFeature(Feature feature, String studentId) throws Exception {
        Student student = studentRepository.findById(studentId).orElse(null);
        if (student != null && student.is_leader()) {
            featureRepository.save(feature);
            processRepository.updateFeatureId(feature);

            return feature;
        }else {
            // Nếu sinh viên không phải là leader, ném ra một ngoại lệ
            throw new Exception("Chỉ leader của nhóm mới có thể tạo feature cho project này.");
        }
    }

}