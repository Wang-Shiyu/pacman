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

    public static boolean timeOut() {
        boolean res = counter > boundary;
        if(res)
            counter = 0;
        return res;
    }

    public static void reset() {
        time = 0;
        boundary = 0;
        counter = 0;
    }

    public static double getTime() {
        return time;
    }
}
