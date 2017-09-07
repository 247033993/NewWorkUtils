package com.paic.claim.icloud.common.utils;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.annotation.IdRes;

import com.paic.crm.sdk.ucmcore.utils.SoundUtil;

import java.io.IOException;

/**
 * 创建时间: 17/8/18
 * 编写人：HBB
 * 描述：响铃
 */

public class SoundUtils {
    private Context ctx;
    private MediaPlayer mp;
    private static SoundUtils instance = null;
    private boolean isAllowPlay = true;
    private boolean playing = false;
    private int resourceId;

    private SoundUtils() {
    }

    public static SoundUtils getInstance() {
        if(null == instance) {
            instance = new SoundUtils();
        }

        return instance;
    }

    public void build(Context ctx, @IdRes int resourceId) {
        this.ctx = ctx;
        this.resourceId = resourceId;
    }

    private MediaPlayer initMedia() {
        if(this.ctx == null) {
            throw new NullPointerException();
        } else {
            MediaPlayer mediaPlayer = MediaPlayer.create(this.ctx, this.resourceId);
            return mediaPlayer;
        }
    }

    public void release() {
        if(null != this.mp) {
            this.mp.release();
        }

    }

    public void playVoice() {
        (new Thread(new Runnable() {
            public void run() {
                SoundUtils.this.mp = SoundUtils.this.initMedia();
                if(SoundUtils.this.isAllowPlay) {
                    if(SoundUtils.this.mp != null) {
                        SoundUtils.this.mp.stop();
                    }

                    try {
                        SoundUtils.this.mp.prepare();
                    } catch (IOException var2) {
                        var2.printStackTrace();
                    }

                    SoundUtils.this.mp.start();
                    SoundUtils.this.mp.setLooping(true);
                    SoundUtils.this.playing = true;
                }
            }
        })).start();
    }

    public void stopVoice() {
        this.playing = false;
        this.mp.release();
    }

    public void pauseVoice() {
        if(this.mp != null && this.mp.isPlaying()) {
            this.mp.stop();
        }

    }

    public void setIsAllowPlay(boolean isAllowPlay) {
        this.isAllowPlay = isAllowPlay;
    }

    public boolean isPlaying() {
        return this.playing;
    }

    public void startAlarm(Context context) {
        MediaPlayer mediaPlayer = MediaPlayer.create(context, this.getSystemDefultRingtoneUri(context));
        mediaPlayer.setLooping(false);

        try {
            mediaPlayer.prepare();
        } catch (IllegalStateException var4) {
            var4.printStackTrace();
        } catch (IOException var5) {
            var5.printStackTrace();
        }

        mediaPlayer.start();
    }

    private Uri getSystemDefultRingtoneUri(Context context) {
        return RingtoneManager.getActualDefaultRingtoneUri(context, 2);
    }
}
