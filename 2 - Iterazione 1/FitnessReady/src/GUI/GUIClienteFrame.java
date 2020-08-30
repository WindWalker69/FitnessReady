package GUI;

import Dominio.*;
import Eccezioni.*;
import java.awt.CardLayout;
import java.awt.Toolkit;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class GUIClienteFrame extends javax.swing.JFrame {

    private FitnessReady FR;
    /**
     * Creates new form GUIFrame
     */
    public GUIClienteFrame() {
        FR = FitnessReady.getInstance();
        initComponents();
    }
    
    private void close(){
        WindowEvent winClosingEvent = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);
    }
    
    private void changeCard(String nomePannello){
        CardLayout card = (CardLayout)mainPanel.getLayout();
        card.show(mainPanel, nomePannello);
        buttonAggiungiAlCarrello.setEnabled(false);
        buttonModificaQuantità.setEnabled(false);
        buttonRimuoviProdotto.setEnabled(false);
        buttonAnnulla.setEnabled(false);
    }
    
    private void updateTable(JTable table, String nomeJTable){
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); //toglie tutte le eventuali righe già presenti in modo da poter aggiornare la tabella senza duplicati
        Object rowData[] = new Object[table.getColumnCount()];
        int i;
        
        switch(nomeJTable){
            case "tableProdotti":
                List<Prodotto> listProd = FR.getCatalogo().getListaProdotti();
                Prodotto prod;
                for(i = 0; i < listProd.size(); i++){
                    prod = listProd.get(i);
                    rowData[0] = prod.getId();
                    rowData[1] = prod.getNome();
                    rowData[2] = prod.getCategoria();
                    rowData[3] = String.format("%.2f", prod.getPrezzo());
                    rowData[4] = prod.getDisp();
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
                    rowData[4] = riga.getQuantità();
                    rowData[5] = String.format("%.2f", riga.getSubTotale());
                    model.addRow(rowData);
                }
                break;
        }
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
        scrollPaneTabellaProdotti = new javax.swing.JScrollPane();
        tableProdotti = new javax.swing.JTable();
        buttonAggiungiAlCarrello = new javax.swing.JButton();
        buttonOrdinaNome = new javax.swing.JButton();
        buttonOrdinaCategoria = new javax.swing.JButton();
        buttonOrdinaPrezzo = new javax.swing.JButton();
        panelCarrello = new javax.swing.JPanel();
        labelCarrello = new javax.swing.JLabel();
        labelImportoSpesa = new javax.swing.JLabel();
        labelTotaleCarrello = new javax.swing.JLabel();
        labelEuro = new javax.swing.JLabel();
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
        menuBar = new javax.swing.JMenuBar();
        menuHome = new javax.swing.JMenu();
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

        tableProdotti.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nome", "Categoria", "Prezzo", "Disponibilità"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
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
            .addGroup(panelAcquistaLayout.createSequentialGroup()
                .addGroup(panelAcquistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelAcquistaLayout.createSequentialGroup()
                        .addGap(350, 350, 350)
                        .addComponent(buttonAggiungiAlCarrello))
                    .addGroup(panelAcquistaLayout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(scrollPaneTabellaProdotti, javax.swing.GroupLayout.PREFERRED_SIZE, 751, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelAcquistaLayout.createSequentialGroup()
                        .addGap(169, 169, 169)
                        .addComponent(buttonOrdinaNome)
                        .addGap(57, 57, 57)
                        .addGroup(panelAcquistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(labelProdotti)
                            .addComponent(buttonOrdinaCategoria))
                        .addGap(66, 66, 66)
                        .addComponent(buttonOrdinaPrezzo)))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        panelAcquistaLayout.setVerticalGroup(
            panelAcquistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAcquistaLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(labelProdotti)
                .addGap(34, 34, 34)
                .addGroup(panelAcquistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonOrdinaCategoria)
                    .addComponent(buttonOrdinaPrezzo)
                    .addComponent(buttonOrdinaNome))
                .addGap(30, 30, 30)
                .addComponent(scrollPaneTabellaProdotti, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addComponent(buttonAggiungiAlCarrello)
                .addGap(60, 60, 60))
        );

        mainPanel.add(panelAcquista, "panelAcquista");

        labelCarrello.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        labelCarrello.setText("Riepilogo Prodotti Carrello");

        labelImportoSpesa.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        labelImportoSpesa.setText("Totale da pagare:");

        labelTotaleCarrello.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        labelTotaleCarrello.setText("0");

        labelEuro.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        labelEuro.setText("€");

        tableCarrello.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nome", "Categoria", "Prezzo", "Quantità", "Sub Totale"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
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
            .addGroup(panelCarrelloLayout.createSequentialGroup()
                .addGroup(panelCarrelloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelCarrelloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelCarrelloLayout.createSequentialGroup()
                            .addGap(291, 291, 291)
                            .addComponent(labelCarrello))
                        .addGroup(panelCarrelloLayout.createSequentialGroup()
                            .addGap(45, 45, 45)
                            .addComponent(scrollPanelTabellaCarrello, javax.swing.GroupLayout.PREFERRED_SIZE, 751, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelCarrelloLayout.createSequentialGroup()
                        .addGroup(panelCarrelloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelCarrelloLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(labelImportoSpesa))
                            .addGroup(panelCarrelloLayout.createSequentialGroup()
                                .addGap(107, 107, 107)
                                .addComponent(buttonModificaQuantità)
                                .addGap(146, 146, 146)
                                .addComponent(buttonRimuoviProdotto)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(panelCarrelloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(buttonAnnulla, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(buttonAcquista, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(18, 18, 18)
                        .addComponent(labelTotaleCarrello)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelEuro)))
                .addContainerGap(51, Short.MAX_VALUE))
        );
        panelCarrelloLayout.setVerticalGroup(
            panelCarrelloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCarrelloLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(labelCarrello)
                .addGap(37, 37, 37)
                .addComponent(scrollPanelTabellaCarrello, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelCarrelloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelImportoSpesa)
                    .addComponent(labelTotaleCarrello)
                    .addComponent(labelEuro))
                .addGap(18, 18, 18)
                .addGroup(panelCarrelloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonAcquista)
                    .addComponent(buttonRimuoviProdotto)
                    .addComponent(buttonModificaQuantità))
                .addGap(33, 33, 33)
                .addComponent(buttonAnnulla)
                .addContainerGap(46, Short.MAX_VALUE))
        );

        mainPanel.add(panelCarrello, "panelCarrello");

        labelVisualizzaAcquisti.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        labelVisualizzaAcquisti.setText("Acquisti Effettuati");

        textAreaVisualizzaAcquisti.setColumns(20);
        textAreaVisualizzaAcquisti.setRows(5);
        scrollPaneVisualizzaAcquisti.setViewportView(textAreaVisualizzaAcquisti);

        javax.swing.GroupLayout panelVisualizzaAcquistiLayout = new javax.swing.GroupLayout(panelVisualizzaAcquisti);
        panelVisualizzaAcquisti.setLayout(panelVisualizzaAcquistiLayout);
        panelVisualizzaAcquistiLayout.setHorizontalGroup(
            panelVisualizzaAcquistiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelVisualizzaAcquistiLayout.createSequentialGroup()
                .addGroup(panelVisualizzaAcquistiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelVisualizzaAcquistiLayout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(scrollPaneVisualizzaAcquisti, javax.swing.GroupLayout.PREFERRED_SIZE, 751, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelVisualizzaAcquistiLayout.createSequentialGroup()
                        .addGap(332, 332, 332)
                        .addComponent(labelVisualizzaAcquisti)))
                .addContainerGap(52, Short.MAX_VALUE))
        );
        panelVisualizzaAcquistiLayout.setVerticalGroup(
            panelVisualizzaAcquistiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelVisualizzaAcquistiLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(labelVisualizzaAcquisti)
                .addGap(30, 30, 30)
                .addComponent(scrollPaneVisualizzaAcquisti, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(129, Short.MAX_VALUE))
        );

        mainPanel.add(panelVisualizzaAcquisti, "panelVisualizzaAcquisti");

        menuHome.setText("Home");
        menuHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuHomeMouseClicked(evt);
            }
        });
        menuBar.add(menuHome);

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
        if(!FR.getGestoreAcquisto().getCarrello().getRigheCarrello().isEmpty())
            buttonAnnulla.setEnabled(true);
    }//GEN-LAST:event_menuCarrelloMouseClicked

    private void tableProdottiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableProdottiMouseClicked
        buttonAggiungiAlCarrello.setEnabled(true);
    }//GEN-LAST:event_tableProdottiMouseClicked

    private void buttonAggiungiAlCarrelloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAggiungiAlCarrelloActionPerformed
        try{
            DefaultTableModel model = (DefaultTableModel) tableProdotti.getModel();
            int selectedRowIndex = tableProdotti.getSelectedRow();
            int idProd = Integer.parseInt(model.getValueAt(selectedRowIndex, 0).toString());
            Prodotto prod = FR.getCatalogo().getProdotto(idProd);
        
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
            JOptionPane.showMessageDialog(this, "La quantità deve essere un numero intero positivo");
        }catch(GenericSystemException e){
            JOptionPane.showMessageDialog(this, "Prodotto non disponibile al momento");
        }catch(Exception e){
            System.out.println(e);
            JOptionPane.showMessageDialog(this, "Quantità inserita superiore a disponibilità prodotto");
        }
    }//GEN-LAST:event_buttonAggiungiAlCarrelloActionPerformed

    private void menuEsciMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuEsciMouseClicked
        Object[] options = {"Sì", "No"};
        
        int n = JOptionPane.showOptionDialog(this, "Sei sicuro di voler uscire", "Question",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
        if(n == 0)
            close();
    }//GEN-LAST:event_menuEsciMouseClicked

    private void tableCarrelloMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableCarrelloMouseClicked
        buttonModificaQuantità.setEnabled(true);
        buttonRimuoviProdotto.setEnabled(true);
    }//GEN-LAST:event_tableCarrelloMouseClicked

    private void buttonAcquistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAcquistaActionPerformed
        try{
            DefaultTableModel model = (DefaultTableModel) tableCarrello.getModel();
            FR.aggiungiAcquisto();
            JOptionPane.showMessageDialog(this, "Acquisto effettuato");
            FR.destroyGestoreAcquisto();
            model.setRowCount(0);
            changeCard("panelLogo");
        }catch(GenericSystemException e){
            JOptionPane.showMessageDialog(this, "Carrello vuoto");
        }
    }//GEN-LAST:event_buttonAcquistaActionPerformed

    private void itemVisualizzaAcquistiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemVisualizzaAcquistiActionPerformed
        changeCard("panelVisualizzaAcquisti");
        textAreaVisualizzaAcquisti.setText("");
        String stringa = "";
        
        for(Acquisto acq : FR.getListaAcquistiCliente()){
            stringa += acq;
        }
        textAreaVisualizzaAcquisti.setText(stringa);
    }//GEN-LAST:event_itemVisualizzaAcquistiActionPerformed

    private void buttonOrdinaNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonOrdinaNomeActionPerformed
        Collections.sort(FR.getCatalogo().getListaProdotti(), new Comparator<Prodotto>(){
            @Override
            public int compare(Prodotto prod1, Prodotto prod2){
                return prod1.getNome().compareTo(prod2.getNome());
            }
        });
        updateTable(tableProdotti, "tableProdotti");
    }//GEN-LAST:event_buttonOrdinaNomeActionPerformed

    private void buttonOrdinaCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonOrdinaCategoriaActionPerformed
        Collections.sort(FR.getCatalogo().getListaProdotti(), new Comparator<Prodotto>(){
            @Override
            public int compare(Prodotto prod1, Prodotto prod2){
                return prod1.getCategoria().compareTo(prod2.getCategoria());
            }
        });
        updateTable(tableProdotti, "tableProdotti");
    }//GEN-LAST:event_buttonOrdinaCategoriaActionPerformed

    private void buttonOrdinaPrezzoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonOrdinaPrezzoActionPerformed
        Collections.sort(FR.getCatalogo().getListaProdotti(), new Comparator<Prodotto>(){
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
        
        FR.getGestoreAcquisto().rimuoviProdotto(idProd);
        JOptionPane.showMessageDialog(this, "Rimozione effettuata");
        labelTotaleCarrello.setText(String.format("%.2f", FR.getGestoreAcquisto().getCarrello().getTotaleCarrello()));
        updateTable(tableCarrello, "tableCarrello");
    }//GEN-LAST:event_buttonRimuoviProdottoActionPerformed

    private void buttonModificaQuantitàActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonModificaQuantitàActionPerformed
        try{
            DefaultTableModel model = (DefaultTableModel) tableCarrello.getModel();
            int selectedRowIndex = tableCarrello.getSelectedRow();
            int idProd = Integer.parseInt(model.getValueAt(selectedRowIndex, 0).toString());
            
            int quantity = Integer.parseInt(JOptionPane.showInputDialog(this, "Inserisci la nuova quantità desiderata"));
            FR.getGestoreAcquisto().modificaQuantità(idProd, quantity, FR.getCatalogo());
            labelTotaleCarrello.setText(String.format("%.2f", FR.getGestoreAcquisto().getCarrello().getTotaleCarrello()));
            updateTable(tableCarrello, "tableCarrello");
        }catch(NotPositiveNumberException | NumberFormatException e){
            JOptionPane.showMessageDialog(this, "La quantità deve essere un numero intero positivo");
        }catch(GenericSystemException e){
            JOptionPane.showMessageDialog(this, "Quantità inserita superiore a disponibilità prodotto");
        }
    }//GEN-LAST:event_buttonModificaQuantitàActionPerformed

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
    private javax.swing.JButton buttonModificaQuantità;
    private javax.swing.JButton buttonOrdinaCategoria;
    private javax.swing.JButton buttonOrdinaNome;
    private javax.swing.JButton buttonOrdinaPrezzo;
    private javax.swing.JButton buttonRimuoviProdotto;
    private javax.swing.JMenuItem itemAcquistaProdotto;
    private javax.swing.JMenuItem itemVisualizzaAcquisti;
    private javax.swing.JLabel labelCarrello;
    private javax.swing.JLabel labelEuro;
    private javax.swing.JLabel labelImportoSpesa;
    private javax.swing.JLabel labelLogo;
    private javax.swing.JLabel labelProdotti;
    private javax.swing.JLabel labelTotaleCarrello;
    private javax.swing.JLabel labelVisualizzaAcquisti;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenu menuAcquisti;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu menuCarrello;
    private javax.swing.JMenu menuEsci;
    private javax.swing.JMenu menuHome;
    private javax.swing.JPanel panelAcquista;
    private javax.swing.JPanel panelCarrello;
    private javax.swing.JPanel panelLogo;
    private javax.swing.JPanel panelVisualizzaAcquisti;
    private javax.swing.JScrollPane scrollPaneTabellaProdotti;
    private javax.swing.JScrollPane scrollPaneVisualizzaAcquisti;
    private javax.swing.JScrollPane scrollPanelTabellaCarrello;
    private javax.swing.JTable tableCarrello;
    private javax.swing.JTable tableProdotti;
    private javax.swing.JTextArea textAreaVisualizzaAcquisti;
    // End of variables declaration//GEN-END:variables
}