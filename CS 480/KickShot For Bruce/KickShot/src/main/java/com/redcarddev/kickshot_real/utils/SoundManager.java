package com.redcarddev.kickshot_real.utils;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

import java.util.HashMap;

/**
 * Created by Rob Kleffner on 2/11/14.
 */
public class SoundManager {
    private static SoundManager _instance = null;
    private final int MAX_STREAMS = 10;
    private SoundPool _soundPool;
    private AudioManager _audioManager;
    private HashMap<String, Integer> _soundMap;
    private Activity _owner = null;

    public static SoundManager Instance() {
        if (_instance == null) {
            _instance = new SoundManager();
        }
        return _instance;
    }

    private SoundManager() {
        _soundPool = new SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC, 0);
        _soundMap = new HashMap<String, Integer>();
    }

    public void SetOwner(Activity a) {
        _owner = a;
        _audioManager = (AudioManager)_owner.getSystemService(Context.AUDIO_SERVICE);
    }

    public void LoadSound(String name, int resourceId) throws Exception {
        if (_owner == null) {
            throw new Exception("Can't load sounds without an owner specified. Call SetOwner before calling load.");
        }
        _soundMap.put(name, _soundPool.load(_owner, resourceId, 1));
    }

    public void PlaySound(String name) {
        float streamVolume = _audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        streamVolume = streamVolume / _audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        if (_soundMap.containsKey(name)) {
            _soundPool.play(_soundMap.get(name), streamVolume, streamVolume, 1, 0, 1);
        }
    }
}