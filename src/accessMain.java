 import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
 import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class accessMain {
        private static final String LOG_FILE = "report.txt";
        private static final String LOG_PATTERN = "\\[(.*?)\\] \\[(.*?)\\] \\[(.*?)\\] \"(.*?) (.*?) (.*?)\"";

        public static void main(String[] args) {
            try {
                task();
            } catch (IOException e) {
                Logger logger = Logger.getLogger(accessMain.class.getName());
                logger.log(Level.SEVERE, "Exception occurred", e);
            }
        }

        private static void task() throws IOException {
            BufferedReader reader = new BufferedReader(new FileReader("resoures/access.txt"));
            FileWriter writer = new FileWriter(LOG_FILE);

            String line;
            int totalRequest = 0;
            Map<String, Integer> ipAddres = new HashMap<>();
            int countHTTP = 0;

            Pattern pattern = Pattern.compile(LOG_PATTERN);

            while ((line = reader.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    totalRequest++;
                    String ip = matcher.group(1);
                    String status = matcher.group(6);

                    if (!ip.equals("-")) {
                        ipAddres.put(ip, ipAddres.getOrDefault(ip, 0) + 1);
                    }

                    if (status.equals("404")) {
                        countHTTP++;
                    }
                }
            }
            writer.write("Total Requests: " + totalRequest + System.lineSeparator());
            writer.write("Requests per IP:" + System.lineSeparator());
            for (Map.Entry<String, Integer> entry : ipAddres.entrySet()) {
                writer.write(entry.getKey() + ": " + entry.getValue() + System.lineSeparator());
            }
            writer.write("404 Requests: " + countHTTP);

            reader.close();
            writer.close();
            System.out.println(ipAddres);
            System.out.println(countHTTP);
            System.out.println(totalRequest);

            System.out.println("Log analysis completed. Report saved in " + LOG_FILE);
        }

    }

