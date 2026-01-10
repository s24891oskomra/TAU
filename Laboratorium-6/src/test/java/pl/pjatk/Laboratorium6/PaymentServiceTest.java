package pl.pjatk.Laboratorium6;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PaymentServiceTest {

    @Autowired
    private PaymentService paymentService;

    @Test
    void test1_GetAllPayments() {
        List<Payment> payments = paymentService.getAllPayments();
        assertNotNull(payments);
        assertFalse(payments.isEmpty(), "Lista płatności nie powinna być pusta");
    }

    @Test
    void test2_GetPaymentById() {
        Payment payment = paymentService.getPaymentById(1);
        assertNotNull(payment);
        assertEquals(1, payment.getId());
    }

    @Test
    void test3_GetPaymentsByStatus() {
        List<Payment> completed = paymentService.getPaymentsByStatus("COMPLETED");
        assertNotNull(completed);
        assertTrue(completed.stream().allMatch(p -> p.getStatus().equals("COMPLETED")));
    }

    @Test
    void test4_GetPaymentsByCurrency() {
        List<Payment> plnPayments = paymentService.getPaymentsByCurrency("PLN");
        assertNotNull(plnPayments);
        assertTrue(plnPayments.stream().allMatch(p -> p.getCurrency().equals("PLN")));
    }

    @Test
    void test5_CreatePayment() {
        Payment newPay = new Payment(0, 500.0, "EUR", "NEW");
        Payment created = paymentService.createPayment(newPay);
        assertEquals(101, created.getId());
        assertEquals("EUR", created.getCurrency());
    }

    @Test
    void test6_UpdatePayment() {
        Payment updateData = new Payment(0, 99.0, "USD", "PENDING");
        Payment updated = paymentService.updatePayment(1, updateData);
        assertEquals(1, updated.getId());
        assertEquals("UPDATED", updated.getStatus());
    }

    @Test
    void test7_DeletePayment() {
        String result = paymentService.deletePayment(1);
        assertTrue(result.contains("deleted successfully"));
    }

    @Test
    void test8_CancelPayment() {
        Payment canceled = paymentService.cancelPayment(1);
        assertNotNull(canceled);
        assertEquals("CANCELED", canceled.getStatus());
    }

    @Test
    void test9_GetPaymentsAboveAmount() {
        List<Payment> highPayments = paymentService.getPaymentsAboveAmount(100.0);
        assertNotNull(highPayments);
        assertTrue(highPayments.stream().allMatch(p -> p.getAmount() >= 100.0));
    }

    @Test
    void test10_GetTotalPaymentCount() {
        long count = paymentService.getTotalPaymentCount();
        assertTrue(count > 0, "Liczba płatności powinna być większa od zera");
    }
}