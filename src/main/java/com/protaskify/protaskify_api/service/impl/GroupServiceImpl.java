package com.protaskify.protaskify_api.service.impl;

import com.protaskify.protaskify_api.model.enity.Group;
import com.protaskify.protaskify_api.repository.GroupRepository;
import com.protaskify.protaskify_api.service.GroupService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class GroupServiceImpl implements GroupService {
    private final GroupRepository groupRepository;
    @Override
    public Group createGroup(Group group) {
        return groupRepository.save(group);
    }

    @Override
    public Group getGroupByID(String id) {
        return groupRepository.findById(id).get();
    }
}
