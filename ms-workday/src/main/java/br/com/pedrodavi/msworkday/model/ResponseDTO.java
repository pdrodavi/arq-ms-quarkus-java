package br.com.pedrodavi.msworkday.model;

public class ResponseDTO {

    private String data;
    private String diaDaSemana;
    private String util;
    private String msg;

    public ResponseDTO() {
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDiaDaSemana() {
        return diaDaSemana;
    }

    public void setDiaDaSemana(String diaDaSemana) {
        this.diaDaSemana = diaDaSemana;
    }

    public String getUtil() {
        return util;
    }

    public void setUtil(String util) {
        this.util = util;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
