package br.com.academy.Enum;

public enum Curso {

    ADMINISTRACAO("Administracao"),
    INFORMATICA("Informatica"),
    CONTABILIDADE("Contabilidade"),
    PROGRAMACAO("Programacao"),
    ENFERMAGEM("Enfermagem");

    private String curso;

    private Curso(String curso) {
        this.curso = curso;
    }
}
