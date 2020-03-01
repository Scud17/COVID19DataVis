package com.profHacks.AngularChartJS;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@lombok.Data
@Table(name="covid19")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Data {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int data_id;
    @Column(name = "country")
    private String country;
    @Column(name = "total_cases")
    private int total_cases;
    @Column(name = "new_cases")
    private int new_cases;
    @Column(name = "total_deaths")
    private int total_deaths;
    @Column(name = "new_deaths")
    private int new_deaths;
    @Column(name = "active_cases")
    private int active_cases;
    @Column(name = "total_recovered")
    private int total_recovered;
    @Column(name = "serious_critical")
    private int serious_critical;
    @Column(name = "date_captured")
    private Timestamp dateCapture;
}
