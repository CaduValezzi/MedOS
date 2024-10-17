package br.com.fiap.view;

import br.com.fiap.controller.PlayerController;
import br.com.fiap.model.dto.Player;

import javax.swing.*;
import java.util.List;

public class PlayerView {
    public static void main(String[] args) {
        String nome, nivel, pontuacao;
        int id_player, opcao;
        String[] escolha = {"Inserir", "Alterar", "Excluir", "Listar", "Listar Todos", "Rankear Jogadores", "Informações Completas", "Desafio Diário", "Ver Insígnias"};
        PlayerController playerController = new PlayerController();

        do {
            try {
                opcao = JOptionPane.showOptionDialog(null, "Escolha uma das opções abaixo", "Escolha", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, escolha, escolha[0]);

                switch (opcao) {
                    case 0: // Inserir Player
                        id_player = Integer.parseInt(JOptionPane.showInputDialog("Digite o id"));
                        nome = JOptionPane.showInputDialog("Digite o nome");
                        nivel = JOptionPane.showInputDialog("Digite o nível");
                        pontuacao = JOptionPane.showInputDialog("Digite a pontuação");
                        System.out.println(playerController.inserirPlayer(id_player, nome, nivel, pontuacao));
                        break;
                    case 1: // Alterar Player
                        id_player = Integer.parseInt(JOptionPane.showInputDialog("Digite o id"));
                        nome = JOptionPane.showInputDialog("Digite o novo nome");
                        nivel = JOptionPane.showInputDialog("Digite o novo nível");
                        pontuacao = JOptionPane.showInputDialog("Digite a nova pontuação");
                        System.out.println(playerController.alterarPlayer(id_player, nome, nivel, pontuacao));
                        break;
                    case 2: // Excluir Player
                        id_player = Integer.parseInt(JOptionPane.showInputDialog("Digite o id"));
                        System.out.println(playerController.excluirPlayer(id_player));
                        break;
                    case 3: // Listar um Player
                        id_player = Integer.parseInt(JOptionPane.showInputDialog("Digite o id"));
                        JOptionPane.showMessageDialog(null, playerController.listarUm(id_player));
                        break;
                    case 4: // Listar Todos
                        List<Player> jogadores = playerController.listarTodos();
                        StringBuilder sb = new StringBuilder();
                        for (Player player : jogadores) {
                            sb.append("ID: ").append(player.getId_player()).append("\n")
                                    .append("Nome: ").append(player.getNome()).append("\n")
                                    .append("Nível: ").append(player.getNivel()).append("\n")
                                    .append("Pontuação: ").append(player.getPontuacao()).append("\n")
                                    .append("-----------------------------\n");
                        }
                        JOptionPane.showMessageDialog(null, sb.toString());
                        break;
                    case 5: // Rankear Jogadores
                        List<Player> rankeados = playerController.rankearJogadores();
                        StringBuilder rankSb = new StringBuilder();
                        for (Player player : rankeados) {
                            rankSb.append("ID: ").append(player.getId_player()).append("\n")
                                    .append("Nome: ").append(player.getNome()).append("\n")
                                    .append("Nível: ").append(player.getNivel()).append("\n")
                                    .append("Pontuação: ").append(player.getPontuacao()).append("\n")
                                    .append("-----------------------------\n");
                        }
                        JOptionPane.showMessageDialog(null, rankSb.toString());
                        break;
                    case 6: // Ver Informações Completas
                        id_player = Integer.parseInt(JOptionPane.showInputDialog("Digite o id"));
                        String informacoesCompletas = playerController.verInformacoesCompletas(id_player);
                        JOptionPane.showMessageDialog(null, informacoesCompletas);
                        break;
                    case 7: // Ver Desafio Diário
                        id_player = Integer.parseInt(JOptionPane.showInputDialog("Digite o id"));
                        String desafioDiario = playerController.verDesafioDiario(id_player);
                        JOptionPane.showMessageDialog(null, desafioDiario);
                        break;
                    case 8: // Ver Insígnias
                        id_player = Integer.parseInt(JOptionPane.showInputDialog("Digite o id"));
                        String insignias = playerController.verInsignias(id_player);
                        JOptionPane.showMessageDialog(null, insignias);
                        break;
                    default:
                        System.out.println("Opção inválida");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        } while (JOptionPane.showConfirmDialog(null, "Deseja continuar?", "Atenção", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0);

        JOptionPane.showMessageDialog(null, "Finalizado");
    }
}
