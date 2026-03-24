package com.senai.ads.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AlunoController {

    @GetMapping("/aluno/cadastro")
    public String cadastroAluno() {
        return "cadastroAluno";
    }

}
