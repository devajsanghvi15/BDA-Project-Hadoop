import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class StarsMapper extends Mapper<Object, Text, Text, IntWritable> {

    private Text yearKey = new Text();
    private IntWritable starsCount = new IntWritable();

    @Override
    protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        // Skip the header row
        if (value.toString().startsWith("name")) {
            return;
        }

        String[] fields = value.toString().split(",");
        try {
            // Extract `created_at` and `stars_count` fields
            String createdAt = fields[8].trim(); // Assuming `created_at` is the 9th column
            String starsStr = fields[1].trim();  // Assuming `stars_count` is the 2nd column

            // Validate the `created_at` field contains a valid year (numeric) and the `stars_count` is a valid integer
            if (createdAt.contains("-") && isValidInteger(starsStr)) {
                String year = createdAt.split("-")[0]; // Extract year (e.g., "2014" from "2014-12-24T17:49:19Z")

                // Additional check: ensure the year is numeric
                if (isValidInteger(year)) {
                    yearKey.set(year);
                    starsCount.set(Integer.parseInt(starsStr));

                    // Emit valid year and stars count
                    context.write(yearKey, starsCount);
                }

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

