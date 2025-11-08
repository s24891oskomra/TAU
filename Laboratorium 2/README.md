# Selenium JUnit 5 – DemoQA Tests

## Strony testowe
- [DemoQA Practice Form](https://demoqa.com/automation-practice-form)
- [DemoQA Droppable](https://demoqa.com/droppable)

## Scenariusz 1 – Formularz kontaktowy
**Cel:** Zweryfikować możliwość wypełnienia i wysłania formularza.  
**Kroki:**
1. Otwórz stronę formularza.  
2. Wprowadź imię, nazwisko i e-mail.  
3. Kliknij przycisk **Submit**.  
4. Sprawdź, że strona zareagowała poprawnie (brak błędów, poprawny tytuł strony).

## Scenariusz 2 – Drag and Drop
**Cel:** Sprawdzić, czy element można przeciągnąć i upuścić.  
**Kroki:**
1. Otwórz stronę „Droppable”.  
2. Przeciągnij element `draggable` na `droppable`.  
3. Sprawdź, że tekst pola docelowego zmienia się na `Dropped!`.

## Uruchamianie testów
```bash
mvn clean test -Dbrowser=chrome
mvn clean test -Dbrowser=firefox
```