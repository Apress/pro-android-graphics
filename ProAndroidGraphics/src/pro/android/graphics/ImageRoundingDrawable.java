package pro.android.graphics;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
public class ImageRoundingDrawable extends Drawable {
    private Paint paintObject;
    public ImageRoundingDrawable(Bitmap sourceImage){
    	BitmapShader imageShader;
    	imageShader = new BitmapShader(sourceImage,
    			Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
    	paintObject = new Paint();
    	paintObject.setAntiAlias(true);
    	paintObject.setShader(imageShader);
    }
	@Override
	public void draw(Canvas arg0) {
		int canvasH = getBounds().height();
		int canvasW = getBounds().width();
		RectF drawRect = new RectF(0.0f,0.0f,canvasW,canvasH);
		arg0.drawRoundRect(drawRect, 50, 50, paintObject);
	}
	@Override
	public int getOpacity() {
		return 0;
	}
	@Override
	public void setAlpha(int arg0) {
		// TODO Auto-generated method stub
	}
	@Override
	public void setColorFilter(ColorFilter arg0) {
		// TODO Auto-generated method stub
	}
}
