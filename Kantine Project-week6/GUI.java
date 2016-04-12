import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.*;

import java.awt.Font;
import java.awt.event.*;
import java.awt.GridLayout;

/**
 * Write a description of class GUI here.
 * 
 * @author Sander van Kasteel
 * @version 0.00001
 */
public class GUI //extends JFrame
{
    private JPanel contentPane;
    private JTextField textFieldMinArtikelenPerSoort;
    private JTextField textFieldMaxArtikelenPerSoort;
    private JTextField textFieldMinArtikelenPerPersoon;
    private JTextField textFieldMaxArtikelenPerPersoon;
    private JTextField textFieldKansDocent;
    private JTextField textFieldKansStudent;
    private JTextField textFieldKansMedewerker;
    private JTable table;
    private JSlider sliderMinArtikelenPerPersoon = new JSlider(0,4);
    private JSlider sliderMaxArtikelenPerPersoon = new JSlider(0,4);
    private JSlider sliderMinArtikelenPerSoort = new JSlider(0,10000);
    private JSlider sliderMaxArtikelenPerSoort = new JSlider(0,20000);
    private KantineSimulatie kantineSimulatie; //= new KantineSimulatie();
    private JFrame frame;
    private JFrame frameKansen = new JFrame("Kans instellingen");
    private JList list;
    private JTextField textField;
    private JTextField textField_1;
    private JScrollPane scrollPane;
    private JScrollPane scrollPaneTextArea;
    private JTextField textFieldDagen;
    private JTextArea textArea = new JTextArea(10, 10);
    private GUI gui = this;
    
    /**
     * Constructor for objects of class GUI
     */
    public GUI()
    {
        kantineSimulatie = new KantineSimulatie();
        initGUI();
        setDefaultValues();
    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    private void initGUI() 
    {
        JFrame frame = new JFrame();
        frame.setTitle("JKantine");
        frame.setAlwaysOnTop(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        
        frame.setBounds(100, 100, 550, 450);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        frame.setContentPane(contentPane);
        contentPane.setLayout(null);
        
        textFieldKansMedewerker = new JTextField();
        textFieldKansStudent = new JTextField();
        textFieldKansDocent = new JTextField();
        
        JButton btnSimuleer = new JButton("Simuleer");
        btnSimuleer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (kantineSimulatie.getKantine().getTotaalArtikelen() == 0) {
                    textArea.setText("");
                    textArea.append("Helaas.. je kan geen simulatie draaien als je geen \nartikelen hebt om te verkopen.");
                } else {
                    textArea.setText("");
                    // get TextFieldInput
                    double prijzen[] = new double[]{1.50, 2.10, 1.65, 1.65};
                    //String artikelNamen 
                    int minArtikelenPerSoort = sliderMinArtikelenPerSoort.getValue();
                    int maxArtikelenPerSoort = sliderMaxArtikelenPerSoort.getValue();
                    int minArtikelenPerPersoon = sliderMinArtikelenPerPersoon.getValue();
                    int maxArtikelenPerPersoon = sliderMaxArtikelenPerPersoon.getValue();
                    // Simuleer dagen
                    Integer dagen = new Integer(textFieldDagen.getText());
                    
                    kantineSimulatie.simuleer(dagen, prijzen, minArtikelenPerSoort, maxArtikelenPerSoort, minArtikelenPerPersoon, maxArtikelenPerPersoon, gui);
                }
            }
        });
        btnSimuleer.setBounds(6, 6, 117, 29);
        contentPane.add(btnSimuleer);
        
        JLabel lblDagen = new JLabel("Dagen");
        lblDagen.setBounds(135, 11, 40, 16);
        contentPane.add(lblDagen);
        
        textFieldDagen = new JTextField();
        textFieldDagen.setBounds(187, 5, 37, 28);
        contentPane.add(textFieldDagen);
        textFieldDagen.setColumns(10);
        
        JButton button = new JButton("+");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dialogToevoegen(); 
            }
        });
        button.setBounds(408, 6, 26, 29);
        contentPane.add(button);
        
        JButton button_1 = new JButton("-");
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int weg = getTable().getSelectedRow();
                //System.out.println(getTable().getSelectedRows().length);
                if(getTable().getSelectedRows().length > 1)
                    kantineSimulatie.getKantine().setVerwijderArtikel(getTable().getSelectedRows());
                else
                    kantineSimulatie.getKantine().setVerwijderArtikel(weg);
                refreshArtikelTable();
            }
        });
        button_1.setBounds(446, 6, 26, 29);
        contentPane.add(button_1);
        
        textFieldMinArtikelenPerSoort = new JTextField();
        textFieldMinArtikelenPerSoort.setText("100");
        /* textFieldMinArtikelenPerSoort.addInputMethodListener(new InputMethodListener() {
            public void caretPositionChanged(InputMethodEvent event) {
            }
            public void inputMethodTextChanged(InputMethodEvent event) {
                System.out.println(sliderMinArtikelenPerSoort.getMaximum());
                if (Integer.parseInt(textFieldMinArtikelenPerSoort.getText()) > sliderMinArtikelenPerSoort.getMaximum()){
                    sliderMinArtikelenPerSoort.setMaximum(Integer.parseInt(textFieldMinArtikelenPerSoort.getText()));
                    sliderMinArtikelenPerSoort.setValue(Integer.parseInt(textFieldMinArtikelenPerSoort.getText()));
                 }
            }
        }); */
        textFieldMinArtikelenPerSoort.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                 if (Integer.parseInt(textFieldMinArtikelenPerSoort.getText()) > sliderMinArtikelenPerSoort.getMaximum()){
                    sliderMinArtikelenPerSoort.setValue(Integer.parseInt(textFieldMinArtikelenPerSoort.getText()));                  
                    sliderMinArtikelenPerSoort.setMaximum(Integer.parseInt(textFieldMinArtikelenPerSoort.getText()));
                    sliderMinArtikelenPerSoort.repaint();
                 }
            }
        });
        

        textFieldMinArtikelenPerSoort.setBounds(16, 70, 100, 28);
        contentPane.add(textFieldMinArtikelenPerSoort);
        textFieldMinArtikelenPerSoort.setColumns(10);
        
        JLabel labelMinPerSoort = new JLabel("Min artikelen per soort");
        labelMinPerSoort.setBounds(16, 47, 156, 16);
        contentPane.add(labelMinPerSoort);
        
        sliderMinArtikelenPerSoort.setSnapToTicks(true);
        sliderMinArtikelenPerSoort.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                Integer currentVal = sliderMinArtikelenPerSoort.getValue();
                textFieldMinArtikelenPerSoort.setText(currentVal.toString());
            }
        });
        sliderMinArtikelenPerSoort.setBounds(6, 100, 117, 29);
        contentPane.add(sliderMinArtikelenPerSoort);
        
        textFieldMaxArtikelenPerSoort = new JTextField();
        textFieldMaxArtikelenPerSoort.setText("100");
        textFieldMaxArtikelenPerSoort.setColumns(10);
        textFieldMaxArtikelenPerSoort.setBounds(16, 174, 100, 28);
        contentPane.add(textFieldMaxArtikelenPerSoort);
        
        JLabel lblAantalStudenten = new JLabel("Max artikelen per soort");
        lblAantalStudenten.setBounds(16, 151, 156, 16);
        contentPane.add(lblAantalStudenten);
        
        sliderMaxArtikelenPerSoort.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                if (sliderMinArtikelenPerSoort.getValue() > sliderMaxArtikelenPerSoort.getValue()) {
                     sliderMinArtikelenPerSoort.setValue(sliderMaxArtikelenPerSoort.getValue());
                }
                
                Integer currentVal = sliderMaxArtikelenPerSoort.getValue();
                textFieldMaxArtikelenPerSoort.setText(currentVal.toString());
            }
        });
        sliderMaxArtikelenPerSoort.setBounds(6, 209, 117, 29);
        contentPane.add(sliderMaxArtikelenPerSoort);
        
        textFieldMinArtikelenPerPersoon = new JTextField();
        textFieldMinArtikelenPerPersoon.setText("100");
        textFieldMinArtikelenPerPersoon.setColumns(10);
        textFieldMinArtikelenPerPersoon.setBounds(215, 70, 37, 28);
        contentPane.add(textFieldMinArtikelenPerPersoon);
        
        JLabel lblAantalMedewerkers = new JLabel("Min artikelen per persoon");
        lblAantalMedewerkers.setBounds(220, 47, 161, 16);
        contentPane.add(lblAantalMedewerkers);
        
        //final JSlider sliderMinArtikelenPerPersoon = new JSlider();
        sliderMinArtikelenPerPersoon.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                if (sliderMinArtikelenPerPersoon.getValue() > sliderMaxArtikelenPerPersoon.getValue()) {
                   sliderMinArtikelenPerPersoon.setValue(sliderMaxArtikelenPerPersoon.getValue());
                } else {
                Integer currentVal = sliderMinArtikelenPerPersoon.getValue();
                textFieldMinArtikelenPerPersoon.setText(currentVal.toString());
                }
            }
        });
        sliderMinArtikelenPerPersoon.setBounds(215, 100, 117, 29);
        contentPane.add(sliderMinArtikelenPerPersoon);
        
        textFieldMaxArtikelenPerPersoon = new JTextField();
        textFieldMaxArtikelenPerPersoon.setText("100");
        textFieldMaxArtikelenPerPersoon.setColumns(10);
        textFieldMaxArtikelenPerPersoon.setBounds(215, 174, 37, 28);
        contentPane.add(textFieldMaxArtikelenPerPersoon);
        
        JLabel lblAantalKantineMedewerkers = new JLabel("Max artikelen per persoon");
        lblAantalKantineMedewerkers.setBounds(215, 151, 166, 16);
        contentPane.add(lblAantalKantineMedewerkers);
        
        //final JSlider sliderMaxArtikelenPerPersoon = new JSlider();
        sliderMaxArtikelenPerPersoon.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                // A fix to redraw the minSlider if minSlider equals the same value as maxSlider
                if (sliderMinArtikelenPerPersoon.getValue() >= sliderMaxArtikelenPerPersoon.getValue()) {
                    int oldValue = sliderMaxArtikelenPerPersoon.getValue();
                    
                    Integer currentVal = sliderMaxArtikelenPerPersoon.getValue();
                    textFieldMaxArtikelenPerPersoon.setText(currentVal.toString());
                    
                    sliderMinArtikelenPerPersoon.setValue(oldValue);
                    
                } 
                Integer currentVal = sliderMaxArtikelenPerPersoon.getValue();
                textFieldMaxArtikelenPerPersoon.setText(currentVal.toString());
            }
        });
        sliderMaxArtikelenPerPersoon.setBounds(215, 209, 117, 29);
        contentPane.add(sliderMaxArtikelenPerPersoon);
        
        textArea.setBounds(16, 240, 365, 176);
        textArea.setWrapStyleWord(true);
        JScrollPane scrollPaneTextArea = new JScrollPane(textArea);
        scrollPaneTextArea.setBounds(16,240,365,176);
        contentPane.add(scrollPaneTextArea);

        
        this.scrollPane = new JScrollPane();
        this.scrollPane.setBounds(404, 47, 140, 375);
        contentPane.add(this.scrollPane);
        
        refreshArtikelTable(); // Is het zelfde als opzetten.
        
        JButton btnKansen = new JButton("Kansen");
            btnKansen.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    dialogKansen();
                }
            });
        btnKansen.setBounds(250, 6, 117, 29);
        contentPane.add(btnKansen);
        
        frame.setVisible(true);
    }
    
    private void dialogToevoegen()
    {
        final JFrame frame = new JFrame("Toevoegen artikel");
        frame.setAlwaysOnTop(true);
        frame.setBounds(100, 100, 278, 168);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        frame.setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblOmschrijving = new JLabel("Omschrijving");
        lblOmschrijving.setBounds(6, 38, 94, 16);
        contentPane.add(lblOmschrijving);
        
        JLabel lblPrijs = new JLabel("Prijs");
        lblPrijs.setBounds(6, 72, 61, 16);
        contentPane.add(lblPrijs);
        
        textField = new JTextField();
        textField.setBounds(106, 32, 134, 28);
        contentPane.add(textField);
        textField.setColumns(10);
        
        textField_1 = new JTextField();
        textField_1.setBounds(106, 66, 134, 28);
        contentPane.add(textField_1);
        textField_1.setColumns(10);
        
        JButton btnToevoegen = new JButton("Toevoegen");
        btnToevoegen.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String naam =  textField.getText(); 
                double prijs = Double.parseDouble(textField_1.getText());
                kantineSimulatie.getKantine().setNieuwArtikel(naam, prijs, 250);
                frame.dispose();
                refreshArtikelTable();
            }
        });
        btnToevoegen.setBounds(6, 100, 117, 29);
        contentPane.add(btnToevoegen);
        
        JButton btnAnnuleren = new JButton("Annuleren");
        btnAnnuleren.setBounds(123, 100, 117, 29);
        btnAnnuleren.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    frame.dispose();
                }
            });
        contentPane.add(btnAnnuleren);
        
        JLabel lblToevoegenArtikel = new JLabel("Toevoegen artikel");
        lblToevoegenArtikel.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
        lblToevoegenArtikel.setBounds(6, 0, 251, 28);
        contentPane.add(lblToevoegenArtikel);
        frame.setVisible(true);
    }
    private int[] returnToPercentages(int[] x)
    {
        int total = 0;

        for(int i = 0; i < x.length; i++) total = total + x[i];
        for(int i = 0; i < x.length; i++) x[i] = Math.round(x[i] / ((float)total/100));

        return x;
    }
    private void setKansen()
    {
        String student = textFieldKansStudent.getText();
        String docent = textFieldKansDocent.getText();
        String medewerker = textFieldKansMedewerker.getText();
            
            try{
                int[] i = {new Integer(student), new Integer(docent), new Integer(medewerker)};
                int[] kansen = returnToPercentages(i);
                
                kantineSimulatie.setKansen(kansen);
                textFieldKansStudent.setText(new Integer(kansen[0]).toString());
                textFieldKansDocent.setText(new Integer(kansen[1]).toString());
                textFieldKansMedewerker.setText(new Integer(kansen[2]).toString());
                frameKansen.dispose();
            }
            catch(NumberFormatException e){
                JLabel foutLabel = new JLabel("Alleen cijfers aub");
                //foutlabel.setForeground(Color.blue);
                foutLabel.setBounds(110, 220, 150, 15);
                contentPane.add(foutLabel);
                frameKansen.repaint();
            }
    }
    private void dialogKansen()
    {
        //JFrame frameKansen = new JFrame("Kans instellingen");
        frameKansen.setBounds(100, 100, 265, 300);
        frameKansen.setAlwaysOnTop(true);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        frameKansen.setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblKansenInstellingen = new JLabel("Kansen instellingen");
        lblKansenInstellingen.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
        lblKansenInstellingen.setBounds(6, 6, 207, 27);
        contentPane.add(lblKansenInstellingen);
        
        textFieldKansStudent.setBounds(6, 73, 50, 28);
        contentPane.add(textFieldKansStudent);
        textFieldKansStudent.setColumns(10);
        
        JLabel lblKansOpStudent = new JLabel("% Kans op Student");
        lblKansOpStudent.setBounds(6, 45, 130, 16);
        contentPane.add(lblKansOpStudent);
        
        JLabel lblKansOpDocent = new JLabel("% Kans op docent");
        lblKansOpDocent.setBounds(6, 113, 130, 16);
        contentPane.add(lblKansOpDocent);
        
        textFieldKansDocent.setBounds(6, 141, 50, 28);
       
        contentPane.add(textFieldKansDocent);
        textFieldKansDocent.setColumns(10);
        
        JLabel lblKansOpKantinemedewerkers = new JLabel("% Kans op kantinemedewerkers");
        lblKansOpKantinemedewerkers.setBounds(6, 181, 200, 16);
        contentPane.add(lblKansOpKantinemedewerkers);
        
        //JTextField textFieldKansKantine = new JTextField();
        textFieldKansMedewerker.setBounds(6, 209, 50, 28);
        
        contentPane.add(textFieldKansMedewerker);
        textFieldKansMedewerker.setColumns(10);
        
        JButton btnOk = new JButton("Ok");
        btnOk.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    setKansen();
                    //frame.dispose();
                }
            });
        btnOk.setBounds(135, 243, 117, 29);
        contentPane.add(btnOk);
        
        JButton btnAnnuleren = new JButton("Annuleren");
        btnAnnuleren.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    frameKansen.dispose();
                    
                }
            });
        btnAnnuleren.setBounds(6, 243, 117, 29);
        contentPane.add(btnAnnuleren);   
        
        // set default waarden
        textFieldKansStudent.setText(Integer.toString(kantineSimulatie.getKansStudent()));
        textFieldKansDocent.setText(Integer.toString(kantineSimulatie.getKansDocent()));
        textFieldKansMedewerker.setText(Integer.toString(kantineSimulatie.getKansMedewerker()));
        
        frameKansen.setVisible(true);
    }
    
    private void closeKansenDialog()
    {
        frame = null;
    }
    
    private JTable getTable(){
        return table;
    }
    
    public JScrollPane getScrollPane() 
    {
        return scrollPane;
    }
    
    public JScrollPane getScrollPaneTextArea()
    {
        return scrollPaneTextArea;
    }
    
    public GUI getGUI()
    {
        return this;
    }
    
    public JTextArea getTextArea()
    {
        return textArea;
    }
    
    private void setDefaultValues() 
    {           
        sliderMinArtikelenPerPersoon.setValue(kantineSimulatie.getMinArtikelPersoon());
        sliderMaxArtikelenPerPersoon.setValue(kantineSimulatie.getMaxArtikelPersoon());
        sliderMinArtikelenPerSoort.setValue(kantineSimulatie.getMinArtikelSoort());
        sliderMaxArtikelenPerSoort.setValue(kantineSimulatie.getMaxArtikelSoort()); 
        textFieldDagen.setText("2");
    }
    
    private void refreshArtikelTable()
    {
        this.table = null;
        Object[][] data = kantineSimulatie.getKantine().getLijstArtikelen();
        String[] columnNames = {"Omschrijving", "Prijs"};
        table = new JTable(data, columnNames);
        getScrollPane().setViewportView(table);
    }
}
