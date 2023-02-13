package pe.com.nttdbank.repository;

import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import pe.com.nttdbank.model.BankAccount;

public interface BankAccountRepository extends PanacheRepository<BankAccount> {
    List<BankAccount> getAll();

    BankAccount getById(Long id);

    Boolean Create(BankAccount bankAccount);

    Boolean Delete(Long id);

    Long CountByBankAccount(String bankAccountNumber);
}
