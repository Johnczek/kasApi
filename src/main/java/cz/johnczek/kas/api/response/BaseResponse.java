package cz.johnczek.kas.api.response;

import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class BaseResponse {

    private String message;

    private Set<AlgorithmRecordResponse> records;

    public void addRecord(AlgorithmRecordResponse record) {
        if (records == null) {
            records = new LinkedHashSet<>();
        }

        records.add(record);
    }
}
