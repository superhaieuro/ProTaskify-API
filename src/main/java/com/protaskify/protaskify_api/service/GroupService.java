package com.protaskify.protaskify_api.service;

import com.protaskify.protaskify_api.model.enity.Group;

import java.util.List;

public interface GroupService {
    public List<Group> getAll();

    public Group save(Group group);

    public void delete(Group group);
}
