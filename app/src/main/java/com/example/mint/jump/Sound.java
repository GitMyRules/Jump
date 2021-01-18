package com.example.mint.jump;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;

/**
 * Created by mint on 27.02.18.
 */

public class Sound extends MediaPlayer{

    private MediaPlayer mainSound;
    private MediaPlayer startSound;
    private MediaPlayer ballHit;
    private MediaPlayer evilballHit;

    public Sound(Context context) {
        startSound= MediaPlayer.create(context, R.raw.sound);
        mainSound= MediaPlayer.create(context, R.raw.dragonballmain);
        ballHit= MediaPlayer.create(context, R.raw.ballhit);
        evilballHit= MediaPlayer.create(context, R.raw.evilwave);
        startSound.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mainSound.setAudioStreamType(AudioManager.STREAM_MUSIC);
        ballHit.setAudioStreamType(AudioManager.STREAM_MUSIC);
        evilballHit.setAudioStreamType(AudioManager.STREAM_MUSIC);

    }

    public void playSound() {
        startSound.start();
    }
    public void mainSound() {
        mainSound.start();
    }

    public void stopPlaySound() { startSound.stop(); }
    public void stopMainSound() {
        mainSound.stop();
    }

    public void playBallSound() {
        ballHit.start();
    }
    public void stopBallSound() {
        ballHit.stop();
    }


    public void playEvilBallSound() { evilballHit.start(); }
    public void stopEvilBallSound() {
        evilballHit.stop();
    }

}
