package com.protaskify.protaskify_api.controller;

import com.protaskify.protaskify_api.model.enity.Group;
import com.protaskify.protaskify_api.model.enity.Invitation;
import com.protaskify.protaskify_api.model.enity.Student;
import com.protaskify.protaskify_api.repository.GroupRepository;
import com.protaskify.protaskify_api.repository.InvitationRepository;
import com.protaskify.protaskify_api.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/group")
@CrossOrigin
public class GroupController {
    private final GroupService groupService;

    private final GroupRepository groupRepository;

    private final InvitationRepository invitationRepository;
    
    @PostMapping("/create")
    public ResponseEntity<?> createGroup(@RequestBody Group group){
        Optional<Group> byId = groupRepository.findById(group.getGroupId());
        if(byId.isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        groupService.createGroup(group);
        return ResponseEntity.ok(group);
    }

    @GetMapping("/unlock")
    public ResponseEntity<Group> unlockGroup(@RequestParam("id") String id)
    {
        Group group = groupService.getGroupByID(id);
        group.setIsActive(true);
        groupRepository.save(group);
        return ResponseEntity.ok(group);
    }

    @DeleteMapping("/remove")
    public ResponseEntity<?> remove(@RequestParam("id") String id){
        Group group = groupService.getGroupByID(id);
        group.setStudents(null);
        groupRepository.save(group);
        groupRepository.delete(group);
        return ResponseEntity.status(HttpStatus.OK).body("");
    }

    @GetMapping("/invite")
    public ResponseEntity<?> Invitation(@RequestParam("id") String id, @RequestParam List<Student> students){
        List<Invitation> list = new ArrayList<>();
        for(Student i : students){
            Invitation invitation = new Invitation();
            invitation.setGroupID(groupRepository.findById(id).get());
            invitation.setReceiverID(i);
            invitation.setCreatedAt(Instant.now());
            list.add(invitation);
        }
        invitationRepository.saveAll(list);
        return ResponseEntity.status(HttpStatus.OK).body("");
    }


}
