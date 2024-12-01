import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class RepoPullRequestsReducer extends Reducer<RepoKey, NullWritable, RepoKey, NullWritable> {

    private int count = 0;
    private static final int TOP_LIMIT = 1000; // Limit to top 1000 repos

    @Override
    public void reduce(RepoKey key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {
        if (count < TOP_LIMIT) {
            context.write(key, NullWritable.get());
            count++;
        }
    }
}
