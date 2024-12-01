import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class PullRequestsReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

    private IntWritable result = new IntWritable();

    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int sum = 0;

        // Sum up the pull requests for the year
        for (IntWritable val : values) {
            sum += val.get();
        }

        result.set(sum);
        // Emit key-value pair (year, total_pull_requests)
        context.write(key, result);
    }
}
