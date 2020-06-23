package com.example.newform.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.newform.R;
import com.example.newform.adapter.ArrayAdapterAluno;
import com.example.newform.adapter.ArrayAdapterGraduacao;
import com.example.newform.adapter.ArrayAdapterModalidade;
import com.example.newform.adapter.ArrayAdapterPlano;
import com.example.newform.dao.AlunoDAO;
import com.example.newform.dao.GraduacaoDAO;
import com.example.newform.dao.MatriculaDAO;
import com.example.newform.dao.MatriculaModalidadeDAO;
import com.example.newform.dao.ModalidadeDAO;
import com.example.newform.dao.PlanoDAO;
import com.example.newform.dialogs.DialogCalendar;
import com.example.newform.dialogs.DialogDefault;
import com.example.newform.dialogs.DialogFiltro;
import com.example.newform.interfaces.GenericCode;
import com.example.newform.models.AlunoModel;
import com.example.newform.models.GraduacaoModel;
import com.example.newform.models.MatriculaModalidadeModel;
import com.example.newform.models.MatriculaModel;
import com.example.newform.models.ModalidadeModel;
import com.example.newform.models.PlanoModel;
import com.example.newform.reyclerview.AdapterDefault;
import com.example.newform.reyclerview.adapter.MatriculaModalidadeAdapter;
import com.example.newform.utils.UtilDate;
import com.example.newform.utils.UtilMask;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.N)
public class MatriculasActivity extends AppCompatActivity implements View.OnClickListener {


    private List<MatriculaModalidadeModel> listMatriculaModalidades = new ArrayList<>();
    private List<MatriculaModalidadeModel> listMatriculaModalidadesExcluidas = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matriculas);
        setTitle(getString(R.string.matriculas));

        ((EditText) findViewById(R.id.edtAluno)).setOnClickListener(this);
        ((Button) findViewById(R.id.btnNovaMatriculaModalidade)).setOnClickListener(this);
        ((FloatingActionButton) findViewById(R.id.btnSalvarMatricula)).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSalvarMatricula:
                save();
                break;
            case R.id.edtAluno:
                openDialogStudent();
                break;
            case R.id.btnNovaMatriculaModalidade:
                openDialogRegisterModality();
                break;
        }
    }

    private void openDialogRegisterModality(){
        openDialogRegisterModality(null);
    }

    private void openDialogRegisterModality(final MatriculaModalidadeModel matriculaModalidade){
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        final LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View view = inflater.inflate(R.layout.dialog_matricula_modalidade, null);

        alertDialog.setView(view);
        final AlertDialog dialog = alertDialog.show();

        final EditText edtModalidade = view.findViewById(R.id.edtModalidade);
        final EditText edtGraduacao = view.findViewById(R.id.edtGraduacao);
        final EditText edtDtInicio = view.findViewById(R.id.edtDtInicio);
        final EditText edtPlano  = view.findViewById(R.id.edtPlano);
        Button btnSalvarModalidade = view.findViewById(R.id.btnSalvarModalidade);
        Button btnExcluirModalidade = view.findViewById(R.id.btnExcluirModalidade);
        ImageView imgCalendar = view.findViewById(R.id.imgCalendar);

        edtDtInicio.addTextChangedListener(UtilMask.insert(UtilMask.DATA_MASK, edtDtInicio));

        if (matriculaModalidade != null){
            edtModalidade.setText(matriculaModalidade.getModalidade());
            edtGraduacao.setText(matriculaModalidade.getGraduacao());
            edtDtInicio.setText(UtilDate.getDateFormatPtBr(matriculaModalidade.getDtInicio()));
            edtPlano.setText(matriculaModalidade.getPlano());

            //Desabilitar e mostrar componentes para edição
            edtModalidade.setEnabled(false);
            edtGraduacao.setEnabled(false);
            edtPlano.setEnabled(false);
            btnExcluirModalidade.setVisibility(View.VISIBLE);
        }

        edtModalidade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialogModality(view);
            }
        });

        edtGraduacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String modality = edtModalidade.getText().toString();

                if (modality.trim().isEmpty()){
                    DialogDefault.messageOk(MatriculasActivity.this, null, getString(R.string.selecione_uma_modalidade), null, null, false, null);
                    return;
                }

                openDialogGraduation(view, modality);
            }
        });

        edtPlano.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String modality = edtModalidade.getText().toString();

                if (modality.trim().isEmpty()){
                    DialogDefault.messageOk(MatriculasActivity.this, null, getString(R.string.selecione_uma_modalidade), null, null, false, null);
                    return;
                }

                openDialogPlan(view, modality);
            }
        });

        imgCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialogCalendar(edtDtInicio);
            }
        });

        btnExcluirModalidade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogDefault.dialogYesNo(MatriculasActivity.this, getString(R.string.deseja_realmente_excluir_modalidade) , new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogQuestion, int op) {
                        switch (op) {
                            case DialogInterface.BUTTON_POSITIVE:
                                listMatriculaModalidadesExcluidas.add(matriculaModalidade);
                                listMatriculaModalidades.remove(matriculaModalidade);
                                dialog.dismiss();
                                loadAdapter();
                                break;
                            default:
                                dialogQuestion.dismiss();
                                break;
                        }
                    }
                });
            }
        });

        btnSalvarModalidade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String modalidade = edtModalidade.getText().toString();
                String graduacao = edtGraduacao.getText().toString();
                String dtInicio = edtDtInicio.getText().toString();
                String plano = edtPlano.getText().toString();

                if (modalidade.trim().isEmpty()){
                    DialogDefault.messageOk(MatriculasActivity.this, null, getString(R.string.selecione_uma_modalidade), null, null, false, null);
                    return;
                }

                if (graduacao.trim().isEmpty()){
                    DialogDefault.messageOk(MatriculasActivity.this, null, getString(R.string.selecione_uma_graduacao), null, null, false, null);
                    return;
                }

                if (plano.trim().isEmpty()){
                    DialogDefault.messageOk(MatriculasActivity.this, null, getString(R.string.selecione_um_plano), null, null, false, null);
                    return;
                }

                MatriculaModalidadeModel matriculaModalidadeSave = new MatriculaModalidadeModel();

                if (matriculaModalidade != null) {
                    matriculaModalidadeSave.setIdMatricula(matriculaModalidade.getIdMatricula());
                }

                matriculaModalidadeSave.setModalidade(modalidade);
                matriculaModalidadeSave.setGraduacao(graduacao);
                matriculaModalidadeSave.setDtInicio(UtilDate.getDateFromString(dtInicio));
                matriculaModalidadeSave.setPlano(plano);

                if (listMatriculaModalidades.contains(matriculaModalidadeSave)){
                    listMatriculaModalidades.set(listMatriculaModalidades.indexOf(matriculaModalidadeSave), matriculaModalidadeSave);
                } else {
                    listMatriculaModalidades.add(matriculaModalidadeSave);
                }

                dialog.dismiss();

                loadAdapter();
            }
        });


    }

    private void openDialogCalendar(final EditText edtDate){
        Date date = new Date();

        String dateInput = edtDate.getText().toString();

        if (dateInput.length() == 10) {
            date = UtilDate.getDateFromString(dateInput);
        }

        final DialogCalendar dialogCalendar = new DialogCalendar(this, date, new GenericCode.DateCode() {
            @Override
            public void code(Date date) {
                edtDate.setText(UtilDate.getDateFormatPtBr(date));
            }
        });
        dialogCalendar.show();
    }

    private void openDialogStudent(){
        AlunoDAO alunoDAO = new AlunoDAO(getApplicationContext());
        List<AlunoModel> alunos = alunoDAO.select();

        if (alunos.isEmpty()){
            DialogDefault.messageOk(this, null, getString(R.string.sem_alunos_registrados), null, null, null);
            return;
        }

        final DialogFiltro dialog = new DialogFiltro(MatriculasActivity.this, alunos, new GenericCode.DialogFiltro<AlunoModel>() {
            @Override
            public void code(List<AlunoModel> listaNova, List<AlunoModel> listaCompleta, String busca) {
                for (AlunoModel aluno: listaCompleta) {
                    if (aluno.getAluno().toLowerCase().contains(busca.toLowerCase())) {
                        listaNova.add(aluno);
                    }
                }
            }
        });

        final ArrayAdapterAluno adapter = new ArrayAdapterAluno(MatriculasActivity.this, alunos, new GenericCode.AdapterClick<AlunoModel>() {
            @Override
            public void click(AlunoModel object) {
                ((EditText) findViewById(R.id.edtAluno)).setText(object.toString());
                ((EditText) findViewById(R.id.edtDtCadastro)).setText(UtilDate.getDateFormatPtBr(new Date()));

                MatriculaModel matricula;

                if ((matricula = new MatriculaDAO(getApplicationContext()).getObjectFromStudent(String.valueOf(object.getId()))) != null){
                    popularRegistration(matricula);
                } else {
                    clearRegistration();
                }

                dialog.close();
            }
        });

        dialog.setAdapter(adapter);
        dialog.show();
    }

    private void openDialogModality(final View view) {
        List<ModalidadeModel> modalidades = new ModalidadeDAO(getApplicationContext()).select();

        if (modalidades.isEmpty()){
            DialogDefault.messageOk(this, null, getResources().getString(R.string.sem_modalidades_registradas), null, null, null);
            return;
        }

        final DialogFiltro dialog = new DialogFiltro(MatriculasActivity.this, modalidades, new GenericCode.DialogFiltro<ModalidadeModel>() {
            @Override
            public void code(List<ModalidadeModel> listaNova, List<ModalidadeModel> listaCompleta, String busca) {
                for (ModalidadeModel modalidade: listaCompleta) {
                    if (modalidade.getModalidade().toLowerCase().contains(busca.toLowerCase())) {
                        listaNova.add(modalidade);
                    }
                }
            }
        });

        final ArrayAdapterModalidade adapter = new ArrayAdapterModalidade(MatriculasActivity.this, modalidades, new GenericCode.AdapterClick<ModalidadeModel>() {
            @Override
            public void click(ModalidadeModel object) {
                ((EditText) view.findViewById(R.id.edtModalidade)).setText(object.getModalidade());
                ((EditText) view.findViewById(R.id.edtGraduacao)).setText(null);
                ((EditText) view.findViewById(R.id.edtPlano)).setText(null);
                dialog.close();
            }
        });

        dialog.setAdapter(adapter);
        dialog.show();

    }

    private void openDialogGraduation(final View view, String modality){
        List<GraduacaoModel> graduacoes = new GraduacaoDAO(getApplicationContext()).selectForModality(modality);

        if (graduacoes.isEmpty()){
            DialogDefault.messageOk(this, null, getString(R.string.sem_graduacoes_registradas_para_modalidade), null, null, null);
            return;
        }

        final DialogFiltro dialog = new DialogFiltro(MatriculasActivity.this, graduacoes, new GenericCode.DialogFiltro<GraduacaoModel>() {
            @Override
            public void code(List<GraduacaoModel> listaNova, List<GraduacaoModel> listaCompleta, String busca) {
                for (GraduacaoModel graduacao: listaCompleta) {
                    if (graduacao.getGraduacao().toLowerCase().contains(busca.toLowerCase())) {
                        listaNova.add(graduacao);
                    }
                }
            }
        });

        final ArrayAdapterGraduacao adapter = new ArrayAdapterGraduacao(MatriculasActivity.this, graduacoes, new GenericCode.AdapterClick<GraduacaoModel>() {
            @Override
            public void click(GraduacaoModel object) {
                ((EditText) view.findViewById(R.id.edtGraduacao)).setText(object.getGraduacao());

                dialog.close();
            }
        });

        dialog.setAdapter(adapter);
        dialog.show();
    }

    private void openDialogPlan(final View view, String modality){
        List<PlanoModel> planos = new PlanoDAO(getApplicationContext()).selectForModality(modality);

        if (planos.isEmpty()){
            DialogDefault.messageOk(this, null, getString(R.string.sem_planos_registrados_para_modalidade), null, null, null);
            return;
        }

        final DialogFiltro dialog = new DialogFiltro(MatriculasActivity.this, planos, new GenericCode.DialogFiltro<PlanoModel>() {
            @Override
            public void code(List<PlanoModel> listaNova, List<PlanoModel> listaCompleta, String busca) {
                for (PlanoModel plano: listaCompleta) {
                    if (plano.getPlano().toLowerCase().contains(busca.toLowerCase())) {
                        listaNova.add(plano);
                    }
                }
            }
        });

        final ArrayAdapterPlano adapter = new ArrayAdapterPlano(MatriculasActivity.this, planos, new GenericCode.AdapterClick<PlanoModel>() {
            @Override
            public void click(PlanoModel object) {
                ((EditText) view.findViewById(R.id.edtPlano)).setText(object.getPlano());
                dialog.close();
            }
        });

        dialog.setAdapter(adapter);
        dialog.show();
    }

    private void save(){
        String codigo = ((EditText) findViewById(R.id.edtIdMatricula)).getText().toString();
        String dtMatricula = ((EditText) findViewById(R.id.edtDtCadastro)).getText().toString();
        String vencimento = ((EditText) findViewById(R.id.edtDiaVencimento)).getText().toString();
        String aluno = ((EditText) findViewById(R.id.edtAluno)).getText().toString();

        if (aluno.trim().isEmpty()){
            DialogDefault.messageOk(MatriculasActivity.this, null, getString(R.string.selecione_um_aluno), null, null, false, null);
            return;
        }

        if (vencimento.trim().isEmpty()){
            DialogDefault.messageOk(MatriculasActivity.this, null, getString(R.string.dia_vencimento_matricula), null, null, false, null);
            return;
        }

        if (Integer.parseInt(vencimento) > 30){
            DialogDefault.messageOk(MatriculasActivity.this, null, getString(R.string.dia_vencimento_invalido), null, null, false, null);
            return;
        }

        if (dtMatricula.trim().isEmpty()){
            DialogDefault.messageOk(MatriculasActivity.this, null, getString(R.string.data_cadastro_obrigatoria), null, null, false, null);
            return;
        }

        if (listMatriculaModalidades.isEmpty()) {
            DialogDefault.messageOk(MatriculasActivity.this, null, getString(R.string.matricula_modalidade_obrigatoria), null, null, false, null);
            return;
        }

        MatriculaModel matricula = new MatriculaModel();

        if (!codigo.trim().isEmpty()) {
            matricula.setId(Long.parseLong(codigo));
        }

        matricula.setIdAluno(Long.parseLong(aluno.split("-")[0].trim()));
        matricula.setAtivo(1);
        matricula.setDtMatricula(UtilDate.getDateFromString(dtMatricula));
        matricula.setDiaVencimento(Integer.parseInt(vencimento));
        matricula.setId(new MatriculaDAO(getApplicationContext()).insertOrReplace(matricula));

        MatriculaModalidadeDAO matriculaModalidadeDAO = new MatriculaModalidadeDAO(getApplicationContext());

        for (MatriculaModalidadeModel matriculaModalidadeExcluida: listMatriculaModalidadesExcluidas){
            matriculaModalidadeDAO.delete(matriculaModalidadeExcluida);
        }

        for (MatriculaModalidadeModel matriculaModalidade: listMatriculaModalidades){
            matriculaModalidade.setIdMatricula(matricula.getId());
            matriculaModalidadeDAO.insertOrReplace(matriculaModalidade);
        }

        finish();
    }

    private void popularRegistration(MatriculaModel matricula){
        ((EditText) findViewById(R.id.edtIdMatricula)).setText(String.valueOf(matricula.getId()));
        ((EditText) findViewById(R.id.edtDtCadastro)).setText(UtilDate.getDateFormatPtBr(matricula.getDtMatricula()));
        ((EditText) findViewById(R.id.edtDiaVencimento)).setText(String.valueOf(matricula.getDiaVencimento()));

        List<MatriculaModalidadeModel> modalidades = new MatriculaModalidadeDAO(getApplicationContext()).selectForRegistration(String.valueOf(matricula.getId()));

        if (!modalidades.isEmpty()){
            listMatriculaModalidades = modalidades;
            loadAdapter();
        }
    }

    private void clearRegistration(){
        ((EditText) findViewById(R.id.edtIdMatricula)).setText(null);
        ((EditText) findViewById(R.id.edtDiaVencimento)).setText(null);
        listMatriculaModalidades = new ArrayList<>();
        listMatriculaModalidadesExcluidas = new ArrayList<>();
        loadAdapter();
    }

    private void loadAdapter(){
        RecyclerView recyclerView = findViewById(R.id.recyclerViewMatriculaModalidades);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        MatriculaModalidadeAdapter adapter = new MatriculaModalidadeAdapter(this, listMatriculaModalidades);
        adapter.setOnClick(new AdapterDefault.OnClick<MatriculaModalidadeModel>() {
            @Override
            public void onClick(View v, MatriculaModalidadeModel object, int position) {
                openDialogRegisterModality(object);
            }
        });

        recyclerView.setAdapter(adapter);
    }

}
