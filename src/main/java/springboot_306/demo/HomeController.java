package springboot_306.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashSet;
import java.util.Set;

@Controller
public class HomeController {

    @Autowired
    DirectorRepository directorRepository;

    Director director = new Director();

    @RequestMapping("/")
    public String index(Model model) {
        // create director
        Director director = new Director();
        director.setName("Stephen Bullock");
        director.setGenre("Sci Fi");

        // create movie
        Movie movie = new Movie();
        movie.setTitle("Star Movie");
        movie.setYear(2017);
        movie.setDescription("About Stars...");

        // add movie to empty list
        Set<Movie> movies = new HashSet<Movie>();
        movies.add(movie);

        // add another movie
        movie = new Movie();
        movie.setTitle("DeathStar Ewoks");
        movie.setYear(2011);
        movie.setDescription("About Ewoks on the DeathStar...");
        movies.add(movie);

        // add list of movies to director's movie list
        director.setMovies(movies);

        // save director to database
        directorRepository.save(director);

        // grab all directors from the database & send them to template
        model.addAttribute("directors", directorRepository.findAll());

        return "index";
    }

}