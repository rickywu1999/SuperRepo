// Team rickMan: Manish Saha, Ricky Wu
// APCS1 pd9
// HW44 -- This or That or Fourteen Other Things
// 2015-12-08

public class Hexadecimal implements Comparable{

    private final static String HEXDIGITS = "0123456789ABCDEF";
    
    private int _decNum;
    private String _hexNum;


    /*=====================================
      default constructor
      pre:  n/a
      post: initializes _decNum to 0, _hexNum to "0"
      =====================================*/
    public Hexadecimal() { 
        _decNum = 0;
	_hexNum = "0";
    }


    /*=====================================
      overloaded constructor
      pre:  n >= 0
      post: sets _decNum to n, _hexNum to equiv string of bits
      =====================================*/
    public Hexadecimal( int n ) {
        _decNum = n;
	_hexNum = decToHex(n);
    }


    /*=====================================
      overloaded constructor
      pre:  s is String representing non-negative hexadecimal number
      post: sets _hexNum to input, _decNum to decimal equiv
      =====================================*/
    public Hexadecimal( String s ) {
	_decNum = hexToDec(s);
	_hexNum = s;
    }


    /*=====================================
      String toString() -- returns String representation of this Object
      pre:  n/a
      post: returns String of 1's and 0's representing value of this Object
      =====================================*/
    public String toString() { 
        return _hexNum;   
    }


    /*=====================================
      String decToHex(int) -- converts base-10 input to hexadecimal
      pre:  n >= 0
      post: returns String of bits
      =====================================*/
    public static String decToHex( int n ) {
        String hex = "";

	//This handles cases where n is 0
	if (n == 0) {
	    hex = "0";
	}

	//This handles cases where n is > 0
	//How it works:
	//1.Convert the remainder of n divided by 16 into hexadecimal
	//2.Add the hexadecimal string to the front of String hex
	//3.Change n to the quotient of n/16
	while (n > 0) {
	    int pos = n % 16;
	    //Goes from right to left
	    hex = HEXDIGITS.substring(pos, pos + 1) + hex;
	    n /= 16;
	}

	return hex;
    }


    /*=====================================
      String decToHexR(int) -- converts base-10 input to hexadecimal, recursively
      pre:  n >= 0
      post: returns String of bits
      =====================================*/
    public static String decToHexR( int n ) {
	String hex = "";

	//Base Case
        if (n < 16) {
	    hex = HEXDIGITS.substring(n, n+1);
	}

	//Else do the recursion
	else {
	    int pos = n % 16;
	    //Right to left
	    hex = decToHexR( n/16 ) + HEXDIGITS.substring(pos, pos + 1);
	}
	
	return hex;
    }


    /*=====================================
      String hexToDec(String) -- converts base-10 input to hexadecimal
      pre:  s represents non-negative hexadecimal number
      post: returns int of bits
      =====================================*/
    public static int hexToDec( String s ) {
	int sum = 0;
        int pow = s.length() - 1;
	//Note: index is used to progress through the String
	int index = 0;
	
	for (; pow >= 0; pow--) {
	    //x is the base 10 version of a single hexadecimal character. x will be an integer between and including 0 and 15
	    int x = HEXDIGITS.indexOf(s.substring(index,index+1));
	    sum += Math.pow(16,pow) * x;
	    index++;
	}
	
	return sum;		
    }


    /*=====================================
      String hexToDecR(String) -- converts base-10 input to hexadecimal, recursively
      pre:  s represents non-negative hexadecimal number
      post: returns int of bits
      =====================================*/
    public static int hexToDecR( String s ) { 
	int sum = 0;
	int pow = s.length() - 1;

	//Base Case
	if (pow == 0) {
	    return HEXDIGITS.indexOf(s);
	}
	
	int x = HEXDIGITS.indexOf(s.substring(0,1));
	sum += Math.pow(16,pow) * x;

	//Goes left to right
	return sum + hexToDecR(s.substring(1));

    }

    public Rational rationalize(){
	return new Rational(_decNum,1);
    }


    /*=============================================
      boolean equals(Object) -- tells whether 2 Objs are equivalent
      pre:  other is an instance of class Hexadecimal
      post: Returns true if this and other are aliases (pointers to same 
      Object), or if this and other represent equal hexadecimal values
      =============================================*/
    public boolean equals( Object other ) { 
        return this == other || compareTo(other) == 0;
    }


    /*=============================================
      int compareTo(Object) -- tells which of two Hexadecimal objects is greater
      pre:  other is instance of class Hexadecimal
      post: Returns 0 if this Object is equal to the input Object,
      negative integer if this<input, positive integer otherwise
      =============================================*/
    public int compareTo( Object other ) {
        if (other instanceof Comparable) {
	    Rational _other = ((Comparable)other).rationalize();
	    return this.rationalize().compareTo(_other);
	}
        throw new ClassCastException("\nMy first error message! compareTo() input not valid");
    }


    //main method for testing
    public static void main( String[] args ) {


	System.out.println();
	System.out.println( "Testing ..." );

	// testing equivalence both hex -> dec and dec -> hex
       	System.out.println(decToHex(0));
	System.out.println(decToHexR(0));
	System.out.println(decToHex(1280));
	System.out.println(decToHexR(1280));
	
	System.out.println(hexToDec("0"));
	System.out.println(hexToDecR("0"));
	System.out.println(hexToDec("500"));
	System.out.println(hexToDecR("500"));

	System.out.println(decToHex(0));
	System.out.println(decToHexR(0));
	System.out.println(decToHex(500));
	System.out.println(decToHexR(500));

       	System.out.println(hexToDec("0"));
	System.out.println(hexToDecR("0"));
	System.out.println(hexToDec("1F4"));
	System.out.println(hexToDecR("1F4"));
	
       	System.out.println();
	System.out.println( "Testing equivalence..." );
	
	// DEFAULT TESTING
	Hexadecimal b1 = new Hexadecimal(500);
	Hexadecimal b2 = new Hexadecimal(500);
	Hexadecimal b3 = b1;
	Hexadecimal b4 = new Hexadecimal(700);

	System.out.println( b1 );
	System.out.println( b2 );
	System.out.println( b3 );       
	System.out.println( b4 );       

	System.out.println( "\n==...\n" );
	System.out.println( b1 == b2 ); //should be false
	System.out.println( b1 == b3 ); //should be true

	System.out.println( "\n.equals()...\n" );
	System.out.println( b1.equals(b2) ); //should be true
	System.out.println( b1.equals(b3) ); //should be true
	System.out.println( b3.equals(b1) ); //should be true
	System.out.println( b4.equals(b2) ); //should be false
	System.out.println( b1.equals(b4) ); //should be false

	System.out.println( "\n.compareTo...\n" );
	System.out.println( b1.compareTo(b2) ); //should be 0
	System.out.println( b1.compareTo(b3) ); //should be 0
	System.out.println( b1.compareTo(b4) ); //should be neg
	System.out.println( b4.compareTo(b1) ); //should be pos

    }//end main()

} //end class
