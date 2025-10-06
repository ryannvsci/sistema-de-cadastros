package main.java.com.sospet.projeto.model;


public class Pet {
    private String nome = " ";
    private Tipo tipo;
    private Sexo sexo;
    private String endereco;
    private double idade;
    private double peso;
    private String raca;
    private final String NAO_INFORMADO = "NÃO INFORMADO";
    String arquivoOrigem;

    public Pet(String nome, Tipo tipo, Sexo sexo, String endereco, double idade, double peso, String raca) {
        this.nome = nome;
        this.tipo = tipo;
        this.sexo = sexo;
        this.endereco = endereco;
        this.idade = idade;
        this.peso = peso;
        this.raca = raca;
    }

    public Pet(String nome, Tipo tipo, Sexo sexo, double idade, double peso, String raca) {
        this.nome = nome;
        this.tipo = tipo;
        this.sexo = sexo;
        this.endereco = NAO_INFORMADO;
        this.idade = idade;
        this.peso = peso;
        this.raca = raca;
    }

    public Pet(String nome, Tipo tipo, Sexo sexo, String endereco, double idade, double peso) {
        this.nome = nome;
        this.tipo = tipo;
        this.sexo = sexo;
        this.endereco = endereco;
        this.idade = idade;
        this.peso = peso;
    }

    public Pet(String nome, Tipo tipo, Sexo sexo, String endereco) {
        this.nome = nome;
        this.tipo = tipo;
        this.sexo = sexo;
        this.endereco = endereco;
    }

    public Pet(String nome, Tipo tipo, Sexo sexo) {
        this.nome = nome;
        this.tipo = tipo;
        this.sexo = sexo;
    }

    public void imprimir() {
        System.out.println(this.nome + " - " + this.tipo.getNOME() + " - " + this.sexo.getGENERO() + " - " + this.endereco + " - " + this.idade + " anos - " + this.peso + " kg - " + this.raca);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public double getIdade() {
        return idade;
    }

    public void setIdade(double idade) {
        this.idade = idade;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getNAO_INFORMADO() {
        return NAO_INFORMADO;
    }

    public String getArquivoOrigem() {
        return arquivoOrigem;
    }

    public void setArquivoOrigem(String arquivoOrigem) {
        this.arquivoOrigem = arquivoOrigem;
    }
}
