package cz.johnczek.kas.api.algorithm.lzw.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
@Builder
public class LZWResultDto {

    private Map<String, Integer> dictionary;

    private List<Integer> result;
}
