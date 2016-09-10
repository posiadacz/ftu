package com.bazior;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FtuApplicationTests {

    public static final String PRODUCT = "products";
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    private void indexDocument(){
        elasticsearchTemplate.deleteIndex(PRODUCT);
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

    private List<ProductEntity> query(String queryString){

        Criteria criteria = new Criteria("name");
        criteria.contains(queryString);
        CriteriaQuery criteriaQuery = new CriteriaQuery(criteria);
        return elasticsearchTemplate.queryForList(criteriaQuery, ProductEntity.class);
    }

	@Test
	public void elasticConnectionTest() {
        indexDocument();
        List<ProductEntity> list = query("foo");
        assertNotNull(list);
        assertEquals(1, list.size());
	}

}
