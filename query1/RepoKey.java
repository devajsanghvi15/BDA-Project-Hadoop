import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableUtils;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class RepoKey implements WritableComparable<RepoKey> {
    private Text repoName;
    private IntWritable starsCount;

    public RepoKey() {
        this.repoName = new Text();
        this.starsCount = new IntWritable();
    }

    public RepoKey(String repoName, int starsCount) {
        this.repoName = new Text(repoName);
        this.starsCount = new IntWritable(starsCount);
    }

    public Text getRepoName() {
        return repoName;
    }

    public IntWritable getStarsCount() {
        return starsCount;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        repoName.write(out);
        starsCount.write(out);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        repoName.readFields(in);
        starsCount.readFields(in);
    }

    @Override
    public int compareTo(RepoKey o) {
        int result = o.starsCount.compareTo(this.starsCount); // Descending order by starsCount
        if (result == 0) {
            result = this.repoName.compareTo(o.repoName); // Secondary sort by repoName (ascending)
        }
        return result;
    }

    @Override
    public String toString() {
        return repoName.toString() + "\t" + starsCount.toString();
    }
}
