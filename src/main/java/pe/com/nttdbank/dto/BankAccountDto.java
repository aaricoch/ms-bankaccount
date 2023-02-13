package pe.com.nttdbank.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BankAccountDto {
    private Long id;
    private Long idCliente;
    private String numeroCuenta;
    private int tipoCuenta;
    private String moneda;
    private Double saldo;
    private String CCI;

    public BankAccountDto() {
    }
}
