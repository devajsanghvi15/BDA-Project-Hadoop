import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class RepoPullRequestsMapper extends Mapper<Object, Text, RepoKey, NullWritable> {

    private RepoKey repoKey = new RepoKey();

    @Override
    public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        String[] fields = value.toString().split(",");
        if (fields.length >= 5) { // Ensure enough fields exist
            try {
                String repoName = fields[0].trim(); // Repository name
                int pullRequestsCount = Integer.parseInt(fields[4].trim()); // Pull requests count
                repoKey = new RepoKey(repoName, pullRequestsCount);
                context.write(repoKey, NullWritable.get());
            } catch (NumberFormatException e) {
                // Ignore invalid rows
            }
        }
    }
}
