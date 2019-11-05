package gameparam;

public class TimeCounter {
    private static double time;
    private static double boundary;

    public static void start() {
        time += 1.0 / GameParam.fps;
    }

    public static void setBoundary(double boundary) {
        TimeCounter.boundary = boundary;
    }

    public static boolean timeOut() {
        return time > boundary;
    }

    public static void reset() {
        time = 0;
        boundary = 0;
    }

    public static void cancel() {
        time = 0;
    }

    public static double getTime() {
        return time;
    }
}
