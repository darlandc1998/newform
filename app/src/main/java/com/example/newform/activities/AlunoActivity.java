package com.example.newform.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import com.example.newform.R;
import com.example.newform.adapter.ArrayAdapterCidade;
import com.example.newform.dao.AlunoDAO;
import com.example.newform.dao.CidadeDAO;
import com.example.newform.dialogs.DialogCalendar;
import com.example.newform.dialogs.DialogDefault;
import com.example.newform.dialogs.DialogFiltro;
import com.example.newform.interfaces.GenericCode;
import com.example.newform.models.AlunoModel;
import com.example.newform.models.CidadeModel;
import com.example.newform.utils.UtilDate;
import com.example.newform.utils.UtilMask;
import com.example.newform.utils.UtilString;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Date;
import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.N)
public class AlunoActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtNome;
    private EditText edtDtNascimento;
    private EditText edtTelefone;
    private EditText edtCelular;
    private EditText edtEmail;
    private EditText edtObservacao;
    private EditText edtEndereco;
    private EditText edtNumero;
    private EditText edtComplemento;
    private EditText edtBairro;
    private EditText edtCidadeEstadoPais;
    private EditText edtCep;
    private RadioButton rbMasculino;
    private RadioButton rbFeminino;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aluno);
        setTitle(getString(R.string.aluno));

        edtNome = findViewById(R.id.edtNome);
        edtDtNascimento = findViewById(R.id.edtDtNascimento);
        edtTelefone = findViewById(R.id.edtTelefone);
        edtCelular = findViewById(R.id.edtCelular);
        edtEmail = findViewById(R.id.edtEmail);
        edtObservacao = findViewById(R.id.edtObservacao);
        edtEndereco = findViewById(R.id.edtEndereco);
        edtNumero = findViewById(R.id.edtNumero);
        edtComplemento = findViewById(R.id.edtComplemento);
        edtBairro = findViewById(R.id.edtBairro);
        edtCidadeEstadoPais = findViewById(R.id.edtCidadeEstadoPais);
        edtCep = findViewById(R.id.edtCep);
        rbMasculino = findViewById(R.id.rbMasculino);
        rbFeminino = findViewById(R.id.rbFeminino);

        edtCidadeEstadoPais.setOnClickListener(this);

        ((ImageView) findViewById(R.id.imgCalendar)).setOnClickListener(this);
        ((FloatingActionButton) findViewById(R.id.btnSalvarAluno)).setOnClickListener(this);

       addMasks();

       AlunoModel alunoIntent = getAlunoIntent();

       if (alunoIntent != null){
           loadStudent(alunoIntent);

           Button btnExcluirAluno = findViewById(R.id.btnExcluirAluno);
           btnExcluirAluno.setVisibility(View.VISIBLE);
           btnExcluirAluno.setOnClickListener(this);
       }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.edtCidadeEstadoPais:
                openDialogCityStateCountry();
                break;
            case R.id.imgCalendar:
                openDialogCalendar(edtDtNascimento);
                break;
            case R.id.btnSalvarAluno:
                save();
                break;
            case R.id.btnExcluirAluno:
                delete();
                break;
        }
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.rbMasculino:
                if (checked)
                    break;
            case R.id.rbFeminino:
                if (checked)
                    break;
        }
    }

    private void addMasks() {
        edtTelefone.addTextChangedListener(UtilMask.insertPhone(edtTelefone));
        edtCelular.addTextChangedListener(UtilMask.insertPhone(edtCelular));
        edtCep.addTextChangedListener(UtilMask.insert(UtilMask.CEP_MASK, edtCep));
        edtDtNascimento.addTextChangedListener(UtilMask.insert(UtilMask.DATA_MASK, edtDtNascimento));
    }

    private void openDialogCityStateCountry() {
        CidadeDAO cidadeDAO = new CidadeDAO(getApplicationContext());
        List<CidadeModel> cidades = cidadeDAO.selectAll();

        final DialogFiltro dialog = new DialogFiltro(AlunoActivity.this, cidades, new GenericCode.DialogFiltro<CidadeModel>() {
            @Override
            public void code(List<CidadeModel> listaNova, List<CidadeModel> listaCompleta, String busca) {
                for (CidadeModel cidade: listaCompleta) {
                    if (cidade.getCidade().toLowerCase().contains(busca.toLowerCase())) {
                        listaNova.add(cidade);
                    }
                }
            }
        });

        final ArrayAdapterCidade adapterCidade = new ArrayAdapterCidade(AlunoActivity.this, cidades, new GenericCode.AdapterClick<CidadeModel>() {
            @Override
            public void click(CidadeModel object) {
                edtCidadeEstadoPais.setText(object.toString());
                dialog.close();
            }
        });

        dialog.setAdapter(adapterCidade);
        dialog.show();
    }

    private void openDialogCalendar(final EditText edtDate) {
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

    private AlunoModel getAlunoIntent(){
       return (AlunoModel) getIntent().getSerializableExtra("aluno");
    }

    private void loadStudent(AlunoModel aluno) {
        edtNome.setText(aluno.getAluno());
        edtDtNascimento.setText(UtilDate.getDateFormatPtBr(aluno.getDtNascimento()));

        if (aluno.getSexo() != null){
            switch (aluno.getSexo()){
                case "M":
                    rbMasculino.setChecked(true);
                    break;
                case "F":
                    rbFeminino.setChecked(true);
                    break;
            }
        }

        if (aluno.getTelefone() != null) {
            edtTelefone.setText(aluno.getTelefone());
        }

        if (aluno.getCelular() != null) {
            edtCelular.setText(aluno.getCelular());
        }

        if (aluno.getEmail() != null) {
            edtEmail.setText(aluno.getEmail());
        }

        if (aluno.getObservacao() != null) {
            edtObservacao.setText(aluno.getObservacao());
        }

        if (aluno.getEndereco() != null) {
            edtEndereco.setText(aluno.getEndereco());
        }

        if (aluno.getNumero() != null) {
            edtNumero.setText(aluno.getNumero());
        }

        if (aluno.getComplemento() != null) {
            edtComplemento.setText(aluno.getComplemento());
        }

        if (aluno.getBairro() != null) {
            edtBairro.setText(aluno.getBairro());
        }

        if (aluno.getCidade() != null && aluno.getEstado() != null && aluno.getPais() != null) {
            edtCidadeEstadoPais.setText(aluno.getCidade() + " - " + aluno.getEstado() + " - " + aluno.getPais());
        }

        if (aluno.getCep() != null) {
            edtCep.setText(aluno.getCep());
        }
    }

    private void save() {
        String nome = edtNome.getText().toString();
        String dtNascimento = edtDtNascimento.getText().toString();
        String telefone = edtTelefone.getText().toString();
        String celular = edtCelular.getText().toString();
        String email = edtEmail.getText().toString();
        String observacao = edtObservacao.getText().toString();
        String endereco = edtEndereco.getText().toString();
        String numero = edtNumero.getText().toString();
        String complemento = edtComplemento.getText().toString();
        String bairro = edtBairro.getText().toString();
        String cidadeEstadoPais = edtCidadeEstadoPais.getText().toString();
        String cep = edtCep.getText().toString();
        String sexo = rbMasculino.isChecked() || rbFeminino.isChecked() ? (rbMasculino.isChecked() ? "M" : "F") : "";

        String messageValidate = null;

        //Campos obrigatórios
        if (endereco.trim().isEmpty()){
            messageValidate = getString(R.string.endereco);
        }

        if (bairro.trim().isEmpty()){
            messageValidate = getString(R.string.bairro);
        }

        if (cidadeEstadoPais.isEmpty()){
            messageValidate = getString(R.string.cidade_estado_pais);
        }

        if (telefone.isEmpty()){
            messageValidate = getString(R.string.telefone);
        }

        if (sexo.isEmpty()){
            messageValidate = getString(R.string.sexo);
        }

        if (dtNascimento.isEmpty()){
            messageValidate = getString(R.string.dtNascimento);
        }

        if (nome.isEmpty()){
            messageValidate = getString(R.string.nome);
        }

        if (messageValidate != null){
            DialogDefault.messageOk(this, null, getString(R.string.obrigatorio_campo) + " " + messageValidate.toLowerCase() + getString(R.string.ponto_exclamacao), null, null, null);
            return;
        }

        //Validações necessárias para atributos
        if (dtNascimento.length() < 10){
            messageValidate = getString(R.string.data_nascimento_invalida);
        }

        if (!email.isEmpty() && !UtilString.validateEmail(email)){
            messageValidate = getString(R.string.email_invalido);
        }

        if (messageValidate != null){
            DialogDefault.messageOk(this, null, messageValidate, null, null, null);
            return;
        }

        AlunoModel alunoIntent = getAlunoIntent();

        AlunoDAO alunoDAO = new AlunoDAO(getApplicationContext());
        AlunoModel aluno = new AlunoModel();

        if (alunoIntent != null){
            aluno.setId(alunoIntent.getId());
        }

        if (!nome.trim().isEmpty()){
            aluno.setAluno(nome);
        }

        if (!dtNascimento.trim().isEmpty()){
             aluno.setDtNascimento(UtilDate.getDateFromString(dtNascimento));
        }

        if (!sexo.trim().isEmpty()){
            aluno.setSexo(sexo);
        }

        if (!telefone.trim().isEmpty()) {
            aluno.setTelefone(telefone.replace("(", "").replace(")", "").replace("-", "").replace(" ", ""));
        }

        if (!celular.trim().isEmpty()) {
            aluno.setCelular(celular.replace("(", "").replace(")", "").replace("-", "").replace(" ", ""));
        }

        if (!email.trim().isEmpty()){
            aluno.setEmail(email);
        }

        if (!observacao.trim().isEmpty()){
            aluno.setObservacao(observacao);
        }

        if (!endereco.trim().isEmpty()){
            aluno.setEndereco(endereco);
        }

        if (!numero.trim().isEmpty()){
            aluno.setNumero(numero);
        }

        if (!complemento.trim().isEmpty()){
            aluno.setComplemento(complemento);
        }

        if (!bairro.trim().isEmpty()){
            aluno.setBairro(bairro);
        }

        if (!cidadeEstadoPais.trim().isEmpty()) {
            String[] dados = cidadeEstadoPais.split("-");
            aluno.setCidade(dados[0].trim());
            aluno.setEstado(dados[1].trim());
            aluno.setPais(dados[2].trim());
        }

        if (!cep.trim().isEmpty()){
            aluno.setCep(cep.replace("-","").trim());
        }

        alunoDAO.insertOrUpdate(aluno);
        destroyActivity();
    }

    private void delete(){
        DialogDefault.dialogYesNo(this, getString(R.string.deseja_realmente_excluir) , new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int op) {
                switch (op) {
                    case DialogInterface.BUTTON_POSITIVE:
                        new AlunoDAO(getApplicationContext()).delete(getAlunoIntent());
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
