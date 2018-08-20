import java.util.*;
import java.io.*;
/**
 * The MovieList class
 * 
 * @author Sung-Che Lee
 * @version 2.1 26/05/2017
 */
public class MovieList
{
    private ArrayList<Movie> movieList;

    /**
     * Default constructor
     */
    public MovieList()
    {
        movieList = new ArrayList<Movie>();
    }
    
    /**
     * Other constructor
     * 
     * @param movieList movie list
     */
    public MovieList(ArrayList<Movie> movieList)
    {
        this.movieList = movieList;
    }
    
    /**
     * Add movie
     * 
     * @param movie movie
     * @return    boolean
     */
    public boolean addMovie(Movie movie)
    {
        return movieList.add(movie);
    }

    /**
     * Delete movie with exact title
     * 
     * @param searchString search string
     * @return    deleted
     */
    public boolean deleteMovieExactTitle(String searchString)
    {
        int index = 0;
        boolean deleted = false;
        
        while (index < movieList.size())
        {
            if (searchString.equals(""))
                return deleted;
            
            Movie movie = new Movie();
            movie = movieList.get(index);
            if (movie.getTitle().equalsIgnoreCase(searchString))
            {
                Scanner console = new Scanner(System.in);
                String choice = "";
                
                System.out.println("The following movie has been found.");
                displayOneMovie(index);
                System.out.println("");
                System.out.println("Would you like to delete it? (Enter y to delete it) ");
                choice = console.nextLine().trim();
                if (choice.equals("y"))
                {
                    System.out.println(movieList.get(index).getTitle() + " has been deleted.");
                    movieList.remove(movie);
                    deleted = true;
                }
            }
            index++;
        }        
        
        return deleted;
    }
    
    /**
     * Delete movie with partial title
     * 
     * @param searchString search string
     * @return    deleted
     */
    public boolean deleteMoviePartialTitle(String searchString)
    {
        int index = 0;
        boolean deleted = false;
        boolean found = false;
        
        System.out.println("");
        System.out.println("The matching movies are: ");
        found = searchMoviePartialTitle(searchString);
        System.out.println("");
        if (!found)
            System.out.println("None");
        else
            System.out.println("You need to select which movie you want to delete.");
            
        while (index < movieList.size())
        {
            if (searchString.equals(""))
                return deleted;
            
            Movie movie = new Movie();
            movie = movieList.get(index);
 
            if (movie.getTitle().toLowerCase().contains(searchString.toLowerCase()))
            {
                Scanner console = new Scanner(System.in);
                String choice = "";
                
                System.out.println("");
                displayOneMovie(index);
                System.out.println("Would you like to delete it? (Enter y to delete it) ");
                choice = console.nextLine().trim();
                if (choice.equals("y"))
                {
                    System.out.println("");
                    System.out.println(movieList.get(index).getTitle() + " has been deleted.");
                    movieList.remove(movie);
                    index--;
                    deleted = true;
                }
            }
            index++;
        }        
        
        return deleted;
    }
    
    /**
     * Display all movies
     */
    public void displayAllMovies()
    {
        for (Movie movie : movieList)
        {
            movie.display();
        }
    }
    
    /**
     * Display favourite movies
     * 
     * @param favouriteRating favourite rating
     * @return    found
     */
    public boolean displayFavouriteMovies(int favouriteRating)
    {
        int index = 0;
        boolean found = false;
        
        while (index < movieList.size())
        {
            Movie movie = new Movie();
            movie = movieList.get(index);
            if (movie.getRating() >= favouriteRating)
            {
                found = true;
                displayOneMovie(index);
            }
            index++;
        }        
        
        return found;
    }
    
    /**
     * Display one movies
     * 
     * @param index index of movielist
     */
    public void displayOneMovie(int index)
    {
        System.out.print(movieList.get(index).getTitle());
        System.out.print(",");
        System.out.print(movieList.get(index).getDirector());
        System.out.print(",");
        
        Movie movie = new Movie();
        for (int indexActor = 0; indexActor < Movie.MAX_ACTOR_NUMBER; indexActor++ )
        {
            if (indexActor < movieList.get(index).getActorList().size())
                System.out.print(movieList.get(index).getActorList().get(indexActor).getName());
            System.out.print(",");
        }
        System.out.println(movieList.get(index).getRating());
    }

    /**
     * Edit movie with exact title
     * 
     * @param searchString search string
     * @return    edited
     */
    public boolean editMovieExactTitle(String searchString)
    {
        int index = 0;
        boolean edited = false;
        
        while (index < movieList.size())
        {
            if (searchString.equals(""))
                return edited;
            
            Movie movie = new Movie();
            movie = movieList.get(index);
            if (movie.getTitle().equalsIgnoreCase(searchString))
            {
                Scanner console = new Scanner(System.in);
                String choice = "";
                
                System.out.println("The following movie has been found.");
                displayOneMovie(index);
                System.out.println("");
                System.out.println("Would you like to edit it? (Enter y to edit it) ");
                choice = console.nextLine().trim();
                if (choice.equals("y"))
                {
                    //edit actors
                    String choiceEditActors = "";
                    System.out.println("Would you like to edit actors? (Enter y to edit it) ");
                    choiceEditActors = console.nextLine().trim();
                    if (choiceEditActors.equals("y"))
                    {
                        //Enter movie actors
                        String actorName = "";
                
                        System.out.println("");
                        System.out.println("You need to enter at least 1 and up to 3 actor names.");
                        System.out.println("Blank string in actor 2 or 3 means that there is no actor after that field.");
                        for (int indexActor = 1; indexActor <= Movie.MAX_ACTOR_NUMBER; indexActor++)
                        {
                            String choiceChangeActor = "";
                            
                            System.out.println("");
                            System.out.println("Original movie actor " + indexActor + " is: " + movie.getActorList().get(indexActor-1).getName());
                            System.out.println("Would you like to change it? (Enter y to change it) " );
                            choiceChangeActor = console.nextLine().trim();
                            if (choiceChangeActor.equals("y"))
                            {
                                System.out.print("Please enter movie actor " + indexActor + " : ");
                                actorName = console.nextLine().trim();
                                if (indexActor == 1)
                                {
                                    while (actorName.equals("") || actorName.contains(","))
                                    {
                                        enterAgain("a movie actor");
                                        actorName = console.nextLine().trim();
                                    }
                                }
                                else
                                {
                                    while (actorName.contains(","))
                                    {
                                        System.out.println("");
                                        System.out.println("A movie actor must not contain \",\" !!");
                                        System.out.print("Please enter a movie actor again: ");
                                        actorName = console.nextLine().trim();
                                    }
                                    if (actorName.equals(""))
                                        while (indexActor < Movie.MAX_ACTOR_NUMBER)
                                        {
                                            movie.getActorList().get(indexActor-1).setName(actorName);
                                            System.out.println("New movie actor " + indexActor + " is: " + movie.getActorList().get(indexActor-1).getName());
                                            indexActor++;
                                        }
                                }
                                movie.getActorList().get(indexActor-1).setName(actorName);
                                System.out.println("New movie actor " + indexActor + " is: " + movie.getActorList().get(indexActor-1).getName());
                            }
                        }
                    }
                    
                    //edit rating
                    String choiceEditrating = "";
                    System.out.println("");
                    System.out.println("Would you like to edit rating? (Enter y to edit it) ");
                    choiceEditrating = console.nextLine().trim();
                    if (choiceEditrating.equals("y"))
                    {
                        int rating = 0;
                        rating = enterRating();

                        movie.setRating(rating);
                    }
                    System.out.println("");
                    System.out.println(movie.getTitle() + " has been edited.");
                    edited = true;
                }
            }
            index++;
        }        
        
        return edited;
    }
    
    /**
     * Error message for unaccepted input
     * 
     * @param item item
     */
    public void enterAgain(String item)
    {
            System.out.println("");
            System.out.println(item + " must not contain blank or \",\" !!");
            System.out.print("Please enter " + item + " again: ");
    }

    /**
     * Enter rating
     * 
     * @return    rating
     */
    public int enterRating()
    {
        Scanner console = new Scanner(System.in);
        String ratingString = "";
        char ratingChar = ' ';
        int rating = 0;
        boolean ratingDone = false;
        
        System.out.print("Please enter a movie rating 1-10: ");
        ratingString = console.nextLine().trim();
        
        while (!ratingDone)
        {
            if (ratingString.length() == 0 || !isDigit(ratingString))
                ratingString = enterRatingAgain();
            else
            {
                rating = Integer.parseInt(ratingString);
                if (rating > 10 || rating < 1)
                    ratingString = enterRatingAgain();
                else
                    ratingDone = true;
            }
        }
        System.out.print("");
        return rating;
    }
    
    /**
     * Error message for entering rating again
     * 
     * @return    rating
     */
    public String enterRatingAgain()
    {
        Scanner console = new Scanner(System.in);
        String ratingString = "";    
        
        System.out.print("It's not accepted. Please enter a movie rating 1-10: ");
        ratingString = console.nextLine().trim();
        return ratingString;
    }
    
    /**
     * Get movie list
     * 
     * @return    movie list
     */
    public ArrayList<Movie> getMovieList()
    {
        return movieList;
    }

    /**
     * Check if a given string is a digit or not.
     * 
     * @param testString test string
     * @return    if test string is digit
     */
    public boolean isDigit(String testString)
    {
        char testChar = ' ';
        boolean testStringIsDigit = true;
        
        for(int index = 0; index < testString.length(); index++)
        {
            testChar = testString.charAt(index);
            if (!Character.isDigit(testChar))
                testStringIsDigit = false;
        }
        return testStringIsDigit;
    }
    
    /**
     * Read file
     * 
     * @param filename input file name
     */
    public void readFile(String filename)
    {
        try
        {
            FileReader inputFile = new FileReader(filename);
            Scanner parser = new Scanner(inputFile);
            while (parser.hasNextLine())
            {
                String[] stringArray = parser.nextLine().split(",");
                
                Movie movie = new Movie();
                movie.setTitle(stringArray[0]);
                movie.setDirector(stringArray[1]);

                for (int index = 1; index <= Movie.MAX_ACTOR_NUMBER; index++)
                {
                    Actor actor  = new Actor();
                    actor.setName(stringArray[index+1]);
                    movie.addActor(actor);
                }

                int rating = Integer.parseInt(stringArray[5]);
                movie.setRating(rating);
                
                movieList.add(movie);
            }
            inputFile.close();
        }
        catch(FileNotFoundException fnfe)
        {
            System.out.println(filename + " is not found");
        }
        catch(IOException ioe)
        {
            System.out.println("Unexpected I/O error occured");
        }
    }
    
    /**
     * Remove movie
     * 
     * @param movie movie
     * @return    boolean
     */
    public boolean removeMovie(Movie movie)
    {
        return movieList.remove(movie);
    }
    
    /**
     * Search if there is the same title of movie
     * 
     * @param searchString search string
     * @return    found
     */
    public boolean searchMovieSameTitle(String searchString)
    {
        int index = 0;
        boolean found = false;
        
        while (index < movieList.size())
        {
            if (searchString.equals(""))
                return found;
            
            Movie movie = new Movie();
            movie = movieList.get(index);
            if (movie.getTitle().equalsIgnoreCase(searchString))
            {
                found = true;
            }
            index++;
        }        
        
        return found;
    }
    
    /**
     * Search movie with partial director name
     * 
     * @param searchString search string
     * @return    found
     */
    public boolean searchMoviePartialDirector(String searchString)
    {
        int index = 0;
        boolean found = false;
        
        while (index < movieList.size())
        {
            if (searchString.equals(""))
                return found;
            
            Movie movie = new Movie();
            movie = movieList.get(index);
            if (movie.getDirector().toLowerCase().contains(searchString.toLowerCase()))
            {
                found = true;
                displayOneMovie(index);
            }
            index++;
        }        
        
        return found;
    }
    
    /**
     * Search if there is the partially same title of movie
     * 
     * @param searchString search string
     * @return    found
     */
    public boolean searchMoviePartialTitle(String searchString)
    {
        int index = 0;
        boolean found = false;
        
        while (index < movieList.size())
        {
            if (searchString.equals(""))
                return found;
            
            Movie movie = new Movie();
            movie = movieList.get(index);
            if (movie.getTitle().toLowerCase().contains(searchString.toLowerCase()))
            {
                found = true;
                displayOneMovie(index);
            }
            index++;
        }        
        
        return found;
    }

    /**
     * Set movie list
     * 
     * @param movieList movie list
     */
    public void setMovieList(ArrayList<Movie> movieList)
    {
        this.movieList = movieList;
    }
    
    /**
     * Write file
     * 
     * @param filename output file name
     */
    public void writeFile(String filename)
    {
        try
        {
            PrintWriter outputFile = new PrintWriter(filename);
            for (Movie movie : movieList)
            {
                outputFile.print(movie.getTitle() + ","
                               + movie.getDirector() + ",");
                for (int indexActor = 0; indexActor < Movie.MAX_ACTOR_NUMBER; indexActor++ )
                {
                    if (indexActor < movie.getActorList().size())
                        outputFile.print(movie.getActorList().get(indexActor).getName());
                    outputFile.print(",");
                }
                outputFile.print(movie.getRating());
                outputFile.println();
            }
            outputFile.close();
        }
        catch(FileNotFoundException fnfe)
        {
            System.out.println(filename + " is not found");
        }
    }
}