<h2>Description</h2>

<h3>Sunny and Square numbers</h3>

<p>A number N is a <strong>sunny</strong> number if N + 1 is a perfect <strong>square</strong> number. In <a href="https://en.wikipedia.org/wiki/Mathematics" rel="noopener noreferrer nofollow" title="Mathematics">mathematics</a>, a <strong>square </strong>number or perfect square is an <a href="https://en.wikipedia.org/wiki/Integer" rel="noopener noreferrer nofollow" title="Integer">integer</a> that is the <a href="https://en.wikipedia.org/wiki/Square_(algebra)" rel="noopener noreferrer nofollow" title="Square (algebra)">square</a> of an integer; in other words, it is the <a href="https://en.wikipedia.org/wiki/Multiplication" rel="noopener noreferrer nofollow" title="Multiplication">product</a> of some integer with itself. For example, 9 is a square number, since it equals 3<sup>2</sup> and can be written as 3 × 3.</p>

<h3>Improving the program</h3>

<p>So, our program can search for spy numbers and palindrome numbers. It is wonderful! But what if we want to find all even spy numbers? Or find all odd numbers of a palindrome. Are there palindromes that are spies? In this step, you will add the ability to search not only by one property but by two at once!</p>

<h3>...</h3>

<h2>Objectives</h2>

<p><em>Your program should process the user requests</em></p>

<p>In this stage, your program should:</p>

<ol>
	<li>Welcome the user</li>
	<li>Display the instruction on how to use it.</li>
	<li>Ask the user to enter a request</li>
	<li>If the user enters zero the program should finish. </li>
	<li>If any numbers are not natural then print an error message and instruction</li>
	<li>If incorrect property specified print the error message and list of available properties</li>
	<li>For one number calculate and print the properties of the given number.</li>
	<li>For two numbers prints the list of numbers with their properties.</li>
	<li>For two numbers and one property prints the list of numbers with the given property only. </li>
	<li>For two numbers and two properties print the list of numbers that have both properties.</li>
	<li>Continue work from step 3.</li>
</ol>

<p>For the current stage, the property names are <code>even</code>, <code>odd</code>, <code>buzz</code> , <code>duck</code>, <code>palindromic</code> , <code>gapful</code> , <code>spy</code>  and <code>harshad</code>. The order of properties, indentation, and spaces doesn't important for the tests. You may format numbers.</p>

<h3>The instruction</h3>

<pre><code class="language-no-highlight">Supported requests:
- one natural number to print the card;
- two natural numbers to print the list;
  - a starting number for the list;
  - a count of numbers in the list;
- two natural numbers and a property to search for;
- two natural numbers and two properties to search for;
- parameters should be separated by space
- 0 for the exit. </code></pre>

<h3>The error messages</h3>

<pre><code class="language-no-highlight">The first parameter should be a natural number or zero.</code></pre>

<pre><code class="language-no-highlight">The second parameter should be a natural number.</code></pre>

<pre><code class="language-no-highlight">The property [SUN] is wrong.
Available properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, JUMPING]</code></pre>

<pre><code class="language-no-highlight">The properties [HOT, SUN] are wrong.
Available properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, JUMPING]</code></pre>

<pre><code class="language-no-highlight">The request contains mutually exclusive properties: [ODD, EVEN]
There are no numbers with all these properties at once.</code></pre>

<p>...</p>

<h2>Examples</h2>

<p>The greater-than symbol followed by a space represents the user input. Note that it's not part of the input.</p>

<p><strong>Example 1</strong></p>

<pre><code class="language-no-highlight">Welcome to Amazing Numbers!

Supported requests:
- one natural number to print its properties;
- two natural numbers separated by space:
  - a starting number for the list;
  - a count of numbers in the list;
- two natural numbers and property to search for;
- two natural numbers and two properties to search for;
- 0 for the exit. 

Enter a request: &gt; 7777777

Properties of 7,777,777
        even: false
         odd: true
        buzz: true
        duck: false
 palindromic: true
      gapful: false
         spy: false
     harshad: false

Enter a request: &gt; 777777 7

         777,777 is odd, buzz, palindromic, gapful
         777,778 is even
         777,779 is odd
         777,780 is even, duck, harshad
         777,781 is odd
         777,782 is even
         777,783 is odd

Enter a request: &gt; 777777 7 spy

       1,111,127 is odd, buzz, spy
       1,111,134 is even, spy
       1,111,143 is odd, spy
       1,111,172 is even, spy
       1,111,217 is odd, buzz, spy
       1,111,271 is odd, buzz, spy
       1,111,314 is even, spy

Enter a request: &gt; 777777 7 spy harshad

       1,111,712 is even, buzz, spy, harshad
      11,111,232 is even, gapful, spy, harshad
      11,112,132 is even, gapful, spy, harshad
      11,112,312 is even, gapful, spy, harshad
      11,113,212 is even, gapful, spy, harshad
      11,118,112 is even, spy, harshad
      11,121,132 is even, gapful, spy, harshad

Enter a request: &gt; xyz

This number is not natural!

Supported requests:
- one natural number to print its properties;
- two natural numbers separated by space:
  - a starting number for the list;
  - a count of numbers in the list;
- two natural numbers and property to search for;
- 0 for the exit. 

Enter a request: </code></pre>

<p><strong>Example 2</strong></p>

<pre><code class="language-no-highlight">Welcome to Amazing Numbers!

Supported requests:
- one natural number to print its properties;
- two natural numbers separated by space:
  - a starting number for the list;
  - a count of numbers in the list;
- two natural numbers and property to search for;
- two natural numbers and two properties to search for;
- 0 for the exit. 

Enter a request: 999999999

Properties of 999,999,999
        even: false
         odd: true
        buzz: false
        duck: false
 palindromic: true
      gapful: false
         spy: false
     harshad: true

Enter a request: 999999999 3

     999,999,999 is odd, palindromic, harshad
   1,000,000,000 is even, duck, gapful, harshad
   1,000,000,001 is odd, buzz, duck, palindromic, gapful

Enter a request: 999999999 1 even buzz

   1,000,000,008 is even, buzz, duck, gapful, harshad

Enter a request: 0

Goodbye!</code></pre>

<p><strong>Example 3</strong></p>

<pre><code class="language-no-highlight">Welcome to Amazing Numbers!

Supported requests:
- one natural number to print its properties;
- two natural numbers separated by space:
  - a starting number for the list;
  - a count of numbers in the list;
- two natural numbers and property to search for;
- two natural numbers and two properties to search for;
- 0 for the exit. 

Enter a request: 1 2 even

               2 is even, palindromic, spy
               4 is even, palindromic, spy

Enter a request: 1 2 even even

               2 is even, palindromic, spy
               4 is even, palindromic, spy

Enter a request: 1 2 even bug

The property [BUG] is unknown.
Available properties: [BUZZ, EVEN, PALINDROMIC, SPY, ODD, GAPFUL, DUCK]

Enter a request: 1 2 no bug

The properties [NO, BUG] are unknown.
Available properties: [BUZZ, EVEN, PALINDROMIC, SPY, ODD, GAPFUL, DUCK]

Enter a request: 1 2 even odd

The request contains mutually exclusive properties: [ODD, EVEN]
There are no numbers with all these properties at once.

Enter a request: 1 2 spy duck

The request contains mutually exclusive properties: [SPY, DUCK]
There are no numbers with all these properties at once.

Enter a request: 1 2 spy even

               2 is even, palindromic, spy
               4 is even, palindromic, spy

Enter a request: 1 2 odd duck

             101 is odd, duck, palindromic
             103 is odd, duck

Enter a request: </code></pre>

<p>...</p>