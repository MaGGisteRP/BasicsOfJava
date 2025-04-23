package model;

import java.util.ArrayList;
import java.util.List;

public class Cinema
{
    private String name;
    private List<Hall> halls;

    public Cinema(String name)
    {
        this.name = name;
        this.halls = new ArrayList<>();
    }

    public String getName()
    {
        return name;
    }

    public List<Hall> getHalls()
    {
        return halls;
    }

    public void addHall(Hall hall)
    {
        halls.add(hall);
    }
}