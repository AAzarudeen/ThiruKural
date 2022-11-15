package com.example.thirukural.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thirukural.FavouriteDataBase;
import com.example.thirukural.R;
import com.example.thirukural.activity.MainScreen;

import java.util.ArrayList;
import java.util.Arrays;

public class SavedAdapters extends RecyclerView.Adapter<SavedAdapters.SavedViewHolder> {

    Context context;

    public SavedAdapters(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public SavedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.saved_number,parent,false);
        return new SavedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SavedViewHolder holder, int position) {
        FavouriteDataBase favouriteDataBase = new FavouriteDataBase(context);
        ArrayList<String> savedList = favouriteDataBase.getFavouriteList();
        String kural_number = savedList.get(position);
        holder.number.setText(kural_number);
        System.out.println(Arrays.toString(favouriteDataBase.getFavouriteList().toArray()));
        holder.number.setOnClickListener(v->{
            MainScreen.from_saved = true;
            MainScreen.kural_number = kural_number;
            Navigation.findNavController(v).navigate(R.id.action_savedFragment_to_random);
        });
    }

    @Override
    public int getItemCount() {
        FavouriteDataBase favouriteDataBase = new FavouriteDataBase(context);
        return favouriteDataBase.getLength();
    }

    public class SavedViewHolder extends RecyclerView.ViewHolder{
        TextView number;
        public SavedViewHolder(@NonNull View itemView) {
            super(itemView);
            number = itemView.findViewById(R.id.saved_number);
        }
    }
}
