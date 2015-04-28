    /*
     * To change this license header, choose License Headers in Project Properties.
     * To change this template file, choose Tools | Templates
     * and open the template in the editor.
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
    import java.util.logging.Level;
    import java.util.logging.Logger;
    import org.jdom2.Attribute;
    import org.jdom2.Document;
    import org.jdom2.Element;
    import org.jdom2.JDOMException;
    import org.jdom2.input.SAXBuilder;
    import java.util.ArrayList; 
    import org.jdom2.output.XMLOutputter;
    import java.util.Random;
    import org.joda.time.LocalDate;
    //import codigoFonte.User;
    //import codigoFonte.Livro;



    public class Sistema {
        private ArrayList<User> users;



        public boolean addUser(User u) throws IOException{

            boolean success = false, matriculaExists = false;
            File file = new File("Sistema.xml");
            Document newDocument = null;
            Element root = null;
            Attribute matricula = null, nome = null, tipo = null, senha = null;
            Element user = null;
            
            if(u.getTipo().isEmpty() || u.getTipo().isEmpty() || u.getPassword().isEmpty()){
                return success;
            }

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

                matricula = new Attribute("matrícula",this.newId());
                tipo = new Attribute("tipo", u.getTipo());
                nome = new Attribute("nome",u.getNome());
                senha = new Attribute("senha",u.getPassword());



                user.setAttribute(matricula);
                user.setAttribute(nome);
                user.setAttribute(tipo);
                user.setAttribute(senha);
                //user.setAttribute(divida);

                root.addContent(user);

                success = true;
            }
    //        

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
            }

            List<Element> listusers = root.getChildren();
            for(Element e : listusers){
                if(e.getAttributeValue("matrícula").equals(u.getMatricula())){
                    e.getAttribute("nome").setValue(u.getNome());
                    e.getAttribute("tipo").setValue(u.getTipo());
                    e.getAttribute("senha").setValue(u.getPassword());
                    
                    success = true;
                    
                    XMLOutputter out = new XMLOutputter();

                    try {
                        FileWriter arquivo = new FileWriter(file);
                        out.output(newDocument, arquivo);
                    } catch (IOException ex) {
                        Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    return success;
                }
            }
            return success;
        }

        public  ArrayList<User> listarUser(){
            File file = new File("Sistema.xml");
            Document newDocument = null;
            Element root = null;
            ArrayList<User> users =  new ArrayList<User>();;
            ArrayList<Livro> livros = new ArrayList<Livro>();


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
                User user = new User(null, null, null, null);
                List<Element> listlivro = e.getChildren("livro");
                List<Element> historico = e.getChildren("histórico");

                if(!listlivro.isEmpty()) 

                    for(Element l : listlivro){
                        Livro livro = new Livro(null,null,null,0);
                        livro.setAutor(l.getAttributeValue("autor"));
                        livro.setEditora(l.getAttributeValue("editora"));
                        livro.setTitulo(l.getAttributeValue("título")); 
                        livros.add(livro);
                }

                //List<Element> historico = e.getChildren("histórico");
                ArrayList<Livro> historicoObjeto = new ArrayList<Livro>();
                if(!historico.isEmpty()){
                    for(Element h : historico){
                        Livro livroHistorico = new Livro(null,null,null,0);
                        livroHistorico.setAutor(h.getAttributeValue("autor"));
                        livroHistorico.setEditora(h.getAttributeValue("editora"));
                        livroHistorico.setTitulo(h.getAttributeValue("título"));
                        historicoObjeto.add(livroHistorico);
                    }
                }


                user.setMatricula(e.getAttributeValue("matrícula"));
                user.setNome(e.getAttributeValue("nome"));
                user.setTipo(e.getAttributeValue("tipo"));
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
            if (e.getAttributeValue("matrícula").equals(matricula)){
                List<Element> listlivros = e.getChildren("livro");
                ArrayList<Livro> livros = new ArrayList<Livro>();
                user = new User(null , null, null, null);
                if(!listlivros.isEmpty()){
                    for(Element l : listlivros){
                        Livro livro = new Livro(null,null,null,0);
                        livro.setAutor(l.getAttributeValue("autor"));
                        livro.setEditora(l.getAttributeValue("editora"));
                        livro.setTitulo(l.getAttributeValue("título"));
                        livros.add(livro);
                    }
                }
                List<Element> historico = e.getChildren("histórico");
                ArrayList<Livro> historicoObjeto = new ArrayList<Livro>();
                if(!historico.isEmpty()){
                    for(Element h : historico){
                        Livro livroHistorico = new Livro(null,null,null,0);
                        livroHistorico.setAutor(h.getAttributeValue("autor"));
                        livroHistorico.setEditora(h.getAttributeValue("editora"));
                        livroHistorico.setTitulo(h.getAttributeValue("título"));
                        historicoObjeto.add(livroHistorico);
                    }
                }

                   // double divida = Double.parseDouble(e.getAttributeValue("divida"));
                user.setLivros(livros);
                //user.setHistórico(historicoObjeto);
                user.setMatricula(matricula);
                user.setTipo(e.getAttributeValue("tipo"));
                user.setNome(e.getAttributeValue("nome"));
                user.setPassword(e.getAttributeValue("senha"));
                    //return user;
    //            }
                return user;
                }
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

        List<Element> listusers = root.getChildren("user");
        for(Element e : listusers){
            if(e.getAttributeValue("matrícula").equals(u.getMatricula())){
                root.removeContent(e);

                XMLOutputter out = new XMLOutputter();

                try {
                    FileWriter arquivo = new FileWriter(file);
                    out.output(newDocument, arquivo);
                } catch (IOException ex) {
                    Logger.getLogger(Sistema.class.getName()).log(Level.SEVERE, null, ex);
                }

                success = true;
                return success;
            }
        }
        return success;
    }
     @SuppressWarnings("empty-statement")
        public String newId() throws IOException{
            File file = new File("idHandlerMatricula.txt");
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
                            int n = Integer.parseInt(read) + 1;
                            hex = Integer.toString(n);
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
                hex = "54205421";
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

        public String emularId() throws IOException{
            File file = new File("idHandlerMatricula.txt");
            BufferedReader in = null;
            String hex = null;
            if(file.exists()){
                String read = null;
                try {
                    in = new BufferedReader(new FileReader(file));
                    do{
                        read = in.readLine();
                        if(read != null){
                            int n = Integer.parseInt(read) + 1;
                            hex = Integer.toString(n) + "\n";
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
                hex = "54205421";
            }
            return hex;

        }

        public boolean autentica(User u){
            File file = new File("Sistema.xml");
            Document newDocument = null;
            Element root = null;
            boolean autenticado = false;


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

            User user = null;
            List<Element> listusers = root.getChildren();
            for(Element e : listusers){
                if (e.getAttributeValue("matrícula").equals(u.getMatricula()) && e.getAttributeValue("senha").equals(u.getPassword())){
                    autenticado = true;
                    return autenticado;
                }
            }
            return autenticado;
        }

        public User login(String matricula){
            User user = this.pesquisarUser(matricula);
            return user;
        }



        public boolean autenticaAdmin(String username, String password){
            File file = new File("Sistema.xml");
            Document newDocument = null;
            Element root = null;
            boolean autenticado = false;


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
            if (root.getAttributeValue("username").equals(username) && root.getAttributeValue("password").equals(password)){
                autenticado = true;
                return autenticado;
            }
            return autenticado;
        }


         public static void main(String[] args) throws IOException{ 
    //         
              User user = new User(null, null, null, null);
              String nome = "Gabriel Angelo";
              String tipo = "aluno";
              String senha = "1aaa23";

              user.setNome(nome);
              user.setPassword(senha);
              user.setTipo(tipo);

              Sistema sistema  = new Sistema();
              String value = sistema.emularId();
              System.out.println(value);

         }

    //         String username = "peoo2015";
    //         String password = "peoo2015";
    //         Sistema sistema = new Sistema();
    //         boolean value = sistema.autenticaAdmin(username, password);
    //         System.out.print(value);
    //     } 
    //         User user = new User(null, null, null, null);
    //          String nome = "Gabriel Angelo";
    //          String tipo = "aluno";
    //          String senha = "1aaa23";
    //          
    //          user.setNome(nome);
    //          user.setPassword(senha);
    //          user.setTipo(tipo);
    //         
    //         Sistema sistema  = new Sistema();
    //         boolean value = sistema.addUser(user);
    //         System.out.println(value);
    //     }  
    ////         
    ////         
    ////         String nome = "Gabriel Angelo";
    ////         String tipo = "aluno";
    ////         String senha = "123";
    ////         
    ////         String titulo = "apanhador do campo de centeio";
    ////         String autor = "irami";
    ////         String editora = "companhia";
    ////         String id = "123";
    ////         String 
    //////         String matricula = "1f";
    ////         user.setNome(nome);
    ////         user.setPassword(senha);
    ////         user.setTipo(tipo);
    ////         Sistema sistema  = new Sistema();
    ////         boolean value = sistema.addUser(user);
    ////         System.out.println(value);
    ////         user.setNome(nome);
    ////         user.setTipo(tipo);
    ////         user.setPassword(senha);
    ////         user.setMatricula(matricula);
    ////
    ////         Sistema sistema  = new Sistema();
    ////         boolean autenticado = sistema.(user);
    //////         if (autenticado){
    //////             User userAutenticado = sistema.login(user.getMatricula());
    //////             System.out.println(userAutenticado.getNome() + " foi logado com sucesso !");
    //////             ArrayList<Livro> listLivros = userAutenticado.getLivros();
    //////             for(Livro l : listLivros){
    //////                 System.out.println(l.getTitulo());
    //////             }
    //////         }
    //////         else{
    //////             System.out.println("erro");
    ////         }
    ////         //System.out.println(autenticado);
    ////         //ArrayList<Livro> lista = user.getHistórico();
    //         
    //         


         }












