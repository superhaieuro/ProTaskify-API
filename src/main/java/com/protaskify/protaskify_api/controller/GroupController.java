package com.protaskify.protaskify_api.controller;

import com.protaskify.protaskify_api.model.enity.Group;
import com.protaskify.protaskify_api.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.hibernate.id.IdentifierGenerator.ENTITY_NAME;
import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/group")
@CrossOrigin
public class GroupController {
    @Autowired
    private final GroupService groupService;

    private final String APPLICATION_NAME = "protaskify_api";

    @GetMapping("/getall")
    public ResponseEntity<List<Group>> getAll(){
        return ResponseEntity.ok(groupService.getAll());
    }

    @PostMapping("/create")
    public ResponseEntity<Group> create(Group group){
        return ResponseEntity.ok(groupService.save(group));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete(Group group){
        groupService.delete(group);
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-" + APPLICATION_NAME + "-alert", ENTITY_NAME);
        headers.add("X-" + APPLICATION_NAME + "-params", id.toString());
        return ResponseEntity
                .noContent()
                .headers(headers)
                .build();
    }
}
