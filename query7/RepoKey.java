import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class RepoKey implements WritableComparable<RepoKey> {
    private String repoName;
    private int pullRequestsCount;

    public RepoKey() {
    }

    public RepoKey(String repoName, int pullRequestsCount) {
        this.repoName = repoName;
        this.pullRequestsCount = pullRequestsCount;
    }

    public String getRepoName() {
        return repoName;
    }

    public void setRepoName(String repoName) {
        this.repoName = repoName;
    }

    public int getPullRequestsCount() {
        return pullRequestsCount;
    }

    public void setPullRequestsCount(int pullRequestsCount) {
        this.pullRequestsCount = pullRequestsCount;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeUTF(repoName);
        out.writeInt(pullRequestsCount);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        repoName = in.readUTF();
        pullRequestsCount = in.readInt();
    }

    @Override
    public int compareTo(RepoKey o) {
        // Sort by pull requests count in descending order. If counts are equal, sort by repo name.
        int result = Integer.compare(o.pullRequestsCount, this.pullRequestsCount);
        if (result == 0) {
            result = this.repoName.compareTo(o.repoName);
        }
        return result;
    }

    @Override
    public String toString() {
        return repoName + "\t" + pullRequestsCount;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof RepoKey) {
            RepoKey other = (RepoKey) obj;
            return repoName.equals(other.repoName) && pullRequestsCount == other.pullRequestsCount;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return repoName.hashCode() + pullRequestsCount;
    }
}
