/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package gerenciamentolivros;

import java.util.List;
import java.util.Scanner;

/**
 *
 * @author arthu
 */
public class GerenciamentoLivros {

    public static void main(String[] args) {
         Scanner scanner = new Scanner(System.in);
        LivroDAO livroDAO = new LivroDAO();
        int opcao;

        do {
            System.out.println("Menu:");
            System.out.println("1. Adicionar livro");
            System.out.println("2. Listar livros");
            System.out.println("3. Editar livro");
            System.out.println("4. Excluir livro");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // consumir nova linha

            switch (opcao) {
                case 1:
                    // Lógica para adicionar livro
                    System.out.print("Título: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Autor: ");
                    String autor = scanner.nextLine();
                    System.out.print("Ano de publicação: ");
                    int ano = scanner.nextInt();
                    scanner.nextLine(); // consumir nova linha
                    System.out.print("Gênero: ");
                    String genero = scanner.nextLine();
                    Livro novoLivro = new Livro(titulo, autor, ano, genero);
                    livroDAO.adicionar(novoLivro);
                    System.out.println("Livro adicionado com sucesso!");
                    break;
                case 2:
                    // Listar livros
                    List<Livro> livros = livroDAO.listar();
                    if (livros.isEmpty()) {
                        System.out.println("Nenhum livro encontrado.");
                    } else {
                        System.out.println("Livros:");
                        for (Livro livro : livros) {
                            System.out.println(livro.getId() + ": " + livro.getTitulo());
                        }
                    }
                    break;
                case 3:
                    // Lógica para editar livro
                    System.out.print("Digite o ID do livro que deseja editar: ");
                    int idParaEditar = scanner.nextInt();
                    scanner.nextLine(); // consumir nova linha
                    Livro livroEditar = livroDAO.listar().stream()
                            .filter(livro -> livro.getId() == idParaEditar)
                            .findFirst().orElse(null);
                    
                    if (livroEditar != null) {
                        System.out.print("Novo título (deixe em branco para manter): ");
                        String novoTitulo = scanner.nextLine();
                        if (!novoTitulo.isEmpty()) {
                            livroEditar.setTitulo(novoTitulo);
                        }
                        System.out.print("Novo autor (deixe em branco para manter): ");
                        String novoAutor = scanner.nextLine();
                        if (!novoAutor.isEmpty()) {
                            livroEditar.setAutor(novoAutor);
                        }
                        System.out.print("Novo ano de publicação (0 para manter): ");
                        int novoAno = scanner.nextInt();
                        if (novoAno != 0) {
                            livroEditar.setAnoPublicacao(novoAno);
                        }
                        scanner.nextLine(); // consumir nova linha
                        System.out.print("Novo gênero (deixe em branco para manter): ");
                        String novoGenero = scanner.nextLine();
                        if (!novoGenero.isEmpty()) {
                            livroEditar.setGenero(novoGenero);
                        }
                        livroDAO.editar(livroEditar);
                        System.out.println("Livro editado com sucesso!");
                    } else {
                        System.out.println("Livro não encontrado.");
                    }
                    break;
                case 4:
                    // Lógica para excluir livro
                    System.out.print("Digite o ID do livro que deseja excluir: ");
                    int idParaExcluir = scanner.nextInt();
                    livroDAO.excluir(idParaExcluir);
                    System.out.println("Livro excluído com sucesso!");
                    break;
                case 5:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 5);
        scanner.close();
    }
}
