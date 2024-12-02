cd query3 && (
    :: Compile the Java files
    javac -classpath "C:\Hadoop\hadoop-3.3.0\share\hadoop\common\*;C:\Hadoop\hadoop-3.3.0\share\hadoop\mapreduce\*" -d . StarsMapper.java StarsReducer.java StarsDriver.java && (
    
    :: Create the JAR file
    jar -cvf stars-count.jar *.class && (
    
    :: Run the Hadoop job
    hadoop jar stars-count.jar StarsDriver /input_dir /query3 && (
    
    :: Check the output
    hadoop fs -cat /query3/part-r-00000 && (
    
    :: Move to the parent directory
    cd ..
    ))))
)
