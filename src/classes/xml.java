package classes;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
public class xml {
    
    public void CreateUser(
            String usuario,
            String clave,
            String permiso,
            String clave_ant,
            String fech_ant,
            String pregunta,
            String respuesta,
            String departamento,
            String cargo,
            String cedula,
            String nombre,
            String apellido
            ) 
    {
        usuario = usuario.trim();
        clave = clave.trim();
        permiso = permiso.trim();
        clave_ant = clave_ant.trim();
        fech_ant =fech_ant.trim();
        departamento = departamento.trim();
        cargo = cargo.trim();
        cedula = cedula.trim();
        nombre = nombre.trim();
        apellido = apellido.trim();
        try {
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		Document doc = docBuilder.newDocument();
		Element rootElement = doc.createElement("hotel");
		doc.appendChild(rootElement);
		Element staff;
            staff = doc.createElement("Mirasol");
		rootElement.appendChild(staff);
		Attr attr = doc.createAttribute("usuario");
		attr.setValue(usuario);
		staff.setAttributeNode(attr);
                
		Element Clave = doc.createElement("clave");
		Clave.appendChild(doc.createTextNode(clave));
		staff.appendChild(Clave);
	
                Element Permiso = doc.createElement("permiso");
		Permiso.appendChild(doc.createTextNode(permiso));
		staff.appendChild(Permiso);
	
                Element Clave_ant = doc.createElement("claveAnterior");
		Clave_ant.appendChild(doc.createTextNode(clave_ant));
		staff.appendChild(Clave_ant);
	
                Element Fecha_ant = doc.createElement("fechaCambio");
		Fecha_ant.appendChild(doc.createTextNode(fech_ant));
		staff.appendChild(Fecha_ant);
                        
                Element Departamento = doc.createElement("departamento");
		Departamento.appendChild(doc.createTextNode(departamento));
		staff.appendChild(Departamento);
                
                Element Cargo = doc.createElement("cargo");
		Cargo.appendChild(doc.createTextNode(cargo));
		staff.appendChild(Cargo);
                
                Element Cedula = doc.createElement("cedula");
		Cedula.appendChild(doc.createTextNode(cedula));
		staff.appendChild(Cedula);
                
                Element Nombre = doc.createElement("nombre");
		Nombre.appendChild(doc.createTextNode(nombre));
		staff.appendChild(Nombre);
                
                Element Apellido = doc.createElement("apellido");
		Apellido.appendChild(doc.createTextNode(apellido));
		staff.appendChild(Apellido);
        
                // write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File("ini\\user.xml"));
		transformer.transform(source, result);
	  } catch (ParserConfigurationException | TransformerException pce) {
	  }
	}

    /**
     *
     * @return
     */
    public String[] leerXml()
    {
        String[] data=new String[10];
        {
 
    try {
 
	File fXmlFile = new File("ini\\user.xml");
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	Document doc = dBuilder.parse(fXmlFile);
 	doc.getDocumentElement().normalize();
        NodeList nList = doc.getElementsByTagName("Mirasol");

        for (int temp = 0; temp < nList.getLength(); temp++) {
 
		Node nNode = nList.item(temp);
 
		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
 
			Element eElement = (Element) nNode;
 
			data[0]=(eElement.getAttribute("usuario"));
			data[1]=(eElement.getElementsByTagName("clave").item(0).getTextContent());
			data[2]=(eElement.getElementsByTagName("permiso").item(0).getTextContent());
			data[3]=(eElement.getElementsByTagName("claveAnterior").item(0).getTextContent());
			data[4]=(eElement.getElementsByTagName("fechaCambio").item(0).getTextContent());
			data[5]=(eElement.getElementsByTagName("departamento").item(0).getTextContent());
			data[6]=(eElement.getElementsByTagName("cargo").item(0).getTextContent());
			data[7]=(eElement.getElementsByTagName("cedula").item(0).getTextContent());
			data[8]=(eElement.getElementsByTagName("nombre").item(0).getTextContent());
			data[9]=(eElement.getElementsByTagName("apellido").item(0).getTextContent());
 
		}
	}
    } catch (ParserConfigurationException | SAXException | IOException | DOMException e) {
    }
  }
return data; 
    }
}
