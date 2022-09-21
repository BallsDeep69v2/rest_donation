package com.example.rest_donation.persistence;

import com.example.rest_donation.domain.Donation;
import com.example.rest_donation.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Integer> {

    @Query("""
                select donation
                from Donation donation
                where donation.donator.id = :id
            """)
    List<Donation> donationsFromPerson(Integer id);



}
