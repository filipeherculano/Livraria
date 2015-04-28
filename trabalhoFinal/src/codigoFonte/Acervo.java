/** This code is open-source. Every item inside it is free to use wherever you want as long as you don't sell it.
 * Keep this code open-source and share it! 
 * This code was built as a conclusion program for a OOP(Objects-Oriented Programming) class. 
 * @author Filipe Herculano Rocha & Gabriel Angelo Freire Gonçalves
 */
package codigoFonte;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;
import org.joda.time.LocalDate;

public class Acervo{
    
    public boolean alugarLivro(User u, Livro l) throws JDOMException{
        boolean success = false;
        File file = new File("Acervo.xml");
        Document newDocument = null;
        Element root = null, user;
        Attribute userEntrega = null, userAluguel = null;
        
        LocalDate now = LocalDate.now(), next = now.plusDays(7);
        String currentDate = now.toString(), nextDate = next.toString();
        
        l.setAluguel(currentDate);
        l.setEntrega(nextDate);
        
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
                for(Element b : listLivro){
                    if(Integer.parseInt(b.getAttributeValue("disponível")) > 0 && b.getAttributeValue("título").equals(l.getTitulo())){
                        int aux = Integer.parseInt(b.getAttributeValue("disponível")) - 1;
                        
                        b.getAttribute("disponível").setValue(String.valueOf(aux));
                        
                        userAluguel = new Attribute("dataAluguel", currentDate);
                        userEntrega = new Attribute("dataEntrega", nextDate);
                        
                        user = new Element("user");
                        user.setAttribute("tipo", u.getTipo());
                        user.setAttribute("matrícula", u.getMatricula());
                        user.setAttribute("nome", u.getNome());
                        user.setAttribute(userAluguel);
                        user.setAttribute(userEntrega);
                        
                        b.addContent(user);
                        
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
                            if(b.getAttributeValue("título").equals(l.getTitulo())){
                                int aux = Integer.parseInt(b.getAttributeValue("disponível")) + 1;
                                
                                b.getAttribute("disponível").setValue(String.valueOf(aux));
                                
                                List<Element> listUser = b.getChildren();
                                for(Element c : listUser){
                                    if(c.getAttributeValue("matrícula").equals(u.getMatricula())){
                                        b.removeContent(c);
                                    }
                                }
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
    
    public Double devolverLivro(User u , Livro l) throws JDOMException{
        boolean success = false;
        File file = new File("Sistema.xml");
        Document newDocument = null;
        Element root = null, aux = null;
        boolean bookExists = false;
        
        if(file.exists()){
                SAXBuilder builder = new SAXBuilder();
            try{
                newDocument = builder.build(file);
            } catch (IOException ex) {
                Logger.getLogger(Acervo.class.getName()).log(Level.SEVERE, null, ex);
            }
            root = newDocument.getRootElement();
        }
        
        List<Element> listUser = root.getChildren();
        for(Element a : listUser){
            if(a.getAttributeValue("matrícula").equals(u.getMatricula())){
                List<Element> listLivro = a.getChildren("livro");
                for(Element b : listLivro){
                    if(b.getAttributeValue("editora").equals(l.getEditora()) && b.getAttributeValue("autor").equals(l.getAutor()) && b.getAttributeValue("título").equals(l.getTitulo())){
                        aux = b;
                        a.removeContent(b);
                        bookExists = true;
                    } 
                }
            }
        }
        
        XMLOutputter out = new XMLOutputter();
        
        try {
            FileWriter arquivo = new FileWriter(file);
            out.output(newDocument, arquivo);
        } catch (IOException ex) {
            Logger.getLogger(Acervo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(bookExists){
            file = new File("Acervo.xml");
        
            if(file.exists()){
                    SAXBuilder builder = new SAXBuilder();
                try{
                    newDocument = builder.build(file);
                } catch (IOException ex) {
                    Logger.getLogger(Acervo.class.getName()).log(Level.SEVERE, null, ex);
                }
                root = newDocument.getRootElement();
            }

            List<Element> listEditora = root.getChildren();
            for(Element a : listEditora){
                List<Element> listLivro = a.getChildren();
                for(Element b : listLivro){
                    if(b.getAttributeValue("autor").equals(aux.getAttributeValue("autor")) && b.getAttributeValue("título").equals(aux.getAttributeValue("título"))){
                        String auxiliar = String.valueOf(Integer.parseInt(b.getAttributeValue("disponível"))+1) ;
                        b.getAttribute("disponível").setValue(auxiliar);
                    }
                }
            }
            
            out = new XMLOutputter();
            
            try {
                FileWriter arquivo = new FileWriter(file);
                out.output(newDocument, arquivo);
            } catch (IOException ex) {
                Logger.getLogger(Acervo.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        return 0.0;
    }
    
    public boolean addLivro(Livro l) throws JDOMException{
        boolean noEditora = true, noBook = true, success = false;
        File file = new File("Acervo.xml");
        Document newDocument = null;
        Element root = null;
        
        if(l.getAutor().isEmpty() || l.getTitulo().isEmpty() || l.getEditora().isEmpty() || String.valueOf(l.getQuantidade()).isEmpty()){
            return success;
        }
        
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
        
        Element livro = new Element("livro");
        Attribute autor = null, quantidade = null, avaible = null, id = null;
        
        List<Element> listEditora = root.getChildren();
        for(Element e : listEditora){
            if(e.getAttributeValue("nome").equals(l.getEditora()) && !success){
                noEditora = false;
                List<Element> listLivro = e.getChildren();
                for(Element b : listLivro){
                    if(b.getAttributeValue("título").equals(l.getTitulo()) && b.getAttributeValue("autor").equals(l.getAutor())){
                        int total = Integer.parseInt(b.getAttributeValue("quantidade"));
                        int disp = Integer.parseInt(b.getAttributeValue("disponível"));
                        total += l.getQuantidade();
                        disp += l.getQuantidade();
                       
                        b.getAttribute("quantidade").setValue(String.valueOf(total));
                        b.getAttribute("disponível").setValue(String.valueOf(disp));
                        
                        noBook = false;
                        success = true;
                    }
                }
                if(noBook){
                    autor = new Attribute("autor", l.getAutor());
                    quantidade = new Attribute("quantidade",String.valueOf(l.getQuantidade()));
                    avaible = new Attribute("disponível", String.valueOf(l.getQuantidade()));
                    
                    try {
                        id = new Attribute("id", this.newId());
                    } catch (IOException ex) {
                        Logger.getLogger(Acervo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    livro.setAttribute(id);
                    livro.setAttribute(autor);
                    livro.setAttribute(quantidade);
                    livro.setAttribute(avaible);
                    livro.setAttribute("título", l.getTitulo());
                    
                    e.addContent(livro);
                    success = true;
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
            
            try {
                id = new Attribute("id", this.newId());
            } catch (IOException ex) {
                Logger.getLogger(Acervo.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            livro.setAttribute(id);
            livro.setAttribute(autor);
            livro.setAttribute(quantidade);
            livro.setAttribute(avaible);
            livro.setAttribute("título", l.getTitulo());
                    
            editora.addContent(livro);
            
            root.addContent(editora);
            
            success = true;
        }
        
        XMLOutputter xout = new XMLOutputter();
        
        try {
            FileWriter arquivo = new FileWriter(file);
            xout.output(newDocument, arquivo);
        } catch (IOException ex) {
            Logger.getLogger(Acervo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return success;
    }
        
    // VERIFICAÇÃO SE O USUARIO JÁ PAGOU.
    public boolean removeLivro(Livro l) throws JDOMException{
        boolean noEditora = true;
        File file = new File("Acervo.xml");
        Document newDocument = null;
        Element root = null;
        boolean removed = false;
        
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
                    if(c.getAttributeValue("título").equals(l.getTitulo()) && c.getAttributeValue("autor").equals(l.getAutor()) && c.getAttributeValue("disponível").equals(c.getAttributeValue("quantidade"))){
                        e.removeContent(c);
                        removed = true;
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
           
        return removed;
    }
    
    public boolean removeLivro(Livro l, int quantidade) throws JDOMException{
        File file = new File("Acervo.xml");
        Document newDocument = null;
        Element root = null;
        boolean removed = false;
        
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
                    if(b.getAttributeValue("autor").equals(l.getAutor()) && b.getAttributeValue("título").equals(l.getTitulo())){
                        if(b.getAttributeValue("disponível").equals(b.getAttributeValue("quantidade"))){
                            if(Integer.parseInt(b.getAttributeValue("disponível")) == quantidade){
                                e.removeContent(b);
                                
                                removed = true;
                            }else{
                                if(Integer.parseInt(b.getAttributeValue("disponível")) > quantidade){
                                    int total = Integer.parseInt(b.getAttributeValue("quantidade")) - quantidade;
                                    int disp = Integer.parseInt(b.getAttributeValue("disponível")) - quantidade;
                                    
                                    b.getAttribute("quantidade").setValue(String.valueOf(total));
                                    b.getAttribute("disponível").setValue(String.valueOf(disp));
                                    
                                    removed = true;
                                }
                            }
                        }else{
                            if(Integer.parseInt(b.getAttributeValue("disponível")) >= quantidade){
                                int total = Integer.parseInt(b.getAttributeValue("quantidade")) - quantidade;
                                int disp = Integer.parseInt(b.getAttributeValue("disponível")) - quantidade;

                                b.getAttribute("quantidade").setValue(String.valueOf(total));
                                b.getAttribute("disponível").setValue(String.valueOf(disp));
                                
                                removed = true;
                            }
                        }
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
        
        return removed;
    }
    
    //Precisa ser ainda atualizado a pesquisa dinâmica
    public Livro pesquisarLivro(String titulo){
        Livro l = null;
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
                    if(b.getAttributeValue("título").equals(titulo)){
                        l = new Livro(null, null, null, 0);
                        l.setAutor(b.getAttributeValue("autor"));
                        l.setEditora(a.getAttributeValue("nome"));
                        l.setId(b.getAttributeValue("id"));
                        l.setQuantidade(Integer.parseInt(b.getAttributeValue("quantidade")));
                        l.setDisponivel(Integer.parseInt(b.getAttributeValue("disponível")));
                        l.setTitulo(b.getAttributeValue("título"));
                    }
                }
            }
        }
        return l;
    }
    
    public ArrayList<Livro> listarLivros(){
        File file = new File("Acervo.xml");
        Document newDocument = null;
        Element root = null;
        ArrayList<Livro> livros = new ArrayList();
        
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
                    Livro l = new Livro(null, null, null, 0);
                    l.setAutor(b.getAttributeValue("autor"));
                    l.setEditora(a.getAttributeValue("nome"));
                    l.setId(b.getAttributeValue("id"));
                    l.setQuantidade(Integer.parseInt(b.getAttributeValue("quantidade")));
                    l.setDisponivel(Integer.parseInt(b.getAttributeValue("disponível")));
                    l.setTitulo(b.getAttributeValue("título"));
                    livros.add(l);
                }
            }
        }
        return livros;
    }

    public boolean alterarLivro(Livro l) throws JDOMException{
        boolean noEditora = true, success = false, newEditora = true;
        File file = new File("Acervo.xml");
        Document newDocument = null;
        Element root = null, aux = null;
        
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
        
        //Alterando atributos que não mechem com a editora.
        List<Element> listEditora = root.getChildren();
        for(Element a : listEditora){
            if(a.getAttributeValue("nome").equals(l.getEditora())){
                List<Element> listLivro = a.getChildren();
                for(Element b : listLivro){
                    if(b.getAttributeValue("id").equals(l.getId())){
                        b.getAttribute("autor").setValue(l.getAutor());
                        b.getAttribute("título").setValue(l.getTitulo());
                        success = true;
                        noEditora = false;
                    }
                }
            }
        }
        
        //se a editora foi alterada, então ele entra aqui
        if(noEditora){
            //aqui ele usa um Element auxiliar para pegar tudo que tem no antigo elemento não alterado e deleta.
            List<Element> listUser = null;
            for(Element a : listEditora){
                List<Element> listLivro = a.getChildren();
                for(Element b : listLivro){
                    if(b.getAttributeValue("id").equals(l.getId())){
                        b.getAttribute("autor").setValue(l.getAutor());
                        b.getAttribute("título").setValue(l.getTitulo());
                        aux = b;
                        listUser = b.getChildren();
                        a.removeContent(b);
                    }
                }
            }
            //se existir a editora que nomeei.
            for(Element a : listEditora){
                if(a.getAttributeValue("nome").equals(l.getEditora())){
                    newEditora = false;
                    success = true;
                    
                    a.addContent(aux);
                }
            }
            
            //se não existir a editora na qual eu coloquei o novo nome
            if(newEditora){
                Element editora = new Element("editora");
                
                Attribute name = new Attribute("nome", l.getEditora());
                
                editora.setAttribute(name);
                
                editora.addContent(aux);
                root.addContent(editora);
                
                success = true;
            }
        }
        
        XMLOutputter out = new XMLOutputter();
        
        try {
            FileWriter arquivo = new FileWriter(file);
            out.output(newDocument, arquivo);
        } catch (IOException ex) {
            Logger.getLogger(Acervo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return success;
    }
    
    public String gerarBoleto(Livro l, String matricula, String nome) throws IOException{
        String boleto = null;
        File file = new File("Sistema.xml");
        Document newDocument = null;
        Element root = null;
        BufferedReader in = null;
        BufferedWriter out = null;
        
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
        
        List<Element> listUser = root.getChildren();
        for(Element a : listUser){
            List<Element> listLivro = a.getChildren("livro");
            if(!listLivro.isEmpty()){
                for(Element b : listLivro){
                    if(b.getAttributeValue("id").equals(l.getId()) && b.getAttributeValue("dataAluguel").equals(l.getAluguel())){
                        boleto = "UECE - UNIVERSIDADE ESTADUAL DO CEARA\n" +
                                    "    BIBLIOTECA CENTRAL - ITAPERI\n" +
                                    "     COMPROVANTE DE EMPRESTIMO\n" +
                                    "\n" +
                                    "----------------------------------------\n" +
                                    "Data do emprestimo : "+l.getAluguel()+"\n" +
                                    "EMPRESTIMO ATUAL - DEVOLVER EM: "+l.getEntrega()+"\n" +
                                    "REG.\t\tTITULO\n" +
                                    "\n" +
                                    "----------------------------------------\n" +
                                    l.getId()+"\t\t"+l.getTitulo()+"\n" +
                                    "\n" +
                                    "\n" +
                                    "\n" +
                                    "________________________________________\n" +
                                    "\n" +
                                    matricula+" - "+nome;
                    }
                }
            }
        }
        String aux = matricula+l.getId()+l.getEntrega()+".txt";
        file = new File(aux);
        
        try{
            out = new BufferedWriter(new FileWriter(file, true));
            out.write(boleto);
            out.newLine();
        } catch(IOException e){
            System.out.println("Ocorreu um problema em: " + e);
            e.printStackTrace();
        } finally {
            if(out != null){
                out.close();
            }
        }
        
        
        return boleto;
    }
            
    @SuppressWarnings("empty-statement")
    public String newId() throws IOException{
        File file = new File("idHandler.txt");
        Random random = new Random();
        BufferedReader in = null;
        BufferedWriter out = null;
        String hex = null;
        
        if(file.exists()){
            String read = null;
            try {
                in = new BufferedReader(new FileReader(file));
                do{
                    read = in.readLine();
                    if(read != null){
                        int n = Integer.parseInt(read, 16) + 1;
                        hex = Integer.toHexString(n);
                    }
                }while(read != null);
                
            } catch (IOException e) {
                System.out.println("Ocorreu um problema em: " + e);
                e.printStackTrace();
            } finally{
                if(in != null){
                    in.close();
                }
            }
        }else{
            hex = "1";
        }
        
        
        try{
            out = new BufferedWriter(new FileWriter(file, true));
            out.write(hex);
            out.newLine();
        } catch(IOException e){
            System.out.println("Ocorreu um problema em: " + e);
            e.printStackTrace();
        } finally {
            if(out != null){
                out.close();
            }
        }
        
        return hex;
    }
    
}
