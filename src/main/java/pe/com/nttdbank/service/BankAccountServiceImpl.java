package pe.com.nttdbank.service;

import java.util.ArrayList;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import pe.com.nttdbank.dto.BankAccountDto;
import pe.com.nttdbank.model.BankAccount;
import pe.com.nttdbank.model.BankAccountType;
import pe.com.nttdbank.repository.BankAccountRepository;

@ApplicationScoped
public class BankAccountServiceImpl implements BankAccountService {

    @Inject
    BankAccountRepository bankAccountRepository;

    public List<BankAccountDto> getAll() {
        List<BankAccount> bankAccounts = bankAccountRepository.getAll();
        List<BankAccountDto> bankAccountDtos = new ArrayList<BankAccountDto>();

        bankAccounts.forEach(bankAccount -> {
            bankAccountDtos.add(toBankAccountDto(bankAccount));
        });

        return bankAccountDtos;
    }

    public BankAccountDto getById(Long id) {
        BankAccount bankAccount = bankAccountRepository.getById(id);
        if (bankAccount == null) {
            return null;
        }
        return toBankAccountDto(bankAccount);
    }

    public Boolean Create(BankAccountDto bankAccountDto) {
        BankAccount bankAccount = toBankAccount(bankAccountDto);

        // Validar Cliente

        // Validar Cuenta
        if (bankAccountRepository.CountByBankAccount(bankAccountDto.getNumeroCuenta()) > 0) {
            // throw new NullPointerException("Already registered BankAccount");
            return false;
        }

        return bankAccountRepository.Create(bankAccount);
    }

    public Boolean Delete(Long id) {
        BankAccount bankAccount = bankAccountRepository.getById(id);
        if (bankAccount == null) {
            // throw new NullPointerException("BankAccount not fount");
            return false;
        }

        return bankAccountRepository.Delete(id);
    }

    private BankAccountDto toBankAccountDto(BankAccount bankAccount) {
        BankAccountDto bankAccountDto = new BankAccountDto();

        bankAccountDto.setId(bankAccount.getIdBankAccount());
        bankAccountDto.setIdCliente(bankAccount.getIdCustomer());
        bankAccountDto.setNumeroCuenta(bankAccount.getBankAccountNumber());
        bankAccountDto.setTipoCuenta(bankAccount.getBankAccountType().getIdBankAccountType());
        bankAccountDto.setMoneda(bankAccount.getCurrency());
        bankAccountDto.setSaldo(bankAccount.getAvailableBalance());
        bankAccountDto.setCCI(bankAccount.getCCI());

        return bankAccountDto;
    }

    private BankAccount toBankAccount(BankAccountDto bankAccountDto) {
        BankAccount bankAccount = new BankAccount();

        bankAccount.setIdBankAccount(bankAccountDto.getId());
        bankAccount.setIdCustomer(bankAccountDto.getIdCliente());
        bankAccount.setBankAccountNumber(bankAccountDto.getNumeroCuenta());
        BankAccountType bankAccountType = new BankAccountType();
        bankAccountType.setIdBankAccountType(bankAccountDto.getTipoCuenta());
        bankAccount.setBankAccountType(bankAccountType);
        bankAccount.setCurrency(bankAccountDto.getMoneda());
        bankAccount.setAvailableBalance(bankAccountDto.getSaldo());
        bankAccount.setCCI(bankAccountDto.getCCI());

        return bankAccount;
    }

}
