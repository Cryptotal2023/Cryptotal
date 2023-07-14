package com.cryptotal.service.core.controller;

import com.cryptotal.service.core.pojo.Member;
import com.cryptotal.service.core.pojo.Result;
import com.cryptotal.service.core.service.MemService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class MemController {

    private final MemService memService;
    @Autowired
    public MemController(MemService memService) {
        this.memService = memService;
    }

    @GetMapping("/list")
    public Result list() {
        log.info("Display all members' info");
        List<Member> memList = memService.list();
        return Result.success(memList);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        log.info("Delete by Member ID: {}",id);
        memService.delete(id);
        return Result.success();
    }

    @PostMapping("/add")
    public Result add(@RequestBody Member mem) {
        log.info("Add new member: {}", mem);
        memService.add(mem);
        return Result.success();
    }

    @PutMapping("update/{id}")
    public Result updateMem(@PathVariable("id") Long id, @RequestBody Member mem) {
        log.info("Update member {}'s information", id);
        mem.setUserId(id);
        memService.updateMem(mem);
        return Result.success();
    }



}




