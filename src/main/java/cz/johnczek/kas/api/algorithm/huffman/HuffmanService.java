package cz.johnczek.kas.api.algorithm.huffman;

public interface HuffmanService {

    String encode(String message);

    String decode(String message);
}
