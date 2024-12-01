import java.io.IOException;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;
import java.util.Comparator;

public class PrimaryLanguageReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

    private Map<String, Integer> languageCountMap = new HashMap<>();

    public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int count = 0;
        
        // Count the number of occurrences for each language
        for (IntWritable val : values) {
            count += val.get();
        }
        
        languageCountMap.put(key.toString(), count);
    }

    @Override
    protected void cleanup(Context context) throws IOException, InterruptedException {
        // Sort the languages by the count in descending order
        languageCountMap.entrySet()
            .stream()
            .sorted((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()))
            .forEachOrdered(entry -> {
                try {
                    context.write(new Text(entry.getKey()), new IntWritable(entry.getValue()));
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            });
    }
}
