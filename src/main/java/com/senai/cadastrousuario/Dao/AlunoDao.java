package com.senai.cadastrousuario.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.senai.cadastrousuario.Model.Aluno;

@Repository // <Aluno, Integer> ou seja <classeModel, tipo do ID - PK>
public interface AlunoDao extends JpaRepository<Aluno, Integer> {

    public Aluno findByEmailAndSenha(String email, String senha);
}
