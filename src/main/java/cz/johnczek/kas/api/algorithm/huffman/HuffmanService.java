package cz.johnczek.kas.api.algorithm.huffman;

public interface HuffmanService {

    /**
     * Method encodes message using Huffman coding
     * @param message message to be coded
     * @return coded message
     */
    String encode(String message);

    /**
     * Message decodes message that was coded by huffman coding
     * @param message huffman coding message
     * @return decoded message
     */
    String decode(String message);
}
