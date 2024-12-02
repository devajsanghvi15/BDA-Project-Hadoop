cd query7 && (
    :: Compile the Java files
    javac -classpath "C:\Hadoop\hadoop-3.3.0\share\hadoop\common\*;C:\Hadoop\hadoop-3.3.0\share\hadoop\mapreduce\*" -d . RepoPullRequestsMapper.java RepoPullRequestsReducer.java RepoPullRequestsDriver.java RepoKey.java && (
    
    :: Create the JAR file
    jar -cvf repo-pr-count.jar *.class && (
    
    :: Run the Hadoop job
    hadoop jar repo-pr-count.jar RepoPullRequestsDriver /input_dir /query7 && (
    
    :: Check the output
    hadoop fs -cat /query7/part-r-00000 && (
    
    :: Move to the parent directory
    cd ..
    ))))
)
