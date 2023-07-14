package com.cryptotal.service.core.service.impl;

import com.cryptotal.service.core.mapper.MemMapper;
import com.cryptotal.service.core.pojo.Member;
import com.cryptotal.service.core.service.MemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class MemServiceImpl implements MemService {

    private final MemMapper memMapper;

    @Autowired
    public MemServiceImpl(MemMapper memMapper) {
        this.memMapper = memMapper;
    }

    @Override
    public List<Member> list() {
        List<Member> memberList = memMapper.list();
        return memberList;
    }

    @Override
    public void delete(Integer id) {
        memMapper.deleteById(id);
    }

    @Override
    public void add(Member mem) {
        mem.setMemberSince(LocalDateTime.now());
        memMapper.addMem(mem);
    }

    @Override
    public void updateMem(Member mem) {
        memMapper.updateMem(mem);
    }
}
