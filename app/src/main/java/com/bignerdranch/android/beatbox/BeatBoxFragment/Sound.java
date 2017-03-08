package com.bignerdranch.android.beatbox.BeatBoxFragment;

import android.content.Intent;

/**
 * Created by hasee on 2017/3/6.
 */

public class Sound {
    private  String mAssetPath;
    private  String mName;
    private Integer mSoundId;
    public  Sound(String assetPath)
    {
        mAssetPath = assetPath;
        String [] comonpents = assetPath.split("/");
        String filename = comonpents[comonpents.length -1];
        mName = filename.replace(".wav","");


    }

    public Integer getSoundId() {
        return mSoundId;
    }

    public void setSoundId(Integer soundId) {
        mSoundId = soundId;
    }

    public String getAssetPath() {
        return mAssetPath;
    }

    public String getName() {
        return mName;
    }
}
