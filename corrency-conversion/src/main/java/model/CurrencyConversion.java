package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CurrencyConversion {
private Long id;
private String from;
private String to;
private BigDecimal quantity;
private BigDecimal exchangeMultiple;
private BigDecimal calculatedAmount;
private String environment;
}
