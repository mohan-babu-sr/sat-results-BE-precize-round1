package backend.satResults.interfaces;

import backend.satResults.model.Candidate;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PostRepository extends MongoRepository<Candidate, String> {
    List<Candidate> findByName(
            String name, String city, String country, int satScore);
}
