package com.globot.hmi.attendance.dao.mapper;

import java.util.List;

import com.globot.hmi.attendance.domain.Menu;
import com.globot.hmi.attendance.domain.MenuExample;
import org.apache.ibatis.annotations.Param;

public interface MenuMapper {
    int countByExample(MenuExample example);

    int deleteByExample(MenuExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Menu record);

    int insertSelective(Menu record);

    List<Menu> selectByExample(MenuExample example);

    Menu selectByPrimaryKey(Integer id);
    
    List<Menu> selectParentMenu();
    
    List<Menu> selectChildMenuByParentId(Integer parentId);

    int updateByExampleSelective(@Param("record") Menu record, @Param("example") MenuExample example);

    int updateByExample(@Param("record") Menu record, @Param("example") MenuExample example);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);
}