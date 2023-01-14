package com.piotrzawada.car_sharing;

public final class Customer {
    private int id;
    private String name;
    private Integer rented_Car_Id;

    public Customer(int id, String name, Integer rented_Car_Id) {
        this.id = id;
        this.name = name;
        this.rented_Car_Id = rented_Car_Id;
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

    public Integer getRented_Car_Id() {
        return rented_Car_Id;
    }

    public void setRented_Car_Id(Integer rented_Car_Id) {
        this.rented_Car_Id = rented_Car_Id;
    }
}
