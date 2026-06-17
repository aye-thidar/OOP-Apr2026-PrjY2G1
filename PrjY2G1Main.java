
import java.util.Scanner;

class PrjY2G1Album {

    private String albumName, artist;
    private PrjY2G1Song songs[];

    public PrjY2G1Album(String albumName, String artist, PrjY2G1Song songs[]) {
        this.albumName = albumName;
        this.artist = artist;
        this.songs = songs;
    }

    public String getArtist() {
        return artist;
    }

    public String getAlbumName() {
        return albumName;
    }

    public String getAlbumDuration() {
        int totalSeconds = 0, totalMinutes = 0, totalHours = 0;
        for (PrjY2G1Song song : songs) {
            totalMinutes += song.getMinutes();
            totalSeconds += song.getSeconds();
        }

        if (totalSeconds > 59) {
            totalMinutes += totalSeconds / 60;
            totalSeconds %= 60;
        }

        if (totalMinutes > 59) {
            totalHours = totalMinutes / 60;
            totalMinutes %= 60;
        }

        String albumHours = totalHours >= 10 ? totalHours + ""
                : "0" + totalHours;

        String albumMinutes = totalMinutes >= 10 ? totalMinutes + ""
                : "0" + totalMinutes;

        String albumSeconds = totalSeconds >= 10 ? totalSeconds + ""
                : "0" + totalSeconds;

        return albumHours + ":" + albumMinutes + ":" + albumSeconds;
    }

    public int getNumberOfSongs() {
        return songs.length;
    }

    public PrjY2G1Song[] getSongs() {
        return songs;
    }

    public void setSongs(PrjY2G1Song songs[]) {
        this.songs = songs;
    }

    public String showAlbum() {
        String songList = "";
        for (int i = 0; i < songs.length; i++) {
            songList += "\n\t" + (i + 1) + ". " + songs[i].toString();
        }
        return "Album: " + albumName + ", Artist: " + artist + "\nSong(s): " + songList + "\n";

    }
}

class PrjY2G1Song {

    private String title;
    private String duration;

    public PrjY2G1Song() {
        this.title = "Unknown Title";
        this.duration = "00:00";
    }

    public PrjY2G1Song(String title, String duration) {
        this.title = title;
        this.duration = duration;
    }

    public String getTitle() {
        return title;
    }

    public String getDuration() {
        return duration;
    }

    public int getMinutes() {
        String[] parts = duration.split(":");
        return Integer.parseInt(parts[0]);
    }

    public int getSeconds() {
        String[] parts = duration.split(":");
        return Integer.parseInt(parts[1]);
    }

    public String toString() {
        return title + " (" + duration + ")";
    }

    public String toString(boolean showDuration) {
        if (showDuration) {
            return this.toString();
        }
        return title;
    }

}

public class PrjY2G1Main {

    static PrjY2G1Album[] albums = {
        new PrjY2G1Album("1989", "Taylor Swift", new PrjY2G1Song[]{
            new PrjY2G1Song("Blank Space", "3:51"),
            new PrjY2G1Song("Shake It Off", "3:39"),
            new PrjY2G1Song("Style", "3:51")
        }),
        new PrjY2G1Album("Short n' Sweet", "Sabrina Carpenter", new PrjY2G1Song[]{
            new PrjY2G1Song("Espresso", "2:55"),
            new PrjY2G1Song("Please Please Please", "3:06"),
            new PrjY2G1Song("Taste", "2:37")
        }),
        new PrjY2G1Album("K-12", "Melanie Martinez", new PrjY2G1Song[]{
            new PrjY2G1Song("Show & Tell", "3:35"),
            new PrjY2G1Song("Lunchbox Friends", "2:49"),
            new PrjY2G1Song("High School Sweethearts", "5:11")
        })
    };
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice = 0;

        while (choice != 5) {
            displayMenu();
            System.out.print("Enter your choice (1-5): ");

            // Handle invalid input 
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine(); // Clear scanner buffer

                switch (choice) {
                    case 1 ->
                        albums = addAlbum(albums);
                    case 2 ->
                        addSongToAlbum(albums);
                    case 3 ->
                        searchAlbum(albums);
                    case 4 ->
                        listAllAlbums();
                    case 5 ->
                        System.out.println("\nExiting program. Goodbye!");
                    default ->
                        System.out.println("\n[Error] Invalid choice! Please select a menu option between 1 and 5.");
                }
            } else {
                System.out.println("\n[Error] Invalid input type! Please enter a valid integer number.");
                scanner.nextLine();
            }
        }
        scanner.close();
    }

    // Menu interface 
    private static void displayMenu() {
        System.out.println("\n=================================");
        System.out.println("        ALBUM MANAGER MENU       ");
        System.out.println("=================================");
        System.out.println("1. Add Album");
        System.out.println("2. Add Songs to Album");
        System.out.println("3. Search Album");
        System.out.println("4. List All Albums");
        System.out.println("5. Exit Program");
        System.out.println("=================================");
    }

    //Add Album 
    public static PrjY2G1Album[] addAlbum(PrjY2G1Album[] albums) {
        System.out.println("=================================");
        System.out.println("\n\t Add New Album\n");

        String albumName = "";

        while (true) {
            System.out.print("Enter Album Name: ");
            albumName = scanner.nextLine();

            if (albumName.trim().isEmpty()) {
                System.out.println("\nAlbum name cannot be empty. Please try again.\n");
                continue;
            }
            boolean isSame = false;

            for (PrjY2G1Album album : albums) {
                if (album != null && album.getAlbumName().equalsIgnoreCase(albumName)) {
                    isSame = true;
                    break;
                }
            }

            if (isSame) {
                System.out.println("\nAlbum name already exists. Please enter a different Album name.\n");
            } else {
                break;
            }
        }

        String artist = "";

        while (true) {
            System.out.print("Enter Artist Name: ");
            artist = scanner.nextLine();

            if (artist.trim().isEmpty()) {
                System.out.println("\nArtist name cannot be empty. Please try again.\n");
            } else {
                break;
            }

        }

        PrjY2G1Album newAlbum = new PrjY2G1Album(albumName, artist, new PrjY2G1Song[]{});
        System.out.println("Album '" + albumName + "' created successfully.");

        int count = 0;
        for (PrjY2G1Album album : albums) {
            if (album != null) {
                count++;
            }
        }

        if (count == albums.length) {
            System.out.println("\nStorage is full. Resizing Storage...\n");
            PrjY2G1Album[] newAlbums = new PrjY2G1Album[albums.length + 1];
            for (int i = 0; i < albums.length; i++) {
                newAlbums[i] = albums[i];
            }
            newAlbums[newAlbums.length - 1] = newAlbum;
            System.out.println("\nStorage resize complete.\n");
            return newAlbums;
        } else {
            albums[count] = newAlbum;
            return albums;
        }
    }

    //add song to album
    public static void addSongToAlbum(PrjY2G1Album[] albums) {
        if (albums[0] == null) {
            System.out.println("\nError. Please add an album first.");
            return;
        }

        boolean found = false;
        PrjY2G1Album foundAlbum = null;

        while (found == false) {
            System.out.print("\nEnter the name of the album to add a song to: ");
            String targetAlbum = scanner.nextLine();

            for (PrjY2G1Album album : albums) {
                if (album != null && album.getAlbumName().equalsIgnoreCase(targetAlbum)) {
                    foundAlbum = album;
                    found = true;
                    break;
                }
            }

            if (found == false) {
                System.out.println("Album '" + targetAlbum + "' not found.");
            }
        }

        System.out.println("=================================");
        System.out.println("\n\t Add Song to " + foundAlbum.getAlbumName() + "\n");
        System.out.print("Enter Song Name: ");
        String songName = scanner.nextLine();

        String duration = "";
        int ttlSeconds = 0;

        while (true) {
            System.out.print("Enter Song Duration (mm:ss): ");
            duration = scanner.nextLine();

            if (duration.indexOf(':') != duration.lastIndexOf(':')) {
                System.out.println("\nError. Too many colons. Please use the mm:ss format only.\n");
            }

            if (duration.endsWith(":")) {
                duration += "00";
            }

            if (duration.startsWith(":")) {
                duration = "0" + duration;
            }

            if (duration.contains(":")) {

                String[] D_parts = duration.split(":");

                if (D_parts.length == 2) {
                    String isOkMin = D_parts[0].trim();
                    String isOkSec = D_parts[1].trim();

                    if (isOkSec.length() == 1) {
                        isOkSec = isOkSec + "0";
                    }

                    if (isOkMin.length() == 1) {
                        isOkMin = "0" + isOkMin;
                    }

                    boolean isNum = true;

                    for (int i = 0; i < isOkMin.length(); i++) {
                        if (!Character.isDigit(isOkMin.charAt(i))) {
                            isNum = false;
                            System.out.println("\nError. You did not enter a valid integer.\n");
                        }
                    }

                    for (int i = 0; i < isOkSec.length(); i++) {
                        if (!Character.isDigit(isOkSec.charAt(i))) {
                            isNum = false;
                            System.out.println("\nError. You did not enter a valid integer.\n");
                        }
                    }

                    if (isNum && !isOkMin.isEmpty() && !isOkSec.isEmpty()) {
                        int Mins = Integer.parseInt(isOkMin);
                        int Secs = Integer.parseInt(isOkSec);

                        ttlSeconds = (Mins * 60) + Secs;
                        break;
                    }
                }

            } else {
                System.out.println("\nInvalid format. You must include numbers on both sides, like 3:45. Please try again.\n");
            }
        }

        int m = (ttlSeconds % 3600) / 60;
        int s = ttlSeconds % 60;

        duration = String.format("%d:%02d", m, s);

        PrjY2G1Song newSong;

        if (songName.trim().isEmpty()) {
            newSong = new PrjY2G1Song("Unknown  Title", duration);
        } else {
            newSong = new PrjY2G1Song(songName, duration);
        }

        PrjY2G1Song[] currentSongs = foundAlbum.getSongs();
        PrjY2G1Song[] updatedSongs = new PrjY2G1Song[currentSongs.length + 1];

        for (int i = 0; i < currentSongs.length; i++) {
            updatedSongs[i] = currentSongs[i];
        }

        updatedSongs[updatedSongs.length - 1] = newSong;
        foundAlbum.setSongs(updatedSongs);

        if (songName.trim().isEmpty()) {
            System.out.println("Song 'Unknown Title' " + duration + " added successfully");
        } else {
            System.out.println("Song '" + songName + "' " + duration + " added successfully.");
        }

    }

//Search Album 
    public static void searchAlbum(PrjY2G1Album albums[]) {
        String albumList = "";
        int number = 0;

        System.out.print("=================================\n\n\tSearch Album\n\nSearch: ");
        String input = scanner.nextLine().toLowerCase();
        for (PrjY2G1Album album : albums) {
            if (album.getAlbumName().toLowerCase().contains(input)) {
                number++;
                albumList += "\n" + number + ". " + album.showAlbum();
            }
        }

        System.out.println(albumList.length() > 0 ? albumList : "The album '" + input + "' does not exist.");
    }

//List All Albums 
    private static void listAllAlbums() {
        System.out.println("=================================\n\tList All Albums\n");

        System.out.printf("%-25s %-25s %15s %15s\n", "Album Name", "Artist", "Total Duration", "Total Songs");
        System.out.println("----------------------------------------------------------------------------------");

        for (PrjY2G1Album album : albums) {
            if (album != null) {
                System.out.printf("%-25s %-25s %15s %15d\n",
                        album.getAlbumName(),
                        album.getArtist(),
                        album.getAlbumDuration(),
                        album.getNumberOfSongs()
                );
            }
        }
    }
}
