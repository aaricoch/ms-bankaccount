package pe.com.nttdbank.model;

import java.util.Date;

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
public class Operation extends BaseEntity {
    @Id
    @GeneratedValue
    private Long idOperation;
    private Long idBankAccount;
    private String concept;
    private Date operationDate;
    private Double amount;
    private double availableBalance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idOperationType", foreignKey = @ForeignKey(name = "FK_Operation_OperationType_idOperationType"))
    private OperationType operationType;

    public Operation() {
        super();
    }
}
