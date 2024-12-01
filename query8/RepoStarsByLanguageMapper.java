import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class RepoStarsByLanguageMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

    private Text language = new Text();

    @Override
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        // Skip the header row
        if (key.get() == 0) {
            return;
        }

        // Split the input CSV by commas
        String[] columns = value.toString().split(",");

        // Assuming that the primary_language is at index 5 and stars_count is at index 1
        if (columns.length >= 6) {
            String lang = columns[5].trim();  // primary_language
            String starsStr = columns[1].trim();  // stars_count

            try {
                int stars = Integer.parseInt(starsStr);  // Convert stars_count to integer
                language.set(lang);  // Set the language as the key
                context.write(language, new IntWritable(stars));  // Emit language and stars count
            } catch (NumberFormatException e) {
                // Skip invalid star count
                return;
            }
        }
    }
}
