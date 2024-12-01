import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class LanguageWatchersReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

    private IntWritable result = new IntWritable();

    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int sum = 0;

        // Sum up the watchers count for each primary language
        for (IntWritable val : values) {
            sum += val.get();
        }

        result.set(sum);
        // Emit key-value pair (primary_language, total_watchers)
        context.write(key, result);
    }
}
