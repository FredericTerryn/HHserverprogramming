package Be.fred.Bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import Be.fred.Bookstore.domain.Book;
import Be.fred.Bookstore.domain.BookRepository;
import Be.fred.Bookstore.domain.Category;
import Be.fred.Bookstore.domain.CategoryRepository;
import Be.fred.Bookstore.domain.User;
import Be.fred.Bookstore.domain.UserRepository;

@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(BookRepository repository, CategoryRepository crepo, UserRepository urepository){
	return(args)->{
			repository.save(new Book("Inception", "Dan Brown", 1996, 001502, 15, new Category("thriller")));
			repository.save(new Book("The Da Vinci Code", "Dan Brown", 1997, 001503, 25, new Category("thriller")));
			repository.save(new Book("The Hunger Games", "Suzan Collins", 2009, 001504, 20, new Category("thriller")));
			crepo.save(new Category("romantic"));
			
			User user1 = new User("user","$2a$06$ELomt8751IViDw8igZeV6.WFEPNV0HKmoFgyeWAksfjO5lN4zkd82","USER");
			User user2 =new User("admin","$2a$10$2JZ.csTWdfBvE0VJB4psDenu0SfVRAITH.FnLE8k9XqDRiAM0rr7i","ADMIN");
			urepository.save(user1);
			urepository.save(user2);
			
			log.info("fetch all students");
			for (Book book : repository.findAll()) {
				log.info(book.toString());
			}
	};
	}
}
