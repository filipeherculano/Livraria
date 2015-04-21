///** This code is open-source. Every item inside it is free to use wherever you want as long as you don't sell it.
// * Keep this code open-source and share it! 
// * This code was built as a conclusion program for a OOP(Objects-Oriented Programming) class. 
// * @author Filipe Herculano Rocha & Gabriel Angelo Freire Gonçalves
// */
//import java.io.File;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import org.jdom2.Attribute;
//import org.jdom2.Document;
//import org.jdom2.Element;
//import org.jdom2.JDOMException;
//import org.jdom2.input.SAXBuilder;
//import java.util.ArrayList;
//
//
//public class Sistema {
//    private ArrayList<User> users;
//    
//    public boolean addUser(User u){
//       // users.add(u);
//        boolean success = false;
//        File file = new File("Sistema.xml");
//        Document newDocument = null;
//        Element root = null;
//        Attribute matricula = null;Attribute nome = null;Attribute tipo = null;
//        Attribute divida = null;
//        Element user = null;
//        
//        if(file.exists()){
//            SAXBuilder builder = new SAXBuilder();
//            try {
//                newDocument = builder.build(file);
//            } catch (JDOMException ex) {
//                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (IOException ex) {
//                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            root = newDocument.getRootElement();
//        }else{
//            root = new Element("sistema");
//            
//            newDocument = new Document(root);
//        }
//        
//        
//            List<Element> listusers = root.getChildren("user");
//            
//            for(Element e : listusers){
//                if(e.getAttributeValue("matricula").equals(u.getMatricula())){
//                    return success;
//                }
//                    }
//        user = new Element("user");
//        matricula = new Attribute("matricula",u.getMatricula());
//        nome = new Attribute("nome",u.getNome());
//        divida = new Attribute("divida",Double.toString(u.getDivida()));
//        tipo = new Attribute("tipo",u.getTipo());
//        user.setAttribute(matricula);
//        user.setAttribute(nome);
//        user.setAttribute(divida);
//        user.setAttribute(tipo);
//        //falta mais algum método pra adicionar ?
//        root.addContent(user);
//        success = true;
//        return success;
//        
//        
//    }
//    
//    public void editarUser(User u){
//        
//    }
//    
//    public  ArrayList<User> listarUser(){
//        File file = new File("Sistema.xml");
//        Document newDocument = null;
//        Element root = null;
//        ArrayList<User> users = null;
//        ArrayList<Livro> livros = null;
//        
//         if(file.exists()){
//            SAXBuilder builder = new SAXBuilder();
//            try {
//                newDocument = builder.build(file);
//            } catch (JDOMException ex) {
//                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
//            }catch (IOException ex) {
//                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            root = newDocument.getRootElement();
//        }else{
//            root = new Element("sistema");
//            
//            newDocument = new Document(root);
//        }
//         
////         List<User> listusers = root.getChildren();
////         for(Element e : listusers){
////             User user = new User(null, null,null,null,0.00);
////             user.setDivida(Double.parseDouble(e.getAttribute("divida")));
////             user.setMatricula(e.getAttribute("matricula"));
////             user.getNome(e.getAttribute("nome"));
////             user.setTipo(e.getAttribute("tipo"));
////             ArrayList<Livro> listlivro = e.getChildren();
////             Livro livro = new Livro(null,null,null,0);
////             for(Element l : listlivro){
////                 
////                 livro.setAutor(l.getAttribute("autor"));
////                 livro.setEditora(l.getAttribute("editora"));
////                 livro.setId(l.getAttribute("id")); 
////                 livros.add(livro);
////             }
////             user.setLivros(livros);
////             users.add(user);
////         }
//         return users;
//    }
//          
//             
//        
//    
//    
//    
//    public User pesquisarUser(String matricula){
//        File file = new File("Sistema.xml");
//        Document newDocument = null;
//        Element root = null;
//        User user = null;
//        if(file.exists()){
//            SAXBuilder builder = new SAXBuilder();
//            try {
//                newDocument = builder.build(file);
//            } catch (JDOMException ex) {
//                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
//            }catch (IOException ex) {
//                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            root = newDocument.getRootElement();
//        }else{
//            root = new Element("sistema");
//            
//            newDocument = new Document(root);
//        }
////        List<Element> listusers = root.getChildren();
////        for(Element e : listusers){
////            if (e.getAttribute("matricula").equals(matricula)){
////                List<Element> listlivros = e.getChildren();
////                user = new User(null, null, null, null, 0.00);
////                Element divida = Double.parseDouble(e.getAttribute("divida"));
////                user.setLivros(listlivros);
////                user.setDivida(divida);
////                user.setMatricula(matricula);
////                user.setTipo(e.getAttribute("tipo"));
////                user.setNome(e.getAttribute("nome"));
////                return user;
////            }
////        }
//        return user;
//    }
//            
//                        
//        
//      
//     
//    
//    
//    public boolean removeUser(User u){
//        File file = new File("Sistema.xml");
//        Document newDocument = null;
//        Element root = null;
//        Element user = null;
//        boolean success = false;
//        
//        if(file.exists()){
//                SAXBuilder builder = new SAXBuilder();
//            try{
//                newDocument = builder.build(file);
//            }catch(IOException ex) {
//                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            root = newDocument.getRootElement();
//        }else{
//            root = new Element("sistema");
//            
//            newDocument = new Document(root);
//        }
//        
//        List<Element> listusers = root.getChildren();
//        user = new Element("user");
//        List<Element> listlivros = null;
//        for(Element e : listusers){
//            listlivros = e.getChildren();
//            double divida = Integer.parseDouble(e.getAttribute("divida"));
//            
//            if(e.getAttribute("matricula").equals(u.getMatricula()) && listlivros.isEmpty() && divida == 0){
//                user.removeContent(e);
//                success = true;
//                return success;
//            }
//            else if (e.getAttribute("matricula").equals(u.getMatricula()) && !listlivros.isEmpty() && divida == 0){
//                return success;
//            }
//            else if (e.getAttribute("matricula").equals(u.getMatricula()) && !listlivros.isEmpty() && divida > 0){
//                return success;
//            }
//        }
//        return success;
//    }
//}
//    
//    
