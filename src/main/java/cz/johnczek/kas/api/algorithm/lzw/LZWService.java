package cz.johnczek.kas.api.algorithm.lzw;

import cz.johnczek.kas.api.algorithm.lzw.dto.LZWResultDto;

import java.util.List;

public interface LZWService {

    /**
     * Method encodes message using LZW
     * @param message message to be coded
     * @return dto holding information about message
     */
    LZWResultDto encode(String message);

    /**
     * Method decodes message using LZW
     * @param compressedMessage list of integers represents encoded parts of original message
     * @return decoded message
     */
    String decode(List<Integer> compressedMessage);

}
