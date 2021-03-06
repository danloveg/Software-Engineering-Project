package comp3350.breadtunes.business;

import java.util.ArrayList;
import java.util.List;

import comp3350.breadtunes.objects.Song;

public class LookUpSongs {
    MusicPlayerState mps;

    public LookUpSongs(MusicPlayerState mps) {
        this.mps = mps;
    }

    public List<Song> searchSongs(String input) {
        List<Song> matchingSongs = new ArrayList<>();

        List<Song> currentSongList = mps.getCurrentSongList();

        for (int i = 0; i < currentSongList.size(); i++) {
            Song ss = currentSongList.get(i);

            if (input.length() != 0 && ss.getName().toUpperCase().contains(input.toUpperCase())) {
                matchingSongs.add(ss);
            }
        }

        return matchingSongs;
    }

    // DO NOT REMOVE, it is used; talk to mario if questions
    public static Song getSong(List<Song> songList, String songName){
        for (Song song: songList) {
            if (song.getName().equals(songName)) {
                return song;
            }
        }
        return null;
    }
}
