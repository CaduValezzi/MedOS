package br.com.fiap.model.dao;

public interface InterfaceDAO {
    public String inserir(Object object);
    public String alterar(Object object);
    public String excluir(Object object);
    public String listarUm(Object object);
    public String listarTodos(Object object);
}
