package com.example.newform.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.newform.R;
import com.example.newform.dao.AlunoDAO;
import com.example.newform.models.AlunoModel;
import com.example.newform.reyclerview.AdapterDefault;
import com.example.newform.reyclerview.adapter.AlunosAdapter;
import com.example.newform.utils.UtilActivityResult;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;
import java.util.List;

public class AlunosActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alunos);
        setTitle(getString(R.string.alunos));
        loadAdapter();
        ((FloatingActionButton) findViewById(R.id.btnNovoAluno)).setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case UtilActivityResult.NOVO:
            case UtilActivityResult.EDITAR:

                if (resultCode == RESULT_OK){
                    loadAdapter();
                }

                break;

        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnNovoAluno:
                startActivityForResult(new Intent(AlunosActivity.this, AlunoActivity.class), UtilActivityResult.NOVO);
                break;
        }
    }

    private void loadAdapter(){
        AlunoDAO alunoDAO = new AlunoDAO(getApplicationContext());
        List<AlunoModel> alunos = alunoDAO.select();

        RecyclerView recyclerView = findViewById(R.id.recyclerViewAlunos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        AlunosAdapter adapter = new AlunosAdapter(AlunosActivity.this, alunos);
        adapter.setOnClick(new AdapterDefault.OnClick<AlunoModel>() {
            @Override
            public void onClick(View v, AlunoModel object, int position) {
                Intent intent = new Intent(AlunosActivity.this, AlunoActivity.class);
                intent.putExtra("aluno", (Serializable) object);
                startActivityForResult(intent, UtilActivityResult.EDITAR);
            }
        });

        recyclerView.setAdapter(adapter);

        ((TextView) findViewById(R.id.txtSemAlunos)).setVisibility(alunos.isEmpty() ? View.VISIBLE : View.GONE);
    }
}
