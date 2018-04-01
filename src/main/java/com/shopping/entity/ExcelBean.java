package com.shopping.entity;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;


public class ExcelBean implements  java.io.Serializable{
    private String headTextName; //列头（标题）名
    private String propertyName; //对应字段名
    private Integer cols; //合并单元格数
    private XSSFCellStyle cellStyle;
    public ExcelBean(){
    }
    public ExcelBean(String headTextName, String propertyName){
        this.headTextName = headTextName;
        this.propertyName = propertyName;
    }
    public ExcelBean(String headTextName, String propertyName, Integer cols) {
        super();
        this.headTextName = headTextName;
        this.propertyName = propertyName;
        this.cols = cols;
    }
    public void setHeadTextName(String headTextName){
        this.headTextName=headTextName;
    }

    public String getHeadTextName() {
        return headTextName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public Integer getCols() {
        return cols;
    }

    public void setCols(Integer cols) {
        this.cols = cols;
    }
}