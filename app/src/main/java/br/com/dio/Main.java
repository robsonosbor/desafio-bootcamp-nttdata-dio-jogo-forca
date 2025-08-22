package br.com.dio;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import br.com.dio.exceptions.LetraJaEscolhidaException;
import br.com.dio.models.*;

import static br.com.dio.utils.ConsoleUtils.limparTela;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Palavra secreta
        String palavra;
        if (args.length == 0) {
            System.out.print("Digite a palavra secreta: ");
            palavra = scanner.nextLine().trim().toLowerCase();
        } else {
            palavra = String.join("", args).toLowerCase();
        }

        List<JogoDaForcaCaractere> caracteres =
                palavra.chars()
                        .mapToObj(c -> new JogoDaForcaCaractere((char) c))
                        .collect(Collectors.toList());

        JogoDaForca jogoDaForca = new JogoDaForca(caracteres);

        boolean jogando = true;
        while (jogando) {
            mostrarEstado(jogoDaForca);
            exibirMenu();

            String opcao = scanner.nextLine().trim();
            switch (opcao) {
                case "1" -> addCaractere(jogoDaForca);
                case "2" -> {
                    System.out.println("Status: " + jogoDaForca.getStatusDoJogo());
                    aguardarEnter();
                }
                case "3" -> jogando = false;
                default -> {
                    System.out.println("Opção inválida");
                    aguardarEnter();
                }
            }

            if (jogoDaForca.getStatusDoJogo() != JogoDaForcaStatus.PENDENTE) {
                mostrarEstado(jogoDaForca);
                System.out.println(jogoDaForca.finalizarJogo());
                jogando = false;
            }
        }
    }

    private static void mostrarEstado(JogoDaForca jogo) {
        limparTela();
        System.out.println(jogo);
    }

    private static void exibirMenu() {
        System.out.println("Selecione uma das opções:");
        System.out.println("1 - Verificar uma letra");
        System.out.println("2 - Verificar o status do jogo");
        System.out.println("3 - Sair do jogo");
        System.out.print("Opção: ");
    }

    private static void addCaractere(JogoDaForca jogoDaForca) {
        System.out.print("Informe uma letra: ");
        String entrada = scanner.nextLine().toLowerCase();
        if (entrada.isEmpty()) {
            System.out.println("Nenhuma letra informada.");
            aguardarEnter();
            return;
        }
        char caractere = entrada.charAt(0);
        try {
            jogoDaForca.adicionarCaractere(caractere);
        } catch (LetraJaEscolhidaException ex) {
            System.out.println(ex.getMessage());
            aguardarEnter();
        }
    }

    private static void aguardarEnter() {
        System.out.println("\nPressione ENTER para continuar...");
        scanner.nextLine();
    }
}