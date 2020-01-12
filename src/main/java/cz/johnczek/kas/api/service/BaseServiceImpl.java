package cz.johnczek.kas.api.service;

import cz.johnczek.kas.api.algorithm.huffman.HuffmanService;
import cz.johnczek.kas.api.algorithm.huffman.dto.HuffmanResultDto;
import cz.johnczek.kas.api.algorithm.lzw.LZWService;
import cz.johnczek.kas.api.algorithm.lzw.dto.LZWResultDto;
import cz.johnczek.kas.api.algorithm.runLength.RunLengthService;
import cz.johnczek.kas.api.response.AlgorithmRecordResponse;
import cz.johnczek.kas.api.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BaseServiceImpl implements BaseService {

    private static final String RLE_NAME = "Run Length Encoding";

    private static final String HUFFMAN_NAME = "Huffmanovo kódování";

    private static final String LZW = "LZW";

    private final RunLengthService runLengthService;

    private final HuffmanService huffmanService;

    private final LZWService lzwService;

    @Override
    public BaseResponse baseMethod(String message) {
        BaseResponse result = new BaseResponse();
        result.setMessage(message);
        result.setMessageSize(getMessageSize(message));

        result.addRecord(performRunLength(message));
        result.addRecord(performHuffman(message));
        result.addRecord(performLZW(message));

        return result;

    }

    /**
     * Method performs RLE algorithm
     *
     * @param message message to be processed
     * @return information about algorithm record
     */
    private AlgorithmRecordResponse performRunLength(String message) {

        long encodingTimeFrom = getCurrentNanoTime();
        String encodedMessage = runLengthService.encode(message);
        long encodingTimeTo = getCurrentNanoTime();

        long decodingTimeFrom = getCurrentNanoTime();
        String decodedMessage = runLengthService.decode(encodedMessage);
        long decodingTimeTo = getCurrentNanoTime();

        return AlgorithmRecordResponse.builder()
                .algorithmName(RLE_NAME)
                .encodedMessage(encodedMessage)
                .encodedMessageSize(getMessageSize(encodedMessage))
                .encodingTime(encodingTimeTo - encodingTimeFrom)
                .decodedMessage(decodedMessage)
                .decodedMessageSize(getMessageSize(message))
                .decodingTime(decodingTimeTo - decodingTimeFrom)
                .encodedMessageSizeToolTip("U RLE se počítá velikost komprimované zprávy jako počet znaků * 1 (Každá znak stringu zabírá 1B)")
                .build();

    }

    /**
     * Method performs Huffman coding
     *
     * @param message message to be processed
     * @return information about algorithm record
     */
    private AlgorithmRecordResponse performHuffman(String message) {

        long encodingTimeFrom = getCurrentNanoTime();
        HuffmanResultDto encodedResult = huffmanService.encode(message);
        long encodingTimeTo = getCurrentNanoTime();

        long decodingTimeFrom = getCurrentNanoTime();
        String decodedMessage = huffmanService.decode(encodedResult.getResult(), encodedResult.getRoot());
        long decodingTimeTo = getCurrentNanoTime();

        return AlgorithmRecordResponse.builder()
                .algorithmName(HUFFMAN_NAME)
                .encodedMessage(encodedResult.getResult())
                .encodedMessageSize((int) Math.ceil((float) getMessageSize(encodedResult.getResult()) / 8))
                .encodingTime(encodingTimeTo - encodingTimeFrom)
                .decodedMessage(decodedMessage)
                .decodedMessageSize(getMessageSize(message))
                .decodingTime(decodingTimeTo - decodingTimeFrom)
                .huffmanDictionary(encodedResult.getDictionary())
                .encodedMessageSizeToolTip("Každý znak u výsledku Huffmanova kódování značí jeden bit. U Huffmanova kódování se tedy počítá velikost komprimované zprávy jako velikost stringu / 8 (tzn. získáme počet bytů, na které lze dané bity uložit). Výsledek se vždy zaokrouhlí na nejbližší celý byte)")
                .build();
    }

    /**
     * Method performs LZW algorithm
     *
     * @param message message to be processed
     * @return information about algorithm record
     */
    private AlgorithmRecordResponse performLZW(String message) {

        long encodingTimeFrom = getCurrentNanoTime();
        LZWResultDto resultDto = lzwService.encode(message);
        long encodingTimeTo = getCurrentNanoTime();

        String encodedMessage = resultDto.getResult().toString();
        List<Integer> encodedMessageList = resultDto.getResult();

        long decodingTimeFrom = getCurrentNanoTime();
        String decodedMessage = lzwService.decode(new ArrayList<>(encodedMessageList));
        long decodingTimeTo = getCurrentNanoTime();

        return AlgorithmRecordResponse.builder()
                .algorithmName(LZW)
                .encodedMessage(encodedMessage)
                .encodedMessageSize(resultDto.getResult().size())
                .encodingTime(encodingTimeTo - encodingTimeFrom)
                .decodedMessage(decodedMessage)
                .decodedMessageSize(getMessageSize(message))
                .decodingTime(decodingTimeTo - decodingTimeFrom)
                .LZWDictionary(resultDto.getDictionary())
                .encodedMessageSizeToolTip("U LZW počítáme zjednodušeně výslednou hodnotu jako  počet prvků výsledku * 1")
                .build();
    }

    /**
     * @return actual nano time
     */
    private long getCurrentNanoTime() {
        return System.nanoTime();
    }

    /**
     * Returns amount of memory needed to save message in bytes. For every char 1B is needed
     *
     * @param message message for which we compute space
     * @return amout of bytes needed to save message
     */
    private int getMessageSize(String message) {
        return message.length();
    }
}
