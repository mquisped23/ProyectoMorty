package com.edu.ProyectoMorty.prueba;

import com.edu.ProyectoMorty.entity.Character;
import com.edu.ProyectoMorty.entity.Episode;
import com.edu.ProyectoMorty.paginacion.Paginacion;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class pruebita {
     String endpoint = "https://rickandmortyapi.com/api/episode?page=1";
     String  previousUri ="", nextUri ="";
    CloseableHttpResponse response;
    String cuerpoDelJson;

    CloseableHttpClient client = HttpClientBuilder.create().build();

    public static void main(String[] args) throws IOException {

        pruebita p = new pruebita();
       // System.out.println(p.getEpisode("https://rickandmortyapi.com/api/episode/1"));
       for (Episode e : p.listEpisodes(Paginacion.NEXT)){
            System.out.println(e.getId());
            System.out.println(e.getName());
            System.out.println(e.getAir_date());
            System.out.println(e.getEpisode());
           System.out.println(e.getCharacters().stream().toList());
          System.out.println(e.getUrl());
          System.out.println(e.getCreated()+"\n");
        }

    }


    public ArrayList<Episode> listEpisodes( Paginacion paginacion) throws IOException {
        ArrayList<Episode> episodes = new ArrayList<>();

        switch (paginacion){
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

        if (pagina.get("prev").equals(null)){
            previousUri=  endpoint;
        }else{
            previousUri=  pagina.get("prev").toString();
        }
        if ( pagina.get("next").equals(null)){
            nextUri=endpoint;
        }
        nextUri = pagina.get("next").toString();
        System.out.println("el uri siguiente es:" + nextUri);
        JSONArray episodios = episodio.getJSONArray("results");
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



    //Este es el metodo para traer un solo episodio
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

           //System.out.println("El perso es:" + o.toString() + "\n");
           remplazo= o.toString().replaceAll("https://rickandmortyapi.com/api/character/", "");

          // System.out.println("al remplazarlo es : " + remplazo);
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

    public Character getCharacter(String url) throws IOException {

        response = client.execute(new HttpGet(url));
        cuerpoDelJson = EntityUtils.toString(response.getEntity());
        JSONObject personaje = new JSONObject(cuerpoDelJson);

         int id = personaje.getInt("id");
         String name = personaje.getString("name");
         String status  = personaje.getString("status");
         String specie = personaje.getString("species");
         String type = personaje.getString("type");
         String gender = personaje.getString("gender");
         String image = personaje.getString("image");
        Character character = new Character(id, name, status, specie, type, gender,image);
         return character;
    }



}