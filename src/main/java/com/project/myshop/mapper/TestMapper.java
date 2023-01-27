package com.project.myshop.mapper;

import com.project.myshop.domain.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TestMapper {
    List<Member> getMemberList();
}
