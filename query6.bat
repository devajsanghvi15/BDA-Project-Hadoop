cd query6 && (
    :: Compile the Java files
    javac -classpath "C:\Hadoop\hadoop-3.3.0\share\hadoop\common\*;C:\Hadoop\hadoop-3.3.0\share\hadoop\mapreduce\*" -d . LanguageWatchersMapper.java LanguageWatchersReducer.java LanguageWatchersDriver.java && (
    
    :: Create the JAR file
    jar -cvf lang-watchers-count.jar *.class && (
    
    :: Run the Hadoop job
    hadoop jar lang-watchers-count.jar LanguageWatchersDriver /input_dir /query6 && (
    
    :: Check the output
    hadoop fs -cat /query6/part-r-00000 && (
    
    :: Move to the parent directory
    cd ..
    ))))
)
