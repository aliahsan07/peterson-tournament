# Peterson's Tournament Algorithm

This project contains implementation of Peterson's Tournament algorithm to solve mutual exclusion of n processes (n >= 2). The idea is built on top of 2-process mutual exclusion. The project is implemented in Java 8. 

## The problem 
At any given time, number of processes try to access the critical section (Critical section is simply a shared counter). But we can only let one process access the critical section at a time while also preserving liveness constraints. 

## Requirements
Java 8 or higher

## How to run
To run the project execute:
sh job-script.sh 

Inside the shell script at line 15 "java Main" takes two arguments 
1) number of iterations per process
2) Number of threads to run 

For example
`java Main 1000 4`
- 1000 times each process will increment the counter
- 4 threads

The output is the count of shared counter at the end of algorithm execution. For this example the output is 4000. Also for analysis purposes the program outputs the throughput execution time and appends it to results.csv file.

You could also directly compile the Java files and run i.e (instead of executing bash script):

`javac Main.java`

`java Main 1000 4`
