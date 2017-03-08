package com.bignerdranch.android.beatbox.BeatBoxFragment;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.bignerdranch.android.beatbox.R;

import java.util.List;

/**
 * Created by hasee on 2017/3/6.
 */

public class BeatBoxFragment extends Fragment {
    private BeatBox mBeatBox;
    private static   String TAG = "BeatBoxFragment";

     public static BeatBoxFragment newInstance()

    {
        Log.d(TAG, "onCreate: ");
        return  new BeatBoxFragment();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
        mBeatBox =  new BeatBox(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_beat_box,container,false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.fragment_beat_box_recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),3));
        recyclerView.setAdapter( new  SoundAdapter(mBeatBox.getSounds()));
        return view;
    }
    public class SoundHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
            private Button mButton;
            private Sound mSound;


        public SoundHolder(LayoutInflater layoutInflater,ViewGroup container) {
            super(layoutInflater.inflate(R.layout.list_item_sound,container,false));
            mButton = (Button) itemView.findViewById(R.id.list_item_sound_button);
            mButton.setOnClickListener(this);
        }
        public   void bingSound(Sound sound)
        {
            mSound = sound;
            mButton.setText(mSound.getName());
        }

        @Override
        public void onClick(View v) {
            mBeatBox.play(mSound);

        }
    }
    public  class SoundAdapter extends RecyclerView.Adapter<SoundHolder>
    {
        private List<Sound> mSounds;
        public SoundAdapter(List<Sound> sounds)
        {
            mSounds =sounds;
        }


        @Override
        public SoundHolder onCreateViewHolder(ViewGroup parent, int viewType) {
           LayoutInflater inflater = LayoutInflater.from(getActivity());
            return  new SoundHolder(inflater,parent);
        }

        @Override
        public void onBindViewHolder(SoundHolder holder, int position) {
            Sound sound = mSounds.get(position);
            holder.bingSound(sound);


        }


        @Override
        public int getItemCount() {
            return mSounds.size();
        }
    }

}
