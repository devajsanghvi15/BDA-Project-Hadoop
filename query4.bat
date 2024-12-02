cd query4 && (
    :: Compile the Java files
    javac -classpath "C:\Hadoop\hadoop-3.3.0\share\hadoop\common\*;C:\Hadoop\hadoop-3.3.0\share\hadoop\mapreduce\*" -d . ForksMapper.java ForksReducer.java ForksDriver.java && (
    
    :: Create the JAR file
    jar -cvf forks-count.jar *.class && (
    
    :: Run the Hadoop job
    hadoop jar forks-count.jar ForksDriver /input_dir /query4 && (
    
    :: Check the output
    hadoop fs -cat /query4/part-r-00000 && (
    
    :: Move to the parent directory
    cd ..
    ))))
)
