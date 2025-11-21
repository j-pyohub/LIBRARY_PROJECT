package com.oopsw.erp_project.dao;

import com.oopsw.erp_project.vo.Item;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ItemDAO {
    List<Item> getItems();
}
