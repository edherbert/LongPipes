/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package System;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import pipes.Pipe;
import pipes.Pipe1;
import pipes.RequirementsInfo;

/**
 * The main gui class. This handles the gui and it's callbacks.
 * @author UP800456
 */
public class PipeGUI extends javax.swing.JFrame {

    DefaultListModel<String> model = new DefaultListModel<>();
    Session session;
    /**
     * Creates new form PipeGUI
     */
    public PipeGUI(Session session) throws Exception {
        
        // Set system look and feel
        UIManager.setLookAndFeel(
            UIManager.getSystemLookAndFeelClassName());
        
        this.session = session;
        
        initComponents();
     
        OrderList.setModel(model);
        OrderList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        updateForm();
    }
    
    public void updateForm(){
        Order current = session.getCurrentOrder();
        
        //If there are more than 0 orders and there is a selected order then enable the form.
        setFormEnabled(session.getNumberOfOrders() > 0 && current != null);
        
        if(current == null) return;
        
        LengthText.setText(current.getRequirements().getLength() + "");
        DiameterText.setText(current.getRequirements().getOuterDiameter() + "");
        
        InsulationCheck.setSelected(current.getRequirements().getInnerInsulation());
        ChemicalResistanceCheck.setSelected(current.getRequirements().getChemicalResistance());
        ReinforcementCheck.setSelected(current.getRequirements().getOuterReinforcement());
        
        //-1 because the combo box lists from 0 (first), and 1 is first, so should be index 0.
        PlasticGradeCombo.setSelectedIndex((int)current.getRequirements().getPlasticGrade() - 1);
        ColourCombo.setSelectedIndex((int)current.getRequirements().getColourPrint());
        
        QuantityCombo.setSelectedIndex(current.getQuantity() - 1);
        
        updateTotal();
        updateFeedback();
    }
    
    public void updateList(){
        model.clear();
        
        for(int i = 0; i < session.getNumberOfOrders(); i++){
            String error = "";
            if(!session.getOrder(i).isPossible()) error = " - Error";
            model.addElement("Order " + (i + 1) + error);
        }
        
        //If there are orders then select the current one.
        if(session.getNumberOfOrders() > 0){
            OrderList.setSelectedIndex(session.getSelectionIndex());
        }
    }
    
    public void updateFeedback(){
        Order current = session.getCurrentOrder();
        //If there is no current order then blank out the box and return;
        if(current == null) {
            FeedbackBox.setText("");
            return;
        }
        String feedback = "";
        String listItem = "Order " + (session.getSelectionIndex() + 1);
        
        if(!current.isPossible()){
            feedback = "That pipe is impossible!\n";
            feedback += current.getFeedback();
            
            listItem += " - Error";
        }else{
            feedback = "That pipe is possible!";
        }
        
        if(OrderList.getSelectedIndex() >= 0){
            model.set(OrderList.getSelectedIndex(), listItem);
        }
        
        FeedbackBox.setText(feedback);
    }
    
    public void updateTotal(){
        Order current = session.getCurrentOrder();
        
        double base = 0d;
        double totalOrder = 0d;
        double sessionTotal = 0d;
        
        if(current != null){
            base = current.getBaseCost();
            totalOrder = current.getTotalCost();
            sessionTotal = session.getSessionTotal();
        }
        
        DecimalFormat df = new DecimalFormat("######0.00");
        
        TotalSingleBox.setText("£" + df.format(base));
        TotalBox.setText("£" + df.format(totalOrder));
        SessionTotal.setText("£" + df.format(sessionTotal));    
        
    }
    
    public void setFormEnabled(boolean enabled){
        LengthText.setEnabled(enabled);
        DiameterText.setEnabled(enabled);
        
        InsulationCheck.setEnabled(enabled);
        ChemicalResistanceCheck.setEnabled(enabled);
        ReinforcementCheck.setEnabled(enabled);
        
        PlasticGradeCombo.setEnabled(enabled);
        ColourCombo.setEnabled(enabled);
        
        QuantityCombo.setEnabled(enabled);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        LengthText = new javax.swing.JTextField();
        DiameterText = new javax.swing.JTextField();
        PlasticGradeCombo = new javax.swing.JComboBox<>();
        ColourCombo = new javax.swing.JComboBox<>();
        InsulationCheck = new javax.swing.JCheckBox();
        ChemicalResistanceCheck = new javax.swing.JCheckBox();
        jLabel6 = new javax.swing.JLabel();
        QuantityCombo = new javax.swing.JComboBox<>();
        ReinforcementCheck = new javax.swing.JCheckBox();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        TotalBox = new javax.swing.JTextPane();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        TotalSingleBox = new javax.swing.JTextPane();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        FeedbackBox = new javax.swing.JTextPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        SessionTotal = new javax.swing.JTextPane();
        jLabel7 = new javax.swing.JLabel();
        NewOrderButton = new javax.swing.JButton();
        DeleteOrderButton = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        CloseButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        OrderList = new javax.swing.JList<>();
        jSeparator4 = new javax.swing.JSeparator();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Long Pipes");
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Baghdad", 0, 24)); // NOI18N
        jLabel1.setText("Long Pipes");

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setText("Length (m) :");

        jLabel3.setText("Diameter (in) :");

        jLabel4.setText("Grade :");

        jLabel5.setText("Colour :");

        LengthText.setName("LengthText"); // NOI18N
        LengthText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textKeyReleased(evt);
            }
        });

        DiameterText.setName("DiameterText"); // NOI18N
        DiameterText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textKeyReleased(evt);
            }
        });

        PlasticGradeCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5" }));
        PlasticGradeCombo.setName("GradeCombo"); // NOI18N
        PlasticGradeCombo.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                popupWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        ColourCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "None", "1 colour", "2 colours" }));
        ColourCombo.setName("ColourCombo"); // NOI18N
        ColourCombo.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                popupWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        InsulationCheck.setText("Insulation");
        InsulationCheck.setName("InsulationCheck"); // NOI18N
        InsulationCheck.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                checkPropertyChange(evt);
            }
        });

        ChemicalResistanceCheck.setText("Chemical Resistance");
        ChemicalResistanceCheck.setName("ChemicalCheck"); // NOI18N
        ChemicalResistanceCheck.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                checkPropertyChange(evt);
            }
        });

        jLabel6.setText("Quantity :");

        QuantityCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));
        QuantityCombo.setName("QuantityCombo"); // NOI18N
        QuantityCombo.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                popupWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        ReinforcementCheck.setText("Outer Reinforcement");
        ReinforcementCheck.setName("ReinforcementCheck"); // NOI18N
        ReinforcementCheck.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                checkPropertyChange(evt);
            }
        });

        TotalBox.setEditable(false);
        jScrollPane4.setViewportView(TotalBox);

        jLabel9.setText("Total (single):");

        jLabel10.setText("Overall total:");

        TotalSingleBox.setEditable(false);
        TotalSingleBox.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane5.setViewportView(TotalSingleBox);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(ColourCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(PlasticGradeCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                    .addGap(18, 18, 18)
                                    .addComponent(DiameterText, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                        .addComponent(LengthText, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(QuantityCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ReinforcementCheck)
                            .addComponent(InsulationCheck)
                            .addComponent(ChemicalResistanceCheck))
                        .addContainerGap(46, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane4)
                                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE))))
                        .addGap(21, 21, 21))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jSeparator5)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(InsulationCheck)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ReinforcementCheck)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ChemicalResistanceCheck))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(LengthText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(DiameterText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(PlasticGradeCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ColourCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel8))))
                .addGap(24, 24, 24)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(jLabel9)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(16, 16, 16))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(QuantityCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        FeedbackBox.setEditable(false);
        jScrollPane1.setViewportView(FeedbackBox);

        SessionTotal.setEditable(false);
        jScrollPane3.setViewportView(SessionTotal);

        jLabel7.setText("Total:");

        NewOrderButton.setText("New Order");
        NewOrderButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NewOrderButtonActionPerformed(evt);
            }
        });

        DeleteOrderButton.setText("Delete Order");
        DeleteOrderButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteOrderButtonActionPerformed(evt);
            }
        });

        CloseButton.setText("Close");
        CloseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CloseButtonActionPerformed(evt);
            }
        });

        OrderList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listSelectionChanged(evt);
            }
        });
        jScrollPane2.setViewportView(OrderList);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(170, 170, 170)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 433, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel7)
                                            .addGap(18, 18, 18)
                                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 5, Short.MAX_VALUE))
                            .addComponent(jSeparator4))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(CloseButton)
                                .addGap(88, 88, 88))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(NewOrderButton)
                                .addGap(18, 18, 18)
                                .addComponent(DeleteOrderButton)
                                .addGap(20, 20, 20))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(NewOrderButton)
                            .addComponent(DeleteOrderButton))
                        .addGap(24, 24, 24)
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CloseButton)))
                .addGap(19, 19, 19))
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void NewOrderButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NewOrderButtonActionPerformed
        session.setCurrentOrder(session.createOrder());

        updateForm();
        updateList();
        updateTotal();

        OrderList.setSelectedIndex(model.getSize() - 1);
    }//GEN-LAST:event_NewOrderButtonActionPerformed

    private void DeleteOrderButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteOrderButtonActionPerformed
        if(session.getNumberOfOrders() <= 0) return;
        session.deleteOrderByIndex(OrderList.getSelectedIndex());
        
        updateList();
        updateForm();
        updateFeedback();
        updateTotal();
    }//GEN-LAST:event_DeleteOrderButtonActionPerformed

    private void CloseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CloseButtonActionPerformed
            //Send an event to the window telling it to close.
            dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));   
    }//GEN-LAST:event_CloseButtonActionPerformed

    private void textKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textKeyReleased
        JTextField f = (JTextField)evt.getComponent();
        String current = f.getText();
        
        if(current.equals("")) return;
        if(session.getNumberOfOrders() <= 0) return;
        try{
            double val = Double.parseDouble(current);
        
            if(f.getName().equals("LengthText")){
                session.getCurrentOrder().getRequirements().setLength(val);
            }else if(f.getName().equals("DiameterText")){
                session.getCurrentOrder().getRequirements().setOuterDiameter(val);
            }
            
            session.getCurrentOrder().updateOrder();
            updateTotal();
            updateFeedback();
        }catch(NumberFormatException e){
            System.err.println("Error converting that input to a double.");
        }
    }//GEN-LAST:event_textKeyReleased

    private void listSelectionChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listSelectionChanged
        session.setCurrentOrder(OrderList.getSelectedIndex());
        
        updateForm();
        updateFeedback();
    }//GEN-LAST:event_listSelectionChanged

    private void checkPropertyChange(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_checkPropertyChange
        JCheckBox c = (JCheckBox)evt.getComponent();
        
        boolean value = c.isSelected();
        
        if(c.getName().equals("InsulationCheck")){
            session.getCurrentOrder().getRequirements().setInnerInsulation(value);
        }else if(c.getName().equals("ReinforcementCheck")){
            session.getCurrentOrder().getRequirements().setOuterReinforcement(value);
        }else if(c.getName().equals("ChemicalCheck")){
            session.getCurrentOrder().getRequirements().setChemicalResistance(value);
        }
        
        session.getCurrentOrder().updateOrder();
        updateTotal();
        updateFeedback();
    }//GEN-LAST:event_checkPropertyChange

    private void popupWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_popupWillBecomeInvisible
        JComboBox combo = (JComboBox)evt.getSource();
        
        int index = combo.getSelectedIndex();
        if(combo.getName().equals("GradeCombo")){
            session.getCurrentOrder().getRequirements().setPlasticGrade(index + 1);
        }else if(combo.getName().equals("ColourCombo")){
            session.getCurrentOrder().getRequirements().setColourPrint(index);
        }else if(combo.getName().equals("QuantityCombo")){
            session.getCurrentOrder().setQuantity(index + 1);
        }
        
        session.getCurrentOrder().updateOrder();
        updateTotal();
        updateFeedback();
    }//GEN-LAST:event_popupWillBecomeInvisible

    private void textKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textKeyPressed
        //Determine if the value is an integer. If it is then allow it.
        try{
            int val = Integer.parseInt(evt.getKeyChar() + "");
        }catch(NumberFormatException e){
            //Allow the . character
            if(evt.getKeyChar() != '.'){
                //Otherwise cancel the event.
                evt.consume();    
            }
        }
    }//GEN-LAST:event_textKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox ChemicalResistanceCheck;
    private javax.swing.JButton CloseButton;
    private javax.swing.JComboBox<String> ColourCombo;
    private javax.swing.JButton DeleteOrderButton;
    private javax.swing.JTextField DiameterText;
    private javax.swing.JTextPane FeedbackBox;
    private javax.swing.JCheckBox InsulationCheck;
    private javax.swing.JTextField LengthText;
    private javax.swing.JButton NewOrderButton;
    private javax.swing.JList<String> OrderList;
    private javax.swing.JComboBox<String> PlasticGradeCombo;
    private javax.swing.JComboBox<String> QuantityCombo;
    private javax.swing.JCheckBox ReinforcementCheck;
    private javax.swing.JTextPane SessionTotal;
    private javax.swing.JTextPane TotalBox;
    private javax.swing.JTextPane TotalSingleBox;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    // End of variables declaration//GEN-END:variables
}
