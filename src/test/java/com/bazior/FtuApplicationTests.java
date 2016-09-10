package com.bazior;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FtuApplicationTests {

    public static final String PRODUCT = "product";
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    private void indexDocument(){
        elasticsearchTemplate.createIndex(PRODUCT);
        ProductEntity product = ProductEntity
                .builder()
                .id("123")
                .name("foo name")
                .brand(("bar brand"))
                .build();

        IndexQuery indexQuery = new IndexQueryBuilder()
                .withId(product.getId())
                .withObject(product)
                .withIndexName(PRODUCT)
                .build();
        elasticsearchTemplate.index(indexQuery);
        elasticsearchTemplate.refresh(PRODUCT);
    }


	@Test
	public void elasticConnectionTest() {
        indexDocument();


	}

}
