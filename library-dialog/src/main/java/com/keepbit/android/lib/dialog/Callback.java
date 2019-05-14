package com.keepbit.android.lib.dialog;

import android.os.Bundle;

/**
 * Created by CoderMario on 2019-05-13.
 */
interface Callback {

    /****
     * */
    void onShowAnimatorStarted(Bundle bundle);

    /****
     * */
    void onShowAnimatorCompleted(Bundle bundle);

    /****
     * */
    void onHideAnimatorStarted(Bundle bundle);

    /****
     * */
    void onHideAnimatorCompleted(Bundle bundle);
}
