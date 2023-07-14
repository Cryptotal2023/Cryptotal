package com.cryptotal.service.core.service;

import com.cryptotal.service.core.pojo.Member;

import java.util.List;

public interface MemService {
    public List<Member> list();

    public void delete(Integer id);

    public void add(Member mem);

    public void updateMem(Member mem);
}
