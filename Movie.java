import java.util.ArrayList;
/**
 * The Movie class
 * 
 * @author Sung-Che Lee
 * @version 2.1 26/05/2017
 */
public class Movie
{
    private String title;
    private String director;
    private ArrayList<Actor> actorList;
    private int rating;
    
    public static final int MAX_ACTOR_NUMBER = 3;

    /**
     * Default constructor
     */
    public Movie()
    {
        title = "";
        director = "";
        actorList = new ArrayList<Actor>();
        rating = 0;
    }
    
    /**
     * Other constructor
     * 
     * @param title movie title
     * @param director movie director
     * @param actorList movie actor list
     * @param rating movie rating
     */
    public Movie(String title, String director, ArrayList<Actor> actorList, int rating)
    {
        if (title.trim().length() != 0)
            this.title = title;
        else
            this.title = "NoTitle";

        if (director.trim().length() != 0)
            this.director = director;
        else
            this.director = "NoDirector";

        this.actorList = actorList;

        if (rating <= 10  && rating >=1)
            this.rating = rating;
        else
            this.rating = 0;
    }

    /**
     * Add actor
     * 
     * @param actor actor object
     * @return    boolean
     */
    public boolean addActor(Actor actor)
    {
        return actorList.add(actor);
    }

    /**
     * Display movie information
     */
    public void display()
    {
        System.out.print(getTitle());
        System.out.print(",");
        System.out.print(getDirector());
        System.out.print(",");
        
        for (int index = 0; index < MAX_ACTOR_NUMBER; index++ )
        {
            if (index < actorList.size())
                System.out.print(actorList.get(index).getName());
            System.out.print(",");
        }
        System.out.println(getRating());
    }
    
    /**
     * Get actor list
     * 
     * @return    actor list
     */
    public ArrayList<Actor> getActorList()
    {
        return actorList;
    }

    /**
     * Get director
     * 
     * @return    director
     */
    public String getDirector()
    {
        return director;
    }

    /**
     * Get rating
     * 
     * @return    rating
     */
    public int getRating()
    {
        return rating;
    }

    /**
     * Get title
     * 
     * @return    title
     */
    public String getTitle()
    {
        return title;
    }
    
    /**
     * Remove actor
     * 
     * @param actor actor object
     * @return    boolean
     */
    public boolean removeActor(Actor actor)
    {
        return actorList.remove(actor);
    }

    /**
     * Set actor list
     * 
     * @param actorList    actor list
     */
    public void setActorList(ArrayList<Actor> actorList)
    {
        this.actorList = actorList;
    }
    
    /**
     * Set director
     * 
     * @param director    director
     */
    public void setDirector(String director)
    {
        if (director.trim().length() != 0)
            this.director = director;
        else
            this.director = "NoDirector";
    }

    /**
     * Set rating
     * 
     * @param rating    rating
     */
    public void setRating(int rating)
    {
        if (rating <= 10  && rating >=1)
            this.rating = rating;
        else
            this.rating = 0;
    }

    /**
     * Set title
     * 
     * @param title    title
     */
    public void setTitle(String title)
    {
        if (title.trim().length() != 0)
            this.title = title;
        else
            this.title = "NoTitle";
    }

}