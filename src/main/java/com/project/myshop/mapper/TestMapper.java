package com.project.myshop.mapper;

import com.project.myshop.dto.TestMapperDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TestMapper {
    List<TestMapperDto> getMemberList();
}
