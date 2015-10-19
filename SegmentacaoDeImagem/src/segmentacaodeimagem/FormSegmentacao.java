/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package segmentacaodeimagem;

import br.ufrn.imd.lp2.imagesegmentation.ImageInformation;
import br.ufrn.imd.lp2.imagesegmentation.ImageSegmentation;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author rai_desk
 */
public class FormSegmentacao extends javax.swing.JFrame {

    JFileChooser fc;
    String path = null;

    /**
     * Creates new form FormSegmentacao
     */
    public FormSegmentacao() {
        initComponents();
        ConfigForm();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jDialog1 = new javax.swing.JDialog();
        PanelImg = new javax.swing.JPanel();
        PanelCtrl = new javax.swing.JPanel();
        ValBlur = new javax.swing.JSpinner();
        ValRadius = new javax.swing.JSpinner();
        ValSize = new javax.swing.JSpinner();
        LabelBlur = new javax.swing.JLabel();
        LabelRadius = new javax.swing.JLabel();
        LabelSize = new javax.swing.JLabel();
        LabelRegioes = new javax.swing.JLabel();
        ButtonSegmentar = new javax.swing.JButton();
        ButtonRotulos = new javax.swing.JButton();
        ButtonImg = new javax.swing.JButton();
        LabelImg = new javax.swing.JLabel();

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 638, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 399, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("frame");
        setMinimumSize(new java.awt.Dimension(400, 200));
        setName("frame"); // NOI18N
        setPreferredSize(new java.awt.Dimension(800, 550));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        PanelImg.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        PanelImg.setMinimumSize(new java.awt.Dimension(100, 50));
        PanelImg.setName("PanelImg"); // NOI18N
        PanelImg.setPreferredSize(new java.awt.Dimension(400, 400));
        PanelImg.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                PanelImgComponentAdded(evt);
            }
        });

        javax.swing.GroupLayout PanelImgLayout = new javax.swing.GroupLayout(PanelImg);
        PanelImg.setLayout(PanelImgLayout);
        PanelImgLayout.setHorizontalGroup(
            PanelImgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 396, Short.MAX_VALUE)
        );
        PanelImgLayout.setVerticalGroup(
            PanelImgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 396, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 400;
        gridBagConstraints.ipady = 400;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 10, 11, 0);
        getContentPane().add(PanelImg, gridBagConstraints);

        PanelCtrl.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        PanelCtrl.setToolTipText("Ajustes");
        PanelCtrl.setMinimumSize(new java.awt.Dimension(50, 100));
        PanelCtrl.setName(""); // NOI18N
        PanelCtrl.setPreferredSize(new java.awt.Dimension(200, 400));

        LabelBlur.setText("BlurLevel:");

        LabelRadius.setText("ColorRadius:");

        LabelSize.setText("MinSize:");

        LabelRegioes.setText("Total de regiões: ");

        ButtonSegmentar.setText("Segmentar");
        ButtonSegmentar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonSegmentarActionPerformed(evt);
            }
        });

        ButtonRotulos.setText("Mostrar mapa de rótulos");
        ButtonRotulos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonRotulosActionPerformed(evt);
            }
        });

        ButtonImg.setText("Selecionar Imagem");
        ButtonImg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonImgActionPerformed(evt);
            }
        });

        LabelImg.setText("NomeImg:");

        javax.swing.GroupLayout PanelCtrlLayout = new javax.swing.GroupLayout(PanelCtrl);
        PanelCtrl.setLayout(PanelCtrlLayout);
        PanelCtrlLayout.setHorizontalGroup(
            PanelCtrlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelCtrlLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelCtrlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelCtrlLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(PanelCtrlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LabelBlur)
                            .addComponent(LabelRadius)
                            .addComponent(LabelSize))
                        .addGap(23, 23, 23)
                        .addGroup(PanelCtrlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(ValSize)
                            .addComponent(ValRadius, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ValBlur, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE))
                        .addGap(37, 37, 37))
                    .addGroup(PanelCtrlLayout.createSequentialGroup()
                        .addComponent(LabelRegioes)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelCtrlLayout.createSequentialGroup()
                        .addGroup(PanelCtrlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(ButtonImg, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ButtonSegmentar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ButtonRotulos, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(PanelCtrlLayout.createSequentialGroup()
                        .addComponent(LabelImg)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        PanelCtrlLayout.setVerticalGroup(
            PanelCtrlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelCtrlLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(PanelCtrlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ValBlur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LabelBlur))
                .addGap(33, 33, 33)
                .addGroup(PanelCtrlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ValRadius, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LabelRadius))
                .addGap(34, 34, 34)
                .addGroup(PanelCtrlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ValSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LabelSize))
                .addGap(18, 18, 18)
                .addComponent(LabelRegioes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(LabelImg)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 90, Short.MAX_VALUE)
                .addComponent(ButtonImg)
                .addGap(9, 9, 9)
                .addComponent(ButtonSegmentar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ButtonRotulos)
                .addGap(9, 9, 9))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 200;
        gridBagConstraints.ipady = 400;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 10, 11, 10);
        getContentPane().add(PanelCtrl, gridBagConstraints);

        getAccessibleContext().setAccessibleDescription("frame");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ConfigForm() {
        fc = new JFileChooser();
        ValBlur.setModel(new SpinnerNumberModel(0.50, 0.00, 100.00, 0.01));
        ValRadius.setModel(new SpinnerNumberModel(50, 1, 100, 1));
        ValSize.setModel(new SpinnerNumberModel(500, 1, 1000, 10));
    }

    private void ButtonSegmentarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonSegmentarActionPerformed
        if (path == null) {
            return;
        }

        double blur = (double) ValBlur.getValue();
        int radius = (int) ValRadius.getValue();
        int size = (int) ValSize.getValue();

        ImageInformation seg = ImageSegmentation.performSegmentation(path, blur, radius, size);
        
        // Impressão na tela da quantidade de regiões gerada
        LabelRegioes.setText("Total de regiões: " + seg.getTotalRegions());
        // Criação de um JFrame e inserção de 2 JLabels com cada uma das imagens.
        //PanelImg.add(new JLabel(new ImageIcon(seg.getOriginalImage())));

        JFrame frame = new JFrame();
        frame.getContentPane().setLayout(new FlowLayout());
        frame.getContentPane().add(new JLabel(new ImageIcon(seg.getOriginalImage()))); // Imagem original
        frame.getContentPane().add(new JLabel(new ImageIcon(seg.getRegionMarkedImage()))); // Imagem segmentada
        frame.pack();
        frame.setVisible(true);
    }//GEN-LAST:event_ButtonSegmentarActionPerformed

    private void PanelImgComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_PanelImgComponentAdded
        System.out.println(evt);
    }//GEN-LAST:event_PanelImgComponentAdded

    private void ButtonRotulosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonRotulosActionPerformed
    }//GEN-LAST:event_ButtonRotulosActionPerformed

    private void ButtonImgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonImgActionPerformed
        int returnVal = fc.showOpenDialog(jDialog1);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            if (file.getName().contains("jpg")) {
                LabelImg.setText("NomeImg: " + file.getName());
                path = file.getAbsolutePath();
                ImageIcon image = new ImageIcon(path);
                JLabel label = new JLabel("", image, JLabel.CENTER);
                PanelImg = new JPanel();
                PanelImg.add( label, BorderLayout.CENTER );
                PanelImg.setVisible(true);
                PanelImg.setLayout(null);
            } else {
                LabelImg.setText("Utilize somente imagens jpg");
            }
        }
    }//GEN-LAST:event_ButtonImgActionPerformed

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
            java.util.logging.Logger.getLogger(FormSegmentacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormSegmentacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormSegmentacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormSegmentacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormSegmentacao().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonImg;
    private javax.swing.JButton ButtonRotulos;
    private javax.swing.JButton ButtonSegmentar;
    private javax.swing.JLabel LabelBlur;
    private javax.swing.JLabel LabelImg;
    private javax.swing.JLabel LabelRadius;
    private javax.swing.JLabel LabelRegioes;
    private javax.swing.JLabel LabelSize;
    private javax.swing.JPanel PanelCtrl;
    private javax.swing.JPanel PanelImg;
    private javax.swing.JSpinner ValBlur;
    private javax.swing.JSpinner ValRadius;
    private javax.swing.JSpinner ValSize;
    private javax.swing.JDialog jDialog1;
    // End of variables declaration//GEN-END:variables
}
