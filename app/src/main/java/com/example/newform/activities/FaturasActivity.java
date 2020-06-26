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
import com.example.newform.dao.FaturaMatriculaDAO;
import com.example.newform.models.FaturaMatriculaModel;
import com.example.newform.reyclerview.AdapterDefault;
import com.example.newform.reyclerview.adapter.FaturasAdapter;
import com.example.newform.utils.UtilActivityResult;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.List;

public class FaturasActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faturas);
        setTitle(getString(R.string.faturas));

        loadAdapter();

        ((FloatingActionButton) findViewById(R.id.btnNovaFatura)).setOnClickListener(this);
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
            case R.id.btnNovaFatura:
                startActivityForResult(new Intent(FaturasActivity.this, FaturaActivity.class), UtilActivityResult.NOVO);
                break;
        }
    }

    private void loadAdapter(){
        List<FaturaMatriculaModel> faturas = new FaturaMatriculaDAO(getApplicationContext()).selectAllWithStudent();

        RecyclerView recyclerView = findViewById(R.id.recyclerViewFaturas);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        FaturasAdapter adapter = new FaturasAdapter(this, faturas);
        adapter.setOnClick(new AdapterDefault.OnClick<FaturaMatriculaModel>() {
            @Override
            public void onClick(View v, FaturaMatriculaModel object, int position) {

            }
        });

        recyclerView.setAdapter(adapter);

        ((TextView) findViewById(R.id.txtSemFaturas)).setVisibility(faturas.isEmpty() ? View.VISIBLE : View.GONE);
    }
}
