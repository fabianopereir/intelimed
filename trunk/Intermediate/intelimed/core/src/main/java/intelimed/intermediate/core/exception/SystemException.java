/* 
 * Project name: RBT Tools
 * Class name: SystemException.java
 * Creation date: 04/03/2010
 * 
 * 
 * CHANGE'S LOG:
 * =====================================================================
 * ####        Jefferson Amorim (jsa@dsc.upe.br)
 * 04/03/2010  Initial version
 * =====================================================================
 */
package intelimed.intermediate.core.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * Class that represent system exceptions.
 * 
 * @author Jefferson Amorim (jsa@dsc.upe.br)
 * 
 */
public class SystemException extends Exception {

    /**
     * Map of key of messages and parameters.
     */
    private Map<String, Object> messages;

    /**
     * Constructor of the system exception.
     * 
     * @param message
     *            Message key.
     */
    public SystemException(String message) {
        this(message, null);
        this.messages.put(message, null);
    }

    /**
     * Constructor of the system exception.
     * 
     * @param message
     *            Message key.
     * @param messagesArgs
     *            Message parameters.
     */
    public SystemException(String message, Object messagesArgs) {
        super(message);
        this.messages = new HashMap<String, Object>();
        this.messages.put(message, messagesArgs);
    }

    /**
     * 
     * Constructor of the system exception.
     * 
     * @param message
     *            Message key.
     * @param cause
     *            Original exception.
     * 
     */
    public SystemException(String message, Throwable cause) {
        super(message, cause);
        this.messages = new HashMap<String, Object>();
        this.messages.put(message, null);
    }

    /**
     * Constructor of the system exception.
     * 
     * @param messages
     *            List of message key.
     */
    public SystemException(List<String> messages) {
        super();
        this.messages = new HashMap<String, Object>();
        for (String string : messages) {
            this.messages.put(string, null);
        }
    }

    /**
     * Constructor of the system exception.
     * 
     * @param messages
     *            Map of key of messages and parameters.
     */
    public SystemException(Map<String, Object> messages) {
        super();
        this.messages = messages;
    }

    /**
     * Return the value of attribute messages.
     * 
     * @return The value of attribute messages.
     */
    public Map<String, Object> getMessages() {
        return messages;
    }

    /**
     * Set a value to attribute messages.
     * 
     * @param messages
     *            The messages to be set.
     */
    public void setMessages(Map<String, Object> messages) {
        this.messages = messages;
    }
}
