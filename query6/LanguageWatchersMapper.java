import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class LanguageWatchersMapper extends Mapper<Object, Text, Text, IntWritable> {

    private Text languageKey = new Text();
    private IntWritable watchersCount = new IntWritable();

    @Override
    protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        // Skip the header row
        if (value.toString().startsWith("name")) {
            return;
        }

        String[] fields = value.toString().split(",");
        try {
            // Extract `primary_language` and `watchers` fields
            String primaryLanguage = fields[5].trim();  // Assuming `primary_language` is the 6th column
            String watchersStr = fields[3].trim();  // Assuming `watchers` is the 4th column

            // Validate the `watchers` field is a valid integer
            if (isValidInteger(watchersStr) && !primaryLanguage.isEmpty()) {
                languageKey.set(primaryLanguage);
                watchersCount.set(Integer.parseInt(watchersStr));

                // Emit valid primary language and watchers count
                context.write(languageKey, watchersCount);
            }

        } catch (Exception e) {
            // Handle any malformed rows
            System.err.println("Error processing row: " + value.toString() + " | Error: " + e.getMessage());
        }
    }

    // Helper method to check if a string is a valid integer
    private boolean isValidInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
