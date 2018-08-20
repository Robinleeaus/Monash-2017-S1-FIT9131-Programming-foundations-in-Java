
/**
 * The Actor class
 * 
 * @author Sung-Che Lee 
 * @version 2.1 26/05/2017
 */
public class Actor
{
    private String name;

    /**
     * Default constructor
     */
    public Actor()
    {
        name = "";
    }

    /**
     * Other constructor
     * 
     * @param name actor name
     */
    public Actor(String name)
    {
        if (name.trim().length() != 0)
            this.name = name;
        else
            this.name = "";
    }


    /**
     * Get actor name
     * 
     * @return    actor's name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Set actor name
     * 
     * @param    actor's name
     */
    public void setName(String name)
    {
        if (name.trim().length() != 0)
            this.name = name;
        else
            this.name = "";
    }
    
   
}
