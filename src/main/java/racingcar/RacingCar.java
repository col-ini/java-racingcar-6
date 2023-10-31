package racingcar;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class RacingCar {
    public List<String> carName = new ArrayList<String>();
    public List<Integer> movementSoFar = new ArrayList<Integer>();
    ExceptionChecker exceptionChecker = new ExceptionChecker();
    Integer numberOfTries;

    public RacingCar() {
        carName();
        setMovement();
        numberOfTries();
    }

    public void changeLine() {
        System.out.println();
    }

    public String inputString(String inputText) {
        System.out.println(inputText);
        String inputStr = Console.readLine();
        return inputStr;
    }

    public void stringToList(String strCarName) {
        if (strCarName.contains(",")) {
            carName = Arrays.asList(strCarName.split(","));
        } else if (!strCarName.contains(",")) {
            carName = Arrays.asList(strCarName);
        }
    }

    public void carName() {
        String strCarName = inputString("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
        exceptionChecker.wrongInputCarName(strCarName);
        stringToList(strCarName);
    }

    public void setMovement() {
        for (int i = 0; i < carName.size(); i++) {
            movementSoFar.add(0);
        }
    }

    public void numberOfTries() {
        String strTries = inputString("시도할 회수는 몇회인가요?");
        exceptionChecker.wrongInputTries(strTries);
        numberOfTries = Integer.parseInt(strTries);
    }

    public int generateRandomNumber() {
        return Randoms.pickNumberInRange(0, 9);
    }

    public boolean move() {
        Integer randomNumber = generateRandomNumber();
        if (randomNumber >= 4) {
            return true;
        }
        return false;
    }

    public void updateMovement(Integer indexOfCarName) {
        if (move()) {
            movementSoFar.set(indexOfCarName, (movementSoFar.get(indexOfCarName) + 1));
        }
    }

    public String getDash(Integer indexOfCarName) {
        String getDash = "";
        for (int i = 0; i < movementSoFar.get(indexOfCarName); i++) {
            getDash += "-";
        }
        return getDash;
    }

    public void printMovement(Integer indexOfCarName) {
        System.out.printf("%s : %s%n", carName.get(indexOfCarName), getDash(indexOfCarName));
    }

    public int longestMovement(List<Integer> movementSoFar) {
        int longestMovement = Collections.max(movementSoFar);
        return longestMovement;
    }

    public List<Integer> howManyWinners(int longestMovement) {
        List<Integer> winnerIndexList = new ArrayList<Integer>();
        for (int i = 0; i < movementSoFar.size(); i++) {
            if (movementSoFar.get(i) == longestMovement) {
                winnerIndexList.add(i);
            }
        }
        return winnerIndexList;
    }

    public void printFinalWinner(List<Integer> winnerIndexList) {
        System.out.print("최종 우승자 : ");
        for (int i = 0; i < winnerIndexList.size() - 1; i++) {
            System.out.printf("%s, ", carName.get(winnerIndexList.get(i)));
        }
        System.out.println(carName.get(winnerIndexList.get(winnerIndexList.size() - 1)));
    }
}