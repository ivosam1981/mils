package com.ivosam.mils.dto;

import com.ivosam.mils.domain.Categoria;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class CategoriaDTO implements Serializable {
    private static final long serialVersionUID = 1l;

    private Long id;

    @NotEmpty(message = "Preenchimento obrigatorio")
    @Length(min=5, max=80, message = "O tamanho deve estar entre 5 e 80 caracteres")
    private String nome;

    public CategoriaDTO(Categoria obj) {
        id = obj.getId();
        nome = obj.getNome();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}
