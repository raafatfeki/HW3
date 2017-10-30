# HW3

Compilation and execution:

Compilation with maven:
mvn compile

Execution with maven:
mvn exec:java -Dexec.mainClass="MyList" -Dexec.args="--key 1 --type i --list 1 2 3 4 5"

Execution with Java(terminal)
cd target/classes/
java -classpath ~/.m2/repository/commons-cli/commons-cli/1.4/commons-cli-1.4.jar:. MyList --key 12 --list 1 2 3 4 5 6 --type i

where:
	~/.m2/repository/commons-cli/commons-cli/1.4/commons-cli-1.4.jar is the path to the jar file that maven download. In fact, maven download the packages in a specific folder, so if you want to execute the code with java not with maven you must specify the jars path as a classpath.

