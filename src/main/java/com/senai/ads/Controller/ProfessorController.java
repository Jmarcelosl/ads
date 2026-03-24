package com.senai.ads.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfessorController {

    @GetMapping("/professor/cadastro")
    public String cadastroProfessor() {
        return "cadastroProfessor";
    }
}
