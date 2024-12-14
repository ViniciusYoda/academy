package br.com.academy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.academy.dao.AlunoDao;
import br.com.academy.model.Aluno;

@Controller
public class AlunoController {

    @Autowired
    private AlunoDao alunorepositorio;
    
    @GetMapping("/inserirAlunos")
    public ModelAndView InsertAlunos(Aluno aluno){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("Aluno/formAluno");
        mv.addObject("aluno", new Aluno());
        return mv;
    }

    @PostMapping("InsertAlunos")
    public ModelAndView inserirAlunos(Aluno aluno){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/Aluno/listAluno");
        alunorepositorio.save(aluno);
        return mv;
    }
}
