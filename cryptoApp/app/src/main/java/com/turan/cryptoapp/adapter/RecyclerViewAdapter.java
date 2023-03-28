package com.turan.cryptoapp.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.turan.cryptoapp.R;
import com.turan.cryptoapp.model.Money;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RowHolder> {
    private ArrayList<Money> modelslist;
    private String[] colors={"#f4f811","#32a5cb","#f7c707","#e8c19a","#ed3548","#00d2ff","#913945","#abcdef"};

    public RecyclerViewAdapter(ArrayList<Money> modelslist) {
        this.modelslist = modelslist;
    }

    @NonNull
    @Override
    public RowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {                  //oluşturduğun row tasarımı ile recycler viewi bağlıyorsun
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());       //inflater bu çeşit xmlleri activiteye bağlamaya yarıyor. Zaten contex veriyosun
        View view = layoutInflater.inflate(R.layout.row,parent,false);      // row holder sınıfının beklediği view oluşturuldu
        return new RowHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RowHolder holder, int position) {                         //görünümleri bağlıyor(alttaki row holderden gelen satırları)
            holder.bind(modelslist.get(position),colors,position);
    }

    @Override
    public int getItemCount() {                                                                     //kaç tane row oluşturulacak
        return modelslist.size();
    }

    public class RowHolder extends RecyclerView.ViewHolder {
        TextView name_text;
        TextView price_text;
        public RowHolder(@NonNull View itemView) {
            super(itemView);
        }
        public  void bind(Money money,String[] colors,int position){
            itemView.setBackgroundColor(Color.parseColor(colors[position % 8]));
            name_text = itemView.findViewById(R.id.name_text);
            price_text = itemView.findViewById(R.id.price_text);
            name_text.setText(money.name);
            price_text.setText(money.price);
        }
    }
}
