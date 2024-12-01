import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class RepoStarsByLanguageReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

    private IntWritable result = new IntWritable();

    @Override
    public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int totalStars = 0;
        int count = 0;

        // Sum the stars and count the repositories
        for (IntWritable val : values) {
            totalStars += val.get();
            count++;
        }

        // Calculate the average stars
        int avgStars = count > 0 ? totalStars / count : 0;
        result.set(avgStars);

        // Emit the programming language and average stars
        context.write(key, result);
    }
}
