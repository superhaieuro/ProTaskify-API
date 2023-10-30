package com.protaskify.protaskify_api.service;

import com.protaskify.protaskify_api.model.enity.Group;
import com.protaskify.protaskify_api.model.enity.Invitation;
import com.protaskify.protaskify_api.model.enity.Project;
import com.protaskify.protaskify_api.model.enity.Student;
import com.protaskify.protaskify_api.repository.GroupRepository;
import com.protaskify.protaskify_api.repository.InvitationRepository;
import com.protaskify.protaskify_api.repository.ProjectRepository;
import com.protaskify.protaskify_api.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupService {
    private final GroupRepository groupRepository;
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

    public void createGroup(Group group, String studentId) {
        Student student = studentRepository.findStudentById(studentId);
        student.setLeader(true);
        studentRepository.save(student);
        groupRepository.save(group);
    }

    public void deleteGroup(Long groupId, Long classId) {
        List<Student> studentList = studentRepository.findStudentByGroupId(classId, groupId);
        for (Student s: studentList) {
            if (s.isLeader())
                s.setLeader(false);
            s.setGroup(null);
        }
        Group group = groupRepository.getById(groupId);
        if (group != null)
            groupRepository.delete(group);
    }

    public void outGroup(String studentId) {
        Student student = studentRepository.findStudentById(studentId);
        if (student != null) {
            student.setGroup(null);
        }
        studentRepository.save(student);
    }

    public boolean getGroupStatus(Long groupId){
        Group group = groupRepository.getById(groupId);
        return group.isStatus();
    }
}
