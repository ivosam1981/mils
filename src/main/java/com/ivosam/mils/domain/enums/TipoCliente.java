package com.ivosam.mils.domain.enums;

public enum TipoCliente {
    PESSOA_FISICA(1, "Pessoa Física"), //0
    PESSOA_JURIDICA(2, "Pessoa Jurídica"); //1

    private int cod;
    private String descricao;

    private TipoCliente(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public int getCod() {
        return cod;
    }

    public String getDescricao() {
        return descricao;
    }

    public static TipoCliente toEnum(Integer cod) {
        if (cod == null) {
            return null;
        }
        for (TipoCliente tipo : TipoCliente.values()) {
            if (cod.equals(tipo.getCod())) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Id invalido:" + cod);
    }
}
