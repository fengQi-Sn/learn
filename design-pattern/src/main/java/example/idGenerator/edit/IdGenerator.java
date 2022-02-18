package example.idGenerator.edit;

import example.idGenerator.edit.exception.IdGenerationFailureException;

/**
 * @author dz0400820
 */
public interface IdGenerator {
    String generate() throws IdGenerationFailureException;
}
