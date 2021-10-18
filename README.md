# README
To build use:
`mvn package`

To run use:
`./run`

To start the server use:
`./run --gui [--port=<port>]`

Hee Jae's Notes:
I was able to traverse the KD Tree using the search algorithm involving axis and euclidean distance of coordinate values.
I also created a generic REPL that will work for the specific commands of `similar <k> <height> <weight> <age>` and 
`similar <k> <userID>`. 

With more time, I would have made following updates: (1) create command handler classes and abstract out the commands 
using a hashmap. (2) create an extensible REPL class. (3) create more tests on the KDTreeSearch class.
