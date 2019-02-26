package comp3350.breadtunes.presentation;
import android.content.Context;
import android.media.MediaPlayer;

import comp3350.breadtunes.business.MusicPlayerState;
import comp3350.breadtunes.business.interfaces.MediaManager;
import comp3350.breadtunes.objects.Song;

// Class that controls the playing, pausing, and playing next/previous
public class MediaPlayerController{
    private MediaManager mediaManager;
    private Context context;
    private MusicPlayerState appState;

    public MediaPlayerController(Context context, MusicPlayerState appState, MediaManager mediaManager){
        this.mediaManager = mediaManager;
        this.context = context;
        this.appState = appState;
    }

    //plays a song, returns a string "succesful" or "failed to find resource" so that activity that calls this metjod can display toast message
    public String playSong(Song song, int resourceId){

        String response;
        if(resourceId == 0){
            response = "Failed to find resource";
        }else{
            if(mediaManager != null && mediaManager.isPlaying()) {
                mediaManager.stopPlayingSong();
            }

            mediaManager.startPlayingSong(context, resourceId);
            mediaManager.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                public void onCompletion(MediaPlayer mediaPlayer) {
                    //get reference to next from app state
                    Song nextSong = appState.getNextSong();
                    if(nextSong != null){
                        int songId = context.getResources().getIdentifier(nextSong.getRawName(), "raw", context.getPackageName());
                        playSong(nextSong, songId);
                    }
                }
            });

            appState.setCurrentSong(song);  //update the state of the music player!
            appState.setIsSongPlaying(true);
            appState.setIsSongPaused(false);
            response = "Playing "+song.getName();
        }
        return response;
    }



    //pause a song, and return a string so that main activity can display toast message with information
    public String pauseSong(){
        String response;
        //check first if a song is playing
        if(appState.isSongPlaying()){
            if(mediaManager != null){ //make sure the media player object is not in idle state
                appState.setPausedPosition(mediaManager.getCurrentPosition()); // save the timestamp in the state so we can resume where we left off
                mediaManager.pausePlayingSong();

                response = "paused song " + appState.getCurrentlyPlayingSong().getName();
                appState.setIsSongPaused(true); //update the state of the music player
                appState.setIsSongPlaying(false);
            }else{
                response = "Can not pause, no song playing";
            }
        }else{
            response = "Can not pause, no song playing";
        }
        return response;
    }


    // WHAT TO DO WHEN THE CURRENT SONG PLAYING FINISHES
    //https://developer.android.com/reference/android/media/MediaPlayer.OnCompletionListener



    // play next song button
    public String playNextSong(Context context){
        String response;
        if(appState.getCurrentlyPlayingSong() != null && appState.getCurrentSongList()!= null){ //make sure there is a song playing
            Song nextSong = appState.getNextSong();

            if(nextSong != null) {
                int nextSongId = context.getResources().getIdentifier(nextSong.getRawName(), "raw", context.getPackageName());    //get the resource pointer
                playSong(nextSong, nextSongId); //play the new song
                response = "playing " + nextSong.getName();
            }else{
                response = "no next song";
            }
        }else{
            response = "no song currently playing";
        }
        return response;
    }

    //play previous song button
    public String playPreviousSong(Context context){
        String response;
        if(appState.getCurrentlyPlayingSong() != null && appState.getCurrentSongList()!= null){ //make sure there is a song playing
            Song previousSong = appState.getPreviousSong();

            if(previousSong != null) {
                int nextSongId = context.getResources().getIdentifier(previousSong.getRawName(), "raw", context.getPackageName());    //get the resource pointer
                playSong(previousSong, nextSongId); //play the new song
                response = "playing " + previousSong.getName();
            }else{
                response = "no previous song";
            }
        }else{
            response = "no song currently playing";
        }
        return response;

    }

    // resume the playing of a song
    public String resumeSong(int resourceId){
     String response;
        try{
            if(appState.isSongPaused()) {
                mediaManager.resumePlayingSong();

                //update app state!
                appState.setIsSongPaused(false);
                appState.setIsSongPlaying(true);

                response = "Response successful";
            }else{
                response = "Can not resume , no song is paused";
            }
        }catch(Exception e){
            response = e.toString();
        }
        return response;
    }


    //called in OnDestroy() to free up the media player, just housekeeping
    public void releaseMediaPlayer(){
        mediaManager.close();
    }
}//media player controller class