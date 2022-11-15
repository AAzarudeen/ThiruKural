package com.example.thirukural.model;

public class ResponseModel {
    String number;
    String sect_tam;
    String chapgrp_tam;
    String chap_tam;
    String line1;
    String line2;
    String tam_exp;
    String sect_eng;
    String chapgrp_eng;
    String chap_eng;
    String eng;
    String eng_exp;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getSect_tam() {
        return sect_tam;
    }

    public void setSect_tam(String sect_tam) {
        this.sect_tam = sect_tam;
    }

    public String getChapgrp_tam() {
        return chapgrp_tam;
    }

    public void setChapgrp_tam(String chapgrp_tam) {
        this.chapgrp_tam = chapgrp_tam;
    }

    public String getChap_tam() {
        return chap_tam;
    }

    public void setChap_tam(String chap_tam) {
        this.chap_tam = chap_tam;
    }

    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getLine2() {
        return line2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public String getTam_exp() {
        return tam_exp;
    }

    public void setTam_exp(String tam_exp) {
        this.tam_exp = tam_exp;
    }

    public String getSect_eng() {
        return sect_eng;
    }

    public void setSect_eng(String sect_eng) {
        this.sect_eng = sect_eng;
    }

    public String getChapgrp_eng() {
        return chapgrp_eng;
    }

    public void setChapgrp_eng(String chapgrp_eng) {
        this.chapgrp_eng = chapgrp_eng;
    }

    public String getChap_eng() {
        return chap_eng;
    }

    public void setChap_eng(String chap_eng) {
        this.chap_eng = chap_eng;
    }

    public String getEng() {
        return eng;
    }

    public void setEng(String eng) {
        this.eng = eng;
    }

    public String getEng_exp() {
        return eng_exp;
    }

    public void setEng_exp(String eng_exp) {
        this.eng_exp = eng_exp;
    }

    public ResponseModel(String number, String sect_tam, String chapgrp_tam, String chap_tam, String line1, String line2, String tam_exp, String sect_eng, String chapgrp_eng, String chap_eng, String eng, String eng_exp) {
        this.number = number;
        this.sect_tam = sect_tam;
        this.chapgrp_tam = chapgrp_tam;
        this.chap_tam = chap_tam;
        this.line1 = line1;
        this.line2 = line2;
        this.tam_exp = tam_exp;
        this.sect_eng = sect_eng;
        this.chapgrp_eng = chapgrp_eng;
        this.chap_eng = chap_eng;
        this.eng = eng;
        this.eng_exp = eng_exp;
    }
}
