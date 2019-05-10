package com.tongzhu.treehouse.mapper.ext;

import com.tongzhu.treehouse.model.Furniture;

import java.util.List;

public interface FurnitureExtMapper {

    void batchInsert(List<Furniture> list);
}
