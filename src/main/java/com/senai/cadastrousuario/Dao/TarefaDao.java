package com.senai.cadastrousuario.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.senai.cadastrousuario.Model.Tarefa;

public interface TarefaDao extends JpaRepository<Tarefa, Long> {
    // Não precisa de escrever nada aqui dentro, o Spring Data faz a magia toda
    // sozinho! ✨
}