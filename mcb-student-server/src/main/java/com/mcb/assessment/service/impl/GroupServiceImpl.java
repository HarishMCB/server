package com.mcb.assessment.service.impl;

import com.mcb.assessment.exceptionhandler.GroupNotFound;
import com.mcb.assessment.exceptionhandler.StudentNotFound;
import com.mcb.assessment.model.Group;
import com.mcb.assessment.repository.GroupRepository;
import com.mcb.assessment.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GroupServiceImpl implements GroupService {
    @Autowired
    GroupRepository groupRepository;
    @Override
    public Group addGroup(Group group) {
        return groupRepository.save(group);
    }

    @Override
    public Group getGroupById(long id) {
         Optional<Group>  group =groupRepository.findById(id);
         group.orElseThrow(()-> new GroupNotFound(id));
         return group.get();
    }
}
