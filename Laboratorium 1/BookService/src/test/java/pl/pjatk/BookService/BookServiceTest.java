package pl.pjatk.BookService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookServiceTest {

    private BookService bookService;

    @BeforeEach
    void setUp() {
        this.bookService = new BookService();
    }

    @Nested
    class getBookByTitleTests {

        @Test
        void shouldReturnBookWhenExists() {
            final String title = "1984";

            final var result = bookService.getBookByTitle(title);

            assertEquals(title, result.getTitle());
            assertNotNull(result);
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
    class updateBookPagesByTitleTests {

        @Test
        void shouldUpdateBookPagesByTitle() {
            final String title = "1984";
            final int page = 863;

            final var result = bookService.updateBookPagesByTitle(title, page);

            assertEquals(page, result.getPages());
        }

        @Test
        void shouldReturnNullWhenBookDoesNotExist() {
            final String title = "Harry Potter";
            final int page = 863;

            final var result = bookService.updateBookPagesByTitle(title, page);

            assertNull(result);
        }
    }

    @Nested
    class getBooksWithinPagesTests {

        @Test
        void shouldReturnBooksWithinPages() {
            final int pageStart = 300;
            final int pageEnd = 400;

            final var result = bookService.getBooksWithinPages(pageStart, pageEnd);

            assertEquals(2, result.size());

        }

        @Test
        void shouldReturnEmptyListWhenBookDoesNotExist() {
            final int pageStart = 100;
            final int pageEnd = 200;

            final var result = bookService.getBooksWithinPages(pageStart, pageEnd);

            assertTrue(result.isEmpty());
        }
    }

}