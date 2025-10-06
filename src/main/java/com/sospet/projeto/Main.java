package main.java.com.sospet.projeto;

import main.java.com.sospet.projeto.repository.PetRepository;
import main.java.com.sospet.projeto.model.Pet;
import main.java.com.sospet.projeto.model.Sexo;
import main.java.com.sospet.projeto.model.Tipo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int option = 0;
        String nome = "";
        String sobrenome = "";
        String nomeCompleto;
        String tipoTemporario;
        Tipo tipo;
        String sexoTemporario = "";
        Sexo sexo;
        String rua = "";
        String numero = "";
        String cidade = "";
        String endereco = "";
        double idade;
        double peso;
        String raca;
        PetRepository pr = new PetRepository();
        String formulario = "formulario.txt";
        System.out.println("Bem vindo ao sistema SOS-PET");
        while (true) {
            System.out.println();
            System.out.println("Selecione uma opção a seguir: ");
            System.out.println("1 - Cadastrar um novo pet");
            System.out.println("2 - Alterar os dados do pet cadastrado");
            System.out.println("3 - Deletar um pet cadastrado");
            System.out.println("4 - Listar todos os pets cadastrados");
            System.out.println("5 - Listar pets por algum critério (idade, nome, raça)");
            System.out.println("6 - Sair");
            int validar = sc.nextInt();
            if (validar <= 0 || validar > 6) {
                System.out.println("Caractere inválido.");
            } else {
                option = validar;
            }
            switch (option) {
                case 1:
                    sc.nextLine();
                    try (BufferedReader br = new BufferedReader(new FileReader(formulario))) {
                        String linha;
                        while ((linha = br.readLine()) != null) {
                            System.out.println(linha);
                        }
                        String regex = "^[a-zA-Z]+$";
                        System.out.println();
                        System.out.print("1 - Nome: ");
                        try {
                            nome = sc.nextLine();
                            if (nome == null || !nome.matches(regex)) {
                                throw new RuntimeException();
                            }
                        } catch (Exception e) {
                            throw new RuntimeException("Erro, para seguir é necessário nome e sobrenome válido");
                        }
                        System.out.print("1 - Sobrenome: ");
                        try {
                            sobrenome = sc.nextLine();
                            if (sobrenome == null || !sobrenome.matches(regex)) {
                                throw new RuntimeException();
                            }
                        } catch (Exception e) {
                            throw new RuntimeException("Erro, para seguir é necessário nome e sobrenome válido");
                        }
                        nomeCompleto = nome + " " + sobrenome;

                        while (true) {
                            System.out.print("2 (G = GATO | C = CACHORRO) - ");
                            tipoTemporario = sc.nextLine();
                            if (tipoTemporario.equalsIgnoreCase("g")) {
                                tipo = Tipo.GATO;
                                break;
                            } else if (tipoTemporario.equalsIgnoreCase("c")) {
                                tipo = Tipo.CACHORRO;
                                break;
                            } else {
                                System.out.println("Tipo inválido");
                            }
                        }
                        while (true) {
                            System.out.print("3 (M - MASCULINO | F - FEMININO) - ");
                            sexoTemporario = sc.nextLine();
                            if (sexoTemporario.equalsIgnoreCase("m")) {
                                sexo = Sexo.MASCULINO;
                                break;
                            } else if (sexoTemporario.equalsIgnoreCase("f")) {
                                sexo = Sexo.FEMININO;
                                break;
                            } else {
                                System.out.println("Sexo inválido");
                            }
                        }
                        System.out.print("4.1 - Rua: ");
                        rua = sc.nextLine();
                        System.out.print("4.2 - Número da Casa: ");
                        numero = sc.nextLine();
                        System.out.print("4.3 - Cidade: ");
                        cidade = sc.nextLine();
                        endereco = rua + ", " + numero + " - " + cidade;
                        System.out.print("5 - ");
                        idade = sc.nextInt();
                        if (idade > 20) {
                            throw new RuntimeException("Idade inválida");
                        }
                        System.out.print("6 - ");
                        peso = sc.nextDouble();
                        if (peso > 60 || peso < 0.5) {
                            throw new RuntimeException("Erro, peso inválido.");
                        }
                        sc.nextLine();
                        System.out.print("7 - ");
                        raca = sc.nextLine();
                        Pet pet = new Pet(nomeCompleto, tipo, sexo, endereco, idade, peso, raca);
                        pr.criarArquivo(pet);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case 2:
                    sc.nextLine();
                    pr.criarListaPets();
                    System.out.println("Qual o tipo do pet? (G - GATO | C - CACHORRO)");
                    String tipoBuscaTemp = sc.nextLine();
                    Tipo tipoBusca = null;
                    if (tipoBuscaTemp.equalsIgnoreCase("G")) {
                        tipoBusca = Tipo.GATO;
                    } else if (tipoBuscaTemp.equalsIgnoreCase("C")) {
                        tipoBusca = Tipo.CACHORRO;
                    }
                    System.out.println("Qual critério deseja buscar?");
                    System.out.println("1 - Nome");
                    System.out.println("2 - Sexo");
                    System.out.println("3 - Endereço");
                    System.out.println("4 - Idade");
                    System.out.println("5 - Peso");
                    System.out.println("6 - Raça");
                    int resposta = sc.nextInt();
                    sc.nextLine();
                    switch (resposta) {
                        case 1:
                            System.out.print("Digite o nome: ");
                            String nomeBusca = sc.nextLine();
                            pr.buscarPorNome(nomeBusca, tipoBusca);
                            break;
                        case 2:
                            System.out.print("Digite o sexo (M - MASCULINO | F - FEMININO): ");
                            String sexoBuscaTemporario = sc.nextLine();
                            Sexo sexoBusca = null;
                            if (sexoBuscaTemporario.equalsIgnoreCase("m")) {
                                sexoBusca = Sexo.MASCULINO;
                            } else if (sexoBuscaTemporario.equalsIgnoreCase("f")) {
                                sexoBusca = Sexo.FEMININO;
                            }
                            pr.buscarPorSexo(sexoBusca, tipoBusca);
                            break;
                        case 3:
                            System.out.print("Digite o endereço completo: ");
                            String enderecoBusca = sc.nextLine();
                            pr.buscarPorEndereco(enderecoBusca, tipoBusca);
                            break;
                        case 4:
                            System.out.print("Digite a idade: ");
                            double idadeBusca = sc.nextDouble();
                            pr.buscarPorIdade(idadeBusca, tipoBusca);
                            break;
                        case 5:
                            System.out.print("Digite o peso: ");
                            double pesoBusca = sc.nextDouble();
                            pr.buscarPorPeso(pesoBusca, tipoBusca);
                            break;
                        case 6:
                            System.out.print("Digite a raça: ");
                            String racaBusca = sc.nextLine();
                            pr.buscarPorRaca(racaBusca, tipoBusca);
                            break;
                    }
                    System.out.print("Qual pet deseja editar: ");
                    int petEditar = sc.nextInt()-1;
                    System.out.println("Qual critério deseja editar");
                    System.out.println("1 - Nome");
                    System.out.println("2 - Endereço");
                    System.out.println("3 - Idade");
                    System.out.println("4 - Peso");
                    System.out.println("5 - Raça");
                    int respostaEditar = sc.nextInt();
                    sc.nextLine();
                    switch (respostaEditar) {
                        case 1:
                            System.out.print("Digite o nome: ");
                            String nomeAltera = sc.nextLine();
                            pr.editarPetNome(petEditar, nomeAltera);
                            break;
                        case 2:
                            System.out.print("Digite o endereço completo: ");
                            String enderecoAltera = sc.nextLine();
                            pr.editarPetEndereco(petEditar, enderecoAltera);
                            break;
                        case 3:
                            System.out.print("Digite a idade: ");
                            double idadeAltera = sc.nextDouble();
                            pr.editarPetIdade(petEditar, idadeAltera);
                            break;
                        case 4:
                            System.out.print("Digite o peso: ");
                            double pesoAltera = sc.nextDouble();
                            pr.editarPetPeso(petEditar, pesoAltera);
                            break;
                        case 5:
                            System.out.print("Digite a raça: ");
                            String racaAltera = sc.nextLine();
                            pr.editarPetRaca(petEditar, racaAltera);
                            break;
                    }
                    break;
                case 3:
                    sc.nextLine();
                    pr.criarListaPets();
                    System.out.println("Qual o tipo do pet? (G - GATO | C - CACHORRO)");
                    String tipoDeletarTemp = sc.nextLine();
                    Tipo tipoDeletar = null;
                    if (tipoDeletarTemp.equalsIgnoreCase("G")) {
                        tipoDeletar = Tipo.GATO;
                    } else if (tipoDeletarTemp.equalsIgnoreCase("C")) {
                        tipoDeletar = Tipo.CACHORRO;
                    }
                    System.out.println("Qual critério deseja buscar?");
                    System.out.println("1 - Nome");
                    System.out.println("2 - Sexo");
                    System.out.println("3 - Endereço");
                    System.out.println("4 - Idade");
                    System.out.println("5 - Peso");
                    System.out.println("6 - Raça");
                    int respostaDeletar = sc.nextInt();
                    sc.nextLine();
                    switch (respostaDeletar) {
                        case 1:
                            System.out.print("Digite o nome: ");
                            String nomeBusca = sc.nextLine();
                            pr.buscarPorNome(nomeBusca, tipoDeletar);
                            break;
                        case 2:
                            System.out.print("Digite o sexo (M - MASCULINO | F - FEMININO): ");
                            String sexoBuscaTemporario = sc.nextLine();
                            Sexo sexoBusca = null;
                            if (sexoBuscaTemporario.equalsIgnoreCase("m")) {
                                sexoBusca = Sexo.MASCULINO;
                            } else if (sexoBuscaTemporario.equalsIgnoreCase("f")) {
                                sexoBusca = Sexo.FEMININO;
                            }
                            pr.buscarPorSexo(sexoBusca, tipoDeletar);
                            break;
                        case 3:
                            System.out.print("Digite o endereço completo: ");
                            String enderecoBusca = sc.nextLine();
                            pr.buscarPorEndereco(enderecoBusca, tipoDeletar);
                            break;
                        case 4:
                            System.out.print("Digite a idade: ");
                            double idadeBusca = sc.nextDouble();
                            pr.buscarPorIdade(idadeBusca, tipoDeletar);
                            break;
                        case 5:
                            System.out.print("Digite o peso: ");
                            double pesoBusca = sc.nextDouble();
                            pr.buscarPorPeso(pesoBusca, tipoDeletar);
                            break;
                        case 6:
                            System.out.print("Digite a raça: ");
                            String racaBusca = sc.nextLine();
                            pr.buscarPorRaca(racaBusca, tipoDeletar);
                            break;
                    }
                    System.out.print("Qual pet deseja deletar: ");
                    int petDeletar = sc.nextInt()-1;
                    pr.deletarPet(petDeletar);
                    break;
                case 4:
                    pr.listarTodosPets();
                    break;
                case 5:
                    sc.nextLine();
                    pr.criarListaPets();
                    System.out.println("Qual o tipo do pet? (G - GATO | C - CACHORRO)");
                    String tipoBuscaTempCase5 = sc.nextLine();
                    Tipo tipoBuscaCase5 = null;
                    if (tipoBuscaTempCase5.equalsIgnoreCase("G")) {
                        tipoBuscaCase5 = Tipo.GATO;
                    } else if (tipoBuscaTempCase5.equalsIgnoreCase("C")) {
                        tipoBuscaCase5 = Tipo.CACHORRO;
                    }
                    System.out.println("Qual critério deseja buscar?");
                    System.out.println("1 - Nome");
                    System.out.println("2 - Sexo");
                    System.out.println("3 - Endereço");
                    System.out.println("4 - Idade");
                    System.out.println("5 - Peso");
                    System.out.println("6 - Raça");
                    int respostaBusca = sc.nextInt();
                    sc.nextLine();
                    switch (respostaBusca) {
                        case 1:
                            System.out.print("Digite o nome: ");
                            String nomeBusca = sc.nextLine();
                            pr.buscarPorNome(nomeBusca, tipoBuscaCase5);
                            break;
                        case 2:
                            System.out.print("Digite o sexo (M - MASCULINO | F - FEMININO): ");
                            String sexoBuscaTemporario = sc.nextLine();
                            Sexo sexoBusca = null;
                            if (sexoBuscaTemporario.equalsIgnoreCase("m")) {
                                sexoBusca = Sexo.MASCULINO;
                            } else if (sexoBuscaTemporario.equalsIgnoreCase("f")) {
                                sexoBusca = Sexo.FEMININO;
                            }
                            pr.buscarPorSexo(sexoBusca, tipoBuscaCase5);
                            break;
                        case 3:
                            System.out.print("Digite o endereço completo: ");
                            String enderecoBusca = sc.nextLine();
                            pr.buscarPorEndereco(enderecoBusca, tipoBuscaCase5);
                            break;
                        case 4:
                            System.out.print("Digite a idade: ");
                            double idadeBusca = sc.nextDouble();
                            pr.buscarPorIdade(idadeBusca, tipoBuscaCase5);
                            break;
                        case 5:
                            System.out.print("Digite o peso: ");
                            double pesoBusca = sc.nextDouble();
                            pr.buscarPorPeso(pesoBusca, tipoBuscaCase5);
                            break;
                        case 6:
                            System.out.print("Digite a raça: ");
                            String racaBusca = sc.nextLine();
                            pr.buscarPorRaca(racaBusca, tipoBuscaCase5);
                            break;
                    }
                    break;
                case 6:
                    System.out.println("Saindo...");
                    return;
            }
        }
    }
}