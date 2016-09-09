package cn.tuofeng;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;

import android.media.MediaRecorder;
import android.media.MediaPlayer;
import android.util.Log;

public class RNAudioRecordModule extends ReactContextBaseJavaModule {

  private static final String DURATION_SOURCE_MIC_KEY = "SOURCE_MIC";
  private static final String DURATION_FORMAT_THREE_GPP_KEY = "THREE_GPP";
  private static final String DURATION_ENCODER_AMR_NB_KEY = "AMR_NB";
  
  private static final String LOG_TAG = "AudioRecordModule";
  
  private MediaRecorder mRecorder = null;
  private MediaPlayer   mPlayer = null;
  
  //private static String mFileName = null;

  public RNAudioRecordModule(ReactApplicationContext reactContext) {
    super(reactContext);
  }
  
  @Override
  public String getName() {
    return "RNAudioRecord";
  }
  
  /*@Override
  public Map<String, Object> getConstants() {
    final Map<String, Object> constants = new HashMap<>();
    constants.put(DURATION_SOURCE_MIC_KEY, MediaRecorder.AudioSource.MIC);
    constants.put(DURATION_FORMAT_THREE_GPP_KEY, MediaRecorder.OutputFormat.THREE_GPP);
    constants.put(DURATION_ENCODER_AMR_NB_KEY, MediaRecorder.AudioEncoder.AMR_NB);
    return constants;
  }*/
  
  @ReactMethod
  public void startRecording(String mFileName, Callback cb) {
    try {
      mRecorder = new MediaRecorder();
      mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
      mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
      mRecorder.setOutputFile(mFileName);
      mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
      mRecorder.prepare();
      mRecorder.start();
      cb.invoke();
    } catch (Exception e) {
      Log.e(LOG_TAG, "prepare() failed");
      cb.invoke(e.getMessage());
    }
  }
  
  @ReactMethod
  public void stopRecording(Callback cb) {
    mRecorder.stop();
    mRecorder.release();
    mRecorder = null;
    cb.invoke();
  }
  
  @ReactMethod
  public void startPlaying(String mFileName, Callback cb) {
    mPlayer = new MediaPlayer();
    try {
      mPlayer.setDataSource(mFileName);
      mPlayer.prepare();
      mPlayer.start();
      cb.invoke();
    } catch (Exception e) {
      Log.e(LOG_TAG, "prepare() failed");
      cb.invoke(e.getMessage());
    }
  }

  @ReactMethod
  public void stopPlaying(Callback cb) {
    mPlayer.release();
    mPlayer = null;
    cb.invoke();
  }

  @ReactMethod
  public void getDuration(String mFileName, Callback cb) {
    mPlayer = new MediaPlayer();
    try {
      mPlayer.setDataSource(mFileName);
      mPlayer.prepare();
      final int duration = mPlayer.getDuration();
      mPlayer.release();
      mPlayer = null;
      cb.invoke(null, duration);
    } catch (Exception e) {
      Log.e(LOG_TAG, "prepare() failed");
      cb.invoke(e.getMessage());
    }
  }

}
