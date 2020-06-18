package com.example.newform.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.newform.R;
import com.example.newform.adapter.ArrayAdapterAno;
import com.example.newform.adapter.ArrayAdapterMatricula;
import com.example.newform.adapter.ArrayAdapterMes;
import com.example.newform.dao.FaturaMatriculaDAO;
import com.example.newform.dao.MatriculaDAO;
import com.example.newform.dao.MatriculaModalidadeDAO;
import com.example.newform.dialogs.DialogDefault;
import com.example.newform.dialogs.DialogFiltro;
import com.example.newform.enums.AnoEnum;
import com.example.newform.enums.MesEnum;
import com.example.newform.interfaces.GenericCode;
import com.example.newform.models.FaturaMatriculaModel;
import com.example.newform.models.MatriculaModalidadeModel;
import com.example.newform.models.MatriculaModel;
import com.example.newform.reyclerview.adapter.FaturaMatriculaModalidadeAdapter;
import com.example.newform.utils.UtilDate;
import com.example.newform.utils.UtilNumber;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Arrays;
import java.util.List;

@SuppressLint("SetTextI18n")
public class FaturaActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fatura);
        setTitle(getString(R.string.fatura));

        ((FloatingActionButton) findViewById(R.id.btnSalvarFatura)).setOnClickListener(this);
        ((EditText) findViewById(R.id.edtMatricula)).setOnClickListener(this);
        ((EditText) findViewById(R.id.edtFaturaMes)).setOnClickListener(this);
        ((EditText) findViewById(R.id.edtFaturaAno)).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.edtMatricula:
                openDialogMatriculation();
                break;
            case R.id.edtFaturaMes:
                openDialogMonth();
                break;
            case R.id.edtFaturaAno:
                openDialogYear();
                break;
            case R.id.btnSalvarFatura:
                save();
                break;
        }
    }

    private void openDialogMatriculation(){
        List<MatriculaModel> matriculas = new MatriculaDAO(getApplicationContext()).selectWithStudentName();

        if (matriculas.isEmpty()){
            DialogDefault.messageOk(this, null, getString(R.string.sem_matriculas_registradas), null, null, null);
            return;
        }

        final DialogFiltro dialog = new DialogFiltro(this, matriculas, new GenericCode.DialogFiltro<MatriculaModel>() {
            @Override
            public void code(List<MatriculaModel> listaNova, List<MatriculaModel> listaCompleta, String busca) {
                for (MatriculaModel matricula: listaCompleta) {
                    if (matricula.getAluno().toLowerCase().contains(busca.toLowerCase())) {
                        listaNova.add(matricula);
                    }
                }
            }
        });

        final ArrayAdapterMatricula adapter = new ArrayAdapterMatricula(this, matriculas, new GenericCode.AdapterClick<MatriculaModel>() {

            @Override
            public void click(MatriculaModel object) {
                ((EditText) findViewById(R.id.edtMatricula)).setText(object.getId() + " - " + object.getAluno());
                ((EditText) findViewById(R.id.edtFaturaDia)).setText(String.valueOf(object.getDiaVencimento()));
                loadAdapter(String.valueOf(object.getId()));
                dialog.close();
            }
        });

        dialog.setAdapter(adapter);
        dialog.show();
    }

    private void openDialogMonth(){
        List<MesEnum> meses = Arrays.asList(MesEnum.values());

        final DialogFiltro dialog = new DialogFiltro(this, meses, new GenericCode.DialogFiltro<MesEnum>() {
            @Override
            public void code(List<MesEnum> listaNova, List<MesEnum> listaCompleta, String busca) {
                for (MesEnum mes: listaCompleta) {
                    if (mes.getDescription().toLowerCase().contains(busca.toLowerCase())) {
                        listaNova.add(mes);
                    }
                }
            }
        });

        ArrayAdapterMes adapter = new ArrayAdapterMes(this, meses, new GenericCode.AdapterClick<MesEnum>() {
            @Override
            public void click(MesEnum object) {
                ((EditText) findViewById(R.id.edtFaturaMes)).setText(object.getDescription());
                dialog.close();
            }
        });

        dialog.setAdapter(adapter);
        dialog.show();
    }

    private void openDialogYear(){
        List<AnoEnum> anos = Arrays.asList(AnoEnum.values());

        final DialogFiltro dialog = new DialogFiltro(this, anos, new GenericCode.DialogFiltro<AnoEnum>() {
            @Override
            public void code(List<AnoEnum> listaNova, List<AnoEnum> listaCompleta, String busca) {
                for (AnoEnum ano: listaCompleta) {
                    if (ano.getDescription().toLowerCase().contains(busca.toLowerCase())) {
                        listaNova.add(ano);
                    }
                }
            }
        });

        ArrayAdapterAno adapter = new ArrayAdapterAno(this, anos, new GenericCode.AdapterClick<AnoEnum>() {
            @Override
            public void click(AnoEnum object) {
                ((EditText) findViewById(R.id.edtFaturaAno)).setText(object.getDescription());
                dialog.close();
            }
        });

        dialog.setAdapter(adapter);
        dialog.show();
    }

    private void loadAdapter(String idRegistration){
        List<MatriculaModalidadeModel> modalidadesMatricula = new MatriculaModalidadeDAO(getApplicationContext()).selectForRegistrationReturningPlanValue(idRegistration);
        RecyclerView recyclerView = findViewById(R.id.recyclerViewFaturaMatriculaModalidade);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new FaturaMatriculaModalidadeAdapter(this, modalidadesMatricula));

        showTotalValue(modalidadesMatricula);
    }

    private void showTotalValue(List<MatriculaModalidadeModel> modalidadesMatricula){
        LinearLayout linearTotal = findViewById(R.id.linearFaturaTotal);
        TextView txtTotal = findViewById(R.id.txtFaturaTotal);

        Double total = 0d;

        if (modalidadesMatricula.isEmpty()){
            linearTotal.setVisibility(View.GONE);
        } else {
            linearTotal.setVisibility(View.VISIBLE);

            for (MatriculaModalidadeModel modalidade: modalidadesMatricula){
                total += modalidade.getValorMensalPlano();
            }

            txtTotal.setText(getString(R.string.cifrao) + UtilNumber.getDecimalFormat().format(total));
        }

        ((EditText) findViewById(R.id.edtFaturaTotal)).setText(total.toString());
    }

    private void save(){
        String matricula = ((EditText) findViewById(R.id.edtMatricula)).getText().toString();
        String dia = ((EditText) findViewById(R.id.edtFaturaDia)).getText().toString();
        String mes = ((EditText) findViewById(R.id.edtFaturaMes)).getText().toString();
        String ano = ((EditText) findViewById(R.id.edtFaturaAno)).getText().toString();
        String total = ((EditText) findViewById(R.id.edtFaturaTotal)).getText().toString();

        String msgError = null;

        if (dia.trim().isEmpty() || mes.trim().isEmpty() || ano.trim().isEmpty()){
            msgError = getString(R.string.informe_uma_data_valida_para_gerar_fatura);
        }

        if (matricula.trim().isEmpty()){
            msgError = getString(R.string.selecione_uma_matricula_para_gerar_fatura);
        }


        if (msgError != null){
            DialogDefault.messageOk(this, null, msgError, null, null, null);
            return;
        }

        FaturaMatriculaModel model = new FaturaMatriculaModel();
        model.setIdMatricula(Long.parseLong(matricula.split("-")[0].trim()));
        model.setDtVencimento(UtilDate.getDateFromValues(Integer.parseInt(dia), MesEnum.getEnumFromDescription(mes).getValue(), Integer.parseInt(ano)));
        model.setValor(Double.parseDouble(total));
        model.setAtivo(1);
        new FaturaMatriculaDAO(getApplicationContext()).insertOrReplace(model);
        destroyActivity();
    }

    private void destroyActivity(){
        setResult(RESULT_OK, new Intent());
        finish();
    }

}
