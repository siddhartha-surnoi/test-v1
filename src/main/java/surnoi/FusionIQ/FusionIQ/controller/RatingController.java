package surnoi.FusionIQ.FusionIQ.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import surnoi.FusionIQ.FusionIQ.data.Rating;
import surnoi.FusionIQ.FusionIQ.repo.RatingRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rating")
public class RatingController {

    @Autowired
    private RatingRepository ratingRepository;

    @PostMapping("/add")
    public ResponseEntity<Object> addRating(@RequestBody Rating rating) {
        if (rating.getValue() < 1 || rating.getValue() > 5) {
            return ResponseEntity.badRequest().body("Rating value must be between 1 and 5");
        }

        Rating savedRating = ratingRepository.save(rating);
        return new ResponseEntity<>(savedRating, HttpStatus.CREATED);
    }
    @GetMapping("/average")
    public ResponseEntity<Double> getAverageRating() {
        List<Rating> ratings = ratingRepository.findAll();

        if (ratings.isEmpty()) {
            return ResponseEntity.ok().body(0.0);
        }
        double sum = 0;
        for (Rating rating : ratings) {
            sum += rating.getValue();
        }
        double averageRating = sum / ratings.size();

        // Cap the average rating at 5 if it exceeds that value
        averageRating = Math.min(averageRating, 5.0);

        return ResponseEntity.ok().body(averageRating);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getRatingById(@PathVariable Long id) {
        Optional<Rating> optionalRating = ratingRepository.findById(id);

        if (optionalRating.isPresent()) {
            return ResponseEntity.ok().body(optionalRating.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Rating not found");
        }
    }

    // Other methods omitted for brevity
}
