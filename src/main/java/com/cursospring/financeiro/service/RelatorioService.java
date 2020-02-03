package com.cursospring.financeiro.service;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.Map;

@Service
public class RelatorioService {


    public void createReport(Map parametros, InputStream jasperStream, DataSource dataSource, HttpServletResponse response)
                                                        throws JRException, SQLException, IOException {

        // Cria o objeto JaperReport com o Stream do arquivo jasper
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);

        // Passa para o JasperPrint o relatório, os parâmetros e a fonte dos dados, no caso uma conexão ao banco de dados
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, dataSource.getConnection());

        // Configura a respota para o tipo PDF
        response.setContentType("application/pdf");

        // Define que o arquivo pode ser visualizado no navegador e também nome final do arquivo
        // para fazer download do relatório troque 'inline' por 'attachment'
        response.setHeader("Content-Disposition", "inline; filename=relatorio.pdf");

        // Faz a exportação do relatório para o HttpServletResponse
        final OutputStream outStream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);

    }

}
