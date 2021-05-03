<h2>Description</h2>

<p>In this stage, the program should check whether a number is a <strong>palindromic </strong>one. It is a symmetrical number, in other words, it stays the same whether we read from left or right. For example, <strong>17371 </strong>is a palindromic number. <strong>5</strong> is also a palindromic number. <strong><span style="color: #000000;">1234</span> </strong>though is not; if read it from right to left, it becomes <strong>4321</strong>. We need to add this new property to our program.</p>

<p>In previous stages, the program processed only one number. From now on, the program should accept a request from a user, analyze and execute it. The program should print <code>Enter a request</code> instead of asking for a natural number. The program should also continue the execution until a user enters a terminate command. Make it <strong>0</strong> (zero).</p>

<p>Your program should welcome users and print the instructions. At this point, make your program execute two commands. If a user enters a natural number, the program should indicate the properties of that number. If a user enters zero, then the program should exit. If a user enters a negative number by mistake, print an error message.</p>

<h2>Objectives</h2>

<p>In this stage, your program should:</p>

<ol>
	<li>Welcome a user;</li>
	<li>Display the instructions;</li>
	<li>Ask for a request;</li>
	<li>Terminate the program if a user enters zero;</li>
	<li>If a number is not natural, print an error message and display the instructions;</li>
	<li>Print the properties of the natural number;</li>
	<li>Continue the execution from step 3, after the request has been processed.</li>
</ol>

<p>The properties are <code>even</code>, <code>odd</code>, <code>buzz</code> , <code>duck</code> and <code>palindromic</code>. The tests won't check the order of properties, indentation, and spaces. You may format numbers as you like. Please, add the information below:</p>

<p><strong>Instructions:</strong></p>

<pre><code class="language-no-highlight">Supported requests:
- enter a natural number to know its properties; 
- enter 0 to exit.</code></pre>

<p><strong>Error message:</strong></p>

<pre><code class="language-no-highlight">The first parameter should be a natural number or zero.</code></pre>

<h2>Examples</h2>

<p>The greater-than symbol followed by a space (<code>&gt; </code>) represents the user input. Note that it's not part of the input.</p>

<pre><code class="language-no-highlight">Welcome to Amazing Numbers!

Supported requests:
- enter a natural number to know its properties; 
- enter 0 to exit.

Enter a request:
&gt; 17
Properties of 17
        even: false
         odd: true
        buzz: true
        duck: false
 palindromic: false

Enter a request:
&gt; 101
Properties of 101
        even: false
         odd: true
        buzz: false
        duck: true
 palindromic: true

Enter a request:
&gt; -56
This number is not natural!

Supported requests:
- enter a natural number to know its properties; 
- enter 0 to exit.

Enter a request:
&gt; 0

Goodbye!</code></pre>

<p><strong><span style="color: #ff4363;">Error message in the example does not follow the error message in the stage objectives.</span></strong></p>