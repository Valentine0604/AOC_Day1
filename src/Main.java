/*

PART ONE

The newly-improved calibration document consists of lines of text; each line originally contained a specific calibration value that the Elves now need to recover. On each line, the calibration value can be found by combining the first digit and the last digit (in that order) to form a single two-digit number.

For example:

1abc2
pqr3stu8vwx
a1b2c3d4e5f
treb7uchet

In this example, the calibration values of these four lines are 12, 38, 15, and 77. Adding these together produces 142.

Consider your entire calibration document. What is the sum of all of the calibration values?

PART TWO

Your calculation isn't quite right. It looks like some of the digits are actually spelled out with letters: one, two, three, four, five, six, seven, eight, and nine also count as valid "digits".

Equipped with this new information, you now need to find the real first and last digit on each line. For example:

two1nine
eightwothree
abcone2threexyz
xtwone3four
4nineeightseven2
zoneight234
7pqrstsixteen
In this example, the calibration values are 29, 83, 13, 24, 42, 14, and 76. Adding these together produces 281.

What is the sum of all of the calibration values?
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Main {
    public static void main(String[] args) {

        File file = new File("input.txt");
        int sum1 = 0;
        int sum2 = 0;
        Pattern pattern = Pattern.compile("one|two|three|four|five|six|seven|eight|nine", Pattern.CASE_INSENSITIVE);
        ArrayList<Character> numberArray = new ArrayList<>();
        Map<String, Integer> numbers = new HashMap<>();
        numbers.put("one",1);
        numbers.put("two",2);
        numbers.put("three",3);
        numbers.put("four",4);
        numbers.put("five",5);
        numbers.put("six",6);
        numbers.put("seven",7);
        numbers.put("eight",8);
        numbers.put("nine",9);

        try{
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while((line = reader.readLine()) != null){
                StringBuilder builder = new StringBuilder(line);
                Matcher matcher = pattern.matcher(builder);
                StringBuffer foundPattern = new StringBuffer();

                //PART ONE
                for(char sign : line.toCharArray()){
                    if(Character.isDigit(sign)){
                        numberArray.add(sign);
                    }
                }

                String numberToAdd1 = String.valueOf(numberArray.get(0)) + String.valueOf(numberArray.get(numberArray.size()-1));
                sum1 += Integer.parseInt(numberToAdd1);
                numberArray.clear();

                //PART TWO
                while(matcher.find()){
                    foundPattern.append(matcher.group());
                    int startIndex = matcher.start();
                    int endIndex = startIndex + foundPattern.length();
                    Integer replacement = numbers.get(foundPattern.toString());
                    StringBuilder patternToChange = new StringBuilder(foundPattern.toString());

                    if(replacement != null){
                        patternToChange.replace(0,patternToChange.toString().length(),replacement.toString());
                        builder.replace(startIndex,endIndex,patternToChange.toString());
                        foundPattern.delete(0,foundPattern.length());
                        matcher = pattern.matcher(builder);
                    }
                }

                for(char sign : builder.toString().toCharArray()){
                    if(Character.isDigit(sign)){
                        numberArray.add(sign);
                    }
                }

                String numberToAdd2 = String.valueOf(numberArray.get(0)) + String.valueOf(numberArray.get(numberArray.size()-1));
                sum2 += Integer.parseInt(numberToAdd2);
                numberArray.clear();
            }

            System.out.println("Sum1: " + sum1);
            System.out.println("Sum2: " + sum2);

        }catch (FileNotFoundException e){
            System.out.println("File not found");
            e.printStackTrace();
        }catch (IOException e){
            System.out.println("File cannot be read");
            e.printStackTrace();
        }

    }
}