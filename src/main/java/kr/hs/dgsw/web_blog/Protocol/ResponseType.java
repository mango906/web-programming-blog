package kr.hs.dgsw.web_blog.Protocol;

public enum ResponseType {
    FAIL(0, "명령을 실행하지 못했습니다."),

    USER_DELETE(101, "사용자를 삭제했습니다."),
    USER_ADD(102, "사용자를 추가했습니다."),
    USER_UPDATE(103, "ID [%d]의 사용자를 수정했습니다."),
    USER_GET(104, "[%s]사용자를 불러왔습니다."),

    POST_ID_GET(200, "ID [%d] 사용자의 글을 불러왔습니다."),
    POST_GET(201, "글을 불러왔습니다"),
    POST_ADD(202, "글을 추가했습니다."),
    POST_UPDATE(203, "글을 수정했습니다."),
    POST_DELETE(204, "글을 삭제했습니다."),

    ATTACHMENT_STORED(301, "");

    final private int code;
    final private String desc;

    ResponseType(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int code() {
        return this.code;
    }

    public String desc() {
        return this.desc;
    }
}
