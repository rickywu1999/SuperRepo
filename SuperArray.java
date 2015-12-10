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

public class SuperArray {

    //~~~~~INSTANCE VARS~~~~~
    //underlying container, or "core" of this data structure:
    private Comparable[] _data;

    //position of last meaningful value
    private int _lastPos;

    //size of this instance of SuperArray
    private int _size;


    //~~~~~METHODS~~~~~
    //default constructor initializes 10-item array
    public SuperArray() {
        _data = new Comparable[10];
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
        Comparable[] temp = new Comparable[ _data.length * 2 ];
        for(int i = 0; i < _data.length; i++)
            temp[i] = _data[i];
        _data = temp;
    }

    //mutator -- set value at index to newVal,
    //           return old value at index
    public Comparable set(int index, Comparable newVal) {
        Comparable temp = _data[index];
        _data[index] = newVal;
        return temp;
    }


    // ~~~~~~~~~~~~~~ PHASE II ~~~~~~~~~~~~~~
    //adds an item after the last item
    public void add(Comparable newVal) {
        //creates temporary integer array with length of 1 greater than _size
        Comparable[] temp = new Comparable[_size+1];
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
    public void add(int index, Comparable newVal) {
        //creates temporary integer array with length of 1 greater than _size
        Comparable[] temp = new Comparable[_size+1];
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
        Comparable[] temp = new Comparable[_size-1];
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

    public int linSearch(Comparable a) {
	for (int i = 0; i < _lastPos ; i++){
	    if (_data[i].compareTo(a) == 0){
		return i;
	    }
	}
	return -1;
    }	

    public boolean isSorted() {
	for (int i = 1; i < _lastPos ; i++){
	    if (_data[i].compareTo(_data[i-1]) < 0){
		return false;
	    }
	}
	return true;
    }

    //main method for testing
    public static void main(String[] args) {
	SuperArray arr = new SuperArray();
	Comparable a = new Binary("101010");
	arr.add(a);
	Comparable b = new Hexadecimal("3F");
	arr.add(b);
        Comparable c = new Rational(201,2);
	arr.add(c);
	
	System.out.println("Array: " + arr);
	System.out.println("Is the array in order? " + arr.isSorted());
	arr.add(1,new Rational(1000000,2));
	System.out.println("After Adding to Array: " + arr);
	System.out.println("Is the array in order? " + arr.isSorted());
	
    }//end main

}//end class
