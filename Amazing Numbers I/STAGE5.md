<h2>Description</h2>

<h3>Spy number</h3>

<p>A number is said to be a Spy number if the sum of all the digits is equal to the product of all digits</p>

<h3>Improving the program</h3>

<p>Our program calculates the properties of numbers, and also knows how to print a list of numbers. But what if we want to find numbers that have a certain property? For example, do we want to find ten Bizz numbers starting at 1000? In this stage, we will talk about the spy numbers. And these numbers are not very many, and they hide quite well. Your task is to modify the program so that it can search for such numbers.</p>

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
	<li>For two numbers and property prints the list of numbers with the given property only. </li>
	<li>Continue work from step 3.</li>
</ol>

<p>For the current stage, the property names are <code>even</code>, <code>odd</code>, <code>buzz</code> , <code>duck</code>, <code>palindromic</code> , <code>gapful</code> and <code>spy</code>. The order of properties, indentation, and spaces doesn't important for the tests. You may format numbers.</p>

<h3> The instruction</h3>

<pre><code class="language-no-highlight">Supported requests:
- natural number to print the card; 
- two natural numbers to print the list:
  - the first parameter represents starting number;
  - the second parameters is count of numbers in the list;
- two natural numbers and a property to search for;
- parameters should be separated by space;
- 0 for the exit.</code></pre>

<h3>The error messages</h3>

<pre><code class="language-no-highlight">The first parameter should be a natural number or zero.</code></pre>

<pre><code class="language-no-highlight">The second parameter should be a natural number.</code></pre>

<pre><code class="language-no-highlight">The property [SUN] is wrong.
Available properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY]</code></pre>

<p>...</p>

<h2>Examples</h2>

<p>The greater-than symbol followed by a space represents the user input. Note that it's not part of the input.</p>

<p><strong>Example 1</strong></p>

<p><em>Starting the program</em></p>

<pre><code class="language-no-highlight">Welcome to Amazing Numbers!

Supported requests:
- one natural number to print its properties 
- two natural numbers separated by space:
  - a starting natural number for the list
  - a count of numbers in the list 
- two natural numbers and property to search for
- 0 for the exit. 

Enter a request: </code></pre>

<p><strong>Example 2</strong></p>

<p><em>Entering an incorrect first number</em></p>

<pre><code class="language-no-highlight">Enter a request: &gt; ABC

This number is not natural!

Supported requests:
- one natural number to print its properties;
- two natural numbers separated by space:
  - a starting number for the list;
  - a count of numbers in the list;
- two natural numbers and property to search for;
- 0 for the exit. 

Enter a request: </code></pre>

<p><strong>Example 3</strong></p>

<p><em>Entering an incorrect second number</em></p>

<pre><code class="language-no-highlight">Enter a request: &gt; 1 -5

The count of numbers in the list should be a natural number.

Supported requests:
- one natural number to print its properties;
- two natural numbers separated by space:
  - a starting number for the list;
  - a count of numbers in the list;
- two natural numbers and property to search for;
- 0 for the exit. 

Enter a request: </code></pre>

<p><strong>Example 4</strong></p>

<p><em>Entering an incorrect property</em></p>

<pre><code class="language-no-highlight">Enter a request: 1 5 XXX

The property 'XXX' is incorrect.
Available properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY]

Enter a request: </code></pre>

<p><strong>Example 5</strong></p>

<p><em>Prints the properties of a specific number.</em></p>

<pre><code class="language-no-highlight">Enter a request: 100000

Properties of 100,000
        even: true
         odd: false
        buzz: false
        duck: true
 palindromic: false
      gapful: true
         spy: false
</code></pre>

<p><strong>Example 6</strong></p>

<p><em>Prints 10 consecutive numbers starting from 100,000</em></p>

<pre><code class="language-no-highlight">Enter a request: 100000 10

         100,000 is even, duck, gapful
         100,001 is odd, duck, palindromic, gapful
         100,002 is even, buzz, duck
         100,003 is odd, duck
         100,004 is even, duck
         100,005 is odd, duck, gapful
         100,006 is even, duck
         100,007 is odd, buzz, duck
         100,008 is even, duck, gapful
         100,009 is odd, buzz, duck</code></pre>

<p><strong>Example 7</strong></p>

<p><em>Searching for 10 spy numbers starting from 100,000</em></p>

<pre><code class="language-no-highlight">Enter a request: 100000 10 spy

         111,126 is even, spy
         111,162 is even, spy
         111,216 is even, buzz, gapful, spy
         111,261 is odd, spy
         111,612 is even, gapful, spy
         111,621 is odd, spy
         112,116 is even, spy
         112,161 is odd, buzz, spy
         112,611 is odd, spy
         116,112 is even, gapful, spy</code></pre>

<p><strong>Example 8</strong></p>

<p><em>Searching for palindromic numbers</em></p>

<pre><code class="language-no-highlight">Enter a request: 100000 10 palindromic

         100,001 is odd, duck, palindromic, gapful
         101,101 is odd, buzz, duck, palindromic, gapful
         102,201 is odd, duck, palindromic, gapful
         103,301 is odd, duck, palindromic, gapful
         104,401 is odd, duck, palindromic, gapful
         105,501 is odd, duck, palindromic, gapful
         106,601 is odd, duck, palindromic, gapful
         107,701 is odd, duck, palindromic, gapful
         108,801 is odd, buzz, duck, palindromic, gapful
         109,901 is odd, duck, palindromic, gapful</code></pre>