package com.example.newform.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.newform.R;
import com.example.newform.dao.PlanoDAO;
import com.example.newform.models.PlanoModel;
import com.example.newform.reyclerview.AdapterDefault;
import com.example.newform.reyclerview.adapter.PlanosAdapter;
import com.example.newform.utils.UtilActivityResult;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.io.Serializable;
import java.util.List;

public class PlanosActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planos);
        setTitle(getString(R.string.planos));

        ((FloatingActionButton) findViewById(R.id.btnNovoPlano)).setOnClickListener(this);

        loadAdapter();
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

    private void loadAdapter(){
        List<PlanoModel> planos = new PlanoDAO(getApplicationContext()).select();

        RecyclerView recycler = findViewById(R.id.recyclerViewPlanos);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setHasFixedSize(true);

        PlanosAdapter adapter = new PlanosAdapter(PlanosActivity.this, planos);
        adapter.setOnClick(new AdapterDefault.OnClick<PlanoModel>() {
            @Override
            public void onClick(View v, PlanoModel object, int position) {
                Intent intent = new Intent(PlanosActivity.this, PlanoActivity.class);
                intent.putExtra("plano", (Serializable) object);
                startActivityForResult(intent, UtilActivityResult.EDITAR);
            }
        });

        recycler.setAdapter(adapter);

        ((TextView) findViewById(R.id.txtSemPlanos)).setVisibility(planos.isEmpty() ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnNovoPlano:
                startActivityForResult(new Intent(PlanosActivity.this, PlanoActivity.class), UtilActivityResult.NOVO);
                break;
        }
    }
}
