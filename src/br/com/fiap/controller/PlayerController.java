package br.com.fiap.controller;

import br.com.fiap.model.dao.ConnectionDB;
import br.com.fiap.model.dao.PlayerDAO;
import br.com.fiap.model.dto.Player;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PlayerController {


    public String inserirPlayer(int id_player, String nome, String nivel, String pontuacao) throws ClassNotFoundException, SQLException {
        String resultado;
        Connection con = ConnectionDB.abrirConexao();
        Player player = new Player();
        player.setId_player(id_player);
        player.setNome(nome);
        player.setNivel(nivel);
        player.setPontuacao(pontuacao);
        PlayerDAO playerDAO = new PlayerDAO(con);
        resultado = playerDAO.inserir(player);
        ConnectionDB.fecharConexao(con);
        return resultado;
    }
    public String alterarPlayer(int id_player, String nome, String nivel, String pontuacao) {
        String resultado;
        Connection con = ConnectionDB.abrirConexao();
        Player player = new Player();
        player.setId_player(id_player);
        player.setNome(nome);
        player.setNivel(nivel);
        player.setPontuacao(pontuacao);
        PlayerDAO playerDAO = new PlayerDAO(con);
        resultado = playerDAO.alternar(player);
        ConnectionDB.fecharConexao(con);
        return resultado;
    }

    public String excluirPlayer(int id_player) {
        String resultado;
        Connection con = ConnectionDB.abrirConexao();
        Player player = new Player();
        player.setId_player(id_player);
        PlayerDAO playerDAO = new PlayerDAO(con);
        resultado = playerDAO.excluir(player);
        ConnectionDB.fecharConexao(con);
        return resultado;
    }

    public String listarUm(Object object) throws ClassNotFoundException, SQLException{
        String resultado;
        Connection con = ConnectionDB.abrirConexao();
        Player player = new Player();
        player.setId_player((Integer) object);
        PlayerDAO playerDAO = new PlayerDAO(con);
        resultado = playerDAO.listarUm(player);
        ConnectionDB.fecharConexao(con);
        return resultado;
    }

    public List<Player> listarTodos() throws SQLException {
        Connection con = ConnectionDB.abrirConexao();
        PlayerDAO playerDAO = new PlayerDAO(con);
        List<Player> jogadores = playerDAO.listarTodos();
        ConnectionDB.fecharConexao(con);
        return jogadores;


    }

    public List<Player> rankearJogadores() throws ClassNotFoundException, SQLException{
        Connection con = ConnectionDB.abrirConexao();
        PlayerDAO playerDAO = new PlayerDAO(con);
        List<Player> jogadores = playerDAO.rankearJogadores();
        ConnectionDB.fecharConexao(con);
        return jogadores;
    }

    public String verInformacoesCompletas(int id_player) throws ClassNotFoundException, SQLException {
        Connection con = ConnectionDB.abrirConexao();
        Player player = new Player();
        player.setId_player(id_player);
        PlayerDAO playerDAO = new PlayerDAO(con);
        String resultado = playerDAO.verInformacoesCompletas(player);
        ConnectionDB.fecharConexao(con);
        return resultado;
    }

    public String verDesafioDiario(int id_player) throws ClassNotFoundException, SQLException {
        Connection con = ConnectionDB.abrirConexao();
        Player player = new Player();
        player.setId_player(id_player);
        PlayerDAO playerDAO = new PlayerDAO(con);
        String resultado = playerDAO.verDesafioDiario(player);
        ConnectionDB.fecharConexao(con);
        return resultado;
    }

    public String verInsignias(int id_player) throws ClassNotFoundException, SQLException {
        Connection con = ConnectionDB.abrirConexao();  // Abre a conexão com o banco de dados
        Player player = new Player();  // Cria um objeto Player
        player.setId_player(id_player);  // Define o ID do jogador

        PlayerDAO playerDAO = new PlayerDAO(con);  // Instancia PlayerDAO com a conexão
        String resultado = playerDAO.verInsignias(player);  // Chama o método verInsignias do PlayerDAO

        ConnectionDB.fecharConexao(con);  // Fecha a conexão com o banco de dados
        return resultado;  // Retorna o resultado com as insígnias do jogador
    }





}
