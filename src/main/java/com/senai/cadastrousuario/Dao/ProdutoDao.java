package com.senai.cadastrousuario.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.senai.cadastrousuario.Model.Produto;

public interface ProdutoDao extends JpaRepository<Produto, Integer> {
}