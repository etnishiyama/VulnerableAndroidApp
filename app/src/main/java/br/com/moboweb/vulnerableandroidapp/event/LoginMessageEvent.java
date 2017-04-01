package br.com.moboweb.vulnerableandroidapp.event;

/**
 * @author Everton Takashi Nishiyama <etnishiyama@gmail.com>
 * @version 1.0
 * @since 3/31/17
 */
public class LoginMessageEvent {
    public static final String EVENT_CANCEL = "cancel_event";
    public static final String EVENT_POST_EXECUTE = "post_execute_event";
    public static final String EVENT_ON_SUCCESS = "on_success_event";
    public static final String EVENT_ON_ERROR = "on_error_event";
    private final String message;

    public LoginMessageEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
