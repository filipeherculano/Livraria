/** This code is open-source. Every item inside it is free to use wherever you want as long as you don't sell it.
 * Keep this code open-source and share it! 
 * This code was built as a conclusion program for a OOP(Objects-Oriented Programming) class. 
 * @author Filipe Herculano Rocha & Gabriel Angelo Freire Gonçalves
 */

package codigoFonte;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;
import org.joda.time.Days;
import org.joda.time.LocalDate;

public class Livro {
    
    private String titulo;
    private String autor;
    private String editora;
    private String id;
    private String aluguel;
    private String entrega;
    private int quantidade;
    private int disponivel;
    private double divida;

    //para calcular um saldo total use este método que é chamado na classe User, não use diretamente aqui.
    public double calcularSaldo(User u) throws JDOMException{
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
            if(u.getMatricula().equals(a.getAttributeValue("matrícula"))){
                if(a.getAttributeValue("tipo").equals("professor")){
                    List<Element> listLivro = a.getChildren("livro");
                    for(Element b : listLivro){
                        if(b.getAttributeValue("id").equals(this.id)){
                            LocalDate now = LocalDate.now();
                            LocalDate entrega = new LocalDate(b.getAttributeValue("dataEntrega"));
                            Days daysBetween = Days.daysBetween(entrega, now);
                            int days = daysBetween.getDays();
                            if(days > 7){
                                saldo = days*0.80;
                            }
                        }
                    }
                }else{
                    List<Element> listLivro = a.getChildren("livro");
                    for(Element b : listLivro){
                        if(b.getAttributeValue("id").equals(this.id)){
                            LocalDate now = LocalDate.now();
                            LocalDate entrega = new LocalDate(b.getAttributeValue("dataEntrega"));
                            int days = Days.daysBetween(entrega, now).getDays();

                            if(days > 7){
                                saldo = days*0.50;
                            }
                        }
                    }
                    
                }
            }
        }
        
        return saldo;
    }
    
    //Para calcular um saldo de um livro use este método.
    public double calcularSaldo(User u, Livro l) throws JDOMException{
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
            if(u.getMatricula().equals(a.getAttributeValue("matrícula"))){
                if(a.getAttributeValue("tipo").equals("professor")){
                    List<Element> listLivro = a.getChildren("livro");
                    for(Element b : listLivro){
                        if(b.getAttributeValue("id").equals(l.getId())){
                            LocalDate now = LocalDate.now();
                            LocalDate entrega = new LocalDate(b.getAttributeValue("dataEntrega"));
                            Days daysBetween = Days.daysBetween(entrega, now);
                            int days = daysBetween.getDays();
                            if(days > 7){
                                saldo = days*0.80;
                            }
                        }
                    }
                }else{
                    List<Element> listLivro = a.getChildren("livro");
                    for(Element b : listLivro){
                        if(b.getAttributeValue("id").equals(l.getId())){
                            LocalDate now = LocalDate.now();
                            LocalDate entrega = new LocalDate(b.getAttributeValue("dataEntrega"));
                            int days = Days.daysBetween(entrega, now).getDays();

                            if(days > 7){
                                saldo = days*0.50;
                            }
                        }
                    }
                    
                }
            }
        }
        
        return saldo;
    }
    
    public Livro(String titulo, String autor, String editora, int quantidade) {
        this.titulo = titulo;
        this.autor = autor;
        this.editora = editora;
        this.quantidade = quantidade;
    }  
    
    public Livro(){
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(int disponivel) {
        this.disponivel = disponivel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAluguel() {
        return aluguel;
    }

    public void setAluguel(String aluguel) {
        this.aluguel = aluguel;
    }

    public String getEntrega() {
        return entrega;
    }

    public void setEntrega(String entrega) {
        this.entrega = entrega;
    }

    public double getDivida() {
        return divida;
    }

    public void setDivida(double divida) {
        this.divida = divida;
    }
    
    
    
}
