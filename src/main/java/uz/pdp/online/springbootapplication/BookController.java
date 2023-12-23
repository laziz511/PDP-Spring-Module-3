package uz.pdp.online.springbootapplication;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BookController {

    private final JdbcTemplate jdbcTemplate;

    @GetMapping("/")
    public String homePage(Model model) {
        String sql = "select * from book";
        List<Book> books = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Book.class));
        model.addAttribute("books", books);
        return "home";
    }

    @GetMapping("/book/create")
    public String bookCreatePage() {
        return "book_create";
    }

    @PostMapping("/book/create")
    public String bookCreate(@ModelAttribute BookCreateDTO dto) {
        String sql = "insert into book (title, description, price, author) values (?, ?, ?, ?)";
        jdbcTemplate.update(sql, dto.getTitle(), dto.getDescription(), dto.getPrice(), dto.getAuthor());
        return "redirect:/";
    }

    @GetMapping("/book/edit/{id}")
    public String bookEditPage(@PathVariable Long id, Model model) {
        String sql = "select * from book where id = ?";
        Book book = jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Book.class), id);
        model.addAttribute("book", book);
        return "book_edit";
    }

    @PostMapping("/book/update/{id}")
    public String bookUpdate(@PathVariable Long id, @ModelAttribute BookCreateDTO dto) {
        String sql = "update book set title = ?, description = ?, price = ?, author = ? where id = ?";
        jdbcTemplate.update(sql, dto.getTitle(), dto.getDescription(), dto.getPrice(), dto.getAuthor(), id);
        return "redirect:/";
    }

    @GetMapping("/book/delete/{id}")
    public String bookDelete(@PathVariable Long id) {
        String sql = "delete from book where id = ?";
        jdbcTemplate.update(sql, id);
        return "redirect:/";
    }
}
