package com.livenne.repository;

import io.github.livenne.BaseMapper;
import io.github.livenne.MatchType;
import com.livenne.common.model.entity.Info;

import java.util.List;
import io.github.livenne.annotation.orm.Repository;
import io.github.livenne.annotation.orm.Cond;
import io.github.livenne.annotation.orm.Query;

@Repository
public interface InfoRepository extends BaseMapper<Info> {

    @Query
    Info getById(@Cond("infoId") Long infoId);

    @Query
    List<Info> getAll();

    @Query
    List<Info> queryName(@Cond(value = "name", type = MatchType.LIKE) String name);
}
