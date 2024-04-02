package com.example.appliststarefas.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.appliststarefas.R;
import com.example.appliststarefas.helper.TarefaDAO;
import com.google.android.material.textfield.TextInputEditText;

public class AdicionarActivity extends AppCompatActivity {

    private TextInputEditText edTarefa;
    private Tarefas atual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar);

        edTarefa = findViewById(R.id.editTextTarefa);
        atual = (Tarefas) getIntent().getSerializableExtra("selecionada");

        if (atual != null){
            edTarefa.setText(atual.getNomeTarefa());
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_tarefas, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

       if (item.getItemId() == R.id.itemSalva){
           TarefaDAO tarefaDAO = new TarefaDAO(getApplicationContext());

           if (atual != null){
               String nomeTarefa = edTarefa.getText().toString();
               if(!nomeTarefa.isEmpty()){
                   Tarefas tarefa = new Tarefas();
                   tarefa.setNomeTarefa(nomeTarefa);
                   tarefa.setId(atual.getId());

                   if (tarefaDAO.atualizar(tarefa)){
                       finish();
                       Toast.makeText(getApplicationContext(), "Sucesso ao salvar tarefa!", Toast.LENGTH_LONG).show();
                   } else {
                       Toast.makeText(getApplicationContext(), "Erro ao salvar tarefa!", Toast.LENGTH_LONG).show();
                   }
               }


           } else {

               String nomeTarefa = edTarefa.getText().toString();
               if(!nomeTarefa.isEmpty()){
                   Tarefas tarefa = new Tarefas();
                   tarefa.setNomeTarefa(nomeTarefa);
                   if (tarefaDAO.salvar(tarefa)){

                       finish();
                       Toast.makeText(getApplicationContext(), "Sucesso ao salvar tarefa!", Toast.LENGTH_LONG).show();
                   } else {
                       Toast.makeText(getApplicationContext(), "Erro ao salvar tarefa!", Toast.LENGTH_LONG).show();
                   }

               }
           }


       }

        return super.onOptionsItemSelected(item);
    }
}