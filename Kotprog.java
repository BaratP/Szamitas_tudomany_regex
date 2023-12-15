import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Kotprog {
    public static void main(String[] args) {
        String inputFile = "src/input.txt";
        String emailOutputFile = "src/email_output.txt";
        String wordOutputFile = "src/word_output.txt";
        String titleOutputFile = "src/title_output.txt";
        String divContentOutputFile = "src/div_content_output.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile));
             BufferedWriter emailBw = new BufferedWriter(new FileWriter(emailOutputFile));
             BufferedWriter wordBw = new BufferedWriter(new FileWriter(wordOutputFile));
             BufferedWriter titleBw = new BufferedWriter(new FileWriter(titleOutputFile));
             BufferedWriter divContentBw = new BufferedWriter(new FileWriter(divContentOutputFile))) {

            String line;
            Pattern emailPattern = Pattern.compile("\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}\\b");
            Pattern wordPattern = Pattern.compile("(\\b\\w+\\b)\\s+\\1");
            Pattern titlePattern = Pattern.compile("(?<=Mr\\.|Ms\\.)\\s\\w+");
            Pattern divContentPattern = Pattern.compile("<div>(.*?)</div>");

            while ((line = br.readLine()) != null) {
                Matcher emailMatcher = emailPattern.matcher(line);
                while (emailMatcher.find()) {
                    String email = emailMatcher.group();
                    emailBw.write(email);
                    emailBw.newLine();
                }

                Matcher wordMatcher = wordPattern.matcher(line);
                while (wordMatcher.find()) {
                    String repeatedWord = wordMatcher.group(1);
                    wordBw.write(repeatedWord);
                    wordBw.newLine();
                }

                Matcher titleMatcher = titlePattern.matcher(line);
                while (titleMatcher.find()) {
                    String title = titleMatcher.group();
                    titleBw.write(title);
                    titleBw.newLine();
                }

                Matcher divContentMatcher = divContentPattern.matcher(line);
                while (divContentMatcher.find()) {
                    String divContent = divContentMatcher.group(1);
                    divContentBw.write(divContent);
                    divContentBw.newLine();
                }
            }

            System.out.println("Matching and processing completed successfully.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
