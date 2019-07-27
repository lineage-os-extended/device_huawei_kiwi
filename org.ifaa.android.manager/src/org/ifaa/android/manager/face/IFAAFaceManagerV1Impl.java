package org.ifaa.android.manager.face;

import android.content.Context;
import android.util.Log;
import org.ifaa.android.manager.face.IFAAFaceManager.AuthenticatorCallback;

public class IFAAFaceManagerV1Impl extends IFAAFaceManagerV1 {
    private static final int DEFAULT_FLAG = 1;
    private static final int FACE_AUTH_VERSION_V1 = 1;
    private static final int IFAA_OP_FAIL = -1;
    private static final int IFAA_OP_SUCCESS = 0;
    private static final String TAG = "IFAAFaceManagerV1Impl";

    public IFAAFaceManagerV1Impl(Context context) {
        IFAAFaceRecognizeManager.createInstance(context);
    }

    public void authenticate(int reqID, int flag, AuthenticatorCallback callback) {
        IFAAFaceRecognizeManager frManager = IFAAFaceRecognizeManager.getInstance();
        if (frManager == null) {
            Log.e(TAG, "IFAAFaceRecognizeManager is null");
        } else if (callback == null) {
            Log.e(TAG, "callback empty");
        } else {
            frManager.setAuthCallback(callback);
            String str = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("reqID is ");
            stringBuilder.append(reqID);
            stringBuilder.append("flag is ");
            stringBuilder.append(flag);
            Log.i(str, stringBuilder.toString());
            int ret = frManager.init();
            if (ret != 0) {
                String str2 = TAG;
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append("init failed returning ");
                stringBuilder2.append(ret);
                Log.e(str2, stringBuilder2.toString());
                return;
            }
            Log.i(TAG, "authenicating... ");
            IFAAFaceRecognizeManager.getFRManager().authenticate(reqID, 1, null);
        }
    }

    public int cancel(int reqID) {
        Log.i(TAG, "canceling...");
        if (IFAAFaceRecognizeManager.getInstance() == null) {
            Log.e(TAG, "IFAAFaceRecognizeManager is null");
            return -1;
        }
        IFAAFaceRecognizeManager.getFRManager().cancelAuthenticate(reqID);
        return 0;
    }

    public int getVersion() {
        return 1;
    }
}
