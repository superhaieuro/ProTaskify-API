package com.protaskify.protaskify_api.service;

import com.protaskify.protaskify_api.model.enity.Group;
import com.protaskify.protaskify_api.model.enity.Project;
import com.protaskify.protaskify_api.model.enity.Student;
import com.protaskify.protaskify_api.repository.GroupRepository;
import com.protaskify.protaskify_api.repository.ProjectRepository;
import com.protaskify.protaskify_api.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GroupService {
    private  final GroupRepository groupRepository;
    private final StudentRepository studentRepository;
    private final ProjectRepository projectRepository;

    public Group getGroup(Long groupId) {
        return groupRepository.getById(groupId);
    }

    public void updateGroupInfo(String oldLeaderId, String newLeaderId, String groupName) {
        Student oldLeader = studentRepository.findStudentById(oldLeaderId);
        if (!oldLeader.equals(newLeaderId)) {
            oldLeader.setLeader(false);
            studentRepository.save(oldLeader);
            Student newLeader = studentRepository.findStudentById(newLeaderId);
            newLeader.setLeader(true);
            studentRepository.save(newLeader);
        }
        Group group = oldLeader.getGroup();
        group.setName(groupName);
        groupRepository.save(group);
    }

    public void updateGroupProject(String studentId, Long projectId) {
        Student student = studentRepository.findStudentById(studentId);
        Group group = student.getGroup();
        Project project = projectRepository.getById(projectId);
        group.setProject(project);
        groupRepository.save(group);
    }
}
