package com.protaskify.protaskify_api.service;

import com.protaskify.protaskify_api.model.enity.Group;

public interface GroupService {
    public Group createGroup(Group group);

    public Group getGroupByID(String id);
}
