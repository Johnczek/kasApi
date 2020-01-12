package cz.johnczek.kas.api.algorithm.huffman;

import lombok.*;

/**
 * Bianry tree node for Huffman coding
 */

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
        final int freqComparasion = Integer.compare(this.getFrequency(), o.getFrequency());

        if (freqComparasion != 0) {
            return freqComparasion;
        }

        return Integer.compare(this.getCharacter(), o.getCharacter());
    }

    public boolean isLeaf() {
        return this.getLeft() == null && this.getRight() == null;
    }
}
