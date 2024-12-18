package br.com.academy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.academy.dao.AlunoDao;
import br.com.academy.model.Aluno;

import jakarta.validation.Valid;

@Controller
public class AlunoController {

    @Autowired
    private AlunoDao alunorepositorio;

    @GetMapping("/inserirAlunos")
    public ModelAndView insertAlunos(Aluno aluno) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("Aluno/formAluno");
        mv.addObject("aluno", new Aluno());
        return mv;
    }

    @PostMapping("/inserirAlunos")
    public ModelAndView inserirAlunos(@Valid Aluno aluno, BindingResult br) {
        ModelAndView mv = new ModelAndView();
        if (br.hasErrors()) {
            mv.setViewName("Aluno/formAluno");
            mv.addObject("aluno", aluno);
        } else {
            alunorepositorio.save(aluno);
            mv.setViewName("redirect:/alunos-adicionados");
        }
        return mv;
    }

    @GetMapping("/alunos-adicionados")
    public ModelAndView listagemAlunos() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("Aluno/listAluno");
        mv.addObject("alunosList", alunorepositorio.findAll());
        return mv;
    }

    @GetMapping("/alterar/{id}")
    public ModelAndView alterar(@PathVariable("id") Integer id) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("Aluno/alterar");
        Aluno aluno = alunorepositorio.findById(id).orElse(null);
        if (aluno != null) {
            mv.addObject("aluno", aluno);
        } else {
            mv.setViewName("redirect:/alunos-adicionados");
        }
        return mv;
    }

    @PostMapping("/alterar")
    public ModelAndView alterar(@Valid Aluno aluno, BindingResult br) {
        ModelAndView mv = new ModelAndView();
        if (br.hasErrors()) {
            mv.setViewName("Aluno/alterar");
            mv.addObject("aluno", aluno);
        } else {
            alunorepositorio.save(aluno);
            mv.setViewName("redirect:/alunos-adicionados");
        }
        return mv;
    }

    @GetMapping("/excluir/{id}")
    public String excluirAluno(@PathVariable("id") Integer id) {
        alunorepositorio.deleteById(id);
        return "redirect:/alunos-adicionados";
    }

    @GetMapping("filtro-alunos")
    public ModelAndView filtroAlunos() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("Aluno/FiltroAluno");
        return mv;
    }

    @GetMapping("/alunos-ativos")
    public ModelAndView listaAlunosAtivos() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("Aluno/alunos-ativos");
        mv.addObject("alunosAtivos", alunorepositorio.findByStatusAtivos());
        return mv;
    }

    @GetMapping("/alunos-inativos")
    public ModelAndView listaAlunosInativos() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("Aluno/alunos-inativos");
        mv.addObject("alunosInativos", alunorepositorio.findByStatusInativos());
        return mv;
    }

    @GetMapping("/alunos-trancado")
    public ModelAndView listaAlunosTrancado() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("Aluno/alunos-trancado");
        mv.addObject("alunosTrancado", alunorepositorio.findByStatusTrancados());
        return mv;
    }

    @GetMapping("/alunos-cancelado")
    public ModelAndView listaAlunosCancelado() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("Aluno/alunos-cancelado");
        mv.addObject("alunosCancelado", alunorepositorio.findByStatusCancelado());
        return mv;
    }

    @PostMapping("pesquisar-aluno")
    public ModelAndView pesquisarAluno(@Requestparam(required = false) String nome){
        ModelAndView mv = new ModelAndView();
        List<Aluno> listaAlunos;
        if (nome == null || nome.trim().isEmpty()) {
            listaAlunos = alunorepositorio.findAll();
        } else {
            listaAlunos = alunorepositorio.findByNomeContainigIgnoreCase(nome);
        }
        mv.addObject("ListaDeAlunos", listaAlunos);
        mv.setViewName("Aluno/pesquisa-resultado");
        return mv;
    }


}
