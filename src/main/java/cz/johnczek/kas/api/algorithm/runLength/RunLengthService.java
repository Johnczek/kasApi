package cz.johnczek.kas.api.algorithm.runLength;

public interface RunLengthService {

    /**
     * Method encodes message using RLE
     * @param message message to be encoded
     * @return encoded message
     */
    String encode(String message);

    /**
     * Method decodes message using RLE
     * @param message ended message to be decoded
     * @return decoded message
     */
    String decode(String message);
}
