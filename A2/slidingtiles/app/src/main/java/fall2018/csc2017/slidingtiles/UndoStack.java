package fall2018.csc2017.slidingtiles;
import java.io.Serializable;
import java.util.ArrayList;


/**
 * A stack with a maximum size, used for undoing moves.
 */
public class UndoStack implements Serializable  {

    /**
     * The stack of items.
     */
    private ArrayList items;

    /**
     * The size of a stack.
     */
    private int maxSize;

    /**
     * Initialize a stack.
     */
    UndoStack(int size){
        this.items = new ArrayList();
        this.maxSize = size;
    }

    /**
     * Push an object item into the stack.
     */
    void push(Object item){
        if (this.items.size() < this.maxSize){
            this.items.add(item);
        } else {
            this.items.remove(0);
            this.items.add(item);
        }
    }

    /**
     * Pop an object item from the stack.
     */
    Object pop(){
            Object item = this.items.get(this.items.size() - 1);
            this.items.remove(this.items.size() - 1);
            return item;
    }

    /**
     * Return the size of the stack.
     */
    public int getSize(){
        return this.items.size();
    }
}
