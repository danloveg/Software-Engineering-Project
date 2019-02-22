package comp3350.breadtunes.tests.business;

import org.junit.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import comp3350.breadtunes.business.Utilities;
import comp3350.breadtunes.business.MusicPlayerState;
import comp3350.breadtunes.objects.Album;
import comp3350.breadtunes.objects.Artist;
import comp3350.breadtunes.objects.Song;
import comp3350.breadtunes.objects.SongDuration;

import static org.junit.Assert.*;

public class UtilitiesTest {
    ArrayList<Song> mockSongList = new ArrayList<>();
    Utilities testTarget;

    @Before
    public void setup() {
        // Populate mock list of songs
        mockSongList.add(new Song("Bloch Prayer",1, new SongDuration(0, 0),
                new Artist("Artist 1", new ArrayList<Album>()),
                new Album("Album 1", new ArrayList<Song>()), "res/raw/prayer.mp3"));
        mockSongList.add(new Song("Haydn Adagio", 1, new SongDuration(0, 0),
                new Artist("Artist 2", new ArrayList<Album>()),
                new Album("Album 2", new ArrayList<Song>()), "res/raw/adagio.mp3"));
        mockSongList.add(new Song("Tchaikovsky Nocturne", 1, new SongDuration(0, 0),
                new Artist("Artist 3", new ArrayList<Album>()),
                new Album("Album 3", new ArrayList<Song>()), "res/raw/nocturne.mp3"));

        testTarget = new Utilities();
    }

    @Test
    public void getSongTest() {
        System.out.println("\nStarting getSongTest");

        // Act
        Song song = testTarget.getSong(mockSongList,"Bloch Prayer");

        // Assert
        assertNotNull(song);
        assertTrue(song.getName().equals("Bloch Prayer"));
        assertTrue(song.getArtist().getName().equals("Artist 1"));

        System.out.println("Finished getSongTest");
    }

    @Test
    public void getSongDoesNotExistTest() {
        System.out.println("\nStarting getSongDoesNotExistTest");

        // Act
        Song song = testTarget.getSong(mockSongList,"Highway to Hell");

        // Assert
        assertNull(song);

        System.out.println("Finished getSongDoesNotExistTest");
    }

    @Test
    public void getSongNamesTest() {
        System.out.println("\nStarting getSongNamesTest");

        // Arrange
        Set<String> results = new HashSet<>(3);

        // Act
        String[] songNames = testTarget.getSongNames(mockSongList);
        for (String songName: songNames) { results.add(songName); }

        // Assert
        assertTrue(results.size() == 3);
        assertTrue(results.contains("Bloch Prayer"));
        assertTrue(results.contains("Haydn Adagio"));
        assertTrue(results.contains("Tchaikovsky Nocturne"));

        System.out.println("Finished getSongNamesTest");
    }
}
