package com.example.groceryapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.UiThread;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class GroceryAdapter extends RecyclerView.Adapter<GroceryAdapter.GroceryViewHolder> {

    ArrayList<Grocery> grocery;
    GroceryList context;


    public GroceryAdapter(ArrayList<Grocery> grocery){
        this.grocery = grocery;
    }
    public GroceryAdapter(ArrayList<Grocery> grocery,GroceryList context){
        this.grocery = grocery;
        this.context = context;
    }

    @NonNull
    @Override
    public GroceryAdapter.GroceryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View item = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.list_item, viewGroup, false);

        return new GroceryViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull final GroceryAdapter.GroceryViewHolder groceryViewHolder, final int i) {

          //  groceryViewHolder.gr_id.setText(grocery.get(i).getId());

            groceryViewHolder.gr_nm.setText(grocery.get(i).getName());
            groceryViewHolder.gr_quantity.setText(grocery.get(i).getQuantity());

//            groceryViewHolder.gr_quantity.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                   // new DatabaseHandler(context).deleteGrocery(grocery.get(i));
//
//                    Intent intent  = new Intent(context,UpdateGrocery.class);
//                    intent.putExtra("Name","1");
//                    intent.putExtra("Quantity", "5 Kg");
//                    context.startActivity(intent);
//                   // context.getList();
//                }
//            });

            groceryViewHolder.delete_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    delete(i);
                }
            });

            groceryViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent  = new Intent(context,UpdateGrocery.class);
                    intent.putExtra("id", grocery.get(i).getId());
                    intent.putExtra("name", grocery.get(i).getName());
                    intent.putExtra("quantity", grocery.get(i).getQuantity());
                    context.startActivity(intent);
                }
            });
    }

    @Override
    public int getItemCount() {
        return grocery.size();
    }

    public void delete(final int i){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
        builder1.setMessage("Are you sure to delete?");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        new DatabaseHandler(context).deleteGrocery(grocery.get(i));
                        context.getList();
                        Toast.makeText(context, "Record Deleted", Toast.LENGTH_LONG).show();
                        dialog.cancel();
                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();

    }

    public static class GroceryViewHolder extends RecyclerView.ViewHolder
    {
        TextView  gr_nm, gr_quantity;
        ImageButton delete_btn;
        CardView cardView;

        public GroceryViewHolder(@NonNull View itemView){
            super(itemView);
           // gr_id = itemView.findViewById(R.id.grocery_id);
            gr_nm = itemView.findViewById(R.id.grocery_name);
            gr_quantity = itemView.findViewById(R.id.grocery_quantity);
            delete_btn = itemView.findViewById(R.id.delete_btn);
            cardView = itemView.findViewById(R.id.my_card);

        }
    }

}
