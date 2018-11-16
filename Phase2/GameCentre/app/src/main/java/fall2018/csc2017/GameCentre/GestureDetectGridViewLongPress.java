package fall2018.csc2017.GameCentre;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;

public class GestureDetectGridViewLongPress extends GestureDetectGridView {
    MovementControllerComplexPress mController;

    public void setMovementController(MovementControllerComplexPress mController) {
        this.mController = mController;
    }
    public GestureDetectGridViewLongPress(Context context) {
        super(context);
        createAndSetGestureDetector(context);
    }

    public GestureDetectGridViewLongPress(Context context, AttributeSet attrs) {
        super(context, attrs);
        createAndSetGestureDetector(context);
    }

    public GestureDetectGridViewLongPress(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        createAndSetGestureDetector(context);
    }
    @Override
    public void createAndSetGestureDetector(final Context context) {

        GestureDetector gDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {

            @Override
            public boolean onSingleTapConfirmed(MotionEvent event) {
                int position = GestureDetectGridViewLongPress.this.pointToPosition
                        (Math.round(event.getX()), Math.round(event.getY()));

                mController.processMove(context, position, R.string.short_press);
                return true;
            }
            @Override
            public void onLongPress(MotionEvent event) {
                int position = GestureDetectGridViewLongPress.this.pointToPosition
                        (Math.round(event.getX()), Math.round(event.getY()));

                mController.processMove(context, position,R.string.long_press);
            }

            @Override
            public boolean onDown(MotionEvent event) {
                return true;
            }

        });
        setgDetector(gDetector);
    }

}
