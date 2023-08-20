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
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

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
    private DefaultListModel<String> cartListModel;
    private JList<String> cartList;
    private DefaultTableModel cartTableModel;
    private JTable cartTable;

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
        gbc.insets = new Insets(15, 15, 15, 15); // Adjust the spacing as needed

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
                    txtPD_Id.setText(product.getId());
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
                    txtPD_Id.setVisible(false);
                    Body_Panel.removeAll();
                    Body_Panel.add(Product_DiscriptionPanel);
                    Body_Panel.repaint();
                    Body_Panel.revalidate();
                    txtPD_Id.setVisible(false);

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
                    // Retrieve the quantity of the selected product (you need to implement the logic for quantity)

                    // Add a row to the cart table with the product details
                    int i = JOptionPane.showConfirmDialog(null, "Please Confirm!", "Add to Cart", JOptionPane.OK_CANCEL_OPTION);
                    if (i == 1) {
                        /* do nothing*/
                    }
                    if (i == 0) {
                        String Id = product.getId();
                        String name = product.getName().replace("_", " ");
                        int quantity = 1;

                        String Pprice = product.getPrice();
                        int sum = Integer.parseInt(Pprice) * quantity;
                        String tot = Integer.toString(sum);

                        DefaultTableModel dt = (DefaultTableModel) tblCart.getModel();

                        // Check if the product with the same ID is already in the table
                        boolean productExists = false;
                        for (int rowIndex = 0; rowIndex < dt.getRowCount(); rowIndex++) {
                            if (dt.getValueAt(rowIndex, 0).equals(name)) { // Assuming the ID is in the first column
                                productExists = true;
                                break;
                            }
                        }

                        if (productExists) {
                            // Show a message here, for example:
                            JOptionPane.showMessageDialog(null, "Product already add to the cart.", "Product Exists", JOptionPane.WARNING_MESSAGE);
                        } else {
                            Object[] toadd = {name, quantity, tot + ".00",Id};
                            dt.addRow(toadd);
                        }

                    }

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

    private String formatCardNumber(String input) {
        StringBuilder formatted = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            if (i > 0 && i % 4 == 0) {
                formatted.append("-");
            }
            formatted.append(input.charAt(i));
        }

        return formatted.toString();
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
        btnsearch1 = new javax.swing.JButton();
        txtSearch = new javax.swing.JTextField();
        Product_search_panel = new javax.swing.JPanel();
        Product_card_Panel1 = new javax.swing.JPanel();
        txtSearch1 = new javax.swing.JTextField();
        btnSearch2 = new javax.swing.JButton();
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
        txtPD_Id = new javax.swing.JLabel();
        About_Us_Page = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        Cart_panel = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        txtTotalPrice = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblCart = new javax.swing.JTable();
        btnRemove = new javax.swing.JButton();
        btnBuy = new javax.swing.JButton();
        Payment = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        txtExpDate = new javax.swing.JTextField();
        txtCardNumber = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        txtCardOwnerName = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        txtCvCode = new javax.swing.JTextField();
        btnConfirmPayment = new javax.swing.JButton();
        Bill_Panel = new javax.swing.JPanel();

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
        nav_bar.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 10, 30, 40));

        lbl_home.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbl_home.setForeground(new java.awt.Color(255, 255, 255));
        lbl_home.setText("Home");
        lbl_home.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_homeMouseClicked(evt);
            }
        });
        nav_bar.add(lbl_home, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 20, 50, 20));

        lbl_aboutUs.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbl_aboutUs.setForeground(new java.awt.Color(255, 255, 255));
        lbl_aboutUs.setText("About Us");
        lbl_aboutUs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_aboutUsMouseClicked(evt);
            }
        });
        nav_bar.add(lbl_aboutUs, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 20, 80, 20));

        lbl_products.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbl_products.setForeground(new java.awt.Color(255, 255, 255));
        lbl_products.setText("Products");
        lbl_products.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_productsMouseClicked(evt);
            }
        });
        nav_bar.add(lbl_products, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 20, 90, 20));

        lbl_cart.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbl_cart.setForeground(new java.awt.Color(255, 255, 255));
        lbl_cart.setText("Cart");
        lbl_cart.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_cartMouseClicked(evt);
            }
        });
        nav_bar.add(lbl_cart, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 20, 50, 20));

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
        Home_Panel_Canteiner.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 140, 230, 45));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jLabel2.setText("Toys.....");
        Home_Panel_Canteiner.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 170, 230, 70));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/PngItem_46837.png"))); // NOI18N
        Home_Panel_Canteiner.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 430, 330));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel5.setText("Welcome to our toy shop! ");
        Home_Panel_Canteiner.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 260, 380, 30));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setText("we strive to offer something for everyone.");
        Home_Panel_Canteiner.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 410, 470, 30));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setText("We are dedicated to bringing joy and ");
        Home_Panel_Canteiner.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 310, 420, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setText("excitement to children of all ages with our wide ");
        Home_Panel_Canteiner.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 330, 470, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setText("selection of toys. At our toy shop, you'll find a diverse");
        Home_Panel_Canteiner.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 350, 470, -1));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setText("range of toys that cater to various interests and ");
        Home_Panel_Canteiner.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 370, 470, -1));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel11.setText("preferences. From classic favorites to the latest trends, ");
        Home_Panel_Canteiner.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 390, 470, 30));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel12.setText(" Explore More");
        jLabel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel12MouseClicked(evt);
            }
        });
        Home_Panel_Canteiner.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 490, 170, 50));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/2.jpg"))); // NOI18N
        jLabel3.setDebugGraphicsOptions(javax.swing.DebugGraphics.FLASH_OPTION);
        Home_Panel_Canteiner.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -10, 1020, 640));

        jScrollPane1.setViewportView(Home_Panel_Canteiner);

        Home.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1020, 630));

        Body_Panel.add(Home, "card5");

        Product_page.setBackground(new java.awt.Color(255, 255, 255));
        Product_page.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel15.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel15.setText("Products");
        jLabel15.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Product_page.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 40, -1, -1));

        Product_card_Panel.setBackground(new java.awt.Color(255, 255, 255));
        Product_card_Panel.setLayout(new java.awt.GridLayout(1, 0));
        Product_page.add(Product_card_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 1020, 460));

        btnsearch1.setBackground(new java.awt.Color(0, 204, 153));
        btnsearch1.setForeground(new java.awt.Color(0, 204, 153));
        btnsearch1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-search-25.png"))); // NOI18N
        btnsearch1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnsearch1.setBorderPainted(false);
        btnsearch1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsearch1ActionPerformed(evt);
            }
        });
        Product_page.add(btnsearch1, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 120, 50, 30));

        txtSearch.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtSearch.setToolTipText("");
        txtSearch.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Product_page.add(txtSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 120, 280, 30));

        Body_Panel.add(Product_page, "card2");

        Product_search_panel.setBackground(new java.awt.Color(255, 255, 255));
        Product_search_panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Product_card_Panel1.setBackground(new java.awt.Color(255, 255, 255));
        Product_card_Panel1.setLayout(new java.awt.GridLayout(1, 0));
        Product_search_panel.add(Product_card_Panel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 1020, 460));

        txtSearch1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtSearch1.setToolTipText("");
        txtSearch1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Product_search_panel.add(txtSearch1, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 120, 280, 30));

        btnSearch2.setBackground(new java.awt.Color(0, 204, 153));
        btnSearch2.setForeground(new java.awt.Color(0, 204, 153));
        btnSearch2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-search-25.png"))); // NOI18N
        btnSearch2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnSearch2.setBorderPainted(false);
        btnSearch2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearch2ActionPerformed(evt);
            }
        });
        Product_search_panel.add(btnSearch2, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 120, 50, 30));

        jLabel16.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel16.setText("Products");
        jLabel16.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Product_search_panel.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 40, -1, -1));

        Body_Panel.add(Product_search_panel, "card3");

        Product_DiscriptionPanel.setBackground(new java.awt.Color(255, 255, 255));
        Product_DiscriptionPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel17.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel17.setText("Product Discription");
        jLabel17.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Product_DiscriptionPanel.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 50, -1, -1));

        jLabel27.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel27.setText("Model Name:");
        Product_DiscriptionPanel.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 230, 160, -1));

        jLabel28.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel28.setText("Price:");
        Product_DiscriptionPanel.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 270, 160, -1));

        jLabel30.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel30.setText("Age Group:");
        Product_DiscriptionPanel.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 310, 160, -1));

        jLabel31.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel31.setText("Category:");
        Product_DiscriptionPanel.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 350, 160, -1));

        jLabel32.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel32.setText("Product Description:");
        Product_DiscriptionPanel.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 390, 170, -1));

        img_Product.setBackground(new java.awt.Color(204, 204, 204));
        img_Product.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        img_Product.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-add-image-48.png"))); // NOI18N
        img_Product.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        img_Product.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        img_Product.setMaximumSize(new java.awt.Dimension(100, 100));
        img_Product.setMinimumSize(new java.awt.Dimension(100, 100));
        img_Product.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Product_DiscriptionPanel.add(img_Product, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 180, 340, 300));

        txtProduct_Description.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        txtProduct_Description.setText("Description");
        Product_DiscriptionPanel.add(txtProduct_Description, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 390, 280, -1));

        txtProduct_Name.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        txtProduct_Name.setText("Name");
        Product_DiscriptionPanel.add(txtProduct_Name, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 230, 280, -1));

        txtProduct_Price.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        txtProduct_Price.setText("Price");
        Product_DiscriptionPanel.add(txtProduct_Price, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 270, 280, -1));

        txtProduct__Agegroup.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        txtProduct__Agegroup.setText("Age Group");
        Product_DiscriptionPanel.add(txtProduct__Agegroup, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 310, 280, -1));

        txtProduct_Category.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        txtProduct_Category.setText("Category");
        Product_DiscriptionPanel.add(txtProduct_Category, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 350, 280, -1));

        btn_addtocart.setBackground(new java.awt.Color(0, 204, 153));
        btn_addtocart.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btn_addtocart.setText("Add to Cart");
        btn_addtocart.setBorderPainted(false);
        btn_addtocart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addtocartActionPerformed(evt);
            }
        });
        Product_DiscriptionPanel.add(btn_addtocart, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 540, 610, -1));

        txtPD_Id.setText("txtPD_Id");
        Product_DiscriptionPanel.add(txtPD_Id, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 190, -1, -1));

        Body_Panel.add(Product_DiscriptionPanel, "card4");

        About_Us_Page.setBackground(new java.awt.Color(255, 255, 255));
        About_Us_Page.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/1.jpg"))); // NOI18N
        About_Us_Page.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 100, 430, 440));

        jLabel18.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel18.setText("About Us");
        jLabel18.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        About_Us_Page.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 110, -1, -1));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("<html><div style='text-align: center;'>Welcome to our toy shop website!<br>We offer a wide range of high-quality toys for children of all ages.\nFrom educational toys to playsets and popular characters, we have something for everyone.\nBrowse our categories, view detailed product descriptions, and enjoy a seamless online shopping experience.\nWe prioritize safety, quality, and excellent customer service.\nExplore our website and bring joy to your little ones with our fantastic selection of toys.\n<br>Happy shopping!</div></html>");
        jLabel19.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel19.setIconTextGap(3);
        About_Us_Page.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 190, 470, 330));

        Body_Panel.add(About_Us_Page, "card6");

        Cart_panel.setBackground(new java.awt.Color(255, 255, 255));
        Cart_panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel20.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel20.setText("Shopping Cart");
        jLabel20.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Cart_panel.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 50, -1, -1));

        txtTotalPrice.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        txtTotalPrice.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txtTotalPrice.setText("Total Price- Rs: 00.00/=");
        Cart_panel.add(txtTotalPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 470, 370, -1));

        tblCart.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tblCart.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        tblCart.setForeground(new java.awt.Color(51, 51, 51));
        tblCart.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Model Name", "Quantity", "Price", "Id"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCart.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tblCart.setGridColor(new java.awt.Color(255, 255, 255));
        tblCart.setRowHeight(18);
        tblCart.setShowGrid(true);
        tblCart.getTableHeader().setResizingAllowed(false);
        tblCart.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCartMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblCart);
        tblCart.getAccessibleContext().setAccessibleDescription("");
        int columnIndexToHide = 3; // Index of the column to hide (0-based index)

        if (columnIndexToHide >= 0 && columnIndexToHide < tblCart.getColumnCount()) {
            TableColumn column = tblCart.getColumnModel().getColumn(columnIndexToHide);
            column.setMinWidth(0);
            column.setMaxWidth(0);
            column.setWidth(0);
            column.setPreferredWidth(0);
        } else {
            System.out.println("Column index is out of bounds.");
        }

        Cart_panel.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 162, 880, 300));

        btnRemove.setBackground(new java.awt.Color(255, 51, 51));
        btnRemove.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnRemove.setText("Remove Item");
        btnRemove.setBorderPainted(false);
        btnRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveActionPerformed(evt);
            }
        });
        Cart_panel.add(btnRemove, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 530, -1, 40));

        btnBuy.setBackground(new java.awt.Color(51, 153, 0));
        btnBuy.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnBuy.setText("Buy");
        btnBuy.setBorderPainted(false);
        btnBuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuyActionPerformed(evt);
            }
        });
        Cart_panel.add(btnBuy, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 530, 150, 40));

        Body_Panel.add(Cart_panel, "card7");

        Payment.setBackground(new java.awt.Color(255, 255, 255));
        Payment.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel21.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("Payment Details");
        jLabel21.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Payment.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 1020, -1));

        jLabel29.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel29.setText("Expiration Date:");
        Payment.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 276, 160, -1));

        jLabel33.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel33.setText("Card Number:");
        Payment.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 200, 160, -1));

        txtExpDate.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtExpDate.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 204, 255)));
        txtExpDate.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtExpDateKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtExpDateKeyTyped(evt);
            }
        });
        Payment.add(txtExpDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 300, 270, 40));

        txtCardNumber.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtCardNumber.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 204, 255)));
        txtCardNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCardNumberActionPerformed(evt);
            }
        });
        txtCardNumber.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCardNumberKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCardNumberKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCardNumberKeyTyped(evt);
            }
        });
        Payment.add(txtCardNumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 220, 600, 40));

        jLabel34.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel34.setText("Card Owner Name:");
        Payment.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 360, 160, -1));

        txtCardOwnerName.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtCardOwnerName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 204, 255)));
        txtCardOwnerName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCardOwnerNameKeyReleased(evt);
            }
        });
        Payment.add(txtCardOwnerName, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 380, 600, 40));

        jLabel36.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel36.setText("CV Code:");
        Payment.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 276, 160, -1));

        txtCvCode.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtCvCode.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 204, 255)));
        txtCvCode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCvCodeKeyReleased(evt);
            }
        });
        Payment.add(txtCvCode, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 300, 270, 40));

        btnConfirmPayment.setBackground(new java.awt.Color(51, 153, 0));
        btnConfirmPayment.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        btnConfirmPayment.setForeground(new java.awt.Color(255, 255, 255));
        btnConfirmPayment.setText("Confirm Payment");
        btnConfirmPayment.setBorderPainted(false);
        btnConfirmPayment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmPaymentActionPerformed(evt);
            }
        });
        Payment.add(btnConfirmPayment, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 450, 600, 40));

        Body_Panel.add(Payment, "card8");

        javax.swing.GroupLayout Bill_PanelLayout = new javax.swing.GroupLayout(Bill_Panel);
        Bill_Panel.setLayout(Bill_PanelLayout);
        Bill_PanelLayout.setHorizontalGroup(
            Bill_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1170, Short.MAX_VALUE)
        );
        Bill_PanelLayout.setVerticalGroup(
            Bill_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 670, Short.MAX_VALUE)
        );

        Body_Panel.add(Bill_Panel, "card9");

        jPanel1.add(Body_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 1170, 670));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1018, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 687, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel35MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel35MouseClicked
        int i = JOptionPane.showConfirmDialog(null, "Do you want to logout?", "Logout", JOptionPane.YES_NO_OPTION);
        if (i == 1) {/* do nothing*/
        }
        if (i == 0) {
            Sign_Page sp = new Sign_Page();
            sp.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_jLabel35MouseClicked

    private void btnsearch1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsearch1ActionPerformed
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
                        gbc.insets = new Insets(15, 15, 15, 15); // Adjust the spacing as needed

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
                                    txtPD_Id.setText(product.getId());
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
                                    txtPD_Id.setVisible(false);
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
                                    // Retrieve the quantity of the selected product (you need to implement the logic for quantity)

                                    // Add a row to the cart table with the product details
                                    int i = JOptionPane.showConfirmDialog(null, "Please Confirm!", "Add to Cart", JOptionPane.OK_CANCEL_OPTION);
                                    if (i == 1) {
                                        /* do nothing*/
                                    }
                                    if (i == 0) {
                                        String Id = product.getId();
                                        String name = product.getName().replace("_", " ");
                                        int quantity = 1;

                                        String Pprice = product.getPrice();
                                        int sum = Integer.parseInt(Pprice) * quantity;
                                        String tot = Integer.toString(sum);

                                        DefaultTableModel dt = (DefaultTableModel) tblCart.getModel();

                                        // Check if the product with the same ID is already in the table
                                        boolean productExists = false;
                                        for (int rowIndex = 0; rowIndex < dt.getRowCount(); rowIndex++) {
                                            if (dt.getValueAt(rowIndex, 0).equals(Id)) { // Assuming the ID is in the first column
                                                productExists = true;
                                                break;
                                            }
                                        }

                                        if (productExists) {
                                            // Show a message here, for example:
                                            JOptionPane.showMessageDialog(null, "Product already add to the cart.", "Product Exists", JOptionPane.WARNING_MESSAGE);
                                        } else {
                                            Object[] toadd = {name, quantity, tot + ".00",Id};
                                            dt.addRow(toadd);
                                        }

                                    }
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

    }//GEN-LAST:event_btnsearch1ActionPerformed

    private void btnSearch2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearch2ActionPerformed
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
                        gbc.insets = new Insets(15, 15, 15, 15); // Adjust the spacing as needed

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

                                    // Add a row to the cart table with the product details
                                    int i = JOptionPane.showConfirmDialog(null, "Please Confirm!", "Add to Cart", JOptionPane.OK_CANCEL_OPTION);
                                    if (i == 1) {
                                        /* do nothing*/
                                    }
                                    if (i == 0) {
                                        String Id = product.getId();
                                        String name = product.getName().replace("_", " ");
                                        int quantity = 1;

                                        String Pprice = product.getPrice();
                                        int sum = Integer.parseInt(Pprice) * quantity;
                                        String tot = Integer.toString(sum);

                                        DefaultTableModel dt = (DefaultTableModel) tblCart.getModel();

                                        // Check if the product with the same ID is already in the table
                                        boolean productExists = false;
                                        for (int rowIndex = 0; rowIndex < dt.getRowCount(); rowIndex++) {
                                            if (dt.getValueAt(rowIndex, 0).equals(Id)) { // Assuming the ID is in the first column
                                                productExists = true;
                                                break;
                                            }
                                        }

                                        if (productExists) {
                                            // Show a message here, for example:
                                            JOptionPane.showMessageDialog(null, "Product already add to the cart.", "Product Exists", JOptionPane.WARNING_MESSAGE);
                                        } else {
                                            Object[] toadd = {name, quantity, tot + ".00",Id};
                                            dt.addRow(toadd);
                                        }

                                    }
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


    }//GEN-LAST:event_btnSearch2ActionPerformed

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
        DefaultTableModel dt = (DefaultTableModel) tblCart.getModel();
       
        // Calculate the sum of the third column and display it in TxtTotal
        double totalSum = 0.0;
        for (int rowIndex = 0; rowIndex < dt.getRowCount(); rowIndex++) {
            String totalValue = dt.getValueAt(rowIndex, 2).toString();
            totalSum += Double.parseDouble(totalValue.replace(".00", ""));
        }
        String formattedTotal = String.format("%.2f", totalSum);
        txtTotalPrice.setText("Total Price: Rs. " + formattedTotal + "/="); // Assuming TxtTotal is a JTextField
        

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

    private void tblCartMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCartMouseClicked
    if (evt.getClickCount() == 2) { // Check for double-click
    int row = tblCart.getSelectedRow();
    DefaultTableModel dt = (DefaultTableModel) tblCart.getModel();

    if (row >= 0 && row < dt.getRowCount()) {
        String Pid =dt.getValueAt(row, 3).toString();// Assuming ID is in the last column
        String pname = dt.getValueAt(row, 0).toString().replace("_", " "); 
        String Pprice = dt.getValueAt(row, 2).toString(); // Assuming price is in the 3rd column

        try {
            // Read the original price from the text file
            List<String> lines = Files.readAllLines(Paths.get("PRODUCTS.txt"));
            double originalPrice = 0.0;

            for (String line : lines) {
                String[] parts = line.split(" ");
                if (parts.length >= 8 && parts[0].equals(Pid)) {
                    originalPrice = Double.parseDouble(parts[2]);
                    break;
                }
            }

            String inputValue = JOptionPane.showInputDialog(this, "Change Quantity");
            
            if (inputValue != null) {  // Check if input is not canceled
                int newQuantity = Integer.parseInt(inputValue);
                double newTotal = newQuantity * originalPrice;

                // Update the quantity and total in the table
                dt.setValueAt(newQuantity, row, 1); // Assuming quantity is in the 2nd column
                dt.setValueAt(String.format("%.2f", newTotal), row, 2);    // Assuming total is in the 3rd column

                // Calculate the sum of the third column and display it in TxtTotal
                double totalSum = 0.0;
                for (int rowIndex = 0; rowIndex < dt.getRowCount(); rowIndex++) {
                    String totalValue = dt.getValueAt(rowIndex, 2).toString();
                    totalSum += Double.parseDouble(totalValue);
                }
                String formattedTotal = String.format("%.2f", totalSum);
                txtTotalPrice.setText("Total Price: Rs. " + formattedTotal + "/="); // Assuming txtTotalPrice is a JTextField
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid input! Please enter a valid integer.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error reading product prices.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } else {
        JOptionPane.showMessageDialog(this, "Please select a row to update.", "Row Selection", JOptionPane.WARNING_MESSAGE);
    }
}

    }//GEN-LAST:event_tblCartMouseClicked

    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveActionPerformed
        DefaultTableModel dt = (DefaultTableModel) tblCart.getModel();
        try {
            if (tblCart.getRowCount() >= 0) {
                int index = tblCart.getSelectedRow();
                dt.removeRow(index);
                JOptionPane.showMessageDialog(this, "Removed Successfull ");

                // Calculate the sum of the third column and display it in TxtTotal
                double totalSum = 0.0;
                for (int rowIndex = 0; rowIndex < dt.getRowCount(); rowIndex++) {
                    String totalValue = dt.getValueAt(rowIndex, 2).toString();
                    totalSum += Double.parseDouble(totalValue.replace(".00", ""));
                }
                String formattedTotal = String.format("%.2f", totalSum);
                txtTotalPrice.setText("Total Price: Rs. " + formattedTotal + "/="); // Assuming TxtTotal is a JTextField
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Please Select a Row to Remove", "Check Again", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnRemoveActionPerformed

    private void btnBuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuyActionPerformed

        //   DefaultTableModel dt = (DefaultTableModel) jTable1.getModel();
        // int RowCount = jTable1.getRowCount();
        //  int Total = 0;
        if (tblCart.getRowCount() <= 0) {
            JOptionPane:
            JOptionPane.showMessageDialog(null, "Please Check the Cart", "Cart is Empty", JOptionPane.ERROR_MESSAGE);

        }
//        else if (jTable1.getRowCount() >= 0) {
//            Main.removeAll();
//            Main.add(Bill_Panel);
//            Main.repaint();
//            Main.revalidate();
//            jTextArea1.setText("");
//
//            //   for (int j = 0; j < RowCount; j++) {
//                //        Total = Total + Integer.parseInt(jTable1.getValueAt(j, 3).toString());}
//
//            jTextArea1.setText(jTextArea1.getText() + "====================================================================\n");
//            jTextArea1.setText(jTextArea1.getText() + "\t\t\t Toy Gallery \n");
//            jTextArea1.setText(jTextArea1.getText() + "====================================================================\n\n\n");
//            jTextArea1.setText(jTextArea1.getText() + "______________________________________________________________________________________________________________________________");
//            jTextArea1.setText(jTextArea1.getText() + "\n Toy Id" + "\t\t Toy Name" + "\t\t Qty" + "\t\t            Price\n ");
//            jTextArea1.setText(jTextArea1.getText() + "-----------------------------------------------------------------------------------------------------------------------------\n ");
//            for (int i = 0; i < jTable1.getRowCount(); i++) {
//                String id = jTable1.getValueAt(i, 0).toString();
//                String name = jTable1.getValueAt(i, 1).toString();
//                String qty = jTable1.getValueAt(i, 2).toString();
//                String price = jTable1.getValueAt(i, 3).toString();
//                jTextArea1.setText(jTextArea1.getText() + id + "\t\t" + name + "\t" + "\t" + qty + "\t\t           " + price + "\n");
//                int sum = Integer.parseInt(qty) * Integer.parseInt(price);
//                String tot = Integer.toString(sum);
//
//            }
//            //jTextArea1.setText(jTextArea1.getText()+"\n\n====================================================================\n");
//            //jTextArea1.setText(jTextArea1.getText()+"\t\t\t Total Price- Rs:"+""+"\n");
//            //jTextArea1.setText(jTextArea1.getText()+"====================================================================\n");
//            //   jTextArea1.setText(Integer.toString(Total));
//            jTextArea1.setText(jTextArea1.getText() + "\n\n\n====================================================================\n");
//            jTextArea1.setText(jTextArea1.getText() + "\t\t\t Thank Yoy");
//            jTextArea1.setText(jTextArea1.getText() + "\n====================================================================\n");
//            //jTextArea1.setText(jTextArea1.getText()+"\t\t\t ");

//        }
        // jTextArea1.setText(Integer.toString(Total));
        
//        Body_Panel.removeAll();
//        Body_Panel.add(Payment);
//        Body_Panel.repaint();
//        Body_Panel.revalidate();
    }//GEN-LAST:event_btnBuyActionPerformed

    private void btn_addtocartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addtocartActionPerformed
        // Add a row to the cart table with the product details
        int i = JOptionPane.showConfirmDialog(null, "Please Confirm!", "Add to Cart", JOptionPane.OK_CANCEL_OPTION);
        if (i == 1) {
            /* do nothing*/
        }
        if (i == 0) {
            String Id = txtPD_Id.getText();
            String name = txtProduct_Name.getText().replace(" ", "_");
            int quantity = 1;

            String Pprice = txtProduct_Price.getText();
            Pprice = Pprice.replace("Rs: ", "").replace(".00/=", ""); // Remove prefixes and suffixes

            int sum = Integer.parseInt(Pprice) * quantity;
            String tot = Integer.toString(sum);

            DefaultTableModel dt = (DefaultTableModel) tblCart.getModel();

            // Check if the product with the same ID is already in the table
            boolean productExists = false;
            for (int rowIndex = 0; rowIndex < dt.getRowCount(); rowIndex++) {
                if (dt.getValueAt(rowIndex, 0).equals(name)) { // Assuming the ID is in the first column
                    productExists = true;
                    break;
                }
            }

            if (productExists) {
                // Show a message here, for example:
                JOptionPane.showMessageDialog(null, "Product already in the cart.", "Product Exists", JOptionPane.WARNING_MESSAGE);
            } else {
                Object[] toadd = {name, quantity, tot + ".00",Id};
                dt.addRow(toadd);
            }

        }
    }//GEN-LAST:event_btn_addtocartActionPerformed

    private void txtCardNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCardNumberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCardNumberActionPerformed

    private void txtCardOwnerNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCardOwnerNameKeyReleased
        String NamePattern = "^[a-zA-Z]+$";
        String Name = txtCardOwnerName.getText().trim();

        if (!Name.matches(NamePattern)) {
            txtCardOwnerName.setBorder(BorderFactory.createLineBorder(Color.RED));
        } else {
            txtCardOwnerName.setBorder(BorderFactory.createLineBorder(Color.decode("#0ed145")));
        }
    }//GEN-LAST:event_txtCardOwnerNameKeyReleased

    private void txtExpDateKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtExpDateKeyReleased
        String expirationDatePattern = "^(0[1-9]|1[0-2])\\/(\\d{2})$"; // MM/YY format
        String expDate = txtExpDate.getText().trim();

        if (!expDate.matches(expirationDatePattern)) {
            txtExpDate.setBorder(BorderFactory.createLineBorder(Color.RED));
        } else {
            txtExpDate.setBorder(BorderFactory.createLineBorder(Color.decode("#0ed145")));
        }
    }//GEN-LAST:event_txtExpDateKeyReleased

    private void txtExpDateKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtExpDateKeyTyped
        char typedChar = evt.getKeyChar();

        if (typedChar == '\b') { // Check if backspace key is pressed
            String text = txtExpDate.getText();

            if (text.length() > 0 && text.charAt(text.length() - 1) == '/') {
                txtExpDate.setText(text.substring(0, text.length() - 1));
            }
        } else {
            String text = txtExpDate.getText();

            if (text.length() == 2) {
                txtExpDate.setText(text + "/");
            }
        }
    }//GEN-LAST:event_txtExpDateKeyTyped

    private void txtCardNumberKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCardNumberKeyTyped

        String text = txtCardNumber.getText().replaceAll("-", "");

        if (text.length() == 4 || text.length() == 9 || text.length() == 14) {
            txtCardNumber.setText(text + "-");
        }
    }//GEN-LAST:event_txtCardNumberKeyTyped

    private void txtCardNumberKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCardNumberKeyPressed
        String text = txtCardNumber.getText().replaceAll("-", "");

        if (text.length() < 16) {
            String formattedText = formatCardNumber(text);
            txtCardNumber.setText(formattedText);
        } else {
            txtCardNumber.setText(text.substring(0, 15)); // Clear after 16 digits
        }
    }//GEN-LAST:event_txtCardNumberKeyPressed

    private void txtCardNumberKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCardNumberKeyReleased
        String CardNumberPattern = "^(\\d{4}-){3}\\d{4}$"; // ####-####-####-####-#### format
        String cardnum = txtCardNumber.getText().trim();

        if (!cardnum.matches(CardNumberPattern)) {
            txtCardNumber.setBorder(BorderFactory.createLineBorder(Color.RED));
        } else {
            txtCardNumber.setBorder(BorderFactory.createLineBorder(Color.decode("#0ed145")));
        }
    }//GEN-LAST:event_txtCardNumberKeyReleased

    private void txtCvCodeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCvCodeKeyReleased
        String CVCodePattern = "^[0-9]{3}$"; // ### format
        String cvCode = txtCvCode.getText().trim();

        if (!cvCode.matches(CVCodePattern)) {
            txtCvCode.setBorder(BorderFactory.createLineBorder(Color.RED));
        } else {
            txtCvCode.setBorder(BorderFactory.createLineBorder(Color.decode("#0ed145")));
        }
    }//GEN-LAST:event_txtCvCodeKeyReleased

    private void btnConfirmPaymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmPaymentActionPerformed
       boolean valid = true;
       
        if (txtCardNumber.getText().trim().isEmpty()
                || txtExpDate.getText().trim().isEmpty()
                || txtCvCode.getText().trim().isEmpty()
                || txtCardOwnerName.getText().trim().isEmpty()) {
            JOptionPane:
            JOptionPane.showMessageDialog(null, "Feild can't be Empty", "Payment Details Form", JOptionPane.ERROR_MESSAGE);
            valid = false;
        }else{
        
            String CardNumberPattern = "^(\\d{4}-){3}\\d{4}$"; // ####-####-####-####-#### format
            String cardnum = txtCardNumber.getText().trim();

            String expirationDatePattern = "^(0[1-9]|1[0-2])\\/(\\d{2})$"; // MM/YY format
            String expDate = txtExpDate.getText().trim();

            String CVCodePattern = "^[0-9]{3}$"; // ### format
            String cvCode = txtCvCode.getText().trim();

            String NamePattern = "^[a-zA-Z]+$";
            String Name = txtCardOwnerName.getText().trim();

            if (!cardnum.matches(CardNumberPattern)) {
                JOptionPane:
                JOptionPane.showMessageDialog(null, "Invalid Card Number", "Payment Details Form", JOptionPane.ERROR_MESSAGE);
                valid = false;

            }
            if (!expDate.matches(expirationDatePattern)) {
                JOptionPane:
                JOptionPane.showMessageDialog(null, "Invalid Expiration Date", "Payment Details Form", JOptionPane.ERROR_MESSAGE);
                valid = false;

            }
            if (!cvCode.matches(CVCodePattern)) {
                JOptionPane:
                JOptionPane.showMessageDialog(null, "Invalid Card CV Code", "Payment Details Form", JOptionPane.ERROR_MESSAGE);
                valid = false;

            }
            if (!Name.matches(NamePattern)) {
                JOptionPane:
                JOptionPane.showMessageDialog(null, "Invalid Card Owner Name", "Payment Details Form", JOptionPane.ERROR_MESSAGE);
                valid = false;

            }

        }
        if (valid) {

            int i = JOptionPane.showConfirmDialog(null, "Please Confirm!", "Payment Details Form", JOptionPane.OK_CANCEL_OPTION);
            if (i == 1) {/* do nothing*/
            }
            if (i == 0) {
                JOptionPane:
                JOptionPane.showMessageDialog(null, "Payment Successfull, Thank you!", "Payment Details Form", JOptionPane.INFORMATION_MESSAGE);
                Body_Panel.removeAll();
                Body_Panel.add(Bill_Panel);
                Body_Panel.repaint();
                Body_Panel.revalidate();
            }
             
             
         }

    }//GEN-LAST:event_btnConfirmPaymentActionPerformed

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
    private javax.swing.JPanel Bill_Panel;
    private javax.swing.JPanel Body_Panel;
    private javax.swing.JPanel Cart_panel;
    private javax.swing.JPanel Home;
    private javax.swing.JPanel Home_Panel_Canteiner;
    private javax.swing.JPanel Payment;
    private javax.swing.JPanel Product_DiscriptionPanel;
    private javax.swing.JPanel Product_card_Panel;
    private javax.swing.JPanel Product_card_Panel1;
    private javax.swing.JPanel Product_page;
    private javax.swing.JPanel Product_search_panel;
    private javax.swing.JButton btnBuy;
    private javax.swing.JButton btnConfirmPayment;
    private javax.swing.JButton btnRemove;
    private javax.swing.JButton btnSearch2;
    private javax.swing.JButton btn_addtocart;
    private javax.swing.JButton btnsearch1;
    private javax.swing.JLabel img_Product;
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
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel lbl_aboutUs;
    private javax.swing.JLabel lbl_cart;
    private javax.swing.JLabel lbl_home;
    private javax.swing.JLabel lbl_products;
    private javax.swing.JPanel nav_bar;
    private javax.swing.JTable tblCart;
    private javax.swing.JTextField txtCardNumber;
    private javax.swing.JTextField txtCardOwnerName;
    private javax.swing.JTextField txtCvCode;
    private javax.swing.JTextField txtExpDate;
    private javax.swing.JLabel txtPD_Id;
    private javax.swing.JLabel txtProduct_Category;
    private javax.swing.JLabel txtProduct_Description;
    private javax.swing.JLabel txtProduct_Name;
    private javax.swing.JLabel txtProduct_Price;
    private javax.swing.JLabel txtProduct__Agegroup;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtSearch1;
    private javax.swing.JLabel txtTotalPrice;
    // End of variables declaration//GEN-END:variables
}
