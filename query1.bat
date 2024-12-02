cd query1 && (
    :: Compile the Java files
    javac -classpath "C:\Hadoop\hadoop-3.3.0\share\hadoop\common\*;C:\Hadoop\hadoop-3.3.0\share\hadoop\mapreduce\*" -d . RepoStarsMapper.java RepoStarsReducer.java RepoStarsDriver.java RepoKey.java && (
    
    :: Create the JAR file
    jar -cvf repo-stars-sort.jar *.class && (
    
    :: Run the Hadoop job
    hadoop jar repo-stars-sort.jar RepoStarsDriver /input_dir /query1 && (
    
    :: Check the output
    hadoop fs -cat /query1/part-r-00000 && (
    
    :: Move to the parent directory
    cd ..
    ))))
)
