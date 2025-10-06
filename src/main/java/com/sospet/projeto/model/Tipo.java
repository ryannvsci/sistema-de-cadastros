package main.java.com.sospet.projeto.model;

public enum Tipo {
    GATO("Gato"),
    CACHORRO("Cachorro");

    String NOME;
    Tipo(String nome) {
        this.NOME = nome;
    }

    public String getNOME() {
        return NOME;
    }
}
