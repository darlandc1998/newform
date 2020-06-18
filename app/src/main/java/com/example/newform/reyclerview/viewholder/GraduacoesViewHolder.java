package com.example.newform.reyclerview.viewholder;

import android.app.Activity;
import android.content.DialogInterface;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.newform.R;
import com.example.newform.dao.GraduacaoDAO;
import com.example.newform.dialogs.DialogDefault;
import com.example.newform.models.GraduacaoModel;
import com.example.newform.reyclerview.AdapterDefault;
import com.example.newform.reyclerview.ViewHolderDefault;

import java.util.List;

public class GraduacoesViewHolder extends ViewHolderDefault<GraduacaoModel> {

    private TextView txtGraduacao;
    private ImageView imgRemoverGraduacao;

    private List<GraduacaoModel> listExclude;
    
    public GraduacoesViewHolder(View view, List<GraduacaoModel> listExclude) {
        super(view);
        
        txtGraduacao = view.findViewById(R.id.txtGraduacao);
        imgRemoverGraduacao = view.findViewById(R.id.imgRemoverGraduacao);

        this.listExclude = listExclude;
    }

    @Override
    public void bind(final GraduacaoModel object, final List<GraduacaoModel> list, final AdapterDefault<GraduacaoModel, ViewHolderDefault<GraduacaoModel>> adapter, final Activity activity, View.OnClickListener onClick) {
        super.bind(object, list, adapter, activity, onClick);
        
        txtGraduacao.setText(object.getGraduacao());

        imgRemoverGraduacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogDefault.dialogYesNo(activity, activity.getString(R.string.remover_graduacao_mensagem_dialog) + " " +object.getGraduacao() + " " + activity.getString(R.string.interrogacao) , new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int op) {
                        switch (op) {
                            case DialogInterface.BUTTON_POSITIVE:
                                listExclude.add(object);
                                list.remove(object);
                                adapter.notifyDataSetChanged();
                                break;
                            default:
                                dialog.dismiss();
                                break;
                        }
                    }
                });
            }
        });
    }
}
