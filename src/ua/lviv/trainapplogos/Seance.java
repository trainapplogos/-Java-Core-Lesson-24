package ua.lviv.trainapplogos;

import java.time.LocalTime;

public class Seance extends Movie {
	private Movie movie;
	private Time startTime;
	private Time endTime;
	private int fullEndHour;
	private int fullEndMin;
	
	
	//short set of parameters
	public Seance(Movie movie, Time startTime) {
		super(movie.getTitle(), movie.getDuration());
		this.movie = movie;
		this.startTime = startTime;
		
		Time duration = movie.getDuration(); //getting duration
		LocalTime localSarttTime = LocalTime.of(startTime.getHour(), startTime.getMin()); //getting startTime in LocalTime format
		LocalTime endTimeCalc = localSarttTime.plusHours(duration.getHour()).plusMinutes(duration.getMin()); //calculating endTime in LocalTime format
		this.endTime = new Time(endTimeCalc.getHour(), endTimeCalc.getMinute()); //set endTime in Time format
		
		int totalMins = startTime.getMin() + duration.getMin();
		int hoursInMins = totalMins / 60;
		this.fullEndMin = totalMins % 60;
		this.fullEndHour = startTime.getHour() + duration.getHour() + hoursInMins;
		
	}
		
	public Seance(Movie movie, int startTimeHours, int startTimeMinutes) {
		this(movie, new Time(startTimeHours, startTimeMinutes));
	}
	
	//full set of parameters
	public Seance(String title, int durationHours, int durationMinutes, int startTimeHours, int startTimeMinutes) {
		this(new Movie(title, new Time(durationHours, durationMinutes)), new Time(startTimeHours, startTimeMinutes));
	}
	
	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public Time getStartTime() {
		return startTime;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	public Time getEndTime() {
		return endTime;
	}

	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}
	
	public int getFullEndHour() {
		return fullEndHour;
	}

	public void setFullEndHour(int fullEndHour) {
		this.fullEndHour = fullEndHour;
	}

	public int getFullEndMin() {
		return fullEndMin;
	}

	public void setFullEndMin(int fullEndMin) {
		this.fullEndMin = fullEndMin;
	}

	@Override
	public String toString() {
		return "Seance of movie: " + movie + "\n     start time: " + startTime + "\n     end time: " + endTime;
	}
}

