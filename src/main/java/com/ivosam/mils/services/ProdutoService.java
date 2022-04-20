package com.ivosam.mils.services;

import com.ivosam.mils.domain.Categoria;
import com.ivosam.mils.domain.Produto;
import com.ivosam.mils.repositories.CategoriaRepository;
import com.ivosam.mils.repositories.ProdutoRepository;
import com.ivosam.mils.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    ProdutoRepository repo;

    @Autowired
    CategoriaRepository categoriaRepository;

    public Produto buscar(Long id){
        Optional<Produto> obj = repo.findById(id);
        return obj.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado! Id:" +id +
                "Tipo " + Produto.class.getName()));
    }

    public Page<Produto> search(String nome, List<Long> ids, Integer page, Integer linesPerPage, String orderBy, String direction){
            PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
            List<Categoria> categorias = categoriaRepository.findAllById(ids);
            return repo.search(nome, categorias, pageRequest);
        }

}
