package screens;

import java.io.IOException;

import android.media.MediaRecorder;

public class MicThread implements Runnable{

//	private short[] SAMPLES = new short[1024];
	MediaRecorder recorder;
	//private AudioDevice player;
	int amplitude = 0;
	public MicThread(){
		recorder = new MediaRecorder();
		setup();
		try {
			recorder.prepare();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void setup(){
		recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
		recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        recorder.setOutputFile("/dev/null"); 
	}
	public int getVolume(){
		return amplitude;
	}
	public void stop(){
//		recorder.stop();
		amplitude = recorder.getMaxAmplitude();
		//recorder.reset();
//		setup();
//		recorder.start();
	}
	@Override
	public void run() {
		amplitude = 0;
		
		recorder.start();
//		recorder = Gdx.audio.newAudioRecorder(22050, true);
//		recorder.read(SAMPLES, 0, SAMPLES.length);
//		recorder.dispose();
//		player = Gdx.audio.newAudioDevice(22050, true);
//		player.writeSamples(SAMPLES, 0, SAMPLES.length);
//		player.dispose();
	}

}
