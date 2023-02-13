package pe.com.nttdbank.service;

import java.util.List;

import pe.com.nttdbank.dto.BankAccountDto;

public interface BankAccountService {
    List<BankAccountDto> getAll();

    BankAccountDto getById(Long id);

    Boolean Create(BankAccountDto customerDto);

    // Boolean Edit(Long id, BankAccountDto customerDto);

    Boolean Delete(Long id);

}
