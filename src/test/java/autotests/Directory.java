package autotests;

import lombok.Getter;

@Getter
public enum Directory {
    FLY("/fly"),
    PROPERTIES("/properties"),
    SOUND("/sound"),
    SWIM("/swim");

    private String value;

    Directory(String value) {
        this.value = value;
    }

}
