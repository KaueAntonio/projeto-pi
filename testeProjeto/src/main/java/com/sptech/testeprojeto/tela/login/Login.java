package com.sptech.testeprojeto.tela.login;

import com.github.britooo.looca.api.core.Looca;
import com.sptech.testeprojeto.Connection;
import com.sptech.testeprojeto.Log;
import com.sptech.testeprojeto.ValidacaoLogin;
import com.sptech.testeprojeto.tela.cliques.TelaAtendimento;
import com.sptech.testeprojeto.tela.monitoramento.Tela;

import java.awt.Color;
import java.awt.Point;
import java.io.Console;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.Timer;
import java.util.TimerTask;
import oshi.SystemInfo;

public class Login extends javax.swing.JFrame {
    Log log = new Log(); 
    private final Point point = new Point();

    Connection config = new Connection();
    JdbcTemplate template = new JdbcTemplate(config.getDataSource());

    public Login(Boolean estado) {
        if (estado) {
            initComponents();
        } else {
            Timer timer = new Timer();
            ValidacaoLogin login = new ValidacaoLogin();
            timer.scheduleAtFixedRate(new TimerTask() {
                public void run() {
                    login.run();
                }
            }, 5000, 5000);
            TelaAtendimento atendimento = new TelaAtendimento();
            // Dimension size = Toolkit.getDefaultToolkit();
            // tela.setSize(size);
            // tela.setUndecorated(true);
            atendimento.setVisible(true);

        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        btnEntrar = new javax.swing.JButton();
        txtLogin = new javax.swing.JTextField();
        txtSenha = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(400, 410));
        setUndecorated(true);
        setResizable(false);
        setSize(new java.awt.Dimension(400, 410));
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }

            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(173, 216, 230));

        jLabel1.setFont(new java.awt.Font("MS UI Gothic", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Login de Acesso");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap(28, Short.MAX_VALUE)
                                .addComponent(jLabel1)
                                .addGap(28, 28, 28)));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Entre com suas credenciais");

        jButton2.setBackground(new java.awt.Color(255, 166, 0));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Sair");
        jButton2.setBorderPainted(false);
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton2.setFocusPainted(false);
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton2MouseEntered(evt);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton2MouseExited(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        btnEntrar.setBackground(new java.awt.Color(60, 161, 195));
        btnEntrar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnEntrar.setForeground(new java.awt.Color(255, 255, 255));
        btnEntrar.setText("Entrar");
        btnEntrar.setBorderPainted(false);
        btnEntrar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnEntrar.setFocusPainted(false);
        btnEntrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEntrarMouseEntered(evt);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnEntrarMouseExited(evt);
            }
        });
        btnEntrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntrarActionPerformed(evt);
            }
        });

        txtLogin.setBackground(new java.awt.Color(242, 242, 242));
        txtLogin.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtLogin.setForeground(new java.awt.Color(91, 95, 90));
        txtLogin.setText("Nome de Usuário");
        txtLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLoginActionPerformed(evt);
            }
        });

        txtSenha.setBackground(new java.awt.Color(242, 242, 242));
        txtSenha.setText("jPasswordField1");
        txtSenha.setMinimumSize(new java.awt.Dimension(64, 26));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addGroup(jPanel2Layout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtLogin)
                                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnEntrar, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 360,
                                                Short.MAX_VALUE)
                                        .addComponent(txtSenha, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap(22, Short.MAX_VALUE)));
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(jLabel2)
                                .addGap(26, 26, 26)
                                .addComponent(txtLogin, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 46,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22,
                                        Short.MAX_VALUE)
                                .addComponent(btnEntrar, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                                .addGap(14, 14, 14)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtLoginActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_txtLoginActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_txtLoginActionPerformed

    // TesteLogin validaLogin = new TesteLogin();

    private void btnEntrarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnEntrarActionPerformed
        String login = txtLogin.getText();
        String senha = txtSenha.getText();

        // verifica se ambos os campos estão vazios
        if (login.isBlank() && senha.isBlank()) {
            JOptionPane.showMessageDialog(this, "Insira seu login e senha!");
        } // verifica se apenas o campo de login está vazio
        else if (login.isBlank() && !senha.isBlank()) {
            JOptionPane.showMessageDialog(this, "Insira seu login!");
        } else if (!login.isBlank() && senha.isBlank()) {
            JOptionPane.showMessageDialog(this, "Insira a sua senha!");
        } else {
            logar(login, senha);

        }
    }// GEN-LAST:event_btnEntrarActionPerformed

    public void logar(String loginGerente, String senhaGerente) {
        log.criarLog("******** validação login ********");
        Timer timer = new Timer();
        ValidacaoLogin login = new ValidacaoLogin();

        String query = String
                .format("SELECT id_operacao, email_gerente, senha_gerente, fk_empresa FROM [dbo].[operacoes]"
                        + " WHERE email_gerente = '%s'"
                        + "AND senha_gerente = '%s'", loginGerente, senhaGerente);

        List lista = template.queryForList(query);
        if (lista.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Usuário ou Senha Incorretos!");
            log.criarLog("usuário ou senha incorretos");
        } else {

            SelectLogin autenticar = template.queryForObject(query, new LoginMapper());
            SystemInfo info = new SystemInfo();
            if (!(autenticar.getNome() == txtLogin.getText()
                    && autenticar.getSenha() == txtSenha.getText())) {
                JOptionPane.showMessageDialog(this, "Login efetuado");
                String queryInsert = String.format("INSERT INTO [dbo].[maquinas]" +
                        "(hostname, serial_maquina, localidade_maquina, fk_operacao)" +
                        "VALUES ('%s', '%s', 'Equipe 2', %d);",
                        info.getHardware().getComputerSystem().getHardwareUUID(),
                        info.getHardware().getComputerSystem().getSerialNumber(), autenticar.getId());

                template.update(queryInsert);
                TelaAtendimento atendimento = new TelaAtendimento();
                // Dimension size = Toolkit.getDefaultToolkit();
                // tela.setSize(size);
                // tela.setUndecorated(true);
                atendimento.setVisible(true);
                timer.scheduleAtFixedRate(new TimerTask() {
                    public void run() {
                        login.run();
                    }
                }, 0, 1000);

            } else {
                JOptionPane.showMessageDialog(this, "Erro de autenticação");
            }

        }
    }

    //
    //

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton2ActionPerformed
        System.exit(0); // TODO add your handling code here:
    }// GEN-LAST:event_jButton2ActionPerformed

    private void btnEntrarMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btnEntrarMouseEntered
        btnEntrar.setBackground(new Color(235, 235, 235));
        btnEntrar.setForeground(new Color(60, 161, 195));// TODO add your handling code here:
    }// GEN-LAST:event_btnEntrarMouseEntered

    private void jButton2MouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jButton2MouseEntered
        jButton2.setBackground(new Color(235, 235, 235));
        jButton2.setForeground(new Color(255, 166, 0));// TODO add your handling code here:
    }// GEN-LAST:event_jButton2MouseEntered

    private void btnEntrarMouseExited(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btnEntrarMouseExited
        btnEntrar.setBackground(new Color(60, 161, 195));
        btnEntrar.setForeground(Color.WHITE); // TODO add your handling code here:
    }// GEN-LAST:event_btnEntrarMouseExited

    private void jButton2MouseExited(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jButton2MouseExited
        jButton2.setBackground(new Color(255, 166, 0));
        jButton2.setForeground(Color.WHITE); // TODO add your handling code here:
    }// GEN-LAST:event_jButton2MouseExited

    private void formMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_formMouseClicked
        // TODO add your handling code here:
    }// GEN-LAST:event_formMouseClicked

    private void formMousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_formMousePressed
        point.x = evt.getX();
        point.y = evt.getY(); // TODO add your handling code here:
    }// GEN-LAST:event_formMousePressed

    private void formMouseDragged(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_formMouseDragged
        Point p = this.getLocation();
        this.setLocation(p.x + evt.getX() - point.y, p.y + evt.getY() - point.y);// TODO add your handling code here:
    }// GEN-LAST:event_formMouseDragged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
        // (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the default
         * look and feel.
         * For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        // </editor-fold>

        // </editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            SystemInfo info = new SystemInfo();
            String maquina = String.format("%s", info.getHardware().getComputerSystem().getSerialNumber());
            String query = String.format("SELECT * FROM [dbo].[maquinas]"
                    + " WHERE serial_maquina = '%s'", maquina);
            JdbcTemplate template = new JdbcTemplate(new Connection().getDataSource());
            List lista = template.queryForList(query);
            if (lista.isEmpty()) {
                Login tela = new Login(true);

                tela.setLayout(new FlowLayout(1));
                // Dimension size = Toolkit.getDefaultToolkit();
                // tela.setSize(size);
                // tela.setUndecorated(true);
                tela.setVisible(true);
                tela.setDefaultCloseOperation(EXIT_ON_CLOSE);
            } else {
                new Login(false);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEntrar;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField txtLogin;
    private javax.swing.JPasswordField txtSenha;
    // End of variables declaration//GEN-END:variables
}
