package com.senai.cadastrousuario.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.senai.cadastrousuario.Dao.AlunoDao;
import com.senai.cadastrousuario.Model.Aluno;

@Controller
public class AlunoController {

    @Autowired
    AlunoDao dao;

    @GetMapping("/aluno/cadastro")
    public String cadastroAluno() {
        return "cadastroAluno";
    }

    @PostMapping("/aluno/cadastro")
    public String novoAluno(Aluno aluno, RedirectAttributes attributes) {
        dao.save(aluno);

        // Mensagem fofa de sucesso
        attributes.addFlashAttribute("mensagemSucesso", "Aluno cadastrado com sucesso! 🌸✨");

        // Redireciona de volta para a própria tela de cadastro
        return "redirect:/aluno/cadastro";
    }

    @GetMapping("/aluno/lista")
    public ModelAndView listaAluno() {
        ModelAndView mv = new ModelAndView("listaAluno");
        List<Aluno> alunos = dao.findAll();
        mv.addObject("alunos", alunos);
        return mv;
    }

    @GetMapping("/aluno/login")
    public String telaLogin() {
        return "login";
    }

    // 2. Recebe os dados do formulário para validar
    @PostMapping("/aluno/login")
    public String fazerLogin(@RequestParam String email, @RequestParam String senha, Model model) {
        // Busca no banco de dados se existe um aluno com esse email e senha
        Aluno alunoLogado = dao.findByEmailAndSenha(email, senha);

        if (alunoLogado != null) {
            // Se encontrou (login correto), redireciona para a rota da lista!
            return "redirect:/aluno/lista";
        } else {
            // Se não encontrou (login errado), volta para a tela de login e manda uma
            // mensagem de erro
            model.addAttribute("erro", "Email ou senha incorretos!");
            return "login";
        }
    }

    // Botão de excluir Aluno
    @GetMapping("/aluno/excluir/{id}")
    public String excluirAluno(@PathVariable int id, RedirectAttributes attributes) {
        // Manda o DAO apagar o aluno que tem esse ID
        dao.deleteById(id);

        // Cria uma mensagem fofa de sucesso
        attributes.addFlashAttribute("mensagemSucesso", "Aluno excluído com sucesso! 🎀");

        // Recarrega a página de lista de alunos
        return "redirect:/aluno/lista";
    }

    @GetMapping("/aluno/editar/{id}")
    public ModelAndView editar(@PathVariable("id") int id) {
        ModelAndView mv = new ModelAndView("cadastroAluno");
        Aluno aluno = dao.findById(id).get();
        mv.addObject("aluno", aluno);
        return mv;
    }

}