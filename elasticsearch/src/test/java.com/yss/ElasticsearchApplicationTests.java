package java.com.yss;



import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ElasticsearchApplicationTests {

	/*@Autowired
	private ItemRepository repository;
	@Autowired
	private BookRepository bookRespistory;
	@Autowired
	RestHighLevelClient restHighLevelClient;
	*//**
	 * 不传入id表示添加操作，会自动生成id
	 * 传入id，ES中有此id表示更新，没有表示添加
	 *
	 * 添加索引和更新索引 id 存在更新 不存在添加
	 *//*

	@Test
	public void testSaveOrUpdate(){
		Book book = new Book();
		book.setId("23");
		book.setName("小名");
		book.setCreateDate(new Date());
		book.setAuthor("杜甫");
		book.setContent("这是中国的好人,这真的是一个很好的人,杜甫很狂");
		bookRespistory.save(book);
	}
	@Test
	public void testDelete(){
		Book book = new Book();
		book.setId("21");
		bookRespistory.delete(book);
	}
	*//**
	 *  查询一个
	 *//*
	@Test
	public void testFindOne(){
		Optional<Book> byId = bookRespistory.findById("21");
		System.out.println(byId.get());
	}

	*//**
	 *  查询所有
	 *//*
	@Test
	public void testFindAll(){
		Iterable<Book> books = bookRespistory.findAll();
		for (Book book : books) {
			System.out.println(book);
		}
	}

	*//**
	 * 查询并排序
	 *//*
	@Test
	public void testFindAllOrder() {
		Iterable<Book> books = bookRespistory.findAll(Sort.by(Sort.Order.asc("createDate")));
		books.forEach(book -> System.out.println(book));
	}
	@Test
	public void testSearchPage() throws IOException {
		//查询请求
		SearchRequest searchRequest = new SearchRequest();

		//查询条件
		SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
		sourceBuilder.from(0).size(2).sort("id", SortOrder.ASC).query(QueryBuilders.matchAllQuery());

		//去哪个索引/类型查询
		searchRequest.indices("item").types("docs").source(sourceBuilder);

		//====>查询方法
		SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

		SearchHit[] hits = search.getHits().getHits();
		for (SearchHit hit : hits) {
			//字符串格式展示
			System.out.println(hit.getSourceAsString());
		}
	}*/

}
