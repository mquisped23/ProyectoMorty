package com.edu.ProyectoMorty.entity;

public class Character {

    private int id;
    private String name;
    private String status;
    private String specie;
    private String type;
    private String gender;
    private String image;

    public Character() {
    }

    public Character(int id, String name, String status, String specie, String type, String gender, String image) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.specie = specie;
        this.type = type;
        this.gender = gender;
        this.image = image;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSpecie() {
        return specie;
    }

    public void setSpecie(String specie) {
        this.specie = specie;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Character{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", specie='" + specie + '\'' +
                ", type='" + type + '\'' +
                ", gender='" + gender + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
