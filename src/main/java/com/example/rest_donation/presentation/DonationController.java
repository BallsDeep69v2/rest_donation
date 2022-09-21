package com.example.rest_donation.presentation;


import com.example.rest_donation.domain.Donation;
import com.example.rest_donation.domain.Person;
import com.example.rest_donation.domain.exceptions.NoSuchDonationException;
import com.example.rest_donation.persistence.DonationRepository;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/api/donations")
public record DonationController(DonationRepository donationRepository) {

    @GetMapping("/{id}")
    public Donation one(@PathVariable Integer id) {
        return donationRepository
                .findById(id)
                .orElseThrow(NoSuchDonationException::new);
    }

    @GetMapping
    public List<Person> allPeopleWhoDonatedAtLeastMin(@RequestParam(required = false, name = "min") Long min) {
        return donationRepository
                .findAllPeopleWhoSpentAtLeast(min);
    }

}
