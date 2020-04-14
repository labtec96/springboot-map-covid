package com.example.springbootmapinit.repositories;

import com.example.springbootmapinit.domain.Point;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ch on 2020-04-14
 */
@Repository
public class PointRepo {

    private List<Point> pointList;

    public PointRepo() {
        this.pointList = new ArrayList<>();
    }

    public List<Point> getPointList() {
        return pointList;
    }

    public void addPoint(Point point) {
        pointList.add(point);
    }
}
