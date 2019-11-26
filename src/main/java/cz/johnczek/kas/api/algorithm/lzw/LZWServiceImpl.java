package cz.johnczek.kas.api.algorithm.lzw;

import cz.johnczek.kas.api.algorithm.lzw.dto.LZWResultDto;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LZWServiceImpl implements LZWService {

    // ASCII dictionary size
    private int baseDictionarySize = 256;

    @Override
    public LZWResultDto encode(String message) {

        String word = "";
        Map<String, Integer> dictionary = getBaseCompressDictionary();
        List<Integer> result = new ArrayList<>();

        for (char ch : message.toCharArray()) {
            String wordConcat = word + ch;

            if (!dictionary.containsKey(wordConcat)) {
                result.add(dictionary.get(word));
                dictionary.put(wordConcat, dictionary.size());
                word = "" + ch;
            } else {
                word = wordConcat;
            }
        }

        if (!word.equals(""))
            result.add(dictionary.get(word));

        return LZWResultDto.builder()
                .dictionary(dictionary)
                .result(result)
                .build();
    }

    @Override
    public String decode(List<Integer> compressedMessage) {

        Map<Integer, String> dictionary = getBaseDecompressDictionary();
        String word = (char)(int)compressedMessage.remove(0) + "";
        StringBuilder result = new StringBuilder(word);

        for (int i : compressedMessage) {

            String actualValue;

            if (dictionary.containsKey(i)) {
                actualValue = dictionary.get(i);
            } else {
                if (i == dictionary.size()) {
                    actualValue = word + word.charAt(0);
                } else {
                    actualValue = "";
                }
            }

            result.append(actualValue);
            dictionary.put(dictionary.size(), word + actualValue.charAt(0));
            word = actualValue;
        }

        return result.toString();
    }

    /**
     * @return base ASCII dictionary
     */
    private Map<String, Integer> getBaseCompressDictionary() {
        Map<String, Integer> result = new HashMap<>();

        for (int i = 0; i < baseDictionarySize; i++) {
            result.put(""+(char)i, i);
        }

        return result;
    }

    /**
     * @return base ASCII dictionary
     */
    private Map<Integer, String> getBaseDecompressDictionary() {
        Map<Integer, String> result = new HashMap<>();

        for (int i = 0; i < baseDictionarySize; i++) {
            result.put(i, (char) i + "");
        }

        return result;
    }
}
