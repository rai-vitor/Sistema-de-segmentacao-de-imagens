package segmentacaodeimagem;

import br.ufrn.imd.lp2.imagesegmentation.ImageInformation;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.SpinnerNumberModel;

/**
 * Classe de controle de interface. Todas as operações da interface do sistema
 * são controladas por ela
 *
 * @author Hiago Miguel & Rai Vitor.
 */
public class FormSegmentacao extends javax.swing.JFrame {

    JFileChooser fileChooser; /*Guarda as informacoes da imagem selecionada pelo usuario */
    String path = null; /*Caminho absoluto da imagem */
    JLabel imagem; /*Representa a imagem orignal */
    ImageInformation seg;/*Contem a imagem segmentada */

    /**
     * Cria um novo form FormSegmentacao
     */
    public FormSegmentacao() {
        initComponents();
        ConfigForm();
    }

    /**
     * Inicializa componentes do form
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fileDialog = new javax.swing.JDialog();
        panelCtrl = new javax.swing.JPanel();
        valBlur = new javax.swing.JSpinner();
        valRadius = new javax.swing.JSpinner();
        valSize = new javax.swing.JSpinner();
        labelBlur = new javax.swing.JLabel();
        labelRadius = new javax.swing.JLabel();
        labelSize = new javax.swing.JLabel();
        labelRegioes = new javax.swing.JLabel();
        buttonSegmentar = new javax.swing.JButton();
        buttonRotulos = new javax.swing.JButton();
        labelImg = new javax.swing.JLabel();
        buttonImg = new javax.swing.JButton();
        panelImg = new javax.swing.JPanel();
        panelNotes = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        campoListTags = new javax.swing.JTextArea();
        campoTag = new javax.swing.JTextField();
        buttonAdd = new javax.swing.JButton();

        javax.swing.GroupLayout fileDialogLayout = new javax.swing.GroupLayout(fileDialog.getContentPane());
        fileDialog.getContentPane().setLayout(fileDialogLayout);
        fileDialogLayout.setHorizontalGroup(
            fileDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 638, Short.MAX_VALUE)
        );
        fileDialogLayout.setVerticalGroup(
            fileDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 399, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("frame");
        setMinimumSize(new java.awt.Dimension(400, 200));
        setName("frame"); // NOI18N
        setPreferredSize(new java.awt.Dimension(850, 550));
        getContentPane().setLayout(new java.awt.FlowLayout());

        panelCtrl.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelCtrl.setToolTipText("Ajustes");
        panelCtrl.setMinimumSize(new java.awt.Dimension(150, 400));
        panelCtrl.setName(""); // NOI18N
        panelCtrl.setPreferredSize(new java.awt.Dimension(180, 400));

        labelBlur.setText("BlurLevel:");

        labelRadius.setText("ColorRadius:");

        labelSize.setText("MinSize:");

        labelRegioes.setText("Total de regiões: ");

        buttonSegmentar.setText("Segmentar");
        buttonSegmentar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSegmentarActionPerformed(evt);
            }
        });

        buttonRotulos.setText("Mostrar mapa de rótulos");
        buttonRotulos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRotulosActionPerformed(evt);
            }
        });

        labelImg.setText("NomeImg:");

        buttonImg.setText("Selecionar Imagem");
        buttonImg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonImgActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelCtrlLayout = new javax.swing.GroupLayout(panelCtrl);
        panelCtrl.setLayout(panelCtrlLayout);
        panelCtrlLayout.setHorizontalGroup(
            panelCtrlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCtrlLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelCtrlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCtrlLayout.createSequentialGroup()
                        .addGroup(panelCtrlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(buttonRotulos, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(buttonSegmentar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(buttonImg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(panelCtrlLayout.createSequentialGroup()
                                .addGroup(panelCtrlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelSize)
                                    .addComponent(labelRadius)
                                    .addComponent(labelBlur))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(panelCtrlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(valSize)
                                    .addComponent(valRadius, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(valBlur, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE))))
                        .addGap(37, 37, 37))
                    .addGroup(panelCtrlLayout.createSequentialGroup()
                        .addGroup(panelCtrlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelRegioes)
                            .addComponent(labelImg))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        panelCtrlLayout.setVerticalGroup(
            panelCtrlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCtrlLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(panelCtrlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(valBlur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelBlur))
                .addGap(33, 33, 33)
                .addGroup(panelCtrlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(valRadius, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelRadius))
                .addGap(34, 34, 34)
                .addGroup(panelCtrlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(valSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelSize))
                .addGap(18, 18, 18)
                .addComponent(labelRegioes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelImg)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
                .addComponent(buttonImg)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonSegmentar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonRotulos)
                .addContainerGap())
        );

        getContentPane().add(panelCtrl);

        panelImg.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelImg.setPreferredSize(new java.awt.Dimension(340, 510));

        javax.swing.GroupLayout panelImgLayout = new javax.swing.GroupLayout(panelImg);
        panelImg.setLayout(panelImgLayout);
        panelImgLayout.setHorizontalGroup(
            panelImgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 334, Short.MAX_VALUE)
        );
        panelImgLayout.setVerticalGroup(
            panelImgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        getContentPane().add(panelImg);

        panelNotes.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelNotes.setPreferredSize(new java.awt.Dimension(200, 400));

        campoListTags.setColumns(3);
        campoListTags.setRows(5);
        campoListTags.setTabSize(4);
        jScrollPane1.setViewportView(campoListTags);

        campoTag.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoTagActionPerformed(evt);
            }
        });

        buttonAdd.setText("+");
        buttonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelNotesLayout = new javax.swing.GroupLayout(panelNotes);
        panelNotes.setLayout(panelNotesLayout);
        panelNotesLayout.setHorizontalGroup(
            panelNotesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNotesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelNotesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                    .addGroup(panelNotesLayout.createSequentialGroup()
                        .addComponent(campoTag)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonAdd)))
                .addContainerGap())
        );
        panelNotesLayout.setVerticalGroup(
            panelNotesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNotesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelNotesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoTag, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonAdd))
                .addContainerGap(178, Short.MAX_VALUE))
        );

        getContentPane().add(panelNotes);

        getAccessibleContext().setAccessibleDescription("frame");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Configurações iniciais do layout
     */
    private void ConfigForm() {
        fileChooser = new JFileChooser();
        valBlur.setModel(new SpinnerNumberModel(0.50, 0.00, 100.00, 0.01));
        valRadius.setModel(new SpinnerNumberModel(50, 1, 100, 1));
        valSize.setModel(new SpinnerNumberModel(500, 1, 1000, 10));
    }

    /**
     * Este método é chamado após o botão "Segmentar" ser acionado. A função
     * dele é pegar uma imagem escolhida pelo usuário e fazer a segmentação dela
     */
    private void buttonSegmentarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSegmentarActionPerformed
        // Se o usuário não tiver selecionado nenhuma imagem a função para aqui
        if (path == null) {
            return;
        }
        //Pega os valores da interface
        double blur = (double) valBlur.getValue();
        int radius = (int) valRadius.getValue();
        int size = (int) valSize.getValue();

        seg = SegmentacaoDeImagem.segmentar(path, blur, radius, size);

        labelRegioes.setText("Total de regiões: " + seg.getTotalRegions());
        //imgSegmentada = new JLabel(new ImageIcon(seg.getRegionMarkedImage()));
        addImg(new ImageIcon(seg.getRegionMarkedImage()));
    }//GEN-LAST:event_buttonSegmentarActionPerformed

    /**
     * Este método é chamado após o botão "Mostrar mapa de rótulos" ser
     * acionado. Chama o metodo estatico 'GerarMapaRotulos' da classe
     * SegmentacaoDeImagem.
     */
    private void buttonRotulosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRotulosActionPerformed
        SegmentacaoDeImagem.GerarMapaRotulos(seg);
        addImg(new ImageIcon(seg.getRegionMarkedImage()));
    }//GEN-LAST:event_buttonRotulosActionPerformed

    /**
     * Este método é chamado após o botão 'Selecionar Imagem' ser acionado Abre
     * um dialog para o usuário escolher uma imagem e mostra a imagem na tela.
     * Só aceita imagens jpg.
     */
    private void buttonImgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonImgActionPerformed
        //int returnVal = fileChooser.showOpenDialog(fileDialog);
        //if (returnVal == JFileChooser.APPROVE_OPTION) {
            //File file = fileChooser.getSelectedFile();
            File file = new File("imgs/model.jpg");  
            if (file.getName().contains("jpg")) {
                labelImg.setText("NomeImg: " + file.getName());
                path = file.getAbsolutePath();
                ImageIcon image = new ImageIcon(path);
                addImg(image);
            } else {
                labelImg.setText("Utilize somente imagens jpg");
            }
        //}
    }//GEN-LAST:event_buttonImgActionPerformed

    private void buttonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonAddActionPerformed

    private void campoTagActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoTagActionPerformed
        //Não consigo apagar este método. Se vc conseguir ganha uma jujuba
    }//GEN-LAST:event_campoTagActionPerformed

    /**
     * Adiciona uma imagem na interface e remove outras que estejam adicionadas.
     *
     * @param img - Imagem a ser adicionada
     */
    private void addImg(ImageIcon image) {
        if (imagem != null) {
            panelImg.remove(imagem);
        }

        Image img = image.getImage();
        Image newimg = img.getScaledInstance(400, 400, java.awt.Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(img);
        imagem = new JLabel(newIcon);
        Dimension d = new Dimension(img.getWidth(rootPane), img.getHeight(rootPane));
        imagem.setMaximumSize(d);
        imagem.setSize(d);
        //add(imagem);
        panelImg.add(imagem);
        revalidate();
        repaint();

        //deixa aqui por enquanto. Depois vemos o lugar correto para ela. Pq tentei de outra forma e deu bug kkk
        imagem.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
               System.out.println("x: "+e.getX()+" y: "+e.getY());
               SegmentacaoDeImagem.setPixels(e.getX(),e.getY(), imagem.getHeight(), imagem.getWidth());
               SegmentacaoDeImagem.getPixel(seg);
               SegmentacaoDeImagem.darkenPixels(seg);
               addImg(new ImageIcon(seg.getRegionMarkedImage()));
            }
        });
    }

    /**
     * Método que inicia o sistema
     */
    public static void main(String args[]) {
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
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormSegmentacao().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAdd;
    private javax.swing.JButton buttonImg;
    private javax.swing.JButton buttonRotulos;
    private javax.swing.JButton buttonSegmentar;
    private javax.swing.JTextArea campoListTags;
    private javax.swing.JTextField campoTag;
    private javax.swing.JDialog fileDialog;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelBlur;
    private javax.swing.JLabel labelImg;
    private javax.swing.JLabel labelRadius;
    private javax.swing.JLabel labelRegioes;
    private javax.swing.JLabel labelSize;
    private javax.swing.JPanel panelCtrl;
    private javax.swing.JPanel panelImg;
    private javax.swing.JPanel panelNotes;
    private javax.swing.JSpinner valBlur;
    private javax.swing.JSpinner valRadius;
    private javax.swing.JSpinner valSize;
    // End of variables declaration//GEN-END:variables
}
