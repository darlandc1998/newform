package com.example.newform.sync;

import android.app.Activity;
import android.util.Log;
import com.example.newform.dao.AlunoDAO;
import com.example.newform.dao.GraduacaoDAO;
import com.example.newform.dao.MatriculaDAO;
import com.example.newform.dao.MatriculaModalidadeDAO;
import com.example.newform.dao.ModalidadeDAO;
import com.example.newform.dao.PlanoDAO;
import com.example.newform.enums.SharedEnum;
import com.example.newform.models.AlunoModel;
import com.example.newform.models.GraduacaoModel;
import com.example.newform.models.MatriculaModalidadeModel;
import com.example.newform.models.MatriculaModel;
import com.example.newform.models.ModalidadeModel;
import com.example.newform.models.PlanoModel;
import com.example.newform.utils.UtilSharedPreferences;
import java.util.List;


public final class ServiceSync {

    public static final String LOG_ID = "SYNC";

    public static void Sync(Activity context){
        final AlunoDAO alunoDAO = new AlunoDAO(context);
        final GraduacaoDAO graduacaoDAO = new GraduacaoDAO(context);
        final MatriculaModalidadeDAO matriculaModalidadeDAO = new MatriculaModalidadeDAO(context);
        final MatriculaDAO matriculaDAO = new MatriculaDAO(context);
        final ModalidadeDAO modalidadeDAO = new ModalidadeDAO(context);
        final PlanoDAO planoDAO = new PlanoDAO(context);

        final Long idUsuario = UtilSharedPreferences.getLong(context, SharedEnum.CODIGO_ALUNO.toString(), -1L);

        if (idUsuario.equals(-1L)) {
            return;
        }

        final List<AlunoModel> alunos = alunoDAO.select();
        final List<ModalidadeModel> modalidades = modalidadeDAO.select();
        final List<GraduacaoModel> graduacoes = graduacaoDAO.select();
        final List<PlanoModel> planos = planoDAO.select();
        final List<MatriculaModel> matriculas = matriculaDAO.select();
        final List<MatriculaModalidadeModel> matriculasModalidades = matriculaModalidadeDAO.select();

        Log.i("SYNC[ALUNOS]", String.valueOf(alunos.size()));
        Log.i("SYNC[MODALIDADES]", String.valueOf(modalidades.size()));
        Log.i("SYNC[GRADUACOES]", String.valueOf(graduacoes.size()));
        Log.i("SYNC[PLANOS]", String.valueOf(planos.size()));
        Log.i("SYNC[MATRICULAS]", String.valueOf(matriculas.size()));
        Log.i("SYNC[MAT_MODALIDADES]", String.valueOf(matriculasModalidades.size()));

            //Alunos
            for (final AlunoModel aluno : alunos) {

                if (aluno.getIdServer() != null){
                    continue;
                }

                final Long idApp = aluno.getId();
                aluno.setId(null);
                aluno.setIdUsuario(idUsuario);
                aluno.setIdServer(AlunoSync.sync(aluno));
                aluno.setId(idApp);
                alunoDAO.updateIdServer(aluno);
            }

            //Modalidades
            for (final ModalidadeModel modalidade: modalidades) {

                if (modalidade.getIdServer() != null){
                    continue;
                }

                modalidade.setIdUsuario(idUsuario);
                modalidade.setIdServer(ModalidadeSync.sync(modalidade));
                modalidadeDAO.updateIdServer(modalidade);
            }

            //Graduação
            for (GraduacaoModel graduacao: graduacoes){

                if (graduacao.getIdServer() != null){
                    continue;
                }

                for (final ModalidadeModel modalidade: modalidades) {
                    if (graduacao.getModalidade().equals(modalidade.getModalidade())) {
                        graduacao.setIdUsuario(idUsuario);
                        graduacao.setModalidade(modalidade.getIdServer().toString());
                        graduacao.setIdServer(GraduacaoSync.sync(graduacao));
                        graduacaoDAO.updateIdServer(graduacao);
                        break;
                    }
                }
            }

        //Planos
        for (final PlanoModel plano: planos) {

            if (plano.getIdServer() != null) {
                continue;
            }

            for (final ModalidadeModel modalidade: modalidades) {
                if (plano.getModalidade().equals(modalidade.getModalidade())) {
                    plano.setIdUsuario(idUsuario);
                    plano.setModalidade(modalidade.getIdServer().toString());
                    plano.setIdServer(PlanoSync.sync(plano));
                    planoDAO.updateIdServer(plano);
                    break;
                }
            }
        }

        for (MatriculaModel matricula: matriculas){

            if (matricula.getIdServer() != null){
                continue;
            }

            for (AlunoModel aluno: alunos) {
                if (matricula.getIdAluno().equals(aluno.getId())){
                    Long idApp = matricula.getId();
                    matricula.setId(null);
                    matricula.setIdUsuario(idUsuario);
                    matricula.setIdAluno(aluno.getIdServer());
                    matricula.setIdServer(MatriculaSync.sync(matricula));
                    matricula.setId(idApp);
                    matriculaDAO.updateIdServer(matricula);
                    break;
                }
            }
        }

        for (final MatriculaModalidadeModel matriculaModalidade: matriculasModalidades) {

            if (matriculaModalidade.getIdServer() != null) {
                continue;
            }

            for (MatriculaModel matricula: matriculas){
                if (matriculaModalidade.getIdMatricula().equals(matricula.getId())){
                    matriculaModalidade.setIdMatricula(matricula.getIdServer());
                    break;
                }
            }
            for (ModalidadeModel modalidade: modalidades){
                if (matriculaModalidade.getModalidade().equals(modalidade.getModalidade())){
                    matriculaModalidade.setModalidade(modalidade.getIdServer().toString());
                    break;
                }
            }
            for (GraduacaoModel graduacao: graduacoes){
                if (matriculaModalidade.getGraduacao().equals(graduacao.getGraduacao())){
                    matriculaModalidade.setGraduacao(graduacao.getIdServer().toString());
                    break;
                }
            }
            for (PlanoModel plano: planos){
                if (matriculaModalidade.getPlano().equals(plano.getPlano())){
                    matriculaModalidade.setPlano(plano.getIdServer().toString());
                    break;
                }
            }
            matriculaModalidade.setIdUsuario(idUsuario);
            matriculaModalidade.setIdServer(MatriculaModalidadeSync.sync(matriculaModalidade));
            matriculaModalidadeDAO.updateIdServer(matriculaModalidade);
        }

    }

}
