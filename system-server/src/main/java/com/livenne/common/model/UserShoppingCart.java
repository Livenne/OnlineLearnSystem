package com.livenne.common.model;

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
@Entity("user_shoppingcart")
public class UserShoppingCart {
    @Id
    private Long id;
    private Long userId;
    private Long courseId;
}
