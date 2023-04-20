package com.example.cfms;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import de.hdodenhof.circleimageview.CircleImageView;

public class CupcakeAdapter extends FirebaseRecyclerAdapter<CupcakeModel,CupcakeAdapter.myViewHolder>{

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public CupcakeAdapter(@NonNull FirebaseRecyclerOptions<CupcakeModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull CupcakeModel model) {
        holder.name.setText(model.getName());
        holder.price.setText(model.getPrice());

        Glide.with(holder.img.getContext())
                .load(model.getPhoto())
                .placeholder(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
                .circleCrop()
                .error(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
                .into(holder.img);

//        bind update view popup with edit button
        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.img.getContext())
                        .setContentHolder(new ViewHolder(R.layout.update_cupcake_popup))
                        .setExpanded(true,1200)
                        .create();

                //dialogPlus.show();
                View view = dialogPlus.getHeaderView();
                EditText name = view.findViewById(R.id.txtName);
                EditText price = view.findViewById(R.id.txtPrice);
                EditText description = view.findViewById(R.id.txtDescription);
                EditText photoURL = view.findViewById(R.id.txtPhotoURL);

                Button btnUpdate = view.findViewById(R.id.btnUpdate);

                name.setText(model.getName());
                price.setText(model.getPrice());
                description.setText(model.getDescription());
                photoURL.setText(model.getPhoto());

                dialogPlus.show();
            }
        });
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cupcake_item,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder {

        CircleImageView img;
        TextView name,price;

        Button btnEdit,btnDelete;

        public myViewHolder(@NonNull View itemView){
            super(itemView);

            img = (CircleImageView) itemView.findViewById(R.id.img1);
            name = (TextView) itemView.findViewById(R.id.nametext);
            price = (TextView) itemView.findViewById(R.id.pricetext);

            btnEdit = (Button)itemView.findViewById(R.id.btnEdit);
            btnDelete = (Button)itemView.findViewById(R.id.btnDelete);
        }
  }
}