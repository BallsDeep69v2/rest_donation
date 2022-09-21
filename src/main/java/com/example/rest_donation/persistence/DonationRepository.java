package com.example.rest_donation.persistence;

import com.example.rest_donation.domain.Donation;
import com.example.rest_donation.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DonationRepository extends JpaRepository<Donation, Integer> {
    @Query("""
                    select donation.donator
                    from Donation donation
                    group by donation.donator
                    having sum(donation.amount) >= :min
            """)
    List<Person> findAllPeopleWhoSpentAtLeast(long min);


}
