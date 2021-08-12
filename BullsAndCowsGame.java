import java.util.*;

public class BullsAndCowsGame {
    static int bulls;
    static int cows;
    static String lastChar;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Error");
        String ranNum = null;
        int inputNum = 0;
        while (true) {
            try {
                inputNum = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Error");
                break;
            }
            System.out.println("Input the number of possible symbols in the code:");
            int inputSymb = 0;
            try {
                inputSymb = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Error");
                break;
            }
            if (inputNum <= 0 || inputNum > 36) {
                System.out.println("Error");
                break;
            } else if (inputSymb < inputNum || inputSymb > 36) {
                System.out.println("Error");
                break;
            } else {
                ranNum = generateNumber(inputNum, inputSymb);
                String stars = getStars(inputNum);
                System.out.printf("The secret is prepared: %s (0-9, a-%s).", stars, lastChar);
                System.out.println();
                int turn = 1;
                String code = ranNum;
                System.out.println("Okay, let's start a game!");
                while (bulls != inputNum) {
                    System.out.printf("Turn %d:", turn);
                    System.out.println();
                    String input = scanner.nextLine();
                    for (int i = 0; i < inputNum; i++) {
                        for (int j = 0; j < inputNum; j++) {
                            if (code.charAt(i) == input.charAt(j) && i == j) {
                                bulls++;
                            } else if (code.charAt(i) == input.charAt(j) && i != j) {
                                cows++;
                            }
                        }
                    }
                    if (bulls == inputNum) {
                        System.out.printf("Grade: %d bull(s).", bulls);
                        System.out.println();
                        System.out.println("Congratulations! You guessed the secret code.");
                        break;
                    } else if (bulls > 0 && cows == 0) {
                        System.out.printf("Grade: %d bull(s).", bulls);
                    } else if (bulls == 0 && cows > 0) {
                        System.out.printf("Grade: %d cow(s).", cows);
                    } else if (bulls > 0 && cows > 0) {
                        System.out.printf("Grade: %d bull(s) and %d cow(s).", bulls, cows);
                    } else if (bulls == 0 && cows == 0) {
                        System.out.print("Grade: None.");
                    }
                    System.out.println();
                    turn++;
                    bulls = 0;
                    cows = 0;
                }
                if (bulls == inputNum) {
                    break;
                }
            }
        }
    }
    public static String generateNumber(int input, int inpSyb) {
        List<String> randomList = new ArrayList<>(List.of("0", "1", "2", "3", "4", "5", "6", "7", "8", "9"));
        List<String> alphabet = new ArrayList<>(List.of("a", "b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"));
        List<String> randABC = new ArrayList<>();
        for (int i = 0; i < inpSyb-10; i++) {
            randABC.add(alphabet.get(i));
        }
        List<String> finalList = new ArrayList<>();
        finalList.addAll(randomList);
        finalList.addAll(randABC);
        lastChar = finalList.get(finalList.size()-1);
        Collections.shuffle(finalList);
        StringBuilder result = new StringBuilder();
        for (var ch : finalList.subList(0, input)) {
            result.append(ch);
        }
        return result.toString();
    }
    public static String getStars(int inp) {
        StringBuilder tmp = new StringBuilder();
        tmp.append("*".repeat(Math.max(0, inp)));
        return tmp.toString();
    }
}