
package com.bdb.util;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 *
 * @author bruce
 */
public class HicLampCommander extends JPanel {
    private final String mLabel;
    private final boolean mHasText;
    private final boolean mHasLed;
    private final JToggleButton mTextButton = new JToggleButton("TEXT");
    private final JToggleButton mLedButton = new JToggleButton("LED");
    
    public HicLampCommander(String label, boolean hasText, boolean hasLed) {
        setBorder(new CompoundBorder(new LineBorder(Color.BLACK), new EmptyBorder(5,5,5,5)));
        
        mLabel = label;
        mHasText = hasText;
        mHasLed = hasLed;
        GridLayout layout = new GridLayout(0,1,0,5);
        setLayout(layout);
        JLabel labelComponent = new JLabel(mLabel);
        labelComponent.setHorizontalAlignment(SwingConstants.CENTER);
        
        add(labelComponent);
        mTextButton.setActionCommand(mLabel + " - Text");
        mLedButton.setActionCommand(mLabel + " - LED");
        
        if (mHasText) {
            add(mTextButton);
        }
        else {
            add(new JLabel(""));
        }
        
        if (mHasLed) {
            add(mLedButton);
        }
        else {
            add(new JLabel(""));
        }
    }
    
    public boolean isTextSelected() {
        return mTextButton.isSelected();
    }
    
    
    public boolean isLedSelected() {
        return mLedButton.isSelected();
    }
    
    public void addActionListener(ActionListener listener) {
        mTextButton.addActionListener(listener);
        mLedButton.addActionListener(listener);
    }
    
    public void removeActionListener(ActionListener listener) {
        mTextButton.removeActionListener(listener);
        mLedButton.removeActionListener(listener);
    }
            
    
    public static void main(String args[]) {
        JFrame frame = new JFrame("Test");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        HicLampCommander hcl = new HicLampCommander("Label", true, true);
        frame.add(hcl);
        hcl = new HicLampCommander("Long Label Label", true, true);
        frame.add(hcl);
        hcl = new HicLampCommander("Long Label Label 2", false, true);
        frame.add(hcl);
        hcl = new HicLampCommander("Long Label Label 3", true, false);
        frame.add(hcl);
        hcl.addActionListener((a) -> {
           System.out.println("Action Command = " + a.getActionCommand());
        });
        frame.pack();
        frame.setVisible(true);
    }
}
