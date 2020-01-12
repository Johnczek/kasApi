package cz.johnczek.kas.api.algorithm.huffman.dto;

import cz.johnczek.kas.api.algorithm.huffman.Node;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
@Builder
public class HuffmanResultDto {

    private Node root;

    private Map<Character, String> dictionary;

    private String result;
}
