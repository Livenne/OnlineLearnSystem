package com.livenne.common.model.dto;

import com.livenne.annotation.Entity;
import com.livenne.annotation.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserOrderDTO {
    public Long userId;
    public Long courseId;
}
