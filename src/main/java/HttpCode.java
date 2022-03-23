public enum HttpCode {
    OK(200) {
        @Override
        public String getMessage() {
            return "OK BUT...";
        }
    },
    BAD_REQUEST(400, "Bad Request"),
    FORBIDDEN(403, "Forbidden");

    private final int code;
    private final String message;

    HttpCode(int code) {
        this.code = code;
        this.message = null;
    }

    HttpCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        if (message != null) {
            return message;
        }

        return name().toLowerCase();
    }
}
