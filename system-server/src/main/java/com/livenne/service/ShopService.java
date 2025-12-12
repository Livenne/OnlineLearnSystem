package com.livenne.service;

import java.util.List;

public interface ShopService {
    Boolean buy(Long userId,Long courseId);

    Boolean buy(Long userId, List<Long> courseIdList);
}
