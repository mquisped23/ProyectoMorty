package com.edu.ProyectoMorty.apiConsumer;

import com.edu.ProyectoMorty.entity.Character;
import com.edu.ProyectoMorty.entity.Episode;
import com.edu.ProyectoMorty.paginacion.Paginacion;
import com.edu.ProyectoMorty.prueba.pruebita;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ConsumirApi {

     String endpoint = "https://rickandmortyapi.com/api/episode/";
     String  previousUri ="", nextUri ="";
    CloseableHttpResponse response;
    String cuerpoDelJson;

    CloseableHttpClient client = HttpClientBuilder.create().build();


    public ArrayList<Episode> listEpisodes( Paginacion paginacion) throws IOException {
        ArrayList<Episode> episodes = new ArrayList<>();
        //esta condicional sirve para validar si el url del prev viene null este muestre el url predeterminado como fuente principal
        if (previousUri.equals("null")){
            previousUri = "https://rickandmortyapi.com/api/episode/";
        }
        //Aqui se usa el enum para la paginacion
        switch (paginacion){
            //si el usuario presiona el boton prev este remplazara la uri por la uri prev
            case PREVIOUS -> {
                endpoint = previousUri;
            }
            case NEXT -> {
                    endpoint = nextUri;
             }
        }


        response = client.execute(new HttpGet(endpoint));
        cuerpoDelJson = EntityUtils.toString(response.getEntity());
        JSONObject episodio = new JSONObject(cuerpoDelJson);

        JSONObject pagina = episodio.getJSONObject("info");
        nextUri = pagina.get("next").toString();
        previousUri = pagina.get("prev").toString();

        //ya que es un next este se ejecuta despues por lo cual se debe colocar despues que el prev
        if (nextUri.equals("null")){
            nextUri = "https://rickandmortyapi.com/api/episode/?page=3";
        }
        JSONArray episodios = episodio.getJSONArray("results");
        //se usa un foreach para recorrer el jsonarray
        episodios.forEach(o -> {
            try {
                episodes.add(getEpisode(((JSONObject) o).getString("url")));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        response.close();
        return episodes;
    }

    public Episode getEpisode(String url) throws IOException {
        Episode episode  = new Episode();
        List<String> personaje = new ArrayList<>();
        response = client.execute(new HttpGet(url));
        cuerpoDelJson = EntityUtils.toString(response.getEntity());
        JSONObject episodio = new JSONObject(cuerpoDelJson);

        int id = episodio.getInt("id");
        String name = episodio.getString("name");
        String air_date  = episodio.getString("air_date");
        String epi = episodio.getString("episode");
        JSONArray perso = episodio.getJSONArray("characters");

        String remplazo = null;
        for(Object o : perso){
            remplazo= o.toString().replaceAll("https://rickandmortyapi.com/api/character/", "");
            personaje.add(remplazo);
        }

        String url1 = episodio.getString("url");
        String created = episodio.getString("created");
        episode.setId(id);
        episode.setName(name);
        episode.setAir_date(air_date);
        episode.setEpisode(epi);
        episode.setCharacters(personaje);
        episode.setUrl(url1);
        episode.setCreated(created);
        return episode;
    }



    //Consumir api del pérsonajes
    public Character getCharacter(String url) throws IOException {
        response = client.execute(new HttpGet("https://rickandmortyapi.com/api/character/"+url));
        cuerpoDelJson = EntityUtils.toString(response.getEntity());
        JSONObject personaje = new JSONObject(cuerpoDelJson);

        int id = personaje.getInt("id");
        String name = personaje.getString("name");
        String status  = personaje.getString("status");
        String specie = personaje.getString("species");
        String type = personaje.getString("type");
        if(type.isEmpty()){
            type = "Desconocido";
        }
        String gender = personaje.getString("gender");
        String image = personaje.getString("image");
        Character personajes = new Character(id, name, status, specie, type, gender,image);
        return personajes;
    }



}
