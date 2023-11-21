package backend.satResults.controllers;

import backend.satResults.interfaces.PostRepository;
import backend.satResults.model.Candidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class GetController {

    @Autowired
    PostRepository postRepository;

    @GetMapping("/candidates")
    public List<Candidate> getCandidates(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String country,
            @RequestParam(required = false) Integer satScore
    ) {
        if (name != null || city != null || country != null || satScore != null) {
            // If any search parameter is provided, perform a search
            return postRepository.findByName(name, city, country, satScore != null ? satScore : 0);
        } else {
            // If no search parameters are provided, return all candidates
            return postRepository.findAll();
        }
    }

    @GetMapping("getCandidate/{id}")
    public ResponseEntity<Candidate> getCandidateById(@PathVariable String id) {
        Optional<Candidate> candidateOptional = postRepository.findById(id);
        return candidateOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

//    public List<Candidate> getAllCandidates() {
//        return postRepository.findAll();
//    }
//
//    @GetMapping("/candidates")
//    public List<Candidate> searchCandidates(
//            @RequestParam(required = false) String name,
//            @RequestParam(required = false) String city,
//            @RequestParam(required = false) String country,
//            @RequestParam(required = false) Integer satScore
//    ) {
//        // Customize this method based on your search criteria
//        return postRepository.findByText(
//                name, city, country, satScore != null ? satScore : 0);
//    }
}
