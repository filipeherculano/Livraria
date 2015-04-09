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


public class Aluno extends User{
    
    public boolean alugarLivro(String matricula, Livro l){
        boolean success = false;
        File file = new File("Sistema.xml");
        Document newDocument = null;
        Element root = null, livro = null;
        Attribute autor = null, editora = null;
        
        if(file.exists()){
            SAXBuilder builder = new SAXBuilder();
            try {
                newDocument = builder.build(file);
            } catch (JDOMException ex) {
                Logger.getLogger(Aluno.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Aluno.class.getName()).log(Level.SEVERE, null, ex);
            }
            root = newDocument.getRootElement();
        }else{
            root = new Element("sistema");
            
            newDocument = new Document(root);
        }
        
        List<Element> listAlunos = root.getChildren("aluno");
        for(Element e : listAlunos){
            if(e.getAttributeValue("matrícula").equals(matricula) && e.getChildren().size() >= 0 && e.getChildren().size() < 2){
                livro = new Element("livro");
                autor = new Attribute("autor", l.getAutor());
                editora = new Attribute("nome", l.getEditora());
                livro.setAttribute(autor);
                livro.setAttribute(editora);
                
                e.addContent(livro);
                
                success = true;
            }
        }
        
        return success;
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
    
    public Livro getLivro(int i){
        return this.livros.get(i);
    }

    public void setLivro(Livro l){
        if(livros.size() <= 2){
            livros.add(l);
        }
    }

    @Override
    public Livro emprestimos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void devolver() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Livro getLivros() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setMatricula() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setNome() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
