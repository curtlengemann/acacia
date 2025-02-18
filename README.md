# Acacia
Acacia is an interpreted scripting language written completely in Java with over 600 unit tests that ensure correctness of the code. Acacia is designed to support complex objects and dynamic typing which enables flexibility and simplicity of code development.

## Highlights
Here are the main takeaways from this guide:
* [An overview of what Acacia is](#overview)
* [How to setup and use Acacia](#setup-and-usage-instructions)
* [Syntax tutorial](#syntax-tutorial)

## Overview

### Why Acacia?
Acacia has some distinct characteristics that make it wonderful to develop in.

First, Acacia is focused on simplicity in reading and writing code. For example, unlike other languages where unique variable declaration syntax is required to maintain correct variable scoping, Acacia allows the developer to declare and assign the variable all with the same syntax leading to less clutter in the code. Acacia also allows shorthand syntax for common binary operations such as string concatenation, substring removal and the math power operation.

Second, Acacia offers a high degree of flexibility. With dynamic typing, developers can easily generalize concepts leading to flexible APIs.
  
Lastly, developers are allowed to create enums. These user-defined data types provide simplistic ways to work with related constants that have a fixed range of possible values and aren't always provided in other dynamically typed languages.

## Setup and usage instructions
### Setup instructions

The Acacia Runtime Environment is utilized to write and run Acacia code. This runtime environment is compiled and run using the following steps.

> [!IMPORTANT]
> Setup requires the installation of a Java version 8 or greater and JUnit4 to run the code properly.

1. Compile the code
   - Open a terminal or command prompt
   - Navigate to the directory where the code is located
   - Use the following command to compile the class files into a folder named `bin`
```
javac -sourcepath ./src/ -d ./bin/ ./src/main/java/com/acacia/Main.java
```
2. Navigate to the newly created `bin` folder
```
cd bin
```
3. Run the compiled code
 ```
java main.java.com.acacia.Main
```

Once done correctly, the following will be seen in the terminal.

![image](https://github.com/user-attachments/assets/f3785148-df4d-465b-a58a-de5a31516ee8)

There are also over 600 tests that can be run using JUnit4 to ensure correctness of the code's behavior.

### Usage instructions

Once the Acacia Runtime Environment is running, valid Acacia code may be entered one line at a time into the terminal. Once the line is entered, press return to execute the line of code.

Example:

![image](https://github.com/user-attachments/assets/6941a4bd-cc94-4fdf-b6aa-7eb54316bd09)

> [!WARNING]
> The same enviroment will be used the whole time the runtime environment is running. Be careful to not accidently overwrite stored variables or functions.

There are some helpful commands to know to get started.
| Command | Description |
| ------- | ------------ |
| `acacia help` | Prints out a list of commands and actions a user may take in the Acacia Runtime Environment |
| `acacia run <path to file>` | Executes Acacia code specified in the file path |
| `exit` | Exits the Acacia Runtime Environment |

> [!NOTE]
> `helloWorld.acacia`, `palindromeFinder.acacia` and `car.acacia` are good examples of code written in the Acacia syntax and can be run using the `acacia run` command.

## Syntax tutorial
Like all other programming languages, Acacia programs consist of a list of statements to be executed by the computer. These statements consist of values, operators, expressions, keywords and comments. Statements are broken up by the parser based on the input from the developer. This input is broken up by whitespace. Therefore, multiple statements can exist on a line as long as all statements are composed of proper syntax. No semi-colons are used to break up statements.

Contents: 
* [Keywords](#keywords)
* [Comments](#comments)
* [Literals](#literals)
* [Variables](#variables)
* [Dynamic typing](#dynamic-typing)
* [Operators](#operators)
* [Functions](#functions)
* [Objects](#objects)
* [Enums](#enums)
* [Conditional statements](#conditional-statements)
* [Loops](#loops)
* [Variable scoping](#variable-scoping)
* [Native functions](#native-functions)

### Keywords
Acacia has a few necessary keywords to help parse and interpret the code. These are as follows:

| Keyword | Description |
|------|-------|
| var | Declares a variable that can be redeclared |
| final | Declares a variable that cannot be redeclared |
| if | Denotes a block of statements that will be executed if a condition is met |
| elif | Denotes a block of statements that will be executed if a condition is met and no previous condition is met |
| else | Denotes a block of statements that will be executed if no previous condition is met |
| fn | Declares a function that can be excecuted elsewhere in the code |
| return | Exits a function |
| enum | Denotes an object that represents a group of like properties |
| for | Denotes a block of statements that will be looped over while a condition is met |
| true | Denotes a true logical state |
| false | Denotes a false logical state |
| null | Denotes the absence of a value |

### Comments
Comments can be used to explain code in order to improve readability or block execution of code. Comments start with a `#` and end with a `#`. All content between the `#` symbols will be ignored.

Example:
```
# I am a fun comment
that spans many lines #
```

### Literals
Literals are a type of value that is fixed. There are 4 different types of literals.

| Literal | Description | Examples |
|------|-------|-------|
| Number | Numeric value with or without a decimal | 2, 100.01, .95 |
| String | Text written with tick marks or double quotes | \`hello\`, "world"|
| Boolean | A constant representation of a logical state | true, false |
| Null | A constant representation of the absence of a value | null |

### Variables
Variables are used to store data values. Variable names start with a letter or an underscore and may contain any combination of letters, numbers and underscores after that.

> [!NOTE]
> Variable names must not match any existing keyword or native function.

The keywords `var` and `final` are used to declare new variables. If the `final` keyword is used, the variable must be assigned a value and may not be reassigned to a new value.

An `=` is used to assign a value to either a new or pre-existing variable. If an `=` is used to assign a value to a new variable, that variable will be automatically declared in the scope in which it is assigned. Chaining can be used to assign multiple variables at once.

Examples:
```
var x
x = 2
```
```
final y = "Hello World"
```
```
a = true
```
```
var q = m = 2
```

### Dynamic Typing
Acacia has dynamic typing. This means that the same variable can be used to store different data types. There are 7 possible data types.

  1. Number
  2. String
  3. Boolean
  4. Null
  5. Function
  6. Object
  7. Enum

Example:
```
x = 2 # x is a number #
x = "hello world" # x is now a string #
x = true # x is now a boolean #
```

> [!TIP]
> Try to use descript variable names to be clear in what the variable is used for. While the flexibility of dynamic typing is useful, it can lead to hard to maintain code if variables aren't clearly named. 

### Operators
Operators are used to perform different logical and mathematical computations. There are 4 different types of operators. These are arithmetic, assignment, comparison and logical. Operator precedence matches that of school mathematics. Multiplication and division are completed first before addition and subtraction. Likewise, expressions in parentheses are evaluated before all other expressions.

| Operator | Type | Description | Examples |
|------|-------|-------|-------|
| + | Arithmetic | Adds values | 2 + 4, "hello" + "world" |
| - | Arithmetic  | Subtracts values | 12 - 3, "hello" - "world" |
| * | Arithmetic | Multiplies values | 3 * 7 |
| / | Arithmetic | Divides values | 21 / 7 |
| % | Arithmetic | Modulus division of values | 17 % 4 |
| ^ | Arithmetic | Exponential | 3 ^ 2 |
| ++ | Arithmetic | Adds 1 to a value | x++ |
| -- | Arithmetic | Subtracts 1 from a value | x-- |
| = | Assignment | Assigns a value to the left hand side | x = y |
| += | Assignment | Adds the right hand side to the left hand side and assigns the value to the left hand side | x += y |
| -= | Assignment | Subtracts the right hand side from the left hand side and assigns the value to the left hand side | x -= y |
| *= | Assignment | Multiplies the right hand side and the left hand side and assigns the value to the left hand side | x *= y |
| /= | Assignment | Divides the right hand side from the left hand side and assigns the value to the left hand side | x /= y |
| %= | Assignment | Modulus division of the right hand side from the left hand side and assigns the value to the left hand side | x %= y |
| ^= | Assignment | Takes the left hand side to the power of the right hand side and assigns the value to the left hand side | x ^= y |
| == | Comparison | Checks equality | 2 == 2 |
| != | Comparison | Checks not equal | 2 != 3 |
| > | Comparison | Greater than | 3 > 2 |
| < | Comparison | Less than | 2 < 3 |
| >= | Comparison | Greater than or equal to | 4 >= 4 |
| <= | Comparison | Less than or equal to | 2 <= 4 |
| & | Logical | Logical and | true & true |
| \| | Logical | Logical or | true \| false |
| ! | Logical | Logical not | !true |
| &= | Assignment | Logical and of left and right hand sides and assigns the value to the left hand side | x &= y |
| \|= | Assignment | Logical or of left and right hand sides and assigns the value to the left hand side | x \|= y |

Numbers can be acted upon by arithmetic, assignment (except `&=` and `|=`) and comparison operators.

Examples:
```
2 + 4 # Results in 6 #
```
```
x = 2 # x equals 2 #
x++ # x equals 3 #
```
```
x = 2 # x equals 2 #
x ^= 3 # x equals 8 #
```
```
2 > 3 # Results in false #
```

Strings are acted upon in a more unique way. They can concatenated by `+`. Other data types will also be transformed into strings and concatenated with the string operand if a string is added to another data type.

Examples:
```
"hello" + "world" # Results in "helloworld" #
```
```
"hello" + 3 # Results in "hello3" #
```

All occurances of a substring can be removed by `-`.

Example:
```
"apple" - "p" # Results in "ale" #
```

Comparison operators are used to either lexiographically compare strings or `==` and `!=` can be used to directly compare equality.

Examples:
```
"a" > "b" # Results in false #
```
```
"bb" >= "ba" # Results in true #
```
```
"apple" == "apple" # Results in true #
```

Booleans can be acted upon by logical operators, logical assignment operators, `==`, and `!=`.

Examples:
```
!true # Results in false #
```
```
false | true # Results in true #
```
```
false == false # Results in true #
```
```
x = true # x equals true #
x &= false # x equals false #
```

Null can be acted upon by any operator and it always results in `null`.

Example:
```
null + 2 # Results in null #
```

### Functions
Functions are a block of statements that are grouped together and named to perform some task. Functions are given a set of parameters and produce some sort of output based on those parameters. This statement block is executed when something calls it. 

A function is defined with the `fn` keyword. After the `fn` keyword comes the name of the function and a comma separated parameter list surrounded by parentheses. Inside the function, the parameters act as local variables. Lastly, a statement block that defines what the function does is surrounded by two curly braces. 

> [!NOTE]
> Function naming rules match the naming rules for variables.

Syntax:
```
fn functionName(parameter1, parameter2) {
  # Code to execute when the function is called. #
}
```

A function is called using `()` after the name of the declared function and supplying a comma seperated list of arguments identical in length to the list of parameters. The arguments supplied will act as the initial values of the parameter variables in the function.

Example:
```
add(2, 4) # Call the add function with the arguments of 2 and 4 #
```

When a function reaches a `return` statement, the function will stop executing and pass that value back to the caller.

> [!NOTE]
> In Acacia, a `return` statement isn't needed to return a value. The last executed value in the last executed statement block will be returned automatically.

Example:
```
# Define the function add #
fn add(x, y) {
  # add will return the value of x + y back to whatever called it #
  return x + y
}

var result = add(2, 4) # Call the add function with the arguments of 2 and 4. Result will have the value of 6. #
```

> [!NOTE]
> Accessing the function without the parentheses will access the function definition itself and not call the function.

### Objects
In Acacia, objects are a type of value that can contain many other values, including other objects. Objects are comprised of `key: value` pairs that are known as the properties and methods on the object. Properties are named values, while methods are functions that are stored as properties. These properties can consist of any other data type in the language. Acacia objects are mutable. Properties are allowed to be added or modified.

Objects can be created by writing a list of `key: value` pairs or through shorthand by just writing a `key` and the value will be determined by what value the matching variable to that key has in the current scope.
Examples:
```
final car = {color: "red", mileage: 50000}
```
```
final person = {name: "James", age: 31, eyes: {color: "blue", vision: "blurry"}} # Note that the key "eyes" contains a nested object #
```
```
x = 2
final a = {x} # a will have a key, "x", that has the value 2 #
```

Object properties can be assigned and accessed through member expressions. There are two different types of member expressions. The first is non-computed member expressions which are represented through dot notation. The second are computed member expressions which are represented through using `[]`. Non-computed member expressions use the property name found after the `.`. Computed member expressions resolve whatever expression is inside the `[]` first before attempting to access a property. Note that the value of the expression inside of the `[]` must result in a string value.

Non-computed member expression syntax:
```
objectName.propertyName
```

Computed member expression syntax:
```
objectName["propertyName"]
```

Examples:
```
car.color = "blue" # Either overrides or creates a new property, "color", on the "car" object #
```
```
final carColor = car.color # Assigns the value from the property "color" on the "car" object to the variable carColor #
```
```
car.paint.color = "blue" # Either overrides or creates a new property, "color", on the "paint" object. The "paint" object is nested within the "car" object. #
```
```
car["color"] = "blue" # Either overrides or creates a new property, "color", on the "car" object #
```
```
final carColor = car["color"] # Assigns the value from the property "color" on the "car" object to the variable carColor #
```
```
car[getColor()] # Accesses the property returned by the "getColor" function #
```
```
car["paint"]["color"] = "blue" # Either overrides or creates a new property, "color", on the "paint" object. The "paint" object is nested within the "car" object. #
```

Object methods can be assigned and accessed in the same way as object properties. Calling the method using `()` will execute the function and simply accessing the property will return the function definition.

Examples:
```
car.getColor() # Calls the "getColor" method on the car object #
```
```
car.getColor # Accesses the function definition #
```

> [!TIP]
> As a best practice, try to declare all objects as `final`. This will help prevent from accidentally changing the type of the variable.

### Enums
An enum is a special type of object that represents a set of unchangeable variables. Enums can be created using the `enum` keyword and separating the constants with a comma. No value can be assigned to these variables as they will be automatically assigned the number that represents their index in the list. Additonally, no new keys may be added once the enum is initialized.

Example:
```
daysOfTheWeek = enum {
  SUNDAY,
  MONDAY,
  TUESDAY,
  WEDNESDAY,
  THURSDAY,
  FRIDAY,
  SATURDAY,
}
```

Enums can be accessed with either the dot syntax or the bracket syntax.

Examples:
```
currentDay = daysOfTheWeek.MONDAY # current day is assigned 1 #
```
```
currentDay = daysOfTheWeek["THURSDAY"] # current day is assigned 4 #
```

### Conditional statements
In Acacia, conditional statements are used to execute different statement blocks based on certain conditions being met.

`if` is used to denote a block of code to be executed if a given condition is satisfied.

`elif` is used to denote a block of code to be executed if a given condition is satisfied and no previous condition is.

`else` is used to denote a block of code to be executed if no previous condition is satisfied.

> [!NOTE]
> Conditions may be surrounded or not surrounded by parentheses.

Syntax:
```
if condition {
  # Executes if condition is true #
} elif (condtion2) {
  # Executes if condition is false and condition2 is true #
} else {
  # Executes if condition and condition2 are false #
}
```

Example:
```
if cookies >= 2 {
  message = "Time to eat cookies"
} elif cookies == 1 {
  message = "We are low on cookies!
} else {
  message = "Out of cookies!"
}
```

### Loops
Loops are useful for executing the same code over and over again given a condition is met. Once the condition is no longer met, the loop is exited.

> [!NOTE]
> Conditions may be surrounded or not surrounded by parentheses.

Syntax:
```
for (condition) {
  # Executes as long as condition is true #
}
```

Example:
```
x = 10
for x > 0 {
  # x counts down from 10 to 0 before exiting the loop #
  x--
}
```

### Variable scoping
Variable scoping determines where a variable can be accessed. The types of scoping are global, function and block. Since variables have different scopes, different variables with the same name can be used in different areas of the code.

Variables in the global scope can be accessed anywhere in the program.

> [!TIP]
> As a best practice, try to minimize the use of global variables. It is very easy to accidentally overwrite a global variable while not intending to.

Example:
```
var x = 3

if true {
  # x can be accessed here because it is global #
  x++
}
```

Variables in the function scope can be accessed anywhere inside the function they are declared in.

Example:
```
fn add() {
  # x and y can be accessed in this function #
  var x = y = 2
  x + y
}
# x and y cannot be accessed here #
```

Variables in the block scope can be accessed anywhere inside the statement block they are declared in.

Example:
```
if true {
  # x can be accessed here #
  x = 3
}
# x cannot be accessed here #
```

### Native functions
Acacia has a few native functions that are used to assist a developer in doing common tasks. These are `print`, `len` and `charAt`.

`print` takes in any number of comma separated parameters and outputs them to standard output in a space delineated fashion.

Example:
```
print(2, "hello", true) # Outputs to standard output 2 hello true #
```

`len` takes in a single string parameter and returns the number of characters in the string.

Example:
```
len("hello world") # Returns 11 #
```

`charAt` takes two parameters and returns a string representation of the character at that index. The first parameter must be the string to search and the second parameter is the index to search for. If the index is out of range, an error will be output to standard output.

Example:
```
charAt("apple", 3) # Returns "l" #
```

## Dependency Licenses
Licenses for dependencies can be found in the dependencyLicenses folder. This project depends on JUnit4 and Hamcrest which are both open source libraries.
