package com.example.pokedexcompleta;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{

    ArrayList<Pokemon>pokemons = new ArrayList<>();
    ListView    listView ;
    ArrayList<String>nombres = new ArrayList<>();
   static ArrayList<String>urlsImg = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listadoPoke);
        //------Pre ejecucion------------------//
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                //Jsoup --> Json
                try
                {
                    Document resCompleto = Jsoup.connect("https://www.pokemon.com/es/pokedex/").get();
                    //scraping de una pagina web
                    nombres = (ArrayList<String>) resCompleto.select("[href^=/es/pokedex/]").eachText();
                    nombres.remove(0);
                    for (int i = 0 ; i < nombres.size(); i++)
                    {
                        String numPokemon = String.format("%03d", i + 1);
                        urlsImg.add("https://assets.pokemon.com/assets/cms2/img/pokedex/full/"+ numPokemon +".png");//conforma la lista de url
                        pokemons.add(new Pokemon(nombres.get(i)));//Conformar la lista e nombres
                    }
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
                //-------Post Ejecucion-------------//
                runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        AdaptadorPersona adaptador = new AdaptadorPersona(pokemons,MainActivity.this);
                        listView.setAdapter(adaptador);

                    }
                });
            }
        }).start();

    }

}