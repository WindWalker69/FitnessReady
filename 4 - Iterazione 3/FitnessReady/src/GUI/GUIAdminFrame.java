package GUI;

import Dominio.*;
import Dominio.Pasti.*;
import Dominio.Esercizi.*;
import Eccezioni.*;

import java.util.*;
import java.awt.CardLayout;
import java.text.SimpleDateFormat;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class GUIAdminFrame extends javax.swing.JFrame {

    private final FitnessReady FR;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    /**
     * Creates new form GUIFrame
     */
    public GUIAdminFrame() {
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
        buttonModificaProdotto.setEnabled(false);
        buttonEliminaProdotto.setEnabled(false);
        buttonDettagliScheda.setEnabled(false);
        buttonModificaScheda.setEnabled(false);
        buttonEliminaScheda.setEnabled(false);
        buttonModificaDatiPromozione.setEnabled(false);
        buttonCambiaStatoPromozione.setEnabled(false);
        buttonEliminaPromozione.setEnabled(false);
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
                
            case "tableSchede":
                List<Scheda> listSchede = FR.getGestoreSchede().getListaSchede();
                Scheda scheda;
                for(i = 0; i < listSchede.size(); i++){
                    scheda = listSchede.get(i);
                    rowData[0] = scheda.getId();
                    rowData[1] = scheda.getNome();
                    rowData[2] = String.format("%.2f", scheda.getAltMin());
                    rowData[3] = String.format("%.2f", scheda.getAltMax());
                    rowData[4] = String.format("%.2f", scheda.getPMin());
                    rowData[5] = String.format("%.2f", scheda.getPMax());
                    rowData[6] = scheda.getObiettivo();
                    model.addRow(rowData);
                }
                break;
            
            case "tablePromozioni":
                List<Promozione> listPromo = FR.getGestorePromozioni().getListaPromozioni();
                Promozione promo;
                for(i = 0; i < listPromo.size(); i++){
                    promo = listPromo.get(i);
                    rowData[0] = promo.getId();
                    rowData[1] = promo.getNome();
                    rowData[2] = promo.getCTarget();
                    rowData[3] = String.format("%.2f", promo.getPercSconto()) + "%";
                    rowData[4] = dateFormat.format(promo.getDataCreazione());
                    rowData[5] = promo.getStato();
                    model.addRow(rowData);
                }
                break;
        }
    }
    
    private int multipleChoice(String question, Object[] options){
        int n = options.length;
        return JOptionPane.showOptionDialog(this, question, "Question", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, options, options[n - 1]);
    }
    
    private Dieta createDiet(){
        String nomePortata;
        int i = 0, choice;
        float grammi;
        List<Portata> elencoPortateColazione = new ArrayList<>();
        List<Portata> elencoPortatePranzo = new ArrayList<>();
        List<Portata> elencoPortateCena = new ArrayList<>();
        List<Pasto> listaPasti = new ArrayList<>();
        Portata portata;
        Dieta dieta;
        
        do{
            switch(i){
                case 0: //Colazione
                    do{
                        nomePortata = JOptionPane.showInputDialog(this, "Inserisci il nome della portata per la COLAZIONE:");
                        grammi = Float.parseFloat(JOptionPane.showInputDialog(this, "Inserisci la quantità in grammi della portata:"));
                        portata = new Portata(nomePortata, grammi);
                        elencoPortateColazione.add(portata);
                        choice = multipleChoice("Vuoi inserire un'altra portata per la COLAZIONE?", new Object[] {"Sì", "No"});
                    }while(choice == 0);
                    
                    listaPasti.add(new Colazione(elencoPortateColazione));
                    i++;
                    break;
                
                case 1: //Pranzo
                    do{
                        nomePortata = JOptionPane.showInputDialog(this, "Inserisci il nome della portata per il PRANZO:");
                        grammi = Float.parseFloat(JOptionPane.showInputDialog(this, "Inserisci la quantità in grammi della portata:"));
                        portata = new Portata(nomePortata, grammi);
                        elencoPortatePranzo.add(portata);
                        choice = multipleChoice("Vuoi inserire un'altra portata per il PRANZO?", new Object[] {"Sì", "No"});
                    }while(choice == 0);
                    
                    listaPasti.add(new Pranzo(elencoPortatePranzo));
                    i++;
                    break;
                
                case 2: //Cena
                    do{
                        nomePortata = JOptionPane.showInputDialog(this, "Inserisci il nome della portata per la CENA:");
                        grammi = Float.parseFloat(JOptionPane.showInputDialog(this, "Inserisci la quantità in grammi della portata:"));
                        portata = new Portata(nomePortata, grammi);
                        elencoPortateCena.add(portata);
                        choice = multipleChoice("Vuoi inserire un'altra portata per la CENA?", new Object[] {"Sì", "No"});
                    }while(choice == 0);
                    
                    listaPasti.add(new Cena(elencoPortateCena));
                    i++;
                    break;
            }
        }while(i != 3);
        
        dieta = new Dieta(listaPasti);
        
        return dieta;
    }
    
    private PianoEsercizi createExercisePlan(){
        Object[] categorie = {"CARDIO", "GAMBE", "BRACCIA", "TORACE", "SPALLE", "DORSALI", "ADDOME"};
        String nomeEsercizio, categoria;
        int numSerie = 0, numRipetizioni = 0, durata, choice;
        List<Esercizio> listaEsercizi = new ArrayList<>();
        PianoEsercizi pianoEsercizi;
        Esercizio esercizio = null;
        
        do{
            categoria = (String)JOptionPane.showInputDialog(this, "Seleziona la categoria dell'esercizio da inserire:",
                    "Categoria", JOptionPane.PLAIN_MESSAGE, null, categorie, categorie[0]);
            nomeEsercizio = JOptionPane.showInputDialog(this, "Inserisci il nome dell'esercizio:");
            if(!categoria.equals("CARDIO")){
                numSerie = Integer.parseInt(JOptionPane.showInputDialog(this, "Inserisci il numero di serie da eseguire:"));
                numRipetizioni = Integer.parseInt(JOptionPane.showInputDialog(this, "Inserisci il numero di ripetizioni per serie da eseguire:"));
            }
            switch(categoria){
                case "CARDIO":
                    durata = Integer.parseInt(JOptionPane.showInputDialog(this, "Inserisci la durata dell'esericizio:"));
                    esercizio = new EsercizioCardio(nomeEsercizio, durata);
                    break;
                
                case "GAMBE":
                    esercizio = new EsercizioGambe(nomeEsercizio, numSerie, numRipetizioni);
                    break;
                
                case "BRACCIA":
                    esercizio = new EsercizioBraccia(nomeEsercizio, numSerie, numRipetizioni);
                    break;
                
                case "TORACE":
                    esercizio = new EsercizioTorace(nomeEsercizio, numSerie, numRipetizioni);
                    break;
                
                case "SPALLE":
                    esercizio = new EsercizioSpalle(nomeEsercizio, numSerie, numRipetizioni);
                    break;
                
                case "DORSALI":
                    esercizio = new EsercizioDorsali(nomeEsercizio, numSerie, numRipetizioni);
                    break;
                
                case "ADDOME":
                    esercizio = new EsercizioAddome(nomeEsercizio, numSerie, numRipetizioni);
                    break;
            }
            
            listaEsercizi.add(esercizio);
            choice = multipleChoice("Vuoi inserire un altro esercizio?", new Object[] {"Sì", "No"});
        }while(choice == 0);
        
        pianoEsercizi = new PianoEsercizi(listaEsercizi);
        
        return pianoEsercizi;
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
        panelProdotti = new javax.swing.JPanel();
        labelProdotti = new javax.swing.JLabel();
        labelAvvisoProdotti1 = new javax.swing.JLabel();
        labelAvvisoProdotti2 = new javax.swing.JLabel();
        labelAvvisoProdotti3 = new javax.swing.JLabel();
        scrollPaneTabellaProdotti = new javax.swing.JScrollPane();
        tableProdotti = new javax.swing.JTable();
        buttonOrdinaNome = new javax.swing.JButton();
        buttonOrdinaCategoria = new javax.swing.JButton();
        buttonOrdinaPrezzo = new javax.swing.JButton();
        buttonAggiungiProdotto = new javax.swing.JButton();
        buttonModificaProdotto = new javax.swing.JButton();
        buttonEliminaProdotto = new javax.swing.JButton();
        panelSchede = new javax.swing.JPanel();
        labelSchede = new javax.swing.JLabel();
        labelDieta = new javax.swing.JLabel();
        labelEsercizi = new javax.swing.JLabel();
        labelAvvisoSchede1 = new javax.swing.JLabel();
        labelAvvisoSchede2 = new javax.swing.JLabel();
        labelAvvisoSchede3 = new javax.swing.JLabel();
        labelAvvisoSchede4 = new javax.swing.JLabel();
        labelAvvisoSchede5 = new javax.swing.JLabel();
        scrollPaneTabellaSchede = new javax.swing.JScrollPane();
        tableSchede = new javax.swing.JTable();
        scrollPaneDieta = new javax.swing.JScrollPane();
        textAreaDieta = new javax.swing.JTextArea();
        scrollPaneEsercizi = new javax.swing.JScrollPane();
        textAreaEsercizi = new javax.swing.JTextArea();
        buttonDettagliScheda = new javax.swing.JButton();
        buttonAggiungiScheda = new javax.swing.JButton();
        buttonModificaScheda = new javax.swing.JButton();
        buttonEliminaScheda = new javax.swing.JButton();
        panelPromozioni = new javax.swing.JPanel();
        labelPromozioni = new javax.swing.JLabel();
        labelAvvisoPromozioni1 = new javax.swing.JLabel();
        labelAvvisoPromozioni2 = new javax.swing.JLabel();
        labelAvvisoPromozioni3 = new javax.swing.JLabel();
        labelAvvisoPromozioni4 = new javax.swing.JLabel();
        scrollPaneTabellaPromozioni = new javax.swing.JScrollPane();
        tablePromozioni = new javax.swing.JTable();
        buttonAggiungiPromozione = new javax.swing.JButton();
        buttonModificaDatiPromozione = new javax.swing.JButton();
        buttonCambiaStatoPromozione = new javax.swing.JButton();
        buttonEliminaPromozione = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();
        menuHome = new javax.swing.JMenu();
        menuProdotti = new javax.swing.JMenu();
        menuSchede = new javax.swing.JMenu();
        menuPromozioni = new javax.swing.JMenu();
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

        labelAvvisoProdotti1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        labelAvvisoProdotti1.setText("(*)");

        labelAvvisoProdotti2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        labelAvvisoProdotti2.setText("(*)");

        labelAvvisoProdotti3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        labelAvvisoProdotti3.setText("(*)Clicca sul prodotto desiderato in tabella");

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

        buttonAggiungiProdotto.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        buttonAggiungiProdotto.setText("Aggiungi Prodotto");
        buttonAggiungiProdotto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAggiungiProdottoActionPerformed(evt);
            }
        });

        buttonModificaProdotto.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        buttonModificaProdotto.setText("Modifica Prodotto");
        buttonModificaProdotto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonModificaProdottoActionPerformed(evt);
            }
        });

        buttonEliminaProdotto.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        buttonEliminaProdotto.setText("Elimina Prodotto");
        buttonEliminaProdotto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEliminaProdottoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelProdottiLayout = new javax.swing.GroupLayout(panelProdotti);
        panelProdotti.setLayout(panelProdottiLayout);
        panelProdottiLayout.setHorizontalGroup(
            panelProdottiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelProdottiLayout.createSequentialGroup()
                .addGroup(panelProdottiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelProdottiLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelProdotti)
                        .addGap(191, 191, 191))
                    .addGroup(panelProdottiLayout.createSequentialGroup()
                        .addGap(143, 143, 143)
                        .addComponent(buttonOrdinaNome)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonOrdinaCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(182, 182, 182)))
                .addComponent(buttonOrdinaPrezzo)
                .addGap(159, 159, 159))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelProdottiLayout.createSequentialGroup()
                .addContainerGap(78, Short.MAX_VALUE)
                .addGroup(panelProdottiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelAvvisoProdotti3)
                    .addComponent(scrollPaneTabellaProdotti, javax.swing.GroupLayout.PREFERRED_SIZE, 904, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(78, 78, 78))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelProdottiLayout.createSequentialGroup()
                .addGap(146, 146, 146)
                .addComponent(buttonAggiungiProdotto, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonModificaProdotto, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelAvvisoProdotti1)
                .addGap(156, 156, 156)
                .addComponent(buttonEliminaProdotto, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelAvvisoProdotti2)
                .addGap(128, 128, 128))
        );
        panelProdottiLayout.setVerticalGroup(
            panelProdottiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelProdottiLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(labelProdotti)
                .addGap(28, 28, 28)
                .addGroup(panelProdottiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonOrdinaCategoria)
                    .addComponent(buttonOrdinaPrezzo)
                    .addComponent(buttonOrdinaNome))
                .addGap(38, 38, 38)
                .addComponent(scrollPaneTabellaProdotti, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addGroup(panelProdottiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonModificaProdotto, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelAvvisoProdotti1)
                    .addComponent(buttonEliminaProdotto, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelAvvisoProdotti2)
                    .addComponent(buttonAggiungiProdotto, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 150, Short.MAX_VALUE)
                .addComponent(labelAvvisoProdotti3)
                .addContainerGap())
        );

        mainPanel.add(panelProdotti, "panelProdotti");

        labelSchede.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        labelSchede.setText("ELENCO SCHEDE FITNESS");

        labelDieta.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        labelDieta.setText("DIETA");

        labelEsercizi.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        labelEsercizi.setText("ESERCIZI");

        labelAvvisoSchede1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        labelAvvisoSchede1.setText("(*)");

        labelAvvisoSchede2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        labelAvvisoSchede2.setText("(*)");

        labelAvvisoSchede3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        labelAvvisoSchede3.setText("(*)");

        labelAvvisoSchede4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        labelAvvisoSchede4.setText("(*)");

        labelAvvisoSchede5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        labelAvvisoSchede5.setText("(*)Clicca sulla Scheda Fitness desiderata in tabella");

        tableSchede.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nome", "Altezza Minima", "Altezza Massima", "Peso Minimo", "Peso Massimo", "Obiettivo Scheda"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableSchede.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableSchedeMouseClicked(evt);
            }
        });
        scrollPaneTabellaSchede.setViewportView(tableSchede);

        textAreaDieta.setColumns(20);
        textAreaDieta.setRows(5);
        scrollPaneDieta.setViewportView(textAreaDieta);

        textAreaEsercizi.setColumns(20);
        textAreaEsercizi.setRows(5);
        scrollPaneEsercizi.setViewportView(textAreaEsercizi);

        buttonDettagliScheda.setText("Visualizza Dieta/Esercizi Scheda");
        buttonDettagliScheda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDettagliSchedaActionPerformed(evt);
            }
        });

        buttonAggiungiScheda.setText("Aggiungi Scheda");
        buttonAggiungiScheda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAggiungiSchedaActionPerformed(evt);
            }
        });

        buttonModificaScheda.setText("Modifica Scheda");
        buttonModificaScheda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonModificaSchedaActionPerformed(evt);
            }
        });

        buttonEliminaScheda.setText("Elimina Scheda");
        buttonEliminaScheda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEliminaSchedaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelSchedeLayout = new javax.swing.GroupLayout(panelSchede);
        panelSchede.setLayout(panelSchedeLayout);
        panelSchedeLayout.setHorizontalGroup(
            panelSchedeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSchedeLayout.createSequentialGroup()
                .addGap(386, 386, 386)
                .addComponent(labelSchede)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panelSchedeLayout.createSequentialGroup()
                .addContainerGap(47, Short.MAX_VALUE)
                .addGroup(panelSchedeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelSchedeLayout.createSequentialGroup()
                        .addComponent(labelAvvisoSchede5)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelSchedeLayout.createSequentialGroup()
                        .addGroup(panelSchedeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(scrollPaneTabellaSchede, javax.swing.GroupLayout.PREFERRED_SIZE, 965, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelSchedeLayout.createSequentialGroup()
                                .addGroup(panelSchedeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(scrollPaneDieta, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelDieta)
                                    .addGroup(panelSchedeLayout.createSequentialGroup()
                                        .addComponent(buttonAggiungiScheda)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(labelAvvisoSchede2)
                                        .addGap(90, 90, 90)
                                        .addComponent(buttonDettagliScheda)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(labelAvvisoSchede1)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(panelSchedeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(labelEsercizi)
                                    .addComponent(scrollPaneEsercizi, javax.swing.GroupLayout.PREFERRED_SIZE, 502, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSchedeLayout.createSequentialGroup()
                                        .addGap(85, 85, 85)
                                        .addComponent(buttonModificaScheda)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(labelAvvisoSchede3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(buttonEliminaScheda)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(labelAvvisoSchede4)
                                        .addGap(45, 45, 45)))))
                        .addGap(48, 48, 48))))
        );
        panelSchedeLayout.setVerticalGroup(
            panelSchedeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSchedeLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(labelSchede)
                .addGap(18, 18, 18)
                .addComponent(scrollPaneTabellaSchede, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelSchedeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonDettagliScheda)
                    .addComponent(buttonModificaScheda)
                    .addComponent(buttonEliminaScheda)
                    .addComponent(labelAvvisoSchede1)
                    .addComponent(labelAvvisoSchede3)
                    .addComponent(labelAvvisoSchede4)
                    .addComponent(buttonAggiungiScheda)
                    .addComponent(labelAvvisoSchede2))
                .addGap(23, 23, 23)
                .addGroup(panelSchedeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelDieta)
                    .addComponent(labelEsercizi))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelSchedeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(scrollPaneDieta, javax.swing.GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE)
                    .addComponent(scrollPaneEsercizi))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(labelAvvisoSchede5)
                .addContainerGap())
        );

        mainPanel.add(panelSchede, "panelSchede");

        labelPromozioni.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        labelPromozioni.setText("ELENCO PROMOZIONI");

        labelAvvisoPromozioni1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        labelAvvisoPromozioni1.setText("(*)");

        labelAvvisoPromozioni2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        labelAvvisoPromozioni2.setText("(*)");

        labelAvvisoPromozioni3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        labelAvvisoPromozioni3.setText("(*)");

        labelAvvisoPromozioni4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        labelAvvisoPromozioni4.setText("(*)Clicca sulla promozione desiderata in tabella");

        tablePromozioni.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id Promo", "Nome Promo", "Categoria Prodotti Scontata", "Percentuale Sconto", "Data ed Ora creazione", "Stato Promo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablePromozioni.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablePromozioniMouseClicked(evt);
            }
        });
        scrollPaneTabellaPromozioni.setViewportView(tablePromozioni);

        buttonAggiungiPromozione.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        buttonAggiungiPromozione.setText("Aggiungi Promozione");
        buttonAggiungiPromozione.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAggiungiPromozioneActionPerformed(evt);
            }
        });

        buttonModificaDatiPromozione.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        buttonModificaDatiPromozione.setText("Modifica Dati Promozione");
        buttonModificaDatiPromozione.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonModificaDatiPromozioneActionPerformed(evt);
            }
        });

        buttonCambiaStatoPromozione.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        buttonCambiaStatoPromozione.setText("Attiva/Disattiva Promozione");
        buttonCambiaStatoPromozione.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCambiaStatoPromozioneActionPerformed(evt);
            }
        });

        buttonEliminaPromozione.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        buttonEliminaPromozione.setText("Elimina Promozione");
        buttonEliminaPromozione.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEliminaPromozioneActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelPromozioniLayout = new javax.swing.GroupLayout(panelPromozioni);
        panelPromozioni.setLayout(panelPromozioniLayout);
        panelPromozioniLayout.setHorizontalGroup(
            panelPromozioniLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPromozioniLayout.createSequentialGroup()
                .addContainerGap(41, Short.MAX_VALUE)
                .addGroup(panelPromozioniLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPromozioniLayout.createSequentialGroup()
                        .addComponent(labelPromozioni)
                        .addGap(426, 426, 426))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPromozioniLayout.createSequentialGroup()
                        .addGroup(panelPromozioniLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelAvvisoPromozioni4)
                            .addGroup(panelPromozioniLayout.createSequentialGroup()
                                .addGroup(panelPromozioniLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(panelPromozioniLayout.createSequentialGroup()
                                        .addComponent(buttonAggiungiPromozione, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(69, 69, 69)
                                        .addComponent(buttonModificaDatiPromozione, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(labelAvvisoPromozioni1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(buttonCambiaStatoPromozione, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(labelAvvisoPromozioni2)
                                        .addGap(54, 54, 54)
                                        .addComponent(buttonEliminaPromozione, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(scrollPaneTabellaPromozioni, javax.swing.GroupLayout.PREFERRED_SIZE, 980, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelAvvisoPromozioni3)))
                        .addGap(18, 18, 18))))
        );
        panelPromozioniLayout.setVerticalGroup(
            panelPromozioniLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPromozioniLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(labelPromozioni)
                .addGap(18, 18, 18)
                .addComponent(scrollPaneTabellaPromozioni, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelPromozioniLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonAggiungiPromozione, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonModificaDatiPromozione, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonEliminaPromozione, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonCambiaStatoPromozione, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelAvvisoPromozioni1)
                    .addComponent(labelAvvisoPromozioni2)
                    .addComponent(labelAvvisoPromozioni3))
                .addGap(18, 18, 18)
                .addComponent(labelAvvisoPromozioni4)
                .addContainerGap(240, Short.MAX_VALUE))
        );

        mainPanel.add(panelPromozioni, "panelPromozioni");

        menuHome.setText("Home");
        menuHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuHomeMouseClicked(evt);
            }
        });
        menuBar.add(menuHome);

        menuProdotti.setText("Prodotti");
        menuProdotti.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuProdottiMouseClicked(evt);
            }
        });
        menuBar.add(menuProdotti);

        menuSchede.setText("Schede Fitness");
        menuSchede.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuSchedeMouseClicked(evt);
            }
        });
        menuBar.add(menuSchede);

        menuPromozioni.setText("Promozioni");
        menuPromozioni.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuPromozioniMouseClicked(evt);
            }
        });
        menuBar.add(menuPromozioni);

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

    private void menuHomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuHomeMouseClicked
        changeCard("panelLogo");
    }//GEN-LAST:event_menuHomeMouseClicked

    private void menuEsciMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuEsciMouseClicked
        int choice = multipleChoice("Sei sicuro di voler effettuare il logout?", new Object[] {"Sì", "No"});
        if(choice == 0){
            new LoginFrame().setVisible(true);
            close();
        }
    }//GEN-LAST:event_menuEsciMouseClicked

    private void buttonOrdinaPrezzoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonOrdinaPrezzoActionPerformed
        Collections.sort(FR.getGestoreProdotti().getCatalogo().getListaProdotti(), new Comparator<Prodotto>(){
            @Override
            public int compare(Prodotto prod1, Prodotto prod2){
                return Float.compare(prod1.getPrezzo(), prod2.getPrezzo());
            }
        });
        updateTable(tableProdotti, "tableProdotti");
    }//GEN-LAST:event_buttonOrdinaPrezzoActionPerformed

    private void buttonOrdinaCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonOrdinaCategoriaActionPerformed
        Collections.sort(FR.getGestoreProdotti().getCatalogo().getListaProdotti(), new Comparator<Prodotto>(){
            @Override
            public int compare(Prodotto prod1, Prodotto prod2){
                return prod1.getCategoria().compareTo(prod2.getCategoria());
            }
        });
        updateTable(tableProdotti, "tableProdotti");
    }//GEN-LAST:event_buttonOrdinaCategoriaActionPerformed

    private void buttonOrdinaNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonOrdinaNomeActionPerformed
        Collections.sort(FR.getGestoreProdotti().getCatalogo().getListaProdotti(), new Comparator<Prodotto>(){
            @Override
            public int compare(Prodotto prod1, Prodotto prod2){
                return prod1.getNome().compareTo(prod2.getNome());
            }
        });
        updateTable(tableProdotti, "tableProdotti");
    }//GEN-LAST:event_buttonOrdinaNomeActionPerformed

    private void tableProdottiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableProdottiMouseClicked
        int selectedRowIndex = -1;
        
        selectedRowIndex = tableProdotti.getSelectedRow();
        if(selectedRowIndex != -1){
            buttonModificaProdotto.setEnabled(true);
            buttonEliminaProdotto.setEnabled(true);
        }
    }//GEN-LAST:event_tableProdottiMouseClicked

    private void menuProdottiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuProdottiMouseClicked
        changeCard("panelProdotti");
        updateTable(tableProdotti, "tableProdotti");
    }//GEN-LAST:event_menuProdottiMouseClicked

    private void buttonModificaProdottoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonModificaProdottoActionPerformed
        Object[] categorie = {"ALIMENTI", "INTEGRATORI", "BEVANDE", "ABBIGLIAMENTO", "ACCESSORI", "ATTREZZATURE", "MACCHINARI"};
        String nome, categoria;
        int prezzoGettoni, disp;
        float prezzo;
        
        try{
            //Recupero prodotto
            DefaultTableModel model = (DefaultTableModel) tableProdotti.getModel();
            int selectedRowIndex = tableProdotti.getSelectedRow();
            int idProd = Integer.parseInt(model.getValueAt(selectedRowIndex, 0).toString());
            Prodotto prod = FR.getGestoreProdotti().getCatalogo().getProdotto(idProd);
            
            //Inserimento dati
            nome = JOptionPane.showInputDialog(this, "Inserisci il nome del prodotto:", prod.getNome());
            categoria = (String)JOptionPane.showInputDialog(this, "Seleziona la categoria del prodotto:",
                    "Categoria", JOptionPane.PLAIN_MESSAGE, null, categorie, prod.getCategoria());
            prezzo = Float.parseFloat(JOptionPane.showInputDialog(this, "Inserisci il prezzo del prodotto:\n(AVVISO: con il punto '.' non la virgola ',')", prod.getPrezzo()));
            prezzoGettoni = Integer.parseInt(JOptionPane.showInputDialog(this, "Inserisci il prezzo in gettoni del prodotto:", prod.getPrezzoGettoni()));
            disp = Integer.parseInt(JOptionPane.showInputDialog(this, "Inserisci la disponibilità del prodotto:", prod.getDisp()));
            
            //Modifica prodotto ed aggiornamento tabella
            FR.getGestoreProdotti().modificaProdotto(idProd, nome, categoria, prezzo, prezzoGettoni, disp);
            JOptionPane.showMessageDialog(this, "Modifica effettuata!");
            updateTable(tableProdotti, "tableProdotti");
        }catch(NotPositiveNumberException | NumberFormatException e){
            JOptionPane.showMessageDialog(this, "Il prezzo, il prezzo in gettoni e la disponibilità devono essere numeri non negativi", "Warning", JOptionPane.WARNING_MESSAGE);
        }catch(GenericSystemException e){
            JOptionPane.showMessageDialog(this, "Nome prodotto già esistente", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_buttonModificaProdottoActionPerformed

    private void buttonAggiungiProdottoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAggiungiProdottoActionPerformed
        Object[] categorie = {"ALIMENTI", "INTEGRATORI", "BEVANDE", "ABBIGLIAMENTO", "ACCESSORI", "ATTREZZATURE", "MACCHINARI"};
        String nome, categoria;
        int prezzoGettoni, disp;
        float prezzo;
        
        try{            
            //Inserimento dati
            nome = JOptionPane.showInputDialog(this, "Inserisci il nome del prodotto:");
            categoria = (String)JOptionPane.showInputDialog(this, "Seleziona la categoria del prodotto:",
                    "Categoria", JOptionPane.PLAIN_MESSAGE, null, categorie, categorie[0]);
            prezzo = Float.parseFloat(JOptionPane.showInputDialog(this, "Inserisci il prezzo del prodotto:\n(AVVISO: con il punto '.' non la virgola ',')"));
            prezzoGettoni = Integer.parseInt(JOptionPane.showInputDialog(this, "Inserisci il prezzo in gettoni del prodotto:"));
            disp = Integer.parseInt(JOptionPane.showInputDialog(this, "Inserisci la disponibilità del prodotto:"));
            
            //Aggiunta prodotto ed aggiornamento tabella prodotti
            FR.getGestoreProdotti().aggiungiProdotto(nome, categoria, prezzo, prezzoGettoni, disp);
            JOptionPane.showMessageDialog(this, "Aggiunta effettuata!");
            updateTable(tableProdotti, "tableProdotti");
        }catch(NotPositiveNumberException | NumberFormatException e){
            JOptionPane.showMessageDialog(this, "Il prezzo, il prezzo in gettoni e la disponibilità devono essere numeri non negativi", "Warning", JOptionPane.WARNING_MESSAGE);
        }catch(GenericSystemException e){
            JOptionPane.showMessageDialog(this, "Prodotto già esistente", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_buttonAggiungiProdottoActionPerformed

    private void buttonEliminaProdottoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEliminaProdottoActionPerformed
        int choice;
        
        choice = multipleChoice("Sei sicuro di voler eliminare questo prodotto?", new Object[] {"Sì", "No"});
        if(choice == 0){
            DefaultTableModel model = (DefaultTableModel) tableProdotti.getModel();
            int selectedRowIndex = tableProdotti.getSelectedRow();
            int idProd = Integer.parseInt(model.getValueAt(selectedRowIndex, 0).toString());
            
            FR.getGestoreProdotti().eliminaProdotto(idProd);
            JOptionPane.showMessageDialog(this, "Eliminazione effettuata!");
            updateTable(tableProdotti, "tableProdotti");
        }
    }//GEN-LAST:event_buttonEliminaProdottoActionPerformed

    private void menuSchedeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuSchedeMouseClicked
        changeCard("panelSchede");
        updateTable(tableSchede, "tableSchede");
    }//GEN-LAST:event_menuSchedeMouseClicked

    private void tableSchedeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableSchedeMouseClicked
        int selectedRowIndex = -1;
        
        selectedRowIndex = tableSchede.getSelectedRow();
        if(selectedRowIndex != -1){
            buttonDettagliScheda.setEnabled(true);
            buttonModificaScheda.setEnabled(true);
            buttonEliminaScheda.setEnabled(true);
        }
    }//GEN-LAST:event_tableSchedeMouseClicked

    private void buttonDettagliSchedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDettagliSchedaActionPerformed
        DefaultTableModel model = (DefaultTableModel) tableSchede.getModel();
        int selectedRowIndex = tableSchede.getSelectedRow();
        int idScheda = Integer.parseInt(model.getValueAt(selectedRowIndex, 0).toString());
        Scheda scheda = FR.getGestoreSchede().getScheda(idScheda);
        
        textAreaDieta.setText(scheda.getDieta().toString());
        textAreaEsercizi.setText(scheda.getPianoEsercizi().toStringAdmin());
    }//GEN-LAST:event_buttonDettagliSchedaActionPerformed

    private void buttonAggiungiSchedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAggiungiSchedaActionPerformed
        String nomeScheda, obiettivo;
        float altMin, altMax, pMin, pMax;
        int choice;
        PianoEsercizi pianoEsercizi;
        Dieta dieta;
        
        try{
            //Inserimento dati
            nomeScheda = JOptionPane.showInputDialog(this, "Inserisci il Nome della Scheda Fitness:");
            altMin = Float.parseFloat(JOptionPane.showInputDialog(this, "Inserisci l'Altezza Minima per la Scheda Fitness:"));
            altMax = Float.parseFloat(JOptionPane.showInputDialog(this, "Inserisci l'Altezza Massima per la Scheda Fitness:"));
            pMin = Float.parseFloat(JOptionPane.showInputDialog(this, "Inserisci il Peso Minimo della per la Scheda Fitness:"));
            pMax = Float.parseFloat(JOptionPane.showInputDialog(this, "Inserisci il Peso Massimo della per la Scheda Fitness:"));
            choice = multipleChoice("Seleziona l'obiettivo della Scheda Fitness", new Object[] {"Definizione massa muscolare", "Aumento massa muscolare"});
            if(choice == 0)
                obiettivo = "Definizione";
            else
                obiettivo = "Aumento";
            dieta = createDiet();
            pianoEsercizi = createExercisePlan();
            
            //Aggiunta scheda ed aggiornamento
            FR.getGestoreSchede().aggiungiScheda(nomeScheda, altMin, altMax, pMin, pMax, obiettivo, dieta, pianoEsercizi);
            JOptionPane.showMessageDialog(this, "Aggiunta effettuata!");
            updateTable(tableSchede, "tableSchede");
            textAreaDieta.setText("");
            textAreaEsercizi.setText("");
            
        }catch(NotPositiveNumberException | NumberFormatException e){
            JOptionPane.showMessageDialog(this, "AVVISO: Errore d'inserimento dati numerici\n"
                    + "(Altezza Minima, Altezza Massima, Peso Minimo, Peso Massimo"
                    + ", Grammi, Numero Serie, Numero Ripetizioni e/o Durata Esercizio)"
                    , "Warning", JOptionPane.WARNING_MESSAGE);
        }catch(GenericSystemException e){
            JOptionPane.showMessageDialog(this, "AVVISO: Nome Scheda Fitness inserito già esistente"
                    , "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_buttonAggiungiSchedaActionPerformed

    private void buttonModificaSchedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonModificaSchedaActionPerformed
        String nomeScheda, obiettivo;
        float altMin, altMax, pMin, pMax;
        int choice;
        PianoEsercizi pianoEsercizi;
        Dieta dieta;
        
        try{
            //Recupero scheda
            DefaultTableModel model = (DefaultTableModel) tableSchede.getModel();
            int selectedRowIndex = tableSchede.getSelectedRow();
            int idScheda = Integer.parseInt(model.getValueAt(selectedRowIndex, 0).toString());
            Scheda scheda = FR.getGestoreSchede().getScheda(idScheda);
            
            //Inserimento dati
            nomeScheda = JOptionPane.showInputDialog(this, "Inserisci il Nome della Scheda Fitness:", scheda.getNome());
            altMin = Float.parseFloat(JOptionPane.showInputDialog(this, "Inserisci l'Altezza Minima per la Scheda Fitness:", scheda.getAltMin()));
            altMax = Float.parseFloat(JOptionPane.showInputDialog(this, "Inserisci l'Altezza Massima per la Scheda Fitness:", scheda.getAltMax()));
            pMin = Float.parseFloat(JOptionPane.showInputDialog(this, "Inserisci il Peso Minimo della per la Scheda Fitness:", scheda.getPMin()));
            pMax = Float.parseFloat(JOptionPane.showInputDialog(this, "Inserisci il Peso Massimo della per la Scheda Fitness:", scheda.getPMax()));
            choice = multipleChoice("Seleziona l'obiettivo della Scheda Fitness", new Object[] {"Definizione massa muscolare", "Aumento massa muscolare"});
            if(choice == 0)
                obiettivo = "Definizione";
            else
                obiettivo = "Aumento";
            choice = multipleChoice("Vuoi creare una nuova Dieta per la scheda selezionata?", new Object[] {"Sì", "No"});
            if(choice == 0)
                dieta = createDiet();
            else
                dieta = scheda.getDieta();
            choice = multipleChoice("Vuoi creare un nuovo Piano Esercizi per la scheda selezionata?", new Object[] {"Sì", "No"});
            if(choice == 0)
                pianoEsercizi = createExercisePlan();
            else
                pianoEsercizi = scheda.getPianoEsercizi();
            
            //Modifica scheda ed aggiornamento
            FR.getGestoreSchede().modificaScheda(idScheda, nomeScheda, altMin, altMax, pMin, pMax, obiettivo, dieta, pianoEsercizi);
            JOptionPane.showMessageDialog(this, "Modifica effettuata!");
            updateTable(tableSchede, "tableSchede");
            textAreaDieta.setText(scheda.getDieta().toString());
            textAreaEsercizi.setText(scheda.getPianoEsercizi().toString());
            
        }catch(NotPositiveNumberException | NumberFormatException e){
            JOptionPane.showMessageDialog(this, "AVVISO: Errore d'inserimento dati numerici\n"
                    + "(Altezza Minima, Altezza Massima, Peso Minimo, Peso Massimo"
                    + ", Grammi, Numero Serie, Numero Ripetizioni e/o Durata Esercizio)"
                    , "Warning", JOptionPane.WARNING_MESSAGE);
        }catch(GenericSystemException e){
            JOptionPane.showMessageDialog(this, "AVVISO: Nome Scheda Fitness inserito già esistente"
                    , "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_buttonModificaSchedaActionPerformed

    private void buttonEliminaSchedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEliminaSchedaActionPerformed
        int choice;
        
        choice = multipleChoice("Sei sicuro di voler eliminare questo prodotto?", new Object[] {"Sì", "No"});
        if(choice == 0){
            DefaultTableModel model = (DefaultTableModel) tableSchede.getModel();
            int selectedRowIndex = tableSchede.getSelectedRow();
            int idScheda = Integer.parseInt(model.getValueAt(selectedRowIndex, 0).toString());
            
            FR.getGestoreSchede().eliminaScheda(idScheda);
            JOptionPane.showMessageDialog(this, "Eliminazione effettuata!");
            updateTable(tableSchede, "tableSchede");
            textAreaDieta.setText("");
            textAreaEsercizi.setText("");
        }
    }//GEN-LAST:event_buttonEliminaSchedaActionPerformed

    private void menuPromozioniMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuPromozioniMouseClicked
        changeCard("panelPromozioni");
        updateTable(tablePromozioni, "tablePromozioni");
    }//GEN-LAST:event_menuPromozioniMouseClicked

    private void tablePromozioniMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablePromozioniMouseClicked
        int selectedRowIndex = -1;
        
        selectedRowIndex = tablePromozioni.getSelectedRow();
        if(selectedRowIndex != -1){
            buttonModificaDatiPromozione.setEnabled(true);
            buttonCambiaStatoPromozione.setEnabled(true);
            buttonEliminaPromozione.setEnabled(true);
        }
    }//GEN-LAST:event_tablePromozioniMouseClicked

    private void buttonAggiungiPromozioneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAggiungiPromozioneActionPerformed
        Object[] categorie = {"ALIMENTI", "INTEGRATORI", "BEVANDE", "ABBIGLIAMENTO", "ACCESSORI", "ATTREZZATURE", "MACCHINARI"};
        String nome, cTarget;
        float percSconto;
        
        try{
            //Inserimento dati
            nome = JOptionPane.showInputDialog(this, "Inserisci il Nome della promozione:");
            cTarget = (String)JOptionPane.showInputDialog(this, "Seleziona la categoria di prodotti da mettere in sconto:",
                    "Categoria Sconto", JOptionPane.PLAIN_MESSAGE, null, categorie, categorie[0]);
            percSconto = Float.parseFloat(JOptionPane.showInputDialog(this, "Inserisci la Percentuale di sconto:"));
            
            //Aggiunta promozione ed aggiornamento tabella
            FR.getGestorePromozioni().aggiungiPromozione(nome, cTarget, percSconto);
            JOptionPane.showMessageDialog(this, "Aggiunta effettuata!");
            updateTable(tablePromozioni, "tablePromozioni");
            
        }catch(NotPositiveNumberException | NumberFormatException e){
            JOptionPane.showMessageDialog(this, "La percentuale di sconto inserita dev'essere un numero positivo", "Warning", JOptionPane.WARNING_MESSAGE);
        }catch(GenericSystemException e){
            JOptionPane.showMessageDialog(this, "Nome promozione già esistente", "Warning", JOptionPane.WARNING_MESSAGE);
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Categoria prodotti già in sconto", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_buttonAggiungiPromozioneActionPerformed

    private void buttonModificaDatiPromozioneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonModificaDatiPromozioneActionPerformed
        Object[] categorie = {"ALIMENTI", "INTEGRATORI", "BEVANDE", "ABBIGLIAMENTO", "ACCESSORI", "ATTREZZATURE", "MACCHINARI"};
        String nome, cTarget;
        float percSconto;
        
        try{
            //Recupero promozione
            DefaultTableModel model = (DefaultTableModel) tablePromozioni.getModel();
            int selectedRowIndex = tablePromozioni.getSelectedRow();
            int idPromo = Integer.parseInt(model.getValueAt(selectedRowIndex, 0).toString());
            Promozione promo = FR.getGestorePromozioni().getPromozione(idPromo);
            
            //Inserimento dati
            nome = JOptionPane.showInputDialog(this, "Inserisci il Nome della promozione:", promo.getNome());
            cTarget = (String)JOptionPane.showInputDialog(this, "Seleziona la categoria di prodotti da mettere in sconto:",
                    "Categoria Sconto", JOptionPane.PLAIN_MESSAGE, null, categorie, promo.getCTarget());
            percSconto = Float.parseFloat(JOptionPane.showInputDialog(this, "Inserisci la Percentuale di sconto:", promo.getPercSconto()));
            
            //Modifica promozione ed aggiornamento
            FR.getGestorePromozioni().modificaDatiPromozione(idPromo, nome, cTarget, percSconto);
            JOptionPane.showMessageDialog(this, "Modifica effettuata!");
            updateTable(tablePromozioni, "tablePromozioni");
            
        }catch(NotPositiveNumberException | NumberFormatException e){
            JOptionPane.showMessageDialog(this, "La percentuale di sconto inserita dev'essere un numero positivo", "Warning", JOptionPane.WARNING_MESSAGE);
        }catch(GenericSystemException e){
            JOptionPane.showMessageDialog(this, "Nome promozione già esistente", "Warning", JOptionPane.WARNING_MESSAGE);
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Categoria prodotti già in sconto", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_buttonModificaDatiPromozioneActionPerformed

    private void buttonCambiaStatoPromozioneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCambiaStatoPromozioneActionPerformed
        int choice;
        
        //Recupero promozione
        DefaultTableModel model = (DefaultTableModel) tablePromozioni.getModel();
        int selectedRowIndex = tablePromozioni.getSelectedRow();
        int idPromo = Integer.parseInt(model.getValueAt(selectedRowIndex, 0).toString());
        Promozione promo = FR.getGestorePromozioni().getPromozione(idPromo);
        
        //Cambio stato
        if(promo.getStato().equals("NON ATTIVA")){
            choice = multipleChoice("Sei sicuro di voler ATTIVARE questa promozione?", new Object[] {"Sì", "No"});
            if(choice == 0){
                FR.getGestorePromozioni().cambiaStatoPromozione(idPromo);
                JOptionPane.showMessageDialog(this, "Promozione ATTIVA");
            }
        }
        else{
            choice = multipleChoice("Sei sicuro di voler DISATTIVARE questa promozione?", new Object[] {"Sì", "No"});
            if(choice == 0){
                FR.getGestorePromozioni().cambiaStatoPromozione(idPromo);
                JOptionPane.showMessageDialog(this, "Promozione NON ATTIVA");
            }
        }
        updateTable(tablePromozioni, "tablePromozioni");
    }//GEN-LAST:event_buttonCambiaStatoPromozioneActionPerformed

    private void buttonEliminaPromozioneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEliminaPromozioneActionPerformed
        int choice;
        
        choice = multipleChoice("Sei sicuro di voler eliminare questa promozione?", new Object[] {"Sì", "No"});
        if(choice == 0){
            //Recupero promozione
            DefaultTableModel model = (DefaultTableModel) tablePromozioni.getModel();
            int selectedRowIndex = tablePromozioni.getSelectedRow();
            int idPromo = Integer.parseInt(model.getValueAt(selectedRowIndex, 0).toString());
            
            //Eliminazione ed aggiornamento
            FR.getGestorePromozioni().eliminaPromozione(idPromo);
            JOptionPane.showMessageDialog(this, "Eliminazione effettuata!");
            updateTable(tablePromozioni, "tablePromozioni");
        }
    }//GEN-LAST:event_buttonEliminaPromozioneActionPerformed

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
            java.util.logging.Logger.getLogger(GUIAdminFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUIAdminFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUIAdminFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUIAdminFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUIAdminFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAggiungiProdotto;
    private javax.swing.JButton buttonAggiungiPromozione;
    private javax.swing.JButton buttonAggiungiScheda;
    private javax.swing.JButton buttonCambiaStatoPromozione;
    private javax.swing.JButton buttonDettagliScheda;
    private javax.swing.JButton buttonEliminaProdotto;
    private javax.swing.JButton buttonEliminaPromozione;
    private javax.swing.JButton buttonEliminaScheda;
    private javax.swing.JButton buttonModificaDatiPromozione;
    private javax.swing.JButton buttonModificaProdotto;
    private javax.swing.JButton buttonModificaScheda;
    private javax.swing.JButton buttonOrdinaCategoria;
    private javax.swing.JButton buttonOrdinaNome;
    private javax.swing.JButton buttonOrdinaPrezzo;
    private javax.swing.JLabel labelAvvisoProdotti1;
    private javax.swing.JLabel labelAvvisoProdotti2;
    private javax.swing.JLabel labelAvvisoProdotti3;
    private javax.swing.JLabel labelAvvisoPromozioni1;
    private javax.swing.JLabel labelAvvisoPromozioni2;
    private javax.swing.JLabel labelAvvisoPromozioni3;
    private javax.swing.JLabel labelAvvisoPromozioni4;
    private javax.swing.JLabel labelAvvisoSchede1;
    private javax.swing.JLabel labelAvvisoSchede2;
    private javax.swing.JLabel labelAvvisoSchede3;
    private javax.swing.JLabel labelAvvisoSchede4;
    private javax.swing.JLabel labelAvvisoSchede5;
    private javax.swing.JLabel labelDieta;
    private javax.swing.JLabel labelEsercizi;
    private javax.swing.JLabel labelLogo;
    private javax.swing.JLabel labelProdotti;
    private javax.swing.JLabel labelPromozioni;
    private javax.swing.JLabel labelSchede;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu menuEsci;
    private javax.swing.JMenu menuHome;
    private javax.swing.JMenu menuProdotti;
    private javax.swing.JMenu menuPromozioni;
    private javax.swing.JMenu menuSchede;
    private javax.swing.JPanel panelLogo;
    private javax.swing.JPanel panelProdotti;
    private javax.swing.JPanel panelPromozioni;
    private javax.swing.JPanel panelSchede;
    private javax.swing.JScrollPane scrollPaneDieta;
    private javax.swing.JScrollPane scrollPaneEsercizi;
    private javax.swing.JScrollPane scrollPaneTabellaProdotti;
    private javax.swing.JScrollPane scrollPaneTabellaPromozioni;
    private javax.swing.JScrollPane scrollPaneTabellaSchede;
    private javax.swing.JTable tableProdotti;
    private javax.swing.JTable tablePromozioni;
    private javax.swing.JTable tableSchede;
    private javax.swing.JTextArea textAreaDieta;
    private javax.swing.JTextArea textAreaEsercizi;
    // End of variables declaration//GEN-END:variables
}
