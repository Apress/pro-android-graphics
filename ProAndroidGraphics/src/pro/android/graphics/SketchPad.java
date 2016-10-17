package pro.android.graphics;
import android.app.Activity;
import android.os.Bundle;
public class SketchPad extends Activity {
	SketchPadView sketchPadView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sketchPadView = new SketchPadView(this);
        setContentView(sketchPadView);
        sketchPadView.requestFocus();
    }
}