package com.cryptotal.service.core.mapper;

import com.cryptotal.service.core.pojo.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MemMapper {

    public List<Member> list();

    public void deleteById(Integer id);

    public void addMem(Member mem);

    public void updateMem(Member mem);
}
