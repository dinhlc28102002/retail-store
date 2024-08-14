package com.rs.retailstore.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Greeting {
    private Long id;
    private String content;
}
