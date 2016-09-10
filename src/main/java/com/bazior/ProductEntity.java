package com.bazior;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Builder
@Document(indexName = "products")
@AllArgsConstructor
@NoArgsConstructor
public class ProductEntity{

    private String id;
    private String name;
    private String brand;
}
