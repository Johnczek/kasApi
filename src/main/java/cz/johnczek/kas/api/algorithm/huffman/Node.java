package cz.johnczek.kas.api.algorithm.huffman;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Node implements Comparable<Node> {

    private int frequency;

    private char character;

    private Node left;

    private Node right;

    @Override
    public int compareTo(Node o) {
        return frequency - o.getFrequency();
    }
}
