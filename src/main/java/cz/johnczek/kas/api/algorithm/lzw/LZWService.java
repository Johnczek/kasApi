package cz.johnczek.kas.api.algorithm.lzw;

import cz.johnczek.kas.api.algorithm.lzw.dto.LZWResultDto;

import java.util.List;

public interface LZWService {

    LZWResultDto encode(String message);

    String decode(List<Integer> compressedMessage);

}
