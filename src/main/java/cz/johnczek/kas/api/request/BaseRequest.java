package cz.johnczek.kas.api.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Base POST request to API
 */

@NoArgsConstructor
@Getter
@Setter
public class BaseRequest {

    private String message;
}
