package surnoi.FusionIQ.FusionIQ.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import surnoi.FusionIQ.FusionIQ.data.Rating;
import surnoi.FusionIQ.FusionIQ.repo.RatingRepository;

import java.util.List;

@Service
public class RatingService {

    @Autowired
    private RatingRepository itemRepository;

    public List<Rating> getAllItems() {
        return itemRepository.findAll();
    }

    public Rating rateItem(Long itemId, int rating) {
        Rating item = itemRepository.findById(itemId).orElseThrow(() -> new RuntimeException("Item not found"));
        item.setValue(rating);
        return itemRepository.save(item);
    }
}
