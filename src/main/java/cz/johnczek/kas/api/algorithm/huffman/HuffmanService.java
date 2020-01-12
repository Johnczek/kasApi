package cz.johnczek.kas.api.algorithm.huffman;

import cz.johnczek.kas.api.algorithm.huffman.dto.HuffmanResultDto;

public interface HuffmanService {

    /**
     * Method encodes message using Huffman coding
     * @param message message to be coded
     * @return coded message and dictionary in dto
     */
    HuffmanResultDto encode(String message);

    /**
     * Message decodes message that was coded by huffman coding
     * @param message huffman coding message
     * @param root root
     * @return decoded message
     */
    String decode(String message, Node root);
}
