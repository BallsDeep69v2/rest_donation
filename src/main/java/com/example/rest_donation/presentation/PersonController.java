package com.example.rest_donation.presentation;

import com.example.rest_donation.domain.Donation;
import com.example.rest_donation.domain.Person;
import com.example.rest_donation.domain.exceptions.NoSuchPersonException;
import com.example.rest_donation.persistence.DonationRepository;
import com.example.rest_donation.persistence.PersonRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/persons")
public record PersonController(PersonRepository personRepository, DonationRepository donationRepository) {

    @GetMapping("/{id}")
    public Person one(@PathVariable Integer id) {
        return personRepository
                .findById(id)
                .orElseThrow(NoSuchPersonException::new);
    }

    @GetMapping("/{id}/donations")
    public List<Donation> getDonations(@PathVariable Integer id){
        if(!personRepository.existsById(id)) throw new NoSuchPersonException();
        return personRepository
                .donationsFromPerson(id);
    }

    @PostMapping
    public ResponseEntity<Person> save(@RequestBody @Valid Person person){
        var savedPerson = personRepository.save(person);

        var uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .build(savedPerson.getId());

        return ResponseEntity
                .created(uri)
                .body(savedPerson);
    }

    @PostMapping("/{id}/donations")
    public ResponseEntity<Donation> saveDonation(@PathVariable Integer id,@RequestBody @Valid Donation donation){
        donation.setDonator(personRepository
                .findById(id)
                .orElseThrow(NoSuchPersonException::new)
        );
        var savedDonation = donationRepository.save(donation);

        var uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .build(savedDonation.getId());

        return ResponseEntity
                .created(uri)
                .body(savedDonation);
    }
}
