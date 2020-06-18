package com.example.newform.reyclerview;

import android.app.Activity;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public abstract class AdapterDefault<Classe, Holder extends ViewHolderDefault<Classe>> extends RecyclerView.Adapter<Holder> {

    private List<Classe> list;
    private Activity activity;
    private OnClick<Classe> onClick;
    private Integer size;

    private boolean utilizaSelecionado = false;
    private boolean utilizaNotifyClick = false;
    private int positionClicked = 0;

    public AdapterDefault(Activity activity, List<Classe> list, OnClick<Classe> onClick) {
        this(activity, list, onClick, false);
    }

    public AdapterDefault(Activity activity, List<Classe> list, OnClick<Classe> onClick, boolean utilizaNotifyClick)  {
        this(activity, list, null, onClick, utilizaNotifyClick);
    }

    public AdapterDefault(Activity activity, List<Classe> list, Integer size, OnClick<Classe> onClick, boolean utilizaNotifyClick)  {
        this.setList(list);
        this.setActivity(activity);
        this.setOnClick(onClick);
        this.setUtilizaNotifyClick(utilizaNotifyClick);
        this.size = size;
    }

    @Override
    public int getItemCount() {
        return size == null ? getList().size() : size;
    }

    @Override
    public long getItemId(int position) {
        return getList().get(position).hashCode();
    }

    @Override
    public void onBindViewHolder(Holder holder, final int position) {
        Classe objectFirst = null;

        try {
            objectFirst = getList().get(position);
        }
        catch (IndexOutOfBoundsException e) {}

        final Classe object = objectFirst;

        View.OnClickListener click = null;

        if (getOnClick() != null) {
            if (position != getPositionClicked() || !isUtilizaSelecionado()) {
                click = new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (isUtilizaSelecionado()) {
                            int oldPosition = getPositionClicked();
                            setPositionClicked(position);
                            notifyItemChanged(oldPosition);
                            notifyItemChanged(position);
                        }

                        getOnClick().onClick(v, object, position);

                        if (isUtilizaNotifyClick()) {
                            notifyDataSetChanged();
                        }
                    }
                };
            }
        }

        holder.bind(object, getList(), (AdapterDefault<Classe, ViewHolderDefault<Classe>>) getAdapter(), getActivity(), click);
    }

    private AdapterDefault<Classe, Holder> getAdapter() {
        return this;
    }

    public List<Classe> getList() {
        return list;
    }

    public void setList(List<Classe> list) {
        this.list = list;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public OnClick<Classe> getOnClick() {
        return onClick;
    }

    public void setOnClick(OnClick<Classe> onClick) {
        this.onClick = onClick;
    }

    public int getPositionClicked() {
        return positionClicked;
    }

    public void setPositionClicked(int position) {
        this.positionClicked = position;
    }

    protected boolean isUtilizaSelecionado() {
        return utilizaSelecionado;
    }

    protected void setUtilizaSelecionado(boolean utilizaSelecionado) {
        this.utilizaSelecionado = utilizaSelecionado;
    }

    public boolean isUtilizaNotifyClick() {
        return utilizaNotifyClick;
    }

    public void setUtilizaNotifyClick(boolean utilizaNotifyClick) {
        this.utilizaNotifyClick = utilizaNotifyClick;
    }

    public interface OnClick<Classe> {
        void onClick(View v, Classe object, int position);
    }
}
