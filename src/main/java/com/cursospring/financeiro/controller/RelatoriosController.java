package com.cursospring.financeiro.controller;

import com.cursospring.financeiro.dto.RelatorioDeContas;
import com.cursospring.financeiro.service.RelatorioService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/relatorios")
public class RelatoriosController {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private RelatorioService relatorioService;

    // Contas a receber

    @GetMapping("/contasAReceber")
    public ModelAndView relatorioContasAReceber(RelatorioDeContas relatorioDeContas){
        return  new ModelAndView("relatorio/ContasAReceber");
    }

    @PostMapping("/contasAReceber")
    public void gerarRelatorioContasAReceber(RelatorioDeContas relatorioDeContas, HttpServletResponse response) throws JRException, SQLException, IOException {

        Map<String, Object> parametros = new HashMap<>();

        parametros.put("dataDe", relatorioDeContas.getDataDe());
        parametros.put("dataAte", relatorioDeContas.getDataAte());

        InputStream jasperStream = this.getClass().getResourceAsStream("/relatorios/relatorio_contas_a_receber.jasper");

        relatorioService.createReport(parametros, jasperStream, dataSource, response);

    }

    // Contas a receber

    @GetMapping("/contasAPagar")
    public ModelAndView relatorioContasAPagar(RelatorioDeContas relatorioDeContas){
        return  new ModelAndView("relatorio/ContasAPagar");
    }

    @PostMapping("/contasAPagar")
    public void gerarRelatorioContasAPagar(RelatorioDeContas relatorioDeContas, HttpServletResponse response) throws JRException, SQLException, IOException {

        Map<String, Object> parametros = new HashMap<>();

        parametros.put("dataDe", relatorioDeContas.getDataDe());
        parametros.put("dataAte", relatorioDeContas.getDataAte());

        InputStream jasperStream = this.getClass().getResourceAsStream("/relatorios/relatorio_contas_a_pagar.jasper");

        relatorioService.createReport(parametros, jasperStream, dataSource, response);

    }




}
