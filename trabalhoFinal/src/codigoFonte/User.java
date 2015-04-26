/** This code is open-source. Every item inside it is free to use wherever you want as long as you don't sell it.
 * Keep this code open-source and share it! 
 * This code was built as a conclusion program for a OOP(Objects-Oriented Programming) class. 
 * @author Filipe Herculano Rocha & Gabriel Angelo Freire Gonçalves
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
import org.jdom2.output.XMLOutputter;
import org.joda.time.LocalDate;


public class User {
    private String tipo;
    private String matricula;
    private String nome;
    private ArrayList<Livro> livros;
    private ArrayList<Livro> historico;
    private double divida;
    private String password;
    
    public User(String tipo, String matricula, String nome, String password) {
        this.tipo = tipo;
        this.matricula = matricula;
        this.nome = nome;
        this.password = password;
    }

    public ArrayList<Livro> getHistorico() {
        return historico;
    }

    public void setHistórico(ArrayList<Livro> histórico) {
        this.historico = histórico;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public boolean alugarLivro(Livro l){
        boolean success = false;
        File file = new File("Sistema.xml");
        Document newDocument = null;
        Element root = null, livro = null;
        Attribute autor = null, editora = null, entrega = null, aluguel = null, id = null;
        
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
        
        List<Element> listUser = root.getChildren();
        for(Element a : listUser){
            if(a.getAttributeValue("matrícula").equals(this.matricula)){
                if(this.tipo.equals("professor") && a.getChildren("livro").size() >= 0 && a.getChildren("livro").size() < 3){
                    List<Element> list = a.getChildren("livro");
                    System.out.println(list.size());
                    
                    livro = new Element("livro");
                    autor = new Attribute("autor", l.getAutor());
                    editora = new Attribute("editora", l.getEditora());
                    aluguel = new Attribute("diaAluguel", l.getAluguel());
                    entrega = new Attribute("diaEntrega", l.getEntrega());
                    id = new Attribute("id", l.getId());

                    livro.setAttribute(id);
                    livro.setAttribute(autor);
                    livro.setAttribute(editora);
                    livro.setAttribute(aluguel);
                    livro.setAttribute(entrega);
                    livro.setAttribute("título", l.getTitulo());
                    
                    

                    a.addContent(livro);
                    Element histórico = new Element("histórico");
                    
                    //Element livroHistórico = new Element("livro");
                    Attribute autorHistórico = new Attribute("autor",l.getAutor());
                    Attribute editoraHistórico = new Attribute("editora", l.getEditora());
                    Attribute aluguelHistórico = new Attribute("diaAluguel", l.getAluguel());
                    Attribute entregaHistórico = new Attribute("diaEntrega", l.getEntrega());
                    Attribute idHistórico = new Attribute("id", l.getId());
                    
                    histórico.setAttribute(idHistórico);
                    histórico.setAttribute(autorHistórico);
                    histórico.setAttribute(editoraHistórico);
                    histórico.setAttribute(aluguelHistórico);
                    histórico.setAttribute(entregaHistórico);
                    histórico.setAttribute("título", l.getTitulo());
                    a.addContent(histórico);
                   
                   
                    success = true;

                }else{
                    if(this.tipo.equals("aluno") && a.getChildren().size() >= 0 && a.getChildren().size() < 2){
                        livro = new Element("livro");
                        autor = new Attribute("autor", l.getAutor());
                        editora = new Attribute("editora", l.getEditora());
                        aluguel = new Attribute("dataAluguel", l.getAluguel());
                        entrega = new Attribute("dataEntrega", l.getEntrega());
                        id = new Attribute("id", l.getId());

                        livro.setAttribute(id);
                        livro.setAttribute(autor);
                        livro.setAttribute(editora);
                        livro.setAttribute(aluguel);
                        livro.setAttribute(entrega);
                        livro.setAttribute("título", l.getTitulo());
                        
                        Element histórico = new Element("histórico");
                    
                        //Element livroHistórico = new Element("livro");
                        Attribute autorHistórico = new Attribute("autor",l.getAutor());
                        Attribute editoraHistórico = new Attribute("editora", l.getEditora());
                        Attribute aluguelHistórico = new Attribute("diaAluguel", l.getAluguel());
                        Attribute entregaHistórico = new Attribute("diaEntrega", l.getEntrega());
                        Attribute idHistórico = new Attribute("id", l.getId());

                        histórico.setAttribute(idHistórico);
                        histórico.setAttribute(autorHistórico);
                        histórico.setAttribute(editoraHistórico);
                        histórico.setAttribute(aluguelHistórico);
                        histórico.setAttribute(entregaHistórico);
                        histórico.setAttribute("título", l.getTitulo());
                        a.addContent(histórico);
                        

                        a.addContent(livro);
                        
//                        histórico = new Element("histórico");
//                        histórico.setAttribute(aluguel);
//                        histórico.setAttribute(autor);
//                        a.addContent(histórico);
                        
//                        histórico.addContent(livro);
                        success = true;
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
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return success;
    }
    
   
    //para calcular saldo total do usuário use sempre este método.
    public double calcularSaldo() throws JDOMException{
        File file = new File("Sistema.xml");
        Document newDocument = null;
        Element root = null;
        double saldo = 0.00;
        
        if(file.exists()){
            SAXBuilder builder = new SAXBuilder();
            
            try {
                newDocument = builder.build(file);
            } catch (IOException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            root = newDocument.getRootElement();
        }
        List<Element> listUser = root.getChildren("livro");
        for(Element a : listUser){
            if(this.matricula.equals(a.getAttributeValue("matrícula"))){
                List<Element> listLivro = a.getChildren();
                for(Element b : listLivro){
                    Livro livro = new Livro();
                    livro.setId(b.getAttributeValue("id"));
                    saldo += livro.calcularSaldo(this);
                }
            }
        }
        
        return saldo;
    }
    
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<Livro> getLivros() {
        return livros;
    }

    public void setLivros(ArrayList<Livro> livros) {
        this.livros = livros;
    }
}
