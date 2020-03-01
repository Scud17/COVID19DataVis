package com.profHacks.AngularChartJS;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DataRepository extends JpaRepository<Data, Integer> {

    @Query(value = "SELECT * from covid19 where country = ?1", nativeQuery = true)
    List<CountryAndCount> getAllByCountry(String country);

    @Query("Select e.country from Data e")
    List<String> getAllCountries();
}
