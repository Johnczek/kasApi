package cz.johnczek.kas.api.algorithm.lzw.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Map;

/**
 * DTO holding LZW result for output serialization.
 */

@Getter
@Builder
public class LZWResultDto {

    private Map<String, Integer> dictionary;

    private List<Integer> result;
}
