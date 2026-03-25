package com.senai.ads.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.senai.ads.Dao.AlunoDao;
import com.senai.ads.Model.Aluno;

@Controller
public class AlunoController {

    @Autowired
    AlunoDao dao;

    @GetMapping("/aluno/cadastro")
    public String cadastroAluno() {
        return "cadastroAluno";
    }

    @PostMapping("/aluno/novo")
    public String novoAluno(Aluno aluno) {
        dao.save(aluno);
        return "";
    }

}
