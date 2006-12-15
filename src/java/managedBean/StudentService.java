/*
 * StudentService.java
 *
 * Created on 13 december 2006, 14:52
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package managedBean;


import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.events.*;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import org.xml.sax.SAXException;


/**
 *
 * @author Kristof
 */
@WebService(targetNamespace="http://tempuri.org/")
public class StudentService
{


   public StudentService () {

        //Uncomment the following line if using designed components 
        //InitializeComponent(); 
    }

    @WebMethod()
    public List<StudentInfo> getStudenten()
    {
        SchemaFactory schemaFactory =
            SchemaFactory.newInstance(
                XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema=null;
        try
        {
            schema= schemaFactory.newSchema(
                new File("Files/studentenlijst.xsd"));
        } catch (SAXException ex)
        {
            ex.printStackTrace();
        }

        DocumentBuilderFactory builder = DocumentBuilderFactory.newInstance();
        
        
        builder.setValidating(false);
        builder.setIgnoringComments(true);
        builder.setSchema(schema);
        DocumentBuilder docBuild = null;
        try
        {
            docBuild = builder.newDocumentBuilder();
        } catch (ParserConfigurationException ex)
        {
            ex.printStackTrace();
        }
        try
        {
            docBuild.parse(FacesContext.getCurrentInstance().getExternalContext().getResource("/Files/studentenlijst.xml").getPath());
        } catch (MalformedURLException ex)
        {
            ex.printStackTrace();
        } catch (SAXException ex)
        {
            ex.printStackTrace();
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }
        return new ArrayList<StudentInfo>();
        //builder.setSchema(Schema.)
  /*      String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        SchemaFactory factory = SchemaFactory.newInstance(language); 
        factory.setErrorHandler(new MyErrorHandler()); 
        factory.setResourceResolver( new MyLSResourceResolver()); 
        StreamSource ss = new StreamSource(new File("/Files/studentenlijst.xsd"));
        Schema schema = factory.newSchema(ss);

/*           
        XmlReaderSettings settings = new XmlReaderSettings();
        settings.ValidationType = ValidationType.Schema;
        settings.IgnoreComments = true;
        settings.IgnoreWhitespace = true;
        settings.IgnoreProcessingInstructions = true;
        settings.ValidationFlags |= System.Xml.Schema.XmlSchemaValidationFlags.ProcessSchemaLocation;
        String path = Server.MapPath("~/Files/studentenlijst.xml"); 
        //using (XmlReader reader = XmlReader.Create(path, settings))
        XmlReader reader;
        {
            try
            {
                List<StudentInfo> lijst = new List<StudentInfo>();
                while (reader.ReadToFollowing("student"))
                {
                    Utils.StudentInfo info = new StudentInfo();
                    XmlReader studentreader = reader.ReadSubtree();
                    while (studentreader.Read())
                    {
                        if (studentreader.IsStartElement("studentnummer"))
                            info.Studentnr = int.Parse(studentreader.ReadElementContentAsString());
                        if (studentreader.IsStartElement("naam"))
                            info.Naam = studentreader.ReadElementContentAsString();
                        if (studentreader.IsStartElement("voornaam"))
                            info.Voornaam = studentreader.ReadElementContentAsString();
                        if (studentreader.IsStartElement("email"))
                            info.Email = studentreader.ReadElementContentAsString();
                        if (studentreader.IsStartElement("studiejaar"))
                            info.Studiejaar = studentreader.ReadElementContentAsString();
                        if (studentreader.IsStartElement("paswoord"))
                            info.Paswoord = studentreader.ReadElementContentAsString();
                        if (studentreader.IsStartElement("projecten"))
                        {
                            XmlReader projectreader = reader.ReadSubtree();
                            info.GeindividualiseerdTraject = true;
                            while (projectreader.Read())
                            {
                                if (projectreader.IsStartElement("project"))
                                {
                                    int projectid = int.Parse(projectreader.GetAttribute("id"));
                                    info.AddProject(projectid);
                                }
                            }
                        }
                    }
                    lijst.Add(info);
                }
                return lijst;
            }
            catch (Exception ex)
            {
                throw new Exception("Fout lezen Xml : " + ex.Message, ex);
            }
        }*/
}
}
