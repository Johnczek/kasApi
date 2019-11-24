package cz.johnczek.kas.api.service;

import cz.johnczek.kas.api.algorithm.runLength.RunLengthService;
import cz.johnczek.kas.api.response.AlgorithmRecordResponse;
import cz.johnczek.kas.api.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BaseServiceImpl implements BaseService {

    private static final String RLE_NAME = "Run Length Encoding";

    private final RunLengthService runLengthService;

    @Override
    public BaseResponse baseMethod(String message) {
        BaseResponse result = new BaseResponse();
        result.setMessage(message);

        result.addRecord(performRunLength(message));

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


    private long getCurrentNanoTime() {
        return System.nanoTime();
    }
}
