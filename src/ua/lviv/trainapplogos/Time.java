package ua.lviv.trainapplogos;

public class Time implements Comparable<Time>{
	private int hour;
	private int min;

	public Time(int hour, int min) throws IllegalArgumentException {
		super();
		this.hour = checkTime(hour, 24);
		this.min =  checkTime(min, 60);
	}

	public int getMin() {
		return min;
	}

	public int getHour() {
		return hour;
	}
	
	public void setMin(int min) throws IllegalArgumentException {
		this.min = checkTime(min, 60);
	}
	
	public void setHour(int hour) throws IllegalArgumentException {
		this.hour = checkTime(hour, 24);
	}

	@Override
	public String toString() {
		return (hour < 10 ? ("0" + hour) : hour) + ":" + (min < 10 ? ("0" + min) : min);
	}
	
	private int checkTime(int time, int range) throws IllegalArgumentException {
		if ((time >= 0) && (time < range)) {
			return time;
		} else { 
			throw new IllegalArgumentException("Error: " + ((range == 24) ? "hours" : "minutes") + " must be in range 0.." + (range - 1) + "!");
		}
	}

	@Override
	public int compareTo(Time t2) {	
		if ((this.hour - t2.getHour()) == 0) {
			return this.min - t2.getMin();
		} else return (this.hour - t2.getHour()); 
		
		/*LocalDateTime lt = LocalTime.of(this.hour, this.min);
		LocalTime lt2 = LocalTime.of(t2.getHour(), t2.getMin());
		return lt.compareTo(lt2); */
	}
}
