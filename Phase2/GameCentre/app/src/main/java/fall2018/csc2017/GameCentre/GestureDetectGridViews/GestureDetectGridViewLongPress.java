/**
 * Excluded from tests because it is a view class.
 */
package fall2018.csc2017.GameCentre.GestureDetectGridViews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import fall2018.csc2017.GameCentre.ClicksOnBoard;
import fall2018.csc2017.GameCentre.MovementControllers.MovementModelComplexPress;

/**
 * a GestureDetectGridView which rects to both short and long presses on the screen.
 */
public class GestureDetectGridViewLongPress extends GestureDetectGridView {
    /**
     *  This complex press movement controller
     */
    MovementModelComplexPress mController;


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

    public void setMovementController(MovementModelComplexPress mController) {
        this.mController = mController;
    }

    @Override
    public void createAndSetGestureDetector(final Context context) {
        GestureDetector gDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {

            @Override
            public boolean onSingleTapConfirmed(MotionEvent event) {
                int position = GestureDetectGridViewLongPress.this.pointToPosition
                        (Math.round(event.getX()), Math.round(event.getY()));

                mController.processMove(context, position, ClicksOnBoard.SHORT);
                return true;
            }
            @Override
            public void onLongPress(MotionEvent event) {
                int position = GestureDetectGridViewLongPress.this.pointToPosition
                        (Math.round(event.getX()), Math.round(event.getY()));

                mController.processMove(context, position,ClicksOnBoard.LONG);
            }

            @Override
            public boolean onDown(MotionEvent event) {
                return true;
            }

        });
        setgDetector(gDetector);
    }

}
