### PROBLEM: SALES TAXES

Basic sales tax is applicable at a rate of 10% on all goods, except books, food, and medical
products that are exempt. Import duty is an additional sales tax applicable on all imported goods
at a rate of 5%, with no exemptions.

When I purchase items I receive a receipt which lists the name of all the items, and their price
(including tax), finishing with the total cost of the items, and the total amounts of sales taxes
paid. The rounding rules for sales tax are that for a tax rate of n%, a shelf price of p contains
(np/100 rounded up to the nearest 0.05) amount of sales tax.

Write an application that prints out the receipt details for these shopping baskets...


#### INPUT:
    Input 1:
    1 book at 12.49
    1 music CD at 14.99
    1 chocolate bar at 0.85
    
    Input 2:
    1 imported box of chocolates at 10.00
    1 imported bottle of perfume at 47.50
    
    Input 3:
    1 imported bottle of perfume at 27.99
    1 bottle of perfume at 18.99
    1 packet of headache pills at 9.75
    1 box of imported chocolates at 11.25

#### OUTPUT
    Output 1:
    1 book: 12.49
    1 music CD: 16.49
    1 chocolate bar: 0.85
    Sales Taxes: 1.50
    Total: 29.83
    
    Output 2:
    1 imported box of chocolates: 10.50
    1 imported bottle of perfume: 54.65
    Sales Taxes: 7.65
    Total: 65.15
    
    Output 3:
    1 imported bottle of perfume: 32.19
    1 bottle of perfume: 20.89
    1 packet of headache pills: 9.75
    1 imported box of chocolates: 11.85
    Sales Taxes: 6.70
    Total: 74.68
    
### SOLUTION 

#### Technological Stack
    - Build Tool: Gradle (With Wrapper linked to version 6.7)
    - Programming Language: Java, version 1.8
    - UnitTest Library: Junit 5.7 (Jupiter) + Parametrized Tests Features
    - Code coverage Library: Jacoco, version 0.8.6
    - Continuous Integration System used : Travis CI: 
   [link to Travis CI build service for the project](https://travis-ci.com/github/massimilianobocchieri/salestaxes)
    


To install the project dependencies, build and install the project artifact and make tests, including code coverage report with jacoco, please run, under the project root folder:

 `./gradlew build jacocoTestReport`

#### Application Acceptance Test

In order to test the application (Receipt) against the three code test scenarios an Acceptance Test **ReceiptShould** is available in the project tests suite

#### Architecture, Design Patterns, Principles and considerations
    I have tried to follow as much as possible the principles of Clean Code, SOLID Principles, with main focus on single Responsibility Principle and Dependency Inversion.
    I have used interfaces where possible.
    Order, Policy classes (tax, rounding rules) have been separated one eachother to allow code to be more testable and more adaptable to changes (OCP).
    
    Template Method Design Pattern has been used (see SalesTax)
          
    Some notes about Data Structures: the problem statement didn't require any specific performance so mainly dynamic collections (List) with sequential/positional (ArrayList) access have been used and that should be acceptable.
    For numbers, I have chosen BigDegimal because more suitable for finance/accounting.
    The choice of StringBuffer to handle with recepit formatting has been related to the fact that it's thread-safe compared to it's "cousin" Stringbuilder and, once again, no particular performance request was stated in the problem, so it should be fine.
           
    The development approach has been a mix between pure TDD and unitTest "after".
    
#### Comments
    Following UB (Uncle Bob) suggestions I  have tried to let the code being autoexplicable with clean names and serve as "live comments", I hope I succedeed.    

#### Possible Improvements
    - Input Handling and exceptions, Optional strategy (code has been written following mainly the happy path scenario, no defensive programming at this stage).
        