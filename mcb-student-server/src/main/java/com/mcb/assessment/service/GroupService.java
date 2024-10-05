package com.mcb.assessment.service;

import com.mcb.assessment.model.Group;

public interface GroupService {

    public  Group addGroup(Group group);

    public Group getGroupById(long id);

}
