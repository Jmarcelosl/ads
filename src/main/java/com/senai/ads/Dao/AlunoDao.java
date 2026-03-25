package com.senai.ads.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.senai.ads.Model.Aluno;

@Repository // <Aluno, Integer> ou seja <classeModel, tipo do ID - PK>
public interface AlunoDao extends JpaRepository<Aluno, Integer> {

}
