package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DragAndDropPage extends BasePage {

    @FindBy(id = "draggable")
    private WebElement draggable;

    @FindBy(id = "droppable")
    private WebElement droppable;

    public DragAndDropPage(WebDriver driver) {
        super(driver);
    }

    public DragAndDropPage open() {
        openUrl("https://demoqa.com/droppable");
        return this;
    }

    public String performDragAndDrop() {
        dragAndDrop(draggable, droppable);
        return getText(droppable);
    }
}
