package ua.lviv.trainapplogos;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws Exception {
		Time opened = new Time(8, 0);
		Time closed = new Time(23, 0);
		Cinema cinema = new Cinema("KingCross Leopolis", opened, closed);
		
		Movie movie1 = new Movie("The last of the Mohicans", 02, 02);
		Movie movie2 = new Movie("Terminator 3: Rise of the machines", 01, 49);
		Movie movie3 = new Movie("The Lord of the Rings: The Return of the King", 04, 11);
		Movie movie4 = new Movie("Braveheart", 03, 02);
		Movie movie5 = new Movie("The Passion of the Christ", 02, 07);
		Movie movie6 = new Movie("Iron Man", 02, 11);
		Movie movie7 = new Movie("Risen", 01, 46);
		Movie movie8 = new Movie("Avengers: Endgame", 03, 01);
		
		cinema.addMovie(movie1);
		cinema.addMovie(movie2);
		cinema.addMovie(movie3);
		cinema.addMovie(movie4);
		cinema.addMovie(movie5);
		cinema.addMovie(movie6);
		cinema.addMovie(movie7);
		cinema.addMovie(movie8);
		
		Seance seance1 = new Seance(movie1, new Time(12, 00));
		Seance seance11 = new Seance(movie1, new Time(14, 00));
		Seance seance12 = new Seance(movie1, new Time(16, 00));
		
		Seance seance2 = new Seance(movie2, new Time(19, 00));
		Seance seance21 = new Seance(movie2, new Time(15, 00));
		Seance seance22 = new Seance(movie2, new Time(17, 00));
		
		Seance seance3 = new Seance(movie4, new Time(17, 00));
		Seance seance4 = new Seance(movie3, new Time(13, 00));
		Seance seance5 = new Seance(movie5, new Time(15, 00));
		Seance seance6 = new Seance(movie6, new Time(18, 00));
		Seance seance7 = new Seance(movie7, new Time(8, 00));
		Seance seance8 = new Seance(movie8, new Time(19, 30));
		Seance seance88 = new Seance(movie8, new Time(19, 35));
//		Seance seance888 = new Seance(movie8, new Time(20, 30)); //will be Error during adding of seance
		
		
		cinema.addSeance(seance1, "wednesday");
		cinema.addSeance(seance11, "wednesday");
		cinema.addSeance(seance12, "wednesday");
		cinema.addSeance(seance2, "monday"); //movie 2
		cinema.addSeance(seance21, "monday"); //movie 2
		cinema.addSeance(seance22, "monday"); //movie 2
		cinema.addSeance(seance3, "Thursday"); 
		cinema.addSeance(seance4, "monday"); //movie 3
		cinema.addSeance(seance5, "friday");
		cinema.addSeance(seance6, "saturday");
		cinema.addSeance(seance7, "sunday");
		cinema.addSeance(seance8, "tuesday");
		cinema.addSeance(seance88, "tuesday");
//		cinema.addSeance(seance888, "sunday"); //error because of close time 
	
		cinema.showSeances("monday"); //4 seances
		cinema.showSeances("tuesday"); //1 seance
		cinema.showSeances("wednesday"); //3 seances
		cinema.showSeances("Thursday"); //1 seance
		cinema.showSeances("Friday"); //1 seance
		cinema.showSeances("saturday"); //1 seance
		cinema.showSeances("Sunday"); //1 seance
		
		while (true) {
			ShowInitialMenu(cinema);
			
			Scanner sc = new Scanner(System.in);
			byte menuItem = sc.nextByte();
			
			switch (menuItem) {
			case 0: //Show name of the current Cinema
				System.out.println("The name of the current cinema is " + cinema.getCinemaName());
				break;
			case 1: //Show the list of movies of this Cinema
				cinema.showMoviesList();
				break;
			case 2: //Add movie to list
				addMovieToLib(cinema);
				break;
			case 3: //Remove movie from list
				removeMovieFromScheduleAndLib(cinema);
				break;
			case 4: //clear movie list
				cinema.clearMoviesLibrary();
				break;
			case 5: //Show the schedule of seances
				showSeancesForDay(cinema);
				break;
			case 6: //Add seance
				addSeanceForDay(cinema);
				break;
			case 7: //Remove seance
				removeSeanceForDay(cinema);
				break;
			case 8: //Delete all seances
				cinema.getSchedules().clear();
				System.out.println("> All seances was deleted!");
				break;
			case 9:
				System.exit(0);
			default:
				break;
			}
		}
	}
	
	public static void ShowInitialMenu(Cinema cinema) {
		System.out.println("-----------------------------------------------");
		System.out.println("[ Current Cinema: " + cinema.getCinemaName() + " ]");
		System.out.println("* ************** [Cinema menu] ************** *");
//		System.out.println("* 0: Show name of current Cinema");
		System.out.println("* 1: Show the list of movies of this Cinema");
		System.out.println("* 2: Add movie to list");
		System.out.println("* 3: Remove movie from list");
		System.out.println("* 4: Clear movies list");
		System.out.println("* 5: Show the schedule of seanses");
		System.out.println("* 6: Add seance");
		System.out.println("* 7: Remove seance");
		System.out.println("* 8: Delete all seances");
		System.out.println("* 9: Exit");
		System.out.println("-----------------------------------------------");
	}
	
	public static Movie addMovieToLib(Cinema cinema) {
		Scanner sc = new Scanner(System.in);
		System.out.println("> Enter name of movie:");
		String name = sc.nextLine();

		return addMovieToLibByName(cinema, name);
	}
	
	public static Movie addMovieToLibByName(Cinema cinema, String name) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter duration in hours:");
		int hours = sc.nextInt();
		System.out.println("Enter duration in minutes:");
		int mins = sc.nextInt();
		
		Movie movie = new Movie(name, hours, mins);
		cinema.addMovie(movie);
		System.out.println("> The movie was added!");
		return movie;
	}
	
	public static void removeMovieFromScheduleAndLib(Cinema cinema) {
		Scanner sc = new Scanner(System.in);
		System.out.println("> Enter name of movie:");
		String name = sc.nextLine();
	
		cinema.removeMovieByName(name);
		System.out.println("> The movie was removed!");
	}
	
	public static void showSeancesForDay(Cinema cinema) throws Exception {
		Scanner sc = new Scanner(System.in);
		System.out.println("> Enter day to show seances:");
		String day = sc.nextLine();
		cinema.showSeances(day);
	}
	
	public static void addSeanceForDay(Cinema cinema) throws Exception {
		Scanner sc = new Scanner(System.in);
		System.out.println("> Enter day to add seance:");
		String day = sc.nextLine();
		
		System.out.println("> Enter name of movie to add:");
		String name = sc.next();
		
		System.out.println("> Enter hours of start time of seance:");
		int hours = sc.nextInt();
		
		System.out.println("> Enter minutes of start time of seance:");
		int mins = sc.nextInt();
		
		Movie resMovie = cinema.getMovieByName(name);
		
		if (resMovie == null) { //if the movie doesn't exist in the library add it
			resMovie = addMovieToLibByName(cinema, name);
			System.out.println("The Movie was added successfully");
		}
		
		Seance seance = new Seance(resMovie, new Time(hours, mins));

		cinema.addSeance(seance, day);
	}
	
	public static void removeSeanceForDay(Cinema cinema) throws IllegalArgumentException, Exception {
		Scanner sc = new Scanner(System.in);
		System.out.println("> Enter day to remove seance:");
		String day = sc.nextLine();
		
		System.out.println("> Enter name of movie to remove:");
		String name = sc.nextLine();
		
		System.out.println("> Enter hours of start time of seance:");
		int hours = sc.nextInt();
		
		System.out.println("> Enter minutes of start time of seance:");
		int mins = sc.nextInt();
		
		Seance seance = cinema.getScheduleByDay(day).getSeanceByNameAndTime(name, new Time(hours, mins));
		
		cinema.removeSeance(seance, day);
		System.out.println("> Seance was removed!");
	}
}
