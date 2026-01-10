package pl.pjatk.Laboratorium6;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    private int id;
    private double amount;
    private String currency;
    private String status;
}
