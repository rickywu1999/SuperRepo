/***************************** 
 * SKELETON for
 * class SuperArray --  A wrapper class for an array.
 * Maintains functionality:
 *  access value at index
 *  overwrite value at index
 *  report number of meaningful items
 * Adds functionality to std Java array:
 *  resizability
 *  ability to print meaningfully
 *  add item (at end)
 *  insert item
 *  remove item (while maintaining "left-justification")
 *****************************/

/*
   Team Static -- James Wang, Ricky Wu
   APCS1 pd9
   HW42 -- Array of Titanium
   2015-12-06
*/

public class SuperArray implements Comparable {

    //~~~~~INSTANCE VARS~~~~~
    //underlying container, or "core" of this data structure:
    private int[] _data;

    //position of last meaningful value
    private int _lastPos;

    //size of this instance of SuperArray
    private int _size;


    //~~~~~METHODS~~~~~
    //default constructor initializes 10-item array
    public SuperArray() {
        _data = new int[10];
        _lastPos = -1; //flag to indicate no lastpos yet
        _size = 0;
    }


    //output array in [a,b,c] format, eg
    // {1,2,3}.toString() -> "[1,2,3]"
    public String toString() {
        String foo = "[";
        for(int i = 0; i < _size; i++) {
            foo += _data[i] + ",";
        }
        //shave off trailing comma
        if (foo.length() > 1)
            foo = foo.substring(0, foo.length()-1);
        foo += "]";
        return foo;
    }


    //double capacity of this SuperArray
    private void expand() {
        int[] temp = new int[ _data.length * 2 ];
        for(int i = 0; i < _data.length; i++)
            temp[i] = _data[i];
        _data = temp;
    }


    //accessor -- return value at specified index
    public int get(int index) { return _data[index]; }


    //mutator -- set value at index to newVal,
    //           return old value at index
    public int set(int index, int newVal) {
        int temp = _data[index];
        _data[index] = newVal;
        return temp;
    }


    // ~~~~~~~~~~~~~~ PHASE II ~~~~~~~~~~~~~~
    //adds an item after the last item
    public void add(int newVal) {
        //creates temporary integer array with length of 1 greater than _size
        int[] temp = new int[_size+1];
        //populates temp with elements in _data
        for (int i = 0; i < _size; i++) {
            temp[i] = _data[i];
        }
        //last element in temp is given the value of newVal
        temp[temp.length-1] = newVal;
        //_data is given value of temp
        _data = temp;
        //_lastPos and _size incremented by 1
        _lastPos += 1;
        _size += 1;
    }


    //inserts an item at index
    //shifts existing elements to the right
    public void add(int index, int newVal) {
        //creates temporary integer array with length of 1 greater than _size
        int[] temp = new int[_size+1];
        //populates temp with elements in _data up to the given index
        for (int i = 0; i < index; i++){
            temp[i] = _data[i];
        }
        //the element at the given index is given the value of newVal
        temp[index] = newVal;
        //starting from the end, temp is populated with elements in _data up to
        //the given index
        for (int i = _lastPos + 1; i > index; i--) {
            temp[i] = _data[i - 1];
        }
        //_data is given value of temp
        _data = temp;
        //_lastPos and _size incremented by 1
        _lastPos += 1;
        _size += 1;
    }


    //removes the item at index
    //shifts elements left to fill in newly-empted slot
    public void remove(int index) {
        //creates temporary integer array with length of 1 less than _size
        int[] temp = new int[_size-1];
        //populates temp with elements in _data up to the given index
        for (int i = 0; i < index; i++) {
            temp[i] = _data[i];
        }
        //starting from the end, temp is populated with elements in _data up to
        //the given index
        for (int i = _lastPos - 1; i >= index; i--) {
            temp[i] = _data[i + 1];
        }
        //_data is given value of temp
        _data = temp;
        //_lastPos and _size decremented by 1
        _lastPos -= 1;
        _size -= 1;
    }


    //return number of meaningful items in _data
    public int size() {
        return _size;
    }


    //main method for testing
    public static void main(String[] args) {
        ListInt curtis = new SuperArray();
        curtis.add(5);
        System.out.println(curtis);
        System.out.println("Current size of SuperArray: " + curtis.size());
        curtis.add(1, 20);
        System.out.println(curtis);
        System.out.println(curtis.get(1));
        System.out.println("Current size of SuperArray: " + curtis.size());
        curtis.remove(1);
        System.out.println(curtis);
    }//end main

}//end class
