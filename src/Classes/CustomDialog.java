/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;

/**
 *
 * @author Sham
 */
public class CustomDialog extends JDialog{
   private ActionListener okActionListener;

    public CustomDialog(Frame owner, String title, String message) {
        super(owner, title, true);
        setResizable(false);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        JLabel label = new JLabel(message);
        label.setFont(label.getFont().deriveFont(Font.PLAIN, 14));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton closeButton = new JButton("OK");
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (okActionListener != null) {
                    okActionListener.actionPerformed(e);
                }
                dispose();
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        buttonPanel.add(closeButton);

        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.add(label, BorderLayout.CENTER);
        contentPane.add(buttonPanel, BorderLayout.SOUTH);
        setContentPane(contentPane);

        pack();
        setLocationRelativeTo(owner);

        // Set dialog background color and button font to match default JOptionPane
        Color backgroundColor = UIManager.getColor("OptionPane.background");
        Color buttonTextColor = UIManager.getColor("OptionPane.messageForeground");
        Font buttonFont = UIManager.getFont("OptionPane.buttonFont");

        contentPane.setBackground(backgroundColor);
        buttonPanel.setBackground(backgroundColor);
        closeButton.setForeground(buttonTextColor);
        closeButton.setFont(buttonFont);
    }

    public void setOkActionListener(ActionListener listener) {
        this.okActionListener = listener;
    }

    @Override
    protected JRootPane createRootPane() {
        JRootPane rootPane = new JRootPane();
        rootPane.setWindowDecorationStyle(JRootPane.NONE);
        return rootPane;
    }
}
