//skeleton file for class Binary

public class Binary {

    private int _decNum;
    private String _binNum;


    /*=====================================
      default constructor
      pre:  n/a
      post: initializes _decNum to 0, _binNum to "0"
      =====================================*/
    public Binary() { 
        _decNum = 0;
	_binNum = "0";
    }


    /*=====================================
      overloaded constructor
      pre:  n >= 0
      post: sets _decNum to n, _binNum to equiv string of bits
      =====================================*/
    public Binary( int n ) {
        this();
	_decNum = n;
	_binNum = decToBin(n);
    }


    /*=====================================
      overloaded constructor
      pre:  s is String representing non-negative binary number
      post: sets _binNum to input, _decNum to decimal equiv
      =====================================*/
    public Binary( String s ) {
        _binNum = s;
	_decNum = binToDec(s);
    }


    /*=====================================
      String toString() -- returns String representation of this Object
      pre:  n/a
      post: returns String of 1's and 0's representing value of this Object
      =====================================*/
    public String toString() { 
        return _binNum;
    }


    /*=====================================
      String decToBin(int) -- converts base-10 input to binary
      pre:  n >= 0
      post: returns String of bits
      eg  decToBin(0) -> "0"
      decToBin(1) -> "1"
      decToBin(2) -> "10"
      decToBin(3) -> "11"
      decToBin(14) -> "1110"
      =====================================*/
    public static String decToBin( int n ) {
	int copy = n;
        String ans = "";
	while(copy > 1){
	    ans = copy%2 + "" + ans;
	    copy = copy/2;
	}
	//goes right to left
	return copy + ans;
    }


    /*=====================================
      String decToBinR(int) -- converts base-10 input to binary, recursively
      pre:  n >= 0
      post: returns String of bits
      eg  decToBinR(0) -> "0"
      decToBinR(1) -> "1"
      decToBinR(2) -> "10"
      decToBinR(3) -> "11"
      decToBinR(14) -> "1110"
      =====================================*/
    public static String decToBinR( int n ) { 
	if (n < 2){
	    return "" + n;
	}
	//goes rigth to left
	return "" + decToBinR( n/2 ) + n%2;
    }


    /*=====================================
      String binToDec(String) -- converts base-10 input to binary
      pre:  s represents non-negative binary number
      post: returns String of bits
      eg  
      binToDec("0") -> 0
      binToDec("1") -> 1
      binToDec("10") -> 2
      binToDec("11") -> 3
      binToDec("1110") -> 14
      =====================================*/
    public static int binToDec( String s ) {
	int len = s.length();
	int ans = 0;
        for (int i = 0; i < len; i++){
	    //goes left to right
	    ans += Integer.parseInt(s.substring(i,i+1)) * (int)Math.pow(2,len-i-1);
	}
	return ans;
    }


    /*=====================================
      String binToDecR(String) -- converts base-10 input to binary, recursively
      pre:  s represents non-negative binary number
      post: returns String of bits
      eg  
      binToDecR("0") -> 0
      binToDecR("1") -> 1
      binToDecR("10") -> 2
      binToDecR("11") -> 3
      binToDecR("1110") -> 14
      =====================================*/
    public static int binToDecR( String s ) {
	int len = s.length();
	if (len == 1){
	    return Integer.parseInt(s) * (int)Math.pow(2,len-1);
	}
	//goes right to left
	return Integer.parseInt(s.substring(len, len-1)) * (int)Math.pow(2,len-1) + binToDecR(s.substring(len-1));
    }


    /*=============================================
      boolean equals(Object) -- tells whether 2 Objs are equivalent
      pre:  other is an instance of class Binary
      post: Returns true if this and other are aliases (pointers to same 
      Object), or if this and other represent equal binary values
      =============================================*/
    public boolean equals( Object other ) { 
 
	//First, check for aliasing.
	boolean retVal = this == other;
 
	//Next, if this and input Object are different objects,
        if ( !retVal ) {
 
	    //...check to see if input Object is a Binary
	    if (other instanceof Binary) {
	        retVal = _decNum == ((Binary)other)._decNum;
	    }				     
	}
	return retVal;    
    }


    /*=============================================
      int compareTo(Object) -- tells which of two Binary objects is greater
      pre:  other is instance of class Binary
      post: Returns 0 if this Object is equal to the input Object,
      negative integer if this<input, positive integer otherwise
      =============================================*/
    public int compareTo( Object other ) {
	if (_decNum > ((Binary)other)._decNum){
	    return 1;
	}
	if (_decNum < ((Binary)other)._decNum){
	    return -1;
	}
	return 0;
    }


    //main method for testing
    public static void main( String[] args ) {

	System.out.println();
	System.out.println( "Testing ..." );

	Binary b1 = new Binary(5);
	Binary b2 = new Binary(5);
	Binary b3 = b1;
	Binary b4 = new Binary(64);

	System.out.println( b1 );
	System.out.println( b1._decNum);
	System.out.println( b2 );
	System.out.println( b2._decNum);
	System.out.println( b3 );
	System.out.println( b3._decNum);
	System.out.println( b4 );
	System.out.println( b4._decNum);

	Binary b5 = new Binary("10011");
	Binary b6 = new Binary("111101");

	System.out.println( b5 );
	System.out.println( b5._decNum);
	System.out.println( b6 );
	System.out.println( b6._decNum);

	System.out.println( "\n==..." );
	System.out.println( b1 == b2 ); //should be false
	System.out.println( b1 == b3 ); //should be true

	System.out.println( "\n.equals()..." );
	System.out.println( b1.equals(b2) ); //should be true
	System.out.println( b1.equals(b3) ); //should be true
	System.out.println( b3.equals(b1) ); //should be true
	System.out.println( b4.equals(b2) ); //should be false
	System.out.println( b1.equals(b4) ); //should be false

	System.out.println( "\n.compareTo..." );
	System.out.println( b1.compareTo(b2) ); //should be 0
	System.out.println( b1.compareTo(b3) ); //should be 0
	System.out.println( b1.compareTo(b4) ); //should be neg
	System.out.println( b4.compareTo(b1) ); //should be pos
    }//end main()

} //end class
