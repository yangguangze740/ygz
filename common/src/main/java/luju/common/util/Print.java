package luju.common.util;

import java.awt.*;

public class Print {
    private String name; // 变量前面的文字描述
    private String value; // 变量值
    private Boolean isWrap = false; //是否换行打印
    private Image image; //图片信息

    public Print(){

    }

    public Print(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public Print(Image image) {
        this.image = image;
    }

    public Print(String name, String value, Boolean isWrap) {
        this.name = name;
        this.value = value;
        this.isWrap = isWrap;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setVal(String value) {
        this.value = value;
    }

    public Boolean getIsWrap() {
        return isWrap;
    }

    public void setIsWrap(Boolean isWrap) {
        this.isWrap = isWrap;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}



