<h2>Description</h2>

<h3>Jumping Number</h3>

<p>A number is called a Jumping Number if all adjacent digits in it differ by 1. The difference between ‘9’ and ‘0’ is not considered as 1.<br>
All single-digit numbers are considered as Jumping Numbers. For example, 7, 8987, and 4343456 are Jumping numbers but 796 and 89098 are not.</p>

<h3>Improving the program</h3>

<p>The program already knows how to calculate eight properties of numbers and it would be strange to limit the query to just two properties. Let's remove this limitation. Let the program search for numbers by any given number of properties. Of course, one must take into account that some properties are inherently excluded. Such, for example, as even and odd numbers. In the tests, we will exclude such requests, but if you are confident in your abilities, then add a request check for mutually exclusive properties. For example, if the user simultaneously enters a search by the number of spies and ducks, then instead of an endless loop, the program can warn the user. Note again that we did not add this check in the tests and you can omit it if you don’t know how to do it.</p>

<p>...</p>

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
	<li>For two numbers and properties print the list of numbers that have all of the properties.</li>
	<li>Continue work from step 3.</li>
</ol>

<p>For the current stage, the property names are <code>even</code>, <code>odd</code>, <code>buzz</code>, <code>duck</code>, <code>palindromic</code>, <code>gapful</code>, <code>spy</code>, <code>harshad</code>and <code>armstrong</code>. The order of properties, indentation, and spaces doesn't important for the tests. You may format numbers.</p>

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
- two natural numbers and properties to search for
- 0 for the exit. 

Enter a natural number: 1234567890

Properties of 1,234,567,890
        even: true
         odd: false
        buzz: false
        duck: true
      gapful: true
     harshad: true
         spy: false
   armstrong: false
    disarium: false

Enter a natural number: 987654321 3

     987,654,321 is odd
     987,654,322 is even
     987,654,323 is odd

Enter a natural number: 10 10 armstrong

             153 is odd, harshad, armstrong
             370 is even, duck, harshad, armstrong
             371 is odd, buzz, armstrong
             407 is odd, buzz, duck, harshad, armstrong
           1,634 is even, armstrong
           8,208 is even, duck, harshad, armstrong
           9,474 is even, armstrong
          54,748 is even, armstrong
          92,727 is odd, buzz, armstrong
          93,084 is even, duck, armstrong

Enter a natural number: 1 10 armstrong even

               2 is even, harshad, spy, armstrong, disarium
               4 is even, harshad, spy, armstrong, disarium
               6 is even, harshad, spy, armstrong, disarium
               8 is even, harshad, spy, armstrong, disarium
             370 is even, duck, harshad, armstrong
           1,634 is even, armstrong
           8,208 is even, duck, harshad, armstrong
           9,474 is even, armstrong
          54,748 is even, armstrong
          93,084 is even, duck, armstrong</code></pre>

<p><strong>Example 2</strong></p>

<pre><code class="language-no-highlight">Enter a request: &gt; 1 11 even armstrong

               2 is even, harshad, spy, armstrong
               4 is even, harshad, spy, armstrong
               6 is even, harshad, spy, armstrong
               8 is even, harshad, spy, armstrong
             370 is even, duck, harshad, armstrong
           1,634 is even, armstrong
           8,208 is even, duck, harshad, armstrong
           9,474 is even, armstrong
          54,748 is even, armstrong
          93,084 is even, duck, armstrong
         548,834 is even, armstrong
</code></pre>

<p><strong>Example 3</strong></p>

<pre><code class="language-no-highlight">Enter a request: &gt; 99 11 spy odd

             123 is odd, spy
             213 is odd, spy
             231 is odd, buzz, gapful, spy
             321 is odd, spy
           1,241 is odd, spy
           1,421 is odd, buzz, spy
           2,141 is odd, spy
           2,411 is odd, spy
           4,121 is odd, spy
           4,211 is odd, spy
          11,125 is odd, spy

Enter a request: &gt; 1 10 spy buzz gapful

             231 is odd, buzz, gapful, spy
         111,216 is even, buzz, gapful, harshad, spy
         161,112 is even, buzz, gapful, harshad, spy
         216,111 is odd, buzz, gapful, spy
       1,111,341 is odd, buzz, gapful, spy
       1,114,113 is odd, buzz, gapful, spy
       1,311,114 is even, buzz, gapful, spy
      11,123,112 is even, buzz, gapful, harshad, spy
      11,132,121 is odd, buzz, gapful, spy
      11,211,312 is even, buzz, gapful, harshad, spy
</code></pre>