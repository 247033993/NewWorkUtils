package com.paic.claim.icloud.common.utils;

import android.app.Service;
import android.content.Context;
import android.os.Vibrator;

/**
 * 创建时间: 17/8/18
 * 编写人：HBB
 * 描述：振动
 */

public class VibratorUtils {
    /**
     * 振动
     * @param context
     * @param vibrateTime 振动时常
     */
    public static void vibrate(Context context, long vibrateTime) {
        Vibrator vib = (Vibrator)context.getSystemService(Service.VIBRATOR_SERVICE);
        vib.vibrate(vibrateTime);
    }
}
