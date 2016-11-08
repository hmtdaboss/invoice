/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jasperinvoice;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.PrintException;
import net.sf.jasperreports.engine.JRException;
import org.icepdf.core.exceptions.PDFException;
import org.icepdf.core.exceptions.PDFSecurityException;

/**
 *
 * @author fsafi
 */
public class JasperInvoice {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Jasper jas = new Jasper();
            jas.generatePdf();
            // TODO code application logic here
        } catch (SQLException ex) {
            Logger.getLogger(JasperInvoice.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JRException ex) {
            Logger.getLogger(JasperInvoice.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PDFException ex) {
            Logger.getLogger(JasperInvoice.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PDFSecurityException ex) {
            Logger.getLogger(JasperInvoice.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(JasperInvoice.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PrintException ex) {
            Logger.getLogger(JasperInvoice.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
