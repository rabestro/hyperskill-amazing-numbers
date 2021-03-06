<h2>Description</h2>

<p>Your next task is to determine whether a number is a Duck number. A duck number is a positive number that contains&nbsp;zeroes.&nbsp;For example, 321<strong>0</strong>, 805<strong>0</strong>896, 7<strong>0</strong>7<strong>0</strong>9 are Duck numbers. Note that a number with a leading 0&nbsp;is not a&nbsp;Duck number. So, numbers like 035 or 0212 are not Duck numbers. Although, 012<strong>0</strong>3 is Duck since it has a trailing 0.</p>

<p>In this stage, we need to simplify the way we display the information. We already have a number of properties that we need to show; we are going to add new features to our program in each stage. From now on, the card should follow this notation:</p>

<pre>
<code class="language-no-highlight">Properties of {number}
{property}: true/false
{property}: true/false
...
{property}: true/false</code></pre>

<p>In this stage, the properties are&nbsp;<code>even</code>, <code>odd</code>, <code>buzz</code>&nbsp;and&nbsp;<code>duck</code>. For <code>14</code>, the program output should look like this:</p>

<pre>
<code class="language-no-highlight">Properties of 14
        even: true
         odd: false
        buzz: true
        duck: false</code></pre>

<p>The property order, indentation,&nbsp;and spaces are not checked by the tests. The tests are also case-insensitive.&nbsp;</p>

<h2>Objectives</h2>

<p>Your program should print the properties of a natural number. In this stage, your program should:</p>

<ol>
	<li>Ask a user to enter a natural number;</li>
	<li>If the number is not natural, the program should print an error message;</li>
	<li>If the number is natural, the program&nbsp;should indicate the properties of the&nbsp;number;</li>
	<li>Finish the program after printing the message.</li>
</ol>

<h2>Examples</h2>

<p>The greater-than symbol followed by a space (<code>&gt;&nbsp;</code>)&nbsp;represents the user input. Note that it&#39;s not part of the input.</p>

<p><strong>Example 1:</strong></p>

<pre>
<code class="language-no-highlight">Enter a natural number:
&gt; -7
This number is not natural!</code></pre>

<p><strong>Example 2:</strong></p>

<pre>
<code class="language-no-highlight">Enter a natural number:
&gt; 15
Properties of 15
        even: false
         odd: true
        buzz: false
        duck: false</code></pre>

<p><strong>Example 3:</strong></p>

<pre>
<code class="language-no-highlight">Enter a natural number:
&gt; 14
Properties of 14
        even: true
         odd: false
        buzz: true
        duck: false</code></pre>

<p><strong>Example 4:</strong></p>

<pre>
<code class="language-no-highlight">Enter a natural number:
&gt; 102
Properties of 102
        even: false
         odd: true
        buzz: false
        duck: true</code></pre>
