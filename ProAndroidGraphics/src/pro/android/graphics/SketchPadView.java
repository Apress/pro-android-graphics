package pro.android.graphics;
import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
public class SketchPadView extends View implements OnTouchListener {
	Paint paintScreen = new Paint();
	Bitmap paintImage = BitmapFactory.decodeResource(getResources(), R.drawable.cloudsky);
	BitmapShader inkShader = new BitmapShader(paintImage,Shader.TileMode.CLAMP,Shader.TileMode.CLAMP);
	List<Coordinate> coordinates = new ArrayList<Coordinate>();
	public SketchPadView(Context context) {
		super(context);
		this.setOnTouchListener(this);
		paintScreen.setAntiAlias(true);
		paintScreen.setShader(inkShader);
		setFocusableInTouchMode(true);
		setFocusable(true);
	}
	@Override
    public void onDraw(Canvas canvas) {
		for (Coordinate point : coordinates) {
                canvas.drawCircle(point.x, point.y, 25, paintScreen);
		}		
	}	
	@Override
	public boolean onTouch(View arg0, MotionEvent arg1) {
		Coordinate point = new Coordinate();
		point.x = arg1.getX();
		point.y = arg1.getY();
		coordinates.add(point);
		invalidate();
		return true;
	}
}
class Coordinate { float x, y; }