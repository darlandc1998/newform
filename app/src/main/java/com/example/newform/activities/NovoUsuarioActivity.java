package com.example.newform.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.newform.R;
import com.example.newform.api.API;
import com.example.newform.dialogs.DialogDefault;
import com.example.newform.enums.SharedEnum;
import com.example.newform.models.RespostaModel;
import com.example.newform.models.UsuarioModel;
import com.example.newform.utils.UtilSharedPreferences;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NovoUsuarioActivity extends AppCompatActivity implements View.OnClickListener  {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_usuario);
        getSupportActionBar().hide();

        ((Button) findViewById(R.id.btnCadastrar)).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnCadastrar:
                saveUser();
                break;
        }
    }

    private void saveUser(){
        String nome = ((EditText) findViewById(R.id.edtUsuario)).getText().toString();
        String senha = ((EditText) findViewById(R.id.edtSenha)).getText().toString();
        String senhaConfirmacao = ((EditText) findViewById(R.id.edtSenhaConfirmacao)).getText().toString();

        String mensagem = null;

        if (!senha.equals(senhaConfirmacao)){
            mensagem = getString(R.string.senhas_nao_conferem);
        }

        if (senhaConfirmacao.trim().isEmpty() || senha.trim().isEmpty()){
            mensagem = getString(R.string.preencha_ambos_campos_de_senha_para_cadastro);
        }

        if (nome.trim().isEmpty()){
            mensagem = getString(R.string.preencha_campo_usuario_para_cadastro);
        }

        if (mensagem != null){
            DialogDefault.messageOk(this, null, mensagem, null, null, null);
            return;
        }

        final UsuarioModel usuario = new UsuarioModel();
        usuario.setNome(nome);
        usuario.setSenha(senha);
        usuario.setHabilitado(true);

        API.postUsuario(usuario, new Callback<RespostaModel>() {
            @Override
            public void onResponse(Call<RespostaModel> call, Response<RespostaModel> response) {
                if (response != null && response.body() != null && response.body().getSucesso()) {
                    Toast.makeText(NovoUsuarioActivity.this, R.string.conta_criada_com_sucesso, Toast.LENGTH_SHORT).show();
                    UtilSharedPreferences.putLong(NovoUsuarioActivity.this, SharedEnum.CODIGO_ALUNO.toString(), response.body().getCodigo());
                    finish();
                } else {
                    DialogDefault.messageOk(NovoUsuarioActivity.this, null, response.body().getMensagem(), null, null, null);
                }
            }

            @Override
            public void onFailure(Call<RespostaModel> call, Throwable t) {
                DialogDefault.messageOk(NovoUsuarioActivity.this, null, t.getMessage(), null, null, null);
            }
        });
    }
}
