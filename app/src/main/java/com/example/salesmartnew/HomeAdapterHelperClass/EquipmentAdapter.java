package com.example.salesmartnew.HomeAdapterHelperClass;




import android.animation.PointFEvaluator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.salesmartnew.R;

import java.util.ArrayList;

public class EquipmentAdapter extends RecyclerView.Adapter<EquipmentAdapter.EquipmentViewHolder> {

    ArrayList<EquipmentHelperClass> equipmentHelperClasses ;

    public EquipmentAdapter(ArrayList<EquipmentHelperClass> equipmentHelperClasses) {
        this.equipmentHelperClasses = equipmentHelperClasses;
    }

    @NonNull
    @Override
    public EquipmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.equipment_design, parent, false);
        EquipmentViewHolder equipmentViewHolder =  new EquipmentViewHolder(view);
        return equipmentViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull EquipmentViewHolder holder, int position) {
        EquipmentHelperClass equipmentHelperClass = equipmentHelperClasses.get(position);
        holder.image.setImageResource(equipmentHelperClass.getImage());
        holder.title.setText(equipmentHelperClass.getTitleCard());
        holder.desc.setText(equipmentHelperClass.getDescriptionCard());

    }

    @Override
    public int getItemCount() {
        return equipmentHelperClasses.size();
    }

    public static class EquipmentViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView title, desc;

        public EquipmentViewHolder(@NonNull View itemView) {
            super(itemView);

            //identify the layout
            image = itemView.findViewById(R.id.image_card);
            title = itemView.findViewById(R.id.text1_card);
            desc = itemView.findViewById(R.id.text2_card);

        }
    }


}
