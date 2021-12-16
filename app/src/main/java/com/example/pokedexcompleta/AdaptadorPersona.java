package com.example.pokedexcompleta;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdaptadorPersona extends BaseAdapter
{
    ArrayList<Pokemon> pokemons;
    Context context;

    public AdaptadorPersona(ArrayList<Pokemon> pokemons, Context context)
    {
        this.pokemons = pokemons;
        this.context = context;
    }

    @Override
    public int getCount()
    {
        return pokemons.size();
    }

    @Override
    public Pokemon getItem(int i)
    {
        return pokemons.get(i);
    }

    @Override
    public long getItemId(int i)
    {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        //genera un view nuevo a partir del layout del poken generado.
        View viewInflado = LayoutInflater.from(context).inflate(R.layout.poke,null);
        TextView txtNombre = viewInflado.findViewById(R.id.nomPoke);
        ImageView imaPoke =  viewInflado.findViewById(R.id.imaPoke);
        txtNombre.setText(pokemons.get(i).getNombre());
        Picasso.get().load(MainActivity.urlsImg.get(i)).into(imaPoke);
        return viewInflado;
    }
}
