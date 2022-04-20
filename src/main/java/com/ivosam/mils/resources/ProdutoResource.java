package com.ivosam.mils.resources;

import com.ivosam.mils.domain.Categoria;
import com.ivosam.mils.domain.Produto;
import com.ivosam.mils.dto.CategoriaDTO;
import com.ivosam.mils.dto.ProdutoDTO;
import com.ivosam.mils.resources.utils.URL;
import com.ivosam.mils.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {

    @Autowired
    ProdutoService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Produto> find(@PathVariable Long id) {
        Produto obj = service.buscar(id);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<ProdutoDTO>> findPage(
                                                        @RequestParam(value = "nome", defaultValue = "0") String nome,
                                                        @RequestParam(value = "categorias", defaultValue = "0") String categorias,
                                                        @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                        @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
                                                        @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
                                                        @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        List<Long> ids = URL.decodeLogList(categorias);
        String nomeDecoded = URL.decodeParam(nome);
        Page<Produto> list = service.search(nomeDecoded, ids, page, linesPerPage, orderBy, direction);
        Page<ProdutoDTO> listDTO = list.map(obj -> new ProdutoDTO(obj));
        return ResponseEntity.ok().body(listDTO);
    }
}
