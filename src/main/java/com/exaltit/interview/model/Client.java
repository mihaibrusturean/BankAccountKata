package com.exaltit.interview.model;

import java.util.UUID;

public class Client {
    private String name;
    private String id;

    public Client(String name) {
        this.name = name;
        UUID uuid = UUID.randomUUID();
        this.id = uuid.toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    //id made immutable because we need it to be constant
    /*public void setId(String id) {
        this.id = id;
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        if (!getName().equals(client.getName())) return false;
        return getId().equals(client.getId());
    }

    @Override
    public int hashCode() {
        //only id used in hashcode because it is immutable, whereas the name could change (e.g. in case of marriage)
        return getId().hashCode();
    }

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
