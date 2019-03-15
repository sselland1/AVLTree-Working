/**
 * Filename:   DuplicateKeyException.java
 * Author:     Scott Selland
 *
 * Bugs:       no known bugs
 */
class DuplicateKeyException extends Exception {
        private static final long serialVersionUID = 1L; 
 
        public DuplicateKeyException() {
		super("Duplicate Key is not permitted");
        }   
 
        public DuplicateKeyException(String message) {
                super(message);
        }   
}