package intelimed.intermediate.core.exception;
/* 
 * Project name: RBT Tools
 * Class name: BusinessException.java
 * Creation date: 04/03/2010
 * 
 * 
 * CHANGE'S LOG:
 * =====================================================================
 * ####        Jefferson Amorim (jsa@dsc.upe.br)
 * 04/03/2010  Initial version
 * =====================================================================
 */


import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * Class that represent business exceptions.
 * 
 * @author Jefferson Amorim (jsa@dsc.upe.br)
 * 
 */
public class BusinessException extends Exception {
    /**
     * Map of key of messages and parameters.
     */
    private Map<String, Object> messages;

    /**
     * Constructor of the business exception.
     * 
     * @param message
     *            Message key.
     */
    public BusinessException(String message) {
        this(message, null);
        this.messages.put(message, null);
    }

    /**
     * Constructor of the business exception.
     * 
     * @param message
     *            Message key.
     * @param messagesArgs
     *            Message parameters.
     */
    public BusinessException(String message, Object messagesArgs) {
        super(message);
        this.messages = new HashMap<String, Object>();
        this.messages.put(message, messagesArgs);
    }

    /**
     * 
     * Constructor of the business exception.
     * 
     * @param message
     *            Message key.
     * @param cause
     *            Original exception.
     * 
     */
    public BusinessException(String message, Throwable cause) {
        super(message, cause);
        this.messages = new HashMap<String, Object>();
        this.messages.put(message, null);
    }

    /**
     * Constructor of the business exception.
     * 
     * @param messages
     *            List of message key.
     */
    public BusinessException(List<String> messages) {
        super();
        this.messages = new HashMap<String, Object>();
        for (String string : messages) {
            this.messages.put(string, null);
        }
    }

    /**
     * Constructor of the business exception.
     * 
     * @param messages
     *            Map of key of messages and parameters.
     */
    public BusinessException(Map<String, Object> messages) {
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
