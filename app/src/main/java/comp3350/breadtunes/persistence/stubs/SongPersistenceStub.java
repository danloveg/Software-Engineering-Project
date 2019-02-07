package comp3350.breadtunes.persistence.stubs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import comp3350.breadtunes.exception.RecordDoesNotExistException;
import comp3350.breadtunes.objects.Album;
import comp3350.breadtunes.objects.Artist;
import comp3350.breadtunes.objects.Song;
import comp3350.breadtunes.objects.SongDuration;
import comp3350.breadtunes.persistence.SongPersistence;

public class SongPersistenceStub implements SongPersistence {
    private List<Song> songs;

    public SongPersistenceStub() {
        this.songs = new ArrayList<>();

        songs.add(
                new Song("Bloch Prayer","prayer", 1, new SongDuration(0, 0),
                        new Artist("Artist 1", new ArrayList<Album>()),
                                new Album("Album 1", new ArrayList<Song>()), "res/raw/prayer.mp3"));
        songs.add(
                new Song("Haydn Adagio","adagio", 1, new SongDuration(0, 0),
                        new Artist("Artist 2", new ArrayList<Album>()),
                        new Album("Album 2", new ArrayList<Song>()), "res/raw/adagio.mp3"));
        songs.add(
                new Song("Tchaikovsky Nocturne", "nocturne",1, new SongDuration(0, 0),
                        new Artist("Artist 3", new ArrayList<Album>()),
                        new Album("Album 3", new ArrayList<Song>()), "res/raw/nocturne.mp3"));
        songs.add(
                new Song("Jaraba Tapatio", "jarabe",1, new SongDuration(0, 0),
                        new Artist("Mariachi", new ArrayList<Album>()),
                        new Album("Album 3", new ArrayList<Song>()), "res/raw/nocturne.mp3"));
    }

    @Override
    public List<Song> getAll() {
        return Collections.unmodifiableList(songs);
    }

    @Override
    public Song insert(Song newSong) {
        // Not checking for duplicates
        songs.add(newSong);
        return newSong;
    }

    @Override
    public Song update(Song updateSong) {
        int index = songs.indexOf(updateSong);

        if (index < 0) {
            throw new RecordDoesNotExistException(String.format("Trying to update nonexistent song (name: %s).", updateSong.getName()));
        }

        songs.set(index, updateSong);

        return updateSong;
    }

    @Override
    public void delete(Song deleteSong) {
        int index = songs.indexOf(deleteSong);

        if (index < 0) {
            throw new RecordDoesNotExistException(String.format("Tying to delete nonexistent song (name: %s)", deleteSong.getName()));
        }

        songs.remove(index);
    }

    //return the raw song name for the given song
    public Song getSong(String songName){

        for(int i=0; i< songs.size(); i++){
            Song temp = songs.get(i);
            if(temp.getName().equals(songName)){
                return temp;
            }
        }
        return null;
    }
}