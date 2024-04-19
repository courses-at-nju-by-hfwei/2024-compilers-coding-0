grammar SignedBinaryNumber;

@header {
package ag;
}

number : sign list ;

sign : '+' | '-' ;

// using right recursion instead of left recursion
list : bit list
     | bit
     ;

bit : '0' | '1' ;