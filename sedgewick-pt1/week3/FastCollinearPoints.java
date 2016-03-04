

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FastCollinearPoints {
    private final Point[] points;
    private LineSegment[] segments;

    public FastCollinearPoints(Point[] points) {
        if (null == points)
            throw new NullPointerException();
        this.points = points;
        findSegments();
    }

    /**
     * Find all segments!
     */
    private void findSegments() {
        int N = points.length;
        List<Point> pointsList = Arrays.asList(points);
        List<LineSegment> lineSegmentList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            // grab an indexed Point object and sort a points list based on the
            // slope order of that Point object.
            Point origin = points[i];
            Collections.sort(pointsList, origin.slopeOrder());
            pointsList.remove(origin);

            // create a list of points that pose as potential candidates for a
            // new line segment if the list is size 3 or larger.
            // consistent slopes among points determine the contents of the list
            List<Point> potentialSegment = new ArrayList<>();
            double tmpSlope = origin.slopeTo(pointsList.get(0));
            for (int j = 0; j < N-1; j++) {
                Point pt = pointsList.get(j);
                if (origin.slopeTo(pt) == tmpSlope) {
                    potentialSegment.add(pt);
                } else {
                    if (potentialSegment.size() >= 3) {
                        potentialSegment.add(origin);
                        lineSegmentList.add(ls(potentialSegment));
                    }
                    tmpSlope = origin.slopeTo(pt);
                    potentialSegment.clear();
                }
            }
            // one final segment, if it exists
            if (potentialSegment.size() >= 3) {
                potentialSegment.add(origin);
                lineSegmentList.add(ls(potentialSegment));
            }
            pointsList.add(origin);
            potentialSegment.clear();
        }
        segments = lineSegmentList.toArray(new LineSegment[lineSegmentList.size()]);
    }


    private LineSegment ls(List<Point> potentialSegment) {
        Point min = Collections.min(potentialSegment);
        Point max = Collections.max(potentialSegment);
        return new LineSegment(min, max);
    }

    /**
     * Are there any null points?
     * Are there any duplicate points?
     *
     * Let's find out and throw a big fat exception, if there are!
     */
    private void improperArgumentCheck(Point[] points) {
        int N = points.length;
        for (int i = 0; i < N; i++) {
            if (null == points[i])
                throw new NullPointerException();
        }

        Arrays.sort(points);
        for (int i = 1; i < N; i++) {
            if (points[i-1].equals(points[i]))
                throw new IllegalArgumentException();
        }
    }

    public int numberOfSegments() {
        return segments.length;
    }

    public LineSegment[] segments() {
        return segments;
    }
}
