package model;

public class Movie
{
    private String title;
    private int duration;

    public Movie(String title, int duration)
    {
        this.title = title;
        this.duration = duration;
    }

    public String getTitle()
    {
        return title;
    }

    public int getDuration()
    {
        return duration;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Movie movie = (Movie) obj;
        return title.equals(movie.title);
    }
}