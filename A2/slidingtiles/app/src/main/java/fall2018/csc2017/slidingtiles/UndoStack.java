package fall2018.csc2017.slidingtiles;
import java.io.Serializable;
import java.util.ArrayList;

public class UndoStack implements Serializable  {

    /**
     * The ArrayList works as stack.
     */
    private ArrayList items;

    /**
     * The size of a stack.
     */
    private int size;

    /**
     * Initialize a stack.
     */
    public UndoStack(int size){
        this.items = new ArrayList();
        this.size = size;
    }

    /**
     * Push object item into a stack.
     */
    public void push(Object item){
        if (this.items.size() < this.size){
            this.items.add(item);
        } else {
            this.items.remove(0);
            this.items.add(item);
        }
    }

    /**
     * Pop object item from a stack.
     */
    public Object pop(){
            Object item = this.items.get(this.items.size() - 1);
            this.items.remove(this.items.size() - 1);
            return item;
    }

    /**
     * Return the size of an item from a stack.
     */
    public int getSize(){
        return this.items.size();
    }
}
