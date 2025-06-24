package com.example.Inventory_management.entity;



public class Role {
    private int id;
    private String name; // e.g. ADMIN or USER



    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }


}

