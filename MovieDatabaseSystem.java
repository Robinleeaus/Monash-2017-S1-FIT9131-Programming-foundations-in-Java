import java.util.*;
/**
 * The MovieDatabaseSystem class
 * 
 * @author Sung-Che Lee
 * @version 2.1 26/05/2017
 */
public class MovieDatabaseSystem
{
    private MovieList movieList;
    
    /**
     * Default constructor
     */
    public MovieDatabaseSystem()
    {
        movieList = new MovieList();
    }

    /**
     * Other constructor
     * 
     * @param movieList movie list
     */
    public MovieDatabaseSystem(MovieList movieList)
    {
        this.movieList = movieList;
    }    

    /**
     * (2) Add movie
     */
    public void addMovie()
    {
        Scanner console = new Scanner(System.in);
        Movie movie = new Movie();
        
        //Enter movie title
        String title = "";
        boolean foundSameTitle = false;
        
        System.out.print("Please enter a movie title: ");
        title = console.nextLine().trim();
        foundSameTitle = movieList.searchMovieSameTitle(title);
        while (title.equals("") || title.contains(",") || foundSameTitle)
        {
            if (foundSameTitle)
            {
                System.out.println("");
                System.out.print("There is already a same movie in the database. Please enter another movie title: ");
            }
            else
                movieList.enterAgain("a movie title");
            title = console.nextLine().trim();
            foundSameTitle = movieList.searchMovieSameTitle(title);
        }
        movie.setTitle(title);
        
        //Enter movie director
        String director = "";

        System.out.print("Please enter a movie director: ");
        director = console.nextLine().trim();
        while (director.equals("") || director.contains(","))
        {
            movieList.enterAgain("a movie director");
            director = console.nextLine().trim();
        }
        movie.setDirector(director);
        
        //Enter movie actors
        String actorName = "";

        System.out.println("Movie actors : ");
        System.out.println("You need to enter at least 1 and up to 3 actor names.");
        System.out.println("Blank string in actor 2 or 3 means that there is no actor after that field.");
        for (int index = 1; index <= Movie.MAX_ACTOR_NUMBER; index++)
        {
            Actor actor = new Actor();
            System.out.print("Please enter movie actor " + index + " : ");
            actorName = console.nextLine().trim();
            if (index == 1)
            {
                while (actorName.equals("") || actorName.contains(","))
                {
                    movieList.enterAgain("a movie actor");
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
                    while (index < Movie.MAX_ACTOR_NUMBER)
                    {
                        Actor actorBlankString = new Actor();
                        actorBlankString.setName(actorName);
                        movie.addActor(actorBlankString);
                        index++;
                    }
            }
            actor.setName(actorName);
            movie.addActor(actor);
        }

        //Enter movie rating
        int rating = 0;
        rating = movieList.enterRating();
        movie.setRating(rating);

        movieList.addMovie(movie);
        System.out.println("");
    }

    /**
     * After the menu is shown, users can choose what they want to do.
     * 
     * @return  boolean
     */
    public boolean chooseOption()
    {
        Scanner console = new Scanner(System.in);
        String optionKeyIn = "";
        char optionSelected = ' ';
        boolean notChosen = true;
        boolean exitProgram = false;
        
        optionKeyIn = console.nextLine().trim();
        System.out.println("");
        while (notChosen)
        {
            if (optionKeyIn.length() == 1)
            {
                optionSelected = optionKeyIn.charAt(0);
                switch (optionSelected)
                {
                    case '1': searchMovies(); notChosen = false; break;
                    case '2': addMovie(); notChosen = false; break;
                    case '3': deleteMovie(); notChosen = false; break;
                    case '4': displayFavouriteMovies(); notChosen = false; break;
                    case '5': exitSystem(); notChosen = false; exitProgram = true;break;
                    case '6': editMovie(); notChosen = false;break;
                    default: optionKeyIn = chooseOptionAgain(); break;
                }    
            }            
            else
                optionKeyIn = chooseOptionAgain();
        }
        return exitProgram;
    }
    
    /**
     * Choose option again
     * 
     * @return  user intput
     */
    public String chooseOptionAgain()
    {
        Scanner console = new Scanner(System.in);
        String optionKeyIn = "";
        
        System.out.println("Please enter only one number between 1 and 6.");
        System.out.print("Choose an option:");
        optionKeyIn = console.nextLine().trim();
        System.out.println("");
        
        return optionKeyIn;
    }

    /**
     * (3) Delete movie
     */
    public void deleteMovie()
    {
        Scanner console = new Scanner(System.in);
        String choice = "";
        String searchString = "";
        boolean deleted = false;
        
        System.out.println("Would you like to use exact or partial search string?");
        System.out.println("Please enter 1 or 2. (1: exact, 2: partial) ");
        choice = console.nextLine().trim();
        while (!choice.equals("1") && !choice.equals("2"))
        {
            System.out.println("Please enter 1 or 2. (1: exact, 2: partial) ");
            choice = console.nextLine().trim();
        }
        
        if (choice.equals("1"))
        {
            seeExistingMovieList();
            System.out.println("Please enter the exact title of movie which you want to delete: ");
            searchString = console.nextLine().trim();
            deleted = movieList.deleteMovieExactTitle(searchString);
        }
        else if (choice.equals("2"))
        {
            seeExistingMovieList();
            System.out.println("Please enter the partial title of movie which you want to delete: ");
            searchString = console.nextLine().trim();
            deleted = movieList.deleteMoviePartialTitle(searchString);
        }
        
        if (!deleted)
            System.out.println("No movie has been found or deleted.");
            
        System.out.println("");
    }
    
    /**
     * (4) Display Favourite movies
     */
    public void displayFavouriteMovies()
    {
        int rating = 0;
        int index = 0;
        boolean found = false;
        
        rating = movieList.enterRating();
        System.out.println("The matching movies are: ");
        found = movieList.displayFavouriteMovies(rating);
        if (!found)
            System.out.println("None");
        System.out.println("");
    }
    
    /**
     * Display a menu
     */
    public void displayMenu()
    {
        System.out.println("Welcome to the Movie Database System");
        System.out.println("================================");
        System.out.println("(1) Search Movies");
        System.out.println("(2) Add Movie");
        System.out.println("(3) Delete Movie");
        System.out.println("(4) Display Favourite Movies");
        System.out.println("(5) Exit System");
        System.out.println("(6) Edit Movie");
        System.out.print("Choose an option:");
    }
    
    /**
     * (6) Edit movie
     */
    public void editMovie()
    {
        System.out.println("The fields which can be edited are actors and rating.");
        
        seeExistingMovieList();
        System.out.println("Please enter the exact title of movie which you want to edit: ");
        
        Scanner console = new Scanner(System.in);
        String searchString = "";
        boolean edited = false;
        
        searchString = console.nextLine().trim();
        edited = movieList.editMovieExactTitle(searchString);
        if (!edited)
            System.out.println("No movie has been found or edited.");
        System.out.println("");
    }

    /**
     * (5) Exit system
     */
    public void exitSystem()
    {
        System.out.println("Byebye!!");
    }
    
    /**
     * (1) Search movies
     */
    public void searchMovies()
    {
        Scanner console = new Scanner(System.in);
        String choice = "";
        boolean found = false;
        String searchString = "";
        ArrayList<String>  searchStringListDirectors = new ArrayList<String>();
        
        System.out.println("Would you like to search by title or directors?");
        System.out.println("Please enter 1 or 2. (1: title, 2: directors) ");
        choice = console.nextLine().trim();
        while (!choice.equals("1") && !choice.equals("2"))
        {
            System.out.println("Please enter 1 or 2. (1: title, 2: directors) ");
            choice = console.nextLine().trim();
        }
        if (choice.equals("1"))
        {
            System.out.print("Please enter the title of movie which you want to search:");
            searchString = console.nextLine().trim();
            System.out.println("");
            System.out.println("The matching movies are: ");
            found = movieList.searchMoviePartialTitle(searchString);
            if (!found)
                System.out.println("None");
        }
        else if (choice.equals("2"))
        {
            boolean enterDirectorDone = false;
            
            System.out.println("You can enter more than one directors to search.");
            System.out.println("Blank string means that you have finished entering all the directors.");
            int index = 1;
            while (!enterDirectorDone)
            {
                System.out.print("Please enter director " + index + " : ");
                searchString = console.nextLine().trim();
                if (searchString.equals(""))
                    enterDirectorDone = true;
                else
                {
                    searchStringListDirectors.add(searchString);
                    index++;
                }
            }
            
            System.out.println("");
            System.out.println("The matching movies are: ");
            System.out.println("");
            for (int indexStringListDirectors = 0; indexStringListDirectors < searchStringListDirectors.size(); indexStringListDirectors++)
            {
                searchString = searchStringListDirectors.get(indexStringListDirectors);
                System.out.println("Director name: " + searchString);
                found = movieList.searchMoviePartialDirector(searchString);
                if (!found)
                    System.out.println("None");
                System.out.println("");
            }
        }
        System.out.println("");
    }
    
    /**
     * See the existing movie list
     */
    public void seeExistingMovieList()
    {
        Scanner console = new Scanner(System.in);
        String choice = "";
        
        System.out.println("Do you want to see the existing movie list? (Enter y to see it) ");
        choice = console.nextLine().trim();
        if (choice.equals("y"))
        {
            System.out.println("");
            System.out.println("The existing movies are: ");
            movieList.displayAllMovies();
            System.out.println("");
        }
    }
    
    
    /**
     * This is the start point of the program.
     */
    public void startProgram()
    {
        boolean exitProgram = false;
        String inputFile = "myvideos.txt";
        String outputFile = "myvideos.txt";
        
        movieList.readFile(inputFile);
        while (!exitProgram)
        {
            displayMenu();
            exitProgram = chooseOption();
        }
        movieList.writeFile(outputFile);
        movieList.getMovieList().clear();
        System.exit(0);
    }
}