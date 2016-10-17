package pro.android.graphics;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
public class ContentsActivity extends Activity {
    Paint paintObject = new Paint();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contents);
        final TransitionDrawable transition = (TransitionDrawable)getResources().getDrawable(R.drawable.trans_contents);
    	ImageView porterDuffImageComposite = (ImageView)findViewById(R.id.porterDuffImageView);
    	porterDuffImageComposite.setBackground(transition);        
        ImageButton transitionButton = (ImageButton)findViewById(R.id.bookmarkImageButton);
        transitionButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				transition.reverseTransition(5000);
			}
		});        
        LayerDrawable layerComposite = (LayerDrawable)getResources().getDrawable(R.drawable.contents_layers); 
//      Bitmap backgroundImage = BitmapFactory.decodeResource(getResources(), R.drawable.cloudsky);
//      Bitmap mutableBackgroundImage = backgroundImage.copy(Bitmap.Config.ARGB_8888, true);
        Bitmap foregroundImage = BitmapFactory.decodeResource(getResources(), R.drawable.bookmark0);     
        Bitmap mutableForegroundImage = foregroundImage.copy(Bitmap.Config.ARGB_8888, true);
        paintObject.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.XOR));
       	Drawable layerOne = ((LayerDrawable)layerComposite).findDrawableByLayerId(R.id.layerThree);
    	Bitmap composite = ((BitmapDrawable)layerOne).getBitmap();
        Bitmap mutableComposite = composite.copy(Bitmap.Config.ARGB_8888, true);    	
        Bitmap compositeImage = Bitmap.createBitmap(mutableForegroundImage);	  
        Canvas imageCanvas = new Canvas(compositeImage);
    	imageCanvas.drawBitmap(mutableComposite, null, new Rect(0, 0, 1000, 1000), paintObject);
    }
}
