cd query5 && (
    :: Compile the Java files
    javac -classpath "C:\Hadoop\hadoop-3.3.0\share\hadoop\common\*;C:\Hadoop\hadoop-3.3.0\share\hadoop\mapreduce\*" -d . PullRequestsMapper.java PullRequestsReducer.java PullRequestsDriver.java && (
    
    :: Create the JAR file
    jar -cvf pr-count.jar *.class && (
    
    :: Run the Hadoop job
    hadoop jar pr-count.jar PullRequestsDriver /input_dir /query5 && (
    
    :: Check the output
    hadoop fs -cat /query5/part-r-00000 && (
    
    :: Move to the parent directory
    cd ..
    ))))
)
