package pl.pjatk.BookService;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class BookService {

    private final List<Book> books;

    public BookService() {
        this.books = List.of(
                new Book("1984", "George Orwell", 328, BookType.HORROR),
                new Book("Sapiens", "Yuval Noah Harari", 443, BookType.AUTOBIOGRAPHY),
                new Book("The Hobbit", "J.R.R. Tolkien", 310, BookType.FANTASY)
        );
    }

    public Book getBookByTitle(String title) {

        for (Book book : books) {
            if (book.getTitle().equals(title)) {
                return book;
            }
        }
        throw new RuntimeException("Book not found");
    }

    public Book updateBookPagesByTitle(String title, int page) {
        for (Book book : books) {
            if (book.getTitle().equals(title)) {
                book.setPages(page);
                return book;
            }
        }
        return null;
    }

    public List<Book> getBooksWithinPages(int pageStart, int pageEnd) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getPages() >= pageStart && book.getPages() <= pageEnd) {
                result.add(book);
            }
        }
        return result;
    }

}
