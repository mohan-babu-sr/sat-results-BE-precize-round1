package backend.satResults.controllers;

import backend.satResults.interfaces.PostRepository;
import backend.satResults.model.Candidate;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class PostController {

    @Autowired
    PostRepository postRepository;

    @RequestMapping
    public void  redirect(HttpServletResponse response) throws IOException {
        response.sendRedirect("google.com");
    }

    @PostMapping("/addCandidate")
    public Candidate addCandidate(@RequestBody Candidate candidate){
        return postRepository.save(candidate);
    }
}
