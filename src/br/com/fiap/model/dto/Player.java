package br.com.fiap.model.dto;

public class Player {
    private int id_player;
    private String nome;
    private String nivel;
    private String pontuacao;

    public Player() {
    }

    public int getId_player() { return id_player; }

    public void setId_player(int id_player) { this.id_player = id_player; }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(String pontuacao) {
        this.pontuacao = pontuacao;
    }
}
