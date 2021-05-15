package ua.lviv.trainapplogos;

public class Movie implements Comparable<Movie> {
	private String title;
	private Time duration;
	
	public Movie(String title, int durationHours, int durationMinutes) {
		super();
		this.title = title;
		this.duration = new Time(durationHours, durationMinutes);
	}
	
	public Movie(String title, Time duration) {
		super();
		this.title = title;
		this.duration = duration;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Time getDuration() {
		return duration;
	}

	public void setDuration(Time duration) {
		this.duration = duration;
	}

	@Override
	public String toString() {
		return "\"" + title + "\"\n     duration: " + duration;
	}

	@Override
	public int compareTo(Movie m2) {
		return (this.title.compareToIgnoreCase(m2.getTitle()) == 0) && 
			   (this.duration.compareTo(m2.getDuration()) == 0) ? 0 : 1;
	}

}
