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
import com.example.newform.dao.ModalidadeDAO;
import com.example.newform.models.ModalidadeModel;
import com.example.newform.reyclerview.AdapterDefault;
import com.example.newform.reyclerview.adapter.ModalidadesAdapter;
import com.example.newform.utils.UtilActivityResult;
import java.io.Serializable;
import java.util.List;

public class ModalidadesActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modalidades);
        setTitle(getString(R.string.modalidades));

        findViewById(R.id.btnNovaModalidade).setOnClickListener(this);

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
        List<ModalidadeModel> modalidades = new ModalidadeDAO(getApplicationContext()).select();

        RecyclerView recyclerView = findViewById(R.id.recyclerViewModalidades);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        ModalidadesAdapter adapter = new ModalidadesAdapter(this, modalidades);
        adapter.setOnClick(new AdapterDefault.OnClick<ModalidadeModel>() {
            @Override
            public void onClick(View v, ModalidadeModel object, int position) {
                Intent intent = new Intent(ModalidadesActivity.this, ModalidadeActivity.class);
                intent.putExtra("modalidade", (Serializable) object);
                startActivityForResult(intent, UtilActivityResult.EDITAR);
            }
        });

        recyclerView.setAdapter(adapter);

        ((TextView) findViewById(R.id.txtSemModalidades)).setVisibility(modalidades.isEmpty() ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnNovaModalidade:
                startActivityForResult(new Intent(ModalidadesActivity.this, ModalidadeActivity.class), UtilActivityResult.NOVO);
                break;
        }
    }
}
