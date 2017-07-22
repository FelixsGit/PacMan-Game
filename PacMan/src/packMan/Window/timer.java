package packMan.Window;

import java.util.TimerTask;
import java.util.Timer;

public class timer {
	
	private int timePassed = 0;
	
	Timer timer = new Timer();
	TimerTask task = new TimerTask(){

		public void run(){
			System.out.println(timePassed);
			timePassed++;
		}
	};

	public void start(){
		timer.scheduleAtFixedRate(task, 1000, 1000);
	}

	public int getTimePassed() {
		return timePassed;
	}

	public void setTimePassed(int timePassed) {
		this.timePassed = timePassed;
	}
}
