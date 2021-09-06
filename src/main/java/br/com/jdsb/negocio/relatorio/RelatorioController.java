package br.com.jdsb.negocio.relatorio;

import java.io.IOException;
import java.sql.Connection;

import org.springframework.core.io.Resource;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class RelatorioController {

	public void imprimirRelatorioSemFiltro(Connection connection,Resource resource) throws JRException, IOException{
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(resource.getURL());
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,null, connection);
        JasperViewer jasperViewer = new JasperViewer(jasperPrint,false);
        jasperViewer.setVisible(true);
	}

}
