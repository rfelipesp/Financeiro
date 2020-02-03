package com.cursospring.financeiro.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class RelatorioDeContas {

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dataDe;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dataAte;

    public Date getDataDe() {
        return dataDe;
    }

    public void setDataDe(Date dataDe) {
        this.dataDe = dataDe;
    }

    public Date getDataAte() {
        return dataAte;
    }

    public void setDataAte(Date dataAte) {
        this.dataAte = dataAte;
    }
}
