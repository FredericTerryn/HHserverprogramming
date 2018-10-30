package Be.fred.Bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import Be.fred.Bookstore.domain.Book;
import Be.fred.Bookstore.domain.BookRepository;
import Be.fred.Bookstore.domain.Category;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookstoreRepositoryTest {
	@Autowired
	private BookRepository brepo;
	
	@Test
	public void findByTitleShoudReturnBook() {
		List<Book> books = brepo.findByTitle("Inception");
		assertThat(books).hasSize(1);
	}
	
	
	@Test
	public void createNewBook() {
		Book book = new Book("Testboek", "schrijver", 15, 15, 15, new Category("romance"));
		brepo.save(book);
		assertThat(book.getId()).isNotNull();
	}
	
	@Test
	public void deleteBook() {
		long id= 1;
		brepo.deleteById(id);
		assertThat(brepo.findById(id)).isNull();
	}
	
}
