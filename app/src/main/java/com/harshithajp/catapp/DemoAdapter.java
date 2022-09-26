package com.harshithajp.catapp;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/// ---- custom adpater class created
public class DemoAdapter extends RecyclerView.Adapter<DemoVH>{

    List<String> items;

    public DemoAdapter(List<String> items)
    {
        this.items = items;
    }


    @NonNull
    @Override
    public DemoVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rows, parent, false);
        return new DemoVH(view).linkAdapter(this);
    }

    @Override
    public void onBindViewHolder(@NonNull DemoVH holder, int position) {
        holder.exampleText.setText(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}

class DemoVH extends RecyclerView.ViewHolder{

    TextView exampleText;
    private DemoAdapter adapter;

    public DemoVH(@NonNull View itemView) {
        super(itemView);

        exampleText = itemView.findViewById(R.id.rowtext);
        //-- code for delete here not implemented
    }


    public DemoVH linkAdapter(DemoAdapter adapter)
    {
        this.adapter = adapter;
        return this;
    }

}
