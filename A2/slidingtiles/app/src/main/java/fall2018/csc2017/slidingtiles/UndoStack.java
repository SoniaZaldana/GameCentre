package fall2018.csc2017.slidingtiles;
import java.io.Serializable;
import java.util.ArrayList;

public class UndoStack implements Serializable  {
    private ArrayList items;
    private int size;

    public UndoStack(int size){
        this.items = new ArrayList();
        this.size = size;
    }

    public void push(Object item){
        if (this.items.size() < this.size){
            this.items.add(item);
        } else {
            this.items.remove(0);
            this.items.add(item);
        }
    }

    public Object pop(){
        Object item = this.items.get(this.items.size() - 1);
        this.items.remove(this.items.size() - 1);
        return item;

    }

    public int getSize(){
        return this.items.size();
    }
}
