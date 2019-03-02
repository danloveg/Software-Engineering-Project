package comp3350.breadtunes.tests.business;

import android.content.Context;

import org.junit.Before;
import org.junit.Test;
import static org.mockito.ArgumentMatchers.*;

import java.util.ArrayList;
import java.util.List;

import comp3350.breadtunes.business.MediaPlayerController;
import comp3350.breadtunes.business.MusicPlayerState;
import comp3350.breadtunes.business.interfaces.MediaManager;
import comp3350.breadtunes.objects.Song;
import comp3350.breadtunes.tests.mocks.MockSongs;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class MediaPlayerControllerTest
{
    MediaPlayerController testTarget;
    MusicPlayerState appState;
    List<Song> mockSongList = new ArrayList<>();
    Context context = mock(Context.class);
    MediaManager mockManager = mock(MediaManager.class);

    @Before
    public void setup()
    {
        mockSongList = MockSongs.getMockSongList();
        appState = new MusicPlayerState(mockSongList);
        testTarget = new MediaPlayerController(context, appState, mockManager);
    }

    @Test
    public void playExistingSongTest()
    {
        System.out.print("\nStarting playSongTest");

        testTarget.playSong(mockSongList.get(0), 1);
        verify(mockManager, times(1)).startPlayingSong(context, 1);

        System.out.println("\nFinished playSongTest");
    }

    @Test
    public void playNonexistentSongTest() {
        System.out.print("\nStarting playNonexistentSongTest");

        testTarget.playSong(new Song.Builder().build(), 0);
        verify(mockManager, times(0)).startPlayingSong(any(Context.class), anyInt());

        System.out.println("\nFinished playNonexistentSongTest");
    }

    @Test
    public void pauseNoPlayingSongTest()
    {
        System.out.print("\nStarting pauseNoPlayingSongTest");

        testTarget.pauseSong();
        verify(mockManager, times(0)).pausePlayingSong();

        System.out.println("\nFinished pauseNoPlayingSongTest");
    }

    @Test
    public void pausePlayingSongTest()
    {
        System.out.print("\nStarting pauseNoPlayingSongTest");

        testTarget.playSong(mockSongList.get(0), 1);
        testTarget.pauseSong();
        verify(mockManager, times(1)).pausePlayingSong();

        System.out.println("\nFinished pauseNoPlayingSongTest");
    }

    @Test
    public void playNextSongNothingPlayingTest() {
        System.out.print("\nStarting playNextSongNothingPlayingTest");

    }

    /*
    @Test
    public void playNextSongTest()
    {
        System.out.print("\nStarting playNextSongTest");

        //case we are not playing
        assertEquals("no song currently playing", testTarget.playNextSong(context));
        verify(mockManager,times(0)).startPlayingSong(context, 0);

        //case we are last on the list and no next song in the list
        testTarget.playSong(mockSongList.get(1),1);
        assertEquals("no next song", testTarget.playNextSong(context));
        verify(mockManager,times(0)).startPlayingSong(context, 0);

        System.out.println("\nEnding playNextSongTest");
    }// playNextSongTest

    @Test
    public void playPreviousSongTest()
    {
        System.out.print("\nStarting playPreviousSongTest");

        //case we are not playing
        assertEquals("no song currently playing", testTarget.playPreviousSong(context));
        verify(mockManager,times(0)).startPlayingSong(context, 0);

        //case we are first on the list and no previous song in the list
        testTarget.playSong(mockSongList.get(0),1);
        assertEquals("no previous song", testTarget.playPreviousSong(context));
        verify(mockManager,times(0)).startPlayingSong(context, 0);

        System.out.println("\nEnding playPreviousSongTest");
    }// playPreviousSongTest
    */

    @Test
    public void resumeSongTest() {
        System.out.print("\nStarting resumeSongTest");

        testTarget.playSong(mockSongList.get(0), 1);
        testTarget.pauseSong();
        testTarget.resumeSong(1);
        verify(mockManager, times(1)).resumePlayingSong();

        System.out.println("\nEnding resumeSongTest");
    }

    @Test
    public void releaseMediaPlayerTest()
    {
        System.out.print("\nreleaseMediaPlayerTest");

        testTarget.releaseMediaPlayer();
        verify(mockManager,times(1)).close();

        System.out.println("\nreleaseMediaPlayerTest");
    }
}
