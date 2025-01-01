package com.jn.ecommerce.customer.model;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Getter
@Setter
public class BaseDocument {

    @NotNull(message = "Creation time cannot be null")
    @CreatedDate
    @Field("create_time")
    private final LocalDateTime createTime = null;

    @NotNull(message = "Modification time cannot be null")
    @LastModifiedDate
    @Field("modify_time")
    private LocalDateTime modifyTime;
}
