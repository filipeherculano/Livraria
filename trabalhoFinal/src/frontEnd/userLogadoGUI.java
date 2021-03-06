/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontEnd;

//import codigoFonte.Sistema;
import codigoFonte.Acervo;
import codigoFonte.Livro;
import codigoFonte.Sistema;
import codigoFonte.User;
import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.jdom2.JDOMException;
import org.joda.time.LocalDate;
        

/**
 *
 * @author filipe
 */
public class userLogadoGUI extends javax.swing.JFrame {
    private User user = new User(null, null, null, null);

    /**
     * Creates new form userLogadoGUI
     */
    public userLogadoGUI() {
        initComponents();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
 
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlblNome = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtbAluguel = new javax.swing.JTable();
        jlblMatricula = new javax.swing.JLabel();
        jlblTipo = new javax.swing.JLabel();
        jtxtfNome = new javax.swing.JTextField();
        jtxtfMatricula = new javax.swing.JTextField();
        jtxtfTipo = new javax.swing.JTextField();
        jtxtfSaldo = new javax.swing.JTextField();
        jlblSaldo = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jlblPainelPrincipal = new javax.swing.JLabel();
        jtxtfSearchLivro = new javax.swing.JTextField();
        jbtnSearchLivro = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbProcuraLivro = new javax.swing.JTable();
        jbtnDevolver = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jbtnAlugar = new javax.swing.JButton();
        jbtnBoleto = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Painel principal");
        setBackground(java.awt.Color.white);
        setResizable(false);
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jlblNome.setText("Nome:");

        jtbAluguel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Livro", "Editora", "Autor", "Dia do alguel", "Dia de entrega", "ID"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jtbAluguel);

        jlblMatricula.setText("Matrícula:");

        jlblTipo.setText("Tipo:");

        jtxtfNome.setEditable(false);
        jtxtfNome.setBackground(java.awt.Color.green);
        jtxtfNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtfNomeActionPerformed(evt);
            }
        });

        jtxtfMatricula.setEditable(false);
        jtxtfMatricula.setBackground(java.awt.Color.green);
        jtxtfMatricula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtfMatriculaActionPerformed(evt);
            }
        });

        jtxtfTipo.setBackground(java.awt.Color.green);
        jtxtfTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtfTipoActionPerformed(evt);
            }
        });

        jtxtfSaldo.setEditable(false);
        jtxtfSaldo.setBackground(java.awt.Color.white);
        jtxtfSaldo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtfSaldoActionPerformed(evt);
            }
        });

        jlblSaldo.setText("Saldo à pagar (R$):");

        jlblPainelPrincipal.setText("Painel principal");

        jtxtfSearchLivro.setToolTipText("Título");
        jtxtfSearchLivro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtfSearchLivroActionPerformed(evt);
            }
        });

        jbtnSearchLivro.setText("Procurar Livro");
        jbtnSearchLivro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnSearchLivroActionPerformed(evt);
            }
        });

        jLabel1.setText("Titulo:");

        jtbProcuraLivro.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Livro", "Editora", "Autor", "Quantidade Total", "Disponível", "ID"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jtbProcuraLivro);

        jbtnDevolver.setText("Devolver um livro");
        jbtnDevolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnDevolverActionPerformed(evt);
            }
        });

        jButton1.setText("Listar todos os livros");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jbtnAlugar.setText("Alugar um livro");
        jbtnAlugar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnAlugarActionPerformed(evt);
            }
        });

        jbtnBoleto.setText("Gerar Boleto.");
        jbtnBoleto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnBoletoActionPerformed(evt);
            }
        });

        jMenu1.setText("Opções");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("Gerar histórico");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setText("Logout");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jSeparator2)
            .addGroup(layout.createSequentialGroup()
                .addGap(617, 617, 617)
                .addComponent(jlblPainelPrincipal)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1065, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jbtnAlugar)
                .addGap(48, 48, 48))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(60, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jtxtfSearchLivro, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jbtnSearchLivro)
                        .addGap(28, 28, 28)
                        .addComponent(jButton1)
                        .addGap(230, 230, 230))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 1069, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addComponent(jlblNome)
                                        .addGap(34, 34, 34)
                                        .addComponent(jtxtfNome, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(113, 113, 113)
                                        .addComponent(jlblTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jtxtfTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jlblMatricula)
                                        .addGap(31, 31, 31)
                                        .addComponent(jtxtfMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(56, 56, 56)
                                        .addComponent(jlblSaldo)
                                        .addGap(11, 11, 11)
                                        .addComponent(jtxtfSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(177, 177, 177)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(49, 49, 49)
                                .addComponent(jbtnDevolver))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(59, 59, 59)
                                .addComponent(jbtnBoleto)))
                        .addGap(44, 44, 44))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jlblPainelPrincipal)
                .addGap(32, 32, 32)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jlblTipo)
                                .addComponent(jtxtfTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                    .addComponent(jlblNome)
                                    .addComponent(jtxtfNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(22, 22, 22)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                    .addComponent(jlblSaldo)
                                    .addComponent(jtxtfSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jtxtfMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlblMatricula))))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jbtnBoleto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jbtnDevolver)))
                .addGap(26, 26, 26)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtxtfSearchLivro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnSearchLivro)
                    .addComponent(jLabel1)
                    .addComponent(jButton1))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(138, 138, 138)
                        .addComponent(jbtnAlugar)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        setSize(new java.awt.Dimension(1363, 827));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jtxtfNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtfNomeActionPerformed
        
    }//GEN-LAST:event_jtxtfNomeActionPerformed

    private void jtxtfMatriculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtfMatriculaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtfMatriculaActionPerformed

    private void jtxtfTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtfTipoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtfTipoActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowActivated

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        
        jtxtfNome.setText(user.getNome());
        jtxtfMatricula.setText(user.getMatricula());
        jtxtfTipo.setText(user.getTipo());
    }//GEN-LAST:event_formWindowOpened

    private void jtxtfSaldoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtfSaldoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtfSaldoActionPerformed

    private void jtxtfSearchLivroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtfSearchLivroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtfSearchLivroActionPerformed

    private void jbtnSearchLivroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnSearchLivroActionPerformed
        Acervo acervo = new Acervo();
        Livro livro = null;
        if(acervo.pesquisarLivro(jtxtfSearchLivro.getText()) != null){
            livro = acervo.pesquisarLivro(jtxtfSearchLivro.getText());
            
            DefaultTableModel model = (DefaultTableModel) jtbProcuraLivro.getModel();

            for(int i = model.getRowCount() - 1; i > -1; i--){
                model.removeRow(i);
            }

            Object[] row = {livro.getTitulo(), livro.getEditora(), livro.getAutor(), livro.getQuantidade(), livro.getDisponivel(), livro.getId()}; 
            model.addRow(row);
        }else{
            JOptionPane.showMessageDialog(rootPane, "Não existe este livro.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jbtnSearchLivroActionPerformed

    private void jbtnDevolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnDevolverActionPerformed
        this.setEnabled(false);
        User user = new User(jtxtfTipo.getText(), jtxtfMatricula.getText(), jtxtfNome.getText(), null);
        Livro livro = new Livro();
        devolverLivroGUI devolverLivro = new devolverLivroGUI(this);
        
        int row = jtbAluguel.getSelectedRow();
        if(row >= 0){
            String[] code = {jtbAluguel.getValueAt(row, 0).toString(), jtbAluguel.getValueAt(row, 1).toString(), 
                jtbAluguel.getValueAt(row, 2).toString(), jtbAluguel.getValueAt(row, 3).toString(), jtbAluguel.getValueAt(row, 4).toString(), 
                jtbAluguel.getValueAt(row, 5).toString()}; 

            devolverLivro.setUser(user);
            devolverLivro.setLivro(livro);

            livro.setTitulo(code[0]);
            livro.setEditora(code[1]);
            livro.setAutor(code[2]);
            livro.setAluguel(code[3]);
            livro.setEntrega(code[4]);
            livro.setId(code[5]);

            devolverLivro.setVisible(true);
        } else{
            JOptionPane.showMessageDialog(rootPane, "Nenhum livro selecionado.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jbtnDevolverActionPerformed

    private void jbtnAlugarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnAlugarActionPerformed
        this.setEnabled(false);
        User user = new User(jtxtfTipo.getText(), jtxtfMatricula.getText(), jtxtfNome.getText(), null);
        Livro livro = new Livro();
        alugarLivroGUI alugarLivro = new alugarLivroGUI(this);
        
        int row = jtbProcuraLivro.getSelectedRow();
        if(row >= 0){
            String[] code = {jtbProcuraLivro.getValueAt(row, 0).toString(), jtbProcuraLivro.getValueAt(row, 1).toString(), 
                jtbProcuraLivro.getValueAt(row, 2).toString(), jtbProcuraLivro.getValueAt(row, 5).toString()}; 

            alugarLivro.setUser(user);
            alugarLivro.setLivro(livro);

            LocalDate now = LocalDate.now(), next = now.plusDays(7);
            String currentDate = now.toString(), nextDate = next.toString();

            livro.setTitulo(code[0]);
            livro.setEditora(code[1]);
            livro.setAutor(code[2]);
            livro.setId(code[3]);
            livro.setEntrega(nextDate);
            livro.setAluguel(currentDate);

            alugarLivro.setVisible(true);
        } else{
            JOptionPane.showMessageDialog(rootPane, "Nenhum livro selecionado.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jbtnAlugarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Acervo acervo = new Acervo();
        ArrayList<Livro> livros = acervo.listarLivros();
        DefaultTableModel model = (DefaultTableModel) jtbProcuraLivro.getModel();
        
        for(int i = model.getRowCount() - 1; i > -1; i--){
            model.removeRow(i);
        }
        
        for(Livro b : livros){
            Object[] row = {b.getTitulo(), b.getEditora(), b.getAutor(), b.getQuantidade(), b.getDisponivel(), b.getId()}; 
            model.addRow(row);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        historicoUserGUI historico = new historicoUserGUI();
        historico.setMatricula(jtxtfMatricula.getText());
        historico.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        
        Sistema sistema = new Sistema();
        User usr = sistema.pesquisarUser(user.getMatricula());
            
        if(usr != null){
            DefaultTableModel model = (DefaultTableModel) jtbAluguel.getModel();
            
            for(int i = model.getRowCount() - 1; i > -1; i--){
                model.removeRow(i);
            }
            for(Livro a : usr.getLivros()){
                Object[] row = {a.getTitulo(), a.getEditora(), a.getAutor(), a.getAluguel() , a.getEntrega(), a.getId()};
                model.addRow(row);
            }
        }
        
        try {
            jtxtfSaldo.setText(String.valueOf(user.calcularSaldo()));
            if(user.calcularSaldo() > 0.0){
                jtxtfSaldo.setBackground(Color.red);
            }
        } catch (JDOMException ex) {
            //descrever erro.
            Logger.getLogger(userLogadoGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_formWindowGainedFocus

    private void jbtnBoletoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnBoletoActionPerformed
        Sistema sistema = new Sistema();
        Acervo acervo = new Acervo();
        Livro livro = new Livro();
        boletoGUI boleto = new boletoGUI();
        
        int row = jtbAluguel.getSelectedRow();
        if(row >= 0){
            String[] code = {jtbAluguel.getValueAt(row, 0).toString(), jtbAluguel.getValueAt(row, 1).toString(), 
                jtbAluguel.getValueAt(row, 2).toString(), jtbAluguel.getValueAt(row, 3).toString(), jtbAluguel.getValueAt(row, 4).toString(), 
                jtbAluguel.getValueAt(row, 5).toString()}; 

            livro.setTitulo(code[0]);
            livro.setEditora(code[1]);
            livro.setAutor(code[2]);
            livro.setAluguel(code[3]);
            livro.setEntrega(code[4]);
            livro.setId(code[5]);

            try {
                boleto.setBoleto(acervo.gerarBoleto(livro, jtxtfMatricula.getText(), jtxtfNome.getText()));
                boleto.setVisible(true);
            } catch (IOException ex) {
                Logger.getLogger(userLogadoGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else{
            JOptionPane.showMessageDialog(rootPane, "Nenhum livro selecionado.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jbtnBoletoActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        dispose();
        new indexGUI().setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(userLogadoGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(userLogadoGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(userLogadoGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(userLogadoGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new userLogadoGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JButton jbtnAlugar;
    private javax.swing.JButton jbtnBoleto;
    private javax.swing.JButton jbtnDevolver;
    private javax.swing.JButton jbtnSearchLivro;
    private javax.swing.JLabel jlblMatricula;
    private javax.swing.JLabel jlblNome;
    private javax.swing.JLabel jlblPainelPrincipal;
    private javax.swing.JLabel jlblSaldo;
    private javax.swing.JLabel jlblTipo;
    private javax.swing.JTable jtbAluguel;
    private javax.swing.JTable jtbProcuraLivro;
    private javax.swing.JTextField jtxtfMatricula;
    private javax.swing.JTextField jtxtfNome;
    private javax.swing.JTextField jtxtfSaldo;
    private javax.swing.JTextField jtxtfSearchLivro;
    private javax.swing.JTextField jtxtfTipo;
    // End of variables declaration//GEN-END:variables
}
