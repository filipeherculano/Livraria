/** This code is open-source. Every item inside it is free to use wherever you want as long as you don't sell it.
 * Keep this code open-source and share it! 
 * This code was built as a conclusion program for a OOP(Objects-Oriented Programming) class. 
 * @author Filipe Herculano Rocha & Gabriel Angelo Freire Gonçalves
 */

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
import org.jdom2.output.XMLOutputter;

public class Acervo {
    private ArrayList<Livro> livros;

    public Acervo() {
        this.livros = new ArrayList();
    }
    
    public boolean alugarLivro(User u, Livro l) throws JDOMException{
        boolean success = false;
        File file = new File("Acervo.xml");
        Document newDocument = null;
        Element root = null;
        
        if(file.exists()){
                SAXBuilder builder = new SAXBuilder();
            try{
                newDocument = builder.build(file);
            } catch (IOException ex) {
                Logger.getLogger(Acervo.class.getName()).log(Level.SEVERE, null, ex);
            }
            root = newDocument.getRootElement();
        }else{
            root = new Element("acervo");
            
            newDocument = new Document(root);
        }
        
        List<Element> listEditora = root.getChildren();
        for(Element e : listEditora){
            if(e.getAttribute("nome").equals(l.getEditora())){
                List<Element> listLivro = e.getChildren();
                for(Element b : listLivro){
                    if(Integer.parseInt(b.getAttributeValue("disponível")) > 0 && b.getText().equals(l.getTitulo())){
                        int aux = Integer.parseInt(b.getAttributeValue("disponível")) - 1;
                        
                        Attribute d = new Attribute("disponível", String.valueOf(aux));
                        b.setAttribute(d);
                        
                        success = true;
                    }
                }
            }
        }
        
        XMLOutputter xout = new XMLOutputter();
        
        try {
            FileWriter arquivo = new FileWriter(file);
            xout.output(newDocument, arquivo);
        } catch (IOException ex) {
            Logger.getLogger(Acervo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(success){
            if(!u.alugarLivro(u.getMatricula(), l)){
                if(file.exists()){
                    SAXBuilder builder = new SAXBuilder();
                    
                    try{
                        newDocument = builder.build(file);
                    } catch (IOException ex) {
                        Logger.getLogger(Acervo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    root = newDocument.getRootElement();
                }else{
                    root = new Element("acervo");

                    newDocument = new Document(root);
                }
                
                listEditora = root.getChildren();
                for(Element e : listEditora){
                    if(e.getAttribute("nome").equals(l.getEditora())){
                        List<Element> listLivro = e.getChildren();
                        for(Element b : listLivro){
                            if(b.getText().equals(l.getTitulo())){
                                int aux = Integer.parseInt(b.getAttributeValue("disponível")) + 1;
                                
                                Attribute d = new Attribute("disponível", String.valueOf(aux));
                                b.setAttribute(d);
                            }
                        }
                    }
                }
                
                xout = new XMLOutputter();
        
                try {
                    FileWriter arquivo = new FileWriter(file);
                    xout.output(newDocument, arquivo);
                } catch (IOException ex) {
                    Logger.getLogger(Acervo.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                success = false;
            }
        }
        
        return success;
    }
    
    public void addLivro(Livro l) throws JDOMException{
        boolean noEditora = true, noBook = true;
        File file = new File("Acervo.xml");
        Document newDocument = null;
        Element root = null;
        
        if(file.exists()){
                SAXBuilder builder = new SAXBuilder();
            try{
                newDocument = builder.build(file);
            } catch (IOException ex) {
                Logger.getLogger(Acervo.class.getName()).log(Level.SEVERE, null, ex);
            }
            root = newDocument.getRootElement();
        }else{
            root = new Element("acervo");
            
            newDocument = new Document(root);
        }
        
        Element livro = new Element("Livro");
        Attribute autor = null, quantidade = null, avaible = null;
        
        List<Element> listEditora = root.getChildren();
        for(Element e : listEditora){
            if(e.getAttribute("nome").equals(l.getEditora())){
                noEditora = false;
                List<Element> listLivro = e.getChildren();
                for(Element b : listLivro){
                    if(b.getText().equals(l.getTitulo()) && b.getAttributeValue("autor").equals(l.getAutor())){
                        int total = Integer.parseInt(b.getAttributeValue("quantidade"));
                        int disp = Integer.parseInt(b.getAttributeValue("disponível"));
                        total += l.getQuantidade();
                        disp += l.getQuantidade();
                        
                        quantidade = new Attribute("quantidade", String.valueOf(total));
                        avaible = new Attribute("disponível", String.valueOf(disp));
                        livro.setAttribute(quantidade);
                        livro.setAttribute(avaible);
                        
                        noBook = false;
                    }
                }
                if(noBook){
                    autor = new Attribute("autor", l.getAutor());
                    quantidade = new Attribute("quantidade",String.valueOf(l.getQuantidade()));
                    avaible = new Attribute("disponível", String.valueOf(l.getQuantidade()));
                    livro.setAttribute(autor);
                    livro.setAttribute(quantidade);
                    livro.setAttribute(avaible);
                    livro.setText(l.getTitulo());
                    e.addContent(livro);
                }   
            }
        }
        
        if(noEditora){
            Element editora = new Element("editora");
            Attribute nome = new Attribute("nome",l.getEditora());
            editora.setAttribute(nome);
            
            autor = new Attribute("autor", l.getAutor());
            quantidade = new Attribute("quantidade",String.valueOf(l.getQuantidade()));
            avaible = new Attribute("disponível", String.valueOf(l.getQuantidade()));
            livro.setAttribute(autor);
            livro.setAttribute(quantidade);
            livro.setAttribute(avaible);
            livro.setText(l.getTitulo());
                    
            editora.addContent(livro);
            
            root.addContent(editora);
        }
        
        XMLOutputter xout = new XMLOutputter();
        
        try {
            FileWriter arquivo = new FileWriter(file);
            xout.output(newDocument, arquivo);
        } catch (IOException ex) {
            Logger.getLogger(Acervo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    public void removeLivro(Livro l) throws JDOMException{
        boolean noEditora = true;
        File file = new File("Acervo.xml");
        Document newDocument = null;
        Element root = null;
        
        if(file.exists()){
                SAXBuilder builder = new SAXBuilder();
            try{
                newDocument = builder.build(file);
            } catch (IOException ex) {
                Logger.getLogger(Acervo.class.getName()).log(Level.SEVERE, null, ex);
            }
            root = newDocument.getRootElement();
        }else{
            root = new Element("acervo");
            
            newDocument = new Document(root);
        }
        
        List<Element> listEditora = root.getChildren();
        for(Element e : listEditora){
            if(e.getAttributeValue("nome").equals(l.getEditora())){
                List<Element> listLivro = e.getChildren();
                for(Element c : listLivro){
                    if(c.getText() == l.getTitulo() && c.getAttributeValue("autor").equals(l.getAutor())){
                        e.removeContent(c);
                    }
                }
            }
        }
        
        XMLOutputter xout = new XMLOutputter();
        
        try {
            FileWriter arquivo = new FileWriter(file);
            xout.output(newDocument, arquivo);
        } catch (IOException ex) {
            Logger.getLogger(Acervo.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
    
    public void removeLivro(Livro l, int quantidade){
        
    }
    
    public Livro pesquisarLivro(String titulo){
        for(Livro a : livros){
            if(a.getTitulo().equals(titulo)){
                return a;
            }else{
                if(a.getAutor().equals(titulo)){
                    return a;
                }
            }
        }
        return null;
    }
}
