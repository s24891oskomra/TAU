package tests;

import base.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.DragAndDropPage;

public class DragAndDropTest extends BaseTest {

    @Test
    void shouldDragElementToTarget() {
        DragAndDropPage page = new DragAndDropPage(driver).open();
        String textAfterDrop = page.performDragAndDrop();

        Assertions.assertTrue(textAfterDrop.contains("Dropped!"),
                "Text after drop should confirm successful action");
    }
}