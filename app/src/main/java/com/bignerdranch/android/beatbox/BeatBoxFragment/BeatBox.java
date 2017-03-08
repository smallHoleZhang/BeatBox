package com.bignerdranch.android.beatbox.BeatBoxFragment;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.widget.Toast;

import com.bignerdranch.android.beatbox.BaetBoxActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hasee on 2017/3/6.
 */

public class BeatBox {
    private static final  String TAG = "BeatBox";

    private static final  String SOUND_FOLDER = "simple_sounds";

    private AssetManager mAssetManager;

    private List<Sound> mSounds = new ArrayList<>();

    private static final int MAX_SOUND = 5 ;

    private SoundPool.Builder mSoundPoolBuidle;

    private SoundPool mSoundPool;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public BeatBox(Context context)
    {
        mAssetManager = context.getAssets();
        mSoundPool = new SoundPool(MAX_SOUND, AudioManager.STREAM_MUSIC, 0);
        loadSounds();
    }
    private  void loadSounds()
    {
        Log.d(TAG, "loadSounds: ");
        String [] soundNames;
        try {
            soundNames = mAssetManager.list(SOUND_FOLDER);
            Log.i(TAG,"Found" + soundNames.length + "sounds");
        } catch (IOException e) {
            Log.i(TAG, "Could not list assets  ",e);
            return;
        }
        for(String filename : soundNames)
        {
            try {
                String assetPath =SOUND_FOLDER  + "/" +filename;
                Sound sound = new Sound(assetPath);
                Load(sound);
                mSounds.add(sound);

            } catch (IOException e)
            {
                Log.d(TAG, "Could not load sound " + filename,e);
            }

        }

    }
    public void Load(Sound sound)throws IOException
    {
        AssetFileDescriptor adf = mAssetManager.openFd(sound.getAssetPath());
        int soundId = mSoundPool.load(adf,1);
        sound.setSoundId(soundId);
    }
    public  List<Sound> getSounds()
    {
        return mSounds;
    }

    public void play(Sound sound)
    {
        Integer soundId = sound.getSoundId();
        if(soundId == null)
        {  Log.d(TAG, "没声 ");
            return;

        }
        mSoundPool.play(soundId,1.0f,1.0f,1,0,1.0f);
     }

}
