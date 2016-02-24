
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BruteCollinearPoints {

    private final Point[] points;
    private LineSegment[] segments;

    /**
     * Finds all line segments containing 4 points
     * @param points
     */
    public BruteCollinearPoints(Point[] points) {
        if (null == points)
            throw new NullPointerException();
        improperArgumentCheck(points);
        this.points = points;
        this.segments = findSegments();
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

    /**
     * Computes all of the 4-distinct-point segments and places them in
     * the array segments.
     */
    private LineSegment[] findSegments() {
        List<LineSegment> segmentList = new ArrayList<>();
        int N = points.length;
        for (int i = 0; i < N; i++) {
            for (int j = i+1; j < N; j++) {
                Double slopePQ = points[i].slopeTo(points[j]);
                for (int k = j+1; k < N; k++) {
                    Double slopePR = points[i].slopeTo(points[k]);
                    if (slopePQ.equals(slopePR)) {
                        for (int l = k + 1; l < N; l++) {
                            Double slopePS = points[i].slopeTo(points[l]);
                            if (slopePQ.equals(slopePS)) {
                                LineSegment lineSegment = new LineSegment(points[i], points[l]);
                                segmentList.add(lineSegment);
                            }
                        }
                    }
                }
            }
        }
        return segmentList.toArray(new LineSegment[segmentList.size()]);
    }

    /**
     * @return the number of 4-distinct-point segments
     */
    public int numberOfSegments() {
        return segments.length;
    }

    /**
     * @return all 4-distinct-point segments
     */
    public LineSegment[] segments() {
        return segments;
    }

    /**
     * @return all points
     */
    public Point[] getPoints() {
        return points;
    }
}
