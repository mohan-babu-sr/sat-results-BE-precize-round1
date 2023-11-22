package backend.satResults.controllers;

import backend.satResults.interfaces.PostRepository;
import backend.satResults.model.Candidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class DeleteController {

    @Autowired
    private PostRepository postRepository;

    @DeleteMapping("deleteCandidate/{id}")
    public ResponseEntity<?> deleteCandidateById(@PathVariable String id) {
        Optional<Candidate> candidateOptional = postRepository.findById(id);
        if (candidateOptional.isPresent()) {
            postRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
