/*
The newly-improved calibration document consists of lines of text; each line originally contained a specific calibration value that the Elves now need to recover. On each line, the calibration value can be found by combining the first digit and the last digit (in that order) to form a single two-digit number.

For example:

1abc2
pqr3stu8vwx
a1b2c3d4e5f
treb7uchet

In this example, the calibration values of these four lines are 12, 38, 15, and 77. Adding these together produces 142.

Consider your entire calibration document. What is the sum of all of the calibration values?
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
        String number = "";
        int sum = 0;
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
            while((line = reader.readLine()) !=null){
                StringBuilder builder = new StringBuilder(line);
                Matcher matcher = pattern.matcher(line);
                StringBuffer foundPattern = new StringBuffer();

                for(char sign : line.toCharArray()){
                    if(Character.isDigit(sign)){
                        numberArray.add(sign);
                    }
                }

                String numberToAdd = String.valueOf(numberArray.get(0)) + String.valueOf(numberArray.get(numberArray.size()-1));
                sum += Integer.parseInt(numberToAdd);
                numberArray.clear();

//                while(matcher.find()){
//                    foundPattern.append(matcher.group());
//
//                    int length = foundPattern.length();
//
//                    Integer replacement = numbers.get(foundPattern.toString());
//                    StringBuilder patternToChange = new StringBuilder(foundPattern.toString());
//                    if(replacement != null){
//                        patternToChange.replace(0,patternToChange.toString().length(),replacement.toString());
//                    }
//                }
            }

            System.out.println("Sum: " + sum);

        }catch (FileNotFoundException e){
            System.out.println("File not found");
            e.printStackTrace();
        }catch (IOException e){
            System.out.println("File cannot be read");
            e.printStackTrace();
        }

    }
}