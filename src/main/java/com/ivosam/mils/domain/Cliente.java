package com.ivosam.mils.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ivosam.mils.domain.enums.TipoCliente;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
public class Cliente implements Serializable {
    private static final long seriaVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    @Column(unique = true)
    private String email;
    private String cnpjCpf;
    private Integer tipoCliente;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Endereco> enderecos = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "TELEFONE")
    private Set<String> telefones = new HashSet<>();

    @OneToMany(mappedBy = "cliente")
    @JsonIgnore
    List<Pedido> pedidos = new ArrayList<>();

    public Cliente() {
    }

    public Cliente(Long id, String nome, String email, String cnpjCpf, TipoCliente tipoCliente) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cnpjCpf = cnpjCpf;
        this.tipoCliente = (tipoCliente == null) ? null : tipoCliente.getCod();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCnpjCpf() {
        return cnpjCpf;
    }

    public void setCnpjCpf(String cnpjCpf) {
        this.cnpjCpf = cnpjCpf;
    }

    public TipoCliente getTipoCliente() {
        return TipoCliente.toEnum(tipoCliente);
    }

    public void setTipoCliente(TipoCliente tipoCliente) {
        this.tipoCliente = tipoCliente.getCod();
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public Set<String> getTelefones() {
        return telefones;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return id.equals(cliente.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
