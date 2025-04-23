package model;

import java.util.ArrayList;
import java.util.List;

public class Hall
{
    private String name;
    private int rows;
    private int seatsPerRow;
    private List<Session> sessions;

    public Hall(String name, int rows, int seatsPerRow)
    {
        this.name = name;
        this.rows = rows;
        this.seatsPerRow = seatsPerRow;
        this.sessions = new ArrayList<>();
    }

    public String getName()
    {
        return name;
    }

    public int getRows()
    {
        return rows;
    }

    public int getSeatsPerRow()
    {
        return seatsPerRow;
    }

    public List<Session> getSessions()
    {
        return sessions;
    }

    public void addSession(Session session)
    {
        session.setHall(this);
        sessions.add(session);
    }
}