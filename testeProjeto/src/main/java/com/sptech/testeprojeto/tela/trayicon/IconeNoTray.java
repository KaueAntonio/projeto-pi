/*
 * IconeNoTray
 *
 * Biblioteca auxiliar para minimizar programas para o Tray
 */
package com.sptech.testeprojeto.tela.trayicon;

import java.awt.AWTException;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;


public class IconeNoTray {

    private boolean suportaTrayIcon;
    private TrayIcon trayIcon;
    private Window parent;
    private String[] textoMenu = {"Restaurar", "Sair"};
    private ImageIcon icone;
    private String toolTipText = "Programa";
    private String balaoSistemaTitulo = "Meu programa";
    private String balaoSistemaDescricao = "O meu programa está executando";

    /**
     * Construtor. Define as características do objeto IconeNoTray
     *
     * @param parent Janela pai
     * @param textoMenu Vetor de 2 Strings onde:
     * <br><b>Índice 0 - </b> É o texto que deve aparecer no menu do <i>Tray</i> para simbolizar restauração da janela
     * <br><b>Índice 1 - </b> É o texto que deve aparecer no menu do <i>Tray</i> para simbolizar a saída do programa
     * @param icone Objeto <i>ImageIcon</i> que será o ícone localizado no Tray
     * @param toolTipText Texto que irá aparecer ao passar o mouse sobre o ícone no tray
     * @param balaoSistemaTitulo Balão de Status do Sistema Operacional - Título
     * @param balaoSistemaDescricao Balão de Status do Sistema Operacional - Descrição
     * @param itensExtras Itens de menu extras no menu do tray
     */
    public IconeNoTray(
            Window parent,
            String[] textoMenu,
            ImageIcon icone,
            String toolTipText,
            String balaoSistemaTitulo,
            String balaoSistemaDescricao,
            MenuItem[] itensExtras)
    {
        this.parent = parent;
        this.textoMenu = textoMenu;
        this.icone = icone;
        this.toolTipText = toolTipText;
        this.balaoSistemaTitulo = balaoSistemaTitulo;
        this.balaoSistemaDescricao = balaoSistemaDescricao;
        setSuportaTrayIcon(true);
        criarTrayIcon(itensExtras);
    }

     /**
     * Construtor. Define as características do objeto IconeNoTray
     *
     * @param parent Janela pai
     * @param textoMenu Vetor de 2 Strings onde:
     * <br><b>Índice 0 - </b> É o texto que deve aparecer no menu do <i>Tray</i> para simbolizar restauração da janela
     * <br><b>Índice 1 - </b> É o texto que deve aparecer no menu do <i>Tray</i> para simbolizar a saída do programa
     * @param icone Objeto <i>ImageIcon</i> que será o ícone localizado no Tray
     * @param toolTipText Texto que irá aparecer ao passar o mouse sobre o ícone no tray
     * @param balaoSistemaTitulo Balão de Status do Sistema Operacional - Título
     * @param balaoSistemaDescricao Balão de Status do Sistema Operacional - Descrição
     */
    public IconeNoTray(
            Window parent,
            String[] textoMenu,
            ImageIcon icone,
            String toolTipText,
            String balaoSistemaTitulo,
            String balaoSistemaDescricao)
    {
        this.parent = parent;
        this.textoMenu = textoMenu;
        this.icone = icone;
        this.toolTipText = toolTipText;
        this.balaoSistemaTitulo = balaoSistemaTitulo;
        this.balaoSistemaDescricao = balaoSistemaDescricao;
        setSuportaTrayIcon(true);
        criarTrayIcon();
    }

    /**
     * Construtor simplificado - define parcialmente as características do objeto
     * @param parent <i>java.awt.Window</i> Janela pai
     * @param icone <i>ImageIcon</i> Ícone que será usado no tray
     */
    public IconeNoTray(Window parent, ImageIcon icone) {
        this.parent = parent;
        this.icone = icone;
        setSuportaTrayIcon(true);
     }


    /**
     * Método executado na inicialização do objeto, determina as características do ícone que será lançado no tray
     */
    public void criarTrayIcon() {

        criarTrayIcon(null);

    }

    /**
     * Método executado na inicialização do objeto, determina as características do ícone que será lançado no tray
     */
    public void criarTrayIcon(MenuItem[] itensExtras) {

        SystemTray tray = SystemTray.getSystemTray();

        // Verifica se o sistema suporta Ã­cones na system tray
        if (!tray.isSupported()) {
            setSuportaTrayIcon(false);
            return;
        }
        //icone q será exibido na bandeja
        //ImageIcon icon = new ImageIcon("running.png");
        //setIconImage(icon.getImage());
        // cria um menu popup para interagir com a aplicação na system tray
        PopupMenu menu = new PopupMenu();
        MenuItem itemRestaurar = new MenuItem(textoMenu[0]);
        itemRestaurar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                // remove a aplicação da tray
                SystemTray.getSystemTray().remove(trayIcon);
                parent.setVisible(true);
            }
        });
        menu.add(itemRestaurar);

        if (itensExtras != null) {
            for (MenuItem item : itensExtras) {
                menu.add(item);
            }
        }
        
        MenuItem itemSair = new MenuItem(textoMenu[1]);
        itemSair.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        menu.add(itemSair);

        // cria o ícone e adiciona o menu a ele
        trayIcon = new TrayIcon(icone.getImage(), toolTipText, menu);
        trayIcon.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    SystemTray.getSystemTray().remove(trayIcon);
                    parent.setVisible(true);
                }
            }
        });
    }

    public void adicionarATray() throws AWTException {

        if (isSuportaTrayIcon()) {

            // esconde a janela
            parent.dispose();
            //adiciona seu programa a systema tray
            SystemTray.getSystemTray().add(trayIcon);
            // aquele balãozinho que aparece qdo o programa tá lá :)
            trayIcon.displayMessage(balaoSistemaTitulo, balaoSistemaDescricao, TrayIcon.MessageType.INFO);

        }

    }

    public void setBalaoSistemaDescricao(String balaoSistemaDescricao) {
        this.balaoSistemaDescricao = balaoSistemaDescricao;
    }

    public void setBalaoSistemaTitulo(String balaoSistemaTitulo) {
        this.balaoSistemaTitulo = balaoSistemaTitulo;
    }

    public void setIcone(ImageIcon icone) {
        this.icone = icone;
    }

    public void setParent(Window parent) {
        this.parent = parent;
    }

    public void setTextoMenu(String[] textoMenu) {
        this.textoMenu = textoMenu;
    }

    public void setToolTipText(String toolTipText) {
        this.toolTipText = toolTipText;
    }

    public void setTrayIcon(TrayIcon trayIcon) {
        this.trayIcon = trayIcon;
    }

    public boolean isSuportaTrayIcon() {
        return suportaTrayIcon;
    }

    public void setSuportaTrayIcon(boolean suportaTrayIcon) {
        this.suportaTrayIcon = suportaTrayIcon;
    }
}