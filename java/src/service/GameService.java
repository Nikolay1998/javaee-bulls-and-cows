package service;

import java.util.ArrayList;
import java.util.Arrays;

public class GameService {

    public static Integer[] compare(String reqNum, String enteredNum) {
        int cows = 0;
        int bulls = 0;
        int i = 0;
        for (String s : enteredNum.split("")) {
            int index = reqNum.indexOf(s);
            if (index >= 0) {
                if (index == i) bulls++;
                else cows++;
            }
            i++;
        }
        return new Integer[]{bulls, cows};
    }

    public static String createReqNum() {
        StringBuilder res = new StringBuilder();
        ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
        for (int i = 0; i < 4; i++) {
            int randomIndex = Math.toIntExact(Math.round(Math.random() * (9 - i)));
            res.append(numbers.get(randomIndex));
            numbers.remove(randomIndex);
        }
        return String.valueOf(res);
    }

    public static Double calcNewRating(Double oldRating, Integer games, Integer attempts){
        if (oldRating == null) {
            return Double.valueOf(attempts);
        } else {
            return (oldRating*games+attempts)/(games+1);
        }
    }
}
