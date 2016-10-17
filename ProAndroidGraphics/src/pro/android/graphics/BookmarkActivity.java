package pro.android.graphics;
import java.io.InputStream;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.widget.ImageView;
public class BookmarkActivity extends Activity {
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark);
        ImageView porterDuffImage = (ImageView)findViewById(R.id.currentBookmarkImage);
		porterDuffImage.setColorFilter(Color.rgb(216,192,96), PorterDuff.Mode.MULTIPLY);
		ImageView backgroundImage = (ImageView)findViewById(R.id.backgroundImage);
		InputStream rawImage = getResources().openRawResource(R.drawable.cloudsky);
		Bitmap sourceImage = BitmapFactory.decodeStream(rawImage);
		backgroundImage.setBackground(new ImageRoundingDrawable(sourceImage));
    }
}
