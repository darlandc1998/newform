package com.example.newform.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.newform.R;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener  {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ((LinearLayout) findViewById(R.id.menuAlunos)).setOnClickListener(this);
        ((LinearLayout) findViewById(R.id.menuModalidades)).setOnClickListener(this);
        ((LinearLayout) findViewById(R.id.menuPlanos)).setOnClickListener(this);
        ((LinearLayout) findViewById(R.id.menuMatriculas)).setOnClickListener(this);
        ((LinearLayout) findViewById(R.id.menuFaturas)).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.menuAlunos:
                startActivity(new Intent(HomeActivity.this, AlunosActivity.class));
                break;
            case R.id.menuModalidades:
                startActivity(new Intent(HomeActivity.this, ModalidadesActivity.class));
                break;
            case R.id.menuPlanos:
                startActivity(new Intent(HomeActivity.this, PlanosActivity.class));
                break;
            case R.id.menuMatriculas:
                startActivity(new Intent(HomeActivity.this, MatriculasActivity.class));
                break;
            case R.id.menuFaturas:
                startActivity(new Intent(HomeActivity.this, FaturasActivity.class));
                break;
        }
    }
}
