package com.edu.ProyectoMorty.entity;

import java.util.List;

public class Episode {

    private int id;
    private String name;
    private String air_date;
    private String episode;
    private List<String> characters;
    private String url;
    private String created;

    public Episode() {
    }

    public Episode(int id) {
        this.id = id;
    }

    public Episode(int id, String air_date, String episode) {
        this.id = id;
        this.air_date = air_date;
        this.episode = episode;
    }

    public Episode(int id, String name, String air_date, String episode, List<String> characters, String url, String created) {
        this.id = id;
        this.name = name;
        this.air_date = air_date;
        this.episode = episode;
        this.characters = characters;
        this.url = url;
        this.created = created;
    }

    public Episode(int id, String name, String air_date, String episode, String url, String created) {
        this.id = id;
        this.name = name;
        this.air_date = air_date;
        this.episode = episode;

        this.url = url;
        this.created = created;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAir_date() {
        return air_date;
    }

    public void setAir_date(String air_date) {
        this.air_date = air_date;
    }

    public String getEpisode() {
        return episode;
    }

    public void setEpisode(String episode) {
        this.episode = episode;
    }

    public List<String> getCharacters() {
        return characters;
    }

    public void setCharacters(List<String> characters) {
        this.characters = characters;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "Episode{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", air_date='" + air_date + '\'' +
                ", episode='" + episode + '\'' +
                ", characters=" + characters +
                ", url='" + url + '\'' +
                ", created='" + created + '\'' +
                '}';
    }
}
