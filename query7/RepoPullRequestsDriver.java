import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class RepoPullRequestsDriver {
    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            System.err.println("Usage: RepoPullRequestsDriver <input path> <output path>");
            System.exit(-1);
        }

        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "Top 1000 Repos by Pull Requests");
        job.setJarByClass(RepoPullRequestsDriver.class);
        job.setMapperClass(RepoPullRequestsMapper.class);
        job.setReducerClass(RepoPullRequestsReducer.class);

        job.setMapOutputKeyClass(RepoKey.class);
        job.setMapOutputValueClass(NullWritable.class);
        job.setOutputKeyClass(RepoKey.class);
        job.setOutputValueClass(NullWritable.class);

        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
