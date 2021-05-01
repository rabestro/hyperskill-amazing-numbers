<h2><strong>Stage 8/8: </strong>Jumping&nbsp;<strong>number</strong></h2>

<h2>Description</h2>

<p>Our program is finished. It knows many interesting properties of numbers, knows how to calculate them, and looks for them. Now, when prompted, the user can list the properties that the number should have. To complete the program, let&#39;s add the ability to exclude a property from the search. Suppose if the user puts a minus in front of the property, this property should be absent from the number. For example, if the user specifies <code>palindromic -duck</code>&nbsp;it means that you need to search for palindromic numbers that are not ducks.</p>

<h3>Jumping&nbsp;Number</h3>

<p>A number is called a Jumping Number if all adjacent digits in it differ by 1. The difference between &lsquo;9&rsquo; and &lsquo;0&rsquo; is not considered as 1.<br />
All single-digit numbers are considered as Jumping Numbers. For example, 7, 8987, and 4343456 are Jumping numbers but 796 and 89098 are not.</p>

<p>...</p>

<h2>Objectives</h2>

<p><em>Your program should process the user requests</em></p>

<p>In this stage, your program should:</p>

<ol>
	<li>Welcome the user</li>
	<li>Display the instruction on how to use it.</li>
	<li>Ask the user to enter a request</li>
	<li>If the user enters an empty request print the instruction again</li>
	<li>If the user enters zero the program should finish.&nbsp;</li>
	<li>If any numbers are not natural then print an error message</li>
	<li>If incorrect property specified print the error message and list of available properties</li>
	<li>For&nbsp;one number&nbsp;calculate and print the card of the given number.</li>
	<li>For two numbers prints the list of numbers with their properties.</li>
	<li>For two numbers and properties print the list of numbers that have all of the properties.</li>
	<li>If minus is specified in front of a property, this property must not be present in the number.</li>
	<li>If the user specifies mutually excluded properties cancel the request and warn the user.&nbsp;</li>
	<li>Continue&nbsp;work from step 3.</li>
</ol>

<p>For the last stage, the property names are&nbsp;<code>even</code>, <code>odd</code>, <code>buzz</code>&nbsp;,&nbsp;<code>duck</code>,&nbsp;<code>palindromic</code>&nbsp;, <code>gapful</code>,&nbsp;<code>spy</code>&nbsp;,&nbsp;<code>harshad</code>,&nbsp;<code>armstrong</code> and <code><span style="color:#000000">jumping</span></code>.&nbsp;&nbsp;The order of properties, indentation,&nbsp;and spaces doesn&#39;t important for the tests. You may format numbers.</p>

<h3>The instruction</h3>

<pre>
<code class="language-no-highlight">Supported requests:
- one natural number to print the card;
- two natural numbers to print the list;:
  - a starting number for the list;
  - a count of numbers in the list;
- two natural numbers and properties to search for;
  - if a property name is preceded by a minus, that 
    property must not be present in the number.
- 0 for the exit. </code></pre>

<h3>The error messages</h3>

<pre>
<code class="language-no-highlight">The first parameter should be a natural number or zero.</code></pre>

<pre>
<code class="language-no-highlight">The second parameter should be a natural number.</code></pre>

<pre>
<code class="language-no-highlight">The property [SUN] is wrong.
Available properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, HARSHAD, JUMPING]</code></pre>

<pre>
<code class="language-no-highlight">The properties [HOT, SUN] are wrong.
Available properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, HARSHAD, JUMPING]</code></pre>

<pre>
<code class="language-no-highlight">The request contains mutually exclusive properties: [ODD, EVEN]
There are no numbers with all these properties at once.</code></pre>

<pre>
<code class="language-no-highlight">The request contains mutually exclusive properties: [GAPFUL, -GAPFUL]
There are no numbers with all these properties at once.</code></pre>

<p>...</p>

<h2>Examples</h2>

<p>The greater-than symbol followed by a space represents the user input. Note that it&#39;s not part of the input.</p>

<p><strong>Example 1</strong></p>

<pre>
<code class="language-no-highlight">Welcome to Amazing Numbers!

Supported requests:
- one natural number to print the card;
- two natural numbers to print the list;
  - a starting number for the list;
  - a count of numbers in the list;
- two natural numbers and properties to search for;
  - if a property name is preceded by a minus, that 
    property must not be present in the number.
- 0 for the exit. 

Enter a request: &gt; 210

Properties of 210
        even: true
         odd: false
        buzz: true
        duck: true
 palindromic: false
      gapful: false
         spy: false
     harshad: true
     jumping: true

Enter a request: &gt; 210 3

             210 is even, buzz, duck, harshad, jumping
             211 is odd
             212 is even, palindromic, jumping

Enter a request: &gt; 10000 5 spy

          11,125 is odd, spy
          11,133 is odd, spy, harshad
          11,152 is even, spy
          11,215 is odd, spy
          11,222 is even, spy

Enter a request: &gt; 999999 palindromic jumping

The second parameter should be a natural number.

Enter a request: &gt; 999999 5 palindromic jumping

       1,010,101 is odd, duck, palindromic, jumping
       1,012,101 is odd, duck, palindromic, jumping
       1,210,121 is odd, duck, palindromic, gapful, jumping
       1,212,121 is odd, palindromic, jumping
       1,232,321 is odd, palindromic, jumping

Enter a request: &gt; 999999 5 palindromic jumping even -duck

       2,121,212 is even, palindromic, jumping
       2,123,212 is even, buzz, palindromic, harshad, jumping
       2,321,232 is even, palindromic, jumping
       2,323,232 is even, palindromic, jumping
       2,343,432 is even, buzz, palindromic, harshad, jumping

Enter a request: &gt; 100 10 duck spy buzz

The request contains mutually exclusive properties: [SPY, DUCK]
There are no numbers with all these properties at once.

Enter a request: &gt; 100 20 spy palindromic even buzz -spy -duck

The request contains mutually exclusive properties: [-SPY, SPY]
There are no numbers with all these properties at once.

Enter a request: &gt; 1 10 -spy -duck

              11 is odd, palindromic
              12 is even, harshad, jumping
              13 is odd
              14 is even, buzz
              15 is odd
              16 is even
              17 is odd, buzz
              18 is even, harshad
              19 is odd
              21 is odd, buzz, harshad, jumping

Enter a request: 0

Goodbye!

Process finished with exit code 0</code></pre>

<p><strong>Example 2</strong></p>

<p><em>Print the list of natural numbers starting from the first parameter. The list has a number of records specified by the second parameter.&nbsp;</em></p>

<pre>
<code class="language-no-highlight">Enter a request: &gt; 7 4

               7 is odd, buzz, harshad, spy, armstrong
               8 is even, harshad, spy, armstrong
               9 is odd, harshad, spy, armstrong
              10 is even, duck, harshad</code></pre>

<p><strong>Example 3</strong></p>

<p><em>Print the list of numbers that have one specified property</em></p>

<pre>
<code class="language-no-highlight">Enter a request: &gt; 1000 10 armstrong

           1,634 is even, armstrong
           8,208 is even, duck, harshad, armstrong
           9,474 is even, armstrong
          54,748 is even, armstrong
          92,727 is odd, buzz, armstrong
          93,084 is even, duck, armstrong
         548,834 is even, armstrong
       1,741,725 is odd, gapful, armstrong
       4,210,818 is even, duck, armstrong
       9,800,817 is odd, buzz, duck, armstrong</code></pre>

<p><strong>Example 4</strong></p>

<p><em>Print the numbers that contain all of the specified properties</em></p>

<pre>
<code class="language-no-highlight">Enter a natural number: &gt; 1 5 odd buzz duck gapful harshad

          10,185 is odd, buzz, duck, gapful, harshad
          10,727 is odd, buzz, duck, gapful, harshad
          10,815 is odd, buzz, duck, gapful, harshad
          11,025 is odd, buzz, duck, gapful, harshad
          12,075 is odd, buzz, duck, gapful, harshad

Enter a natural number: &gt; 1 2 odd buzz spy harshad

               7 is odd, buzz, harshad, spy, armstrong
          31,311 is odd, buzz, harshad, spy
</code></pre>

<p><strong>Example 5</strong></p>

<pre>
<code class="language-no-highlight">Enter a natural number: 10 3 armstrong even -duck -harshad -buzz

           1,634 is even, armstrong
           9,474 is even, armstrong
          54,748 is even, armstrong</code></pre>
