package pl.pjatk.BookService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookServiceTest {

    private BookService bookService;

    @BeforeEach
    void setUp() {
        this.bookService = new BookService();
    }

    @Nested
    class GetBookByTitleTests {

        @Test
        void shouldReturnBookWhenExists() {
            final String title = "1984";

            final var result = bookService.getBookByTitle(title);

            assertEquals(title, result.getTitle());
            assertNotNull(result);
            assertEquals("George Orwell", result.getAuthor());
        }

        @Test
        void shouldThrowExceptionWhenBookDoesNotExist() {
            final String title = "Harry Potter";

            final var result = assertThrows(RuntimeException.class,
                    () -> bookService.getBookByTitle(title));

            assertNotNull(result);
            assertTrue(result.getMessage().contains("Book not found"));
        }
    }

    @Nested
    class UpdateBookPagesByTitleTests {

        @Test
        void shouldUpdateBookPagesByTitle() {
            final String title = "1984";
            final int page = 863;

            final var result = bookService.updateBookPagesByTitle(title, page);

            assertEquals(page, result.getPages());
            assertSame(result, bookService.getBookByTitle(title));
        }

        @Test
        void shouldReturnNullWhenBookDoesNotExist() {
            final String title = "Harry Potter";
            final int page = 863;

            final var result = bookService.updateBookPagesByTitle(title, page);

            assertNull(result);
        }

        @Test
        void shouldNotChangeOtherBooksWhenUpdatingOne() {
            final String updatedTitle = "1984";
            final int newPages = 999;
            bookService.updateBookPagesByTitle(updatedTitle, newPages);

            final var hobbit = bookService.getBookByTitle("The Hobbit");
            assertNotEquals(newPages, hobbit.getPages());
        }
    }

    @Nested
    class GetBooksWithinPagesTests {

        @Test
        void shouldReturnBooksWithinPages() {
            final int pageStart = 300;
            final int pageEnd = 400;

            final var result = bookService.getBooksWithinPages(pageStart, pageEnd);

            assertEquals(2, result.size());
            assertTrue(result.stream().allMatch(book -> book.getPages() >= pageStart && book.getPages() <= pageEnd));
        }

        @Test
        void shouldReturnEmptyListWhenBookDoesNotExist() {
            final int pageStart = 100;
            final int pageEnd = 200;

            final var result = bookService.getBooksWithinPages(pageStart, pageEnd);

            assertTrue(result.isEmpty());
        }

        @Test
        void shouldIncludeBoundaryValues() {
            final int pageStart = 310;
            final int pageEnd = 328;

            final List<Book> result = bookService.getBooksWithinPages(pageStart, pageEnd);

            assertFalse(result.isEmpty());
            assertTrue(result.stream().anyMatch(b -> b.getPages() == 310 || b.getPages() == 328));
        }
    }
}
