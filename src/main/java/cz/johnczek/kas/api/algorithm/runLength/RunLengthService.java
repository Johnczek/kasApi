package cz.johnczek.kas.api.algorithm.runLength;

public interface RunLengthService {

    String encode(String message);

    String decode(String message);
}
