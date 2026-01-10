package pl.pjatk.Laboratorium6;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentService {

    private final ObjectMapper objectMapper = new ObjectMapper();

    private List<Payment> loadData() {
        try {
            InputStream is = getClass().getResourceAsStream("/data.json");
            return objectMapper.readValue(is, new TypeReference<List<Payment>>() {});
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public List<Payment> getAllPayments() {
        return loadData();
    }

    public Payment getPaymentById(int id) {
        return loadData().stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<Payment> getPaymentsByStatus(String status) {
        return loadData().stream()
                .filter(p -> p.getStatus().equalsIgnoreCase(status))
                .collect(Collectors.toList());
    }

    public List<Payment> getPaymentsByCurrency(String currency) {
        return loadData().stream()
                .filter(p -> p.getCurrency().equalsIgnoreCase(currency))
                .collect(Collectors.toList());
    }

    public Payment createPayment(Payment newPayment) {
        newPayment.setId(101);
        return newPayment;
    }

    public Payment updatePayment(int id, Payment updatedData) {
        updatedData.setId(id);
        updatedData.setStatus("UPDATED");
        return updatedData;
    }

    public String deletePayment(int id) {
        return "Payment " + id + " deleted successfully (Mocked)";
    }

    public Payment cancelPayment(int id) {
        Payment p = getPaymentById(id);
        if (p != null) p.setStatus("CANCELED");
        return p;
    }

    public List<Payment> getPaymentsAboveAmount(double minAmount) {
        return loadData().stream()
                .filter(p -> p.getAmount() >= minAmount)
                .collect(Collectors.toList());
    }

    public long getTotalPaymentCount() {
        return loadData().size();
    }
}