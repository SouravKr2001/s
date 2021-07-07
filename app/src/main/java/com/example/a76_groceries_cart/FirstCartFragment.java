package com.example.a76_groceries_cart;

import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FirstCartFragment extends Fragment implements CartAdapter.DeleteItem,CartAdapter.TotalPrice {


    @Override
    public void getTotalPrice(double price) {
        txtSum.setText(String.valueOf(price) + "$");
    }


    @Override
    public void onDeleteResult(GroceryItem item) {
        Utils.deleteItemFromCart(getActivity(),item);
        ArrayList<GroceryItem> cartItem =Utils.getCartItems(getActivity());
        if (null != cartItem){
            if (cartItem.size()>0){
                txtNoItems.setVisibility(View.GONE);
                itemsRelLayout.setVisibility(View.VISIBLE);
            adapter.setItems(cartItem);
            }else{
                txtNoItems.setVisibility(View.VISIBLE);
                itemsRelLayout.setVisibility(View.GONE);
            }
        }else{
            txtNoItems.setVisibility(View.VISIBLE);
            itemsRelLayout.setVisibility(View.GONE);
        }





    }

    private RecyclerView recyclerView;
    private TextView txtSum,txtNoItems;
    private Button btnNext;
    private RelativeLayout itemsRelLayout;

    private CartAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_cart_first,container,false);
        initviews(view);
        adapter= new CartAdapter(getActivity(),this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        ArrayList<GroceryItem> cartItem =Utils.getCartItems(getActivity());
        if (null != cartItem){
            if (cartItem.size()>0){
                txtNoItems.setVisibility(View.GONE);
                itemsRelLayout.setVisibility(View.VISIBLE);
                adapter.setItems(cartItem);
            }else{
                txtNoItems.setVisibility(View.VISIBLE);
                itemsRelLayout.setVisibility(View.GONE);
            }
        }else{
            txtNoItems.setVisibility(View.VISIBLE);
            itemsRelLayout.setVisibility(View.GONE);
        }
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction =getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.container,new SecondCartFragment());
                transaction.commit();
            }
        });

        return view;
    }

    private void initviews(View view){
        recyclerView=view.findViewById(R.id.recyclerView);
        txtSum=view.findViewById(R.id.txtTotalprice);
        txtNoItems=view.findViewById(R.id.txtNoItem);
        btnNext=view.findViewById(R.id.btnNext);
        itemsRelLayout=view.findViewById(R.id.itemsRelLayout);
    }

}
