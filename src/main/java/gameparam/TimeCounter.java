package gameparam;

public class TimeCounter {
    private static double time;
    private static double boundary;
    private static double counter;

    public static void start() {
        time += 1.0 / GameParam.fps;
        counter += 1.0 / GameParam.fps;
    }

    public static void setBoundary(double boundary) {
        TimeCounter.boundary = boundary;
    }

    /**
     * check the counter.
     * @return true if counter reach boundary.
     */
    public static boolean timeOut() {
        boolean res = counter > boundary;
        if (res) {
            counter = 0;
        }
        return res;
    }

    /**
     * reset fields in time counter.
     */
    public static void reset() {
        time = 0;
        boundary = 0;
        counter = 0;
    }

    public static double getTime() {
        return time;
    }
}
