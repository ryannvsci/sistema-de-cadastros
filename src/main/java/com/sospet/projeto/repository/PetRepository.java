package main.java.com.sospet.projeto.repository;

import main.java.com.sospet.projeto.model.Pet;
import main.java.com.sospet.projeto.model.Sexo;
import main.java.com.sospet.projeto.model.Tipo;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PetRepository {
    List<Pet> pets = new ArrayList<>();
    List<Pet> petsAchados = new ArrayList<>();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmm");
    LocalDateTime dt = LocalDateTime.now();
    static String nomeArquivo;

    public static Pet lerArquivoPet(File arquivo) {
        try (Scanner sc = new Scanner(arquivo)) {
            String nome = sc.nextLine();
            String tipoString = sc.nextLine();
            String sexoString = sc.nextLine();
            String endereco = sc.nextLine();
            String idadeString = sc.nextLine();
            String pesoString = sc.nextLine();
            String raca = sc.nextLine();
            Tipo tipo = tipoString.equalsIgnoreCase("gato") ? Tipo.GATO : Tipo.CACHORRO;
            Sexo sexo = sexoString.equalsIgnoreCase("femea") ? Sexo.FEMININO : Sexo.MASCULINO;
            double idade = Double.parseDouble(idadeString.replace(" anos", "").trim());
            double peso = Double.parseDouble(pesoString.replace(" kg", "").trim());
            Pet pet = new Pet(nome, tipo, sexo, endereco, idade, peso, raca);
            pet.setArquivoOrigem(arquivo.getName());
            return pet;
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Erro na leitura");
        }
    }

    public void criarListaPets() {
        pets.clear();
        File diretorio = new File("petsCadastrados");
        File[] arquivos = diretorio.listFiles();
        if (arquivos != null) {
            for (File arquivo : arquivos) {
                Pet pet = lerArquivoPet(arquivo);
                pets.add(pet);
            }
        }
    }

    public void deletarPet(int numPet) {
        Pet petDeletar = petsAchados.get(numPet);
        String nomeDeletar = petDeletar.getArquivoOrigem();
        File arquivoDeletar = new File("petsCadastrados", nomeDeletar);
        arquivoDeletar.delete();
    }

    public void editarPetNome(int numPet, String nome) {
        Pet petEditar = petsAchados.get(numPet);
        petEditar.setNome(nome);
        editarNomeArquivo(petEditar, petEditar.getArquivoOrigem());
        criarListaPets();
    }

    public void editarPetEndereco(int numPet, String endereco) {
        Pet petEditar = petsAchados.get(numPet);
        petEditar.setEndereco(endereco);
        criarArquivo(petEditar, petEditar.getArquivoOrigem());
        criarListaPets();
    }

    public void editarPetIdade(int numPet, double idade) {
        Pet petEditar = petsAchados.get(numPet);
        petEditar.setIdade(idade);
        criarArquivo(petEditar, petEditar.getArquivoOrigem());
        criarListaPets();
    }

    public void editarPetPeso(int numPet, double peso) {
        Pet petEditar = petsAchados.get(numPet);
        petEditar.setPeso(peso);
        criarArquivo(petEditar, petEditar.getArquivoOrigem());
        criarListaPets();
    }

    public void editarPetRaca(int numPet, String raca) {
        Pet petEditar = petsAchados.get(numPet);
        petEditar.setRaca(raca);
        criarArquivo(petEditar, petEditar.getArquivoOrigem());
        criarListaPets();
    }
    
    public void buscarPorNome(String nome, Tipo tipo) {
        petsAchados.clear();
        System.out.println("Resultados: ");
        int i = 1;
        for (Pet pet : pets) {
            if (pet.getNome().toLowerCase().contains(nome.toLowerCase()) && pet.getTipo() == tipo) {
                petsAchados.add(pet);
                System.out.print(i + ". ");pet.imprimir();
                i++;
            }
        }
    }

    public void buscarPorSexo(Sexo sexo, Tipo tipo) {
        petsAchados.clear();
        System.out.println("Resultados: ");
        int i = 1;
        for (Pet pet : pets) {
            if (pet.getSexo() == sexo && pet.getTipo() == tipo) {
                petsAchados.add(pet);
                System.out.print(i + ". ");pet.imprimir();
                i++;
            }
        }
    }

    public void buscarPorEndereco(String endereco, Tipo tipo) {
        petsAchados.clear();
        System.out.println("Resultados: ");
        int i = 1;
        for (Pet pet : pets) {

            if (pet.getEndereco().equalsIgnoreCase(endereco) && pet.getTipo() == tipo) {
                petsAchados.add(pet);
                System.out.print(i + ". ");pet.imprimir();
                i++;
            }
        }
        if (petsAchados.isEmpty()) {
            System.out.println("Sem pets com estes critérios.");
        }
    }

    public void buscarPorIdade(Double idade, Tipo tipo) {
        petsAchados.clear();
        System.out.println("Resultados: ");
        int i = 1;
        for (Pet pet : pets) {

            if (pet.getIdade() == idade && pet.getTipo() == tipo) {
                petsAchados.add(pet);
                System.out.print(i + ". ");pet.imprimir();
                i++;
            }
        }
    }

    public void buscarPorPeso(Double peso, Tipo tipo) {
        petsAchados.clear();
        System.out.println("Resultados: ");
        int i = 1;
        for (Pet pet : pets) {
            if (pet.getPeso() == peso && pet.getTipo() == tipo) {
                petsAchados.add(pet);
                System.out.print(i + ". ");pet.imprimir();
                i++;
            }
        }
    }

    public void buscarPorRaca(String raca, Tipo tipo) {
        petsAchados.clear();
        System.out.println("Resultados: ");
        int i = 1;
        for (Pet pet : pets) {
            if (pet.getRaca().equalsIgnoreCase(raca) && pet.getTipo() == tipo) {
                petsAchados.add(pet);
                System.out.print(i + ". ");pet.imprimir();
                i++;
            }
        }
    }


    public void listarTodosPets() {
        criarListaPets();
        if (pets.isEmpty()) {
            System.out.println("Sem pets cadastrados");
        } else {
            for (Pet pet : pets) {
                pet.imprimir();
            }
        }
    }

    private void gerarNomeArquivo(String nome) {
        String regex = "\\S";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(nome);
        StringBuilder sb = new StringBuilder();
        while(matcher.find()) {
            sb.append(matcher.group());
        }
        String nomeSemEspaco = sb.toString();
        nomeArquivo = formatter.format(dt) + "-"+nomeSemEspaco.toUpperCase()+".txt";
    }

    public void criarArquivo(Pet pet) {
        {
            this.gerarNomeArquivo(pet.getNome());
            pet.setArquivoOrigem(nomeArquivo);
        }
        File file = new File("petsCadastrados", nomeArquivo);

        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try(FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(pet.getNome());
            bw.newLine();
            bw.write(pet.getTipo().getNOME());
            bw.newLine();
            bw.write(pet.getSexo().getGENERO());
            bw.newLine();
            bw.write(pet.getEndereco());
            bw.newLine();
            bw.write((int) pet.getIdade() + " anos");
            bw.newLine();
            bw.write(pet.getPeso() + " kg");
            bw.newLine();
            bw.write(pet.getRaca());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void criarArquivo(Pet pet, String nomeDoArquivo) {
        File dir = new File("petsCadastrados");
        if (!dir.exists()) {
            dir.mkdir();
        }
        File file = new File(dir, nomeDoArquivo);

        try(FileWriter fw = new FileWriter(file, false);
            BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(pet.getNome());
            bw.newLine();
            bw.write(pet.getTipo().getNOME());
            bw.newLine();
            bw.write(pet.getSexo().getGENERO());
            bw.newLine();
            bw.write(pet.getEndereco());
            bw.newLine();
            bw.write((int) pet.getIdade() + " anos");
            bw.newLine();
            bw.write(pet.getPeso() + " kg");
            bw.newLine();
            bw.write(pet.getRaca());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void editarNomeArquivo(Pet pet, String nomeDoArquivo) {
        File dir = new File("petsCadastrados");
        if (!dir.exists()) {
            dir.mkdir();
        }
        File file = new File(dir, nomeDoArquivo);
        gerarNomeArquivo(pet.getNome());
        File renomear = new File(dir, nomeArquivo);

        file.renameTo(renomear);
        pet.setArquivoOrigem(nomeArquivo);


        try(FileWriter fw = new FileWriter(renomear, false);
            BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(pet.getNome());
            bw.newLine();
            bw.write(pet.getTipo().getNOME());
            bw.newLine();
            bw.write(pet.getSexo().getGENERO());
            bw.newLine();
            bw.write(pet.getEndereco());
            bw.newLine();
            bw.write((int) pet.getIdade() + " anos");
            bw.newLine();
            bw.write(pet.getPeso() + " kg");
            bw.newLine();
            bw.write(pet.getRaca());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}