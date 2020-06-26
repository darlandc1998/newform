package com.example.newform.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.newform.R;
import com.example.newform.api.API;
import com.example.newform.dialogs.DialogDefault;
import com.example.newform.models.UsuarioModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        ((TextView) findViewById(R.id.txtNovoUsuario)).setOnClickListener(this);
        ((Button) findViewById(R.id.btnLogar)).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogar:
                startActivity(new Intent(MainActivity.this, HomeActivity.class));
                //login();
                break;
            case R.id.txtNovoUsuario:
                startActivity(new Intent(MainActivity.this, NovoUsuarioActivity.class));
                break;
        }
    }

    private void login(){
        String usuario = ((EditText) findViewById(R.id.edtUsuario)).getText().toString();
        String senha = ((EditText) findViewById(R.id.edtSenha)).getText().toString();;

        if (usuario.trim().isEmpty() || senha.trim().isEmpty()){
            DialogDefault.messageOk(this, null, getString(R.string.informe_usuario_senha_para_logar), null, null, null);
            return;
        }

        API.getUsuario(usuario, senha, new Callback<UsuarioModel>() {
            @Override
            public void onResponse(Call<UsuarioModel> call, Response<UsuarioModel> response) {
                if (response == null){
                    DialogDefault.messageOk(MainActivity.this, null, getString(R.string.dados_invalidos), null, null, null);
                } else if (response.body().getHabilitado()) {
                    startActivity(new Intent(MainActivity.this, HomeActivity.class));
                } else {
                    DialogDefault.messageOk(MainActivity.this, null, getString(R.string.usuario_nao_habilitado), null, null, null);
                }
            }

            @Override
            public void onFailure(Call<UsuarioModel> call, Throwable t) {
                DialogDefault.messageOk(MainActivity.this, null, t.getMessage(), null, null, null);
            }
        });
    }


}
