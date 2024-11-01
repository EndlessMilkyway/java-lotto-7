package lotto.io.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import lotto.io.InputValidator;
import lotto.domain.Lotto;
import lotto.domain.BonusNumber;
import lotto.domain.Money;

public class InputView {

    private static final String AMOUNT_INPUT_GUIDE_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String WINNING_NUMBERS_INPUT_GUIDE_MESSAGE = "당첨 번호를 입력해 주세요.";
    private static final String BONUS_NUMBER_INPUT_GUIDE_MESSAGE = "보너스 번호를 입력해 주세요.";
    private static final String INPUT_DELIM = ",";

    private final InputValidator validator = new InputValidator();

    public Money getAmountOfMoney() {
        System.out.println(AMOUNT_INPUT_GUIDE_MESSAGE);
        Money money;
        while (true) {
            try {
                String input = Console.readLine();
                money = validator.validateAmountOfMoney(input);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return money;
    }

    public Lotto getWinningNumbers() {
        System.out.println(WINNING_NUMBERS_INPUT_GUIDE_MESSAGE);
        Lotto winningNumbers;
        while (true) {
            try {
                String input = Console.readLine();
                validateWinningNumbers(input);
                List<Integer> numbers = convertStringToList(input);
                winningNumbers = new Lotto(numbers);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return winningNumbers;
    }

    private void validateWinningNumbers(String input) {
        validator.validateWhiteSpace(input);
        validator.validateEmptyElemFromInput(input);
        validator.validateEndsWithComma(input);
        validator.validateExistNotDigitElems(input);
    }

    public BonusNumber getBonusNumber() {
        System.out.println(BONUS_NUMBER_INPUT_GUIDE_MESSAGE);
        BonusNumber bonusNumber;
        while (true) {
            try {
                String input = Console.readLine();
                validateBonusNumber(input);
                int number = Integer.parseInt(input);
                bonusNumber = new BonusNumber(number);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return bonusNumber;
    }

    private void validateBonusNumber(String input) {
        validator.validateWhiteSpace(input);
        validator.validateNonDigitInput(input);
    }

    private List<Integer> convertStringToList(String input) {
        String[] numbers = input.split(INPUT_DELIM);
        return Arrays.stream(numbers)
                .map(number -> Integer.parseInt(number.strip()))
                .sorted(Comparator.naturalOrder())
                .toList();
    }
}
