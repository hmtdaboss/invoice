/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jasperinvoice;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import javax.print.PrintException;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import org.icepdf.core.exceptions.PDFException;
import org.icepdf.core.exceptions.PDFSecurityException;

/**
 *
 * @author fsafi
 */
public class Jasper {
    private Connection conn; //objet de connexion à la BDD
    private Statement stat;
    private static  String INVOICE_QUERY = 
            "select pro.codebarre as id , pro.libelle as name, pv.prix as price, "
            + "mag.nomMagasin as shopname, mag.adresse as adres, mag.codepostal as postalcode, "
            + "mag.commune as commune, mag.tel as tel, mag.tva as tva, mag.mail as mail, "
            + "cli.idClient as clientid, cli.nomSociete as clientsocietyname, cli.adresse as clientadress, "
            + "cli.codepostal as clientpostalcode, cli.commune as clientcommune, cli.tel as clienttel, "
            + "cli.tva as clienttva from produit pro join prixdevente pv on pro.codebarre = pv.codebarre "
            + "join magasin mag on mag.idmag = pv.idmag "
            + "join client cli on cli.idmag = mag.idmag "
            + "limit 20";
    

    public void generatePdf() throws SQLException, JRException, PDFException, PDFSecurityException, IOException, PrintException {
        String DBPath = "src/extraVideoDB.db";
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:" + DBPath);
            stat = conn.createStatement();
            System.out.println("Connexion a " + DBPath + " avec succès");

        } catch (ClassNotFoundException | SQLException notFoundException) {
            System.out.println("Erreur de connecxion");

        }

                

        System.out.println(INVOICE_QUERY);
       

        // - Paramètres à envoyer au rapport
        Map parameters = new HashMap();
        parameters.put("query", INVOICE_QUERY);
        parameters.put("test", "F A C T U R E");
        parameters.put("reference", 1);

        FileInputStream fis = new FileInputStream("src/invoice.jasper"); 
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fis);
//Load bufferedInputStream file.jasper 
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(bufferedInputStream); 
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,parameters,conn);
        
       
       JasperExportManager.exportReportToPdfFile(jasperPrint, "classic.pdf");
        
     
       
        
        //JasperPrint jasperPrint = JasperFillManager.fillReport("src/invoice.jasper", parameters, conn);

        //  JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, conn);
        // - Création du rapport au format PDF
//        JasperExportManager.exportReportToPdfFile(jasperPrint, "classic.pdf");
//        
//        Document pdf = new Document();
//        pdf.setFile("classic.pdf");
//        SwingController sc = new SwingController();
//        DocumentViewController vc = new DocumentViewControllerImpl(sc);
//        vc.setDocument(pdf);
//        org.icepdf.ri.common.PrintHelper printHelper;
//        printHelper = new org.icepdf.ri.common.PrintHelper(vc, pdf.getPageTree(),
//                MediaSizeName.NA_LEGAL, PrintQuality.DRAFT);

       

    }

    private void searchInDB(String codeBarre) {
       

    }
}
