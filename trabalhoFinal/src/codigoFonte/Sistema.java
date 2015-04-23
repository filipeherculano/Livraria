/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package codigoFonte;


import java.io.File;
import java.io.FileWriter;
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
import org.jdom2.output.XMLOutputter;
//import codigoFonte.User;
//import codigoFonte.Livro;



public class Sistema {
    private ArrayList<User> users;

//    Sistema() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
    
    public boolean addUser(User u){
       // users.add(u);
        boolean success = false, matriculaExists = false;
        File file = new File("Sistema.xml");
        Document newDocument = null;
        Element root = null;
        Attribute matricula = null, nome = null, tipo = null, divida = null;
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
        
        if(root.getChildren().size() > 0){
            List<Element> listUsers = root.getChildren();
            for(Element a : listUsers){
                if(a.getAttributeValue("matrícula").equals(u.getMatricula())){
                    matriculaExists = true;
                }
            }
        }
        
        if(!matriculaExists){
            user = new Element("user");
        
            matricula = new Attribute("matrícula", u.getMatricula());
            tipo = new Attribute("tipo", u.getTipo());

            user.setAttribute(matricula);
            user.setText(u.getNome());
            user.setAttribute(tipo);

            root.addContent(user);
            
            success = true;
        }
//        List<Element> listusers = root.getChildren("user");
//
//        for(Element e : listusers){
//            //if(!e.getAttributeValue("matricula").equals( u.getMatricula())){
//                user = new Element("user");
//                matricula = new Attribute("matricula", u.getMatricula());
//                nome = new Attribute("nome", u.getNome());
//                divida = new Attribute("divida", Double.toString(u.getDivida()));
//                tipo = new Attribute("tipo", u.getTipo());
//                user.setAttribute(matricula);
//                user.setAttribute(nome);
//                user.setAttribute(divida);
//                user.setAttribute(tipo);
//                //falta mais algum método pra adicionar ?
//                //lista = root.getChildren();
//                e.addContent(user);
//                success = true;
//                return success;
//            //}
//        }
        
        XMLOutputter out = new XMLOutputter();
    
        try {
            FileWriter arquivo = new FileWriter(file);
            out.output(newDocument, arquivo);
        } catch (IOException ex) {
            Logger.getLogger(Sistema.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return success;
    }
    
    public boolean editarUser(User u){
        File file = new File("Sistema.xml");
        Document newDocument = null;
        Element root = null;
        boolean success = false;
        
        if(file.exists()){
            SAXBuilder builder = new SAXBuilder();
            try {
                newDocument = builder.build(file);
            } catch (JDOMException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }catch (IOException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }
            root = newDocument.getRootElement();
        }else{
            root = new Element("sistema");
            
            newDocument = new Document(root);
        }
        
        List<Element> listusers = root.getChildren();
        for(Element e : listusers){
            if(e.getAttribute("matricula").equals(u.getMatricula())){
                e.getAttribute("nome").setValue(u.getNome());
                e.getAttribute("tipo").setValue(u.getTipo());
                success = true;
                return success;
            }
        }
        return success;
    }
    
    public  ArrayList<User> listarUser(){
        File file = new File("Sistema.xml");
        Document newDocument = null;
        Element root = null;
        ArrayList<User> users = null;
        ArrayList<Livro> livros = null;
        
        if(file.exists()){
            SAXBuilder builder = new SAXBuilder();
            try {
                newDocument = builder.build(file);
            } catch (JDOMException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }catch (IOException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }
            root = newDocument.getRootElement();
        }else{
            root = new Element("sistema");
            
            newDocument = new Document(root);
        }
         
         List<Element> listusers = root.getChildren();
         for(Element e : listusers){
             User user = new User();
             //user.setDivida(Double.parseDouble(e.getAttribute("divida")));
             user.setMatricula(e.getAttributeValue("matricula"));
             user.setNome(e.getAttributeValue("nome"));
             user.setTipo(e.getAttributeValue("tipo"));
             List<Element> listlivro = e.getChildren();
             Livro livro = new Livro(null,null,null,0);
             for(Element l : listlivro){
                 
                 livro.setAutor(l.getAttributeValue("autor"));
                 livro.setEditora(l.getAttributeValue("editora"));
                 livro.setTitulo(l.getAttributeValue("titulo")); 
                 livros.add(livro);
             }
             user.setLivros(livros);
             users.add(user);
         }
         return users;
    }
          
             
        
    
    
    
public User pesquisarUser(String matricula){
    File file = new File("Sistema.xml");
    Document newDocument = null;
    Element root = null;
    User user = null;
    if(file.exists()){
        SAXBuilder builder = new SAXBuilder();
        try {
            newDocument = builder.build(file);
        } catch (JDOMException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }catch (IOException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        root = newDocument.getRootElement();
    }else{
        root = new Element("sistema");

        newDocument = new Document(root);
    }
    List<Element> listusers = root.getChildren();
    for(Element e : listusers){
        if (e.getAttribute("matricula").equals(matricula)){
            List<Element> listlivros = e.getChildren();
            ArrayList<Livro> livros = null;
            for(Element l : listlivros){
                Livro livro = new Livro(null,null,null,0);
                livro.setAutor(l.getAttributeValue("autor"));
                livro.setEditora(l.getAttributeValue("editora"));
                livro.setTitulo(l.getAttributeValue("titulo"));
                livros.add(livro);
                user = new User();
                double divida = Double.parseDouble(e.getAttributeValue("divida"));
                user.setLivros(livros);
                user.setDivida(divida);
                user.setMatricula(matricula);
                user.setTipo(e.getAttributeValue("tipo"));
                user.setNome(e.getAttributeValue("nome"));
                return user;
            }
        }
        return user;
    }
    return user;
}


public boolean removeUser(User u){
    File file = new File("Sistema.xml");
    Document newDocument = null;
    Element root = null;
    Element user = null;
    boolean success = false;
        
  if(file.exists()){
            SAXBuilder builder = new SAXBuilder();
            try {
                newDocument = builder.build(file);
            } catch (JDOMException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }catch (IOException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }
            root = newDocument.getRootElement();
        }else{
            root = new Element("sistema");
            
            newDocument = new Document(root);
        }
        
    List<Element> listusers = root.getChildren();
    for(Element e : listusers){
        List<Element> listlivros = e.getChildren();
        double divida = Double.parseDouble(e.getAttributeValue("divida"));
            
        if(e.getAttribute("matricula").equals(u.getMatricula())){
            if(listlivros.isEmpty()){
                if(divida == 0){
                    root.removeContent(e);
                    success = true;
                    return success;
                }
            }else{
                if (divida == 0){
                    return success;
                }
                else if (divida > 0){
                    return success;
                }
            }
        }
    }
    
        XMLOutputter out = new XMLOutputter();
    
        try {
            FileWriter arquivo = new FileWriter(file);
            out.output(newDocument, arquivo);
        } catch (IOException ex) {
            Logger.getLogger(Sistema.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    return success;
}
   
            
     public static void main(String[] args) {  
         User user = new User();
         String nome = "Gabriel Angelo";
         String tipo = "Aluno";
         String matricula = "1275215";
         //double divida = 3.00;
         user.setNome(nome);
         user.setTipo(tipo);
         user.setMatricula(matricula);
         Sistema sistema = new Sistema();
         boolean value = sistema.addUser(user);
         System.out.printf("%s",value);
     }
      
     
    
    
      
}

     


    
    

