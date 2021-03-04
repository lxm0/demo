package elasticsearch;

import com.example.demo.DemoApplication;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.JestResult;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.core.Delete;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import io.searchbox.indices.CreateIndex;
import io.searchbox.indices.DeleteIndex;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.elasticsearch.index.query.QueryBuilders.termQuery;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class ElasticsearchTest {


    @Test
    public void contextLoads() {
        BookRequest bookRequest = new BookRequest();
        BookDocument bookDocument = new BookDocument();

        bookDocument.setId("002");
        bookDocument.setBookName("book name");
        bookDocument.setPages(100);
        bookDocument.setDesc("book desc");
        bookDocument.setBookAuthor("ano");
        bookRequest.setId(bookDocument.getId());
        bookRequest.setBody(bookDocument);
        bookRequest.setTypeName("book");
        bookRequest.setIndexName("hfaudit.alert_batch");
        bookRequest.setKeyword("desc");

        JestClient jestClient = getJestCline();
        // 添加索引
        CreateIndex createIndex = new CreateIndex.Builder(bookRequest.getIndexName()).build();
        //删除索引
        DeleteIndex deleteIndex = new DeleteIndex.Builder(bookRequest.getIndexName()).build();
        // 添加文档，相当于mysql中的一条数据
        Index.Builder builder = new Index.Builder(bookRequest.getBody());
        Index index = builder.index(bookRequest.getIndexName()).type(bookRequest.getTypeName()).build();
        // 删除文档
        Delete deleteDocument =
                new Delete.Builder(bookRequest.getId()).index(bookRequest.getIndexName()).type(bookRequest.getTypeName()).build();
        // 查询
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        boolQueryBuilder.must(termQuery("id", bookDocument.getId()));
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(boolQueryBuilder);
        try {
            //执行
            JestResult result = jestClient.execute(index);
            System.out.println(result);

            SearchResult searchResult = jestClient.execute(new Search.Builder(searchSourceBuilder.toString())
                                                             .addIndex(bookRequest.getIndexName())
                                                             .addType(bookRequest.getTypeName())
                                                             .build());
            System.out.println(searchResult);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public JestClient getJestCline(){
        JestClientFactory factory = new JestClientFactory();
        factory.setHttpClientConfig(new HttpClientConfig
                .Builder("http://tdh60dev02:9200")
                                            .multiThreaded(true)
                                            .build());
        return  factory.getObject();
    }

}
