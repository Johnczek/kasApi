package cz.johnczek.kas.api.response;

import lombok.*;

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
}
