# Markdown

Markdown is a plain-text file format. There are lots of programming tools that use Markdown, and it's useful and
easy to learn. Hash marks (the number sign) indicate headers. Asterisks indicate lists.

# Template

==== Begin template ====

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
## Location: Class: WarehouseSimulation, Line 41, method  start(WarehouseManager m,TranslationTable t).
## Description: Too many lines. More than 10-15 lines for a method make it too bloated.
## Solution: Extract submethods. Extract a method to deal with events which are orders, and one to deal with
## events which are pickers and sequencers.

## Code smell: Large Class
## Category: Bloater
## Location: Class WarehouseManager
## Description: Class has only one responsibility(manage warehouse), but it is too broad,
## so it includes a lot of sub-responsibilities as a result.
## Solution: Split the methods in WarehouseManager into 2 classes: A class PickingManager, and a class SequencingManager.
## Class PickingManager will be in charge of addPickingOrders(), addPicker(), processPickEvent(), pickertoMarshalling().
## Class SequencingManager will be in charge of addSequencingOrders(), addSequencer() sequencerSequenced(), processScanEvent.
## Both classes have common responsibilities, so make them subclasses of the superclass WarehouseManager(alternatively
## WareHouseManager can also be an interface with the given methods), and define
## in it, the abstract methods addWorker(), addOrders(), processEvent(), which both classes will implement.



## Code smell: Switch statement
## Category: Object-orientation abuser.
## Objected oriented abuser in warehouseSimulation.
## Location: Line 65-89 in WareHouseSimulation
## Description:Both if statements which check for the type of worker do the same actions, which results in a complex
## and long if statement, with duplicate code.
## Solution: Use polymorphism and make Picker and Sequencer both subclasses of Worker, so you don't need
## to use a switch statement and instead execute the same code on Worker. Make Worker abstract and define in it the
## methods that both subclasses already implement. The code for the appropriate subclass will be executed when run.


## Code smell: Data Class
## Category: Dispensable
## Location: Class: Order
## Description: Contains only "fields and crude methods for accessing them (getters and setters)."(sourcemaking)
## Order does not contain any functionality apart for containing data for other classes.
## Solution: Find relevant methods and include them in the Order Class. Relevant methods could be cancelOrder() -> void,
## isOrdered() -> bool, etc


## Code smell: Duplicate Code
## Category: Dispensable
## Location Class: Class PickerOrderList and SequencerOrderList
## Description: addOrder(), size(), pickerSKUMatches()/sequencerSKUMatches, getIDs() follow the same logic. Only
## difference is the name of the variables.
## Solution: Extract a SuperClass OrderList which can be abstract and declare the methods both of them have in common.

