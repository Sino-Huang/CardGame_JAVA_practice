Reviewer: Sukai Huang (u5763216)

Component: <Task 7>
* Task 7: get the list of supporters for a given player after a sequence of moves
Author: Chenzhe Zhao (u6284020)

Review Comments:

1. The algorithm is very straight-forward. No redundant/ repeating steps can 
be found. 


2. The method can be decomposited into small pieces. Therefore one can debug and read the code more easily. 

3. The code is well-documented. The steps are documented/commented before each code 
block. It hence enhances the readability of the code.   

4. A good use of string.split() method to convert board placement string data 
into a list of card elements. Rather than string, list data type is clearer in 
terms of finding card difference. 

5. Also a clever use of split("(?<=\\G...)") grammar to 
separate the string.

7. The method re-used functions and data (hm map, updateBoard method) that are done in previous tasks.
This helps to save the space and makes the program more efficient.   

8. The code follows the Java naming convention (CamelCase). As such, the 
coding style is quite consistent. 