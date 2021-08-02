# bid-data

This project has been developed using Apache Camel , Springboot and Java 8.

# Quick Start Guide

Below are the steps to be followed to start big-data application
- Install Maven
- Install Git
- git clone https://github.com/ayush8878/bid-data.git
- correct LOG_PATH in log4j2.xml for windows machine
- mvn clean install

To run application use below command with custom config use below command
- Without commandline argument
java -jar target/big-data-1.jar
- With commandline argument
java -jar target/big-data-1.jar --input.path={{input_path}} --processing.path={{processing_path}} --processed.path={{processed_path}} --error.path={{error_path}} --report.path={{report_path}} 

NOTE: Incase any path is not provided , default path will be referred

# Application Details

- Input Files will be processed from provided input directory or default which will further create below sub-directories
```
├───input
│   ├───processed
│   │   └───20210802
│   └───processing
|   |    └───20210802  
|   └───error
|        └───20210802 
├───report
│   └───20210802 
```
- Application only support Files with text content as of now but can be extented to support more
- At a time multiple files can process simultaneously
- Duplicate files will not be processed
- In case of error file will move to error directory
- During processing file will be moved to procesing directory
- On successful processing file will be moved to processed directory and statistics will be stored in reports

Below are the statistics Report will contain
-wordCount=1
-specialCharacterCount=11
-dotCount=0
-mostUsedWord=hello
