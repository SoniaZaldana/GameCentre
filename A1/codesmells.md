# Markdown

Markdown is a plain-text file format. There are lots of programming tools that use Markdown, and it's useful and
easy to learn. Hash marks (the number sign) indicate headers. Asterisks indicate lists.

# Template

Use the following Code Smell template (copy and paste it at the end of this file and then edit it; don't include the "Begin template" or "End template" lines):

==== Begin template ====
## Code Smell: [Write the code smell name]

### Code Smell Category: [Write the code smell category name]

### List of classes and line numbers involved:

* [Write a class and list of line numbers, one class per asterisk, that describe the smell]

### Description:

[In your own words, explain how this particular code smells.]

### Solution:

[In your own words, explain how you might solve this code smell:
how would you refactor the code?]
==== End template ====

# List of code smells
## Code smell: Long method
## Category: Bloater
## Location: Class: WarehouseSimulation, Line 41, method start.
## Description: Too many lines. More than 10-15 lines for a method make it too bloated.
## Solution: Extract submethods.

## Code smell: Switch statement
## Category: Object-orientation abuser.
# Objected oriented abuser in warehouseSimulation.
# Location: Line 65-89 in WreHouseSimulation
# Description:Both if statements which check for the type of worker do the same actions, which results in a complex
# and long if statement, with a lot of very similar code.
# Solution: Use polymorphism and make Picker and Sequencer both subclasses of Worker, so you don't even need
# to use an switch statement and instead execute the same code on Worker. Make Worker abstract and define in it the
# methods that both subclasses already implement. At Runtime, the code for the appropriate subclass will be executed.