package com.bazior;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductEntity{

    private String id;
    private String name;
    private String brand;
}
