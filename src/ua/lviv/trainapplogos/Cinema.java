package ua.lviv.trainapplogos;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Cinema {
	private ArrayList<Movie> moviesLibrary = new ArrayList<>();
	private TreeMap <Days, Schedule> schedules =  new TreeMap<>();
	private Time openTime; //time of opening of Cinema
	private Time closeTime; //time of closing of Cinema
	private String cinemaName;
	
	public Cinema(String name, Time open, Time close) {
		super();
		this.cinemaName = name;
		this.openTime = open;
		this.closeTime = close;
	}
	
	public Cinema() {
		super();
	}

	public Days stringToDays(String day) throws Exception {
//		throw new Exception(""The day " + day + " doesn't exist!"");
		return Days.valueOf(day.toUpperCase());
	}
	
	public void addMovie(Movie movie) {
		this.moviesLibrary.add(movie);
	}
	
	public void removeMovieFromLib(Movie movie) {
		this.moviesLibrary.remove(movie);
	}
	
	public void removeMovieByName(String name) {
		moviesLibrary.removeIf(m -> m.getTitle().equalsIgnoreCase(name));
		
		for (Map.Entry<Days, Schedule> val: schedules.entrySet()) {
			Schedule schedule = val.getValue();
			schedule.removeMovieByName(name);
		}
	}
	
	public void addSeance(Seance seance, String day) throws Exception {
		if ((seance.getStartTime().compareTo(this.openTime) >= 0) && 
			((seance.getFullEndHour() < this.closeTime.getHour()) ||
			((seance.getFullEndHour() == this.closeTime.getHour()) && 
			 (seance.getFullEndMin() <= this.closeTime.getMin())))) {

			Schedule currentSchedule = this.schedules.get(stringToDays(day));
			
			if (currentSchedule == null) currentSchedule = new Schedule();
			
			currentSchedule.addSeance(seance);
			
			this.schedules.put(stringToDays(day), currentSchedule);
			System.out.println("> The Seance was added!");
		} else System.out.println("> Error: Can't add to " + day + " " + seance +
					"\nPlease enter time of start and end of Seanse between " + 
					this.openTime + " and " + this.closeTime + 
					"\nAlso take into account the duration of the movie\n");
	}
	
	public Schedule getScheduleByDay(String day) throws Exception {
		return this.schedules.get(stringToDays(day));
	}
	
	public void removeSeance(Seance seance, String day) throws Exception {
		Schedule currentSchedule = getScheduleByDay(day);
		
		if (currentSchedule != null) {
			currentSchedule.removeSeance(seance);
			this.schedules.put(stringToDays(day), currentSchedule);
		}
	}
	
	public void showSeances(String day) throws Exception {
		System.out.println("> The List of Seanses for " + day.toLowerCase() + ":");
		Schedule currentSchedule = this.schedules.get(stringToDays(day));
		currentSchedule.showSeances();
		System.out.println();
	}
	
	public void showMoviesList() {
		AtomicInteger ind = new AtomicInteger(1);
		
		System.out.println("> The List of movies in this Cinema:");
		moviesLibrary.forEach(m -> System.out.println(ind.getAndIncrement() + ") " + m));
	}
	
	public Movie getMovieByName(String name) {
		for (Movie movie : moviesLibrary) {
			if (movie.getTitle().equalsIgnoreCase(name)) {
				return movie; 
			}
		}
		return null;
	}
	
	public ArrayList<Movie> getMoviesLibrary() {
		return moviesLibrary;
	}

	public void setMoviesLibrary(ArrayList<Movie> moviesLibrary) {
		this.moviesLibrary = moviesLibrary;
	}

	public TreeMap<Days, Schedule> getSchedules() {
		return schedules;
	}

	public void setSchedules(TreeMap<Days, Schedule> schedules) {
		this.schedules = schedules;
	}

	public Time getOpenTime() {
		return openTime;
	}

	public void setOpenTime(Time openTime) {
		this.openTime = openTime;
	}

	public Time getCloseTime() {
		return closeTime;
	}

	public void setCloseTime(Time closeTime) {
		this.closeTime = closeTime;
	}

	public String getCinemaName() {
		return cinemaName;
	}

	public void setCinemaName(String cinemaName) {
		this.cinemaName = cinemaName;
	}
	
	public void clearMoviesLibrary() {
		moviesLibrary.clear();
		System.out.println("> The Movies List was cleared!");
	}
}
