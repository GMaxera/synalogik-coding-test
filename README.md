
Solution submitted by Mariagiovanna Mazzapioda (23rd December 2021)

# Software used:
- Java 8
- Junit 4.13.2
- (IDE) - IntelliJ IDEA 2021.3 (Ultimate Edition) 

# How to Run the source code
Main Class: Solution.java
Argument: "path/to/the/file/toElaborate.txt"

API can run without argument but, in that case, it will use the provided default example
stored in "./src/main/resources/basic_example.txt".


This is a Maven project, use standard maven command to build it:
> mvn compile

> mvn test


# What the API does
This class is going to provide some statistics about the content of given text file like:
total number of words, average word length, frequencies of length.


# What is considered a WORD?
A word is the results of the following steps:

1. Any sequence of consecutive characters found between: spaces, '[', ']', '{', '}', '(', ')'

2. A string resulting from step 1 is cleaned by removing both from the head and tail any character(s) 
   from the following list: '!', '\'', '*', '+', ',', '-', '.', '/', ':', ';', '<', '=', '>', '?', '^', '_', '|'
   NOTE: these characters will be removed ONLY IF they are found at the beginning or at the end of the string.
   NOTE2: these characters: "#", "$", "%", "&", "@", "~" are intentionally kept when found at the beginning or at
          the end of the string.