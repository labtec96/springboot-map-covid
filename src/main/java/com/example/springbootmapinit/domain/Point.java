package com.example.springbootmapinit.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by ch on 2020-04-14
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Point {

    private double lat;
    private double lon;
    private String text;

}
