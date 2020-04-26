package com.example.springbootmapinit.domain;

import lombok.*;

/**
 * Created by ch on 2020-04-26
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Country {
    private Integer allCasesToday;
    private Integer allCasesYesterday;
    private Integer todayNewCases;
    private String name;
    private double casesPerOneMillionToday;
    private double casesPerOneMillionYesterday;
    private Point point;

    public Country(Integer allCasesToday, String name, double casesPerOneMillion, Point point) {
        this.allCasesToday = allCasesToday;
        this.name = name;
        this.casesPerOneMillionToday = casesPerOneMillion;
        this.point = point;
    }
}
