package cz.johnczek.kas.api.algorithm.huffman;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

@Service
public class HuffmanServiceImpl implements HuffmanService {

    private static Map<Character, String> prefixMap = new HashMap<>();
    private static Node root;

    private Node buildBinaryTree(Map<Character, Integer> frequency) {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        Set<Character> keys = frequency.keySet();

        for (Character ch : keys) {
            Node node = new Node(frequency.get(ch), ch, null, null);
            queue.offer(node);
        }


        while (queue.size() > 1) {

            Node n1 = queue.peek();
            queue.poll();

            Node n2 = queue.peek();
            queue.poll();

            root = new Node(n1.getFrequency() + n2.getFrequency(), '-', n1, n2);

            queue.offer(root);
        }

        return queue.poll();
    }

    private void setPrefixCodes(Node node, StringBuilder prefix) {

        if (node != null) {

            if (node.getLeft() != null && node.getRight() != null) {
                prefix.append('0');
                setPrefixCodes(node.getLeft(), prefix);
                prefix.deleteCharAt(prefix.length() - 1);

                prefix.append('1');
                setPrefixCodes(node.getRight(), prefix);
                prefix.deleteCharAt(prefix.length() - 1);
            } else {
                prefixMap.put(node.getCharacter(), prefix.toString());
            }
        }
    }

    @Override
    public String encode(String message) {
        Map<Character, Integer> frequency = new HashMap<>();

        for (int i = 0; i < message.length(); i++) {
            if (!frequency.containsKey(message.charAt(i))) {
                frequency.put(message.charAt(i), 0);
            }

            frequency.put(message.charAt(i), frequency.get(message.charAt(i)) +1);
        }

        root = buildBinaryTree(frequency);

        StringBuilder stringBuilder = new StringBuilder();

        setPrefixCodes(root, stringBuilder);

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < message.length(); i++) {
            result.append(prefixMap.get(message.charAt(i)));
        }

        return result.toString();
    }


    @Override
    public String decode(String message) {
        Node tempNode = root;
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < message.length(); i++) {
            int value = Character.getNumericValue(message.charAt(i));

            tempNode = (value == 0) ? tempNode.getLeft() : tempNode.getRight();

            if (tempNode.getLeft() == null && tempNode.getRight() == null) {
                result.append(tempNode.getCharacter());
                tempNode = root;
            }
        }

        return result.toString();
    }
}
