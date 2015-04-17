/** This code is open-source. Every item inside it is free to use wherever you want as long as you don't sell it.
 * Keep this code open-source and share it! 
 * This code was built as a conclusion program for a OOP(Objects-Oriented Programming) class. 
 * @author Filipe Herculano Rocha & Gabriel Angelo Freire Gonçalves
 */
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import java.util.ArrayList;


public class Sistema {
    private ArrayList<User> users;
    
    public boolean addUser(User u,boolean professor){
       // users.add(u);
        boolean success = false;
        File file = new File("Sistema.xml");
        Document newDocument = null;
        Element root = null;
        Attribute matricula = null;Attribute nome = null;
        Attribute divida = null;
        Element user = null;
        
        if(file.exists()){
            SAXBuilder builder = new SAXBuilder();
            try {
                newDocument = builder.build(file);
            } catch (JDOMException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }
            root = newDocument.getRootElement();
        }else{
            root = new Element("sistema");
            
            newDocument = new Document(root);
        }
        
        if (professor){
            List<Element> listprofessores = root.getChildren("professor");
            
            for(Element e : listprofessores){
                if(!e.getAttributeValue("matricula").equals(u.getMatricula()    )){
                    return success;
                }
                    }
            user = new Element("professor");
            matricula = new Attribute("matricula",u.getMatricula());
            nome = new Attribute("nome",u.getNome());
            divida = new Attribute("divida",Double.toString(u.getDivida()));
            user.setAttribute(matricula);
            user.setAttribute(nome);
            user.setAttribute(divida);
            success = true;
            return success;
        }else{
            List<Element> listalunos = root.getChildren("aluno");
            
            for(Element e : listalunos){
                if(!e.getAttributeValue("matricula").equals(u.getMatricula())){
                    return success;
                }
            }
            user = new Element("aluno");
            matricula = new Attribute("matricula",u.getMatricula());
            nome = new Attribute("nome",u.getNome());
            divida = new Attribute("divida",Double.toString(u.getDivida()));
            user.setAttribute(matricula);
            user.setAttribute(nome);
            user.setAttribute(divida);
            success = true;
            return success;
        }
    }
    
    public void editarUser(User u,boolean professor){
    
    }
            
            
                        
        
      
     
    
    
    public void removeUser(User u){
        users.remove(u);
    }
    
    
}
