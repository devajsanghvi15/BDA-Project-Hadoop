import java.io.IOException;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Mapper;

public class PrimaryLanguageMapper extends Mapper<Object, Text, Text, IntWritable> {

    private final static IntWritable one = new IntWritable(1);
    private Text language = new Text();

    public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        // Split the input line into fields. Assuming the input is a CSV format.
        String[] fields = value.toString().split(",");
        
        // Index for primary_language, assuming primary_language is the 6th field.
        // Adjust if the format is different.
        String primaryLanguage = fields[5]; 
        
        if (primaryLanguage != null && !primaryLanguage.isEmpty()) {
            language.set(primaryLanguage);
            context.write(language, one);
        }
    }
}
