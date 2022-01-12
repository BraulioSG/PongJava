import java.time.Duration;
import java.time.Instant;

//Reference: yt -> CoderBoi

public class Time{
    private Time () {
    }

    private static Duration frameTime = Duration.ZERO;
    private static Duration endTime = Duration.ZERO;
    private static Instant startTime = Instant.now();
    private static double deltaTime = frameTime.toMillis() - endTime.toMillis();

    public static void InitTime(){
	startTime = Instant.now();
	frameTime = Duration.ZERO;
    }

    public static void CalcDeltaTime(){
	frameTime = Duration.between(startTime, Instant.now());
	deltaTime = (double)frameTime.toMillis() - endTime.toMillis();
	endTime = frameTime;
    }

    public static double getDeltaTime(){
	return deltaTime / 1000;
    }
}
