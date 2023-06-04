/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import Classes.Category;
import Classes.Product;
import Classes.UserAccounts;
import java.awt.Color;
import java.awt.Image;
import java.io.File;
import javax.swing.Timer;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Sham
 */
public class Administrator_Panel extends javax.swing.JFrame {

    private DefaultTableModel categoryTableModel;
    private Category category;
    Product toy = new Product();
    private File selectedFile; // Declare a class-level variable to store the selected file

    private Timer timer;
    private int position = 0;
    private int delay = 2000; // Adjust the delay (in milliseconds) between slides
    UserAccounts newuser = new UserAccounts();
    
    
    public Administrator_Panel() {
        initComponents();
        
        category = new Category();
       

        show(position);
        // Start the timer for auto sliding
        timer = new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                position++;
                if (position >= takeImage().length) {
                    position = 0; // Restart from the beginning when reaching the end
                }
                show(position);
            }
        });
        timer.start();

    }

    public String[] takeImage() {
        File f = new File(getClass().getResource("/Slider").getFile());
        String[] images = f.list();
        return images;
    }

    public void show(int index) {
        String[] images = takeImage();
        String img = images[index];
        ImageIcon icon = new ImageIcon(getClass().getResource("/Slider/" + img));
        Image image = icon.getImage().getScaledInstance(admin_slider.getWidth(), admin_slider.getHeight(), Image.SCALE_SMOOTH);
        admin_slider.setIcon(new ImageIcon(image));
        admin_slider.revalidate(); // Ensure proper rendering of the new image
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        Admin_menu_panel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnAdmin_Customers = new javax.swing.JButton();
        btnAdmin_Categories = new javax.swing.JButton();
        btnAdmin_product = new javax.swing.JButton();
        btnAdmin_Home = new javax.swing.JButton();
        Admin_nav_bar = new javax.swing.JPanel();
        Admin_body_panel = new javax.swing.JPanel();
        View_Products = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        btn_add_category1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        Admin_home_page = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        admin_slider = new javax.swing.JLabel();
        Manage_categoryes = new javax.swing.JPanel();
        btn_add_category = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_Category = new javax.swing.JTable();
        jLabel14 = new javax.swing.JLabel();
        txt_newCategoryname = new javax.swing.JTextField();
        Add_Products_Panel = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtToy_ID = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtModel_Name = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtPrice = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txtQty = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txtAge_Group = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        cmbCategory = new javax.swing.JComboBox<>();
        jLabel22 = new javax.swing.JLabel();
        btn_add_new_product = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtDescription = new javax.swing.JTextArea();
        imgProduct = new javax.swing.JLabel();
        View_Customer_details = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        UserTable = new javax.swing.JTable();
        jLabel23 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Admin_menu_panel.setBackground(new java.awt.Color(102, 102, 102));
        Admin_menu_panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/admin.png"))); // NOI18N
        jLabel1.setText("Admin Panel");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Admin_menu_panel.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 160, 140));

        btnAdmin_Customers.setBackground(new java.awt.Color(102, 102, 102));
        btnAdmin_Customers.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        btnAdmin_Customers.setForeground(new java.awt.Color(255, 255, 255));
        btnAdmin_Customers.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-people-30 (1).png"))); // NOI18N
        btnAdmin_Customers.setText("Customers");
        btnAdmin_Customers.setToolTipText("Customers");
        btnAdmin_Customers.setBorder(null);
        btnAdmin_Customers.setBorderPainted(false);
        btnAdmin_Customers.setContentAreaFilled(false);
        btnAdmin_Customers.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnAdmin_Customers.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAdmin_Customers.setOpaque(true);
        btnAdmin_Customers.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnAdmin_Customers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAdmin_CustomersMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAdmin_CustomersMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnAdmin_CustomersMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnAdmin_CustomersMouseReleased(evt);
            }
        });
        btnAdmin_Customers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdmin_CustomersActionPerformed(evt);
            }
        });
        Admin_menu_panel.add(btnAdmin_Customers, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 500, 160, 110));

        btnAdmin_Categories.setBackground(new java.awt.Color(102, 102, 102));
        btnAdmin_Categories.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        btnAdmin_Categories.setForeground(new java.awt.Color(255, 255, 255));
        btnAdmin_Categories.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-categorize-30.png"))); // NOI18N
        btnAdmin_Categories.setText("Categories");
        btnAdmin_Categories.setToolTipText("Categories");
        btnAdmin_Categories.setBorder(null);
        btnAdmin_Categories.setBorderPainted(false);
        btnAdmin_Categories.setContentAreaFilled(false);
        btnAdmin_Categories.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnAdmin_Categories.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAdmin_Categories.setOpaque(true);
        btnAdmin_Categories.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnAdmin_Categories.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAdmin_CategoriesMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAdmin_CategoriesMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnAdmin_CategoriesMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnAdmin_CategoriesMouseReleased(evt);
            }
        });
        btnAdmin_Categories.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdmin_CategoriesActionPerformed(evt);
            }
        });
        Admin_menu_panel.add(btnAdmin_Categories, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 290, 160, 110));

        btnAdmin_product.setBackground(new java.awt.Color(102, 102, 102));
        btnAdmin_product.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        btnAdmin_product.setForeground(new java.awt.Color(255, 255, 255));
        btnAdmin_product.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-packing-30.png"))); // NOI18N
        btnAdmin_product.setText("Products");
        btnAdmin_product.setToolTipText("Products");
        btnAdmin_product.setBorder(null);
        btnAdmin_product.setBorderPainted(false);
        btnAdmin_product.setContentAreaFilled(false);
        btnAdmin_product.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnAdmin_product.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAdmin_product.setOpaque(true);
        btnAdmin_product.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnAdmin_product.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAdmin_productMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAdmin_productMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnAdmin_productMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnAdmin_productMouseReleased(evt);
            }
        });
        btnAdmin_product.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdmin_productActionPerformed(evt);
            }
        });
        Admin_menu_panel.add(btnAdmin_product, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 390, 160, 110));

        btnAdmin_Home.setBackground(new java.awt.Color(102, 102, 102));
        btnAdmin_Home.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        btnAdmin_Home.setForeground(new java.awt.Color(255, 255, 255));
        btnAdmin_Home.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-home-30.png"))); // NOI18N
        btnAdmin_Home.setText("Home");
        btnAdmin_Home.setToolTipText("Home");
        btnAdmin_Home.setBorder(null);
        btnAdmin_Home.setBorderPainted(false);
        btnAdmin_Home.setContentAreaFilled(false);
        btnAdmin_Home.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnAdmin_Home.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAdmin_Home.setOpaque(true);
        btnAdmin_Home.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnAdmin_Home.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAdmin_HomeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAdmin_HomeMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnAdmin_HomeMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnAdmin_HomeMouseReleased(evt);
            }
        });
        btnAdmin_Home.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdmin_HomeActionPerformed(evt);
            }
        });
        Admin_menu_panel.add(btnAdmin_Home, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 160, 110));

        jPanel1.add(Admin_menu_panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 160, 730));

        Admin_nav_bar.setBackground(new java.awt.Color(102, 102, 102));
        Admin_nav_bar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(Admin_nav_bar, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 0, 1000, 60));

        Admin_body_panel.setLayout(new java.awt.CardLayout());

        View_Products.setBackground(new java.awt.Color(255, 255, 255));
        View_Products.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel15.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel15.setText("Manage Products");
        jLabel15.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        View_Products.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 50, -1, -1));

        btn_add_category1.setBackground(new java.awt.Color(0, 204, 153));
        btn_add_category1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btn_add_category1.setText("Add New Products");
        btn_add_category1.setBorderPainted(false);
        btn_add_category1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_add_category1ActionPerformed(evt);
            }
        });
        View_Products.add(btn_add_category1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 180, -1, -1));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        View_Products.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 270, 850, 350));

        Admin_body_panel.add(View_Products, "card4");

        Admin_home_page.setBackground(new java.awt.Color(240, 248, 233));
        Admin_home_page.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setText("Sold Items");
        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel2.setFocusable(false);
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 150, 40));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-shopping-cart-30 (1).png"))); // NOI18N
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 20, 30, 40));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Sold Items");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 130, 20));

        Admin_home_page.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 170, 220, 100));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel4.setText("Administrator Home");
        jLabel4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Admin_home_page.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 50, -1, -1));

        jPanel3.setBackground(new java.awt.Color(153, 153, 153));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel5.setText("User Count");
        jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel5.setFocusable(false);
        jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 150, 40));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-people-30.png"))); // NOI18N
        jLabel6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 20, 30, 40));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("User Count");
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 130, 20));

        Admin_home_page.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 170, 220, 100));

        jPanel4.setBackground(new java.awt.Color(153, 153, 153));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel7.setText("Product Count");
        jLabel7.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel7.setFocusable(false);
        jLabel7.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jPanel4.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 150, 40));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-packing-30 (1).png"))); // NOI18N
        jLabel8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel4.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 20, 30, 40));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Product Count");
        jPanel4.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 130, 20));

        Admin_home_page.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 170, 220, 100));

        admin_slider.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Admin_home_page.add(admin_slider, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 330, 670, 310));

        Admin_body_panel.add(Admin_home_page, "card2");

        Manage_categoryes.setBackground(new java.awt.Color(255, 255, 255));
        Manage_categoryes.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_add_category.setBackground(new java.awt.Color(0, 204, 153));
        btn_add_category.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btn_add_category.setText("Add");
        btn_add_category.setBorderPainted(false);
        btn_add_category.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_add_categoryActionPerformed(evt);
            }
        });
        Manage_categoryes.add(btn_add_category, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 270, -1, -1));

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel13.setText("Manage Categories");
        jLabel13.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Manage_categoryes.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 50, -1, -1));

        tbl_Category.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Category Name", "Action"
            }
        ));
        tbl_Category.setToolTipText("");
        tbl_Category.setFillsViewportHeight(true);
        tbl_Category.setRowHeight(20);
        tbl_Category.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_CategoryMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_Category);

        Manage_categoryes.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(522, 219, 420, 360));

        jLabel14.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel14.setText("Category Name");
        Manage_categoryes.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 240, 140, -1));

        txt_newCategoryname.setFont(new java.awt.Font("Times New Roman", 0, 17)); // NOI18N
        txt_newCategoryname.setForeground(new java.awt.Color(0, 77, 64));
        txt_newCategoryname.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 77, 64)));
        txt_newCategoryname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_newCategorynameKeyReleased(evt);
            }
        });
        Manage_categoryes.add(txt_newCategoryname, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 270, 180, 30));

        Admin_body_panel.add(Manage_categoryes, "card3");

        Add_Products_Panel.setBackground(new java.awt.Color(255, 255, 255));
        Add_Products_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel16.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel16.setText("Add New Products");
        jLabel16.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Add_Products_Panel.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 50, -1, -1));

        jLabel12.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel12.setText("Toy ID:");
        Add_Products_Panel.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 170, 104, -1));

        txtToy_ID.setFont(new java.awt.Font("Times New Roman", 0, 19)); // NOI18N
        txtToy_ID.setForeground(new java.awt.Color(0, 77, 64));
        txtToy_ID.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 77, 64)));
        Add_Products_Panel.add(txtToy_ID, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 170, 280, -1));

        jLabel17.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel17.setText("Model Name:");
        Add_Products_Panel.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 210, 120, -1));

        txtModel_Name.setFont(new java.awt.Font("Times New Roman", 0, 19)); // NOI18N
        txtModel_Name.setForeground(new java.awt.Color(0, 77, 64));
        txtModel_Name.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 77, 64)));
        Add_Products_Panel.add(txtModel_Name, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 210, 280, -1));

        jLabel18.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel18.setText("Price:");
        Add_Products_Panel.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 250, 104, -1));

        txtPrice.setFont(new java.awt.Font("Times New Roman", 0, 19)); // NOI18N
        txtPrice.setForeground(new java.awt.Color(0, 77, 64));
        txtPrice.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 77, 64)));
        Add_Products_Panel.add(txtPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 250, 90, -1));

        jLabel19.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel19.setText("Quantity:");
        Add_Products_Panel.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 290, 104, -1));

        txtQty.setFont(new java.awt.Font("Times New Roman", 0, 19)); // NOI18N
        txtQty.setForeground(new java.awt.Color(0, 77, 64));
        txtQty.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 77, 64)));
        Add_Products_Panel.add(txtQty, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 290, 90, -1));

        jLabel20.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel20.setText("Age Group:");
        Add_Products_Panel.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 330, 104, -1));

        txtAge_Group.setFont(new java.awt.Font("Times New Roman", 0, 19)); // NOI18N
        txtAge_Group.setForeground(new java.awt.Color(0, 77, 64));
        txtAge_Group.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 77, 64)));
        Add_Products_Panel.add(txtAge_Group, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 330, 40, -1));

        jLabel21.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel21.setText("Category:");
        Add_Products_Panel.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 370, 104, -1));

        cmbCategory.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        cmbCategory.setBorder(null);
        Add_Products_Panel.add(cmbCategory, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 370, 220, -1));

        jLabel22.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel22.setText("Product Description:");
        Add_Products_Panel.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 410, 170, -1));

        btn_add_new_product.setBackground(new java.awt.Color(0, 204, 153));
        btn_add_new_product.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btn_add_new_product.setText("Add");
        btn_add_new_product.setBorderPainted(false);
        btn_add_new_product.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_add_new_productActionPerformed(evt);
            }
        });
        Add_Products_Panel.add(btn_add_new_product, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 570, 820, -1));

        txtDescription.setColumns(20);
        txtDescription.setFont(new java.awt.Font("Times New Roman", 0, 19)); // NOI18N
        txtDescription.setForeground(new java.awt.Color(0, 77, 64));
        txtDescription.setRows(5);
        txtDescription.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 0));
        txtDescription.setHighlighter(null);
        jScrollPane3.setViewportView(txtDescription);

        Add_Products_Panel.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 410, -1, 130));

        imgProduct.setBackground(new java.awt.Color(204, 204, 204));
        imgProduct.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imgProduct.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8-add-image-48.png"))); // NOI18N
        imgProduct.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        imgProduct.setMaximumSize(new java.awt.Dimension(100, 100));
        imgProduct.setMinimumSize(new java.awt.Dimension(100, 100));
        imgProduct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imgProductMouseClicked(evt);
            }
        });
        Add_Products_Panel.add(imgProduct, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 180, 340, 340));

        Admin_body_panel.add(Add_Products_Panel, "card5");

        View_Customer_details.setBackground(new java.awt.Color(255, 255, 255));
        View_Customer_details.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        UserTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        UserTable.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        UserTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "First Name", "Last Name", "User Email"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        UserTable.setToolTipText("");
        UserTable.setEnabled(false);
        UserTable.setGridColor(new java.awt.Color(0, 0, 0));
        UserTable.setRowSelectionAllowed(false);
        UserTable.setShowGrid(true);
        UserTable.setUpdateSelectionOnSort(false);
        jScrollPane4.setViewportView(UserTable);

        View_Customer_details.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 180, 820, 430));

        jLabel23.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel23.setText("View Customer Details");
        jLabel23.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        View_Customer_details.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 50, -1, -1));

        Admin_body_panel.add(View_Customer_details, "card5");

        jPanel1.add(Admin_body_panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 60, 1000, 670));

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

    private void btnAdmin_CustomersMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAdmin_CustomersMouseEntered
        btnAdmin_Customers.setBackground(new Color(0, 77, 64));
    }//GEN-LAST:event_btnAdmin_CustomersMouseEntered

    private void btnAdmin_CustomersMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAdmin_CustomersMouseExited
        btnAdmin_Customers.setBackground(new Color(102, 102, 102));
    }//GEN-LAST:event_btnAdmin_CustomersMouseExited

    private void btnAdmin_CustomersMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAdmin_CustomersMousePressed
        btnAdmin_Customers.setBackground(new Color(0, 77, 64));
    }//GEN-LAST:event_btnAdmin_CustomersMousePressed

    private void btnAdmin_CustomersMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAdmin_CustomersMouseReleased
        btnAdmin_Customers.setBackground(new Color(102, 102, 102));
    }//GEN-LAST:event_btnAdmin_CustomersMouseReleased

    private void btnAdmin_CategoriesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAdmin_CategoriesMouseEntered
        btnAdmin_Categories.setBackground(new Color(0, 77, 64));
    }//GEN-LAST:event_btnAdmin_CategoriesMouseEntered

    private void btnAdmin_CategoriesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAdmin_CategoriesMouseExited
        btnAdmin_Categories.setBackground(new Color(102, 102, 102));
    }//GEN-LAST:event_btnAdmin_CategoriesMouseExited

    private void btnAdmin_CategoriesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAdmin_CategoriesMousePressed
        btnAdmin_Categories.setBackground(new Color(0, 77, 64));
    }//GEN-LAST:event_btnAdmin_CategoriesMousePressed

    private void btnAdmin_CategoriesMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAdmin_CategoriesMouseReleased
        btnAdmin_Categories.setBackground(new Color(102, 102, 102));
    }//GEN-LAST:event_btnAdmin_CategoriesMouseReleased

    private void btnAdmin_productMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAdmin_productMouseEntered
        btnAdmin_product.setBackground(new Color(0, 77, 64));
    }//GEN-LAST:event_btnAdmin_productMouseEntered

    private void btnAdmin_productMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAdmin_productMouseExited
        btnAdmin_product.setBackground(new Color(102, 102, 102));
    }//GEN-LAST:event_btnAdmin_productMouseExited

    private void btnAdmin_productMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAdmin_productMousePressed
        btnAdmin_product.setBackground(new Color(0, 77, 64));
    }//GEN-LAST:event_btnAdmin_productMousePressed

    private void btnAdmin_productMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAdmin_productMouseReleased
        btnAdmin_product.setBackground(new Color(102, 102, 102));
    }//GEN-LAST:event_btnAdmin_productMouseReleased

    private void btnAdmin_productActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdmin_productActionPerformed
        Admin_body_panel.removeAll();
        Admin_body_panel.add(View_Products);
        Admin_body_panel.repaint();
        Admin_body_panel.revalidate();
    }//GEN-LAST:event_btnAdmin_productActionPerformed

    private void btnAdmin_HomeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAdmin_HomeMouseExited
        btnAdmin_Home.setBackground(new Color(102, 102, 102));

    }//GEN-LAST:event_btnAdmin_HomeMouseExited

    private void btnAdmin_HomeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAdmin_HomeMousePressed
        btnAdmin_Home.setBackground(new Color(0, 77, 64));
    }//GEN-LAST:event_btnAdmin_HomeMousePressed

    private void btnAdmin_HomeMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAdmin_HomeMouseReleased
        btnAdmin_Home.setBackground(new Color(102, 102, 102));

    }//GEN-LAST:event_btnAdmin_HomeMouseReleased

    private void btnAdmin_HomeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAdmin_HomeMouseEntered
        btnAdmin_Home.setBackground(new Color(0, 77, 64));
    }//GEN-LAST:event_btnAdmin_HomeMouseEntered

    private void btnAdmin_CategoriesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdmin_CategoriesActionPerformed
        Admin_body_panel.removeAll();
        Admin_body_panel.add(Manage_categoryes);
        Admin_body_panel.repaint();
        Admin_body_panel.revalidate();

        // Category category = new Category();
        category.populateCategoryTable(tbl_Category);
    }//GEN-LAST:event_btnAdmin_CategoriesActionPerformed

    private void btnAdmin_HomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdmin_HomeActionPerformed
        Admin_body_panel.removeAll();
        Admin_body_panel.add(Admin_home_page);
        Admin_body_panel.repaint();
        Admin_body_panel.revalidate();
    }//GEN-LAST:event_btnAdmin_HomeActionPerformed

    private void tbl_CategoryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_CategoryMouseClicked

        int selectedRow = tbl_Category.getSelectedRow();
        if (selectedRow != -1) {
            int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this category?", "Confirmation", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                String categoryName = (String) tbl_Category.getValueAt(selectedRow, 0);
                DefaultTableModel tableModel = (DefaultTableModel) tbl_Category.getModel();
                category.deleteCategory(categoryName, tableModel, selectedRow);
            }
        }
    }//GEN-LAST:event_tbl_CategoryMouseClicked

    private void btn_add_categoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_add_categoryActionPerformed
        boolean valid = true;
        String CategoryNamePattern = "^[a-zA-Z]+$";
        String Name = txt_newCategoryname.getText();
        boolean CategoryExists = category.isCategoryExist(txt_newCategoryname.getText());

        if (txt_newCategoryname.getText().isEmpty()) {
            JOptionPane:
            JOptionPane.showMessageDialog(null, "Feild can't be Empty", "Add New Category", JOptionPane.ERROR_MESSAGE);
            valid = false;
        } else if (!Name.matches(CategoryNamePattern)) {
            JOptionPane:
            JOptionPane.showMessageDialog(null, "Invalid Category Name", "Add New Category", JOptionPane.ERROR_MESSAGE);
            valid = false;
        } else if (CategoryExists) {
            JOptionPane.showMessageDialog(null, "Category name already exists!!", "Add New Category", JOptionPane.ERROR_MESSAGE);
            valid = false;
        }

        if (valid) {
            String CName = txt_newCategoryname.getText().replace(" ", "_");

            // Set the name of the category
            category.setName(CName);

            // Call the addCategory method
            boolean added = category.addCategory();

            if (added) {
                JOptionPane.showMessageDialog(null, " Add Successfully", "Add New Category", JOptionPane.INFORMATION_MESSAGE);
                txt_newCategoryname.setText("");
                category.populateCategoryTable(tbl_Category);
            } else {
                JOptionPane.showMessageDialog(null, "Sorry! Somthing Went Wrong", "Add New Category", JOptionPane.WARNING_MESSAGE);
            }
        }
    }//GEN-LAST:event_btn_add_categoryActionPerformed

    private void txt_newCategorynameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_newCategorynameKeyReleased
        String NamePattern = "^[a-zA-Z]+$";
        String CategoryName = txt_newCategoryname.getText();

        if (!CategoryName.matches(NamePattern)) {
            txt_newCategoryname.setBorder(BorderFactory.createLineBorder(Color.RED));
        } else {
            txt_newCategoryname.setBorder(BorderFactory.createLineBorder(Color.decode("#0ed145")));
        }
    }//GEN-LAST:event_txt_newCategorynameKeyReleased

    private void btn_add_category1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_add_category1ActionPerformed
        Admin_body_panel.removeAll();
        Admin_body_panel.add(Add_Products_Panel);
        Admin_body_panel.repaint();
        Admin_body_panel.revalidate();

        category.populateCategoryComboBox(cmbCategory);
    }//GEN-LAST:event_btn_add_category1ActionPerformed

    private void btn_add_new_productActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_add_new_productActionPerformed
        boolean valid = true;

        boolean tidExists = toy.isPIDExist(txtToy_ID.getText());

        if (txtToy_ID.getText().isEmpty()
                || txtModel_Name.getText().isEmpty()
                || txtPrice.getText().isEmpty()
                || txtAge_Group.getText().isEmpty()
                || txtQty.getText().isEmpty()
                || cmbCategory.getSelectedItem().toString().isEmpty()
                || txtDescription.getText().isEmpty()) {
            JOptionPane:
            JOptionPane.showMessageDialog(null, "Feild can't be Empty", "Add New Product", JOptionPane.ERROR_MESSAGE);
            valid = false;
        } else if (tidExists) {
            JOptionPane.showMessageDialog(null, "TID already exists!", "Add New Product", JOptionPane.ERROR_MESSAGE);
            valid = false;
        } else {
            try {
                int price = Integer.parseInt(txtPrice.getText());
                int TQty = Integer.parseInt(txtQty.getText());
                int ageGroup = Integer.parseInt(txtAge_Group.getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input! Price, Quantity and age group fields should be integers.", "Error", JOptionPane.ERROR_MESSAGE);
                valid = false;
            }
        }
        if (selectedFile == null) {
            JOptionPane.showMessageDialog(null, "Please Select a image!", "Add New Product", JOptionPane.ERROR_MESSAGE);
            valid = false;
        }

        String imageName = selectedFile.getName();
        if (selectedFile != null) {
            try {
                Path sourcePath = selectedFile.toPath();
                Path destinationPath = Path.of("src/Products", selectedFile.getName());

                // Copy the file to the destination directory
                Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);

                // System.out.println("Image copied successfully.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            //  System.out.println("No image selected.");
        }

        if (valid) {
            String Tid = txtToy_ID.getText();
            String MName = txtModel_Name.getText().replace(" ", "_");
            String Price = txtPrice.getText();
            String qty = txtQty.getText();
            String age = txtAge_Group.getText();
            String Category = cmbCategory.getSelectedItem().toString();
            String Description = txtDescription.getText().replace(" ", "_");

            Product newToys = new Product(Tid, MName, Price, qty, age, Category, Description, imageName);
            if (newToys.addPRODUCTS()) {
                JOptionPane.showMessageDialog(null, " Add Successfully", "Add New Product", JOptionPane.INFORMATION_MESSAGE);
                txtToy_ID.setText("");
                txtModel_Name.setText("");
                txtPrice.setText("");
                txtQty.setText("");
                txtAge_Group.setText("");
                txtDescription.setText("");

                selectedFile = null;
                ImageIcon defaultImageIcon = new ImageIcon("src/Images/icons8-add-image-48.png");
                imgProduct.setIcon(defaultImageIcon);
            } else {
                JOptionPane.showMessageDialog(null, "Sorry! Somthing Went Wrong", "Add New Products", JOptionPane.WARNING_MESSAGE);
            }

        }
    }//GEN-LAST:event_btn_add_new_productActionPerformed

    private void imgProductMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgProductMouseClicked
        JFileChooser fileChooser = new JFileChooser();

        // Set the file chooser to select only image files
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "jpeg", "png", "gif");
        fileChooser.setFileFilter(filter);

        // Open the file chooser dialog
        int result = fileChooser.showOpenDialog(this);  // Replace 'this' with your frame reference

        // Check if a file was selected
        if (result == JFileChooser.APPROVE_OPTION) {
            // Get the selected file
            selectedFile = fileChooser.getSelectedFile();

            // Display the selected image in the imgProduct label
            try {
                BufferedImage originalImage = ImageIO.read(selectedFile);
                Image scaledImage = originalImage.getScaledInstance(imgProduct.getWidth(), imgProduct.getHeight(), Image.SCALE_SMOOTH);
                ImageIcon imageIcon = new ImageIcon(scaledImage);
                imgProduct.setIcon(imageIcon);

                // Display the selected image name without the file format
                String imageName = selectedFile.getName();
                int dotIndex = imageName.lastIndexOf(".");
                if (dotIndex > 0) {
                    imageName = imageName.substring(0, dotIndex);
                }
                //   System.out.println("Selected file: " + imageName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_imgProductMouseClicked

    private void btnAdmin_CustomersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdmin_CustomersActionPerformed
       Admin_body_panel.removeAll();
        Admin_body_panel.add(View_Customer_details);
        Admin_body_panel.repaint();
        Admin_body_panel.revalidate();
        
        DefaultTableModel model = (DefaultTableModel) UserTable.getModel();
        model.setRowCount(0);
        String allUser = newuser.viewAllUser();
        String[] pro = allUser.split("/");

        for (int i = 0; i < pro.length; i++) {
            String line = pro[i].toString().trim();
            String[] dataRow = line.split(" ");
            model.addRow(dataRow);
        }
    }//GEN-LAST:event_btnAdmin_CustomersActionPerformed

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
            java.util.logging.Logger.getLogger(Administrator_Panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Administrator_Panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Administrator_Panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Administrator_Panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Administrator_Panel().setVisible(true);
            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Add_Products_Panel;
    private javax.swing.JPanel Admin_body_panel;
    private javax.swing.JPanel Admin_home_page;
    private javax.swing.JPanel Admin_menu_panel;
    private javax.swing.JPanel Admin_nav_bar;
    private javax.swing.JPanel Manage_categoryes;
    private javax.swing.JTable UserTable;
    private javax.swing.JPanel View_Customer_details;
    private javax.swing.JPanel View_Products;
    private javax.swing.JLabel admin_slider;
    private javax.swing.JButton btnAdmin_Categories;
    private javax.swing.JButton btnAdmin_Customers;
    private javax.swing.JButton btnAdmin_Home;
    private javax.swing.JButton btnAdmin_product;
    private javax.swing.JButton btn_add_category;
    private javax.swing.JButton btn_add_category1;
    private javax.swing.JButton btn_add_new_product;
    private javax.swing.JComboBox<String> cmbCategory;
    private javax.swing.JLabel imgProduct;
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
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable tbl_Category;
    private javax.swing.JTextField txtAge_Group;
    private javax.swing.JTextArea txtDescription;
    private javax.swing.JTextField txtModel_Name;
    private javax.swing.JTextField txtPrice;
    private javax.swing.JTextField txtQty;
    private javax.swing.JTextField txtToy_ID;
    private javax.swing.JTextField txt_newCategoryname;
    // End of variables declaration//GEN-END:variables
}
