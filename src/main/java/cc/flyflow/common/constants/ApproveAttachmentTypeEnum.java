package cc.flyflow.common.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ApproveAttachmentTypeEnum {
    IMAGE("image", "图片"),
    FILE("file", "文件"),
    ;

    private String type;

    private String name;
}