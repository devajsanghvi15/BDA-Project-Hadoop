cd query8 && (
    :: Compile the Java files
    javac -classpath "C:\Hadoop\hadoop-3.3.0\share\hadoop\common\*;C:\Hadoop\hadoop-3.3.0\share\hadoop\mapreduce\*" -d . RepoStarsByLanguageMapper.java RepoStarsByLanguageReducer.java RepoStarsByLanguageDriver.java && (
    
    :: Create the JAR file
    jar -cvf repo-stars-by-lang.jar *.class && (
    
    :: Run the Hadoop job
    hadoop jar repo-stars-by-lang.jar RepoStarsByLanguageDriver /input_dir /query8 && (
    
    :: Check the output
    hadoop fs -cat /query8/part-r-00000 && (
    
    :: Move to the parent directory
    cd ..
    ))))
)
