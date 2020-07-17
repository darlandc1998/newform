package com.example.newform.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.newform.R;
import com.example.newform.dao.GraduacaoDAO;
import com.example.newform.dao.ModalidadeDAO;
import com.example.newform.dialogs.DialogDefault;
import com.example.newform.models.GraduacaoModel;
import com.example.newform.models.ModalidadeModel;
import com.example.newform.reyclerview.adapter.GraduacoesAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;

public class ModalidadeActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtModalidade;
    private EditText edtGraduacao;
    private ImageView imgNovaGraduacao;
    private RecyclerView recyclerView;

    private List<GraduacaoModel> graduacoes = new ArrayList<>();
    private List<GraduacaoModel> graduacoesExcluidas = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modalidade);
        setTitle(getString(R.string.modalidade));

        edtModalidade = findViewById(R.id.edtModalidade);
        edtGraduacao = findViewById(R.id.edtGraduacao);
        imgNovaGraduacao = findViewById(R.id.imgNovaGraduacao);
        recyclerView = findViewById(R.id.recyclerViewGraduacoes);

        imgNovaGraduacao.setOnClickListener(this);
        ((FloatingActionButton) findViewById(R.id.btnSalvarModalidade)).setOnClickListener(this);

        ModalidadeModel modalidade = getModalidadeIntent();

        if (modalidade != null) {
            loadModality(modalidade);

            Button btnExcluir = findViewById(R.id.btnExcluirModalidade);
            btnExcluir.setVisibility(View.VISIBLE);
            btnExcluir.setOnClickListener(this);
        }

        loadAdapter();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgNovaGraduacao:
                addGraduation();
                break;
            case R.id.btnSalvarModalidade:
                save();
                break;
            case R.id.btnExcluirModalidade:
                delete();
                break;
        }
    }

    private ModalidadeModel getModalidadeIntent(){
        return (ModalidadeModel) getIntent().getSerializableExtra("modalidade");
    }

    private void loadModality(ModalidadeModel modalidade) {
        edtModalidade.setText(modalidade.getModalidade());
        loadGraduations(modalidade.getModalidade());
    }

    private void loadGraduations(String modalidade){
        graduacoes = new GraduacaoDAO(getApplicationContext()).selectForModality(modalidade);
    }

    private void loadAdapter(){
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new GraduacoesAdapter(ModalidadeActivity.this, graduacoes, graduacoesExcluidas));
    }

    private void addGraduation() {

        if (recyclerView.getAdapter() == null){
            return;
        }

        String graduacao = edtGraduacao.getText().toString();

        if (graduacao.trim().isEmpty()){
            DialogDefault.messageOk(ModalidadeActivity.this, null, getString(R.string.informe_uma_descricao_para_graduacao), null, null, null);
            return;
        }

        graduacoes.add(new GraduacaoModel(null, null, graduacao, 1));
        recyclerView.getAdapter().notifyDataSetChanged();
        edtGraduacao.setText(null);
    }

    private void save(){
        String modalidade = edtModalidade.getText().toString();

        if (modalidade.trim().isEmpty()){
            DialogDefault.messageOk(ModalidadeActivity.this, null, getString(R.string.informe_uma_descricao_para_modalidade), null, null, null);
            return;
        }

        if (graduacoes.isEmpty()){
            DialogDefault.messageOk(ModalidadeActivity.this, null, getString(R.string.informe_graduacao_para_modalidade), null, null, null);
            return;
        }

        ModalidadeDAO modalidadeDAO = new ModalidadeDAO(getApplicationContext());
        GraduacaoDAO graduacaoDAO = new GraduacaoDAO(getApplicationContext());

        //Salvar modalidade
        modalidadeDAO.insertOrReplace(new ModalidadeModel(null, modalidade, 1));

        //Deletar graduações
        for (GraduacaoModel graduacao: graduacoesExcluidas){
            graduacaoDAO.delete(graduacao);
        }

        //Salvar graduações
        for (GraduacaoModel graduacao: graduacoes){
            graduacao.setModalidade(modalidade);
            graduacaoDAO.insertOrReplace(graduacao);
        }

        destroyActivity();
    }

    private void destroyActivity(){
        setResult(RESULT_OK, new Intent());
        finish();
    }

    private void delete(){
        DialogDefault.dialogYesNo(this, getString(R.string.deseja_realmente_excluir) , new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int op) {
                switch (op) {
                    case DialogInterface.BUTTON_POSITIVE:
                        ModalidadeDAO modalidadeDAO = new ModalidadeDAO(getApplicationContext());
                        GraduacaoDAO graduacaoDAO = new GraduacaoDAO(getApplicationContext());

                        //Deletar graduações
                        for (GraduacaoModel graduacao: graduacoes){
                            graduacaoDAO.delete(graduacao);
                        }

                        for (GraduacaoModel graduacao: graduacoesExcluidas){
                            graduacaoDAO.delete(graduacao);
                        }

                        //Deletar modalidade
                        modalidadeDAO.delete(getModalidadeIntent());

                        destroyActivity();
                        break;
                    default:
                        dialog.dismiss();
                        break;
                }
            }
        });
    }

}
