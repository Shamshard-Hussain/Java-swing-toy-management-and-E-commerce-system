/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import Classes.Category;
import Classes.CustomDialog;
import Classes.Product;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import org.w3c.dom.events.DocumentEvent;

/**
 *
 * @author Sham
 */
public class Index extends javax.swing.JFrame {

    /**
     * Creates new form Index
     */
    Category category = new Category();

    Product toy = new Product();
    private List<Product> productsList;

    public Index() {
        initComponents();
        productsList = new ArrayList<>();
        populateProducts();
        displayProductCards();
        category.populateAllCategoryComboBox(cmbCategory);
      

    }

    private void populateProducts() {
        try ( BufferedReader br = new BufferedReader(new FileReader("PRODUCTS.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {

                String[] productData = line.split(" ");
                if (productData.length == 8) {
                    Product product = new Product();
                    product.setName(productData[1]);
                    product.setPrice(productData[2]);
                    product.setCategory(productData[5]);
                    product.setImageName("src/Products/" + productData[7]);
                    resizeImage(product);
                    productsList.add(product);

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void resizeImage(Product product) {
        try {
            ImageIcon imageIcon = new ImageIcon(product.getImageName());
            Image image = imageIcon.getImage().getScaledInstance(265, 173, Image.SCALE_SMOOTH);
            product.setImageIcon(new ImageIcon(image));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void displayProductCards() {
        Product_card_Panel.removeAll();
        JPanel productGridPanel = new JPanel(new GridBagLayout());
        productGridPanel.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10); // Adjust the spacing as needed

        for (Product product : productsList) {
            JPanel productCardPanel = new JPanel(new BorderLayout());
            productCardPanel.setBackground(Color.WHITE);
            productCardPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

            // Create a JLabel for the product image
            JLabel productImageLabel = new JLabel(product.getImageIcon());
            productImageLabel.setHorizontalAlignment(JLabel.CENTER);
            productCardPanel.add(productImageLabel, BorderLayout.CENTER);

            // Create a JPanel to hold the product details (name, price, and "Add to Cart" button)
            JPanel productDetailsPanel = new JPanel(new BorderLayout());
            productDetailsPanel.setBackground(Color.WHITE);

            // Create a JLabel for the product name
            JLabel productNameLabel = new JLabel(product.getName());
            productNameLabel.setHorizontalAlignment(JLabel.CENTER);
            productNameLabel.setFont(new Font("Arial", Font.BOLD, 14));
            productDetailsPanel.add(productNameLabel, BorderLayout.NORTH);

            // Create a JLabel for the product price
            JLabel productPriceLabel = new JLabel("Price: Rs: " + product.getPrice() + "00./=");
            productPriceLabel.setHorizontalAlignment(JLabel.CENTER);
            productDetailsPanel.add(productPriceLabel, BorderLayout.CENTER);

            // Create an "Add to Cart" button
            JButton addToCartButton = new JButton("Add to Cart");
            addToCartButton.setBackground(Color.GREEN);
            addToCartButton.setForeground(Color.WHITE);
            addToCartButton.setFont(new Font("Arial", Font.BOLD, 12));
            addToCartButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Add your code here to handle the "Add to Cart" button action
                }
            });
            productDetailsPanel.add(addToCartButton, BorderLayout.SOUTH);

            productCardPanel.add(productDetailsPanel, BorderLayout.SOUTH);

            productGridPanel.add(productCardPanel, gbc);

            // Update the grid constraints for the next product
            gbc.gridx++;
            if (gbc.gridx > 3) {
                gbc.gridx = 0;
                gbc.gridy++;
            }
        }

        JScrollPane jScrollPane = new JScrollPane(productGridPanel);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Add the JScrollPane to your main panel or frame
        Product_card_Panel.add(jScrollPane, BorderLayout.CENTER);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        nav_bar = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        Body_Panel = new javax.swing.JPanel();
        Home = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        cmbCategory = new javax.swing.JComboBox<>();
        Product_card_Panel = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        nav_bar.setBackground(new java.awt.Color(102, 102, 102));
        nav_bar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-login-25.png"))); // NOI18N
        jLabel35.setToolTipText("Logout");
        jLabel35.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel35MouseClicked(evt);
            }
        });
        nav_bar.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 10, 30, 40));

        jPanel1.add(nav_bar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1170, 60));

        Body_Panel.setLayout(new java.awt.CardLayout());

        Home.setBackground(new java.awt.Color(255, 255, 255));
        Home.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel15.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel15.setText("Products");
        jLabel15.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Home.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 50, -1, -1));

        jLabel31.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel31.setText("Category:");
        Home.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 104, -1));

        cmbCategory.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All" }));
        cmbCategory.setAutoscrolls(true);
        cmbCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCategoryActionPerformed(evt);
            }
        });
        Home.add(cmbCategory, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 120, 200, -1));

        Product_card_Panel.setBackground(new java.awt.Color(255, 255, 255));
        Product_card_Panel.setLayout(new java.awt.GridLayout(1, 0));
        Home.add(Product_card_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 1170, 520));

        jButton1.setBackground(new java.awt.Color(0, 204, 153));
        jButton1.setForeground(new java.awt.Color(0, 204, 153));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-search-25.png"))); // NOI18N
        jButton1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton1.setBorderPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        Home.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 120, 50, 30));

        Body_Panel.add(Home, "card2");

        jPanel1.add(Body_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 1170, 670));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel35MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel35MouseClicked
        int i = JOptionPane.showConfirmDialog(null, "Do you want to logout?", "Logout?", JOptionPane.YES_NO_OPTION);
        if (i == 1) {/* do nothing*/
        }
        if (i == 0) {
            Sign_Page sp = new Sign_Page();
            sp.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_jLabel35MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       productsList.clear();
        Object selectedItem = cmbCategory.getSelectedItem();
      //  if (selectedItem != null) {
            String selectedCategory = selectedItem.toString();

            try ( BufferedReader br = new BufferedReader(new FileReader("PRODUCTS.txt"))) {
                String line;
                boolean foundProducts = false; // Flag to check if any products were found for the selected category
                while ((line = br.readLine()) != null) {
                    String[] productData = line.split(" ");
                    if (productData.length == 8) {
                        Product product = new Product();
                        product.setName(productData[1]);
                        product.setPrice(productData[2]);
                        product.setCategory(productData[5]);
                        product.setImageName("src/Products/" + productData[7]);
                        resizeImage(product);

                        if ("All".equals(selectedCategory) || product.getCategory().equalsIgnoreCase(selectedCategory)) {
                            productsList.add(product);
                            foundProducts = true; // Set the flag to true if any products are found
                        }
                    }
                }
                if (!foundProducts) {
                    CustomDialog dialog = new CustomDialog(null, "Products", "No products found for the selected category.");
                    dialog.setOkActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                             category.populateAllCategoryComboBox(cmbCategory);
                             populateProducts();
                             displayProductCards();
                        }
                    });
                    dialog.setVisible(true);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            displayProductCards(); // Update the displayed product cards
      //  }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cmbCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCategoryActionPerformed
        productsList.clear();
        Object selectedItem = cmbCategory.getSelectedItem();
        if (selectedItem != null) {
            String selectedCategory = selectedItem.toString();

            try ( BufferedReader br = new BufferedReader(new FileReader("PRODUCTS.txt"))) {
                String line;
                boolean foundProducts = false; // Flag to check if any products were found for the selected category
                while ((line = br.readLine()) != null) {
                    String[] productData = line.split(" ");
                    if (productData.length == 8) {
                        Product product = new Product();
                        product.setName(productData[1]);
                        product.setPrice(productData[2]);
                        product.setCategory(productData[5]);
                        product.setImageName("src/Products/" + productData[7]);
                        resizeImage(product);

                        if ("All".equals(selectedCategory) || product.getCategory().equalsIgnoreCase(selectedCategory)) {
                            productsList.add(product);
                            foundProducts = true; // Set the flag to true if any products are found
                        }
                    }
                }
                if (!foundProducts) {
                    CustomDialog dialog = new CustomDialog(null, "Products", "No products found for the selected category.");
                    dialog.setOkActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                             category.populateAllCategoryComboBox(cmbCategory);
                             populateProducts();
                             displayProductCards();
                        }
                    });
                    dialog.setVisible(true);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            displayProductCards(); // Update the displayed product cards
        }
    }//GEN-LAST:event_cmbCategoryActionPerformed

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
            java.util.logging.Logger.getLogger(Index.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Index.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Index.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Index.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Index().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Body_Panel;
    private javax.swing.JPanel Home;
    private javax.swing.JPanel Product_card_Panel;
    private javax.swing.JComboBox<String> cmbCategory;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel nav_bar;
    // End of variables declaration//GEN-END:variables
}
