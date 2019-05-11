/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.dbs.smartorchestra.gui;

import cz.cvut.fel.dbs.smartorchestra.EventSettings;
import cz.cvut.fel.dbs.smartorchestra.UIControlled;
import cz.cvut.fel.dbs.smartorchestra.model.entities.Sections;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

/**
 *
 * @author Matěj Bartoň
 */
public class EventDetails extends JDialog implements UIControlled<EventSettings>{
    private JLabel caption;
    private EventSettings controller;
    
    /* Common - text fields */
    protected JTextField fieldName;
    protected JTextField fieldBeginsDate;
    protected JTextField fieldBeginsTime;
    protected JTextField fieldEndsDate;
    protected JTextField fieldEndsTime;
    protected JTextField fieldAddrInstitution;
    protected JTextField fieldAddrStreet;
    protected JTextField fieldAddrHouseNumber;
    protected JTextField fieldAddrTown;
    protected JTextField fieldAddrZipCode;
    
    /* Common - error labels */
    protected JLabel infoName;
    protected JLabel infoBeginsDate;
    protected JLabel infoBeginsTime;
    protected JLabel infoEndsDate;
    protected JLabel infoEndsTime;
    protected JLabel infoAddrInstitution;
    protected JLabel infoAddrStreet;
    protected JLabel infoAddrHouseNumber;
    protected JLabel infoAddrTown;
    protected JLabel infoAddrZipCode;
    
    /* Section groups */
    private SectionGroup groupStrings;
    private SectionGroup groupWinds;
    private SectionGroup groupOther;
    
    private JButton checkAllSections;
    private boolean invertChecking;
    
    private JButton btnSubmit;
    private JButton btnCancel;
    private JButton btnDeleteEvent;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                EventDetails e = new EventDetails(null);
                if(e.doModal() == UserDetails.SAVE_DETAILS) {

                }
            }
        });
    }
    
    

    public EventDetails(Frame owner) {
        super(owner, true);
        loadComponents();
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }

    private int doModal() {
        setVisible(true);
        return 0;
    }
    
    @Override
    public EventSettings getUIController() {
        return controller;
    }

    @Override
    public void setUIController(EventSettings controller) {
        this.controller = new EventSettings(this);
    }
    
    private class Formfield<T>{
        JLabel label;
        JLabel info;
        T field;
        
        public Formfield(JPanel parent, String fieldName, T field){
            this.field = field;
                                    
            label = new JLabel();
            label.setText(fieldName);
            label.setAlignmentX(LEFT_ALIGNMENT);
            
            info = new JLabel();
            Font f = info.getFont();
            info.setFont(f.deriveFont(f.getStyle() & ~Font.BOLD));
            info.setAlignmentX(LEFT_ALIGNMENT);
            info.setForeground(Color.red);
            info.setHorizontalAlignment(SwingConstants.LEFT);
            
            JPanel c = new JPanel();
            c.setLayout(new FlowLayout(FlowLayout.LEFT));
            
            
            c.add(this.label);
            c.add((Component) this.field);
            c.add(this.info);
            
            parent.add(c);
        }
    }
       
    private enum SectionType{
        STRINGS(0, "smyčce"),
        WINDS(1, "dechy"),
        OTHER(2, "ostatní");
        
        private final int index;
        private final String name;
        
        private SectionType(int index, String name){
            this.index = index;
            this.name = name;
        }
        
        @Override
        public String toString() {
            return name;
        }       
    }
    
    private class SectionGroup extends JPanel{
        private JButton caption;
        private List<JCheckBox> sections;
        private JPanel content;
                        
        public SectionGroup(SectionType sectionType, EventSettings controller){
            sections = new ArrayList();
            setLayout(new BorderLayout());
            caption = new JButton();
            caption.setText(sectionType.toString());
            caption.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    checkSections(true);
                    checkAllSections.setText("zrušit výběr");
                    invertChecking = true;
                }
            });
            content = new JPanel();
            content.setLayout(new BoxLayout(content, BoxLayout.PAGE_AXIS));
            
            add(caption, BorderLayout.NORTH);
            add(new JScrollPane(content), BorderLayout.CENTER);
        }
        
        public void loadSections(List<Sections> sections){
            for(Sections section : sections){
                JCheckBox checkBox = new JCheckBox();
                checkBox.setText(section.getSectionname());
                this.sections.add(checkBox);
                content.add(checkBox);
            }
        }
        
        public List<JCheckBox> getSections(){
            return sections;
        }
        
        public void checkSections(boolean isSelected) {
            for(JCheckBox section : sections){
                section.setSelected(isSelected);
            }
        }        
    }
    
    private void loadComponents(){
        /* COMMON TAB */
        
        JPanel tabCommon = new JPanel();
        tabCommon.setAlignmentX(LEFT_ALIGNMENT);
        tabCommon.setLayout(new BoxLayout(tabCommon, BoxLayout.PAGE_AXIS));
        
                               
        Formfield<JTextField> name = new Formfield(tabCommon, "Název akce:", new JTextField(15));
        fieldName = name.field;
        infoName = name.info;
        
        Formfield<JPanel> begins = new Formfield(tabCommon, "Začátek:", new JPanel());
        fieldBeginsDate = new JTextField(6);
        fieldBeginsTime = new JTextField(3);
        begins.field.add(fieldBeginsDate);
        begins.field.add(fieldBeginsTime);
        
        Formfield<JPanel> ends = new Formfield(tabCommon, "Konec:", new JPanel());
        fieldEndsDate = new JTextField(6);
        fieldEndsTime = new JTextField(3);
        ends.field.add(fieldEndsDate);  
        ends.field.add(fieldEndsTime);
        
        Formfield<JTextField> inst = new Formfield(tabCommon, "Název instituce:", new JTextField(15));
        fieldAddrInstitution = inst.field;
        infoAddrInstitution = inst.info;
        
        Formfield<JTextField> street = new Formfield(tabCommon, "Ulice:", new JTextField(12));
        fieldAddrStreet = street.field;
        infoAddrStreet = street.info;
        
        Formfield<JTextField> hn = new Formfield(tabCommon, "Č.P.:", new JTextField(5));
        fieldAddrHouseNumber = hn.field;
        fieldAddrHouseNumber.setToolTipText("Číslo popisné");
        infoAddrHouseNumber = hn.info;
        
        Formfield<JTextField> town = new Formfield(tabCommon, "Město:", new JTextField(10));
        fieldAddrTown = town.field;
        infoAddrTown = town.info;
        
        Formfield<JTextField> zip = new Formfield(tabCommon, "PSČ:", new JTextField(5));
        fieldAddrZipCode = zip.field;
        fieldAddrZipCode.setToolTipText("Poštovní směrovací číslo");
        infoAddrZipCode = zip.info;
        
        /* SECTION TAB */
        JPanel tabSections = new JPanel();
        tabSections.setLayout(new BorderLayout());
                       
        JPanel sectionGroups = new JPanel();
        sectionGroups.setLayout(new GridLayout(0,3));
        
        
        groupStrings = new SectionGroup(SectionType.STRINGS, controller);
        groupWinds = new SectionGroup(SectionType.WINDS, controller);
        groupOther = new SectionGroup(SectionType.OTHER, controller);
        sectionGroups.add(groupStrings);
        sectionGroups.add(groupWinds);
        sectionGroups.add(groupOther);
        
        checkAllSections = new JButton();
        checkAllSections.setText("vyber všechny sekce");
        checkAllSections.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(invertChecking){
                    checkAllSections.setText("vyber všechny sekce");
                    invertChecking = false;
                } else {
                    checkAllSections.setText("zrušit výběr");
                    invertChecking = true;
                }       
                groupStrings.checkSections(invertChecking);
                groupWinds.checkSections(invertChecking);
                groupOther.checkSections(invertChecking);
            }
        });
        
        
        tabSections.add(checkAllSections, BorderLayout.NORTH);
        tabSections.add(sectionGroups, BorderLayout.CENTER);
        
        JTabbedPane content = new JTabbedPane();
        content.addTab("Obecné", tabCommon);
        content.addTab("Sekce", tabSections);
                
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(content, BorderLayout.CENTER);
        
        caption = new JLabel();
        caption.setText("Detaily události");
        caption.setFont(caption.getFont().deriveFont(14F));
        caption.setPreferredSize(new Dimension(getSize().width, 50));
        caption.setHorizontalAlignment(SwingConstants.CENTER);
        getContentPane().add(caption, BorderLayout.NORTH);
        
        btnSubmit = new JButton("Uložit");
        btnSubmit.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                btnSubmitClicked(e);
            }
        });
        btnCancel = new JButton("Zrušit");
        btnCancel.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                btnCancelClicked(e);
            }
        });
        btnDeleteEvent = new JButton("Smazat událost");
        btnDeleteEvent.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                btnDeleteEventClicked(e);
            }
        });
        
        JPanel btnGroup = new JPanel();
        btnGroup.setLayout(new FlowLayout(FlowLayout.RIGHT));
        btnGroup.add(btnSubmit);
        btnGroup.add(btnCancel);
        btnGroup.add(btnDeleteEvent);
        
        getContentPane().add(btnGroup, BorderLayout.SOUTH);       
        pack();
        setResizable(false);
        setSize(new Dimension(480,410));
        
        // TEST DATA
        List<Sections> secStrings = new ArrayList();
        secStrings.add(new Sections(1, "I.housle", SectionType.STRINGS.toString(), true));
        secStrings.add(new Sections(1, "II.housle", SectionType.STRINGS.toString(), true));
        secStrings.add(new Sections(1, "Viola", SectionType.STRINGS.toString(), true));
        groupStrings.loadSections(secStrings);
        
        List<Sections> secWinds = new ArrayList();
        secWinds.add(new Sections(1, "Hoboj", SectionType.STRINGS.toString(), true));
        secWinds.add(new Sections(1, "Flétna", SectionType.STRINGS.toString(), true));
        secWinds.add(new Sections(1, "Trubka", SectionType.STRINGS.toString(), true));
        groupWinds.loadSections(secWinds);
        
        List<Sections> secOther = new ArrayList();
        secOther.add(new Sections(1, "Klavír", SectionType.STRINGS.toString(), true));
        secOther.add(new Sections(1, "Sbor", SectionType.STRINGS.toString(), true));
        secOther.add(new Sections(1, "Perkuse", SectionType.STRINGS.toString(), true));
        groupOther.loadSections(secOther);
        
    }
    
    protected void btnSubmitClicked(ActionEvent e){
        
    }
    
    protected void btnCancelClicked(ActionEvent e){
        dispose();
    }
    
    protected void btnDeleteEventClicked(ActionEvent e){
        
    }
    
}
