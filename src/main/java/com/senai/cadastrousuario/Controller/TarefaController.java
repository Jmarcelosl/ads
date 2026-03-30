package com.senai.cadastrousuario.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.senai.cadastrousuario.Dao.TarefaDao;
import com.senai.cadastrousuario.Model.Tarefa;

@Controller
public class TarefaController {

    @Autowired
    TarefaDao dao;

    // 1. Mostrar a lista de tarefas
    @GetMapping("/tarefa/lista")
    public ModelAndView listaTarefas() {
        ModelAndView mv = new ModelAndView("listaTarefas"); // Nome exato do seu ficheiro HTML
        List<Tarefa> tarefas = dao.findAll();
        mv.addObject("tarefas", tarefas);
        return mv;
    }

    // 2. Criar uma nova tarefa
    @PostMapping("/tarefa/nova")
    public String novaTarefa(Tarefa tarefa, RedirectAttributes attributes) {
        // Quando criamos uma tarefa, ela começa sempre como pendente (false)
        tarefa.setConcluida(false);
        dao.save(tarefa);

        attributes.addFlashAttribute("mensagemSucesso", "Nova tarefa adicionada! 💖");
        return "redirect:/tarefa/lista";
    }

    // 3. Mudar o status (Pendente <-> Concluída)
    @GetMapping("/tarefa/status/{id}")
    public String mudarStatus(@PathVariable Long id) {
        // Procura a tarefa pelo ID
        Tarefa tarefa = dao.findById(id).orElse(null);

        if (tarefa != null) {
            // Inverte o status: se era true vira false, se era false vira true
            tarefa.setConcluida(!tarefa.isConcluida());
            dao.save(tarefa);
        }
        return "redirect:/tarefa/lista";
    }

    // 4. Excluir uma tarefa
    @GetMapping("/tarefa/excluir/{id}")
    public String excluirTarefa(@PathVariable Long id, RedirectAttributes attributes) {
        dao.deleteById(id);
        attributes.addFlashAttribute("mensagemSucesso", "Tarefa apagada com sucesso! 🗑️");
        return "redirect:/tarefa/lista";
    }

    // 5. Abrir o ecrã para editar uma tarefa
    @GetMapping("/tarefa/editar/{id}")
    public ModelAndView telaEditar(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView("editarTarefa");
        Tarefa tarefa = dao.findById(id).orElse(null);
        mv.addObject("tarefa", tarefa);
        return mv;
    }

    // 6. Guardar as alterações da edição
    @PostMapping("/tarefa/editar")
    public String salvarEdicao(Tarefa tarefa, RedirectAttributes attributes) {
        dao.save(tarefa); // O Spring sabe que se o ID já existir, ele deve atualizar e não criar um novo!
        attributes.addFlashAttribute("mensagemSucesso", "Tarefa atualizada! ✨");
        return "redirect:/tarefa/lista";
    }
}