package Be.fred.Bookstore.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import Be.fred.Bookstore.domain.Book;
import Be.fred.Bookstore.domain.BookRepository;
import Be.fred.Bookstore.domain.CategoryRepository;

@Controller
public class BookstoreController {
	
	@Autowired
	private BookRepository bookstoreRepository;
	@Autowired CategoryRepository CRepository;

	@GetMapping("/index")
	public String index() {
		return "index";
	}
	
	//SHOW ALL BOOKS WITH THYMELEAF
	@GetMapping("/booklist")
	public String booklist(Model model) {
		model.addAttribute("books", bookstoreRepository.findAll());
		return "booklist";
	}
	
	//REST to get all books
	@GetMapping(value="/books")
	public @ResponseBody List<Book> bookListRest(){
		return (List<Book>) bookstoreRepository.findAll();
	}
	
	
	//REST find book by id
	@GetMapping(value="/book/{id}")
	public @ResponseBody Optional<Book> findStudentRest(@PathVariable("id") Long id) {
		return bookstoreRepository.findById(id);
	}
	
	
	//BEGIN SAVE
	@RequestMapping(value="/add")
	public String addBook(Model model) {
		model.addAttribute("book", new Book());
		model.addAttribute("categories", CRepository.findAll());
		return "addBook";
	}
	
	@PostMapping("/save")
	public String save(Book book) {
		bookstoreRepository.save(book);
		return "redirect:booklist";
	}
	//END SAVE
	
	//begin UPDATE
	@GetMapping(value="/update/{id}")
	public String updateBook(@PathVariable("id") Long id, Model model) {
		model.addAttribute("book", bookstoreRepository.findById(id));
		model.addAttribute("categories", CRepository.findAll());
		return "update";
	}
	
	@PostMapping(value = "update/upsave")
	public String upsave(Book book) {
		Long id = book.getId();
		bookstoreRepository.deleteById(book.getId());
		book.setId(id);
		bookstoreRepository.save(book);
		return "redirect:../booklist";
	}
	//end UPDATE
	
	
	//delete
	@GetMapping("/delete/{id}")
	public String deleteBook(@PathVariable("id")Long Id, Model model) {
		bookstoreRepository.deleteById(Id);
		return "redirect:../booklist";
	}
	
	//login
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	
}
