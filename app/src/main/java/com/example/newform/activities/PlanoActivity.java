package com.example.newform.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.newform.R;
import com.example.newform.adapter.ArrayAdapterModalidade;
import com.example.newform.dao.ModalidadeDAO;
import com.example.newform.dao.PlanoDAO;
import com.example.newform.dialogs.DialogDefault;
import com.example.newform.dialogs.DialogFiltro;
import com.example.newform.interfaces.GenericCode;
import com.example.newform.models.ModalidadeModel;
import com.example.newform.models.PlanoModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class PlanoActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtPlano;
    private EditText edtValor;
    private EditText edtModalidade;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plano);
        setTitle(getString(R.string.plano));

        edtPlano = findViewById(R.id.edtPlano);
        edtValor = findViewById(R.id.edtValor);
        edtModalidade = findViewById(R.id.edtModalidade);

        edtModalidade.setOnClickListener(this);
        ((FloatingActionButton) findViewById(R.id.btnSalvarPlano)).setOnClickListener(this);

        PlanoModel plano = getPlanoIntent();

        if (plano != null){
            loadPlan(plano);

            Button btnExcluirPlano = findViewById(R.id.btnExcluirPlano);
            btnExcluirPlano.setVisibility(View.VISIBLE);
            btnExcluirPlano.setOnClickListener(this);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSalvarPlano:
                save();
                break;
            case R.id.btnExcluirPlano:
                delete();
                break;
            case R.id.edtModalidade:
                openDialogModality();
                break;
        }
    }

    private PlanoModel getPlanoIntent(){
        return (PlanoModel) getIntent().getSerializableExtra("plano");
    }

    private void loadPlan(PlanoModel plano) {
        edtPlano.setText(plano.getPlano());
        edtValor.setText(plano.getValorMensal() != null ? plano.getValorMensal().toString() : null);
        edtModalidade.setText(plano.getModalidade());
    }

    private void openDialogModality(){
        List<ModalidadeModel> modalidades = new ModalidadeDAO(getApplicationContext()).select();

        if (modalidades.isEmpty()){
            DialogDefault.messageOk(this, null, getString(R.string.sem_modalidades_registradas) + getString(R.string.ponto_exclamacao), null, null, null);
            return;
        }

        final DialogFiltro dialog = new DialogFiltro(PlanoActivity.this, modalidades, new GenericCode.DialogFiltro<ModalidadeModel>() {
            @Override
            public void code(List<ModalidadeModel> listaNova, List<ModalidadeModel> listaCompleta, String busca) {
                for (ModalidadeModel modalidade: listaCompleta) {
                    if (modalidade.getModalidade().toLowerCase().contains(busca.toLowerCase())) {
                        listaNova.add(modalidade);
                    }
                }
            }
        });

        final ArrayAdapterModalidade adapterModalidade = new ArrayAdapterModalidade(PlanoActivity.this, modalidades, new GenericCode.AdapterClick<ModalidadeModel>() {
            @Override
            public void click(ModalidadeModel object) {
                edtModalidade.setText(object.getModalidade());
                dialog.close();
            }
        });

        dialog.setAdapter(adapterModalidade);
        dialog.show();
    }

    private void save(){
        String plano = edtPlano.getText().toString();
        String valor = edtValor.getText().toString();
        String modalidade = edtModalidade.getText().toString();

        if (plano.trim().isEmpty() || valor.trim().isEmpty() || modalidade.trim().isEmpty()){
            DialogDefault.messageOk(this, null, getString(R.string.todos_campos_obrigatorios), null, null, null);
            return;
        }

        PlanoModel planoModel = new PlanoModel();
        planoModel.setPlano(plano);
        planoModel.setValorMensal(Double.parseDouble(valor));
        planoModel.setModalidade(modalidade);
        new PlanoDAO(getApplicationContext()).insertOrReplace(planoModel);

        destroyActivity();
    }

    private void delete() {
        DialogDefault.dialogYesNo(this, getString(R.string.deseja_realmente_excluir) , new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int op) {
                switch (op) {
                    case DialogInterface.BUTTON_POSITIVE:
                        new PlanoDAO(getApplicationContext()).delete(getPlanoIntent());
                        destroyActivity();
                        break;
                    default:
                        dialog.dismiss();
                        break;
                }
            }
        });
    }

    private void destroyActivity(){
        setResult(RESULT_OK, new Intent());
        finish();
    }
}
