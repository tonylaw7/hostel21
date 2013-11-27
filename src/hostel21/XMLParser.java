package hostel21;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.io.File;
 
public class XMLParser {
 
  public static void parser(String path) {

    try {
 
            File fXmlFile = new File(path);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);            
            doc.getDocumentElement().normalize();
                
            NodeList nList = doc.getElementsByTagName("hostel");
        
                for (int temp=0; temp< nList.getLength(); temp++) {
                        Node nNode = nList.item(temp);
                        
                        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                                Element eElement = (Element) nNode;
                                
                                Hostel newHostel = new Hostel();                                
                                
                                String name = eElement.getElementsByTagName("name").item(0).getTextContent();
                                String street = eElement.getElementsByTagName("street").item(0).getTextContent();
                                String city = eElement.getElementsByTagName("city").item(0).getTextContent();
                                String state = eElement.getElementsByTagName("state").item(0).getTextContent();
                                String postal = eElement.getElementsByTagName("postal_code").item(0).getTextContent();
                                String country = eElement.getElementsByTagName("country").item(0).getTextContent();
                                Address newAddress = new Address(street, city, state, postal, country);
                                
                                String phone = eElement.getElementsByTagName("phone").item(0).getTextContent();
                                String email = eElement.getElementsByTagName("email").item(0).getTextContent();
                                String facebook = eElement.getElementsByTagName("facebook").item(0).getTextContent();
                                String web = eElement.getElementsByTagName("web").item(0).getTextContent();
                                Contact newContact = new Contact(phone, email, facebook, web);
                                
                                String checkin = eElement.getElementsByTagName("check_in_time").item(0).getTextContent();
                                String checkout = eElement.getElementsByTagName("check_out_time").item(0).getTextContent();
                                boolean smoking = (eElement.getElementsByTagName("smoking").item(0).getTextContent().equals("N")) ? false:true;
                                boolean alcohol = (eElement.getElementsByTagName("alcohol").item(0).getTextContent().equals("N")) ? false:true;
                                String cancelation_deadline = eElement.getElementsByTagName("cancellation_deadline").item(0).getTextContent();
                                String cancellation_penalty = eElement.getElementsByTagName("cancellation_penalty").item(0).getTextContent();
                                
                                newHostel = new Hostel(name,newAddress,newContact,checkin,checkout,smoking,alcohol,cancelation_deadline,cancellation_penalty);
                                
                                Element aElement = (Element) nNode;
                                for (int i=0; i < aElement.getElementsByTagName("availability").getLength(); i++) {
                                                long datetemp = Long.parseLong(aElement.getElementsByTagName("date").item(i).getTextContent());
                                                boolean flag=true;
                                                
                                                Date newDate;
                                                int room = Integer.parseInt(aElement.getElementsByTagName("room").item(i).getTextContent());                                        
                                                int bedNumber = Integer.parseInt(aElement.getElementsByTagName("bed").item(i).getTextContent());                                        
                                                float price = Float.parseFloat(aElement.getElementsByTagName("price").item(i).getTextContent());
                                                Bed newBed = new Bed(bedNumber,room,price,true);
                                                
                                                if (newHostel.getDates() != null) {
                                                        for (int j=0; j<newHostel.getDates().size();j++) {
                                                                if (newHostel.getDates().get(j).getDate()==datetemp) {
                                                                        newHostel.getDates().get(j).getBeds().add(newBed);
                                                                        flag= false;
                                                                        break;
                                                                }
                                                        }
                                                        
                                                }
                                                if (flag) {
                                                        newDate = new Date(datetemp);
                                                        newDate.getBeds().add(newBed);
                                                        newHostel.getDates().add(newDate);
                                                }
                                        }
                                Main.hostel21.add(newHostel);
                                }
                                
                        }
         
    } 
          catch (Exception e) {
        e.printStackTrace();
    }
  }
 
}