package example.idGenerator.edit.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author dz0400820
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IdGenerationFailureException extends Exception {
    private String errMsg;
}
