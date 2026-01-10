# Dokumentacja API Procesora Płatności (Atrapa)

System umożliwia symulację operacji płatniczych. API jest zamockowane – dane pochodzą z pliku JSON.

## Endpointy:
1. `GET /payments` - Pobiera listę wszystkich płatności.
2. `GET /payments/{id}` - Pobiera szczegóły płatności po ID.
3. `GET /payments/status/{status}` - Filtruje płatności po statusie (PENDING, COMPLETED, FAILED).
4. `GET /payments/currency/{code}` - Pobiera płatności w danej walucie.
5. `POST /payments` - Tworzy nową płatność (zwraca obiekt z nadanym ID).
6. `PUT /payments/{id}` - Aktualizuje dane płatności.
7. `DELETE /payments/{id}` - Usuwa płatność z systemu.
8. `PATCH /payments/{id}/cancel` - Anuluje płatność (zmienia status na CANCELED).
9. `GET /payments/search?minAmount=100` - Wyszukuje płatności powyżej kwoty.
10. `GET /payments/summary` - Zwraca podsumowanie (liczbę transakcji).