package com.ivosam.mils.services.validation;

import com.ivosam.mils.domain.Cliente;
import com.ivosam.mils.domain.enums.TipoCliente;
import com.ivosam.mils.dto.ClienteNewDTO;
import com.ivosam.mils.repositories.ClienteRepository;
import com.ivosam.mils.resources.exceptions.FieldMessage;
import com.ivosam.mils.services.validation.utils.BR;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

    @Autowired
    private ClienteRepository repo;

    @Override
    public void initialize(ClienteInsert ann) {
    }

    @Override
    public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        if (objDto.getTipoCliente().equals(TipoCliente.PESSOA_FISICA.getCod()) && !BR.isValidCpf(objDto.getCnpjCpf())) {
            list.add(new FieldMessage("cnpjCpf", "CPF inválido"));
        }

        if (objDto.getTipoCliente().equals(TipoCliente.PESSOA_JURIDICA.getCod()) && !BR.isValidCnpj(objDto.getCnpjCpf())) {
            list.add(new FieldMessage("cnpjCpf", "CNPJ inválido"));
        }
        Cliente aux = repo.findByEmail(objDto.getEmail());
        if (aux != null) {
            list.add(new FieldMessage("email", "Email já existente"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();
    }
}