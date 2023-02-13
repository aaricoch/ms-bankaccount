package pe.com.nttdbank.repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import pe.com.nttdbank.common.StatusType;
import pe.com.nttdbank.model.BankAccount;

@ApplicationScoped
public class BankAccountRepositoryImpl implements BankAccountRepository {

    public List<BankAccount> getAll() {
        return BankAccount.list("state", StatusType.Active.value);
    }

    public BankAccount getById(Long id) {
        Optional<BankAccount> bankAccount = BankAccount.findByIdOptional(id);
        if (!bankAccount.isPresent()) {
            return null;
        }
        return bankAccount.get();
    }

    @Transactional
    public Boolean Create(BankAccount bankAccount) {
        Boolean result = false;

        bankAccount.State = StatusType.Active.value;
        bankAccount.AuditCreateUser = 1;
        bankAccount.AuditCreateDate = new Date();

        bankAccount.persist();
        if (bankAccount.isPersistent()) {
            result = true;
        }

        return result;
    }

    @Transactional
    public Boolean Delete(Long id) {
        Boolean result = false;
        BankAccount bankAccount = getById(id);

        bankAccount.State = StatusType.Inactive.value;
        bankAccount.AuditDeleteUser = 1;
        bankAccount.AuditDeleteDate = new Date();
        bankAccount.persist();

        if (bankAccount.isPersistent()) {
            result = true;
        }

        return result;
    }

    public Long CountByBankAccount(String bankAccountNumber) {
        Map<String, Object> params = new HashMap<>();
        params.put("bankaccountnumber", bankAccountNumber);
        params.put("state", StatusType.Active.value);

        String query = "bankaccountnumber = :bankaccountnumber and state = :state";

        return BankAccount.count(query, params);
    }

}
