package com.livenne.repository;

import com.livenne.BaseMapper;
import com.livenne.MatchType;
import com.livenne.annotation.*;
import com.livenne.common.model.entity.Info;

import java.util.List;

@Repository
public interface InfoRepository extends BaseMapper<Info> {

    @Query
    Info getById(@Cond("infoId") Long infoId);

    @Query
    List<Info> getAll();

    @Query
    List<Info> queryName(@Cond(value = "name", type = MatchType.LIKE) String name);
}
