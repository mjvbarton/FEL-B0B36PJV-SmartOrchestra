/*
 * SmartOrchestra - semestral project for B0B36PJV and B0B36DBS subject at CTU-FEE
 * COPYRIGHT (c) Matej Barton 2019 (bartom47@fel.cvut.cz)
 */
package cz.cvut.fel.dbs.smartorchestra.gui;

import cz.cvut.fel.dbs.smartorchestra.AdminAccessible;
import cz.cvut.fel.dbs.smartorchestra.EventSettings;
import cz.cvut.fel.dbs.smartorchestra.UIControlled;
import cz.cvut.fel.dbs.smartorchestra.model.entities.SectionType;
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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
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
 * This class represents a GUI dialog for editing information about selected event.
 * <b>GUI in this dialog was created from scratch without using Designer</b>
 * @author Matěj Bartoň <i>(bartom47@fel.cvut.cz)</i>
 */
public class EventDetails extends JDialog implements UIControlled<EventSettings>, AdminAccessible{
    private JLabel caption;
    private EventSettings controller;
    private JTabbedPane content;
    
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
     * Initiates the class with a value of parent frame.
     * See {@link JDialog} for more information
     * @param owner - instance of {@code Frame}
     */
    public EventDetails(Frame owner) {
        super(owner, true);
        loadComponents();
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }
    
    /**
     * Modal runtime of the dialog
     * @return - {@code int} exit code, in this case 0
     */
    public int doModal() {
        setVisible(true);
        return 0;
    }
    
    /**
     * See {@link UIControlled} interface for more information.
     * @return the controller
     */
    @Override
    public EventSettings getUIController() {
        return controller;
    }
    
    /**
     * See {@link UIControlled} interface for more information.
     * @param controller 
     */
    @Override
    public void setUIController(EventSettings controller) {
        this.controller = controller;
    }

    /**
     * Clear info JLabels. Used by the controller for clearing the dialog form before validation.
     */
    public void clearInfos() {
        infoName.setText("");
        infoBeginsDate.setText("");
        infoEndsDate.setText("");
        infoAddrInstitution.setText("");
        infoAddrStreet.setText("");
        infoAddrHouseNumber.setText("");
        infoAddrTown.setText("");
        infoAddrZipCode.setText("");
    }

    /**
     * See {@link AdminAccessible} for more information.
     * @param isEnabled {@code boolean}
     */
    @Override
    public void enableAdminAccess(boolean isEnabled) {
        fieldName.setEditable(isEnabled);
        fieldBeginsDate.setEditable(isEnabled);
        fieldEndsDate.setEditable(isEnabled);
        fieldAddrInstitution.setEditable(isEnabled);
        fieldAddrStreet.setEditable(isEnabled);
        fieldAddrHouseNumber.setEditable(isEnabled);
        fieldAddrTown.setEditable(isEnabled);
        fieldAddrZipCode.setEditable(isEnabled);
        content.setEnabledAt(1, isEnabled);
        btnDeleteEvent.setEnabled(isEnabled);
    }
    
    /**
     * This inner class represents formfield. It is used just for creation of the dialog. 
     * @param <T> - class of the field
     */
    private class Formfield<T>{
        JLabel label;
        JLabel info;
        T field;
        
        /**
         * Initializes formfiel
         * @param parent - the {@code JPanel} that formfield belongs too
         * @param fieldName - a {@code String} value that will be passed to the {@code Formfield.label}
         * @param field - the field of selected class
         */
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
    
    /**
     * This inner class represents group of sections (available groups are: strings, winds, other}.
     */
    public class SectionGroup extends JPanel{
        private JButton caption;
        private HashMap<Sections, JCheckBox> sections;
        private JPanel content;
        private SectionType type;
        
        /**
         * This method initializes this class with given attributes.
         * @param sectionType - value of enum {@link SectionType}
         * @param controller - an {@link EventSettings} controller of its parent
         */
        public SectionGroup(SectionType sectionType, EventSettings controller){
            sections = new HashMap();
            setLayout(new BorderLayout());
            caption = new JButton();
            type = sectionType;
            caption.setText(sectionType.toString(true));
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
        
        /**
         * Loads sections from given {@code List}.
         * @param sections - list of sections {@code List<Sections>}
         */
        public void loadSections(List<Sections> sections){
            for(Sections section : sections){
                JCheckBox checkBox = new JCheckBox();
                checkBox.setText(section.getSectionname());
                this.sections.put(section, checkBox);
                content.add(checkBox);
            }
        }
        
        /**
         * Gives a hash map of sections and proper checkboxes.
         * @return a {@code HashMap} with {@link Sections} as the key and {@code JCheckBox} as value
         */
        public HashMap<Sections, JCheckBox> getSections(){
            return sections;
        }
        
        /**
         * Finds sections from given list in {@code SectionGroup.sections} and set their checkboxes selected {@code true} 
         * if their type matches {@code SectionGroup.type} value.
         * @param activeSections - {@code List} of given sections
         */
        public void checkSections(List<Sections> activeSections) {
            for(Sections section : activeSections){
                if(section.getSectiontype() == type){
                    sections.get(section).setSelected(true);
                }
            }
        }
        
        // Sets all sections in SectionGroup.sections the boolean value given
        private void checkSections(boolean isChecked){
            sections.forEach((section, checkBox) -> {
                checkBox.setSelected(isChecked);
            });
        }
    }
    
    // Loads components into this class
    private void loadComponents(){
        /* COMMON TAB */
        
        JPanel tabCommon = new JPanel();
        tabCommon.setAlignmentX(LEFT_ALIGNMENT);
        tabCommon.setLayout(new BoxLayout(tabCommon, BoxLayout.PAGE_AXIS));
        
                               
        Formfield<JTextField> name = new Formfield(tabCommon, "Název akce:", new JTextField(15));
        fieldName = name.field;
        infoName = name.info;
        
        Formfield<JTextField> begins = new Formfield(tabCommon, "Začátek:", new JTextField(10));
        infoBeginsDate = begins.info;
        fieldBeginsDate = begins.field;
        
        // Sets the value of fieldBeginsDate if the field is empty
        fieldBeginsDate.addMouseListener(new MouseListener(){
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
                
            @Override
            public void mouseClicked(MouseEvent e) {
                if(fieldBeginsDate.getText().isEmpty()){
                    DateFormat f = new SimpleDateFormat("dd.MM.yyyy HH:mm");
                    fieldBeginsDate.setText(f.format(new Date()));
                }
            }
        });
        
        
        Formfield<JTextField> ends = new Formfield(tabCommon, "Konec:", new JTextField(10));
        infoEndsDate = ends.info;
        fieldEndsDate = ends.field;
        fieldEndsDate.addMouseListener(new MouseListener(){
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
                
            // Sets the value of fieldEndsDate if the field is empty
            @Override
            public void mouseClicked(MouseEvent e) {
                if(fieldEndsDate.getText().isEmpty() && fieldBeginsDate.getText().isEmpty()){
                    DateFormat f = new SimpleDateFormat("dd.MM.yyyy HH:mm");
                    fieldBeginsDate.setText(f.format(new Date()));
                }
                if(fieldEndsDate.getText().isEmpty()){
                    fieldEndsDate.setText(fieldBeginsDate.getText());
                }
            }
        });
            
        
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
        
        // Implementing checking all sections
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
        
        content = new JTabbedPane();
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
        
        // Connecting the action to class method
        btnSubmit.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                btnSubmitClicked(e);
            }
        });
        btnCancel = new JButton("Zrušit");
        
        // Connecting the action to class method
        btnCancel.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                btnCancelClicked(e);
            }
        });
        btnDeleteEvent = new JButton("Smazat událost");
        
        // Connecting the action to class method
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
    }
    
    /**
     * Method of {@code EventDetails.btnSubmit} actionListener. 
     * Initiates saving process at the controller.
     * @param e - {@code ActionEvent} object
     */
    protected void btnSubmitClicked(ActionEvent e){
        controller.saveEvent();
    }
    
    /**
     * Method of {@code EventDetails.btnCancel} actionListener. 
     * Closes the dialog.
     * @param e - {@code ActionEvent} object
     */
    protected void btnCancelClicked(ActionEvent e){
        dispose();
    }
    
    /**
     * Method of {@code EventDetails.btnDelete} actionListener.
     * Initiates deletion process at the controller.
     * @param e - {@code ActionEvent} object
     */
    protected void btnDeleteEventClicked(ActionEvent e){
        controller.deleteEvent();
    }

    /**
     * Returns field of the event name.
     * @return - a {@code JTextField} object
     */
    public JTextField getFieldName() {
        return fieldName;
    }

    /**
     * Returns field of the event begginning date.
     * @return - a {@code JTextField} object
     */
    public JTextField getFieldBeginsDate() {
        return fieldBeginsDate;
    }

    /**
     * Returns field of the event beginning time.
     * @return - a {@code JTextField} object
     * @deprecated As of SmartOrchestra 1.0, because the string value of mixed date and time is used in {@link #getFieldBeginsDate()}. The method is left there for further development.
     */
    @Deprecated
    public JTextField getFieldBeginsTime() {
        return fieldBeginsTime;
    }

    /**
     * Returns field of the event end date.
     * @return - a {@code JTextField} object
     */
    public JTextField getFieldEndsDate() {
        return fieldEndsDate;
    }

    /**
     * Returns field of the event end time.
     * @return - a {@code JTextField} object
     * @deprecated As of SmartOrchestra 1.0, because the string value of mixed date and time is used in {@link #getFieldEndsDate()}. The method is left there for further development.
     */
    @Deprecated
    public JTextField getFieldEndsTime() {
        return fieldEndsTime;
    }

    /**
     * Returns field of the event address - institution (e. g. The Royal Albert Hall)
     * @return - a {@code JTextField} object
     */
    public JTextField getFieldAddrInstitution() {
        return fieldAddrInstitution;
    }

    /**
     * Returns field of the event address - street.
     * @return - a {@code JTextField} object
     */
    public JTextField getFieldAddrStreet() {
        return fieldAddrStreet;
    }

    /**
     * Returns field of the event address - house number.
     * @return - a {@code JTextField} object
     */
    public JTextField getFieldAddrHouseNumber() {
        return fieldAddrHouseNumber;
    }

    /**
     * Returns field of the event address - town.
     * @return - a {@code JTextField} object
     */
    public JTextField getFieldAddrTown() {
        return fieldAddrTown;
    }

    /**
     * Returns field of the event address - zipcode.
     * @return - a {@code JTextField} object
     */
    public JTextField getFieldAddrZipCode() {
        return fieldAddrZipCode;
    }

    /**
     * Returns info label for the event name field. This could be useful for passing error
     * information during form validation.
     * @return - a {@code JLabel} object
     */
    public JLabel getInfoName() {
        return infoName;
    }

    /**
     * Returns info label for the event beginning date field. This could be useful for passing error
     * information during form validation.
     * @return - a {@code JLabel} object
     */
    public JLabel getInfoBeginsDate() {
        return infoBeginsDate;
    }

    /**
     * Returns info label for the event beginning time field. This could be useful for passing error
     * information during form validation.
     * @return - a {@code JLabel} object
     * @deprecated As of SmartOrchestra 1.0, because the string value of mixed date and time is used in {@link #getFieldBeginsDate()}. Use {@link #getInfoBeginsDate() } instead.
     */
    @Deprecated
    public JLabel getInfoBeginsTime() {
        return infoBeginsTime;
    }

    /**
     * Returns info label for the ends date field. This could be useful for passing error
     * information during form validation.
     * @return - a {@code JLabel} object
     */
    public JLabel getInfoEndsDate() {
        return infoEndsDate;
    }

    /**
     * Returns info label for the event ends time field. This could be useful for passing error
     * information during form validation.
     * @return - a {@code JLabel} object
     * @deprecated As of SmartOrchestra 1.0, because the string value of mixed date and time is used in {@link #getFieldEndsDate()}. Use {@link #getInfoEndsDate() } instead.
     */
    @Deprecated
    public JLabel getInfoEndsTime() {
        return infoEndsTime;
    }

    /**
     * Returns info label for the event address - instituion (e. g. value Royal Albert Hall) field. This could be useful for passing error
     * information during form validation.
     * @return - a {@code JLabel} object
     */
    public JLabel getInfoAddrInstitution() {
        return infoAddrInstitution;
    }

    /**
     * Returns info label for the event address - street field. This could be useful for passing error
     * information during form validation.
     * @return - a {@code JLabel} object
     */
    public JLabel getInfoAddrStreet() {
        return infoAddrStreet;
    }

    /**
     * Returns info label for the event address - house number field. This could be useful for passing error
     * information during form validation.
     * @return - a {@code JLabel} object
     */
    public JLabel getInfoAddrHouseNumber() {
        return infoAddrHouseNumber;
    }

    /**
     * Returns info label for the event address - town field. This could be useful for passing error
     * information during form validation.
     * @return - a {@code JLabel} object
     */
    public JLabel getInfoAddrTown() {
        return infoAddrTown;
    }

    /**
     * Returns info label for the event address - zipcode field. This could be useful for passing error
     * information during form validation.
     * @return - a {@code JLabel} object
     */
    public JLabel getInfoAddrZipCode() {
        return infoAddrZipCode;
    }

    /**
     * Gets pointer to the section group of {@link SectionType#STRINGS}
     * @return - a {@link SectionGroup} object
     */
    public SectionGroup getGroupStrings() {
        return groupStrings;
    }

    /**
     * Gets pointer to the section group of {@link SectionType#WINDS}
     * @return - a {@link SectionGroup} object
     */
    public SectionGroup getGroupWinds() {
        return groupWinds;
    }

    /**
     * Gets pointer to the section group of {@link SectionType#OTHER}
     * @return - a {@link SectionGroup} object
     */
    public SectionGroup getGroupOther() {
        return groupOther;
    }

    /**
     * Gets the delete event button.
     * @return - a {@code JButton} object
     */
    public JButton getBtnDeleteEvent() {
        return btnDeleteEvent;
    }
}
