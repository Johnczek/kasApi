package cz.johnczek.kas.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.Map;

/**
 * Base response for every algorithm
 */

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class AlgorithmRecordResponse {

    private String algorithmName;

    private String encodedMessage;

    private int encodedMessageSize;

    private long encodingTime;

    private String decodedMessage;

    private int decodedMessageSize;

    private long decodingTime;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Map<String, Integer> dictionary;
}
