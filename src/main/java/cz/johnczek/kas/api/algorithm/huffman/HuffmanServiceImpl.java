package cz.johnczek.kas.api.algorithm.huffman;

import cz.johnczek.kas.api.algorithm.huffman.dto.HuffmanResultDto;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

@Service
public class HuffmanServiceImpl implements HuffmanService {

    private static final int DICTIONARY_SIZE = 256;

    @Override
    public HuffmanResultDto encode(String message) {

        final int[] frequencyTable = buildDictionary(message);
        final Node root = buildTree(frequencyTable);
        final Map<Character, String> table = buildTable(root);

        return HuffmanResultDto.builder()
                .result(getMessage(message, table))
                .dictionary(table)
                .root(root)
                .build();
    }

    @Override
    public String decode(String message, Node root) {
        final StringBuilder result = new StringBuilder();

        Node currentNode = root;
        int i = 0;
        while (i < message.length()) {

            while (!currentNode.isLeaf()) {
                char bit = message.charAt(i);
                if (bit == '1') {
                    currentNode = currentNode.getRight();
                } else {
                    currentNode = currentNode.getLeft();
                }
                i++;
            }

            result.append(currentNode.getCharacter());
            currentNode = root;
        }

        return result.toString();
    }

    private String getMessage(String message, Map<Character, String> table) {
        StringBuilder result = new StringBuilder();

        for (char ch : message.toCharArray()) {
            result.append(table.get(ch));
        }

        return result.toString();
    }

    private Map<Character, String> buildTable(final Node root) {
        Map<Character, String> table = new HashMap<>();

        buildTable(root, "", table);

        return table;
    }

    private static void buildTable(Node root, String s, Map<Character, String> table) {
        if (!root.isLeaf()) {
            buildTable(root.getLeft(), s + '0', table);
            buildTable(root.getRight(), s + '1', table);
        } else {
            table.put(root.getCharacter(), s);
        }
    }

    private Node buildTree(int[] frequency) {
        final PriorityQueue<Node> queue = new PriorityQueue<>();

        for (char ch = 0; ch < DICTIONARY_SIZE; ch++) {
            if (frequency[ch] > 0) {
                queue.add(new Node(frequency[ch], ch, null, null));
            }
        }

        if (queue.size() == 1) {
            queue.add(new Node(1, '\0', null, null));
        }

        while (queue.size() > 1) {
            final Node left = queue.poll();
            final Node right = queue.poll();
            final Node parent = new Node(left.getFrequency() + right.getFrequency(), '\0', left, right);
            queue.add(parent);
        }

        return queue.poll();
    }

    private int[] buildDictionary(final String message) {

        final int[] frequency = new int[DICTIONARY_SIZE];

        for (final char ch : message.toCharArray()) {
            frequency[ch]++;
        }

        return frequency;
    }

}
