import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class ForksMapper extends Mapper<Object, Text, Text, IntWritable> {

    private Text yearKey = new Text();
    private IntWritable forksCount = new IntWritable();

    @Override
    protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        // Skip the header row
        if (value.toString().startsWith("name")) {
            return;
        }

        String[] fields = value.toString().split(",");
        try {
            // Extract `created_at` and `forks_count` fields
            String createdAt = fields[8].trim(); // Assuming `created_at` is the 9th column
            String forksStr = fields[2].trim();  // Assuming `forks_count` is the 3rd column

            // Validate the `created_at` field contains a valid year (numeric) and the `forks_count` is a valid integer
            if (createdAt.contains("-") && isValidInteger(forksStr)) {
                String year = createdAt.split("-")[0]; // Extract year (e.g., "2014" from "2014-12-24T17:49:19Z")

                // Additional check: ensure the year is numeric
                if (isValidInteger(year)) {
                    yearKey.set(year);
                    forksCount.set(Integer.parseInt(forksStr));

                    // Emit valid year and forks count
                    context.write(yearKey, forksCount);
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
