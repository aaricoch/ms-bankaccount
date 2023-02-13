package pe.com.nttdbank.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class BankAccount extends BaseEntity {
    @Id
    @GeneratedValue
    private Long idBankAccount;
    private Long idCustomer;
    private String bankAccountNumber;
    private String currency;
    private Double availableBalance;
    private String CCI;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idBankAccountType", foreignKey = @ForeignKey(name = "FK_BankAccount_BankAccountType_idBankAccount"))
    private BankAccountType bankAccountType;

    public BankAccount() {
        super();
    }
}
