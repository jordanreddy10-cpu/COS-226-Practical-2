# COS-226-Practical-2
u25029666, u25407725, u25110782
## How to use
### Compiling the code
To compile the code go to the folder where all of the '.java' files are located, then open your terminal and run the command: 'javac *.java'
### Running the code
Once the code has been compiled run this command:
'java Main'
### Testing different locks
By default the 'Main.java' file will show the Filter Lock algorithm working. (4 Threads that each perform 5 increments resulting in a displayed counter that ends at 20).
To run 'BakeryLock', you need to uncomment where that lock is created in 'Main.java' and then comment out the creation of any other lock. This is so there is always only 1 lock object created and tested at a time.
