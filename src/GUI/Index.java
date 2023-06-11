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
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.Timer;

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

        ImageIcon li = new ImageIcon(getClass().getResource("/Images/2.jpg"));
        Image image = (li).getImage().getScaledInstance(jLabel3.getWidth(), jLabel3.getHeight(), Image.SCALE_SMOOTH);
        li = new ImageIcon(image);
        jLabel3.setIcon(li);

        ImageIcon li2 = new ImageIcon(getClass().getResource("/Images/PngItem_46837.png"));
        Image image2 = (li2).getImage().getScaledInstance(jLabel4.getWidth(), jLabel4.getHeight(), Image.SCALE_SMOOTH);
        li2 = new ImageIcon(image2);
        jLabel4.setIcon(li2);

        ImageIcon li3 = new ImageIcon(getClass().getResource("/Images/1.jpg"));
        Image image3 = (li3).getImage().getScaledInstance(jLabel14.getWidth(), jLabel14.getHeight(), Image.SCALE_SMOOTH);
        li3 = new ImageIcon(image3);
        jLabel14.setIcon(li3);

        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jLabel12.isShowing()) {
                    // Generate random RGB values for the color
                    Random random = new Random();
                    int red = random.nextInt(256);
                    int green = random.nextInt(256);
                    int blue = random.nextInt(256);

                    // Create a new Color object with the random RGB values
                    Color randomColor = new Color(red, green, blue);

                    // Set the foreground color of jLabel12 to the random color
                    jLabel12.setForeground(randomColor);
                }
            }
        });

        // Start the timer
        timer.start();

    }

    private void populateProducts() {
        productsList.clear(); // Clear the list before populating it again
        try ( BufferedReader br = new BufferedReader(new FileReader("PRODUCTS.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] productData = line.split(" ");
                if (productData.length == 8) {
                    Product product = new Product();
                    product.setId(productData[0]);
                    product.setName(productData[1]);
                    product.setPrice(productData[2]);
                    product.setAgeGroup(productData[3]);
                    product.setCategory(productData[5]);
                    product.setDescription(productData[6]);
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

            JButton productImageButton = new JButton(product.getImageIcon());
            productImageButton.setHorizontalAlignment(JButton.CENTER);
            productImageButton.setBorderPainted(false);
            productImageButton.setContentAreaFilled(false);
            productImageButton.setFocusPainted(false);
            productImageButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    txtProduct_Name.setText(product.getName().replace("_", " "));
                    txtProduct_Price.setText("Rs: " + product.getPrice() + ".00/=");
                    txtProduct__Agegroup.setText(product.getAgeGroup() + "+");
                    txtProduct_Category.setText(product.getCategory().replace("_", " "));
                    txtProduct_Description.setText(product.getDescription().replace("_", " "));
                    String imagePath = product.getImageName(); // Get the file path from getImageName()

                    if (imagePath != null && !imagePath.isEmpty()) {
                        String filePath = imagePath; // Construct the complete file path

                        // Create an ImageIcon from the file path
                        ImageIcon imageIcon = new ImageIcon(filePath);

                        // Scale the image to fit the label size
                        Image image = imageIcon.getImage().getScaledInstance(img_Product.getWidth(), img_Product.getHeight(), Image.SCALE_SMOOTH);

                        // Create a new ImageIcon with the scaled image
                        ImageIcon scaledImageIcon = new ImageIcon(image);

                        // Set the scaled image icon as the label's icon
                        img_Product.setIcon(scaledImageIcon);
                    } else {
                        // Set a placeholder or default image if the file path is empty or null
                        img_Product.setIcon(new ImageIcon("src/Images/icons8-add-image-48.png"));
                    }

                    Body_Panel.removeAll();
                    Body_Panel.add(Product_DiscriptionPanel);
                    Body_Panel.repaint();
                    Body_Panel.revalidate();

                }
            });
            productCardPanel.add(productImageButton, BorderLayout.CENTER);

            // Create a JPanel to hold the product details (name, price, and "Add to Cart" button)
            JPanel productDetailsPanel = new JPanel(new BorderLayout());
            productDetailsPanel.setBackground(Color.WHITE);

            // Create a JLabel for the product name
            JLabel productNameLabel = new JLabel(product.getName());
            productNameLabel.setHorizontalAlignment(JLabel.CENTER);
            productNameLabel.setFont(new Font("Arial", Font.BOLD, 14));
            productDetailsPanel.add(productNameLabel, BorderLayout.NORTH);

            // Create a JLabel for the product price
            JLabel productPriceLabel = new JLabel("Price: Rs " + product.getPrice() + ".00/=");
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

                }
            });
            productDetailsPanel.add(addToCartButton, BorderLayout.SOUTH);

            productCardPanel.add(productDetailsPanel, BorderLayout.SOUTH);

            productGridPanel.add(productCardPanel, gbc);

            // Update the grid constraints for the next product
            gbc.gridx++;
            if (gbc.gridx > 2) {
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
        lbl_home = new javax.swing.JLabel();
        lbl_aboutUs = new javax.swing.JLabel();
        lbl_products = new javax.swing.JLabel();
        lbl_cart = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        Body_Panel = new javax.swing.JPanel();
        Home = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Home_Panel_Canteiner = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        Product_page = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        Product_card_Panel = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        txtSearch = new javax.swing.JTextField();
        Product_search_panel = new javax.swing.JPanel();
        Product_card_Panel1 = new javax.swing.JPanel();
        txtSearch1 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        Product_DiscriptionPanel = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        img_Product = new javax.swing.JLabel();
        txtProduct_Description = new javax.swing.JLabel();
        txtProduct_Name = new javax.swing.JLabel();
        txtProduct_Price = new javax.swing.JLabel();
        txtProduct__Agegroup = new javax.swing.JLabel();
        txtProduct_Category = new javax.swing.JLabel();
        btn_addtocart = new javax.swing.JButton();
        About_Us_Page = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        Cart_panel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
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

        lbl_home.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbl_home.setForeground(new java.awt.Color(255, 255, 255));
        lbl_home.setText("Home");
        lbl_home.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_homeMouseClicked(evt);
            }
        });
        nav_bar.add(lbl_home, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 20, 50, 20));

        lbl_aboutUs.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbl_aboutUs.setForeground(new java.awt.Color(255, 255, 255));
        lbl_aboutUs.setText("About Us");
        lbl_aboutUs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_aboutUsMouseClicked(evt);
            }
        });
        nav_bar.add(lbl_aboutUs, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 20, 80, 20));

        lbl_products.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbl_products.setForeground(new java.awt.Color(255, 255, 255));
        lbl_products.setText("Products");
        lbl_products.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_productsMouseClicked(evt);
            }
        });
        nav_bar.add(lbl_products, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 20, 90, 20));

        lbl_cart.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbl_cart.setForeground(new java.awt.Color(255, 255, 255));
        lbl_cart.setText("Cart");
        lbl_cart.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_cartMouseClicked(evt);
            }
        });
        nav_bar.add(lbl_cart, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 20, 50, 20));

        jLabel13.setFont(new java.awt.Font("Trebuchet MS", 3, 36)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Toy Gallery");
        nav_bar.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 210, 60));

        jPanel1.add(nav_bar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1170, 60));

        Body_Panel.setBackground(new java.awt.Color(255, 255, 255));
        Body_Panel.setLayout(new java.awt.CardLayout());

        Home.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        Home_Panel_Canteiner.setBackground(new java.awt.Color(204, 204, 204));
        Home_Panel_Canteiner.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jLabel1.setText("Children ");
        Home_Panel_Canteiner.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 190, -1, 45));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jLabel2.setText("Toys.....");
        Home_Panel_Canteiner.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 230, 220, 70));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/PngItem_46837.png"))); // NOI18N
        Home_Panel_Canteiner.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 190, 570, 360));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel5.setText("Welcome to our toy shop website! ");
        Home_Panel_Canteiner.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 310, 420, 30));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setText("we strive to offer something for everyone.");
        Home_Panel_Canteiner.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 460, 550, 30));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setText("We are dedicated to bringing joy and ");
        Home_Panel_Canteiner.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 360, 460, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setText("excitement to children of all ages with our wide ");
        Home_Panel_Canteiner.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 380, 510, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setText("selection of toys. At our toy shop, you'll find a diverse");
        Home_Panel_Canteiner.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 400, 640, -1));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setText("range of toys that cater to various interests and ");
        Home_Panel_Canteiner.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 420, 590, -1));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel11.setText("preferences. From classic favorites to the latest trends, ");
        Home_Panel_Canteiner.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 440, 550, 30));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel12.setText(" Explore More");
        jLabel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel12MouseClicked(evt);
            }
        });
        Home_Panel_Canteiner.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 530, 170, 50));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/2.jpg"))); // NOI18N
        jLabel3.setDebugGraphicsOptions(javax.swing.DebugGraphics.FLASH_OPTION);
        Home_Panel_Canteiner.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1170, 670));

        jScrollPane1.setViewportView(Home_Panel_Canteiner);

        Home.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1170, 670));

        Body_Panel.add(Home, "card5");

        Product_page.setBackground(new java.awt.Color(255, 255, 255));
        Product_page.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel15.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel15.setText("Products");
        jLabel15.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Product_page.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 50, -1, -1));

        Product_card_Panel.setBackground(new java.awt.Color(255, 255, 255));
        Product_card_Panel.setLayout(new java.awt.GridLayout(1, 0));
        Product_page.add(Product_card_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 1170, 520));

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
        Product_page.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 120, 50, 30));

        txtSearch.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtSearch.setToolTipText("");
        txtSearch.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Product_page.add(txtSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 120, 340, 30));

        Body_Panel.add(Product_page, "card2");

        Product_search_panel.setBackground(new java.awt.Color(255, 255, 255));
        Product_search_panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Product_card_Panel1.setBackground(new java.awt.Color(255, 255, 255));
        Product_card_Panel1.setLayout(new java.awt.GridLayout(1, 0));
        Product_search_panel.add(Product_card_Panel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 1170, 520));

        txtSearch1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtSearch1.setToolTipText("");
        txtSearch1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Product_search_panel.add(txtSearch1, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 120, 340, 30));

        jButton2.setBackground(new java.awt.Color(0, 204, 153));
        jButton2.setForeground(new java.awt.Color(0, 204, 153));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-search-25.png"))); // NOI18N
        jButton2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton2.setBorderPainted(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        Product_search_panel.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 120, 50, 30));

        jLabel16.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel16.setText("Products");
        jLabel16.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Product_search_panel.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 50, -1, -1));

        Body_Panel.add(Product_search_panel, "card3");

        Product_DiscriptionPanel.setBackground(new java.awt.Color(255, 255, 255));
        Product_DiscriptionPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel17.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel17.setText("Product Discription");
        jLabel17.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Product_DiscriptionPanel.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 50, -1, -1));

        jLabel27.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel27.setText("Model Name:");
        Product_DiscriptionPanel.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 230, 160, -1));

        jLabel28.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel28.setText("Price:");
        Product_DiscriptionPanel.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 270, 160, -1));

        jLabel30.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel30.setText("Age Group:");
        Product_DiscriptionPanel.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 310, 160, -1));

        jLabel31.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel31.setText("Category:");
        Product_DiscriptionPanel.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 350, 160, -1));

        jLabel32.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel32.setText("Product Description:");
        Product_DiscriptionPanel.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 390, 170, -1));

        img_Product.setBackground(new java.awt.Color(204, 204, 204));
        img_Product.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        img_Product.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-add-image-48.png"))); // NOI18N
        img_Product.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        img_Product.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        img_Product.setMaximumSize(new java.awt.Dimension(100, 100));
        img_Product.setMinimumSize(new java.awt.Dimension(100, 100));
        img_Product.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Product_DiscriptionPanel.add(img_Product, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 180, 340, 300));

        txtProduct_Description.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        txtProduct_Description.setText("Description");
        Product_DiscriptionPanel.add(txtProduct_Description, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 390, 280, -1));

        txtProduct_Name.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        txtProduct_Name.setText("Name");
        Product_DiscriptionPanel.add(txtProduct_Name, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 230, 280, -1));

        txtProduct_Price.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        txtProduct_Price.setText("Price");
        Product_DiscriptionPanel.add(txtProduct_Price, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 270, 280, -1));

        txtProduct__Agegroup.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        txtProduct__Agegroup.setText("Age Group");
        Product_DiscriptionPanel.add(txtProduct__Agegroup, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 310, 280, -1));

        txtProduct_Category.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        txtProduct_Category.setText("Category");
        Product_DiscriptionPanel.add(txtProduct_Category, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 350, 280, -1));

        btn_addtocart.setBackground(new java.awt.Color(0, 204, 153));
        btn_addtocart.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btn_addtocart.setText("Add to Cart");
        btn_addtocart.setBorderPainted(false);
        Product_DiscriptionPanel.add(btn_addtocart, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 540, 610, -1));

        Body_Panel.add(Product_DiscriptionPanel, "card4");

        About_Us_Page.setBackground(new java.awt.Color(255, 255, 255));
        About_Us_Page.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/1.jpg"))); // NOI18N
        About_Us_Page.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 100, 510, 480));

        jLabel18.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel18.setText("About Us");
        jLabel18.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        About_Us_Page.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 110, -1, -1));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("<html><div style='text-align: center;'>Welcome to our toy shop website!<br>We offer a wide range of high-quality toys for children of all ages.\nFrom educational toys to playsets and popular characters, we have something for everyone.\nBrowse our categories, view detailed product descriptions, and enjoy a seamless online shopping experience.\nWe prioritize safety, quality, and excellent customer service.\nExplore our website and bring joy to your little ones with our fantastic selection of toys.\n<br>Happy shopping!</div></html>");
        jLabel19.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel19.setIconTextGap(3);
        About_Us_Page.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 190, 470, 330));

        Body_Panel.add(About_Us_Page, "card6");

        javax.swing.GroupLayout Cart_panelLayout = new javax.swing.GroupLayout(Cart_panel);
        Cart_panel.setLayout(Cart_panelLayout);
        Cart_panelLayout.setHorizontalGroup(
            Cart_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1170, Short.MAX_VALUE)
        );
        Cart_panelLayout.setVerticalGroup(
            Cart_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 670, Short.MAX_VALUE)
        );

        Body_Panel.add(Cart_panel, "card7");

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
        String keyword = txtSearch.getText().trim();
        productsList.clear();

        try ( BufferedReader br = new BufferedReader(new FileReader("PRODUCTS.txt"))) {
            String line;
            boolean foundProducts = false; // Flag to check if any products were found

            while ((line = br.readLine()) != null) {
                String[] productData = line.split(" ");
                if (productData.length == 8) {
                    Product productdata = new Product();
                    productdata.setId(productData[0]);
                    productdata.setName(productData[1]);
                    productdata.setPrice(productData[2]);
                    productdata.setAgeGroup(productData[3]);
                    productdata.setCategory(productData[5]);
                    productdata.setDescription(productData[6]);
                    productdata.setImageName("src/Products/" + productData[7]);
                    resizeImage(productdata);

                    // Perform the search based on name, category, price, and description
                    if (productdata.getName().toLowerCase().contains(keyword.toLowerCase())
                            || productdata.getCategory().toLowerCase().contains(keyword.toLowerCase())
                            || productdata.getPrice().toLowerCase().contains(keyword.toLowerCase())
                            || productdata.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                        productsList.add(productdata);
                        foundProducts = true; // Set the flag to true if any products are found

                        Product_card_Panel1.removeAll();
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

                            // Create a JButton for the product image
                            JButton productImageButton = new JButton(product.getImageIcon());
                            productImageButton.setHorizontalAlignment(JButton.CENTER);
                            productImageButton.setBorderPainted(false);
                            productImageButton.setContentAreaFilled(false);
                            productImageButton.setFocusPainted(false);
                            productCardPanel.add(productImageButton, BorderLayout.CENTER);
                            productImageButton.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    // Add your code here to handle the "click" image action
                                    txtProduct_Name.setText(product.getName().replace("_", " "));
                                    txtProduct_Price.setText("Rs: " + product.getPrice() + ".00/=");
                                    txtProduct__Agegroup.setText(product.getAgeGroup() + "+");
                                    txtProduct_Category.setText(product.getCategory().replace("_", " "));
                                    txtProduct_Description.setText(product.getDescription().replace("_", " "));
                                    String imagePath = product.getImageName(); // Get the file path from getImageName()

                                    if (imagePath != null && !imagePath.isEmpty()) {
                                        String filePath = imagePath; // Construct the complete file path

                                        // Create an ImageIcon from the file path
                                        ImageIcon imageIcon = new ImageIcon(filePath);

                                        // Scale the image to fit the label size
                                        Image image = imageIcon.getImage().getScaledInstance(img_Product.getWidth(), img_Product.getHeight(), Image.SCALE_SMOOTH);

                                        // Create a new ImageIcon with the scaled image
                                        ImageIcon scaledImageIcon = new ImageIcon(image);

                                        // Set the scaled image icon as the label's icon
                                        img_Product.setIcon(scaledImageIcon);
                                    } else {
                                        // Set a placeholder or default image if the file path is empty or null
                                        img_Product.setIcon(new ImageIcon("src/Images/icons8-add-image-48.png"));
                                    }

                                    Body_Panel.removeAll();
                                    Body_Panel.add(Product_DiscriptionPanel);
                                    Body_Panel.repaint();
                                    Body_Panel.revalidate();

                                }
                            });

                            // Create a JPanel to hold the product details (name, price, and "Add to Cart" button)
                            JPanel productDetailsPanel = new JPanel(new BorderLayout());
                            productDetailsPanel.setBackground(Color.WHITE);

                            // Create a JLabel for the product name
                            JLabel productNameLabel = new JLabel(product.getName());
                            productNameLabel.setHorizontalAlignment(JLabel.CENTER);
                            productNameLabel.setFont(new Font("Arial", Font.BOLD, 14));
                            productDetailsPanel.add(productNameLabel, BorderLayout.NORTH);

                            // Create a JLabel for the product price
                            JLabel productPriceLabel = new JLabel("Price: Rs: " + product.getPrice() + ".00/=");
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
                            if (gbc.gridx > 2) {
                                gbc.gridx = 0;
                                gbc.gridy++;
                            }
                        }

                        JScrollPane jScrollPane = new JScrollPane(productGridPanel);
                        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

                        // Add the JScrollPane to your main panel or frame
                        Product_card_Panel1.add(jScrollPane, BorderLayout.CENTER);

                        Body_Panel.removeAll();
                        Body_Panel.add(Product_search_panel);
                        Body_Panel.repaint();
                        Body_Panel.revalidate();

                    }
                }
            }

            if (!foundProducts) {
                CustomDialog dialog = new CustomDialog(null, "Products", "No products found for the search keyword.");
                dialog.setOkActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Body_Panel.removeAll();
                        Body_Panel.add(Product_page);
                        Body_Panel.repaint();
                        Body_Panel.revalidate();
                    }
                });
                dialog.setVisible(true);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String keyword = txtSearch1.getText().trim();
        productsList.clear();

        try ( BufferedReader br = new BufferedReader(new FileReader("PRODUCTS.txt"))) {
            String line;
            boolean foundProducts = false; // Flag to check if any products were found

            while ((line = br.readLine()) != null) {
                String[] productData = line.split(" ");
                if (productData.length == 8) {
                    Product productdata = new Product();
                    productdata.setId(productData[0]);
                    productdata.setName(productData[1]);
                    productdata.setPrice(productData[2]);
                    productdata.setAgeGroup(productData[3]);
                    productdata.setCategory(productData[5]);
                    productdata.setDescription(productData[6]);
                    productdata.setImageName("src/Products/" + productData[7]);
                    resizeImage(productdata);

                    // Perform the search based on name, category, price, and description
                    if (productdata.getName().toLowerCase().contains(keyword.toLowerCase())
                            || productdata.getCategory().toLowerCase().contains(keyword.toLowerCase())
                            || productdata.getPrice().toLowerCase().contains(keyword.toLowerCase())
                            || productdata.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                        productsList.add(productdata);
                        foundProducts = true; // Set the flag to true if any products are found

                        Product_card_Panel1.removeAll();
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

                            // Create a JButton for the product image
                            JButton productImageButton = new JButton(product.getImageIcon());
                            productImageButton.setHorizontalAlignment(JButton.CENTER);
                            productImageButton.setBorderPainted(false);
                            productImageButton.setContentAreaFilled(false);
                            productImageButton.setFocusPainted(false);
                            productCardPanel.add(productImageButton, BorderLayout.CENTER);
                            productImageButton.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    // Add your code here to handle the "click" image action
                                    txtProduct_Name.setText(product.getName().replace("_", " "));
                                    txtProduct_Price.setText("Rs: " + product.getPrice() + ".00/=");
                                    txtProduct__Agegroup.setText(product.getAgeGroup() + "+");
                                    txtProduct_Category.setText(product.getCategory().replace("_", " "));
                                    txtProduct_Description.setText(product.getDescription().replace("_", " "));
                                    String imagePath = product.getImageName(); // Get the file path from getImageName()

                                    if (imagePath != null && !imagePath.isEmpty()) {
                                        String filePath = imagePath; // Construct the complete file path

                                        // Create an ImageIcon from the file path
                                        ImageIcon imageIcon = new ImageIcon(filePath);

                                        // Scale the image to fit the label size
                                        Image image = imageIcon.getImage().getScaledInstance(img_Product.getWidth(), img_Product.getHeight(), Image.SCALE_SMOOTH);

                                        // Create a new ImageIcon with the scaled image
                                        ImageIcon scaledImageIcon = new ImageIcon(image);

                                        // Set the scaled image icon as the label's icon
                                        img_Product.setIcon(scaledImageIcon);
                                    } else {
                                        // Set a placeholder or default image if the file path is empty or null
                                        img_Product.setIcon(new ImageIcon("src/Images/icons8-add-image-48.png"));
                                    }

                                    Body_Panel.removeAll();
                                    Body_Panel.add(Product_DiscriptionPanel);
                                    Body_Panel.repaint();
                                    Body_Panel.revalidate();
                                }
                            });

                            // Create a JPanel to hold the product details (name, price, and "Add to Cart" button)
                            JPanel productDetailsPanel = new JPanel(new BorderLayout());
                            productDetailsPanel.setBackground(Color.WHITE);

                            // Create a JLabel for the product name
                            JLabel productNameLabel = new JLabel(product.getName());
                            productNameLabel.setHorizontalAlignment(JLabel.CENTER);
                            productNameLabel.setFont(new Font("Arial", Font.BOLD, 14));
                            productDetailsPanel.add(productNameLabel, BorderLayout.NORTH);

                            // Create a JLabel for the product price
                            JLabel productPriceLabel = new JLabel("Price: Rs: " + product.getPrice() + ".00/=");
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
                            if (gbc.gridx > 2) {
                                gbc.gridx = 0;
                                gbc.gridy++;
                            }
                        }

                        JScrollPane jScrollPane = new JScrollPane(productGridPanel);
                        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

                        // Add the JScrollPane to your main panel or frame
                        Product_card_Panel1.add(jScrollPane, BorderLayout.CENTER);

                        Body_Panel.removeAll();
                        Body_Panel.add(Product_search_panel);
                        Body_Panel.repaint();
                        Body_Panel.revalidate();

                    }
                }
            }

            if (!foundProducts) {
                CustomDialog dialog = new CustomDialog(null, "Products", "No products found for the search keyword.");
                dialog.setOkActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Body_Panel.removeAll();
                        Body_Panel.add(Product_page);
                        Body_Panel.repaint();
                        Body_Panel.revalidate();
                    }
                });
                dialog.setVisible(true);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }//GEN-LAST:event_jButton2ActionPerformed

    private void lbl_homeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_homeMouseClicked
        Body_Panel.removeAll();
        Body_Panel.add(Home);
        Body_Panel.repaint();
        Body_Panel.revalidate();
    }//GEN-LAST:event_lbl_homeMouseClicked

    private void lbl_aboutUsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_aboutUsMouseClicked
        Body_Panel.removeAll();
        Body_Panel.add(About_Us_Page);
        Body_Panel.repaint();
        Body_Panel.revalidate();
    }//GEN-LAST:event_lbl_aboutUsMouseClicked

    private void lbl_productsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_productsMouseClicked
        Body_Panel.removeAll();
        Body_Panel.add(Product_page);
        Body_Panel.repaint();
        Body_Panel.revalidate();
    }//GEN-LAST:event_lbl_productsMouseClicked

    private void lbl_cartMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_cartMouseClicked
        Body_Panel.removeAll();
        Body_Panel.add(Cart_panel);
        Body_Panel.repaint();
        Body_Panel.revalidate();
    }//GEN-LAST:event_lbl_cartMouseClicked

    private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseClicked
        Body_Panel.removeAll();
        Body_Panel.add(Product_page);
        Body_Panel.repaint();
        Body_Panel.revalidate();
    }//GEN-LAST:event_jLabel12MouseClicked

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
    private javax.swing.JPanel About_Us_Page;
    private javax.swing.JPanel Body_Panel;
    private javax.swing.JPanel Cart_panel;
    private javax.swing.JPanel Home;
    private javax.swing.JPanel Home_Panel_Canteiner;
    private javax.swing.JPanel Product_DiscriptionPanel;
    private javax.swing.JPanel Product_card_Panel;
    private javax.swing.JPanel Product_card_Panel1;
    private javax.swing.JPanel Product_page;
    private javax.swing.JPanel Product_search_panel;
    private javax.swing.JButton btn_addtocart;
    private javax.swing.JLabel img_Product;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_aboutUs;
    private javax.swing.JLabel lbl_cart;
    private javax.swing.JLabel lbl_home;
    private javax.swing.JLabel lbl_products;
    private javax.swing.JPanel nav_bar;
    private javax.swing.JLabel txtProduct_Category;
    private javax.swing.JLabel txtProduct_Description;
    private javax.swing.JLabel txtProduct_Name;
    private javax.swing.JLabel txtProduct_Price;
    private javax.swing.JLabel txtProduct__Agegroup;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtSearch1;
    // End of variables declaration//GEN-END:variables
}
