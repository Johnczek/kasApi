package cz.johnczek.kas.api.service;

import cz.johnczek.kas.api.algorithm.huffman.HuffmanService;
import cz.johnczek.kas.api.algorithm.runLength.RunLengthService;
import cz.johnczek.kas.api.response.AlgorithmRecordResponse;
import cz.johnczek.kas.api.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
@RequiredArgsConstructor
public class BaseServiceImpl implements BaseService {

    private static final String RLE_NAME = "Run Length Encoding";

    private static final String HUFFMAN_NAME = "Huffmanovo kódování";

    private final RunLengthService runLengthService;

    private final HuffmanService huffmanService;

    @Override
    public BaseResponse baseMethod(String message) {
        BaseResponse result = new BaseResponse();
        result.setMessage(message);

        result.addRecord(performRunLength(message));
        result.addRecord(performHuffman(message));

        return result;

    }

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
                .encodedMessageSize(encodedMessage.getBytes().length)
                .encodingTime(encodingTimeTo-encodingTimeFrom)
                .decodedMessage(decodedMessage)
                .decodedMessageSize(decodedMessage.getBytes().length)
                .decodingTime(decodingTimeTo-decodingTimeFrom)
                .build();

    }

    private AlgorithmRecordResponse performHuffman(String message) {

        long encodingTimeFrom = getCurrentNanoTime();
        String encodedMessage = huffmanService.encode(message);
        long encodingTimeTo = getCurrentNanoTime();

        long decodingTimeFrom = getCurrentNanoTime();
        String decodedMessage = huffmanService.decode(encodedMessage);
        long decodingTimeTo = getCurrentNanoTime();

        return AlgorithmRecordResponse.builder()
                .algorithmName(HUFFMAN_NAME)
                .encodedMessage(encodedMessage)
                .encodedMessageSize(encodedMessage.getBytes().length)
                .encodingTime(encodingTimeTo-encodingTimeFrom)
                .decodedMessage(decodedMessage)
                .decodedMessageSize(new BigInteger(decodedMessage.getBytes()).toString(2).length())
                .decodingTime(decodingTimeTo-decodingTimeFrom)
                .build();
    }


    private long getCurrentNanoTime() {
        return System.nanoTime();
    }
}
