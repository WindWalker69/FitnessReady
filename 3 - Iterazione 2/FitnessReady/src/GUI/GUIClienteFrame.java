package GUI;

import Dominio.*;
import Eccezioni.*;

import java.util.*;
import java.awt.CardLayout;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class GUIClienteFrame extends javax.swing.JFrame {

    private final FitnessReady FR;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    /**
     * Creates new form GUIFrame
     */
    public GUIClienteFrame() {
        FR = FitnessReady.getInstance();
        initComponents();
    }
    
    //----METODI INTERNI----
    
    private void close(){
        this.dispose();
    }
    
    private void changeCard(String nomePannello){
        CardLayout card = (CardLayout)mainPanel.getLayout();
        card.show(mainPanel, nomePannello);
        buttonAggiungiAlCarrello.setEnabled(false);
        buttonModificaQuantità.setEnabled(false);
        buttonRimuoviProdotto.setEnabled(false);
        buttonAcquista.setEnabled(false);
        buttonAnnulla.setEnabled(false);
        buttonPrenota.setEnabled(false);
        buttonModificaOrdine.setEnabled(false);
        buttonDettagliPrenotazione.setEnabled(false);
        buttonModificaPrenotazione.setEnabled(false);
        buttonEliminaPrenotazione.setEnabled(false);
    }
    
    private void updateTable(JTable table, String nomeJTable){
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); //toglie tutte le eventuali righe già presenti in modo da poter aggiornare la tabella senza duplicati
        Object rowData[] = new Object[table.getColumnCount()];
        int i;
        
        switch(nomeJTable){
            case "tableProdotti":
                List<Prodotto> listProd = FR.getGestoreProdotti().getCatalogo().getListaProdotti();
                Prodotto prod;
                for(i = 0; i < listProd.size(); i++){
                    prod = listProd.get(i);
                    rowData[0] = prod.getId();
                    rowData[1] = prod.getNome();
                    rowData[2] = prod.getCategoria();
                    rowData[3] = String.format("%.2f", prod.getPrezzo());
                    rowData[4] = prod.getPrezzoGettoni();
                    rowData[5] = prod.getDisp();
                    model.addRow(rowData);
                }
                break;
            
            case "tableCarrello":
                List<RigaCarrello> listCarrello = FR.getGestoreAcquisto().getCarrello().getRigheCarrello();
                RigaCarrello riga;
                for(i = 0; i < listCarrello.size(); i++){
                    riga = listCarrello.get(i);
                    rowData[0] = riga.getProdotto().getId();
                    rowData[1] = riga.getProdotto().getNome();
                    rowData[2] = riga.getProdotto().getCategoria();
                    rowData[3] = String.format("%.2f", riga.getProdotto().getPrezzo());
                    rowData[4] = riga.getProdotto().getPrezzoGettoni();
                    rowData[5] = riga.getQuantità();
                    rowData[6] = String.format("%.2f", riga.getSubTotale());
                    rowData[7] = riga.getSubTotaleGettoni();
                    model.addRow(rowData);
                }
                break;
                
            case "tablePrenotazioni":
                List<Prenotazione> listPrenotazioniCliente = FR.getGestorePrenotazioni().getListaPrenotazioniCliente(FR.getClienteAttuale().getEmail());
                Prenotazione prenotazione;
                for(i = 0; i < listPrenotazioniCliente.size(); i++){
                    prenotazione = listPrenotazioniCliente.get(i);
                    rowData[0] = prenotazione.getId();
                    rowData[1] = dateFormat.format(prenotazione.getDataPrenotazione());
                    model.addRow(rowData);
                }
                break;
        }
    }
    
    private String correctIntegerEntry(String message, int expectedLength){
        String param;
        char paramChar;
        int i, paramLength;
        
        do{
            param = JOptionPane.showInputDialog(this, message);
            paramLength = param.length();
            for(i = 0; i < paramLength; i++){
                paramChar = param.charAt(i);
                Integer.parseInt(String.valueOf(paramChar));
            }
            if(paramLength != expectedLength)
                switch(expectedLength){
                    case 3:
                        JOptionPane.showMessageDialog(this, "Errore inserimento:\n"
                                + "il CVV deve essere un numero intero a 3 cifre", "Warning", JOptionPane.WARNING_MESSAGE);
                        break;
                    
                    case 5:
                        JOptionPane.showMessageDialog(this, "Errore inserimento:\n"
                                + "il CAP deve essere un numero intero a 5 cifre", "Warning", JOptionPane.WARNING_MESSAGE);
                        break;
                    
                    case 16:
                        JOptionPane.showMessageDialog(this, "Errore inserimento:\n"
                                + "il Numero di Carta deve essere un numero intero a 16 cifre", "Warning", JOptionPane.WARNING_MESSAGE);
                        break;
                }
        }while(paramLength != expectedLength);
        
        return param;
    }
    
    private String correctIntegerEntry(String message, int expectedLength, String defaultValue){
        String param;
        char paramChar;
        int i, paramLength;
        
        do{
            param = JOptionPane.showInputDialog(this, message, defaultValue);
            paramLength = param.length();
            for(i = 0; i < paramLength; i++){
                paramChar = param.charAt(i);
                Integer.parseInt(String.valueOf(paramChar));
            }
            if(paramLength != expectedLength)
                switch(expectedLength){
                    case 3:
                        JOptionPane.showMessageDialog(this, "Errore inserimento:\n"
                                + "il CVV deve essere un numero intero a 3 cifre", "Warning", JOptionPane.WARNING_MESSAGE);
                        break;
                    
                    case 5:
                        JOptionPane.showMessageDialog(this, "Errore inserimento:\n"
                                + "il CAP deve essere un numero intero a 5 cifre", "Warning", JOptionPane.WARNING_MESSAGE);
                        break;
                    
                    case 16:
                        JOptionPane.showMessageDialog(this, "Errore inserimento:\n"
                                + "il Numero di Carta deve essere un numero intero a 16 cifre", "Warning", JOptionPane.WARNING_MESSAGE);
                        break;
                }
        }while(paramLength != expectedLength);
        
        return param;
    }
    
    private int multipleChoice(String question, Object[] options){
        int n = options.length;
        return JOptionPane.showOptionDialog(this, question, "Question", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, options, options[n - 1]);
    }
    
    private Date addDaysToDate(Date date, int days){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_MONTH, days);
        return c.getTime();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        panelLogo = new javax.swing.JPanel();
        labelLogo = new javax.swing.JLabel();
        panelAcquista = new javax.swing.JPanel();
        labelProdotti = new javax.swing.JLabel();
        labelAvvisoAggiungi1 = new javax.swing.JLabel();
        labelAvvisoAggiungi2 = new javax.swing.JLabel();
        labelAvvisoPrenota1 = new javax.swing.JLabel();
        labelAvvisoPrenota2 = new javax.swing.JLabel();
        scrollPaneTabellaProdotti = new javax.swing.JScrollPane();
        tableProdotti = new javax.swing.JTable();
        buttonAggiungiAlCarrello = new javax.swing.JButton();
        buttonPrenota = new javax.swing.JButton();
        buttonOrdinaNome = new javax.swing.JButton();
        buttonOrdinaCategoria = new javax.swing.JButton();
        buttonOrdinaPrezzo = new javax.swing.JButton();
        panelCarrello = new javax.swing.JPanel();
        labelCarrello = new javax.swing.JLabel();
        labelImportoSpesa = new javax.swing.JLabel();
        labelTotaleCarrello = new javax.swing.JLabel();
        labelImportoSpesaGettoni = new javax.swing.JLabel();
        labelTotaleGettoni = new javax.swing.JLabel();
        labelEuro = new javax.swing.JLabel();
        labelAvvisoCarrello1 = new javax.swing.JLabel();
        labelAvvisoCarrello2 = new javax.swing.JLabel();
        labelAvvisoCarrello3 = new javax.swing.JLabel();
        labelAvvisoCarrello4 = new javax.swing.JLabel();
        labelAvvisoCarrello5 = new javax.swing.JLabel();
        scrollPanelTabellaCarrello = new javax.swing.JScrollPane();
        tableCarrello = new javax.swing.JTable();
        buttonModificaQuantità = new javax.swing.JButton();
        buttonRimuoviProdotto = new javax.swing.JButton();
        buttonAcquista = new javax.swing.JButton();
        buttonAnnulla = new javax.swing.JButton();
        panelVisualizzaAcquisti = new javax.swing.JPanel();
        labelVisualizzaAcquisti = new javax.swing.JLabel();
        scrollPaneVisualizzaAcquisti = new javax.swing.JScrollPane();
        textAreaVisualizzaAcquisti = new javax.swing.JTextArea();
        panelOrdini = new javax.swing.JPanel();
        labelOrdini = new javax.swing.JLabel();
        scrollPaneOrdini = new javax.swing.JScrollPane();
        textAreaOrdini = new javax.swing.JTextArea();
        buttonModificaOrdine = new javax.swing.JButton();
        panelDatiProfilo = new javax.swing.JPanel();
        labelDatiProfilo = new javax.swing.JLabel();
        labelImmagineProfilo = new javax.swing.JLabel();
        labelNome = new javax.swing.JLabel();
        labelCognome = new javax.swing.JLabel();
        labelEmail = new javax.swing.JLabel();
        labelPassword = new javax.swing.JLabel();
        labelAltezza = new javax.swing.JLabel();
        labelPeso = new javax.swing.JLabel();
        labelMetri = new javax.swing.JLabel();
        labelChili = new javax.swing.JLabel();
        labelObiettivo = new javax.swing.JLabel();
        labelGettoni = new javax.swing.JLabel();
        labelTotGettoni = new javax.swing.JLabel();
        labelAvvisoDatiProfilo1 = new javax.swing.JLabel();
        textFieldNome = new javax.swing.JTextField();
        textFieldCognome = new javax.swing.JTextField();
        textFieldEmail = new javax.swing.JTextField();
        textFieldPassword = new javax.swing.JTextField();
        textFieldAltezza = new javax.swing.JTextField();
        textFieldPeso = new javax.swing.JTextField();
        radioButtonDefinizione = new javax.swing.JRadioButton();
        radioButtonAumento = new javax.swing.JRadioButton();
        buttonModificaDati = new javax.swing.JButton();
        panelVisualizzaScheda = new javax.swing.JPanel();
        labelNomeScheda = new javax.swing.JLabel();
        labelDieta = new javax.swing.JLabel();
        labelEsercizi = new javax.swing.JLabel();
        scrollPaneDieta = new javax.swing.JScrollPane();
        textAreaDieta = new javax.swing.JTextArea();
        scrollPaneEsercizi = new javax.swing.JScrollPane();
        textAreaEsercizi = new javax.swing.JTextArea();
        buttonEseguiEsercizi = new javax.swing.JButton();
        panelPrenotazioni = new javax.swing.JPanel();
        labelPrenotazioni = new javax.swing.JLabel();
        labelDettagliPrenotazione = new javax.swing.JLabel();
        labelAvvisoPrenotazione1 = new javax.swing.JLabel();
        labelAvvisoPrenotazione2 = new javax.swing.JLabel();
        labelAvvisoPrenotazione3 = new javax.swing.JLabel();
        labelAvvisoPrenotazione4 = new javax.swing.JLabel();
        scrollPaneTabellaPrenotazioni = new javax.swing.JScrollPane();
        tablePrenotazioni = new javax.swing.JTable();
        scrollPaneDettagliPrenotazione = new javax.swing.JScrollPane();
        textAreaDettagliPrenotazione = new javax.swing.JTextArea();
        buttonDettagliPrenotazione = new javax.swing.JButton();
        buttonEliminaPrenotazione = new javax.swing.JButton();
        buttonModificaPrenotazione = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();
        menuHome = new javax.swing.JMenu();
        menuProfilo = new javax.swing.JMenu();
        itemDatiProfilo = new javax.swing.JMenuItem();
        itemVisualizzaScheda = new javax.swing.JMenuItem();
        menuOrdini = new javax.swing.JMenu();
        menuPrenotazioni = new javax.swing.JMenu();
        menuAcquisti = new javax.swing.JMenu();
        itemAcquistaProdotto = new javax.swing.JMenuItem();
        itemVisualizzaAcquisti = new javax.swing.JMenuItem();
        menuCarrello = new javax.swing.JMenu();
        menuEsci = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mainPanel.setLayout(new java.awt.CardLayout());

        labelLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/fitness-ready-logo.png"))); // NOI18N

        javax.swing.GroupLayout panelLogoLayout = new javax.swing.GroupLayout(panelLogo);
        panelLogo.setLayout(panelLogoLayout);
        panelLogoLayout.setHorizontalGroup(
            panelLogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLogoLayout.createSequentialGroup()
                .addGap(265, 265, 265)
                .addComponent(labelLogo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(265, 265, 265))
        );
        panelLogoLayout.setVerticalGroup(
            panelLogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLogoLayout.createSequentialGroup()
                .addGap(140, 140, 140)
                .addComponent(labelLogo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(140, 140, 140))
        );

        mainPanel.add(panelLogo, "panelLogo");

        labelProdotti.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        labelProdotti.setText("Elenco Prodotti");

        labelAvvisoAggiungi1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        labelAvvisoAggiungi1.setText("(*)");

        labelAvvisoAggiungi2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        labelAvvisoAggiungi2.setText("(*) Clicca sul prodotto desiderato in tabella");

        labelAvvisoPrenota1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        labelAvvisoPrenota1.setText("(**)");

        labelAvvisoPrenota2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        labelAvvisoPrenota2.setText("(**) La prenotazione è disponibile solo per i prodotti aventi disponibilità pari a 0");

        tableProdotti.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nome", "Categoria", "Prezzo", "Prezzo Gettoni", "Disponibilità"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableProdotti.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableProdottiMouseClicked(evt);
            }
        });
        scrollPaneTabellaProdotti.setViewportView(tableProdotti);

        buttonAggiungiAlCarrello.setText("Aggiungi al carrello");
        buttonAggiungiAlCarrello.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAggiungiAlCarrelloActionPerformed(evt);
            }
        });

        buttonPrenota.setText("Prenota");
        buttonPrenota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPrenotaActionPerformed(evt);
            }
        });

        buttonOrdinaNome.setText("Ordina per Nome");
        buttonOrdinaNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonOrdinaNomeActionPerformed(evt);
            }
        });

        buttonOrdinaCategoria.setText("Ordina per Categoria");
        buttonOrdinaCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonOrdinaCategoriaActionPerformed(evt);
            }
        });

        buttonOrdinaPrezzo.setText("Ordina per Prezzo");
        buttonOrdinaPrezzo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonOrdinaPrezzoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelAcquistaLayout = new javax.swing.GroupLayout(panelAcquista);
        panelAcquista.setLayout(panelAcquistaLayout);
        panelAcquistaLayout.setHorizontalGroup(
            panelAcquistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAcquistaLayout.createSequentialGroup()
                .addGroup(panelAcquistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelAcquistaLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelProdotti)
                        .addGap(191, 191, 191))
                    .addGroup(panelAcquistaLayout.createSequentialGroup()
                        .addGap(143, 143, 143)
                        .addComponent(buttonOrdinaNome)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonOrdinaCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(182, 182, 182)))
                .addComponent(buttonOrdinaPrezzo)
                .addGap(159, 159, 159))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAcquistaLayout.createSequentialGroup()
                .addContainerGap(78, Short.MAX_VALUE)
                .addGroup(panelAcquistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollPaneTabellaProdotti, javax.swing.GroupLayout.PREFERRED_SIZE, 904, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelAcquistaLayout.createSequentialGroup()
                        .addComponent(labelAvvisoAggiungi2)
                        .addGap(51, 51, 51)
                        .addComponent(labelAvvisoPrenota2)))
                .addGap(78, 78, 78))
            .addGroup(panelAcquistaLayout.createSequentialGroup()
                .addGap(463, 463, 463)
                .addGroup(panelAcquistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(buttonPrenota, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonAggiungiAlCarrello, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelAcquistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelAvvisoAggiungi1)
                    .addComponent(labelAvvisoPrenota1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelAcquistaLayout.setVerticalGroup(
            panelAcquistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAcquistaLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(labelProdotti)
                .addGap(28, 28, 28)
                .addGroup(panelAcquistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonOrdinaCategoria)
                    .addComponent(buttonOrdinaPrezzo)
                    .addComponent(buttonOrdinaNome))
                .addGap(38, 38, 38)
                .addComponent(scrollPaneTabellaProdotti, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54)
                .addGroup(panelAcquistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonAggiungiAlCarrello)
                    .addComponent(labelAvvisoAggiungi1))
                .addGap(18, 18, 18)
                .addGroup(panelAcquistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonPrenota)
                    .addComponent(labelAvvisoPrenota1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 79, Short.MAX_VALUE)
                .addGroup(panelAcquistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelAvvisoAggiungi2)
                    .addComponent(labelAvvisoPrenota2))
                .addGap(26, 26, 26))
        );

        mainPanel.add(panelAcquista, "panelAcquista");

        labelCarrello.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        labelCarrello.setText("Riepilogo Prodotti Carrello");

        labelImportoSpesa.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        labelImportoSpesa.setText("Totale carrello:");

        labelTotaleCarrello.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        labelTotaleCarrello.setText("0");

        labelImportoSpesaGettoni.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        labelImportoSpesaGettoni.setText("Totale carrello in gettoni:");

        labelTotaleGettoni.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        labelTotaleGettoni.setText("0");

        labelEuro.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        labelEuro.setText("€");

        labelAvvisoCarrello1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        labelAvvisoCarrello1.setText("(*)");

        labelAvvisoCarrello2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        labelAvvisoCarrello2.setText("(*)");

        labelAvvisoCarrello3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        labelAvvisoCarrello3.setText("(*) Clicca sul prodotto desiderato in tabella");

        labelAvvisoCarrello4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        labelAvvisoCarrello4.setText("(**)");

        labelAvvisoCarrello5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        labelAvvisoCarrello5.setText("(**) +10€ di spedizione per ordini inferiori a 35€");

        tableCarrello.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nome", "Categoria", "Prezzo", "Prezzo Gettoni", "Quantità", "Sub Totale", "Sub Totale Gettoni"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableCarrello.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableCarrelloMouseClicked(evt);
            }
        });
        scrollPanelTabellaCarrello.setViewportView(tableCarrello);

        buttonModificaQuantità.setText("Modifica Quantità");
        buttonModificaQuantità.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonModificaQuantitàActionPerformed(evt);
            }
        });

        buttonRimuoviProdotto.setText("Rimuovi Prodotto");
        buttonRimuoviProdotto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRimuoviProdottoActionPerformed(evt);
            }
        });

        buttonAcquista.setText("Acquista Prodotti");
        buttonAcquista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAcquistaActionPerformed(evt);
            }
        });

        buttonAnnulla.setText("Annulla Acquisto");
        buttonAnnulla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAnnullaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelCarrelloLayout = new javax.swing.GroupLayout(panelCarrello);
        panelCarrello.setLayout(panelCarrelloLayout);
        panelCarrelloLayout.setHorizontalGroup(
            panelCarrelloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCarrelloLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(labelCarrello)
                .addGap(411, 411, 411))
            .addGroup(panelCarrelloLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(panelCarrelloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCarrelloLayout.createSequentialGroup()
                        .addGroup(panelCarrelloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelCarrelloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(labelAvvisoCarrello5, javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(panelCarrelloLayout.createSequentialGroup()
                                    .addComponent(labelAvvisoCarrello3)
                                    .addGap(29, 29, 29)))
                            .addGroup(panelCarrelloLayout.createSequentialGroup()
                                .addComponent(buttonModificaQuantità)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelAvvisoCarrello1)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonRimuoviProdotto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelAvvisoCarrello2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panelCarrelloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(buttonAcquista, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelImportoSpesaGettoni, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(panelCarrelloLayout.createSequentialGroup()
                                .addComponent(labelAvvisoCarrello4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelImportoSpesa))
                            .addComponent(buttonAnnulla, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(panelCarrelloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelCarrelloLayout.createSequentialGroup()
                                .addComponent(labelTotaleCarrello)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelEuro))
                            .addComponent(labelTotaleGettoni)))
                    .addComponent(scrollPanelTabellaCarrello, javax.swing.GroupLayout.PREFERRED_SIZE, 960, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        panelCarrelloLayout.setVerticalGroup(
            panelCarrelloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCarrelloLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(labelCarrello)
                .addGap(34, 34, 34)
                .addComponent(scrollPanelTabellaCarrello, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelCarrelloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelImportoSpesa)
                    .addComponent(labelTotaleCarrello)
                    .addComponent(labelEuro)
                    .addComponent(labelAvvisoCarrello4))
                .addGap(18, 18, 18)
                .addGroup(panelCarrelloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelImportoSpesaGettoni)
                    .addComponent(labelTotaleGettoni))
                .addGap(31, 31, 31)
                .addGroup(panelCarrelloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonAcquista)
                    .addComponent(buttonRimuoviProdotto)
                    .addComponent(buttonModificaQuantità)
                    .addComponent(labelAvvisoCarrello1)
                    .addComponent(labelAvvisoCarrello2))
                .addGap(18, 18, 18)
                .addComponent(buttonAnnulla)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
                .addComponent(labelAvvisoCarrello3)
                .addGap(10, 10, 10)
                .addComponent(labelAvvisoCarrello5)
                .addContainerGap())
        );

        mainPanel.add(panelCarrello, "panelCarrello");

        labelVisualizzaAcquisti.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        labelVisualizzaAcquisti.setText("Acquisti Effettuati");

        textAreaVisualizzaAcquisti.setEditable(false);
        textAreaVisualizzaAcquisti.setColumns(20);
        textAreaVisualizzaAcquisti.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        textAreaVisualizzaAcquisti.setRows(5);
        scrollPaneVisualizzaAcquisti.setViewportView(textAreaVisualizzaAcquisti);

        javax.swing.GroupLayout panelVisualizzaAcquistiLayout = new javax.swing.GroupLayout(panelVisualizzaAcquisti);
        panelVisualizzaAcquisti.setLayout(panelVisualizzaAcquistiLayout);
        panelVisualizzaAcquistiLayout.setHorizontalGroup(
            panelVisualizzaAcquistiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelVisualizzaAcquistiLayout.createSequentialGroup()
                .addGroup(panelVisualizzaAcquistiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelVisualizzaAcquistiLayout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addComponent(scrollPaneVisualizzaAcquisti, javax.swing.GroupLayout.PREFERRED_SIZE, 900, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelVisualizzaAcquistiLayout.createSequentialGroup()
                        .addGap(442, 442, 442)
                        .addComponent(labelVisualizzaAcquisti)))
                .addContainerGap(81, Short.MAX_VALUE))
        );
        panelVisualizzaAcquistiLayout.setVerticalGroup(
            panelVisualizzaAcquistiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelVisualizzaAcquistiLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(labelVisualizzaAcquisti)
                .addGap(30, 30, 30)
                .addComponent(scrollPaneVisualizzaAcquisti, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(176, Short.MAX_VALUE))
        );

        mainPanel.add(panelVisualizzaAcquisti, "panelVisualizzaAcquisti");

        labelOrdini.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        labelOrdini.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelOrdini.setText("Ordini Effettuati");

        textAreaOrdini.setEditable(false);
        textAreaOrdini.setColumns(20);
        textAreaOrdini.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        textAreaOrdini.setRows(5);
        scrollPaneOrdini.setViewportView(textAreaOrdini);

        buttonModificaOrdine.setText("Modifica Ordine");
        buttonModificaOrdine.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonModificaOrdineActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelOrdiniLayout = new javax.swing.GroupLayout(panelOrdini);
        panelOrdini.setLayout(panelOrdiniLayout);
        panelOrdiniLayout.setHorizontalGroup(
            panelOrdiniLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelOrdiniLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelOrdiniLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelOrdiniLayout.createSequentialGroup()
                        .addComponent(labelOrdini)
                        .addGap(456, 456, 456))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelOrdiniLayout.createSequentialGroup()
                        .addComponent(buttonModificaOrdine)
                        .addGap(466, 466, 466))))
            .addGroup(panelOrdiniLayout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(scrollPaneOrdini, javax.swing.GroupLayout.PREFERRED_SIZE, 900, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 80, Short.MAX_VALUE))
        );
        panelOrdiniLayout.setVerticalGroup(
            panelOrdiniLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelOrdiniLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(labelOrdini)
                .addGap(37, 37, 37)
                .addComponent(scrollPaneOrdini, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(buttonModificaOrdine)
                .addContainerGap(157, Short.MAX_VALUE))
        );

        mainPanel.add(panelOrdini, "panelOrdini");

        labelDatiProfilo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        labelDatiProfilo.setText("Dati Profilo");

        labelImmagineProfilo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/immagine profilo.jpg"))); // NOI18N

        labelNome.setText("Nome:");

        labelCognome.setText("Cognome:");

        labelEmail.setText("E-mail:");

        labelPassword.setText("Password:");

        labelAltezza.setText("Altezza:");

        labelPeso.setText("Peso:");

        labelMetri.setText("m (*)");

        labelChili.setText("Kg (*)");

        labelObiettivo.setText("Obiettivo:");

        labelGettoni.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        labelGettoni.setText("Gettoni:");

        labelTotGettoni.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        labelTotGettoni.setText("0");

        labelAvvisoDatiProfilo1.setText("(*) usare il punto \".\" e non la virgola \",\"");

        radioButtonDefinizione.setText("Definizione massa muscolare");
        radioButtonDefinizione.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioButtonDefinizioneActionPerformed(evt);
            }
        });

        radioButtonAumento.setText("Aumento massa muscolare");
        radioButtonAumento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioButtonAumentoActionPerformed(evt);
            }
        });

        buttonModificaDati.setText("Modifica Dati");
        buttonModificaDati.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonModificaDatiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelDatiProfiloLayout = new javax.swing.GroupLayout(panelDatiProfilo);
        panelDatiProfilo.setLayout(panelDatiProfiloLayout);
        panelDatiProfiloLayout.setHorizontalGroup(
            panelDatiProfiloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDatiProfiloLayout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addGroup(panelDatiProfiloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelNome)
                    .addComponent(labelCognome)
                    .addComponent(labelEmail)
                    .addComponent(labelPassword)
                    .addComponent(labelAltezza)
                    .addComponent(labelPeso)
                    .addComponent(labelObiettivo))
                .addGap(18, 18, 18)
                .addGroup(panelDatiProfiloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelDatiProfiloLayout.createSequentialGroup()
                        .addComponent(radioButtonAumento)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panelDatiProfiloLayout.createSequentialGroup()
                        .addComponent(radioButtonDefinizione)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelDatiProfiloLayout.createSequentialGroup()
                        .addGroup(panelDatiProfiloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelDatiProfiloLayout.createSequentialGroup()
                                .addComponent(textFieldPeso, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(labelChili))
                            .addGroup(panelDatiProfiloLayout.createSequentialGroup()
                                .addComponent(textFieldAltezza, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(labelMetri))
                            .addComponent(textFieldPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textFieldCognome, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 248, Short.MAX_VALUE)
                        .addGroup(panelDatiProfiloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelAvvisoDatiProfilo1)
                            .addComponent(labelImmagineProfilo)
                            .addGroup(panelDatiProfiloLayout.createSequentialGroup()
                                .addComponent(labelGettoni)
                                .addGap(18, 18, 18)
                                .addComponent(labelTotGettoni)))
                        .addGap(98, 98, 98))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDatiProfiloLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelDatiProfiloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonModificaDati)
                    .addComponent(labelDatiProfilo))
                .addGap(474, 474, 474))
        );
        panelDatiProfiloLayout.setVerticalGroup(
            panelDatiProfiloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDatiProfiloLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(labelDatiProfilo)
                .addGap(34, 34, 34)
                .addGroup(panelDatiProfiloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelDatiProfiloLayout.createSequentialGroup()
                        .addGroup(panelDatiProfiloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelNome))
                        .addGap(40, 40, 40)
                        .addGroup(panelDatiProfiloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textFieldCognome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelCognome))
                        .addGap(40, 40, 40)
                        .addGroup(panelDatiProfiloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelEmail)))
                    .addComponent(labelImmagineProfilo))
                .addGroup(panelDatiProfiloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelDatiProfiloLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(panelDatiProfiloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textFieldPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelPassword)))
                    .addGroup(panelDatiProfiloLayout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addGroup(panelDatiProfiloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelGettoni)
                            .addComponent(labelTotGettoni))))
                .addGap(25, 25, 25)
                .addGroup(panelDatiProfiloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textFieldAltezza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelAltezza)
                    .addComponent(labelMetri))
                .addGap(40, 40, 40)
                .addGroup(panelDatiProfiloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textFieldPeso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelPeso)
                    .addComponent(labelChili))
                .addGap(40, 40, 40)
                .addGroup(panelDatiProfiloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radioButtonDefinizione)
                    .addComponent(labelObiettivo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(radioButtonAumento)
                .addGap(17, 17, 17)
                .addGroup(panelDatiProfiloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelAvvisoDatiProfilo1)
                    .addComponent(buttonModificaDati))
                .addGap(18, 18, 18))
        );

        mainPanel.add(panelDatiProfilo, "panelDatiProfilo");

        labelNomeScheda.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        labelNomeScheda.setText("Nome Scheda");

        labelDieta.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        labelDieta.setText("DIETA");

        labelEsercizi.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        labelEsercizi.setText("ESERCIZI");

        textAreaDieta.setColumns(20);
        textAreaDieta.setRows(5);
        scrollPaneDieta.setViewportView(textAreaDieta);

        textAreaEsercizi.setColumns(20);
        textAreaEsercizi.setRows(5);
        scrollPaneEsercizi.setViewportView(textAreaEsercizi);

        buttonEseguiEsercizi.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        buttonEseguiEsercizi.setText("Esegui Esercizi");
        buttonEseguiEsercizi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEseguiEserciziActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelVisualizzaSchedaLayout = new javax.swing.GroupLayout(panelVisualizzaScheda);
        panelVisualizzaScheda.setLayout(panelVisualizzaSchedaLayout);
        panelVisualizzaSchedaLayout.setHorizontalGroup(
            panelVisualizzaSchedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelVisualizzaSchedaLayout.createSequentialGroup()
                .addGroup(panelVisualizzaSchedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelVisualizzaSchedaLayout.createSequentialGroup()
                        .addGap(447, 447, 447)
                        .addComponent(labelNomeScheda))
                    .addGroup(panelVisualizzaSchedaLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(panelVisualizzaSchedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelEsercizi)
                            .addGroup(panelVisualizzaSchedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(labelDieta)
                                .addComponent(scrollPaneDieta, javax.swing.GroupLayout.DEFAULT_SIZE, 1005, Short.MAX_VALUE)
                                .addComponent(scrollPaneEsercizi)))))
                .addContainerGap(27, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelVisualizzaSchedaLayout.createSequentialGroup()
                .addGap(0, 456, Short.MAX_VALUE)
                .addComponent(buttonEseguiEsercizi, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(442, 442, 442))
        );
        panelVisualizzaSchedaLayout.setVerticalGroup(
            panelVisualizzaSchedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelVisualizzaSchedaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelNomeScheda)
                .addGap(36, 36, 36)
                .addComponent(labelDieta)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPaneDieta, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(labelEsercizi)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPaneEsercizi, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(buttonEseguiEsercizi, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        mainPanel.add(panelVisualizzaScheda, "panelVisualizzaScheda");

        labelPrenotazioni.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        labelPrenotazioni.setText("Prenotazioni");

        labelDettagliPrenotazione.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        labelDettagliPrenotazione.setText("DETTAGLI PRENOTAZIONE");

        labelAvvisoPrenotazione1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        labelAvvisoPrenotazione1.setText("(*)");

        labelAvvisoPrenotazione2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        labelAvvisoPrenotazione2.setText("(*)");

        labelAvvisoPrenotazione3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        labelAvvisoPrenotazione3.setText("(*)");

        labelAvvisoPrenotazione4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        labelAvvisoPrenotazione4.setText("(*)Clicca sulla prenotazione desiderata in tabella");

        tablePrenotazioni.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Data ed ora"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablePrenotazioni.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablePrenotazioniMouseClicked(evt);
            }
        });
        scrollPaneTabellaPrenotazioni.setViewportView(tablePrenotazioni);

        textAreaDettagliPrenotazione.setColumns(20);
        textAreaDettagliPrenotazione.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        textAreaDettagliPrenotazione.setRows(5);
        scrollPaneDettagliPrenotazione.setViewportView(textAreaDettagliPrenotazione);

        buttonDettagliPrenotazione.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        buttonDettagliPrenotazione.setText("Dettagli Prenotazione");
        buttonDettagliPrenotazione.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDettagliPrenotazioneActionPerformed(evt);
            }
        });

        buttonEliminaPrenotazione.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        buttonEliminaPrenotazione.setText("Elimina Prenotazione");
        buttonEliminaPrenotazione.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEliminaPrenotazioneActionPerformed(evt);
            }
        });

        buttonModificaPrenotazione.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        buttonModificaPrenotazione.setText("Modifica Prenotazione");
        buttonModificaPrenotazione.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonModificaPrenotazioneActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelPrenotazioniLayout = new javax.swing.GroupLayout(panelPrenotazioni);
        panelPrenotazioni.setLayout(panelPrenotazioniLayout);
        panelPrenotazioniLayout.setHorizontalGroup(
            panelPrenotazioniLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrenotazioniLayout.createSequentialGroup()
                .addGroup(panelPrenotazioniLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPrenotazioniLayout.createSequentialGroup()
                        .addGap(470, 470, 470)
                        .addComponent(labelPrenotazioni))
                    .addGroup(panelPrenotazioniLayout.createSequentialGroup()
                        .addGap(176, 176, 176)
                        .addComponent(scrollPaneTabellaPrenotazioni, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(126, 126, 126)
                        .addGroup(panelPrenotazioniLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(buttonDettagliPrenotazione, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(buttonEliminaPrenotazione, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonModificaPrenotazione, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelPrenotazioniLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelAvvisoPrenotazione1)
                            .addComponent(labelAvvisoPrenotazione2)
                            .addComponent(labelAvvisoPrenotazione3)))
                    .addGroup(panelPrenotazioniLayout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addGroup(panelPrenotazioniLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelAvvisoPrenotazione4)
                            .addComponent(scrollPaneDettagliPrenotazione, javax.swing.GroupLayout.PREFERRED_SIZE, 952, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelDettagliPrenotazione))))
                .addContainerGap(53, Short.MAX_VALUE))
        );
        panelPrenotazioniLayout.setVerticalGroup(
            panelPrenotazioniLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPrenotazioniLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelPrenotazioni)
                .addGroup(panelPrenotazioniLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPrenotazioniLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(scrollPaneTabellaPrenotazioni, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelPrenotazioniLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(panelPrenotazioniLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(buttonDettagliPrenotazione, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelAvvisoPrenotazione1))
                        .addGap(18, 18, 18)
                        .addGroup(panelPrenotazioniLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(buttonModificaPrenotazione, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelAvvisoPrenotazione2))
                        .addGap(18, 18, 18)
                        .addGroup(panelPrenotazioniLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(buttonEliminaPrenotazione, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelAvvisoPrenotazione3))))
                .addGap(18, 18, 18)
                .addComponent(labelDettagliPrenotazione)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scrollPaneDettagliPrenotazione, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 86, Short.MAX_VALUE)
                .addComponent(labelAvvisoPrenotazione4)
                .addGap(32, 32, 32))
        );

        mainPanel.add(panelPrenotazioni, "panelPrenotazioni");

        menuHome.setText("Home");
        menuHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuHomeMouseClicked(evt);
            }
        });
        menuBar.add(menuHome);

        menuProfilo.setText("Profilo");

        itemDatiProfilo.setText("Dati Profilo");
        itemDatiProfilo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemDatiProfiloActionPerformed(evt);
            }
        });
        menuProfilo.add(itemDatiProfilo);

        itemVisualizzaScheda.setText("Visualizza Scheda");
        itemVisualizzaScheda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemVisualizzaSchedaActionPerformed(evt);
            }
        });
        menuProfilo.add(itemVisualizzaScheda);

        menuBar.add(menuProfilo);

        menuOrdini.setText("Ordini");
        menuOrdini.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuOrdiniMouseClicked(evt);
            }
        });
        menuBar.add(menuOrdini);

        menuPrenotazioni.setText("Prenotazioni");
        menuPrenotazioni.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuPrenotazioniMouseClicked(evt);
            }
        });
        menuBar.add(menuPrenotazioni);

        menuAcquisti.setText("Acquista");

        itemAcquistaProdotto.setText("Acquista Prodotto");
        itemAcquistaProdotto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemAcquistaProdottoActionPerformed(evt);
            }
        });
        menuAcquisti.add(itemAcquistaProdotto);

        itemVisualizzaAcquisti.setText("Visualizza Acquisti");
        itemVisualizzaAcquisti.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemVisualizzaAcquistiActionPerformed(evt);
            }
        });
        menuAcquisti.add(itemVisualizzaAcquisti);

        menuBar.add(menuAcquisti);

        menuCarrello.setText("Carrello");
        menuCarrello.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuCarrelloMouseClicked(evt);
            }
        });
        menuBar.add(menuCarrello);

        menuEsci.setText("Esci");
        menuEsci.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuEsciMouseClicked(evt);
            }
        });
        menuBar.add(menuEsci);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //----METODI GUI----
    
    private void itemAcquistaProdottoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemAcquistaProdottoActionPerformed
        changeCard("panelAcquista");
        if(FR.getGestoreAcquisto() == null)
            FR.setGestoreAcquisto();
        updateTable(tableProdotti, "tableProdotti");
    }//GEN-LAST:event_itemAcquistaProdottoActionPerformed

    private void menuHomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuHomeMouseClicked
        changeCard("panelLogo");
    }//GEN-LAST:event_menuHomeMouseClicked

    private void menuCarrelloMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuCarrelloMouseClicked
        changeCard("panelCarrello");
        if(FR.getGestoreAcquisto() == null)
            FR.setGestoreAcquisto();
        labelTotaleCarrello.setText(String.format("%.2f", FR.getGestoreAcquisto().getCarrello().getTotaleCarrello()));
        labelTotaleGettoni.setText(String.valueOf(FR.getGestoreAcquisto().getCarrello().getTotaleGettoni()));
        if(!FR.getGestoreAcquisto().getCarrello().getRigheCarrello().isEmpty()){
            buttonAcquista.setEnabled(true);
            buttonAnnulla.setEnabled(true);
        }
    }//GEN-LAST:event_menuCarrelloMouseClicked

    private void tableProdottiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableProdottiMouseClicked
        DefaultTableModel model = (DefaultTableModel) tableProdotti.getModel();
        int selectedRowIndex = -1;
        selectedRowIndex = tableProdotti.getSelectedRow();
        int idProd = Integer.parseInt(model.getValueAt(selectedRowIndex, 0).toString());
        Prodotto prod = FR.getGestoreProdotti().getCatalogo().getProdotto(idProd);
        
        if(selectedRowIndex != -1){
            if(prod.getDisp() == 0)
                buttonPrenota.setEnabled(true);
            else
                buttonPrenota.setEnabled(false);
            
            buttonAggiungiAlCarrello.setEnabled(true);
        }
        
    }//GEN-LAST:event_tableProdottiMouseClicked

    private void buttonAggiungiAlCarrelloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAggiungiAlCarrelloActionPerformed
        try{
            DefaultTableModel model = (DefaultTableModel) tableProdotti.getModel();
            int selectedRowIndex = tableProdotti.getSelectedRow();
            int idProd = Integer.parseInt(model.getValueAt(selectedRowIndex, 0).toString());
            Prodotto prod = FR.getGestoreProdotti().getCatalogo().getProdotto(idProd);
        
            int quantity = Integer.parseInt(JOptionPane.showInputDialog(this, "Inserisci la quantità desiderata"));
            boolean notAlreadyHave = FR.getGestoreAcquisto().aggiungiAlCarrello(prod, quantity);
            
            //notAlreadyHave è una variabile usata per indicare se il prodotto selezionato è già presente nel carrello
            //('true' = non presente e quindi da aggiungere, 'false' = già presente)
            if(!notAlreadyHave)
                JOptionPane.showMessageDialog(this, "Prodotto già presente nel carrello");
            else
                JOptionPane.showMessageDialog(this, "Prodotto aggiunto al carrello");
            updateTable(tableCarrello, "tableCarrello");
        }catch(NotPositiveNumberException | NumberFormatException e){
            JOptionPane.showMessageDialog(this, "La quantità deve essere un numero intero positivo", "Warning", JOptionPane.WARNING_MESSAGE);
        }catch(GenericSystemException e){
            JOptionPane.showMessageDialog(this, "Prodotto non disponibile al momento", "Warning", JOptionPane.WARNING_MESSAGE);
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Quantità inserita superiore a disponibilità prodotto", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_buttonAggiungiAlCarrelloActionPerformed

    private void menuEsciMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuEsciMouseClicked
        int choice = multipleChoice("Sei sicuro di voler effettuare il logout?", new Object[] {"Sì", "No"});
        if(choice == 0){
            new LoginFrame().setVisible(true);
            close();
        }
    }//GEN-LAST:event_menuEsciMouseClicked

    private void tableCarrelloMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableCarrelloMouseClicked
        int selectedRowIndex = -1;
        
        selectedRowIndex = tableCarrello.getSelectedRow();
        if(selectedRowIndex != -1){
            buttonModificaQuantità.setEnabled(true);
            buttonRimuoviProdotto.setEnabled(true);
        }
    }//GEN-LAST:event_tableCarrelloMouseClicked

    private void buttonAcquistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAcquistaActionPerformed
        String cap, indirizzo, città, numCarta = "", cvv = "", metodoPagamento = "";
        boolean correctIns = false;
        int choice;
        
        choice = multipleChoice("Sei sicuro di voler proseguire con l'acquisto?", new Object[] {"Sì", "No"});
        if(choice == 0)
            try{
                //Inserimento dati per l'ordine
                Date dataEsecuzione = new Date();
                Date dataArrivo = addDaysToDate(dataEsecuzione, 7);
                
                indirizzo = JOptionPane.showInputDialog(this, "Inserisci l'indirizzo di spedizione:");
                città = JOptionPane.showInputDialog(this, "Inserisci la città:");
                cap = correctIntegerEntry("Inserisci il CAP:", 5);
                
                choice = multipleChoice("Scegli il metodo di pagamento:", new Object[] {"Carta di credito", "Gettoni"});
                if(choice == 0){
                    metodoPagamento = "Carta di credito";
                    numCarta = correctIntegerEntry("Inserisci il Numero di Carta (senza spazi):", 16);
                    cvv = correctIntegerEntry("Inserisci il CVV:", 3);
                    correctIns = true;
                }
                else if(choice == 1){
                    metodoPagamento = "Gettoni";
                    correctIns = true;
                }
                
                if(correctIns){
                    //Esecuzione acquisto ed aggiornamento
                    FR.buyAndUpdate(dataEsecuzione, dataArrivo, indirizzo, città, cap, metodoPagamento, numCarta, cvv);
                    
                    JOptionPane.showMessageDialog(this, "Acquisto effettuato");
                    DefaultTableModel model = (DefaultTableModel) tableCarrello.getModel();
                    model.setRowCount(0);
                    changeCard("panelLogo");
                }
            }catch(NumberFormatException e){
                JOptionPane.showMessageDialog(this, "Errore inserimento:\n"
                        + "CAP, numero carta e CVV devono essere numeri interi", "Warning", JOptionPane.WARNING_MESSAGE);
            }catch(GenericSystemException e){
                JOptionPane.showMessageDialog(this, "Gettoni insufficienti", "Warning", JOptionPane.WARNING_MESSAGE);
            }
    }//GEN-LAST:event_buttonAcquistaActionPerformed

    private void itemVisualizzaAcquistiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemVisualizzaAcquistiActionPerformed
        changeCard("panelVisualizzaAcquisti");
        
        textAreaVisualizzaAcquisti.setText(FR.visualizzaAcquisti());
    }//GEN-LAST:event_itemVisualizzaAcquistiActionPerformed

    private void buttonOrdinaNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonOrdinaNomeActionPerformed
        Collections.sort(FR.getGestoreProdotti().getCatalogo().getListaProdotti(), new Comparator<Prodotto>(){
            @Override
            public int compare(Prodotto prod1, Prodotto prod2){
                return prod1.getNome().compareTo(prod2.getNome());
            }
        });
        updateTable(tableProdotti, "tableProdotti");
    }//GEN-LAST:event_buttonOrdinaNomeActionPerformed

    private void buttonOrdinaCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonOrdinaCategoriaActionPerformed
        Collections.sort(FR.getGestoreProdotti().getCatalogo().getListaProdotti(), new Comparator<Prodotto>(){
            @Override
            public int compare(Prodotto prod1, Prodotto prod2){
                return prod1.getCategoria().compareTo(prod2.getCategoria());
            }
        });
        updateTable(tableProdotti, "tableProdotti");
    }//GEN-LAST:event_buttonOrdinaCategoriaActionPerformed

    private void buttonOrdinaPrezzoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonOrdinaPrezzoActionPerformed
        Collections.sort(FR.getGestoreProdotti().getCatalogo().getListaProdotti(), new Comparator<Prodotto>(){
            @Override
            public int compare(Prodotto prod1, Prodotto prod2){
                return Float.compare(prod1.getPrezzo(), prod2.getPrezzo());
            }
        });
        updateTable(tableProdotti, "tableProdotti");
    }//GEN-LAST:event_buttonOrdinaPrezzoActionPerformed

    private void buttonAnnullaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAnnullaActionPerformed
        DefaultTableModel model = (DefaultTableModel) tableCarrello.getModel();
        FR.destroyGestoreAcquisto();
        model.setRowCount(0);
        JOptionPane.showMessageDialog(this, "Acquisto annullato");
        changeCard("panelLogo");
    }//GEN-LAST:event_buttonAnnullaActionPerformed

    private void buttonRimuoviProdottoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRimuoviProdottoActionPerformed
        DefaultTableModel model = (DefaultTableModel) tableCarrello.getModel();
        int selectedRowIndex = tableCarrello.getSelectedRow();
        int idProd = Integer.parseInt(model.getValueAt(selectedRowIndex, 0).toString());
        
        //Rimozione prodotto
        FR.getGestoreAcquisto().rimuoviProdotto(idProd);
        JOptionPane.showMessageDialog(this, "Rimozione effettuata");
        
        menuCarrelloMouseClicked(null);
        updateTable(tableCarrello, "tableCarrello");
    }//GEN-LAST:event_buttonRimuoviProdottoActionPerformed

    private void buttonModificaQuantitàActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonModificaQuantitàActionPerformed
        try{
            DefaultTableModel model = (DefaultTableModel) tableCarrello.getModel();
            int selectedRowIndex = tableCarrello.getSelectedRow();
            int idProd = Integer.parseInt(model.getValueAt(selectedRowIndex, 0).toString());
            
            //Modifica quantità
            int quantity = Integer.parseInt(JOptionPane.showInputDialog(this, "Inserisci la nuova quantità desiderata"));
            FR.getGestoreAcquisto().modificaQuantità(idProd, quantity, FR.getGestoreProdotti().getCatalogo());
            
            //Aggiornamento dati
            menuCarrelloMouseClicked(null);
            updateTable(tableCarrello, "tableCarrello");
        }catch(NotPositiveNumberException | NumberFormatException e){
            JOptionPane.showMessageDialog(this, "La quantità deve essere un numero intero positivo", "Warning", JOptionPane.WARNING_MESSAGE);
        }catch(GenericSystemException e){
            JOptionPane.showMessageDialog(this, "Quantità inserita superiore a disponibilità prodotto", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_buttonModificaQuantitàActionPerformed

    private void menuOrdiniMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuOrdiniMouseClicked
        String emailClienteAttuale = FR.getClienteAttuale().getEmail();
        String stringa = "";
        
        changeCard("panelOrdini");
        if(!FR.getGestoreOrdini().getListaOrdiniCliente(emailClienteAttuale).isEmpty())
            buttonModificaOrdine.setEnabled(true);
        
        for(Ordine ordine : FR.getGestoreOrdini().getListaOrdiniCliente(emailClienteAttuale))
            stringa += ordine;
        textAreaOrdini.setText(stringa);
    }//GEN-LAST:event_menuOrdiniMouseClicked

    private void buttonModificaOrdineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonModificaOrdineActionPerformed
        String indirizzo, città, cap;
        String emailClienteAttuale = FR.getClienteAttuale().getEmail();
        Ordine ordine;
        int idOrdine;
        
        try{
            //Inserimento dati
            idOrdine = Integer.parseInt(JOptionPane.showInputDialog(this, "Inserisci numero dell'ordine da modificare:"));
            ordine = FR.getGestoreOrdini().getOrdine(emailClienteAttuale, idOrdine);
            
            indirizzo = JOptionPane.showInputDialog(this, "Inserisci l'indirizzo di spedizione:", ordine.getIndirizzo());
            città = JOptionPane.showInputDialog(this, "Inserisci la città:", ordine.getCittà());
            cap = correctIntegerEntry("Inserisci il CAP:", 5, ordine.getCap());
            
            //Esecuzione modifica ordine
            FR.getGestoreOrdini().modificaOrdine(idOrdine, emailClienteAttuale, indirizzo, città, cap);
            JOptionPane.showMessageDialog(this, "Modifica ordine n° " + idOrdine + " effettuata!");
            
            //Aggiornamento pannello
            menuOrdiniMouseClicked(null);
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(this, "Errore inserimento:\n"
                        + "Il numero dell'ordine inserito dev'essere un intero positivo", "Warning", JOptionPane.WARNING_MESSAGE);
        }catch(GenericSystemException e){
            JOptionPane.showMessageDialog(this, "Il numero d'ordine inserito è inesistente", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_buttonModificaOrdineActionPerformed

    private void itemDatiProfiloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemDatiProfiloActionPerformed
        Cliente c = FR.getClienteAttuale();
        
        changeCard("panelDatiProfilo");
        
        textFieldNome.setText(c.getNome());
        textFieldCognome.setText(c.getCognome());
        textFieldEmail.setText(c.getEmail());
        textFieldPassword.setText(c.getPassword());
        textFieldAltezza.setText(String.valueOf(c.getAltezza()));
        textFieldPeso.setText(String.valueOf(c.getPeso()));
        if(c.getObiettivo().equals("Definizione")){
            radioButtonDefinizione.setSelected(true);
            radioButtonAumento.setSelected(false);
        }
        else{
            radioButtonAumento.setSelected(true);
            radioButtonDefinizione.setSelected(false);
        }
        labelTotGettoni.setText(String.valueOf(c.getGettoni()));
    }//GEN-LAST:event_itemDatiProfiloActionPerformed

    private void radioButtonDefinizioneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioButtonDefinizioneActionPerformed
        if(radioButtonDefinizione.isSelected())
            radioButtonAumento.setSelected(false);
    }//GEN-LAST:event_radioButtonDefinizioneActionPerformed

    private void radioButtonAumentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioButtonAumentoActionPerformed
        if(radioButtonAumento.isSelected())
            radioButtonDefinizione.setSelected(false);
    }//GEN-LAST:event_radioButtonAumentoActionPerformed

    private void buttonModificaDatiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonModificaDatiActionPerformed
        String nome, cognome, email, password, altezza, peso, obiettivo = "";
        
        try{
            //Inserimento dati
            nome = textFieldNome.getText();
            cognome = textFieldCognome.getText();
            email = textFieldEmail.getText().toLowerCase();
            password = textFieldPassword.getText();
            altezza = textFieldAltezza.getText();
            peso = textFieldPeso.getText();
            if(radioButtonDefinizione.isSelected())
                obiettivo = "Definizione";
            else if(radioButtonAumento.isSelected())
                obiettivo = "Aumento";
            
            //Esecuzione modifica
            FR.modificaDati(nome, cognome, email, password, altezza, peso, obiettivo);
            JOptionPane.showMessageDialog(this, "Modifica dati effettuata");
            
            //Aggiornamento pannello
            itemDatiProfiloActionPerformed(null);
        }catch(ExistingEmailException | EmptyFieldException e){
            JOptionPane.showMessageDialog(this, e, "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_buttonModificaDatiActionPerformed

    private void itemVisualizzaSchedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemVisualizzaSchedaActionPerformed
        String emailClienteAttuale = FR.getClienteAttuale().getEmail();
        Scheda schedaCliente = FR.getGestoreSchede().getSchedaCliente(emailClienteAttuale);
        
        changeCard("panelVisualizzaScheda");
        
        labelNomeScheda.setText(schedaCliente.getNome());
        textAreaDieta.setText(schedaCliente.getDieta().toString());
        textAreaEsercizi.setText(schedaCliente.getPianoEsercizi().toString());
    }//GEN-LAST:event_itemVisualizzaSchedaActionPerformed

    private void buttonEseguiEserciziActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEseguiEserciziActionPerformed
        TimerFrame TF = new TimerFrame();
        
        TF.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosed(WindowEvent e){
                itemVisualizzaSchedaActionPerformed(null);
            }
        });
        
        TF.setVisible(true);
    }//GEN-LAST:event_buttonEseguiEserciziActionPerformed

    private void menuPrenotazioniMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuPrenotazioniMouseClicked
        changeCard("panelPrenotazioni");
        updateTable(tablePrenotazioni, "tablePrenotazioni");
    }//GEN-LAST:event_menuPrenotazioniMouseClicked

    private void buttonPrenotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPrenotaActionPerformed
        String cap, indirizzo, città, numCarta = "", cvv = "", metodoPagamento = "";
        boolean correctIns = false;
        int choice, quantità;
        
        try{
            DefaultTableModel model = (DefaultTableModel) tableProdotti.getModel();
            int selectedRowIndex = tableProdotti.getSelectedRow();
            int idProd = Integer.parseInt(model.getValueAt(selectedRowIndex, 0).toString());
            Prodotto prod = FR.getGestoreProdotti().getCatalogo().getProdotto(idProd);
            
            quantità = Integer.parseInt(JOptionPane.showInputDialog(this, "Inserisci la quantità da prenotare:"));
            indirizzo = JOptionPane.showInputDialog(this, "Inserisci l'indirizzo di spedizione:");
            città = JOptionPane.showInputDialog(this, "Inserisci la città:");
            cap = correctIntegerEntry("Inserisci il CAP:", 5);
            
            choice = multipleChoice("Scegli il metodo di pagamento:", new Object[] {"Carta di credito", "Gettoni"});
            if(choice == 0){
                metodoPagamento = "Carta di credito";
                numCarta = correctIntegerEntry("Inserisci il Numero di Carta (senza spazi):", 16);
                cvv = correctIntegerEntry("Inserisci il CVV:", 3);
                correctIns = true;
            }
            else if(choice == 1){
                metodoPagamento = "Gettoni";
                correctIns = true;
            }
            
            if(correctIns){
                FR.getGestorePrenotazioni().effettuaPrenotazione(FR.getClienteAttuale(), new Date(), prod, quantità, indirizzo, città, cap, metodoPagamento, numCarta, cvv);
                JOptionPane.showMessageDialog(this, "Prenotazione Effettuata!");
            }
        }catch(NotPositiveNumberException | NumberFormatException e){
            JOptionPane.showMessageDialog(this, "La quantità deve essere un numero intero positivo", "Warning", JOptionPane.WARNING_MESSAGE);
        }catch(GenericSystemException e){
            JOptionPane.showMessageDialog(this, "Gettoni insufficienti", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_buttonPrenotaActionPerformed

    private void tablePrenotazioniMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablePrenotazioniMouseClicked
        int selectedRowIndex = -1;
        
        selectedRowIndex = tablePrenotazioni.getSelectedRow();
        if(selectedRowIndex != -1){
            buttonDettagliPrenotazione.setEnabled(true);
            buttonModificaPrenotazione.setEnabled(true);
            buttonEliminaPrenotazione.setEnabled(true);
        }
    }//GEN-LAST:event_tablePrenotazioniMouseClicked

    private void buttonDettagliPrenotazioneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDettagliPrenotazioneActionPerformed
        DefaultTableModel model = (DefaultTableModel) tablePrenotazioni.getModel();
        int selectedRowIndex = tablePrenotazioni.getSelectedRow();
        int idPrenotazione = Integer.parseInt(model.getValueAt(selectedRowIndex, 0).toString());
        Prenotazione prenotazione = FR.getGestorePrenotazioni().getPrenotazione(FR.getClienteAttuale().getEmail(), idPrenotazione);
        
        textAreaDettagliPrenotazione.setText(prenotazione.toString());
    }//GEN-LAST:event_buttonDettagliPrenotazioneActionPerformed

    private void buttonModificaPrenotazioneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonModificaPrenotazioneActionPerformed
        String cap, indirizzo, città, numCarta = "", cvv = "", metodoPagamento = "";
        Prenotazione prenotazione, newPrenotazione;
        boolean correctIns = false;
        int choice, quantità;
        
        choice = multipleChoice("Sei sicuro di voler proseguire con la modifica?", new Object[] {"Sì", "No"});
        if(choice == 0)
            try{
                DefaultTableModel model = (DefaultTableModel) tablePrenotazioni.getModel();
                int selectedRowIndex = tablePrenotazioni.getSelectedRow();
                int idPrenotazione = Integer.parseInt(model.getValueAt(selectedRowIndex, 0).toString());
                prenotazione = FR.getGestorePrenotazioni().getPrenotazione(FR.getClienteAttuale().getEmail(), idPrenotazione);
                
                //Inserimento dati con valore di default
                quantità = Integer.parseInt(JOptionPane.showInputDialog(this, "Inserisci la quantità da prenotare:", prenotazione.getQuantità()));
                indirizzo = JOptionPane.showInputDialog(this, "Inserisci l'indirizzo di spedizione:", prenotazione.getIndirizzo());
                città = JOptionPane.showInputDialog(this, "Inserisci la città:", prenotazione.getCittà());
                cap = correctIntegerEntry("Inserisci il CAP:", 5, prenotazione.getCap());
                
                choice = multipleChoice("Scegli il metodo di pagamento:", new Object[] {"Carta di credito", "Gettoni"});
                if(choice == 0){
                    metodoPagamento = "Carta di credito";
                    numCarta = correctIntegerEntry("Inserisci il Numero di Carta (senza spazi):", 16, prenotazione.getNumCarta());
                    cvv = correctIntegerEntry("Inserisci il CVV:", 3, prenotazione.getCvv());
                    correctIns = true;
                }
                else if(choice == 1){
                    metodoPagamento = "Gettoni";
                    correctIns = true;
                }
                
                //Esecuzione modifica
                if(correctIns){
                    newPrenotazione = FR.getGestorePrenotazioni().modificaPrenotazione(prenotazione, FR.getClienteAttuale(), quantità, indirizzo, città, cap, metodoPagamento, numCarta, cvv);
                    JOptionPane.showMessageDialog(this, "Modifica Effettuata!");
                    textAreaDettagliPrenotazione.setText(newPrenotazione.toString());
                }
            }catch(NotPositiveNumberException | NumberFormatException e){
                JOptionPane.showMessageDialog(this, "La quantità deve essere un numero intero positivo", "Warning", JOptionPane.WARNING_MESSAGE);
            }catch(GenericSystemException e){
                JOptionPane.showMessageDialog(this, "Gettoni insufficienti", "Warning", JOptionPane.WARNING_MESSAGE);
            }
    }//GEN-LAST:event_buttonModificaPrenotazioneActionPerformed

    private void buttonEliminaPrenotazioneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEliminaPrenotazioneActionPerformed
        int choice;
        
        DefaultTableModel model = (DefaultTableModel) tablePrenotazioni.getModel();
        int selectedRowIndex = tablePrenotazioni.getSelectedRow();
        int idPrenotazione = Integer.parseInt(model.getValueAt(selectedRowIndex, 0).toString());
        
        choice = multipleChoice("Sei sicuro di voler eliminare la prenotazione?", new Object[] {"Sì", "No"});
        if(choice == 0){
            FR.getGestorePrenotazioni().eliminaPrenotazione(FR.getClienteAttuale(), idPrenotazione);
            JOptionPane.showMessageDialog(this, "Eliminazione Effettuata!");
            updateTable(tablePrenotazioni, "tablePrenotazioni");
            textAreaDettagliPrenotazione.setText("");
        }
    }//GEN-LAST:event_buttonEliminaPrenotazioneActionPerformed

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
            java.util.logging.Logger.getLogger(GUIClienteFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUIClienteFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUIClienteFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUIClienteFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUIClienteFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAcquista;
    private javax.swing.JButton buttonAggiungiAlCarrello;
    private javax.swing.JButton buttonAnnulla;
    private javax.swing.JButton buttonDettagliPrenotazione;
    private javax.swing.JButton buttonEliminaPrenotazione;
    private javax.swing.JButton buttonEseguiEsercizi;
    private javax.swing.JButton buttonModificaDati;
    private javax.swing.JButton buttonModificaOrdine;
    private javax.swing.JButton buttonModificaPrenotazione;
    private javax.swing.JButton buttonModificaQuantità;
    private javax.swing.JButton buttonOrdinaCategoria;
    private javax.swing.JButton buttonOrdinaNome;
    private javax.swing.JButton buttonOrdinaPrezzo;
    private javax.swing.JButton buttonPrenota;
    private javax.swing.JButton buttonRimuoviProdotto;
    private javax.swing.JMenuItem itemAcquistaProdotto;
    private javax.swing.JMenuItem itemDatiProfilo;
    private javax.swing.JMenuItem itemVisualizzaAcquisti;
    private javax.swing.JMenuItem itemVisualizzaScheda;
    private javax.swing.JLabel labelAltezza;
    private javax.swing.JLabel labelAvvisoAggiungi1;
    private javax.swing.JLabel labelAvvisoAggiungi2;
    private javax.swing.JLabel labelAvvisoCarrello1;
    private javax.swing.JLabel labelAvvisoCarrello2;
    private javax.swing.JLabel labelAvvisoCarrello3;
    private javax.swing.JLabel labelAvvisoCarrello4;
    private javax.swing.JLabel labelAvvisoCarrello5;
    private javax.swing.JLabel labelAvvisoDatiProfilo1;
    private javax.swing.JLabel labelAvvisoPrenota1;
    private javax.swing.JLabel labelAvvisoPrenota2;
    private javax.swing.JLabel labelAvvisoPrenotazione1;
    private javax.swing.JLabel labelAvvisoPrenotazione2;
    private javax.swing.JLabel labelAvvisoPrenotazione3;
    private javax.swing.JLabel labelAvvisoPrenotazione4;
    private javax.swing.JLabel labelCarrello;
    private javax.swing.JLabel labelChili;
    private javax.swing.JLabel labelCognome;
    private javax.swing.JLabel labelDatiProfilo;
    private javax.swing.JLabel labelDettagliPrenotazione;
    private javax.swing.JLabel labelDieta;
    private javax.swing.JLabel labelEmail;
    private javax.swing.JLabel labelEsercizi;
    private javax.swing.JLabel labelEuro;
    private javax.swing.JLabel labelGettoni;
    private javax.swing.JLabel labelImmagineProfilo;
    private javax.swing.JLabel labelImportoSpesa;
    private javax.swing.JLabel labelImportoSpesaGettoni;
    private javax.swing.JLabel labelLogo;
    private javax.swing.JLabel labelMetri;
    private javax.swing.JLabel labelNome;
    private javax.swing.JLabel labelNomeScheda;
    private javax.swing.JLabel labelObiettivo;
    private javax.swing.JLabel labelOrdini;
    private javax.swing.JLabel labelPassword;
    private javax.swing.JLabel labelPeso;
    private javax.swing.JLabel labelPrenotazioni;
    private javax.swing.JLabel labelProdotti;
    private javax.swing.JLabel labelTotGettoni;
    private javax.swing.JLabel labelTotaleCarrello;
    private javax.swing.JLabel labelTotaleGettoni;
    private javax.swing.JLabel labelVisualizzaAcquisti;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenu menuAcquisti;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu menuCarrello;
    private javax.swing.JMenu menuEsci;
    private javax.swing.JMenu menuHome;
    private javax.swing.JMenu menuOrdini;
    private javax.swing.JMenu menuPrenotazioni;
    private javax.swing.JMenu menuProfilo;
    private javax.swing.JPanel panelAcquista;
    private javax.swing.JPanel panelCarrello;
    private javax.swing.JPanel panelDatiProfilo;
    private javax.swing.JPanel panelLogo;
    private javax.swing.JPanel panelOrdini;
    private javax.swing.JPanel panelPrenotazioni;
    private javax.swing.JPanel panelVisualizzaAcquisti;
    private javax.swing.JPanel panelVisualizzaScheda;
    private javax.swing.JRadioButton radioButtonAumento;
    private javax.swing.JRadioButton radioButtonDefinizione;
    private javax.swing.JScrollPane scrollPaneDettagliPrenotazione;
    private javax.swing.JScrollPane scrollPaneDieta;
    private javax.swing.JScrollPane scrollPaneEsercizi;
    private javax.swing.JScrollPane scrollPaneOrdini;
    private javax.swing.JScrollPane scrollPaneTabellaPrenotazioni;
    private javax.swing.JScrollPane scrollPaneTabellaProdotti;
    private javax.swing.JScrollPane scrollPaneVisualizzaAcquisti;
    private javax.swing.JScrollPane scrollPanelTabellaCarrello;
    private javax.swing.JTable tableCarrello;
    private javax.swing.JTable tablePrenotazioni;
    private javax.swing.JTable tableProdotti;
    private javax.swing.JTextArea textAreaDettagliPrenotazione;
    private javax.swing.JTextArea textAreaDieta;
    private javax.swing.JTextArea textAreaEsercizi;
    private javax.swing.JTextArea textAreaOrdini;
    private javax.swing.JTextArea textAreaVisualizzaAcquisti;
    private javax.swing.JTextField textFieldAltezza;
    private javax.swing.JTextField textFieldCognome;
    private javax.swing.JTextField textFieldEmail;
    private javax.swing.JTextField textFieldNome;
    private javax.swing.JTextField textFieldPassword;
    private javax.swing.JTextField textFieldPeso;
    // End of variables declaration//GEN-END:variables
}
