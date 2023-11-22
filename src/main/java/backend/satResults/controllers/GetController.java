package backend.satResults.controllers;

import backend.satResults.interfaces.PostRepository;
import backend.satResults.model.Candidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
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
            @RequestParam(required = false) Integer satScore,
            @RequestParam(required = false) String sortBy
    ) {
        List<Candidate> result;

        if (name != null || city != null || country != null || satScore != null) {
            result = postRepository.findByName(name, city, country, satScore != null ? satScore : 0);
        } else {
            result = postRepository.findAll();
        }

        // Apply sorting based on sortBy parameter
        if (sortBy != null) {
            if (sortBy.equals("-satScore")) {
                result.sort(Comparator.comparingInt(Candidate::getSatScore).reversed());
            } else if (sortBy.equals("satScore")) {
                result.sort(Comparator.comparingInt(Candidate::getSatScore));
            } else if (sortBy.equals("name")) {
                result.sort(Comparator.comparing(Candidate::getName, String.CASE_INSENSITIVE_ORDER));
            } else if (sortBy.equals("-name")) {
                result.sort(Comparator.comparing(Candidate::getName, String.CASE_INSENSITIVE_ORDER).reversed());
            }
        }

        return result;
    }


    @GetMapping("getCandidate/{id}")
    public ResponseEntity<Candidate> getCandidateById(@PathVariable String id) {
        Optional<Candidate> candidateOptional = postRepository.findById(id);
        return candidateOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
