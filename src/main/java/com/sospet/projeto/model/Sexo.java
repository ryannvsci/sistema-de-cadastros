package main.java.com.sospet.projeto.model;

public enum Sexo {
    MASCULINO("Macho"),
    FEMININO("Femea");

    String GENERO;

    Sexo(String genero) {
        this.GENERO = genero;
    }

    public String getGENERO() {
        return GENERO;
    }
}
