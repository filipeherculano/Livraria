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
            if(!u.alugarLivro(l)){
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
                    if(c.getText().equals(l.getTitulo()) && c.getAttributeValue("autor").equals(l.getAutor())){
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
    
    public void removeLivro(Livro l, int quantidade) throws JDOMException{
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
                List<Element> listLivros = e.getChildren();
                for(Element b : listLivros){
                    if(b.getAttributeValue("autor").equals(l.getAutor()) && b.getText().equals(l.getTitulo())){
                        if(b.getAttributeValue("disponível").equals(b.getAttributeValue("quantidade"))){
                            if(Integer.parseInt(b.getAttributeValue("disponível")) == quantidade){
                                e.removeContent(b);
                            }else{
                                if(Integer.parseInt(b.getAttributeValue("disponível")) > quantidade){
                                    int total = Integer.parseInt(b.getAttributeValue("quantidade")) - quantidade;
                                    int disp = Integer.parseInt(b.getAttributeValue("disponível")) - quantidade;

                                    b.setAttribute("quantidade",String.valueOf(total));
                                    b.setAttribute("disponível", String.valueOf(disp));
                                }
                            }
                        }else{
                            if(Integer.parseInt(b.getAttributeValue("disponível")) >= quantidade){
                                int total = Integer.parseInt(b.getAttributeValue("quantidade")) - quantidade;
                                int disp = Integer.parseInt(b.getAttributeValue("disponível")) - quantidade;

                                b.setAttribute("quantidade",String.valueOf(total));
                                b.setAttribute("disponível", String.valueOf(disp));
                            }
                        }
                    }
                }
            }
        }
               
    }
    
    public ArrayList<Livro> pesquisarLivro(String titulo){
        Livro l = new Livro(null, null, null, 0);
        File file = new File("Acervo.xml");
        Document newDocument = null;
        Element root = null;
        livros = null;
//        boolean notFound = true;
//        String []tituloSplit = titulo.split("\\s+");
        
        if(file.exists()){
                SAXBuilder builder = new SAXBuilder();
            try {
                newDocument = builder.build(file);
            } catch (JDOMException ex) {
                Logger.getLogger(Acervo.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Acervo.class.getName()).log(Level.SEVERE, null, ex);
            }
            root = newDocument.getRootElement();
            
            List<Element> listEditora = root.getChildren();
            for(Element a : listEditora){
                List<Element> listLivro = a.getChildren();
                for(Element b : listLivro){
                    if(b.getText().equals(titulo)){
                        l.setAutor(b.getAttributeValue("autor"));
                        l.setEditora(a.getAttributeValue("nome"));
                        l.setQuantidade(Integer.parseInt(b.getAttributeValue("quantidade")));
                        l.setDisponivel(Integer.parseInt(b.getAttributeValue("disponível")));
                        l.setTitulo(b.getText());
                        livros.add(l);
                    }
//                    if(notFound){
//                        String []livroSplit = b.getText().split("\\s+");
//                        for(int i = 0; i < tituloSplit.length; i++){
//                            while(notFound){
//                                for(int j = 0; j < livroSplit.length; i++){
//                                    if(tituloSplit[i].equals(livroSplit[j])){
//                                        l.setAutor(b.getAttributeValue("autor"));
//                                        l.setEditora(a.getAttributeValue("nome"));
//                                        l.setQuantidade(Integer.parseInt(b.getAttributeValue("quantidade")));
//                                        l.setDisponivel(Integer.parseInt(b.getAttributeValue("disponível")));
//                                        l.setTitulo(b.getText());
//                                        livros.add(l);
//
//                                        notFound = false;
//                                    }
//                                }
//                            }
//                        }
//                    }
                }
            }
        }
        return livros;
    }
    
    public ArrayList<Livro> listarLivros(){
        livros = null;
        Livro l = new Livro(null, null, null, 0);
        File file = new File("Acervo.xml");
        Document newDocument = null;
        Element root = null;
        
        if(file.exists()){
                SAXBuilder builder = new SAXBuilder();
            try {
                newDocument = builder.build(file);
            } catch (JDOMException ex) {
                Logger.getLogger(Acervo.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Acervo.class.getName()).log(Level.SEVERE, null, ex);
            }
            root = newDocument.getRootElement();
        
            List<Element> listEditora = root.getChildren();
            for(Element a : listEditora){
                List<Element> listLivro = a.getChildren();
                for(Element b : listLivro){
                    l.setAutor(b.getAttributeValue("autor"));
                    l.setEditora(a.getAttributeValue("nome"));
                    l.setQuantidade(Integer.parseInt(b.getAttributeValue("quantidade")));
                    l.setDisponivel(Integer.parseInt(b.getAttributeValue("disponível")));
                    l.setTitulo(b.getText());
                    livros.add(l);
                }
            }
        }
        
        return livros;
    }
}
