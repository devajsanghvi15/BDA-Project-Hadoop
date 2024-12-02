cd query2 && (
    :: Compile the Java files
    javac -classpath "C:\Hadoop\hadoop-3.3.0\share\hadoop\common\*;C:\Hadoop\hadoop-3.3.0\share\hadoop\mapreduce\*" -d . PrimaryLanguageMapper.java PrimaryLanguageReducer.java PrimaryLanguageDriver.java && (
    
    :: Create the JAR file
    jar -cvf primary-language-count.jar *.class && (
    
    :: Run the Hadoop job
    hadoop jar primary-language-count.jar PrimaryLanguageDriver /input_dir /query2 && (
    
    :: Check the output
    hadoop fs -cat /query2/part-r-00000 && (
    
    :: Move to the parent directory
    cd ..
    ))))
)
