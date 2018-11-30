/**
 * Excluded from tests because it is a view class.
 */
package fall2018.csc2017.GameCentre.GestureDetectGridViews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;

import fall2018.csc2017.GameCentre.MovementControllers.MovementModelSimplePress;

/**
 * a GestureDetectGridView which reacts to only short presses on the screen.
 */
public class GestureDetectGridViewShortPress extends GestureDetectGridView {
    MovementModelSimplePress mController;
    public GestureDetectGridViewShortPress(Context context) {
        super(context);
        createAndSetGestureDetector(context);
    }

    public GestureDetectGridViewShortPress(Context context, AttributeSet attrs) {
        super(context, attrs);
        createAndSetGestureDetector(context);
    }

    public GestureDetectGridViewShortPress(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        createAndSetGestureDetector(context);
    }

    public void setMovementController(MovementModelSimplePress mController) {
        this.mController = mController;
    }

    @Override
    public void createAndSetGestureDetector(final Context context) {

        GestureDetector gDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {

            @Override
            public boolean onSingleTapConfirmed(MotionEvent event) {
                int position = GestureDetectGridViewShortPress.this.pointToPosition
                        (Math.round(event.getX()), Math.round(event.getY()));

                mController.processMove(context, position);
                return true;
            }

            @Override
            public boolean onDown(MotionEvent event) {
                return true;
            }

        });
        setgDetector(gDetector);
    }
}

