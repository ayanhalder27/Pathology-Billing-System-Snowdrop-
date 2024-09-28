cd "$(CURRENT_DIRECTORY)"
del /S *.class
javac Start.java
java Start
del /S *.class