import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class RepoStarsByLanguageDriver {

    public static void main(String[] args) throws Exception {
        // Check for valid number of arguments
        if (args.length != 2) {
            System.err.println("Usage: RepoStarsByLanguageDriver <input path> <output path>");
            System.exit(-1);
        }

        // Set up configuration and job
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "Average Stars by Language");
        job.setJarByClass(RepoStarsByLanguageDriver.class);

        // Set the Mapper and Reducer classes
        job.setMapperClass(RepoStarsByLanguageMapper.class);
        job.setReducerClass(RepoStarsByLanguageReducer.class);

        // Set the output key and value types
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        // Set input and output paths
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        // Wait for job completion
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
