<h2>Description</h2>

<h3>Gapful Number</h3>

<p>In addition to the previous properties, you should calculate one more - <strong>Gapful Number</strong>. It&nbsp;is a number&nbsp;of at least 3 digits such that it is divisible by the concatenation of its first and last digit. So the number 12 is not a gapful because it has only two digits and 132 is the gapful number because <strong>1</strong>3<strong>2</strong> % 12 == 0. Another example of gapful number is <strong>7</strong>88<strong>1 </strong>% 71 == 0.&nbsp;</p>

<h3>Improving the program</h3>

<p>Until this stage, the program could only process one number at a time. Now the user should be able to enter either one number to calculate the properties of this number or two numbers separated by space&nbsp;indicating the beginning and length of the list of numbers. When entering a single number, the program should work the same as before. The program should analyze the number and print all properties of that number. As before, if the user enters zero then the program should exit. If the user enters two numbers, then the first number will denote the starting number of the list, and the second number represents the length of this list. Information about each number should be printed on one line in the format:</p>

<pre>
<code class="language-no-highlight">{number}: {property}, {property}, ...{property}
</code></pre>

<p>Please note, that in the one-line format you should print only names of&nbsp;properties represented in the number.&nbsp;</p>

<p>Here is an example of a list of two numbers:</p>

<pre>
<code class="language-no-highlight">             140 is even, buzz, duck, gapful
             141 is odd, palindromic</code></pre>

<h3>...</h3>

<h2>Objectives</h2>

<ol>
</ol>

<p><em>Your program should process the user requests</em></p>

<p>In this stage, your program should:</p>

<ol>
	<li>Welcome the user</li>
	<li>Display the instruction on how to use it.</li>
	<li>Ask the user to enter a request</li>
	<li>If the user enters zero the program should finish.&nbsp;</li>
	<li>If any numbers are not natural then print an error message and display the instruction</li>
	<li>For&nbsp;one number&nbsp;calculate and print the properties of the given number.</li>
	<li>For two numbers prints the list of numbers with properties.</li>
	<li>After the request is processed continue program execution&nbsp;from step 3.</li>
</ol>

<p>For the current stage, the property names are&nbsp;<code>even</code>, <code>odd</code>, <code>buzz</code>&nbsp;,&nbsp;<code>duck</code>,&nbsp;<code>palindromic</code>&nbsp;and&nbsp;<code>gapful</code>. The order of properties, indentation,&nbsp;and spaces doesn&#39;t important for the tests. You may format numbers.</p>

<p><strong>&nbsp;The instruction</strong></p>

<pre>
<code class="language-no-highlight">Supported requests:
- natural number to print the card; 
- two natural numbers to print the list:
  - the first parameter represents starting number;
  - the second parameters is count of numbers in the list;
- parameters should be separated by space;
- 0 for the exit.</code></pre>

<p><strong>The error messages</strong></p>

<pre>
<code class="language-no-highlight">The first parameter should be a natural number or zero.</code></pre>

<pre>
<code class="language-no-highlight">The second parameter should be a natural number.</code></pre>

<p>...</p>

<h2>Examples</h2>

<p>The greater-than symbol followed by a space represents the user input. Note that it&#39;s not part of the input.</p>

<p><strong>Example 1</strong></p>

<pre>
<code class="language-no-highlight">Welcome to Amazing Numbers!

Supported requests:
- one natural number to print its properties;
- two natural numbers separated by space:
  - a starting number for the list;
  - a count of numbers in the list;
- 0 for the exit. 

Enter a request: 7

Properties of 7
        even: false
         odd: true
        buzz: true
        duck: false
 palindromic: true
      gapful: false

Enter a request: 100 10

             100 is even, duck
             101 is odd, duck, palindromic
             102 is even, duck
             103 is odd, duck
             104 is even, duck
             105 is odd, buzz, duck, gapful
             106 is even, duck
             107 is odd, buzz, duck
             108 is even, duck, gapful
             109 is odd, duck

Enter a request: exit

This number is not natural!

Supported requests:
- one natural number to print its properties;
- two natural numbers separated by space:
  - a starting number for the list;
  - a count of numbers in the list;
- 0 for the exit. 

Enter a request: 0

Goodbye!</code></pre>

<p><strong>Example 2</strong></p>

<pre>
<code class="language-no-highlight">Welcome to Amazing Numbers!

Supported requests:
- one natural number to print its properties;
- two natural numbers separated by space:
  - a starting number for the list;
  - a count of numbers in the list;
- 0 for the exit. 

Enter a request: &gt; 999999 9

         999,999 is odd, buzz, palindromic, gapful
       1,000,000 is even, duck, gapful
       1,000,001 is odd, duck, palindromic
       1,000,002 is even, duck
       1,000,003 is odd, duck
       1,000,004 is even, duck
       1,000,005 is odd, duck, gapful
       1,000,006 is even, buzz, duck
       1,000,007 is odd, buzz, duck

Enter a request: &gt; 999999

Properties of 999,999
        even: false
         odd: true
        buzz: true
        duck: false
 palindromic: true
      gapful: true

Enter a request: &gt; 999999 0

The count should be a natural number.

Supported requests:
- one natural number to print its properties;
- two natural numbers separated by space:
  - a starting number for the list;
  - a count of numbers in the list;
- 0 for the exit. 

Enter a request: &gt; 0

Goodbye!</code></pre>

<p>...</p>
