package com.senai.ads.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CursoController {

    @GetMapping("/curso/cadastro")
    public String cadastroCurso() {
        return "cadastroCurso";
    }

}
