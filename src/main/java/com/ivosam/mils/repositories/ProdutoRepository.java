package com.ivosam.mils.repositories;

import com.ivosam.mils.domain.Categoria;
import com.ivosam.mils.domain.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    @Transactional(readOnly = true)
    @Query("select distinct obj from Produto obj inner join obj.categorias cat " +
            " WHERE  obj.nome LIKE %:nome% AND cat in :categorias")
    Page<Produto> search(@Param("nome") String nome, @Param("categorias") List<Categoria> categorias, Pageable pageRequest);


}
