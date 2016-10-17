package pro.android.graphics;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Surface;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.VideoView;
import android.view.WindowManager;
public class MainActivity extends Activity {
	AnimationDrawable frameAnimation;
	AnimationSet pagAnimationSet = new AnimationSet(true);
	Animation scaleZeroToFullAnimation;
	Animation alphaZeroToFullAnimation;
	Animation rotateZeroToFullAnimation;
	ProgressDialog downloadProgress;
	VideoView splashScreen;
	Uri splashScreenUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        splashScreen = (VideoView)findViewById(R.id.splashScreenVideoView);        
        setUpAnimation();
        downloadProgress = new ProgressDialog(this);
        downloadProgress.setTitle("Terragen 3 Virtual World Fly-Through Video");
        downloadProgress.setMessage("Downloading Video from Media Server...");
        downloadProgress.setCancelable(false);
        downloadProgress.setIndeterminate(false);
        downloadProgress.show();
        Display rotationDegrees = ((WindowManager)getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        switch(rotationDegrees.getRotation()){
        case(Surface.ROTATION_0):
        	splashScreenUri = Uri.parse("HTTP://www.e-bookclub.com/pag480x800portrait.mp4");
            break;
        case(Surface.ROTATION_90):
        	splashScreenUri = Uri.parse("HTTP://www.e-bookclub.com/pag800x480landscape.mp4");
            break;        
        case(Surface.ROTATION_180):
        	splashScreenUri = Uri.parse("HTTP://www.e-bookclub.com/pag480x800portrait.mp4");
            break;
        case(Surface.ROTATION_270):
        	splashScreenUri = Uri.parse("HTTP://www.e-bookclub.com/pag800x480landscape.mp4");
            break;        
        }
        splashScreen.setVideoURI(splashScreenUri);
        splashScreen.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
			@Override
			public void onPrepared(MediaPlayer splashScreenMediaPlayer) {
				splashScreenMediaPlayer.setLooping(true);
				downloadProgress.dismiss();
				splashScreen.start();
			} });     
    }
    private void setUpAnimation() {
        final ImageView pag = (ImageView) findViewById(R.id.pagImageView);
        pag.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View arg0) {
		        pag.setImageDrawable(frameAnimation);
				pag.startAnimation(pagAnimationSet);		        
				frameAnimation.start();
			}});        
        pag.setOnLongClickListener(new View.OnLongClickListener(){
			@Override
			public boolean onLongClick(View arg0) {
				frameAnimation.stop();
				pag.setImageDrawable(getResources().getDrawable(R.drawable.pag0));
				return true;
			}});
        alphaZeroToFullAnimation = new AlphaAnimation(0, 1);
       	alphaZeroToFullAnimation.setDuration(5000);        
       	scaleZeroToFullAnimation = new ScaleAnimation(0, 1, 0, 1,
       			Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
       	scaleZeroToFullAnimation.setDuration(5000);
       	rotateZeroToFullAnimation = new RotateAnimation(0, 360,
       			Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
       	rotateZeroToFullAnimation.setDuration(5000);       	
        pagAnimationSet.addAnimation(scaleZeroToFullAnimation);
        pagAnimationSet.addAnimation(alphaZeroToFullAnimation);
        pagAnimationSet.addAnimation(rotateZeroToFullAnimation);
        pagAnimationSet.setStartOffset(250);
        pagAnimationSet.setInterpolator(new AccelerateInterpolator());        
       	frameAnimation = new AnimationDrawable();
       	frameAnimation.addFrame(getResources().getDrawable(R.drawable.pag0), 111);
       	frameAnimation.addFrame(getResources().getDrawable(R.drawable.pag1), 111);
    	frameAnimation.addFrame(getResources().getDrawable(R.drawable.pag2), 111);
    	frameAnimation.addFrame(getResources().getDrawable(R.drawable.pag3), 111);
    	frameAnimation.addFrame(getResources().getDrawable(R.drawable.pag4), 111);
    	frameAnimation.addFrame(getResources().getDrawable(R.drawable.pag5), 111);
    	frameAnimation.addFrame(getResources().getDrawable(R.drawable.pag6), 111);
    	frameAnimation.addFrame(getResources().getDrawable(R.drawable.pag7), 111);
    	frameAnimation.addFrame(getResources().getDrawable(R.drawable.pag8), 111);
    	frameAnimation.addFrame(getResources().getDrawable(R.drawable.pag0), 1);
    	frameAnimation.setOneShot(false);
	}    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }  
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.table_of_contents:
            	Intent intent_toc = new Intent(this, ContentsActivity.class);
            	this.startActivity(intent_toc);
            	break;
            case R.id.bookmark_utility:
            	Intent intent_bmu = new Intent(this, BookmarkActivity.class);
            	this.startActivity(intent_bmu);
            	break;
            case R.id.sketchPad:
            	Intent intent_spu = new Intent(this, SketchPad.class);
            	this.startActivity(intent_spu);
            	break;
            default:
                return super.onOptionsItemSelected(item);
        }
		return true;
    }
}
