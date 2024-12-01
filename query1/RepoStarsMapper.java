import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class RepoStarsMapper extends Mapper<Object, Text, RepoKey, NullWritable> {

    private RepoKey repoKey = new RepoKey();

    @Override
    public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        String[] fields = value.toString().split(",");
        if (fields.length >= 2) {
            try {
                String repoName = fields[0].trim(); // Repository name
                int starsCount = Integer.parseInt(fields[1].trim()); // Stars count
                repoKey = new RepoKey(repoName, starsCount);
                context.write(repoKey, NullWritable.get());
            } catch (NumberFormatException e) {
                // Ignore invalid rows
            }
        }
    }
}
