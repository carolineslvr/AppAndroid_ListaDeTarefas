package com.example.appliststarefas.helper;

import com.example.appliststarefas.activity.Tarefas;

import java.util.List;

public interface iTarefaDAO {

    public boolean salvar(Tarefas tarefa);
    public boolean atualizar(Tarefas tarefa);
    public boolean deletar(Tarefas tarefa);
    public List<Tarefas> listar();


}
