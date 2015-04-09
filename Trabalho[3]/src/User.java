/** This code is open-source. Every item inside it is free to use wherever you want as long as you don't sell it.
 * Keep this code open-source and share it! 
 * This code was built as a conclusion program for a OOP(Objects-Oriented Programming) class. 
 * @author Filipe Herculano Rocha & Gabriel Angelo Freire Gonçalves
 */

import java.util.ArrayList;


public abstract class User {
    protected String matricula;
    protected String nome;
    protected ArrayList<Livro> livros;
    protected double divida;
    
    public abstract Livro emprestimos();
    
    public abstract void devolver();
    
    public abstract boolean alugarLivro(String matricula, Livro l);
    
    public abstract String getMatricula();
    
    public abstract String getNome();
    
    public abstract Livro getLivros();
    
    public abstract void setMatricula();
    
    public abstract void setNome();
    
    public abstract void setLivro(Livro l);
}
