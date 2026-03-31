package com.senai.cadastrousuario.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.senai.cadastrousuario.Dao.ProdutoDao;
import com.senai.cadastrousuario.Model.Produto;

@Controller
public class ProdutoController {

    @Autowired
    ProdutoDao dao;

    // 1. Mostrar a lista de produtos
    @GetMapping("/produto/lista")
    public ModelAndView listaProdutos() {
        ModelAndView mv = new ModelAndView("listaProduto");
        List<Produto> produtos = dao.findAll();
        mv.addObject("produtos", produtos);
        return mv;
    }

    // 2. Mostrar a tela de cadastro
    @GetMapping("/produto/cadastro")
    public String telaCadastro() {
        return "cadastroProduto";
    }

    // 3. Salvar o novo produto no banco
    @PostMapping("/produto/cadastro")
    public String salvarProduto(Produto produto, RedirectAttributes attributes) {
        dao.save(produto);
        attributes.addFlashAttribute("mensagemSucesso", "Produto cadastrado com sucesso! 📦✨");
        return "redirect:/produto/lista";
    }

    // 4. Excluir produto
    @GetMapping("/produto/excluir/{id}")
    public String excluirProduto(@PathVariable Integer id, RedirectAttributes attributes) {
        dao.deleteById(id);
        attributes.addFlashAttribute("mensagemSucesso", "Produto excluído! 🗑️");
        return "redirect:/produto/lista";
    }

    // 5. Atualizar Estoque (Aumentar)
    @GetMapping("/produto/estoque/aumentar/{id}")
    public String aumentarEstoque(@PathVariable Integer id) {
        Produto produto = dao.findById(id).orElse(null);
        if (produto != null) {
            produto.setEstoque(produto.getEstoque() + 1);
            dao.save(produto);
        }
        return "redirect:/produto/lista";
    }

    // 6. Atualizar Estoque (Diminuir)
    @GetMapping("/produto/estoque/diminuir/{id}")
    public String diminuirEstoque(@PathVariable Integer id) {
        Produto produto = dao.findById(id).orElse(null);
        if (produto != null && produto.getEstoque() > 0) { // Não deixa o estoque ficar negativo
            produto.setEstoque(produto.getEstoque() - 1);
            dao.save(produto);
        }
        return "redirect:/produto/lista";
    }
}