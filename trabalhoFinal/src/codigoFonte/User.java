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


public class User {
    private String tipo;
    private String matricula;
    private String nome;
    private ArrayList<Livro> livros;
    private double divida;
    
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
                if(this.tipo.equals("professor") && a.getChildren().size() >= 0 && a.getChildren().size() < 3){

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

                        a.addContent(livro);

                        success = true;
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
        List<Element> listUser = root.getChildren();
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

    public double getDivida() {
        return divida;
    }

    public void setDivida(double divida) {
        this.divida = divida;
    }

    
}
