package cz.johnczek.kas.api.algorithm.runLength;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class RunLengthServiceImpl implements RunLengthService {

    @Override
    public String encode(String message) {

        if (StringUtils.isBlank(message)) {
            return "";
        }

        StringBuilder stringBuilder = new StringBuilder();
        char[] messageChars = message.toCharArray();
        char currentMessageChar = messageChars[0];

        int index = 1;

        for (int i = 1; i < messageChars.length; i++) {
            if (currentMessageChar == messageChars[i]) {
                index++;
            } else {
                if (index > 1) {
                    stringBuilder.append(index);
                }

                stringBuilder.append(currentMessageChar);
                currentMessageChar = messageChars[i];
                index = 1;
            }
        }

        if (index > 1) {
            stringBuilder.append(index);
        }

        stringBuilder.append(currentMessageChar);

        return stringBuilder.toString();
    }

    @Override
    public String decode(String message) {

        if (StringUtils.isBlank(message)) {
            return "";
        }

        StringBuilder stringBuilder = new StringBuilder();
        char[] messageChars = message.toCharArray();
        boolean previousCharWasDigit = false;
        StringBuilder loadedDigits = new StringBuilder();

        for (char ch : messageChars) {
            if (Character.isDigit(ch)) {
                loadedDigits.append(ch);
                previousCharWasDigit = true;
            } else {
                if (previousCharWasDigit) {
                    String multipleString = new String(new char[Integer.parseInt(loadedDigits.toString())]).replace("\0", ch + "");
                    stringBuilder.append(multipleString);
                    previousCharWasDigit = false;
                    loadedDigits = new StringBuilder();
                } else {
                    stringBuilder.append(ch);
                }
            }
        }

        return stringBuilder.toString();
    }
}
